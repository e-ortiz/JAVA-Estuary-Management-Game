package Controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Model.Team;
import Model.mModel;
import View.Block;
import View.MainScreen;

public class Mouse implements MouseMotionListener, MouseListener {
	int[] holds = { -1, -1 };
	private Team team;

	public Mouse() {
		// System.out.println("MOUse");
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	// for now we only have to click the button to get
	// some game play
	public void mousePressed(MouseEvent e) {
		Point mcl = MainScreen.getMcl();
		Block b = MainScreen.getMap().getBlock(e.getButton());
		this.holds = MainScreen.getMui().click(e.getButton());
		if(holds[0] != -1 && holds[1] != -1) {
			MainScreen.setInformation("Pick A Quest On Screen");
		}
		else if(holds[0]!=-1) {
			MainScreen.setInformation("Pick An Item");
		}
		
		// hold[0]is team id, hold[1] is item id
		if (b != null) {
			
			// check move status
			// if the quest id match
			// the or condition is if it's bonus
			// which means questId = 5 holds[1](which is the item id)
			// is 3
			if ((holds[0] != -1 && holds[1] != -1 && b.getId() == -1 && holds[1] == b.getQuestID())
					|| (holds[0] != -1 && holds[1] != -1 && b.getId() == -1 && holds[1] == b.getQuestID() - 2)) {
				team = mModel.move(holds, mcl);
				mModel.questCheck(e.getButton(), b);
				MainScreen.getMui().updateTeam(team);
				// reset move status
				holds[0] = -1;
				holds[1] = -1;
				MainScreen.setInformation("Pick A Team");

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MainScreen.setMcl(e.getX(), e.getY());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MainScreen.setMcl(e.getX(), e.getY());

	}

}
