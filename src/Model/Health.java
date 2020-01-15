package Model;

import java.awt.Graphics;
import java.awt.Rectangle;

import View.MainScreen;

public class Health {
	//class that handle the health bar
	//which is at the top of the main JPanel
	private static int HealthWidth = 4;
	private static int HealthHeight = 50;
	private int total_health_length = 100 * HealthWidth;
	private int HealthX = MainScreen.width/2 - (total_health_length/2);
	private int HealthY = 10;
	private int size = 100;
	private Rectangle[] a = new Rectangle[size];
	private Rectangle left, right, up, bottom;
	private int hp_score = size;
	
	//control of the health bar at the top
	public Health() {
		//init();
	}
	//left,right,up and bottom indicates for the
	//outline of the health bar
	public void init() {
		for (int i = 0; i < size; i++) {
			a[i] = new Rectangle(HealthX + HealthWidth * i, HealthY, HealthWidth, HealthHeight);
		}
		left = new Rectangle(HealthX, HealthY, 1, HealthHeight);
		right = new Rectangle(HealthX + total_health_length, HealthY, 1, HealthHeight);
		up = new Rectangle(HealthX, HealthY, total_health_length, 1);
		bottom = new Rectangle(HealthX, HealthY + HealthHeight, total_health_length, 1);

	}

	public int getHp_score() {
		return hp_score;
	}

	public void setHp_score(int hp_score) {
		this.hp_score = hp_score;
	}
	public void changesize(int hp_score) {
	}
	public int getSize() {
		return size;
	}
	
}