package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Welcome extends JPanel {
	private String screen;
	private JLabel begin;
	private JLabel tutorial;
	private JLabel title;
	private JLabel detail;
	private Dimension buttonSize;
	private Font font = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 72);
	private Font font_start = new Font(Font.SERIF, Font.BOLD, 42);
	private BufferedImage bufferedImage[], titleImg;
	private BufferedImage girl1[], girl2[], girl3[];
	private BufferedImage sky[];
	private int imgHeight;
	private int imgWidth;
	private Action drawAction;
	private int indexGirl = 0;
	private int indexSky = 0;
	private final int frameGirl = 24;
	private final int frameSky = 36;
	private int increment = 1;

	public Welcome() {
		buttonSize = new Dimension(100, 100);
		screen = "welcome";
		bufferedImage = new BufferedImage[4];
		girl1 = new BufferedImage[frameGirl];
		girl2 = new BufferedImage[frameGirl];
		girl3 = new BufferedImage[frameGirl];
		sky = new BufferedImage[frameSky];
		try {
			bufferedImage[0] = ImageIO.read(new File("res/welcome_play.png"));
			bufferedImage[1] = ImageIO.read(new File("res/welcome_tutorial.png"));
			for (int i = 0; i < frameGirl; i++) {
				girl1[i] = createImageGirl(i, 1);
			}
			for (int i = 0; i < frameGirl; i++) {
				girl2[i] = createImageGirl(i, 2);
			}
			for (int i = 0; i < frameGirl; i++) {
				girl3[i] = createImageGirl(i, 3);
			}
			for (int i = 0; i < frameSky; i++) {
				sky[i] = createImageSky(i);
			}
			titleImg = ImageIO.read(new File("res/title.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
		title = new JLabel("Final Present");
		title.setForeground(Color.BLUE);
		title.setFont(font);
		begin = new JLabel(new ImageIcon(bufferedImage[0]));
		tutorial = new JLabel(new ImageIcon(bufferedImage[1]));
		detail = new JLabel(new ImageIcon("res/more_info.png"));
		// x y w h
		title.setBounds(150, 50, 500, 100);
		add(detail);
		add(begin);
		add(tutorial);
		drawAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}

		};
		Timer t = new Timer(30, drawAction);
		t.start();
	}

	private BufferedImage createImageGirl(int num, int girlnum) {
		BufferedImage bufferedImage;
		try {
			if (num < 10) {
				bufferedImage = ImageIO.read(
						new File("res/Girl" + String.valueOf(girlnum) + "/tile00" + String.valueOf(num) + ".png"));
			} else if (num < 30) {
				bufferedImage = ImageIO
						.read(new File("res/Girl" + String.valueOf(girlnum) + "/tile0" + String.valueOf(num) + ".png"));
			} else {
				bufferedImage = null;
			}
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BufferedImage createImageSky(int num) {
		BufferedImage bufferedImage;
		try {
			if (num < 10) {
				bufferedImage = ImageIO.read(new File("res/Welcome/tile00" + String.valueOf(num) + ".png"));
			} else if (num < 40) {
				bufferedImage = ImageIO.read(new File("res/Welcome/tile0" + String.valueOf(num) + ".png"));
			} else {
				bufferedImage = null;
			}
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getScreen() {
		return screen;
	}

	public JLabel getBegin() {
		return begin;
	}

	public JLabel getTutorial() {
		return tutorial;
	}

	public JLabel getDetail() {
		return detail;
	}
	
	public JPanel getPanel() {
		return this;
	}

	@Override
	protected void paintComponent(Graphics g) {
		begin.setBounds(getWidth() / 5, (getHeight() / 20) * 11 - 20, 240, 80);
		tutorial.setBounds((getWidth() / 5) * 2+50, (getHeight() / 20) * 11 - 20, 240, 80);
		detail.setBounds((getWidth() / 5) * 3+100, (getHeight() / 20) * 11 - 20, 240, 80);
		super.paintComponent(g);
		indexSky += increment;
		if (indexSky == (frameSky - 1) || indexSky == 0) {
			increment = -increment;
		}
		g.drawImage(sky[indexSky], 0, 0, getWidth(), getHeight(), null);
		indexGirl = (indexGirl + 1) % frameGirl;
		g.drawImage(titleImg, 0, 50, this);
		g.drawImage(girl1[indexGirl], 0, getHeight() - 225, 200, 200, null);
		g.drawImage(girl2[indexGirl], 200, getHeight() - 225, 200, 200, null);
		g.drawImage(girl3[indexGirl], 400, getHeight() - 225, 200, 200, null);
	}
}
