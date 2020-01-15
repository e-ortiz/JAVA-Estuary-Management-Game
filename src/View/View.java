package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import Controller.Controller;
import Controller.Mouse;
import Main.mData;
import Model.Quest;

public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String title = "DNEERs Game 10-6";
	private CardLayout cardlayout;
	private JPanel curr_Panel;
	private Welcome w;
	public MainScreen m;
	private Scoreboard s;
	private JFrame jf;
	private Tutorial1 t1;
	private Tutorial2 t2;
	private detailPage d;
	protected boolean gamestart;
	private JButton End;
	private int hp;
	private int time;
	private ArrayList<Quest> quests_good;
	private ArrayList<Quest> quests_bad;
	private Mouse mouse;
	private mData data;


	// constructor
	public View(mData data) {
		this.data=data;
		this.hp = data.hp;
		this.time = data.time;
		this.quests_good = data.quests_good;
		this.quests_bad = data.quests_bad;
		init();
	}

	// initiate the whole view for the data
	// using card layout as our switch
	private void init() {

		cardlayout = new CardLayout();
		mouse = new Mouse();
		jf = new JFrame();
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setTitle(title);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationByPlatform(true);
		jf.setVisible(true);
		w = new Welcome();
		t1 = new Tutorial1();
		t2 = new Tutorial2();
		d = new detailPage();
		m = new MainScreen(data);
		End = m.getButton();
		m.addMouseListener(mouse);
		m.addMouseMotionListener(mouse);
		s = new Scoreboard(data);
		curr_Panel = new JPanel(cardlayout);
		curr_Panel.add(w.getPanel(), w.getScreen());
		curr_Panel.add(d.getPanel(),d.getScreen());
		curr_Panel.add(s.getPanel(), s.getScreen());
		curr_Panel.add(t1.getPanel(), t1.getScreen());
		curr_Panel.add(t2.getPanel(), t2.getScreen());
		curr_Panel.add(m, "main");
		jf.add(curr_Panel);
		addButtonfunc();
	}
	
//	private void viewHPUpdate(int hp ,int time){
//		hp
//	}
	
	// add button function for
	// every button we assigned
	private void addButtonfunc() {
		w.getBegin().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				cardlayout.show(curr_Panel, "main");
				Controller.setGamestart(true);
			   }
		});
		w.getTutorial().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				cardlayout.show(curr_Panel, t1.getScreen());
			   }
		});
		w.getDetail().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				cardlayout.show(curr_Panel, d.getScreen());
			   }
		});
		End.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						s.updateScore(data);
						
						Controller.setGameRestart(true);
						cardlayout.show(curr_Panel, s.getScreen());
					}
				});
			}
		});
		t2.getBack().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(curr_Panel, w.getScreen());
			}
		});
		t1.getNext().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(curr_Panel, t2.getScreen());
			}
		});
		t2.getPrevious().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(curr_Panel, t1.getScreen());
			}
		});
		t1.getSkip().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						cardlayout.show(curr_Panel, "main");
						Controller.setGamestart(true);
					}
				});
			}
		});
		t2.getStart().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						cardlayout.show(curr_Panel, "main");
						Controller.setGamestart(true);
					}
				});
			}
		});
		d.getBack().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						cardlayout.show(curr_Panel, w.getScreen());
					}
				});
			}
		});
		s.getBack().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(curr_Panel, w.getScreen());
			}
		});
	}
	public void updateData(mData data) {
		this.data=data;
	}

	public JButton getEnd() {
		return End;
	}

	public int getTime() {
		return time;
	}

	public MainScreen getMainScreen() {
		return m;
	}
}