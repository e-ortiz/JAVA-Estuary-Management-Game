package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import Model.Health;
import Model.Item;
import Model.Quest;
import Model.Team;
import Model.mModel;
import View.Block;

public class modelTest {
	mModel mm = new mModel();
	Quest q = new Quest(0, 2,1);
	Quest q2 = new Quest(1, 2,1);
	Health h = new Health();
	Team te = new Team(0,0,0);
	Team te2 = new Team(0,1,1);
	Item it = new Item(1,0,1);

	@Test
	public void test() {
		assertEquals("initial test", 0, mm.gettime_msc());
	}

	@Test
	public void test1() {
		Team more_team[] = {te,te2};
		mm.updateMM(1, 1);
		assertEquals("update test", 1, mm.gettime_msc());
		mm.updateMM(1, 2);
		assertEquals("update test", 1, mm.gettime_msc());
		mm.updateMM(0, 3);
		mm.getMap();
		q2.setQuest_x(0);
		q2.setQuest_y(0);
		q2.setSolving(1);
		ArrayList tmp_arr = new ArrayList();
		ArrayList tmp_arr2 = new ArrayList();
		tmp_arr.add(q2);
		q.setQuest_x(0);
		q.setQuest_y(0);
		q.setSolving(1);
		tmp_arr2.add(q);
		mm.updateMM(100000, 1);
		mm.getQuestFinish();
		mm.getQuestNotFinish();
		mm.setQuestFinish(1);
		mm.setQuestNotFinish(9);
		mm.setQuests_good(tmp_arr);
		mm.setQuests_need_to_be_solved(tmp_arr2);
		Block b = new Block(0, 0, 0, 0, 0, 0);
		mm.questCheck(1, b);
		mm.setTeam(more_team);
		mm.getTeam();
		mm.getItem();
		mm.toString();
		int holds[] = {0,-1};
		Point p = new Point();
		mm.move(holds, p);
	}

	@Test
	public void test2() {
		mm.getQuests_good();
		mm.getQuests_need_to_be_solved();
		mm.getWidth();
		mm.setHp_score(10);
		mm.getHp_score();
		mm.getHp();
		mm.changeHp(-1);
		mm.reset();
		mm.questCheck(1, new Block(1, 0, 0, 0, 0, 0));
		
		// assertEquals("update test",1,mm.gettime_msc());
	}

	@Test
	public void test3() {
		h.init();
		h.getHp_score();
		h.setHp_score(1);
		h.changesize(1);
		h.getSize();
	}

	@Test
	public void test4() {
		q.setSolving(1);
		q.setGood_bad(1);
		q.setFtime(1);
		q.isTimeUp(8);
		q.setSolving(1);
		q.setGood_bad(0);
		q.setSolving(1);
		q.isTimeUp(8);
		q.setSolving(-1);
		q.setGood_bad(1);
		q.isTimeUp(8);
		q.getId();
		q.setId(10);
		q.setFtime(1);
		q.getStime();
		q.getFtime();
		q.getQuest_x();
		q.getQuest_y();
		q.getSolving();
		q.setFtime(1);
		q.setQuest_x(1);
		q.setQuest_y(1);
		q.setSolving(1);
		q.getQuest_size();
		q.getSpawn();
		q.setSpawn(1);
		q.getGood_bad();
		q.setGood_bad(1);
		q.setQuest_size(1);
		q.setStime(1);
	}
	
	@Test
	public void test5() {
		it.setX(-1);
		assertEquals("x test",it.getX(),-1);
		it.setY(-2);
		assertEquals("y test",it.getY(),-2);
		assertEquals("good_bad test",it.getGood_bad(),1);
		it.setGood_bad(1);
		it.getItemID();
		it.setItemID(1);
	}
	
	@Test
	public void test6() {
		te.setX(-1);
		assertEquals("x test",te.getX(),-1);
		te.setY(-2);
		assertEquals("y test",te.getY(),-2);
		te.setName(-3);
		assertEquals("name test",te.getName(),-3);
	}
}
