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

public class Tutorial2 extends JPanel {
	private String screen1, screen2;
	private JPanel t1, t2;
	private JLabel title1, title2, a, b;
	private JButton next, previous, skip, start, back_to_main;
	private BufferedImage bufferedImage;
	private Font font = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 36);
	private Font font_text = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 20);
	private int imgHeight, imgWidth;

	public Tutorial2() {
		screen1 = "tutorial2";
		title2 = new JLabel("TuTorial2");
		title2.setBounds(150, 50, 700, 100);
		title2.setFont(font);
		title2.setForeground(Color.white);
		a = new JLabel("some instruction");
		a.setBounds(150, 150, 700, 100);
		a.setFont(font_text);
		a.setForeground(Color.WHITE);
		b = new JLabel("some other instruction");
		b.setBounds(150, 250, 700, 100);
		b.setFont(font_text);
		b.setForeground(Color.WHITE);

		try {
			bufferedImage = ImageIO.read(new File("res/tutorial_screenshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imgHeight = bufferedImage.getHeight();
		imgWidth = bufferedImage.getWidth();
		back_to_main = new JButton("BACK");
		back_to_main.setFont(new Font("BACK",Font.PLAIN, 30));;
		start = new JButton("START");
		start.setFont(new Font("START",Font.PLAIN, 30));
		previous = new JButton("PRE");
		previous.setFont(new Font("PRE",Font.PLAIN, 30));
		setLayout(null);
		// add(title2);
		// add(a);
		// add(b);
		add(previous);
		add(start);
		add(back_to_main);

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
		previous.setBounds(getWidth() - 500, getHeight() - 180, 150, 60);
		start.setBounds(getWidth() - 350, getHeight() - 180, 150, 60);
		back_to_main.setBounds(getWidth() - 200, getHeight() - 180, 150, 60);
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
//		g.drawImage(bufferedImage, 0, 0, 500, 500, null);
		
	}
}
