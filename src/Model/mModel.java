package Model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import View.Block;
import View.Map;

public class mModel implements Serializable {

	// public static Map map;
	private static int time_msc;
	public int width, height;
	public boolean isFirst = true;
	private static Health hp;
	private static ArrayList<Quest> quests_need_to_be_solved;
	private static ArrayList<Quest> quests_good;
	private Iterator<Quest> questsGood;
	private Iterator<Quest> questsBad;
	private int quests_bad_limit = 3;
	private int quests_good_limit = 1;
	private static Map map;

	public static int teamW = 3;
	public static int teamSize = 64;
	public static int teamspace = 5;
	public static Team[] team = new Team[teamW];
	int teamHold = -1;
	private int itemHold = -1;
	private int moved = -1;
	public static int itemW = 2;
	public static int itemSize = 64;
	public static int itemspace = 5;
	private static Item[] item = new Item[itemW];
	private static int hp_score = 100;

	private HashSet<Integer[]> existLoc;
	// private ArrayList<Integer[]> existLoc;
	private static int QuestFinish=0;
	private	static int QuestNotFinish=0;

	// constructor
	public mModel() {
		// assign every logic variable that we need to use after
		quests_need_to_be_solved = new ArrayList<Quest>();
		quests_good = new ArrayList<Quest>();
		existLoc = new HashSet<Integer[]>();
		hp = new Health();
		time_msc = 0;
		map = new Map();

		for (int i = 0; i < team.length; i++) {
			team[i] = new Team(i, 40, 100 + (teamSize + teamspace) * i);

		}
		item[0] = new Item(0, 0, 495);
		item[1] = new Item(1, 0, 495);
	}

	// method called every drawAction to update the whole frame
	/**
	 * 
	 * @param t
	 * @param isQuest
	 */
	public void updateMM(int t, int isQuest) {
		int index = 0;
//		for (Integer[] tmp : existLoc) {
//			System.out.println(tmp[0] + " " + tmp[1] + "index: " + index);
//			index++;
//		}
//		System.out.println("****************************");
		time_msc = t;
		// when the game start at 1 sec or during 6 sec
		// generate some quest to be solved
		// since this function is execuate every 100 msec
		// the isQuest is used to identify if current msec is
		// the time to do generate a quest
		if (isQuest == 1) {
			questGenerater();
		} else if (isQuest == 2) {
			questGenerater();
			questGenerater();
		} else {
			// System.out.println("sdadaa");
		}
		Quest tmp;
		Integer[] tmpLoc = { -1, -1 };
		Integer[] itTmpLoc = { -1, -1 };
		Iterator<Integer[]> t1 = existLoc.iterator();
		questsGood = quests_good.iterator();
		questsBad = quests_need_to_be_solved.iterator();

		while (questsGood.hasNext()) {
			tmp = questsGood.next();
			tmpLoc[0] = tmp.getQuest_x();
			tmpLoc[1] = tmp.getQuest_y();
			if (tmp.isTimeUp(time_msc)) {
				if(tmp.getSolving()==1) {
					QuestFinish++;
				}
				else {
					QuestNotFinish++;
				}
				questsGood.remove();
//				while (t1.hasNext()) {
//					itTmpLoc = t1.next();
//					if (tmpLoc[0] == itTmpLoc[0] && tmpLoc[1] == itTmpLoc[1]) {
//						t1.remove();
//					}
//				}
			}
		}
		t1 = existLoc.iterator();
		while (questsBad.hasNext()) {
			tmp = questsBad.next();
			tmpLoc[0] = tmp.getQuest_x();
			tmpLoc[1] = tmp.getQuest_y();
			if (tmp.isTimeUp(time_msc)) {
				if(tmp.getSolving()==1) {
					QuestFinish++;
				}
				else {
					QuestNotFinish++;
				}
				questsBad.remove();
//				while (t1.hasNext()) {
//					itTmpLoc = t1.next();
//					if (tmpLoc[0] == itTmpLoc[0] && tmpLoc[1] == itTmpLoc[1]) {
//						t1.remove();
//					}
//				}
			}
		}

	}

	// if this function get called,
	// initial quest for bonus hp first
	// then the quest that decrement hp
	// if there is space for a quest;

