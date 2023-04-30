package paiza.B109;

import java.util.*;

public class B109 {

    static boolean[][] allSeat;

    static int p;

    static int q;

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int reservedSeatNum = sc.nextInt();

            allSeat = new boolean[sc.nextInt()][sc.nextInt()];

            p = sc.nextInt();

            q = sc.nextInt();

            for (int i = 0; i < allSeat.length; i++) {
                for (int j = 0; j < allSeat[i].length; j++) {
                    allSeat[i][j] = false;
                }
            }

            for (int i = 0; i < reservedSeatNum; i++) {
                allSeat[sc.nextInt()][sc.nextInt()] = true;
            }
        }

        if (allSeat[p][q]) {
            List<int[]> mostPlace = calcManhattanDistance(allSeat);
            for (int[] seat : mostPlace) {
                System.out.println(seat[0] + " " + seat[1]);
            }
        } else {
            System.out.println(p + " " + q);
        }

    }

    static List<int[]> calcManhattanDistance(boolean[][] allSeat) {
        int min = (allSeat.length - 1) + (allSeat[1].length - 1);
        List<int[]> mostPlace = new ArrayList<>();
        for (int i = 0; i < allSeat.length; i++) {
            for (int j = 0; j < allSeat[i].length; j++) {

                if (allSeat[i][j]) {
                    continue;
                }

                int tmpMin = Math.abs(p - i) + Math.abs(q - j);

                if (tmpMin < min) {
                    min = tmpMin;
                    mostPlace.clear();
                    mostPlace.add(new int[]{i, j});
                } else if (tmpMin == min) {
                    mostPlace.add(new int[]{i, j});
                }
            }
        }

        return mostPlace;
    }

}
