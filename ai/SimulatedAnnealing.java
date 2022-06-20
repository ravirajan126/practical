import java.util.Arrays;

public class SimulatedAnnealing {

    static final double Tmin = .0001;
    static final double alpha = 0.9;
    static final int numIterations = 100;
    static final int M = 5, N = 5;
    public static double T = 1;

    public static void main(String[] args) {
        String[][] sourceArray = new String[M][N];
        Solution min = new Solution(Double.MAX_VALUE, null);
        Solution currentSol = genRandSol();

        while (T > Tmin) {
            for (int i = 0; i < numIterations; i++) {
                if (currentSol.CVRMSE < min.CVRMSE) {
                    min = currentSol;
                    break;
                }

                Solution newSol = neighbor(currentSol);
                double ap = Math.pow(Math.E,
                        (currentSol.CVRMSE - newSol.CVRMSE) / T);
                if (ap > Math.random())
                    currentSol = newSol;
            }

            T *= alpha;
        }
        System.out.println(min.CVRMSE + "\n\n");

        for (String[] row : sourceArray) Arrays.fill(row, "X");

        for (int object : min.config) {
            int[] coord = indexToPoints(object);
            sourceArray[coord[0]][coord[1]] = "-";
        }

        for (String[] row : sourceArray)
            System.out.println(Arrays.toString(row));
    }

    public static Solution neighbor(Solution currentSol) {
        return currentSol;
    }

    public static Solution genRandSol() {
        int[] a = {1, 2, 3, 4, 5};
        return new Solution(-1, a);
    }

    public static int[] indexToPoints(int index) {
        return new int[]{index % M, index / M};
    }

    static class Solution {
        public double CVRMSE;
        public int[] config;

        public Solution(double CVRMSE, int[] configuration) {
            this.CVRMSE = CVRMSE;
            config = configuration;
        }
    }
}
