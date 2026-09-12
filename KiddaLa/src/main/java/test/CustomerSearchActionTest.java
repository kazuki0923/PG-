package test;

import action.CustomerSearchAction;

public class CustomerSearchActionTest {

    private static int okCount = 0;
    private static int ngCount = 0;

    public static void main(String[] args) {

        CustomerSearchAction action = new CustomerSearchAction();

        executeTest(
                "1. 電話番号のみ",
                action,
                new String[] { "09012345678", "" },
                1,
                false
        );

        executeTest(
                "2. カナのみ",
                action,
                new String[] { "", "イトウハナエ" },
                2,
                false
        );

        executeTest(
                "3. TEL＋カナ",
                action,
                new String[] { "0314142135", "ワタナベ" },
                1,
                false
        );

        String[] spaceData = { " 090 1234 5678 ", "" };

        executeTest(
                "4. スペース除去",
                action,
                spaceData,
                1,
                false
        );

        if ("09012345678".equals(spaceData[0])) {
            System.out.println("スペース除去確認：OK");
        } else {
            System.out.println("スペース除去確認：NG");
            ngCount++;
        }

        executeTest(
                "5. 該当なし",
                action,
                new String[] { "00000000000", "" },
                0,
                true
        );

        executeTest(
                "6. 両方空文字",
                action,
                new String[] { "", "" },
                0,
                true
        );

        System.out.println("================================");
        System.out.println("合計：6件");
        System.out.println("OK：" + okCount + "件");
        System.out.println("NG：" + ngCount + "件");
    }

    private static void executeTest(
            String testName,
            CustomerSearchAction action,
            String[] data,
            int expectedRows,
            boolean expectNull) {

        System.out.println("================================");
        System.out.println("--- " + testName + " ---");

        try {
            String[][] result = action.execute(data);

            if (expectNull) {
                if (result == null) {
                    System.out.println("結果：OK");
                    okCount++;
                } else {
                    System.out.println("結果：NG");
                    System.out.println("期待値：null");
                    System.out.println("実際：" + result.length + "行");
                    ngCount++;
                }
                return;
            }

            int actualRows = result == null ? 0 : result.length;

            System.out.println("期待行数：" + expectedRows);
            System.out.println("実際行数：" + actualRows);

            if (result != null && actualRows == expectedRows) {
                System.out.println("結果：OK");
                okCount++;

                for (String[] row : result) {
                    System.out.println("--------------------");
                    for (String value : row) {
                        System.out.println(value);
                    }
                }
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
}
