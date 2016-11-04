public class Percolation {

	int gs; // Grid length
	boolean[][] opened; // a n-by-n matrix stores which site is open.
	boolean[][] fullCheck; // a n-by-n matrix stores which site is full.
	boolean refreshFull; // Flag: need to check full status of each site?

	/**
	 * Initialize
	 * 
	 * @param n
	 */
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		gs = n;
		opened = new boolean[gs][gs];
		fullCheck = new boolean[gs][gs];
		refreshFull = true;
		// Give initial values for opened and fullCheck.
		for (int i = 0; i < gs; i++) {
			for (int j = 0; j < gs; j++) {
				opened[i][j] = false;
				fullCheck[i][j] = false;
			}
		}
	}

	public void open(int row, int col) {
		// open site (row, col) if it is not open already
		row = row - 1;
		col = col - 1;
		if (validate(row, col)) {
			// Set this site as open
			opened[row][col] = true;
			// Set this site as full if it's on top or it's neighbor is full
			checkSurroundingForFull(row, col);
			setNeighborsToFull(row, col);
		}
		refreshFull = true;
	}

	private void checkSurroundingForFull(int r, int c) {
		if (r == 0 || checkSurroundingForFullHelper(r + 1, c) || checkSurroundingForFullHelper(r - 1, c)
				|| checkSurroundingForFullHelper(r, c + 1) || checkSurroundingForFullHelper(r, c - 1))
			fullCheck[r][c] = true;
	}

	private boolean checkSurroundingForFullHelper(int r, int c) {
		if (!validate(r, c))
			return false;
		return fullCheck[r][c];
	}

	private void setNeighborsToFull(int row, int col) {
		if (fullCheck[row][col]) {
			checkNeighbors(row + 1, col);
			checkNeighbors(row - 1, col);
			checkNeighbors(row, col - 1);
			checkNeighbors(row, col + 1);
		}
	}

	// If the location of site is valid and
	// If the the site is open
	// If the site is not full
	private void checkNeighbors(int r, int c) {
		if (validate(r, c) && opened[r][c] && !fullCheck[r][c]) {
			fullCheck[r][c] = true;
			setNeighborsToFull(r, c);
		}
	}

	public boolean isOpen(int row, int col) {
		row = row - 1;
		col = col - 1;
		if (validate(row, col)) {
			return opened[row][col];
		}
		return false;
	}

	public boolean isFull(int row, int col) {
		row = row - 1;
		col = col - 1;
		if (!validate(row, col)) {
			return false;
		}

		return fullCheck[row][col];
	}

	public boolean percolates() {
		for (int i = 1; i <= gs; i++) {
			if (isFull(gs, i)) {
				return true;
			}

		}
		return false;
	}

	private boolean validate(int row, int col) {
		if (row >= 0 && row < gs) {
			if (col >= 0 && col < gs) {
				return true;
			}
		}

		return false;
	}

}