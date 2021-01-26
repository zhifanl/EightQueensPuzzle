
package exercise8_5;




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
		
		newChessboard.chessboard=new char[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				newChessboard.chessboard[i][j]=chessboard[i][j];
			}
		}
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
					countQueens++;
				}
			}
		}
		if((countQueens+queensRemaining)>8||queensRemaining<0) {
			return false;
		}
		//recursive function
		recursivePlaceQueens(queensRemaining);
		missionComplete=0;//reset the default trigger to 0. 
		//double check if the replacement is successful,if no, return false
		int doubleCheck=0;
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessboard[i][j]=='Q') {
					doubleCheck++;
				}
			}
		}
		return (doubleCheck==countQueens+queensRemaining);//double check if the recursive actually works if setQueens called multiple times.

	}


	static int missionComplete=0;// very important, it is to control if can jump out of the loop and not cleanup the correct Qs after finished.
	
	private void recursivePlaceQueens(int queensRemaining) {

		//base case
		if(queensRemaining==0) { 
			missionComplete=1;
			return;}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(chessboard[i][j]=='o') {
					//System.out.println(possibleLocation(i,j));
					if(possibleLocation(i,j)) {
						chessboard[i][j]='Q';
						queensRemaining--;

						recursivePlaceQueens(queensRemaining);//recursive call
						if(missionComplete==0) {
							chessboard[i][j]='o';
							queensRemaining++;
						}//if return from previous recursive and didnt use all of queensRemaining, do the cleanup.
						else if(missionComplete==1) {
							break;
						}// if place all the Qs already, no cleanup and jump out of the loop.

					}
				} 
			}if(missionComplete==1) {
					break;}//jump out of the loop if all Qs placed.
		}

		return;	}


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
		EightQueens a=(EightQueens)chessboardObject.clone();
		
		boolean a1=chessboardObject.setQueens(2);
		chessboardObject.displayBoard();

		System.out.println("  ");
		boolean a2=chessboardObject.setQueens(2);
		//Why cannot do this another time? Because the algorithm did not work to leave space for another adding.
		chessboardObject.displayBoard();

		System.out.println(a2);
	
		System.out.println("abc");
		char[][]c=a.getBoard();
		

		
	}

}
