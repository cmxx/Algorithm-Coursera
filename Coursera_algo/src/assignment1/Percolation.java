package assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	
	int gs;			// Grid length
	int ct;			// Grid Elements
	boolean[][] opened;
	boolean[][] fullCheck;
	boolean refreshFull;		
	// This is used to check whether to run the block from top
	// to bottom to check full status
	WeightedQuickUnionUF qu;
	
	
	public Percolation(int n){
		// create n-by-n grid, with all sites blocked
		gs = n;
		ct = n^2;
		opened = new boolean[gs][gs];
		fullCheck = new boolean[gs][gs];
		refreshFull = true;
		
		for (int i = 0; i<gs; i++){
			for (int j=0; j<gs; j++){
			opened[i][j] = false;
			fullCheck[i][j] = false;
			}
		}
		
//		qu = new WeightedQuickUnionUF(ct);
	}

	public void open(int row, int col){
		// open site (row, col) if it is not open already
		if(validate(row,col)){
			opened[row][col] = true;
			//neighbor(row,col);
			//getUnion(opened,row,col);
		}
		refreshFull = true;
	}

	public boolean isOpen(int row, int col){
		// is site (row, col) open?
		if (!validate(row,col)) return false;
		return opened[row][col];
	}

	public boolean isFull(int row, int col){
		// is site (row, col) full?
		if (!validate(row,col)) return false;
		if (refreshFull){
			for (int i=0; i < gs; i++){
				getFull(opened,fullCheck,0,i);
			}
			refreshFull = false;
		}
		return fullCheck[row][col];
	}

	public boolean percolates(){
		// does the system percolate?
		for (int i=0; i< gs; i++){
			if (fullCheck[gs-1][i]){
				return true;
			}
		}
		return false;
	}
	
	
	// Helper Methods
	/**
	 * Test whether the index is valid.
	 * If it is valid, return 1D location.
	 * Else, return -1.
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean validate(int row, int col) {
		if (row >= 0 && row < gs) {
			if (col >= 0 && col < gs) {
				return true;
			}
		}
		return false;
	}
/*
	*//**
	 * Convert the index's 2D location to 1D.
	 * This method should be only used in validate() method
	 * @param row
	 * @param col
	 * @return
	 *//*
	private int converter(int row, int col) {
		return row * gs + col;
	}*/
/*
	*//**
	 * (NeighborHelper) 1. Validation. 2. isOpen. 3. Return 1D location
	 * Union valid neighbors
	 * @param row
	 * @param col
	 *//*
	private void neighbor(int row, int col) {

		int t = validate(row, col);
		int n1 = neighborHelper(row - 1, col); // Top
		int n2 = neighborHelper(row + 1, col); // Bottom
		int n3 = neighborHelper(row, col - 1); // Left
		int n4 = neighborHelper(row, col + 1); // Right

		int[] n = { n1, n2, n3, n4 };
		
		// If both the neighbor and the index is valid
		// and the neighbor is open,
		for (int i = 0; i < n.length; i++) {
			if (n[i] >= 0 && t >= 0) {
				qu.union(t, n[i]);
			}
		}
	}
	
	private int neighborHelper(int row, int col){
		if (row >= 0 && row < gs) {
			if (col >= 0 && col < gs) {
				if(isOpen(row,col)==true){
					return converter(row, col);
				}
			}
		}
		return -1;
	}*/
	
	
/*	
	private void getUnion(boolean[][] o, int row,int col){
		if (row<0 || row >= gs) return;
		if (col<0 || col >= gs) return;
		if(o[row][col]) return;
		
		o[row][col] = true;
		
		getUnionHelper(row,col,row-1,col);
		getUnionHelper(row,col,row+1,col);
		getUnionHelper(row,col,row,col-1);
		getUnionHelper(row,col,row,col+1);
		}*/
	
/*	private void getUnionHelper(int r1, int c1, int r2, int c2){
		if (!validate(r1,c1) || !validate(r2,c2)) return;
		if (opened[r2][c2]){
			qu.union(converter(r1,c1), converter(r2,c2));
		}
	}*/
	
	private void getFull(boolean[][] o, boolean[][] f, int row, int col){
		
		if (row<0 || row >= gs) return;
		if (col<0 || col >= gs) return;
		if(!o[row][col]) return;			// If this site is not open
		if(f[row][col]) return;			// If this site has been marked as full
		
		f[row][col] = true;
		
		getFull(opened, fullCheck, row-1,col);	// Up
		getFull(opened, fullCheck, row+1, col);	// Down
		getFull(opened, fullCheck, row, col-1);	// Left
		getFull(opened, fullCheck, row, col+1);	// Right
		
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}