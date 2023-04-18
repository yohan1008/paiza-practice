package paiza.b108;

import java.util.Scanner;


/**
 * paizaの課題は各グループの全員が順番にゴンドラに乗っていった場合に、各ゴンドラの合計乗車人数は何人か？を計算します。
 * そのため、繰り返し処理の起点を「グループ」として、グループの人数分ゴンドラの合計乗車人数を計算する。
 * グループの人数がすべてゴンドラに乗って計算が終わったら、次のグループを対象として同じ処理を繰り返す。
 *
 * という形にすると、仕様と実装が同じような作りとなり、理解しやすくなると思います。
 *
 * そのため、本コードではループ処理の起点をグループの人数を保持する配列としました。
 * そして、グループに所属する人数を順次ゴンドラに乗せていく処理は、最終的に全員がゴンドラに乗り終わるまで
 * 何回繰り返せば良いか？が予め分からないことから、再帰処理として、全員が乗り込むまでは繰り返す形としました。
 *
 * 配列を拡張for文でループすることで、想定外の配列インデックスにアクセスして「IndexOutOfBoundsExceptoin」が発生しないようにしています！！
 *
 * @author 児島弘
 *
 */
public class B108Review {

    // 観覧車で稼働しているゴンドラの数
    static private int gondolaNum;

    // 乗客が登場する対象のゴンドラを表すインデックス番号
    static private int gondolaIdx = 0;

    // 各ゴンドラの収容可能人数。ゴンドラ一つ一つの定員が設定される。
    static private int[] gondolaCapacity;

    // 各ゴンドラの合計乗車人数。ゴンドラ一つ一つの合計乗車人数を計上する。
    static private int[] gondolaTotalPassengers;

    public static void main(String[] args) {

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        // 各グループの人数
        int[] groupPeopleNum;
        try (Scanner sc = new Scanner(System.in)) {
            // ゴンドラの数
            gondolaNum = sc.nextInt();
            // ゴンドラの定員を記録する配列
            gondolaCapacity = new int[gondolaNum];
            // ゴンドラの合計乗車人数を記録する配列
            gondolaTotalPassengers = new int[gondolaNum];

            // グループの数
            int groupNum = sc.nextInt();
            // グループの人数を記録する配列
            groupPeopleNum = new int[groupNum];

            // 各ゴンドラの定員を取得する
            for (int i = 0; i < gondolaNum; i++) {
                gondolaCapacity[i] = sc.nextInt();
            }

            // 各グループの人数を取得する
            for (int i = 0; i < groupNum; i++) {
                groupPeopleNum[i] = sc.nextInt();
            }
        }

        //------------------------------------------------------------
        // 集計
        //------------------------------------------------------------
        // 各ゴンドラの合計乗車人数を計上する。
        for (int peopleNum : groupPeopleNum) {
            calcTotalPassengers(peopleNum);
        }

        //------------------------------------------------------------
        // 出力
        //------------------------------------------------------------
        // 合計乗車人数の出力
        for (int totalPassengers : gondolaTotalPassengers) {
            System.out.println(totalPassengers);
        }
    }

    /**
     *ゴンドラの合計乗車人数を求める。
     *
     * @param peopleNum 乗車人数
     */
    private static void calcTotalPassengers(int peopleNum) {
        // 乗車するゴンドラのインデックス
        int currentGondolaIdx = gondolaIdx;
        // 乗車するゴンドラの定員を取得する
        int capacity = gondolaCapacity[currentGondolaIdx];

        // 次のゴンドラにインデックスを進める。ゴンドラが一周したら最初のゴンドラに乗車するのでインデックスが戻る。
        // グループの人数は最低値が一人であり、0人はない仕様。そのため、定員以内でも、定員越えでも必ず次のゴンドラに進めて良い。
        gondolaIdx++;
        if (gondolaIdx > (gondolaNum - 1)) {
            gondolaIdx = 0;
        }

        // 乗車人数がゴンドラの定員以内であれば今回のグループは全員が乗車したためループ処理を終了して次のグループを処理する。
        if (peopleNum <= capacity) {
            gondolaTotalPassengers[currentGondolaIdx] += peopleNum;
            return;
        }

        // 乗車人数がゴンドラの定員を超えている場合は定員分乗車する。
        // 残りの人数は次のゴンドラを対象に再度乗車処理を再帰的に行い、全員がゴンドラに乗り終わるまで再帰ループする
        gondolaTotalPassengers[currentGondolaIdx] += capacity;
        calcTotalPassengers(peopleNum - capacity);
    }
}