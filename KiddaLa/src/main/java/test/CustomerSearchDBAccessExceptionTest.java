package test;

import dao.CustomerSearchDBAccess;

public class CustomerSearchDBAccessExceptionTest {

    private static int okCount = 0;
    private static int ngCount = 0;

    public static void main(String[] args) {

        CustomerSearchDBAccess dao = new CustomerSearchDBAccess();

        System.out.println("=== DB接続エラーテスト ===");
        System.out.println("※DBを停止した状態で実行する。");

        executeTest("3. searchCustomerByTel DB接続異常",
                () -> dao.searchCustomerByTel("09012345678"));

        executeTest("12. searchCustomerByKana DB接続異常",
                () -> dao.searchCustomerByKana("イトウ"));

        executeTest("18. searchCustomer DB接続異常",
                () -> dao.searchCustomer("09012345678", "アオキ"));

        System.out.println("================================");
        System.out.println("合計：" + (okCount + ngCount) + "件");
        System.out.println("OK：" + okCount + "件");
        System.out.println("NG：" + ngCount + "件");
    }

    private static void executeTest(String testName, ExceptionProcess process) {
        System.out.println("================================");
        System.out.println("--- " + testName + " ---");

        try {
            process.execute();
            System.out.println("結果：NG");
            System.out.println("Exceptionが発生しませんでした。");
            ngCount++;
        } catch (Exception e) {
            System.out.println("結果：OK");
            System.out.println("Exception発生を確認");
            System.out.println("例外：" + e.getClass().getName());
            okCount++;
        }
    }

    @FunctionalInterface
    private interface ExceptionProcess {
        void execute() throws Exception;
    }
}
