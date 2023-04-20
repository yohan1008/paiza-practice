package paiza.b107;

import java.util.ArrayList;
import java.util.Scanner;

public class B107 {

    static int cardNum;
    static int[] cardArr;
    static int groupNum;
    static int shuffleCnt;
    static ArrayList<Integer> groupNumList = new ArrayList<>();
    static ArrayList<int[]> groupedCardList = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            cardNum = sc.nextInt();
            groupNum = sc.nextInt();
            shuffleCnt = sc.nextInt();
            cardArr = new int[cardNum];

            for(int i = 0 ; i < cardArr.length ; i++) {
                cardArr[i] = i + 1;
            }

            int mok = cardNum / groupNum;
            int namoge = cardNum % groupNum;

            for (int i = 0; i < mok; i++) {
                groupNumList.add(groupNum);
            }

            if (namoge != 0) {
                groupNumList.add(namoge);
            }
        }

        for (int i = 0; i < shuffleCnt; i++) {
            makeGroup();
            shuffle();
        }

        for (int i = 0; i < cardArr.length; i++) {
            System.out.println(cardArr[i]);
        }

    }

    static void makeGroup() {
        int cardIdx = 0;
        for (int cnt : groupNumList) {
            int[] tmpCardArr = new int[cnt];
            for (int i = 0; i < cnt; i++) {
                tmpCardArr[i] = cardArr[cardIdx];
                cardIdx++;
            }
            groupedCardList.add(tmpCardArr);
        }
    }

    static void shuffle() {
        ArrayList<int[]> tmpGroupedCardList = new ArrayList<>();
        for(int i = groupedCardList.size() - 1; i >= 0 ; i --) {
            tmpGroupedCardList.add(groupedCardList.get(i));
        }
        groupedCardList = tmpGroupedCardList;
        int cardIdx = 0;
        for(int[] card : groupedCardList) {
            for (int i = 0; i < card.length; i++) {
                cardArr[cardIdx] = card[i];
                cardIdx++;
            }
        }
        groupedCardList.clear();
    }
}
