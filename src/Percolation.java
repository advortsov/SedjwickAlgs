/**
 *
 */
public class Percolation {
    private Site[][] grid;
    private int limit;


    private class Site {
        boolean isOpen;
        boolean isFull;

        Site(boolean isOpen, boolean isFull) {
            this.isOpen = isOpen;
            this.isFull = isFull;
        }

        Site() {
            this.isOpen = Math.random() < 0.7;
        }

        @Override
        public String toString() {
            String s = "X"; // ■
            if (isOpen && !isFull) s = "О";
            if (isOpen && isFull) s = "_";
            return s;
        }
    }

    private void printGrid() {
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public Percolation(int n) {               // create n-by-n grid, with all sites blocked
        grid = new Site[n][n];
        limit = n;

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                grid[i][j] = new Site();
            }
        }
    }

    public void open(int row, int col) {  // open site (row, col) if it is not open already
        grid[row][col].isOpen = true;
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        return grid[row][col].isOpen;
    }

    public boolean isFull(int row, int col) {// is site (row, col) full?
        return grid[row][col].isFull;
    }

    public int numberOfOpenSites() {      // number of open sites
        int opened = 0;
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                if (grid[i][j].isOpen) opened++;
            }
        }
        return opened;
    }

    public boolean percolates() {         // does the system percolate?
        for (int i = 0; i < limit; i++) {
            if (grid[0][i].isOpen) {
                if (count(0, i)) return true;
            }
        }
        return false;
    }

    private boolean count(int x, int y) {
        if (x >= limit) {
            printGrid();
            return true;
        }
        if (x >= 0 && y >= 0 && y < limit) {
            if (!this.isFull(x, y)) {
                if (grid[x][y].isOpen) {
                    grid[x][y].isFull = true;
                    return count(x, y - 1) || count(x + 1, y) || count(x, y + 1) || count(x - 1, y);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(70);
        p.printGrid();
        System.out.println(p.percolates());
    }
}