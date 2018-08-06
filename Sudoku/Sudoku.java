package Backtracking.Sudoku;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//Program solves sudoku puzzles
public class Sudoku {
	
	//sudoku board
	private int[][] board;
	
	/************************************************************************/
	
	//Constructor of sudoku class
	public Sudoku (String inputFile) throws IOException
	{
		//open input file
		Scanner inFile = new Scanner(new File(inputFile));
		
		//initialize result and board
		board = new int[9][9];
		
		//read board
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				board[i][j] = inFile.nextInt();
	
		inFile.close();
	}
	
	/************************************************************************/
	
	//Method solves a given puzzle
	public void solve()
	{											//try to fill board starting
		if(fill(0))								//at the beginning
			display();							//if success display board
		else
			System.out.println("No solution"); 	//otherwise failure
	}
	
	/************************************************************************/
	
	//Recursive/back tracking method to fill a board. It fills the board
	//starting at a given location
	private boolean fill(int location)
	{
		//System.out.println(location);
		int x = location/9;				//find x,y coordinates of 
		int y = location%9;				//current location
		int value;
		
		if(location > 80)				//if location exceeds board
			return true;				//whole board is filled
		
		else if (board[x][y] != 0)		//if location already has value
			return fill(location + 1);	//fill rest of board
		
		else
		{
			for(value = 1; value <= 9; value++)
			{
				board[x][y] = value;	//try numbers 1-9 at location
				
				if(check(x,y) && fill(location + 1))
				{
					return true;		//if no conflicts and rest of
										//board can be filled then done
				}
			}
			
			board[x][y] = 0;			//if none of numbers 1-9 work then
			return false;				//empty location, fail, and back track
		}
	}
	
	/************************************************************************/
	
	//Method checks whether value at a given location causes any conflicts
	private boolean check(int x, int y)
	{
		int a, b, i, j;
		
		for(j = 0; j < 9; j++)			//check value causes conflict in row
			if(j != y && board[x][j] == board[x][y])
				return false;
		
		for (i = 0; i < 9; i++)			//check value causes conflict in column
			if(i != x && board[i][y] == board[x][y])
				return false;
		
		a = (x/3) * 3;					//check value causes conflict
		b = (y/3) * 3;					//in 3x3 region
		
		for(i = 0; i < 3; i++)
			for(j=0; j < 3; j++)
				if((a + i != x) && (b + j != y) && board[a+i][b+j] == board[x][y])
					return false;
		
		return true;
	}
	
	/************************************************************************/
	
	//Method displays a board
	private void display()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}

}






