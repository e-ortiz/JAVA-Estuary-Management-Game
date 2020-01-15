package possibleAdvancingChange;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SimpleTable {
	JFrame jf = new JFrame("Score Board");
	JTable table;
	Object[][] tableData = { new Object[] { "employee1", 29, 13 }, new Object[] { "employee2", 56, 18 },
			new Object[] { "employee3", 35, 26 }};
	Object[] columnTitle = { "name", "complete task", "uncomplete task" };

	public void init() {
		table = new JTable(tableData, columnTitle);
//		table.setSize(new Dimension(300,300));
		jf.setPreferredSize(new Dimension(900,900));
		JScrollPane js = new JScrollPane(table);
		js.setSize(new Dimension(300,300));
		js.setPreferredSize(new Dimension(300,300));
		jf.add(js);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SimpleTable().init();
	}
}