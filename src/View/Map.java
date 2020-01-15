package View;

import java.awt.*;

import javax.swing.JButton;

public class Map {

	// public int worldW = 18;
	public int worldW = 12;
	public int worldH = 7;
	public int blockSize = 95;
	public Block[][] block;
	private JButton End;

	// Constructor
	public Map() {
		init();

	}

	// initiate the central map in the model
	// the idea is using 2D array with Block class that would draw the image
	// that we want for every block
	private void init() {
		block = new Block[worldH][worldW];
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block[0].length; j++) {
				block[i][j] = new Block((MainScreen.width / 2) - (worldW * blockSize / 2) + j * blockSize,
						64 + i * blockSize, blockSize, blockSize, j, i);
				// block[i][j].aid=i;
				// block[i][j].gid=j;
			}
		}

	}

	// when the user click on the screen this method
	// would return the block he/she select
	// if they click on block
	public Block getBlock(int button) {
		if (button == 1) {
			for (int i = 0; i < block.length; i++) {
				for (int j = 0; j < block[0].length; j++) {
					if (block[i][j].contains(MainScreen.mcl)) {

						System.out.println("id:" + block[i][j].getId());
						System.out.println("block:" + i + " " + j);
						return block[i][j];
					}
				}
			}
		}
		return null;
	}

	// when select, return the id;
	public int getBlockID(int button, Block b) {
		if (button == 1) {
			int tmp = b.getId();
			System.out.println("ID" + tmp);
			return tmp;

		}
		return 0;
	}

	// call the block draw function to actually draw the block.
	public void draw(Graphics g) {
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block[0].length; j++) {
				block[i][j].draw(g);

			}
		}
	}

	public int getWorldW() {
		return worldW;
	}

	public int getWorldH() {
		return worldH;
	}
}
