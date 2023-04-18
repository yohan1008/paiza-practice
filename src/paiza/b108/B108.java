package paiza.b108;

import java.util.Scanner;

public class B108 {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        Scanner sc = new Scanner(System.in);
        //ゴンドラの数
        int gondoraNum = sc.nextInt();
        //グループの数
        int groupNum = sc.nextInt();
        //各ゴンドラのリミット
        int[] numLimit = new int[gondoraNum];
        //各グループの人数
        int[] groupPeopleNum = new int[groupNum];
        //最後に各ゴンドラに乗った人数（答え）
        int[] resultGroupPeopleNum = new int[gondoraNum];
        int total = 0;

        //ゴンドラのリミットを代入
        for (int i = 0; i < gondoraNum; i++) {
            numLimit[i] = sc.nextInt();
        }

        //各グループの人数を代入
        for (int i = 0; i < groupNum; i++) {
            int tmpGroupPeopleNum = sc.nextInt();
            groupPeopleNum[i] = tmpGroupPeopleNum;
            total += tmpGroupPeopleNum;
        }

        //ゴンドラの切り替えのための変数
        int j = 0;
        //グループの切り替えのための変数
        int k = 0;
        while (total > 0) {
            //各ゴンドラのリミット
            int limit = numLimit[j];
            //各グループの人数
            int peopleNum = groupPeopleNum[k];

            if (limit >= peopleNum) {
                //乗るグループの人数がゴンドラのリミットの以下の場合
                //全員ゴンドラに乗せる
                resultGroupPeopleNum[j] += peopleNum;
                //トータルの人数から引く
                total = total - peopleNum;
                //全てのグループを乗せたため次のグループに変更
                if (k == groupNum - 1) {
                    k = 0;
                } else {
                    k++;
                }
            } else {
                //乗るグループの人数がゴンドラのリミットの以上の場合
                //乗せれる人数分だけ乗せる。まだ残人数があるためグループの切り替えは無し。
                resultGroupPeopleNum[j] += limit;
                //次のゴンドラに残人数を乗せるために人数を引き継ぐ
                groupPeopleNum[k] = peopleNum - limit;
                //トータルの人数から引く
                total = total - limit;
            }

            //ゴンドラの切り替え
            if (j == gondoraNum - 1) {
                j = 0;
            } else {
                j++;
            }
        }

        //最後の出力
        for (int i = 0; i < resultGroupPeopleNum.length; i++) {
            System.out.println(resultGroupPeopleNum[i]);
        }
    }
}