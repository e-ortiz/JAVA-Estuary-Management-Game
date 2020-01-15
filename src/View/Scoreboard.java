package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.mData;

public class Scoreboard extends JPanel {
	int nhp,ntime;
	private String screen;
	private JLabel title, total_complete, total_incomplete, hp, time, grade;
	private BufferedImage bufferedImage;
	private JButton back_main;
	private Font font = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 72);
	private Font font_text = new Font(Font.SERIF, Font.BOLD, 30);
	private int imgHeight;
	private int imgWidth;
	private Object data;
	
	//the class for scoreboard
	public Scoreboard(mData data) {
		this.data=data;
		screen = "scoreboard";
		try {
			bufferedImage = ImageIO.read(new File("res/scoreboard_pattern.jpg"));
			imgHeight = bufferedImage.getHeight();
			imgWidth = bufferedImage.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
		title = new JLabel("SCORE");
		title.setFont(font);
		title.setForeground(Color.WHITE);
		total_complete = new JLabel();
		total_complete.setText("Complete quest");
		total_complete.setBounds(300, 150, 500, 100);
		total_complete.setFont(font_text);
		total_complete.setForeground(Color.white);
		total_incomplete = new JLabel();
		total_incomplete.setText("incomplete quest");
		total_incomplete.setBounds(300, 250, 500, 100);
		total_incomplete.setFont(font_text);
		total_incomplete.setForeground(Color.WHITE);
		this.hp = new JLabel();
		this.hp.setText("Remaining hp: " + data.hp + "%");
		this.hp.setBounds(300, 350, 500, 100);
		this.hp.setFont(font_text);
		this.hp.setForeground(Color.BLUE);
		this.time = new JLabel();
		this.time.setText("remaining time: " + data.time + " sec");
		this.time.setBounds(300, 450, 500, 100);
		this.time.setFont(font_text);
		this.time.setForeground(Color.WHITE);
		this.hp.setForeground(Color.WHITE);
		this.grade = new JLabel();
		this.grade.setText("Your Grade is: " + Grade(data) + "");
		this.grade.setBounds(300, 550, 500, 100);
		this.grade.setFont(font_text);
		this.grade.setForeground(Color.white);
		back_main = new JButton("BACK TO WELCOME");
		add(title);
		add(back_main);
		add(total_complete);
		add(total_incomplete);
		add(this.hp);
		add(this.time);
		add(grade);
	}
	// save for further interaction with model
	// public void addScore(int complete,int incomplete,int hp,double time){
	// total_complete = new JLabel();
	// total_complete.setText("complete quest"+complete);
	// total_complete.setBounds(0, 0, 200, 50);
	// total_incomplete = new JLabel();
	// total_incomplete.setText("<html><h1>header1 text</h1></html>");
	// total_incomplete.setBounds(0, 20, 200, 50);
	// this.hp = new JLabel();
	// this.hp.setText("<html><h2>header2 text</h2></html>");
	// this.hp.setBounds(0, 40, 200, 50);
	// this.time = new JLabel();
	// this.time.setText("<html><h3>header3 text</h3></html>");
	// this.time.setBounds(0, 60, 200, 50);
	// sc.add(total_complete);
	// sc.add(total_incomplete);
	// sc.add(this.hp);
	// sc.add(this.time);
	// }

	public String getScreen() {
		return screen;
	}

	public JPanel getPanel() {
		return this;
	}

	public JButton getBack() {
		return back_main;
	}

	private String Grade(mData data) {
		if ( data.hp< 20) {
			return "D";
		} else if (data.hp < 50) {
			return "C";
		} else if (data.hp < 80) {
			return "B";
		} else {
			
			return "A";
		}

	}
	
	//update the score board if needed
	public void updateScore(mData data) {
		this.data=data;
		this.hp.setText("Remaining HP: " + data.hp + "%");
		total_complete.setText("Complete Quest: "+data.QuestFinish);
		total_incomplete.setText("Incomplete Quest: "+data.QuestNotFinish);
		this.grade.setText("Your Grade is: " + Grade(data) + "");
	
//		System.out.println(nhp);
//		System.out.println(ntime);
//		this.hp.setText("remaining hp: " + nhp + "%");
//		this.time.setText("remaining time: " + (60 - (ntime)/10/10/10) + "sec");
//		this.grade.setText("Your Grade is: " + Grade(nhp) + "");
	}

	@Override
	protected void paintComponent(Graphics g) {
		title.setBounds(getWidth() / 2 - 100, getHeight() / 8 - 200, 400, 400);
		back_main.setBounds(getWidth() - 200, getHeight() - 180, 150, 60);
		super.paintComponent(g);
        for (int y = 0; y < getHeight(); y += imgHeight) {
            for (int x = 0; x < getWidth(); x += imgWidth) {
                g.drawImage(bufferedImage, x, y, this);
            }
        }
	}
}