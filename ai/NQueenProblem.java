public class NQueenProblem {
    final int N = 4;

    public static void main(String[] args) {
        NQueenProblem Queen = new NQueenProblem();
        Queen.solveNQ();
    }

    void printSolution(int[][] board) {
        for (int[] row : board) {
            for (int i : row)
                System.out.print(" " + i + " ");
            System.out.println();
        }
    }

    boolean isSafe(int[][] board, int row, int col) {
        int i, j;
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    boolean solveNQUtil(int[][] board, int col) {

        if (col >= N)
            return true;

        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQUtil(board, col + 1))
                    return true;

                board[i][col] = 0;
            }
        }
        return false;
    }

    void solveNQ() {
        int[][] board = {{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
            return;
        }
        printSolution(board);
    }
}
