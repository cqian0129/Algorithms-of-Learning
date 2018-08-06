/*
 * ExpressionCompare.java
 * 
 * UI class
 * Get 2 inputs.
 * Instantiate 2 trees.
 * Get the result sets to compare.
 * Construct truth table to present the result.
 * 
 */

import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class ExpressionCompare implements ActionListener{
	JFrame frame = new JFrame("Boolean Expression");
	Container con = new Container();//
	
	JLabel expression1 = new JLabel("Expression 1");
	JLabel expression2 = new JLabel("Expression 2");
	
	
	JTextField text1 = new JTextField();//text for expression1
	JTextField text2 = new JTextField();//text for expression2

	JButton start = new JButton("Start");//start button
	
	JLabel truthTable = new JLabel("Truth Table",JLabel.CENTER);

	JLabel resultLabel = new JLabel("Result: ");

	JTable table = new JTable();
	JScrollPane tableScroll;
	private DefaultTableModel model;
	
	// initial column names for truth table
	String[] columnNames =  {"a", "b", "c", "Expression 1", "Expression 2"};
	
	ExpressionCompare() {
		
		frame.setLocation(new Point(260, 170));
		frame.setSize(560, 385);
		frame.setContentPane(con);
		
		expression1.setBounds(55, 20, 120, 35);
		text1.setBounds(150, 20, 220, 35);

		expression2.setBounds(55, 70, 120, 35);
		text2.setBounds(150, 70, 220, 35);
		
		start.setBounds(400, 70, 80, 35);
	
		truthTable.setBounds(25,120,490,20);
		truthTable.setFont( new Font("",Font.BOLD, 16));
		
		Object[][] obj=new Object[1][4];
		model = new DefaultTableModel(obj, columnNames);
		table = new JTable(model);
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(Object.class, render);
		
		tableScroll = new JScrollPane(table);
		tableScroll.setBounds(25,145,490,153);

        resultLabel.setBounds(25, 305, 490, 40);
        resultLabel.setFont( new Font("",Font.BOLD, 15));
        
		start.addActionListener(this);
		
	    con.add(expression1);
		con.add(text1);
		con.add(expression2);
		con.add(text2);
		con.add(start);	
		con.add(truthTable);
		con.add(tableScroll);
		con.add(resultLabel);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(start)) {
			
			startCompare();
			
		}
		
	}
	
	private void startCompare(){
			
		String function1 = text1.getText();
		String function2 = text2.getText();
		
		Tree tree1 = new Tree();
		Tree tree2 = new Tree();
		
		tree1.insert(function1);
		tree2.insert(function2);
		
		String treeVari1 = tree1.str;
		String treeVari2 = tree2.str;
		
		// put variables in both tree into the other
		tree1.handleVari(treeVari2);
		tree2.handleVari(treeVari1);
		
		char[] variables = tree1.source;
		
		int l = variables.length;   // how many variables

		
		boolean[] resultBooleanSet1 = tree1.getResult();
		boolean[] resultBooleanSet2 = tree2.getResult();
		
		int setLength = resultBooleanSet1.length;
				
		String[] result1 = new String[setLength];
		String[] result2 = new String[setLength];
		
		//change boolean array to string array
		for(int i=0;i<setLength;i++){
			
			if(resultBooleanSet1[i]==true)
				result1[i]="1";
			else
				result1[i]="0";
			
			if(resultBooleanSet2[i]==true)
				result2[i]="1";
			else
				result2[i]="0";
		}
		
		//change truth table columns' names
		String[] columnNames = new String[l+2];
		for(int i = 0; i<l ; i++){
			columnNames[i] = variables[i]+"";
		}
		//last two columns is the expression
		columnNames[l] = function1;
		columnNames[l+1] = function2;
		
		model.setColumnCount(l+2);
		model.setColumnIdentifiers(columnNames);
		
		// last 2 columns is a little wider than values' column
		table.getColumnModel().getColumn(l).setPreferredWidth(100);
		table.getColumnModel().getColumn(l+1).setPreferredWidth(100);
		
		//delete all current lines in truth table
		int numT = model.getRowCount();
		while (numT > 0) {
            model.removeRow(0);
            numT--;
        }
		
		// construct truth table
		int lineNumber = result1.length; // how many data arrays
		String[] data = new String[l+2]; // Data of each line
		for(int i=0;i<lineNumber;i++) // for each line
		{
			String binary = Integer.toBinaryString(i);				
			char[] binaryTemp = binary.toCharArray();
			
			//change 10 to 0010
			int m = binaryTemp.length-1;
			for(int k=l-1;k>=0;k--){
				if(k>=l-binaryTemp.length)
				{
					data[k] = binaryTemp[m]+"";
					m--;
				}
				else
					data[k] = "0";
			}

			data[l] = result1[i];
			data[l+1] = result2[i];
			
			model.addRow(data);
		} 

		if(Arrays.equals(result1,result2))
			resultLabel.setText("Result: two expressions present the same boolean function." );
		else
			resultLabel.setText("Result: two expressions present different boolean functions." );
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ExpressionCompare();

	}
	
}
