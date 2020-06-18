package com.sudoku.Main;

import com.sudoku.Utility.Board;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int N = 9 ;
		Board sudoku = new Board(N);
		int [][] board = new int[N][N] ;

		try {
			Scanner scanner = new Scanner(new File("input.csv"));
			for( int i = 0 ; scanner.hasNextLine() ; i++ ){
				Scanner parser = new Scanner(scanner.nextLine());
				parser.useDelimiter(",");
				for( int j = 0 ; parser.hasNext() ; j++ ){
					board[i][j] = parser.nextInt();
				}
				parser.close();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		sudoku.setBoard(board);
		sudoku.show();
		sudoku.solve();
		sudoku.show();
		System.out.println(sudoku.cycle);
	}


}
