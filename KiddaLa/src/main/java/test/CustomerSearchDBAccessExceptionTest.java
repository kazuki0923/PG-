package test;

import dao.CustomerSearchDBAccess;

public class CustomerSearchDBAccessExceptionTest {

    public static void main(String[] args) {

        CustomerSearchDBAccess dao =
                new CustomerSearchDBAccess();

        System.out.println(
                "=== DB接続エラーテスト ===");

        try {

            dao.searchCustomerByTel(
                    "09012345678");

            System.out.println(
                    "結果：NG");

            System.out.println(
                    "Exceptionが発生しませんでした。");

        } catch (Exception e) {

            System.out.println(
                    "結果：OK");

            System.out.println(
                    "Exception発生を確認");

            System.out.println(
                    "例外："
                    + e.getClass().getName());
        }
    }
}