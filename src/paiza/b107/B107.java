package paiza.b107;

import java.util.ArrayList;
import java.util.Scanner;

public class B107 {

    static ArrayList<Integer> cntArr = new ArrayList<>();
    static ArrayList<int[]> cardList = new ArrayList<>();
    static int cardNum;
    static int[] cardArr;
    static int setNum;
    static int shuffleCnt;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            cardNum = sc.nextInt();
            setNum = sc.nextInt();
            shuffleCnt = sc.nextInt();

            for(int i = 0 ; i < cardArr.length ; i++) {
                cardArr[i] = i + 1;
            }

            int mok = cardNum / setNum;
            int namoge = cardNum % setNum;

            for (int i = 0; i < mok; i++) {
                cntArr.add(setNum);
            }

            if (namoge != 0) {
                cntArr.add(namoge);
            }
        }

        grouping(cardArr);

        for (int i = 0; i < shuffleCnt; i++) {
            shuffle();

            System.out.println("================");
            for(int[] card : cardList) {
                for(int j = 0 ; j < card.length ; j++) {
                    System.out.println(card[j]);
                }
            }
            System.out.println("================");
        }

        for(int[] card : cardList) {
            for(int i = 0 ; i < card.length ; i++) {
                System.out.println(card[i]);
            }
        }

    }

    static void grouping(int[] cardArr) {
        //다시 배열로 변환 해야됨
        int cardIdx = 0;
        for (int cnt : cntArr) {
            int[] tmpCardArr = new int[cnt];
            for (int i = 0; i < cnt; i++) {
                tmpCardArr[i] = cardArr[cardIdx];
                cardIdx++;
            }
            cardList.add(tmpCardArr);
        }
    }

    static void shuffle() {
        ArrayList<int[]> tmpCardList = new ArrayList<>();
        for(int i = cardList.size() - 1; i >= 0 ; i --) {
            tmpCardList.add(cardList.get(i));
        }
        cardList = tmpCardList;
    }

}
