package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {
    public static void main(String[] args) {
        // 本番用DB「KIDDA_LA」への接続情報
        String url = "jdbc:mysql://localhost:3306/KIDDA_LA";
        String user = "root";
        String password = "kazuki48"; // 一輝さんのパスワード

        try {
            // 1. データベースに接続
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();

            // 2. CUSTOMERテーブルからデータを取得
            ResultSet rs = stm.executeQuery("SELECT * FROM CUSTOMER WHERE CUSTID = 1");

            // 3. 画面に結果を表示
            while (rs.next()) {
                System.out.println("★本番DB接続大成功！★ 顧客名: " + rs.getString("CUSTNAME"));
            }

            // 4. お片付け
            rs.close();
            stm.close();
            con.close();

        } catch (Exception e) {
            System.out.println("⚠️接続に失敗しました。詳細を確認してください。");
            e.printStackTrace();
        }
    }
}