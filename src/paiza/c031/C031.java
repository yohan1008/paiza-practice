package paiza.c031;

import java.util.*;

/**
 * C031問題は標準時刻と標準時刻からの各都市の進み、この二つを利用して全世界の各都市に合った日記投稿時刻を求める問題となる。
 * 最初に入力から一つの都市名、都市からの投稿時刻、標準時刻からの進みを必ず提供して貰えるため、この三つを利用して標準時刻を求める
 * その後、求めた標準時刻に各都市の標準刻からの進みを足し、各都市の投稿時刻を計算する。
 * <p>
 * 各都市と標準時刻を紐づけて置く必要があるのと、入力された順で投稿時刻を出力させる必要があるため、
 * LikedHashMapに都市名と標準時刻からの進みを保持することにする。
 * また、時間は24時間制(00:00~23:59)で表示する必要があるため24以上の場合は-24をし、-1以下の場合は+24の計算を行う。
 * 分に関しては処理が不要なため最初に求めた分をそのまま保持し、出力させる。
 *
 * @author kang yohan
 */

public class C031 {

    //各都市の総数
    static int cityNum;
    //各都市の標準時刻からの進み
    static Map<String, Integer> cityTimeDiffes = new LinkedHashMap<>();
    //投稿を行ったユーザの所在地の都市名
    static String cityName;
    //投稿を行ったユーザの現地での投稿時刻
    static String cityTime;
    //標準時刻の時間
    static int standardTimeHour;
    //標準時刻の分
    static String standardTimeMin;

    public static void main(String[] args) {

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            //各都市の総数を取得
            cityNum = sc.nextInt();
            //各都市の標準時刻からの進みを取得
            for (int i = 0; i < cityNum; i++) {
                cityTimeDiffes.put(sc.next(), sc.nextInt());
            }
            //投稿を行ったユーザの所在地の都市名を取得
            cityName = sc.next();
            //投稿を行ったユーザの現地での投稿時刻を取得
            cityTime = sc.next();
        }

        //------------------------------------------------------------
        // 処理及び出力
        //------------------------------------------------------------
        //標準時刻を求める
        calcStandardTime();
        //各都市の投稿時間を求めて出力する
        calcAndPrintPostTime();
    }

    /**
     * 標準時刻を計算する。
     *
     * @author kang yohan
     */
    static void calcStandardTime() {
        //標準時刻を計算するために投稿時刻の時間をIntegerに変換
        int cityHourInt = Integer.parseInt(cityTime.substring(0, 2));

        //ユーザーの投稿時刻を使用して標準時刻の時間を計算
        standardTimeHour = cityHourInt - cityTimeDiffes.get(cityName);
        //24時間制に変換する
        if (standardTimeHour >= 24) {
            //24以上の場合
            standardTimeHour = standardTimeHour - 24;
        } else if (standardTimeHour < 0) {
            //0より小さい場合
            standardTimeHour = standardTimeHour + 24;
        }

        //標準時刻の分を求める（最終的に出力するだけなので処理は不要）
        standardTimeMin = cityTime.substring(3, cityTime.length());
    }

    /**
     * 各都市の投稿時刻を計算して出力する
     *
     * @author kang yohan
     */
    static void calcAndPrintPostTime() {
        for (String key : cityTimeDiffes.keySet()) {
            //標準時刻に標準時刻からの進みを足しての各都市の投稿時刻を計算する
            int citiesHourInt = standardTimeHour + cityTimeDiffes.get(key);

            //24時間制に変換する
            if (citiesHourInt >= 24) {
                //24以上の場合
                citiesHourInt = citiesHourInt - 24;
            } else if (citiesHourInt < 0) {
                //0よりも小さい場合
                citiesHourInt = citiesHourInt + 24;
            }

            //時が10より小さい場合は出力のため0をつける
            String citiesHourStr = citiesHourInt < 10 ? "0" + citiesHourInt : Integer.toString(citiesHourInt);
            //答えの出力
            System.out.println(citiesHourStr + ":" + standardTimeMin);
        }
    }
}