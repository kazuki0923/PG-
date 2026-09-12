package test;

import java.util.ArrayList;

import action.CustomerSearchAction;
import dao.CustomerSearchDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class CustomerSearchIntegrationTest {

    private static int okCount = 0;
    private static int ngCount = 0;

    public static void main(String[] args) {

        CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
        CustomerSearchAction action = new CustomerSearchAction();

        try {
            // 1. 結合1 OrderControlUtility - Customer
            ArrayList<Customer> oneCustomer = new ArrayList<Customer>();
            oneCustomer.add(new Customer(
                    1,
                    "青木まゆみ",
                    "アオキマユミ",
                    "09012345678",
                    "東京都千代田区神田小川町1-1-1"));

            String[][] converted = OrderControlUtility.customerToArray(oneCustomer);
            check(
                    "1. Utility-Customer",
                    converted != null
                    && converted.length == 1
                    && converted[0].length == 4
                    && "1".equals(converted[0][0])
                    && "青木まゆみ".equals(converted[0][1])
                    && "アオキマユミ".equals(converted[0][2])
                    && "東京都千代田区神田小川町1-1-1".equals(converted[0][3]));

            // 2. 結合2 DAO - Customer（TEL）
            ArrayList<Customer> telList = dao.searchCustomerByTel("09012345678");
            check(
                    "2. DAO-Customer TEL",
                    telList != null
                    && telList.size() == 1
                    && telList.get(0).getCustId() == 1
                    && "青木まゆみ".equals(telList.get(0).getCustName()));

            // 3. 結合2 DAO - Customer（カナ）
            ArrayList<Customer> kanaList = dao.searchCustomerByKana("イトウ");
            check(
                    "3. DAO-Customer カナ",
                    kanaList != null
                    && kanaList.size() == 2
                    && kanaList.get(0).getKana().contains("イトウ")
                    && kanaList.get(1).getKana().contains("イトウ"));

            // 4. 結合2 DAO - Customer（TEL＋カナ）
            ArrayList<Customer> bothList = dao.searchCustomer("0314142135", "ワタナベ");
            check(
                    "4. DAO-Customer TEL+カナ",
                    bothList != null
                    && bothList.size() == 1
                    && bothList.get(0).getCustId() == 15
                    && "ワタナベカナコ".equals(bothList.get(0).getKana()));

            // 5. 結合3 Action - DAO（TELのみ）
            String[][] actionTel = action.execute(new String[] { "09012345678", "" });
            check(
                    "5. Action-DAO TELのみ",
                    actionTel != null
                    && actionTel.length == 1
                    && "1".equals(actionTel[0][0]));

            // 6. 結合3 Action - DAO（カナのみ）
            String[][] actionKana = action.execute(new String[] { "", "イトウ" });
            check(
                    "6. Action-DAO カナのみ",
                    actionKana != null
                    && actionKana.length == 2);

            // 7. 結合3 Action - DAO（TEL＋カナ）
            String[][] actionBoth = action.execute(new String[] { "0314142135", "ワタナベ" });
            check(
                    "7. Action-DAO TEL+カナ",
                    actionBoth != null
                    && actionBoth.length == 1
                    && "15".equals(actionBoth[0][0]));

            // 8. 結合4 Action - Customer
            check(
                    "8. Action-Customer 属性保持",
                    actionBoth != null
                    && actionBoth.length == 1
                    && "渡部香生子".equals(actionBoth[0][1])
                    && "ワタナベカナコ".equals(actionBoth[0][2])
                    && "東京都千代田区神田神保町1-1-1".equals(actionBoth[0][3]));

            // 9. 結合5 Action - Utility
            check(
                    "9. Action-Utility 配列変換",
                    actionTel != null
                    && actionTel.length == 1
                    && actionTel[0].length == 4);

            // 10. 結合5 検索結果0件
            String[][] noHit = action.execute(new String[] { "00000000000", "" });
            check(
                    "10. Action-Utility 0件",
                    noHit == null);

            // 11. 全体 TELのみ
            String[][] wholeTel = action.execute(new String[] { "09012345678", "" });
            check(
                    "11. 全体 TELのみ",
                    wholeTel != null
                    && wholeTel.length == 1
                    && "青木まゆみ".equals(wholeTel[0][1]));

            // 12. 全体 カナのみ
            String[][] wholeKana = action.execute(new String[] { "", "イトウ" });
            check(
                    "12. 全体 カナのみ",
                    wholeKana != null
                    && wholeKana.length == 2);

            // 13. 全体 TEL＋カナ
            String[][] wholeBoth = action.execute(new String[] { "0314142135", "ワタナベ" });
            check(
                    "13. 全体 TEL+カナ",
                    wholeBoth != null
                    && wholeBoth.length == 1
                    && "渡部香生子".equals(wholeBoth[0][1]));

            // 14. 全体 該当なし
            String[][] wholeNoHit = action.execute(new String[] { "00000000000", "" });
            check(
                    "14. 全体 該当なし",
                    wholeNoHit == null);

        } catch (Exception e) {
            System.out.println("テスト実行中に例外が発生しました。");
            System.out.println("例外：" + e.getClass().getName());
            System.out.println("メッセージ：" + e.getMessage());
            ngCount++;
        }

        System.out.println("================================");
        System.out.println("合計：" + (okCount + ngCount) + "件");
        System.out.println("OK：" + okCount + "件");
        System.out.println("NG：" + ngCount + "件");
    }

    private static void check(String name, boolean condition) {
        System.out.println("================================");
        System.out.println("--- " + name + " ---");

        if (condition) {
            System.out.println("結果：OK");
            okCount++;
        } else {
            System.out.println("結果：NG");
            ngCount++;
        }
    }
}
