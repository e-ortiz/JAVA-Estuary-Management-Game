package Model;

public class Team {

	// private static final long serialVersionUID = 1L;
	// public static int teamW = 5;
	// public static int teamSize = 64;
	// public static int teamspace = 5;
	private int name;
	private int x;
	private int y;
	
	//Constructor for team
	//name is not using but save for
	//possible future use
	public Team(int name, int x, int y) {
		this.name=name;
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

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}


}