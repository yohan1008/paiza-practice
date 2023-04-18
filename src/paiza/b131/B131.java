package paiza.b131;

import java.util.*;

/**
 * B131は電車での移動ルートの運賃を計算する問題となる。ここでN個の路線とM個の駅が存在する。
 * 現在地と目的地さえ分かれば運賃表で移動にかかる運賃を検索することができる。そして検索した運賃を順に足していき、合計運賃をか計算する。
 * <p>
 * 運賃表はＮ個の各路線は同じ駅数をもっているため、2次元配列に保持をする。
 * また、各駅を移動するルートも同様[路線、目的地]の形で固定で入力されるため、2次元配列で保持をする。
 * 同じ番号の駅に移動する場合は特に処理が不要なため、スキップさせる。
 *
 * @author kang yohan
 */

public class B131 {

    //運賃表
    static int[][] subwayFareArr;

    //移動ルート
    static int[][] routeArr;

    //現在地
    static int currentStation = 1;

    //合計運賃
    static int totalFare = 0;

    public static void main(String[] args) {
        //-------------------------------------------------------
        //入力
        //-------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            //運賃表を取得
            subwayFareArr = new int[sc.nextInt()][sc.nextInt()];
            for (int i = 0; i < subwayFareArr.length; i++) {
                for (int j = 0; j < subwayFareArr[i].length; j++) {
                    subwayFareArr[i][j] = sc.nextInt();
                }
            }

            //移動ルートを取得
            routeArr = new int[sc.nextInt()][2];
            for (int i = 0; i < routeArr.length; i++) {
                routeArr[i][0] = sc.nextInt();
                routeArr[i][1] = sc.nextInt();
            }
        }

        //-------------------------------------------------------
        //集計
        //-------------------------------------------------------
        //現在地から目的地まで移動する際にかかる運賃を求めて合計運賃に加算する。
        try {
            for (int i = 0; i < routeArr.length; i++) {
                calcTotalFare(routeArr[i][0], routeArr[i][1]);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("存在しない駅です。");
        }

        //-------------------------------------------------------
        //出力
        //-------------------------------------------------------
        System.out.println(totalFare);
    }

    /**
     * 現在地から目的地まで移動する際にかかる運賃を計算して合計運賃に加算する。
     *
     * @param routeNo
     * @param destinationStation
     */
    static void calcTotalFare(int routeNo, int destinationStation) {
        //現在地と目的地の駅番号が同じ場合は処理不要
        if (currentStation == destinationStation) {
            return;
        }

        //配列のインデックスは0から始まるため-1をする
        int tmpRouteNo = routeNo - 1;
        int tmpCurrentStation = currentStation - 1;
        int tmpDestinationStation = destinationStation - 1;

        //現在地から目的地まで移動する際にかかる運賃を計算する
        //0番の駅から現在地の駅までの運賃 - 0番の駅から目的地の駅までの運賃 = 移動する際にかかる運賃
        int toCurStationFare = subwayFareArr[tmpRouteNo][tmpCurrentStation];
        int toDesStationFare = subwayFareArr[tmpRouteNo][tmpDestinationStation];
        //マイナスの場合はプラスに変換する
        int fare = toCurStationFare - toDesStationFare < 0 ?
                -(toCurStationFare - toDesStationFare) : toCurStationFare - toDesStationFare;

        //合計運賃に加算
        totalFare += fare;
        //移動後は目的地が現在地になるため現在地を変更する
        currentStation = destinationStation;
    }
}
