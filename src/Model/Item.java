package Model;

import java.awt.*;
public class Item {
	private int good_bad;
	private int x;
	private int y;
	private int itemID;
	
	//constructor
	//if good_bad is 0 it's item for good
	//if is 1 it's item for bad
	public Item(int good_bad, int x,int y) {
		this.good_bad = good_bad;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

	public int getGood_bad() {
		return good_bad;
	}

	public void setGood_bad(int good_bad) {
		this.good_bad = good_bad;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}}
