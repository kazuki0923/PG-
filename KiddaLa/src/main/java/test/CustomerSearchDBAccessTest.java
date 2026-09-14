package test;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;

public class CustomerSearchDBAccessTest {

    private static int okCount = 0;
    private static int ngCount = 0;

    public static void main(String[] args) {

        CustomerSearchDBAccess dao = new CustomerSearchDBAccess();

        // 正式な単体テスト仕様書18項目のうち、DB停止を伴わない15項目を実施する。
        executeTest("1. TEL正常 09012345678",
                () -> dao.searchCustomerByTel("09012345678"), 1);

        executeTest("2. TEL該当なし 00000000000",
                () -> dao.searchCustomerByTel("00000000000"), 0);

        executeTest("3. TEL複数件 0314142135",
                () -> dao.searchCustomerByTel("0314142135"), 3);

        executeTest("4. TEL空文字",
                () -> dao.searchCustomerByTel(""), 0);

        executeTest("5. TEL null",
                () -> dao.searchCustomerByTel(null), 0);

        executeTest("7. カナ イトウ",
                () -> dao.searchCustomerByKana("イトウ"), 2);

        executeTest("8. カナ ワタナベ",
                () -> dao.searchCustomerByKana("ワタナベ"), 1);

        executeTest("9. カナ該当なし ソンザイシナイ",
                () -> dao.searchCustomerByKana("ソンザイシナイ"), 0);

        executeTest("10. カナ イトウハナエ",
                () -> dao.searchCustomerByKana("イトウハナエ"), 2);

        executeTest("11. カナ アオキ",
                () -> dao.searchCustomerByKana("アオキ"), 1);

        executeTest("13. TEL＋カナ正常 0314142135 / ワタナベ",
                () -> dao.searchCustomer("0314142135", "ワタナベ"), 1);

        executeTest("14. TEL一致・カナ不一致 0314142135 / イトウ",
                () -> dao.searchCustomer("0314142135", "イトウ"), 0);

        executeTest("15. TEL不一致 00000000000 / ワタナベ",
                () -> dao.searchCustomer("00000000000", "ワタナベ"), 0);

        executeTest("16. TEL＋カナ正常 09012345678 / アオキ",
                () -> dao.searchCustomer("09012345678", "アオキ"), 1);

        executeTest("17. TEL一致・カナ不一致 09012345678 / イトウ",
                () -> dao.searchCustomer("09012345678", "イトウ"), 0);

        System.out.println("================================");
        System.out.println("通常系・該当なし 合計：" + (okCount + ngCount) + "件");
        System.out.println("OK：" + okCount + "件");
        System.out.println("NG：" + ngCount + "件");
        System.out.println("※項番6・12・18は CustomerSearchDBAccessExceptionTest でDB停止状態にして実施する。");
    }

    private static void executeTest(
            String testName,
            SearchProcess process,
            int expectedCount) {

        System.out.println("================================");
        System.out.println("--- " + testName + " ---");

        try {
            ArrayList<Customer> list = process.execute();
            int actualCount = list == null ? 0 : list.size();

            System.out.println("期待件数：" + expectedCount);
            System.out.println("実際件数：" + actualCount);

            if (actualCount == expectedCount) {
                System.out.println("結果：OK");
                okCount++;
            } else {
                System.out.println("結果：NG");
                ngCount++;
            }

        } catch (Exception e) {
            System.out.println("結果：NG");
            System.out.println("例外：" + e.getClass().getName());
            System.out.println("メッセージ：" + e.getMessage());
            ngCount++;
        }
    }

    @FunctionalInterface
    private interface SearchProcess {
        ArrayList<Customer> execute() throws Exception;
    }
}
