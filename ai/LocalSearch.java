import java.util.*;

public class LocalSearch {
    Integer[] weight = {25, 8, 20, 6, 12, 0};
    int[][] graph = {{0, 1, 1, 0, 0, 0},
                     {1, 0, 1, 1, 0, 0},
                     {1, 1, 0, 0, 1, 0},
                     {0, 1, 0, 0, 1, 1},
                     {0, 0, 1, 1, 0, 1},
                     {0, 0, 0, 1, 1, 0}};
    int[] FLAG_ARR = {0, 0, 0, 0, 0, 0};

    public static void main(String[] args) {
        LocalSearch csp = new LocalSearch();
        ArrayList<Integer> que = new ArrayList<>();
        int a = 0;
        for (int i = 0; i < 6; i++) {
            if (a == 5) {
                System.out.println("SUCCESSFULLY IMPLEMENTED");
                break;
            } else {
                que.addAll(csp.successors(a));
                Collections.sort(que);
            }
            a = Arrays.asList(csp.weight).indexOf(que.get(0));
            System.out.print(a);
            if (que.isEmpty())
                System.out.print("FAILURE");
            que.remove(0);
            System.out.println(que);
        }
    }

    Vector<Integer> successors(int i) {
        Vector<Integer> t = new Vector<>();
        FLAG_ARR[i] = 1;
        for (int j = 0; j < 6; j++) {
            if (graph[i][j] == 1 && FLAG_ARR[j] != 1)
                t.add(weight[j]);
            else if (FLAG_ARR[j] == 1)
                t.remove(weight[j]);
        }
        return t;
    }
}
