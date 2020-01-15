package Main;

import java.awt.EventQueue;

import javax.swing.Timer;

import Controller.Controller;

public class Main {
	static final int delay = 100;//msec 10 fps is good enough
	
	//basically just start the game by call the constructor of the controller
	//within given time delay
	public static void main(String args[]) {
		EventQueue.invokeLater(
				new Runnable() {
					public void run() {
						Controller controller = new Controller(delay);
						Timer t = new Timer(delay, controller.drawAction);
						System.out.println("It worked!");
						t.start();
					}
				});

	}
}