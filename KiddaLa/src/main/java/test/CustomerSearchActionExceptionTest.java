package test;

import action.CustomerSearchAction;

public class CustomerSearchActionExceptionTest {

    public static void main(String[] args) {

        CustomerSearchAction action = new CustomerSearchAction();

        System.out.println("=== Action DB接続エラーテスト ===");

        try {
            action.execute(new String[] { "09012345678", "" });
            System.out.println("結果：NG");
            System.out.println("Exceptionが発生しませんでした。");
        } catch (Exception e) {
            System.out.println("結果：OK");
            System.out.println("Exception伝播を確認");
            System.out.println("例外：" + e.getClass().getName());
        }
    }
}
