package View;

import java.awt.*;

//this class is for using in the central map
public class Block extends Rectangle {

	public int gid;
	public int aid;
	public int id = 1;
	private int questID;
	
	//init the block with ids for identification
	//blocks are the main structure of main map
	public Block(int x, int y, int width, int height, int gid, int aid) {
		setBounds(x, y, width, height);
		this.gid = gid;
		this.aid = aid;
		id = 1;
	}

	// default graph for the block is green grass back ground
	public void draw(Graphics g) {
		g.drawImage(MainScreen.main_map, x, y, width, height, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestID() {
		return questID;
	}

	public void setQuestID(int questID) {
		this.questID = questID;
	}
}
