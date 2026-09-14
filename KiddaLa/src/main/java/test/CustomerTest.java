package test;

import java.util.Objects;

import model.Customer;

public class CustomerTest {

    private static int okCount = 0;
    private static int ngCount = 0;

    public static void main(String[] args) {
        testConstructor();
        testCustId();
        testCustName();
        testKana();
        testTel();
        testAddress();

        System.out.println("================================");
        System.out.println("合計：" + (okCount + ngCount) + "件");
        System.out.println("OK：" + okCount + "件");
        System.out.println("NG：" + ngCount + "件");
    }

    private static void testConstructor() {
        Customer c1 = new Customer(
                1,
                "青木まゆみ",
                "アオキマユミ",
                "09012345678",
                "東京都千代田区神田小川町1-1-1");

        check("1. コンストラクタ（通常値）",
                c1.getCustId() == 1
                && Objects.equals(c1.getCustName(), "青木まゆみ")
                && Objects.equals(c1.getKana(), "アオキマユミ")
                && Objects.equals(c1.getTel(), "09012345678")
                && Objects.equals(c1.getAddress(), "東京都千代田区神田小川町1-1-1"));

        Customer c2 = new Customer(0, null, null, null, null);

        check("2. コンストラクタ（0・null）",
                c2.getCustId() == 0
                && c2.getCustName() == null
                && c2.getKana() == null
                && c2.getTel() == null
                && c2.getAddress() == null);
    }

    private static void testCustId() {
        Customer c = new Customer();
        c.setCustId(2);
        check("3. setCustId(2)", c.getCustId() == 2);

        c.setCustId(0);
        check("4. setCustId(0)", c.getCustId() == 0);

        Customer c1 = new Customer(1, null, null, null, null);
        check("5. getCustId() 通常値", c1.getCustId() == 1);

        Customer c2 = new Customer(0, null, null, null, null);
        check("6. getCustId() 0", c2.getCustId() == 0);
    }

    private static void testCustName() {
        Customer c = new Customer();
        c.setCustName("青木まゆみ");
        check("7. setCustName(通常値)", Objects.equals(c.getCustName(), "青木まゆみ"));

        c.setCustName(null);
        check("8. setCustName(null)", c.getCustName() == null);

        Customer c1 = new Customer(0, "青木まゆみ", null, null, null);
        check("9. getCustName() 通常値", Objects.equals(c1.getCustName(), "青木まゆみ"));

        Customer c2 = new Customer(0, null, null, null, null);
        check("10. getCustName() null", c2.getCustName() == null);
    }

    private static void testKana() {
        Customer c = new Customer();
        c.setKana("アオキマユミ");
        check("11. setKana(通常値)", Objects.equals(c.getKana(), "アオキマユミ"));

        c.setKana(null);
        check("12. setKana(null)", c.getKana() == null);

        Customer c1 = new Customer(0, null, "アオキマユミ", null, null);
        check("13. getKana() 通常値", Objects.equals(c1.getKana(), "アオキマユミ"));

        Customer c2 = new Customer(0, null, null, null, null);
        check("14. getKana() null", c2.getKana() == null);
    }

    private static void testTel() {
        Customer c = new Customer();
        c.setTel("09012345678");
        check("15. setTel(通常値)", Objects.equals(c.getTel(), "09012345678"));

        c.setTel(null);
        check("16. setTel(null)", c.getTel() == null);

        Customer c1 = new Customer(0, null, null, "09012345678", null);
        check("17. getTel() 通常値", Objects.equals(c1.getTel(), "09012345678"));

        Customer c2 = new Customer(0, null, null, null, null);
        check("18. getTel() null", c2.getTel() == null);
    }

    private static void testAddress() {
        Customer c = new Customer();
        c.setAddress("東京都千代田区神田小川町1-1-1");
        check("19. setAddress(通常値)", Objects.equals(c.getAddress(), "東京都千代田区神田小川町1-1-1"));

        c.setAddress(null);
        check("20. setAddress(null)", c.getAddress() == null);

        Customer c1 = new Customer(0, null, null, null, "東京都千代田区神田小川町1-1-1");
        check("21. getAddress() 通常値", Objects.equals(c1.getAddress(), "東京都千代田区神田小川町1-1-1"));

        Customer c2 = new Customer(0, null, null, null, null);
        check("22. getAddress() null", c2.getAddress() == null);
    }

    private static void check(String testName, boolean condition) {
        System.out.println("================================");
        System.out.println("--- " + testName + " ---");

        if (condition) {
            System.out.println("結果：OK");
            okCount++;
        } else {
            System.out.println("結果：NG");
            ngCount++;
        }
    }
}
