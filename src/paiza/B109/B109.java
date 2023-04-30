package paiza.B109;

import java.util.*;

/**
 * B109の問題はもっとも映画が見やすい席からマンハッタン距離を計算して映画が見やすい席を求める問題となる。
 * まず全ての席を表す必要があるため、2次元配列で全席を表す。またbooleanの配列で表すことで予約されているか予約されていないかを保持することができる。
 * (予約されている場合はtrue、されていない場合はfalseとする)
 *
 * もっとも映画が見やすいと指定された席は一つでその席の予約がされていない場合はもっとも映画が見やすい席となるので、その席の位置をただ出力する。
 * もしもっとも映画が見やすい席が予約済みの場合はマンハッタン距離を計算してまだ予約されていない席からもっとも映画がみやすい席をもとめて出力する。
 *
 * @author kang yohan
 */

public class B109 {

    //もっとも映画が見やすい席の縦の位置(P)
    static int bestSeatP;

    //もっとも映画が見やすい席の横の位置(Q)
    static int bestSeatQ;

    public static void main(String[] args) {

        //全席
        boolean[][] allSeats;
        try (Scanner sc = new Scanner(System.in)) {
            //予約されてる席の数を取得する。
            int reservedSeatNum = sc.nextInt();

            //全席を作成する。
            allSeats = new boolean[sc.nextInt()][sc.nextInt()];
            for (int i = 0; i < allSeats.length; i++) {
                for (int j = 0; j < allSeats[i].length; j++) {
                    allSeats[i][j] = false;
                }
            }

            //もっとも映画が見やすい席の縦の位置を取得する(P)
            bestSeatP = sc.nextInt();

            //もっとも映画が見やすい席の横の位置を取得する(Q)
            bestSeatQ = sc.nextInt();

            //予約済みの席を取得する。
            for (int i = 0; i < reservedSeatNum; i++) {
                allSeats[sc.nextInt()][sc.nextInt()] = true;
            }
        }

        if (allSeats[bestSeatP][bestSeatQ]) {
            //もっとも映画が見やすい席が予約さえれている場合
            //マンハッタン距離を計算して未予約席の中でもっとも映画がみやすい席を求めて出力する。
            List<int[]> goodSeats = calcManhattanDistance(allSeats);
            for (int[] seat : goodSeats) {
                System.out.println(seat[0] + " " + seat[1]);
            }
        } else {
            //もっとも映画が見やすい席が未予約の場合は単純にその席を出力する。
            System.out.println(bestSeatP + " " + bestSeatQ);
        }

    }

    /**
     * マンハッタン距離を計算してもっとも映画が見やすい席を求める。
     *
     * @param allSeat
     * @return
     */
    static List<int[]> calcManhattanDistance(boolean[][] allSeat) {
        //最初は現状のマンハッタン距離の最大値をまず格納してもっと短い距離があれば更新させる。
        int min = (allSeat.length - 1) + (allSeat[1].length - 1);

        //未予約席の中でもっとも映画がみやすい席
        List<int[]> goodSeats = new ArrayList<>();

        for (int i = 0; i < allSeat.length; i++) {
            for (int j = 0; j < allSeat[i].length; j++) {

                //予約済みの席はスキップさせる。
                if (allSeat[i][j]) {
                    continue;
                }

                //未予約の席の場合
                //マンハッタン距離を計算する。
                int tmpMin = Math.abs(bestSeatP - i) + Math.abs(bestSeatQ - j);

                if (tmpMin < min) {
                    //現在の最小値のマンハッタン距離よりももっと短い場合,最小値を更新後、映画が見やすい席のリストをクリアして再度作り直す。
                    min = tmpMin;
                    goodSeats.clear();
                    goodSeats.add(new int[]{i, j});
                } else if (tmpMin == min) {
                    //現在の最小値のマンハッタン距離と同じ場合、映画が見やすい席のリストに追加する。
                    goodSeats.add(new int[]{i, j});
                }
            }
        }

        //映画が見やすい席のリストをreturnする。
        return goodSeats;
    }

}
