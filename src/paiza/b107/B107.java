package paiza.b107;

import java.util.ArrayList;
import java.util.Scanner;

public class B107 {

    static int[] cardsArr;
    static int setNum;
    static int shuffleCnt;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            cardsArr = new int[sc.nextInt()];

            for (int i = 0; i < cardsArr.length; i++) {
                cardsArr[i] = i + 1;
            }

            setNum = sc.nextInt();

            shuffleCnt = sc.nextInt();
        }


    }

    static void shuffle() {
        ArrayList<int[]> groupCardsArr = new ArrayList<>();
        for(int i = 0 ; i < cardsArr.length ; i++) {
            for(int j = 0 ; j < setNum ; j++) {

            }
        }
    }

}
