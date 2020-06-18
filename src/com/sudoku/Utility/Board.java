package com.sudoku.Utility;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class Board {
	
	private int[][] board;
	public final Integer size ;
	private Integer placed ;

	public Board(int size) {
		this.size = size;
		this.board= new int[size][size];
		placed = 0 ;
	}

	public boolean solve(){

		if(isSolved()) return true ;

		int [] nextBlankPos = getNextBlank();

		int row = nextBlankPos[0];
		int col = nextBlankPos[1];

		if(row == -1 && col == -1) return true ;

		for (int guess = 1; guess <= size; guess++) {
			if (canPlace(row, col, guess)) {
				board[row][col] = guess;
				placed++ ;
				if (solve()) return true;
				else {
					placed -- ;
					board[row][col] = 0;
				}
			}
		}
		return false;

	}

	private int[] getNextBlank(){
		int [] indexes = new int[2] ;
		indexes[0] = -1 ;
		indexes[1] = -1 ;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if( board[i][j] == 0 ){
					indexes[0] = i ;
					indexes[1] = j ;
				}
			}
		}

		return indexes ;
	}
	public  boolean isSolved() {
		return placed == size * size ;
	}
	
	public void setBoard(@NotNull Board otherBoard) {
		setBoard(otherBoard.getBoard());
	}

	public void setBoard(int[][] board) {
		for( int i = 0 ; i < size ; i++ ) {
			for( int j = 0 ; j < size ; j++ ) {
				int value = board[i][j] ;
				if(board[i][j] != 0) {
					if(canPlace(i, j, value)) {
						this.board[i][j] = value ;
					} else {
						this.board = new int[size][size];
					}
				}
			}
		}
	}
	
	public boolean canPlace(int I, int J, int guess) {

		//For row
		for (int j = 0; j < size ; j++) if (this.board[I][j] == guess) return false;

		//For column
		for( int i = 0 ; i < size ; i++ ) if (this.board[i][J] == guess) return false;


		//For subgrid
		int sqrt = (int)Math.sqrt(size) ;
		int boxi = I - I % sqrt ;
		int boxj = J - J % sqrt ;

		for( int i = 0 ; i < sqrt ; i++ ){
			for( int j = 0 ; j < sqrt ; j++ ){
				int row = i + boxi ;
				int col = j + boxj ;
				if(this.board[row][col] == guess ) return false ;
			}
		}

		return true ;
	}
	
	public int[][] getBoard() {
		return board;
	}

	public int @NotNull [] findNextBlank(int i, int j) {
		int []  indexes = new int[2];
		indexes[0] = size ;
		indexes[1] = size ;
		for(  ; i < size ; i++ ) {
			for( ; j < size ; j++ ) {
				if( this.board[i][j] == 0 ) {
					indexes[0] = i ;
					indexes[1] = j ;
					
					return indexes ;
				}
			}
		}
		return indexes ;
	}
	
	//to display board
	public void show() {
		printDivider();
		for( int i = 0 ; i < size ; i++ ) {
			System.out.print("|");
			for (int j = 0; j < size; j++) {
				String printFlag = this.board[i][j] != 0  ?  " " + this.board[i][j] : " .";
				System.out.print(printFlag);
				if( j % Math.sqrt(size) == Math.sqrt(size) - 1 ) {
					System.out.print(" |");
				}
			}
			System.out.println();
			if( i % Math.sqrt(size) == Math.sqrt(size) - 1 ) {
				printDivider();
			}
			
		}
	}

	private void printDivider() {
		StringBuilder builder = new StringBuilder("+");
		for( int i = 0 ; i < Math.sqrt(size) ; i++ ) {
			for( int j = 0 ; j < 2*Math.sqrt(size) + 1 ; j++ ) {
				builder.append("-");
			}
			builder.append("+");
		}
		System.out.println(builder);
	}


}
