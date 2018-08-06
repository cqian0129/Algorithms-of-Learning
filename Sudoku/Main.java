package Backtracking.Sudoku;

import java.io.IOException;

//Test program for Sudoku problem
public class Main {

	public static void main(String[] args) throws IOException 
	{
		Sudoku sdk = new Sudoku("src/Backtracking/Sudoku/file3");
		
		sdk.solve();

	}

}
