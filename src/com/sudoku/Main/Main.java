package com.sudoku.Main;

import com.sudoku.Utility.Board;
import org.jetbrains.annotations.NotNull;

public class Main {

	public static void main(String[] args) {
		Board sudoku = new Board(9);
		int [][] board = {
				{5,3,0,0,7,0,0,0,0},
				{6,0,0,1,9,5,0,0,0},
				{0,9,8,0,0,0,0,6,0},
				{8,0,0,0,6,0,0,0,3},
				{4,0,0,8,0,3,0,0,1},
				{7,0,0,0,2,0,0,0,6},
				{0,6,0,0,0,0,2,8,0},
				{0,0,0,4,1,9,0,0,5},
				{0,0,0,0,8,0,0,7,9},

		};
		sudoku.setBoard(board);
		sudoku.show();
		sudoku.solve();
		sudoku.show();
	}


}