	// team 0 -> item 0(bad) -> solve quest0 && item 3(good) -> solve quest5
	// team 1 -> item 1(bad) -> solve quest1 quest2 && item 3(good) -> solve
	// quest5
	// team 2 -> item 2(bad) -> solve quest3 quest4 && item 3(good) -> solve
	// quest5
	private void questGenerater() {
		boolean flag = true;
		Quest tmp;
		Integer[] addLoc = { -1, -1 };
		if (quests_good.size() < quests_good_limit) {
//			do {
//				tmp = new Quest(0, time_msc, 5);
//				addLoc[0] = tmp.getQuest_x();
//				addLoc[1] = tmp.getQuest_y();
//				flag = existLoc.add(addLoc);
//			} while (flag);
			quests_good.add(new Quest(0, time_msc, 5));
//			quests_good.add(tmp);
		}
		flag = true;
		// would product 0 - 4
		int questId = ThreadLocalRandom.current().nextInt(0, 5);
		if (quests_need_to_be_solved.size() < quests_bad_limit) {
//			do {
//				tmp = new Quest(1, time_msc, questId);
//				addLoc[0] = tmp.getQuest_x();
//				addLoc[1] = tmp.getQuest_y();
//				flag = existLoc.add(addLoc);
//			} while (flag);
			quests_need_to_be_solved.add(new Quest(1, time_msc, questId));
//			quests_need_to_be_solved.add(tmp);
		}
	}

	// go through
	// the quest
	// and see if
	// any quest
	// need to
	// be solved
	/**
	 * 
	 * @param button
	 * @param b
	 */
	public static void questCheck(int button, Block b) {
		if (button == 1) {

			for (Quest q : quests_need_to_be_solved) {
				if (q.getQuest_x() == b.aid && q.getQuest_y() == b.gid) {
					System.out.println("get!");
					q.setSolving(1);
				}
			}
			for (Quest q : quests_good) {
				System.out.println(b.getX() + " " + b.getY());
				if (q.getQuest_x() == b.aid && q.getQuest_y() == b.gid) {
					System.out.println("get!");
					q.setSolving(1);

				}
			}
		}
	}

	/**
	 * 
	 * @param holds
	 * @param p
	 * @return
	 */
	public static Team move(int[] holds, Point p) {
		// team move function
		int i = holds[0];
		// int tmpx = teams[teamHold].x;
		// int tmpy = teams[teamHold].y;
		// int tmpid = teamHold;
		team[i].setX((int) p.getX() + 25);
		team[i].setY((int) p.getY() + 25);
		return team[i];

	}

	// reset the model to ready for restart
	public void reset() {
		quests_good.clear();
		quests_need_to_be_solved.clear();
		existLoc.clear();
		hp_score = hp.getSize();
		for (int i = 0; i < team.length; i++) {
			team[i] = new Team(i, 40, 100 + (teamSize + teamspace) * i);
		}
		item[0] = new Item(0, 0, 495);
		item[1] = new Item(1, 0, 495);
		QuestFinish=0;
		QuestNotFinish=0;
		
	}

	public static int gettime_msc() {
		// TODO Auto-generated method stub
		return time_msc;
	}

	public int getWidth() {
		return width;
	}

	// public static MainScreenUI getMui() {
	// return mui;
	// }

	public static Health getHp() {
		return hp;
	}

	/**
	 * 
	 * @param n
	 */
	public static void changeHp(int n) {
		int max = hp_score + n;
		if (max < hp.getSize()) {
			hp_score += n;
		} else if (max >= hp.getSize()) {
			hp_score = hp.getSize();
		}
	}


	public static int getHp_score() {
		return hp_score;
	}

	public void setHp_score(int n) {
		hp_score = n;
	}

	public static ArrayList<Quest> getQuests_need_to_be_solved() {
		return quests_need_to_be_solved;
	}

	public static ArrayList<Quest> getQuests_good() {
		return quests_good;
	}

	public static Map getMap() {
		// TODO Auto-generated method stub
		return map;
	}

	public static Team[] getTeam() {
		// TODO Auto-generated method stub
		return team;
	}

	public void setTeam(Team[] t) {
		// TODO Auto-generated method stub
		this.team = t;
	}

	public static Item[] getItem() {
		// TODO Auto-generated method stub
		return item;
	}

	public String toString() {
		return "mModel";
	}

	public static void setQuests_need_to_be_solved(ArrayList<Quest> quests_need_to_be_solved) {
		mModel.quests_need_to_be_solved = quests_need_to_be_solved;
	}

	public static void setQuests_good(ArrayList<Quest> quests_good) {
		mModel.quests_good = quests_good;
	}

	public static int getQuestFinish() {
		return QuestFinish;
	}

	public static int getQuestNotFinish() {
		return QuestNotFinish;
	}

	public void setQuestFinish(int questFinish) {
		QuestFinish = questFinish;
	}

	public void setQuestNotFinish(int questNotFinish) {
		QuestNotFinish = questNotFinish;
	}

}
