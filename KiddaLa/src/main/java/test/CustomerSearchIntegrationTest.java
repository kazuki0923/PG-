package test;

import java.util.ArrayList;
import java.util.Arrays;

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

        // 結合1：OrderControlUtility - Customer（5項目）
        executeTest("結合1-1 Customerクラスを解決できる",
                () -> Class.forName("model.Customer") != null);

        executeTest("結合1-2 customerToArray内でgetCustIdを使用できる", () -> {
            String[][] data = OrderControlUtility.customerToArray(createOneCustomer());
            return data.length == 1 && "1".equals(data[0][0]);
        });

        executeTest("結合1-3 customerToArray内でgetCustNameを使用できる", () -> {
            String[][] data = OrderControlUtility.customerToArray(createOneCustomer());
            return data.length == 1 && "青木まゆみ".equals(data[0][1]);
        });

        executeTest("結合1-4 customerToArray内でgetKanaを使用できる", () -> {
            String[][] data = OrderControlUtility.customerToArray(createOneCustomer());
            return data.length == 1 && "アオキマユミ".equals(data[0][2]);
        });

        executeTest("結合1-5 customerToArray内でgetAddressを使用できる", () -> {
            String[][] data = OrderControlUtility.customerToArray(createOneCustomer());
            return data.length == 1
                    && "東京都千代田区神田小川町1-1-1".equals(data[0][3]);
        });

        // 結合2：CustomerSearchDBAccess - Customer（5項目）
        executeTest("結合2-1 Customerクラスを解決できる",
                () -> Class.forName("model.Customer") != null);

        executeTest("結合2-2 searchCustomerByTelでCustomerを生成できる", () -> {
            ArrayList<Customer> list = dao.searchCustomerByTel("09012345678");
            return list != null && list.size() == 1 && list.get(0) != null;
        });

        executeTest("結合2-3 searchCustomerByKanaで検索結果件数分のCustomerを生成できる", () -> {
            ArrayList<Customer> list = dao.searchCustomerByKana("イトウ");
            return list != null && list.size() == 2
                    && list.get(0) != null && list.get(1) != null;
        });

        executeTest("結合2-4 searchCustomerでCustomerをArrayListへ追加できる", () -> {
            ArrayList<Customer> list = dao.searchCustomer("0314142135", "ワタナベ");
            return list != null && list.size() == 1 && list.get(0) != null;
        });

        executeTest("結合2-5 ResultSetの値をCustomerへ設定できる", () -> {
            ArrayList<Customer> list = dao.searchCustomerByTel("09012345678");
            if (list == null || list.size() != 1) {
                return false;
            }
            Customer c = list.get(0);
            return c.getCustId() == 1
                    && "青木まゆみ".equals(c.getCustName())
                    && "アオキマユミ".equals(c.getKana())
                    && "09012345678".equals(c.getTel())
                    && "東京都千代田区神田小川町1-1-1".equals(c.getAddress());
        });

        // 結合3：CustomerSearchAction - CustomerSearchDBAccess（4項目）
        executeTest("結合3-1 CustomerSearchDBAccessクラスを解決できる",
                () -> Class.forName("dao.CustomerSearchDBAccess") != null);

        executeTest("結合3-2 電話番号のみ指定時にTEL検索を利用できる", () -> {
            String[][] data = action.execute(new String[] { "09012345678", "" });
            return data != null && data.length == 1 && "1".equals(data[0][0]);
        });

        executeTest("結合3-3 カナのみ指定時にカナ検索を利用できる", () -> {
            String[][] data = action.execute(new String[] { "", "イトウ" });
            return data != null && data.length == 2;
        });

        executeTest("結合3-4 電話番号・カナ指定時に複合検索を利用できる", () -> {
            String[][] data = action.execute(new String[] { "0314142135", "ワタナベ" });
            return data != null && data.length == 1 && "15".equals(data[0][0]);
        });

        // 結合4：CustomerSearchAction - Customer（3項目）
        executeTest("結合4-1 Customerクラスを解決できる",
                () -> Class.forName("model.Customer") != null);

        executeTest("結合4-2 ArrayList<Customer>を宣言・利用できる", () -> {
            ArrayList<Customer> list = dao.searchCustomerByTel("09012345678");
            return list != null && list instanceof ArrayList<?>;
        });

        executeTest("結合4-3 DAOから返却された顧客情報リストを受け取れる", () -> {
            String[][] data = action.execute(new String[] { "09012345678", "" });
            return data != null && data.length == 1
                    && "青木まゆみ".equals(data[0][1]);
        });

        // 結合5：CustomerSearchAction - OrderControlUtility（2項目）
        executeTest("結合5-1 OrderControlUtilityクラスを解決できる",
                () -> Class.forName("model.OrderControlUtility") != null);

        executeTest("結合5-2 execute内でcustomerToArrayを利用した結果を返却できる", () -> {
            ArrayList<Customer> list = dao.searchCustomerByTel("09012345678");
            String[][] expected = OrderControlUtility.customerToArray(list);
            String[][] actual = action.execute(new String[] { "09012345678", "" });
            return Arrays.deepEquals(expected, actual);
        });

        System.out.println("================================");
        System.out.println("合計：" + (okCount + ngCount) + "件");
        System.out.println("OK：" + okCount + "件");
        System.out.println("NG：" + ngCount + "件");
    }

    private static ArrayList<Customer> createOneCustomer() {
        ArrayList<Customer> list = new ArrayList<Customer>();
        list.add(new Customer(
                1,
                "青木まゆみ",
                "アオキマユミ",
                "09012345678",
                "東京都千代田区神田小川町1-1-1"));
        return list;
    }

    private static void executeTest(String testName, TestProcess process) {
        System.out.println("================================");
        System.out.println("--- " + testName + " ---");

        try {
            if (process.execute()) {
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
    private interface TestProcess {
        boolean execute() throws Exception;
    }
}
