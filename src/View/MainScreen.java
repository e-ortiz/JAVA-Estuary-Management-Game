package View;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.*;

import Main.mData;
import Model.Quest;

public class MainScreen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Map map;
	// private Health hp;
	private JButton End;
	private MouseListener mouse;
	

	private BufferedImage[] quest_bufferedImage;
	private ArrayList<Quest> quests_bad;
	private ArrayList<Quest> quests_good;
	private int nhp;
	private int ntime;
	public static int width, height;
	public static boolean isFirst = true;
	public static BufferedImage main_map;
	public static BufferedImage quest_img[];
	public static Point mcl = new Point(0, 0);
	private static MainScreenUI mui;
	private static int HealthWidth = 4;
	private static int HealthHeight = 50;
	private int total_health_length = 100 * HealthWidth;
	private int HealthX;
	private int HealthY = 10;
	private int size = 100;
	private Rectangle[] a = new Rectangle[size];
	private Rectangle left, right, up, bottom;
	private int flag = 1;
	private mData data;
	private BufferedImage bufferedImage;
	private int imgHeight,imgWidth;
	public static String information="Pick A Character";
	

	// get the data from the view
	public MainScreen(mData data) {
		this.data=data;
		this.nhp = data.hp;
		this.ntime = data.time;
		width = getWidth();
		height = getHeight();
		

		
		setLayout(null);
		End = new JButton("QUIT");
		add(End);
		try {
//			bufferedImage = ImageIO.read(new File("res/grass_texture.png"));
			bufferedImage = ImageIO.read(new File("res/main_background.png"));
			imgHeight = bufferedImage.getHeight();
			imgWidth = bufferedImage.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// loading images
		this.quests_good = data.quests_good;
		this.quests_bad = data.quests_bad;
		quest_bufferedImage = new BufferedImage[10];
		try {
			// team 0 -> item 0(bad) -> solve quest0 && item 3(good) -> solve quest5
			// team 1 -> item 1(bad) -> solve quest1 quest2 && item 3(good) -> solve
			// quest5
			// team 2 -> item 2(bad) -> solve quest3 quest4 && item 3(good) -> solve
			// quest5
			quest_bufferedImage[0] = ImageIO.read(new File("res/quest0.png"));
			quest_bufferedImage[1] = ImageIO.read(new File("res/quest1.png"));
			quest_bufferedImage[2] = ImageIO.read(new File("res/quest2.png"));
			quest_bufferedImage[3] = ImageIO.read(new File("res/quest3.png"));
			quest_bufferedImage[4] = ImageIO.read(new File("res/quest4.png"));
			quest_bufferedImage[5] = ImageIO.read(new File("res/quest5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		quest_img = quest_bufferedImage;
	}

	// this method get paint every frame
	public void paint(Graphics g) {
		if (isFirst) {
			width = getWidth();
			height = getHeight();
			newMap();
			isFirst = false;
		}
		g.clearRect(0, 0, getWidth(), getHeight());
		// draw the basic bottom map first
		// then the mui, which is the items and the teams
		// then hp
		// then the quest
//		map.draw(g);
		
		for (int y = 0; y < getHeight(); y += imgHeight) {
			for (int x = 0; x < getWidth(); x += imgWidth) {
				g.drawImage(bufferedImage, x, y, this);
			}
		}
//		g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
		mui.draw(g);
		g.setColor(Color.pink);
		if (flag == -1) {
			for (int i = 0; i < nhp; i++) {
				g.fillRect(a[i].x, a[i].y, a[i].width, a[i].height);
			}
			g.setColor(Color.BLACK);
			g.fillRect(left.x, left.y, left.width, left.height);
			g.fillRect(right.x, right.y, right.width, right.height);
			g.fillRect(up.x, up.y, up.width, up.height);
			g.fillRect(bottom.x, bottom.y, bottom.width, bottom.height);
			g.setColor(Color.pink);
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("what", Font.BOLD, 30));
		g.drawString(Integer.toString((ntime) / 1000), MainScreen.width - 70, 50);
		
//		g.setColor(Color.BLACK);
		g.setFont(new Font("info", Font.BOLD, 40));
		g.drawString(information, 50, MainScreen.height-50);
		
		// drawing quest
		if (quests_good != null && quests_good.size() != 0) {
			
			for (Quest q : quests_good) {
				if (q.getSpawn() == -1) {
					map.block[q.getQuest_x()][q.getQuest_y()].setId(-1);
					map.block[q.getQuest_x()][q.getQuest_y()].setQuestID(q.getId());
					q.setSpawn(1);
				}
				// team 0 -> item 0(bad) -> solve quest0 && item 3(good) -> solve quest5
				// team 1 -> item 1(bad) -> solve quest1 quest2 && item 3(good) -> solve
				// quest5
				// team 2 -> item 2(bad) -> solve quest3 quest4 && item 3(good) -> solve
				// quest5
				g.drawImage(MainScreen.quest_img[5], MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].x,
						MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].y, MainScreen.map.blockSize,
						MainScreen.map.blockSize, null);
			}
			
		}
		if (quests_bad != null && quests_bad.size() != 0) {
			for (Quest q : quests_bad) {
				if (q.getSpawn() == -1) {
					map.block[q.getQuest_x()][q.getQuest_y()].setId(-1);
					map.block[q.getQuest_x()][q.getQuest_y()].setQuestID(q.getId());
					q.setSpawn(1);
				}
				// team 0 -> item 0(bad) -> solve quest0 && item 3(good) -> solve quest5
				// team 1 -> item 1(bad) -> solve quest1 quest2 && item 3(good) -> solve
				// quest5
				// team 2 -> item 2(bad) -> solve quest3 quest4 && item 3(good) -> solve
				// quest5
//				if (q.getSolving() == 1) {
//					g.drawImage(MainScreen.quest_img[2], MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].x,
//							MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].y, MainScreen.map.blockSize,
//							MainScreen.map.blockSize, null);
//				} else if (q.getGood_bad() == 0) {
//					g.drawImage(MainScreen.quest_img[0], MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].x,
//							MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].y, MainScreen.map.blockSize,
//							MainScreen.map.blockSize, null);
//				} else if (q.getGood_bad() == 1) {
//					g.drawImage(MainScreen.quest_img[1], MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].x,
//							MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].y, MainScreen.map.blockSize,
//							MainScreen.map.blockSize, null);
//				}
				g.drawImage(MainScreen.quest_img[q.getId()], MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].x,
				MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].y, MainScreen.map.blockSize,
				MainScreen.map.blockSize, null);
			}
		}

	}

	private void newMap() {
		map = new Map();
		mui = new MainScreenUI(data);
		// hp = new Health();
		End.setBounds(0, 0, 80, 80);
		// for (int i = 0; i < main_map.length; i++) {
		try {
			main_map = ImageIO.read(new File("res/background_test.jpeg"));
			// main_map[i] = createImage(
			// new FilteredImageSource(main_map[i].getSource(), new
			// CropImageFilter(0, 26 * i, 26, 26)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }

	}

	public void update(mData game) {
		this.nhp = game.hp;
		this.ntime = game.time;
		this.quests_good = game.quests_good;
		this.quests_bad = game.quests_bad;
		

		initHp(ntime, flag);
		repaint();
	}

	private void initHp(int time, int flag) {
		if (time > 1 && flag == 1) {
			this.HealthX = width / 2 - (total_health_length / 2);
			for (int i = 0; i < size; i++) {
				a[i] = new Rectangle(HealthX + HealthWidth * i, HealthY, HealthWidth, HealthHeight);
			}

			left = new Rectangle(HealthX, HealthY, 1, HealthHeight);
			right = new Rectangle(HealthX + total_health_length, HealthY, 1, HealthHeight);
			up = new Rectangle(HealthX, HealthY, total_health_length, 1);
			bottom = new Rectangle(HealthX, HealthY + HealthHeight, total_health_length, 1);
			setFlag(-1);
		}
	}

	// public void QuestSpawn(Quest q) {
	// // random generate x and y for the block
	// // since nextInt(min,max+1)
	// // but index start with 0
	// // so nextInt(min,max+1-1)
	// MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].setId(-1);
	// setBounds(MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].x,
	// MainScreen.map.block[q.getQuest_x()][q.getQuest_y()].y,
	// q.getQuest_size(),
	// q.getQuest_size());
	// }

	// public static Team getTeams() {
	// return teams;
	// }
	//
	// public void setTeams(Team teams) {
	// this.teams = teams;
	// }

	public static MainScreenUI getMui() {
		return mui;
	}

	public void setMui(MainScreenUI mui) {
		this.mui = mui;
	}

	public static Point getMcl() {
		return mcl;
	}

	public static void setMcl(int x, int y) {

		mcl = new Point(x, y);
	}

	public JButton getButton() {
		return End;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public static Map getMap() {
		return map;
	}

	public String getInformation() {
		return information;
	}

	public static void setInformation(String s) {
		information =s;
	}

}
