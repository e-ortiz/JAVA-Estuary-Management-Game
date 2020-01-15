package View;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tutorial1 extends JPanel {

	private int imgHeight;
	private int imgWidth;
	private String screen1, screen2;
	private JPanel t1, t2;
	private JLabel title1, title2, a, a2, b, c, d, arrow1, arrow2, arrow3, arrow4;
	private JButton next, previous, skip, start, back_to_main;
	private BufferedImage backgroundTile, fish, herbicide, hands, clipboard, mosquitoes, multifloraRoses, phragmites,
			japaneseStiltgrass, mileAMinuteWeeds, horseshoeCrabs;
	private Font font = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 40);
	private Font font_text = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 25);

	public Tutorial1() {
		setLayout(null);
		screen1 = "tutorial1";
		title1 = new JLabel("Matching");
		title1.setBounds(150, 50, 500, 100);
		title1.setFont(font);
		title1.setForeground(Color.BLACK);

		a = new JLabel("Each Team have");
		a.setForeground(Color.WHITE);
		a.setFont(font_text);

		a2 = new JLabel("DIFFERENT ITEM!!");
		a2.setForeground(Color.WHITE);
		a2.setFont(font_text);

		arrow1 = new JLabel("----->");
		arrow1.setForeground(Color.BLACK);
		arrow1.setFont(font_text);
		arrow1.setBounds(175, 200, 1000, 50);

		b = new JLabel(
				"Use environmentally-friendly herbicide to kill off the widely-spread multiflora rose and phragmites.");
		b.setBounds(500, 400, 1000, 100);
		b.setFont(font_text);
		b.setForeground(Color.BLACK);

		arrow2 = new JLabel("----->");
		arrow2.setForeground(Color.BLACK);
		arrow2.setFont(font_text);
		arrow2.setBounds(175, 400, 1000, 50);

		c = new JLabel("Use your hands to pull out invasive plants like Japanese stiltgrass and mile-a-minute-weed.");
		c.setBounds(500, 500, 1000, 100);
		c.setFont(font_text);
		c.setForeground(Color.BLACK);

		arrow3 = new JLabel("----->");
		arrow3.setForeground(Color.BLACK);
		arrow3.setFont(font_text);
		arrow3.setBounds(175, 500, 1000, 50);

		d = new JLabel("Use a clipboard to survey the horseshoe crabs.");
		d.setBounds(500, 600, 1000, 100);
		d.setFont(font_text);
		d.setForeground(Color.BLACK);

		arrow4 = new JLabel("----->");
		arrow4.setForeground(Color.BLACK);
		arrow4.setFont(font_text);
		arrow4.setBounds(175, 600, 1000, 50);

		try {
			backgroundTile = ImageIO.read(new File("res/matches_tutorial.png"));
			imgHeight = backgroundTile.getHeight();
			imgWidth = backgroundTile.getWidth();

			fish = ImageIO.read(new File("res/item0.png"));
			herbicide = ImageIO.read(new File("res/item1.png"));
			hands = ImageIO.read(new File("res/item2.png"));
			clipboard = ImageIO.read(new File("res/item3.png"));
			mosquitoes = ImageIO.read(new File("res/quest0.png"));
			multifloraRoses = ImageIO.read(new File("res/quest1.png"));
			phragmites = ImageIO.read(new File("res/quest2.png"));
			japaneseStiltgrass = ImageIO.read(new File("res/quest3.png"));
			mileAMinuteWeeds = ImageIO.read(new File("res/quest4.png"));
			horseshoeCrabs = ImageIO.read(new File("res/quest5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		next = new JButton("NEXT");
		next.setFont(new Font("NEXT",Font.PLAIN, 30));
		skip = new JButton("SKIP");
		skip.setFont(new Font("SKIP",Font.PLAIN, 30));
		// add(title1);
		add(next);
		add(skip);
		add(a);
		add(a2);
		// add(b);
		// add(c);
		// add(d);
		// add(arrow1);
		// add(arrow2);
		// add(arrow3);
		// add(arrow4);

	}

	public JPanel getPanel() {
		return this;
	}

	public JButton getNext() {
		return next;
	}

	public JButton getSkip() {
		return skip;
	}

	public JButton getStart() {
		return start;
	}

	public JButton getPrevious() {
		return previous;
	}

	public JButton getBack() {
		return back_to_main;
	}

	public String getScreen() {
		return screen1;
	}

	@Override
	protected void paintComponent(Graphics g) {
		a.setBounds(getWidth() - 300, 0, 400, 100);
		a2.setBounds(getWidth() - 300, 100, 300, 30);
		next.setBounds(getWidth() - 500, getHeight() - 180, 150, 60);
		skip.setBounds(getWidth() - 350, getHeight() - 180, 150, 60);
		super.paintComponent(g);

		// for (int y = 0; y < getHeight(); y += imgHeight) {
		// for (int x = 0; x < getWidth(); x += imgWidth) {
		// g.drawImage(backgroundTile, x, y, this);
		// }
		// }
		g.drawImage(backgroundTile, 0, 0, getWidth(), getHeight(), null);
		// g.drawImage(fish, 50, 200, 100, 100, null);
		// g.drawImage(mosquitoes, 250, 200, 100, 100, null);
		//
		// g.drawImage(herbicide, 50, 400, 100, 100, null);
		// g.drawImage(multifloraRoses, 250, 400, 100, 100, null);
		// g.drawImage(phragmites, 350, 400, 100, 100, null);
		//
		// g.drawImage(hands, 50, 500, 100, 100, null);
		// g.drawImage(japaneseStiltgrass, 250, 500, 100, 100, null);
		// g.drawImage(mileAMinuteWeeds, 350, 500, 100, 100, null);
		//
		// g.drawImage(clipboard, 50, 600, 100, 100, null);
		// g.drawImage(horseshoeCrabs, 250, 600, 100, 100, null);
	}
}
