package Model;

import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class Quest extends Rectangle {
	private int id;
	private int stime;
	private int ftime;
	private int quest_x;
	private int quest_y;
	private int quest_size = 32;
	private int good_bad = -1;
	private int solving = -1;
	private int spawn = -1;
	
	//0 means good quest
	//1 means bad quest
	public Quest(int good_bad, int start_time,int id) {
		this.setGood_bad(good_bad);
		if (good_bad == 0) {
			this.id = id;
			stime = start_time;
			ftime = start_time + 6000;
			QuestSpawn();
		} else if (good_bad == 1) {
			this.id = id;
			stime = start_time;
			ftime = start_time + 12000;
			QuestSpawn();
		}

	}

	// new quest is spawn;
	public void QuestSpawn() {
		// random generate x and y for the block
		// since nextInt(min,max+1)
		// but index start with 0
		// so nextInt(min,max+1-1)
		this.quest_x = ThreadLocalRandom.current().nextInt(0, 7);
		this.quest_y = ThreadLocalRandom.current().nextInt(0, 12);
	}

	// check if the quest have to gone auto
	// or if get solved;
	public boolean isTimeUp(int time) {
		if (time >= ftime) {
			if (solving == 1) {
				System.out.println("hp up");
				// 0 means good
				// 1 means bad
				if (getGood_bad() == 1) {
					mModel.changeHp(5);
				} else if (getGood_bad() == 0) {
					mModel.changeHp(10);
				}

			} else if (solving == -1) {
				if (getGood_bad() == 1) {
					mModel.changeHp(-10);
				}
			}

			return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStime() {
		return stime;
	}

	public int getFtime() {
		return ftime;
	}

	public int getQuest_x() {
		return quest_x;
	}

	public int getQuest_y() {
		return quest_y;
	}

	public int getSolving() {
		return solving;
	}

	public void setStime(int stime) {
		this.stime = stime;
	}

	public void setFtime(int ftime) {
		this.ftime = ftime;
	}

	public void setQuest_x(int quest_x) {
		this.quest_x = quest_x;
	}

	public void setQuest_y(int quest_y) {
		this.quest_y = quest_y;
	}

	public void setSolving(int solving) {
		this.solving = solving;
	}



	public int getQuest_size() {
		return quest_size;
	}

	public void setQuest_size(int quest_size) {
		this.quest_size = quest_size;
	}

	public int getSpawn() {
		return spawn;
	}

	public void setSpawn(int spawn) {
		this.spawn = spawn;
	}

	public int getGood_bad() {
		return good_bad;
	}

	public void setGood_bad(int good_bad) {
		this.good_bad = good_bad;
	}

}