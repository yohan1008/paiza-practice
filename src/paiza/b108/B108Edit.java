package paiza.b108;

import java.util.*;

public class B108Edit {

    //ゴンドラの数
    static int gondolaNum = 0;
    //グループの数
    static int groupNum = 0;
    //乗車するゴンドラを表すインデックス
    static int gondolaIdx = 0;
    //各ゴンドラの定員
    static int[] groupGondolaCapacity;
    //最後に各ゴンドラに乗った人数（答え）
    static int[] resultGroupPeopleNum;

    public static void main(String[] args) {

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        //・指摘事項
        //「Scanner」などのClosableメソッドを使用するときはclose()を利用してリソースを閉じる必要がある。
        //もし閉じていない場合リソースリークの問題に繋がるため癖をつけること。
        //JavaSE7からは「try-with-resource文」で自動的にリソースをcloseさせることが出来、close漏れを防ぐことが出来る。

        //各グループの人数
        int[] groupPeopleNum;
        try (Scanner sc = new Scanner(System.in)) {
            //ゴンドラの数を取得
            gondolaNum = sc.nextInt();
            //グループの数を取得
            groupNum = sc.nextInt();
            //ゴンドラの定員を取得
            groupGondolaCapacity = new int[gondolaNum];
            //各グループの人数を取得
            groupPeopleNum = new int[groupNum];
            //最後に各ゴンドラに乗った人数
            resultGroupPeopleNum = new int[gondolaNum];

            //各ゴンドラの定員を取得
            for (int i = 0; i < gondolaNum; i++) {
                groupGondolaCapacity[i] = sc.nextInt();
            }

            //各グループの人数を取得
            for (int i = 0; i < groupNum; i++) {
                groupPeopleNum[i] = sc.nextInt();
            }
        }

        //------------------------------------------------------------
        // 集計
        //------------------------------------------------------------
        //・指摘事項
        //「do ~ while()」を利用したいなと思ったとき、トラバースしながら処理するときは
        //再帰ループを利用して処理を行う方が良い。
        //再帰ループのメリットとしてはネストも減らせて可読性が上がり、無駄な変数を利用しなくても済むようになる。
        for (int peopleNum : groupPeopleNum) {
            calcTotalPassengers(peopleNum);
        }

        //------------------------------------------------------------
        // 出力
        //------------------------------------------------------------
        //・指摘事項
        //for文を使用する必要がない場合は拡張for文を使用すること
        //拡張for文を使用することで何らかの不具合により「IndexOutOfBoundsExceptoin」などのエラーが
        //出てしまう恐れ無くループ処理を実装することができる。
        for (int peopleNum : resultGroupPeopleNum) {
            System.out.println(peopleNum);
        }
    }

    /**
     * ゴンドラの乗車人数を求める。
     *
     * @param peopleNum
     */
    public static void calcTotalPassengers(int peopleNum) {
        //現時点で乗車するゴンドラを表すインデックス
        int currentGondolaIdx = gondolaIdx;
        //現時点で乗車するゴンドラに乗れる定員
        int currentCapacity = groupGondolaCapacity[currentGondolaIdx];

        //次のゴンドラに切り替える。ゴンドラが一周したら最初のゴンドラに切り替える。
        gondolaIdx++;
        if (gondolaIdx > (gondolaNum - 1)) {
            gondolaIdx = 0;
        }

        //定員以内の場合は該当のグループを全員乗せたことになるので、再帰ループを終了させ次のグループを乗車させる。
        if (peopleNum <= currentCapacity) {
            resultGroupPeopleNum[currentGondolaIdx] += peopleNum;
            return;
        }

        //定員より多い場合は続けて乗せる必要があるため、再帰ループを使用して残りのグループを乗車させる。
        resultGroupPeopleNum[currentGondolaIdx] += currentCapacity;
        calcTotalPassengers(peopleNum - currentCapacity);
    }
}