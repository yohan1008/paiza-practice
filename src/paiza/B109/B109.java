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

        Map<Integer, Integer> mostPlace = calcManhattanDistance(allSeat);

        for (int i : mostPlace.keySet()) {
            System.out.println(i + " " + mostPlace.get(i));
        }

    }

    static Map<Integer, Integer> calcManhattanDistance(boolean[][] allSeat) {
        int min = (allSeat.length - 1) + (allSeat[1].length - 1);
        Map<Integer, Integer> mostPlace = new HashMap<>();
        for (int i = 0; i < allSeat.length; i++) {
            for (int j = 0; j < allSeat[i].length; j++) {
                System.out.println("i : " + i + ", j : " + j + ", min : " + min);

                if (allSeat[i][j]) {
                    System.out.println(i + ", " + j + " SKIP");
                    continue;
                }

                System.out.println("+++++++++++++++++++");

                int tmpMin = Math.abs(p - i) + Math.abs(q - j);

                System.out.println("tmpMin : " + tmpMin);

                if (tmpMin < min) {
                    min = tmpMin;
                    mostPlace.clear();
                    mostPlace.put(i, j);
                } else if (tmpMin == min) {
                    mostPlace.put(i, j);
                }

                System.out.println("--------------------------------------");
            }
        }

        return mostPlace;
    }

}
