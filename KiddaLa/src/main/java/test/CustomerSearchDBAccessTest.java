package test;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;

public class CustomerSearchDBAccessTest {

    private static int okCount = 0;
    private static int ngCount = 0;

    public static void main(String[] args) {

        CustomerSearchDBAccess dao =
                new CustomerSearchDBAccess();

        executeTest(
                "1. TEL正常",
                () -> dao.searchCustomerByTel(
                        "09012345678"),
                1
        );

        executeTest(
                "2. TEL該当なし",
                () -> dao.searchCustomerByTel(
                        "00000000000"),
                0
        );

        executeTest(
                "3. TELスペース入り",
                () -> dao.searchCustomerByTel(
                        "090 1234 5678"),
                0
        );

        executeTest(
                "4. カナ「イトウ」",
                () -> dao.searchCustomerByKana(
                        "イトウ"),
                2
        );

        executeTest(
                "5. カナ「ワタナベ」",
                () -> dao.searchCustomerByKana(
                        "ワタナベ"),
                1
        );

        executeTest(
                "6. カナ該当なし",
                () -> dao.searchCustomerByKana(
                        "ソンザイシナイ"),
                0
        );

        executeTest(
                "7. TEL＋カナ正常",
                () -> dao.searchCustomer(
                        "0314142135",
                        "ワタナベ"),
                1
        );

        executeTest(
                "8. TEL一致・カナ不一致",
                () -> dao.searchCustomer(
                        "0314142135",
                        "イトウ"),
                0
        );

        executeTest(
                "9. TEL不一致",
                () -> dao.searchCustomer(
                        "00000000000",
                        "ワタナベ"),
                0
        );

        System.out.println(
                "================================");

        System.out.println(
                "合計：" + (okCount + ngCount) + "件");

        System.out.println(
                "OK：" + okCount + "件");

        System.out.println(
                "NG：" + ngCount + "件");
    }

    private static void executeTest(
            String testName,
            SearchProcess process,
            int expectedCount) {

        System.out.println(
                "================================");

        System.out.println(
                "--- " + testName + " ---");

        try {

            ArrayList<Customer> list =
                    process.execute();

            int actualCount =
                    list == null ? 0 : list.size();

            System.out.println(
                    "期待件数：" + expectedCount);

            System.out.println(
                    "実際件数：" + actualCount);

            if (actualCount == expectedCount) {

                System.out.println("結果：OK");
                okCount++;

            } else {

                System.out.println("結果：NG");
                ngCount++;
            }

        } catch (Exception e) {

            System.out.println("結果：NG");

            System.out.println(
                    "例外："
                    + e.getClass().getName());

            System.out.println(
                    "メッセージ："
                    + e.getMessage());

            ngCount++;
        }

        System.out.println();
    }

    @FunctionalInterface
    private interface SearchProcess {

        ArrayList<Customer> execute()
                throws Exception;
    }
}
