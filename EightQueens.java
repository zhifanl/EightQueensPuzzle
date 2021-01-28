//package exercise8_5;
/**
 * @author Zhifan Li
 * @version 1.1
 * @since  1.0
 *
 */
import java.util.Arrays;
//EightQueens class
public class EightQueens implements Cloneable{
	private char[][]chessboard;
	
	//clone method
	public Object clone() throws CloneNotSupportedException{
		EightQueens newChessboard=(EightQueens)super.clone();
		
		newChessboard.chessboard=getBoard();
	
		return newChessboard;
	}

	//Constructor
	public EightQueens() {
		chessboard=new char[8][8];
		for(int i=0;i<8;i++) {
			Arrays.fill(chessboard[i], 'o');
		}
	}
	
	//display the chessboard
	public void displayBoard() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(this.chessboard[i][j]=='Q') {
					System.out.print("Q ");}
				else if(this.chessboard[i][j]=='o'){
					System.out.print("o ");

				}
			}

			System.out.println("");
		}
	}
	
	//retrieve the chessboard, return a new 2D array
	public char[][]getBoard(){
		var returnChessboard=new char[8][8];
		for(int i=0;i<8;i++) {
			returnChessboard[i]=Arrays.copyOf(chessboard[i], 8);
		}
		return returnChessboard;
	}
	//set Q on chessboard
	public void setQueen(int row,int column) {
		this.chessboard[row][column]='Q';
	}
	//set o on chessboard
	public void emptySquare(int row , int column) {
		this.chessboard[row][column]='o';
	} 
	//set several Queens at one time, have recursive function inside
	public boolean setQueens(int queensRemaining) {
		int countQueens=0;
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessboard[i][j]=='Q') {
					chessboard[i][j]='o';//replace with o first in order to call possibleLocation and without attacking itself being Q.
					if(!possibleLocation(i, j))
					{	chessboard[i][j]='Q';//set it back to Q, in the future it will still make setQueens false.
						return false;}
					chessboard[i][j]='Q';//done checking, passed checking,set it back to Q.
					countQueens++;
				}
			}
		}
		if((countQueens+queensRemaining)>8||queensRemaining<0) {
			return false;
		}
		//recursive function
		boolean a=recursivePlaceQueens(queensRemaining);
		return a;
	}

	
	private boolean recursivePlaceQueens(int queensRemaining) {
		//base case
		if(queensRemaining==0) { 
			return true;}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessboard[i][j]=='o') {
					if(possibleLocation(i,j)) {
						chessboard[i][j]='Q';
						queensRemaining--;
						if(recursivePlaceQueens(queensRemaining)){return true;}//recursive call
							else{chessboard[i][j]='o';
							queensRemaining++;}
						}//if return from previous recursive and didnt use all of queensRemaining, do the cleanup.
						}
			}
		} 
		return false;}


//see if the current location is possible to place a Q
	public boolean possibleLocation(int row,int col) {
		for(int i=0;i<8;i++) {
			if(chessboard[row][i]=='Q') {return false;}
		}
		for(int j=0;j<8;j++) {
			if(chessboard[j][col]=='Q') {return false;}
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessboard[i][j]=='Q'){
					if(Math.abs(i-row)==Math.abs(j-col)) {
						return false;
					}
				}

			}
		}
		return true;
    }

/**
	 * @param args Optional argument
	 */
	public static void main(String[] args)throws CloneNotSupportedException {
		EightQueens chessboardObject=new EightQueens();
		chessboardObject.setQueen(0,0);
		//EightQueens a=(EightQueens)chessboardObject.clone();
		boolean a4=chessboardObject.setQueens(2);
		boolean a1=chessboardObject.setQueens(5);
		System.out.println(a1);
		chessboardObject.displayBoard();
	}

}
