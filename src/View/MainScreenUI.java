package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.mData;
import Model.Item;
import Model.Team;

public class MainScreenUI extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int teamW = 3;
	public static int teamSize = 80;
	public static int teamspace = 5;
	// public static Team[] team;
	public Rectangle[] teams = new Rectangle[teamW];
	int teamHold = -1;
	private int itemHold = -1;
	private int moved = -1;
	public static int itemW = 2;
	public static int itemSize = 64;
	public static int itemspace = 5;
	public Item item_for_chosen[];
	public Rectangle[] items = new Rectangle[itemW];
	// public static Item[] item;
	private int bx;
	private int by;
	private int bid;
	private ArrayList<Integer> bArr;

	private BufferedImage quit_image;
	private BufferedImage[] team_bufferedImage;
	private BufferedImage[] item_bufferedImage;
	private int time;
	private mData data;
	public int[] holds = { -1, -1 };

	public MainScreenUI(mData data) {
		this.data = data;
		this.time = data.time;

		// this.team=data.team;

		this.item_for_chosen = new Item[4];
		init();

		// test
		team_bufferedImage = new BufferedImage[data.team.length];
		// item_bufferedImage = new BufferedImage[data.item.length];
		item_bufferedImage = new BufferedImage[4];
		for (int i = 0; i < data.team.length; i++) {
			team_bufferedImage[i] = createImageTeam(i);
		}
		// load two items, one for solving bonus quest
		// one for solving bad quest
		data.item[0].setX(((MainScreen.width) / 2) - ((itemSize * (itemW) / 2) - (itemSize + itemspace) * 0));
		data.item[1].setX(((MainScreen.width) / 2) - ((itemSize * (itemW) / 2) - (itemSize + itemspace) * 1));
		// load image for item and quest
		try {
			item_bufferedImage[0] = ImageIO.read(new File("res/item0.png"));
			item_bufferedImage[1] = ImageIO.read(new File("res/item1.png"));
			item_bufferedImage[2] = ImageIO.read(new File("res/item2.png"));
			item_bufferedImage[3] = ImageIO.read(new File("res/item3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedImage createImageTeam(int num) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("res/team" + String.valueOf(num + 1) + ".png"));
			// bufferedImage = ImageIO.read(new File("res/team1.png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// team 0 -> item 0(bad) -> solve quest0 && item 3(good) -> solve quest5
	// team 1 -> item 1(bad) -> solve quest1 quest2 && item 3(good) -> solve
	// quest5
	// team 2 -> item 2(bad) -> solve quest3 quest4 && item 3(good) -> solve
	// quest5
	public int[] click(int button) {
		// Images catching
		int teamNum = holds[0];
		if (button == 1) {
			if (holds[0] == -1) {
				for (int i = 0; i < teams.length; i++) {
					if (teams[i].contains(MainScreen.mcl)) {
						holds[0] = i;
						System.out.println("team" + i);
						return holds;
					}
				}
			}

			else if (holds[1] == -1) {
				for (int i = 0; i < teams.length; i++) {
					if (teams[i].contains(MainScreen.mcl)) {
						holds[0] = i;
						System.out.println("team" + i);
						return holds;
					}
				}
				if (items[0].contains(MainScreen.mcl)) {
					if (teamNum == 0) {
						holds[1] = teamNum;
					} else if (teamNum == 1) {
						holds[1] = teamNum;
					} else if (teamNum == 2) {
						holds[1] = teamNum;
					} else {
						// System.out.println("debug area 0");
					}
					System.out.println("item" + holds[1]);
					return holds;
				} else if (items[1].contains(MainScreen.mcl)) {
					holds[1] = 3;
					System.out.println("item" + holds[1]);
					return holds;
				} else {
					// System.out.println("debug area 1");
				}
			} else if (holds[0] != -1 && holds[1] != -1) {
				for (int i = 0; i < teams.length; i++) {
					if (teams[i].contains(MainScreen.mcl)) {
						holds[0] = i;
						holds[1] = -1;
						System.out.println("team" + i);
						return holds;
					}
				}

				if (items[0].contains(MainScreen.mcl)) {
					if (teamNum == 0) {
						holds[1] = teamNum;
					} else if (teamNum == 1) {
						holds[1] = teamNum;
					} else if (teamNum == 2) {
						holds[1] = teamNum;
					} else {
						// System.out.println("debug area 2");
					}
					System.out.println("item" + holds[1]);
					return holds;
				} else if (items[1].contains(MainScreen.mcl)) {
					holds[1] = 3;
					System.out.println("item" + holds[1]);
					return holds;
				} else {
					// System.out.println("debug area 3");
				}

			} else {
				System.out.println("no");
			}
		}
		return holds;
	}

	public void init() {

		bArr = new ArrayList<Integer>();
		// initiate the team and the item
		// both team and item have their own class

		for (int i = 0; i < data.team.length; i++) {
			// teams[i] = new Rectangle(40, 60 + (teamSize + teamspace) * i,
			// teamSize, teamSize);
			// teams[i] = new Rectangle(MainScreen.width/4, 60 + (teamSize +
			// teamspace) * i, teamSize, teamSize);
			teams[i] = new Rectangle(10, 100 + (teamSize + teamspace) * i, teamSize, teamSize);
		}
		for (int i = 0; i < data.item.length; i++) {
			// items[i] = new Rectangle(((MainScreen.width) / 2) - ((itemSize *
			// (itemW) / 2) - (itemSize + itemspace) * i),
			// 495, itemSize, itemSize);
			items[i] = new Rectangle(((MainScreen.width) / 2) - ((itemSize * (itemW) / 2) - (itemSize + itemspace) * i),
					MainScreen.height - itemSize - 5, itemSize, itemSize);
		}
		try {
			quit_image = ImageIO.read(new File("res/quit_image.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// fill the item and team rectangle
	public void draw(Graphics g) {
		// for (int i = 0; i < teams.length; i++) {
		// g.fillRect(teams[i].x, teams[i].y, teams[i].width, teams[i].height);
		// }
		if (holds[0] != -1) {
			g.setColor(Color.ORANGE);
			g.fillRect(teams[holds[0]].x, teams[holds[0]].y, teams[holds[0]].width, teams[holds[0]].height);
		}
		g.setColor(Color.BLACK);
		for (int i = 0; i < teams.length; i++) {
			g.drawImage(team_bufferedImage[i], teams[i].x, teams[i].y, teams[i].width, teams[i].height, null);
		}
		for (int i = 0; i < items.length; i++) {
			g.fillRect(items[i].x, items[i].y, items[i].width, items[i].height);
		}
		if (holds[1] != -1) {
			g.setColor(Color.orange);
			if (holds[1] != 3) {
				g.fillRect(items[0].x, items[0].y, items[0].width, items[0].height);
			} else if (holds[1] == 3) {
				g.fillRect(items[1].x, items[1].y, items[1].width, items[1].height);
			} else {
				// System.out.println("debug print select");
			}
		}
		g.setColor(Color.black);
		if (holds[0] != -1) {
			g.drawImage(item_bufferedImage[holds[0]], items[0].x, items[0].y, items[0].width, items[0].height, null);
			g.drawImage(item_bufferedImage[3], items[1].x, items[1].y, items[1].width, items[1].height, null);
		}
		g.drawImage(quit_image, 10, 10, 50, 50, null);
		// g.setFont(new Font("what", Font.BOLD, 30));
		// g.drawString(Integer.toString((time) / 1000), MainScreen.width - 70,
		// 50);
	}

	public void updateTeam(Team team) {
		teams[team.getName()].x = team.getX();
		teams[team.getName()].y = team.getY();
		holds[0] = -1;
		holds[1] = -1;
	}

	public void reset() {
		holds[0] = -1;
		holds[1] = -1;
	}
}
