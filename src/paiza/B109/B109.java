package paiza.B109;

import java.util.*;

public class B109 {

    static int[][] allSeat;

    static int p;

    static int q;

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int reservedSeatNum = sc.nextInt();

            allSeat = new int[sc.nextInt()][sc.nextInt()];

            p = sc.nextInt();

            q = sc.nextInt();

            for (int i = 0; i < allSeat.length; i++) {
                for (int j = 0; j < allSeat[i].length; j++) {
                    allSeat[i][j] = 0;
                }
            }

            for (int i = 0; i < reservedSeatNum; i++) {
                allSeat[sc.nextInt()][sc.nextInt()] = 1;
            }
        }

        Map<Integer, Integer> mostPlace = calcManhattanDistance(allSeat);

        for (int i : mostPlace.keySet()) {
            System.out.println(i + " " + mostPlace.get(i));
        }

    }

    static Map<Integer, Integer> calcManhattanDistance(int[][] allSeat) {
        int min = allSeat.length + allSeat[1].length;
        Map<Integer, Integer> mostPlace = new HashMap<>();
        for (int i = 0; i < allSeat.length; i++) {
            for (int j = 0; j < allSeat[i].length; j++) {
                if (allSeat[i][j] == 1) {
                    continue;
                }

                int tmpMin = Math.abs(p - i) + Math.abs(q - j);

                if (tmpMin < min) {
                    min = tmpMin;
                    mostPlace.clear();
                    mostPlace.put(i, j);
                } else if (tmpMin == min) {
                    mostPlace.put(i, j);
                }
            }
        }

        return mostPlace;
    }

}