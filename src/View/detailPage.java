package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class detailPage extends JPanel {
	private String screen;
	private BufferedImage bufferedImage[];
	private JButton back;
	private int index = 0;
	private JButton next;

	detailPage() {
		this.setBackground(Color.WHITE);
		screen = "Detail";
		back = new JButton("BACK");
		next = new JButton("NEXT");
//		next = new JLabel();
		bufferedImage = new BufferedImage[6];
		try {
			bufferedImage[0] = ImageIO.read(new File("res/detail0.png"));
			bufferedImage[1] = ImageIO.read(new File("res/detail1.png"));
			bufferedImage[2] = ImageIO.read(new File("res/detail2.png"));
			bufferedImage[3] = ImageIO.read(new File("res/detail3.png"));
			bufferedImage[4] = ImageIO.read(new File("res/detail4.png"));
			bufferedImage[5] = ImageIO.read(new File("res/detail5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			index = (index+1) % 6;
			repaint();
			}
			
		});
//		next.addMouseListener(new MouseAdapter(){
//			@Override
//		    public void mouseClicked(MouseEvent e) {
//				index = (index+1) % 6;
//				repaint();
//		    }
//		});
		add(next);
		add(back);
	}

	public void currPic() {

	}

	public JPanel getPanel() {
		return this;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public JButton getBack() {
		return back;
	}

	@Override
	protected void paintComponent(Graphics g) {
		next.setBounds(getWidth() - 500, getHeight() - 180, 150, 60);
		back.setBounds(getWidth() - 350, getHeight() - 180, 150, 60);
		super.paintComponent(g);
		g.drawImage(bufferedImage[index], 50, 50, 1000, 500, null);
	}
}
