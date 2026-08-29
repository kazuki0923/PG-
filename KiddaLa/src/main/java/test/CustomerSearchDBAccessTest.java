package test;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;

public class CustomerSearchDBAccessTest {

    public static void main(String[] args) {

        try {

            CustomerSearchDBAccess dao = new CustomerSearchDBAccess();

            // 1. searchCustomerByTel ("09012345678") -> 1件
            System.out.println("--- 1. TEL検索 ---");
            ArrayList<Customer> list1 = dao.searchCustomerByTel("09012345678");
            printList(list1);

            // 2. searchCustomerByKana ("イトウ") -> 2件以上
            System.out.println("--- 2. カナ検索 ---");
            ArrayList<Customer> list2 = dao.searchCustomerByKana("イトウ");
            printList(list2);

            // 3. searchCustomer ("0314142135", "ワタナベ") -> 1件
            System.out.println("--- 3. TEL+カナ検索 ---");
            ArrayList<Customer> list3 = dao.searchCustomer("0314142135", "ワタナベ");
            printList(list3);

            // 4. searchCustomerByTel ("00000000000") -> 0件
            System.out.println("--- 4. 該当なし検索 ---");
            ArrayList<Customer> list4 = dao.searchCustomerByTel("00000000000");
            printList(list4);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // コンソール出力用メソッド
    private static void printList(ArrayList<Customer> list) {
        System.out.println("件数：" + list.size());
        for (Customer c : list) {
            System.out.println("ID：" + c.getCustId());
            System.out.println("氏名：" + c.getCustName());
            System.out.println("カナ：" + c.getKana());
            System.out.println("TEL：" + c.getTel());
            System.out.println("住所：" + c.getAddress());
        }
        System.out.println();
    }

}