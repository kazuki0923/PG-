package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 注文管理用ユーティリティ
 */
public class OrderControlUtility {

    /**
     * 顧客情報リストを検索結果表示用データに変換する。
     * @param list 顧客情報リスト: ArrayList<Customer>
     * @return tableData 検索結果表示用データ: String[][]
     */
    public static String[][] customerToArray(ArrayList<Customer> list) {
        if (list == null || list.isEmpty()) {
            return new String[0][0];
        }

        String[][] tableData = new String[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            Customer c = list.get(i);
            tableData[i][0] = String.valueOf(c.getCustId());
            tableData[i][1] = c.getCustName();
            tableData[i][2] = c.getKana();
            tableData[i][3] = c.getAddress();
        }

        return tableData;
    }

    /**
     * 商品情報リストを商品情報表示用データに変換する。
     * @param list 商品情報リスト: ArrayList<Item>
     * @return tableData 商品情報表示用データ: String[][]
     */
    public static String[][] itemToArray(ArrayList<Item> list) {
        if (list == null || list.isEmpty()) {
            return new String[0][0];
        }

        String[][] tableData = new String[list.size()][3];
        return tableData;
    }

    /**
     * 注文明細情報リストを注文明細情報表示用データに変換する。
     * @param orderDetailList 注文明細情報リスト: ArrayList<OrderDetail>
     * @return tableData 注文明細情報表示用データ: String[][]
     */
    public static String[][] orderToArray(ArrayList<OrderDetail> orderDetailList) {
        if (orderDetailList == null || orderDetailList.isEmpty()) {
            return new String[0][0];
        }

        String[][] tableData = new String[orderDetailList.size()][4];
        return tableData;
    }

    /**
     * 現在日付を生成して返却する。
     * @return year + "-" + month + "-" + day (現在日付: String)
     */
    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);

        return year + "-" + month + "-" + day;
    }
}
