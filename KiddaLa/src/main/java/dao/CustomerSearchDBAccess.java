package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

/**
 * 顧客情報のデータベースアクセスを管理するDAOクラス
 */
public class CustomerSearchDBAccess {

    // DB接続情報の定数定義
    private static final String URL = "jdbc:mysql://localhost:65534/KIDDA_LA";
    private static final String USER = "user1";
    private static final String PASS = "pass1";

    /**
     * KIDDA_LAデータベースとの接続を確立する。
     */
    private Connection createConnection() throws Exception {
        // JDBCドライバのロード
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 接続の確立
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * KIDDA_LAデータベースとの接続を切断する。
     */
    private void closeConnection(Connection con) throws Exception {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    /**
     * 引数telを条件にしたSELECT文を実行し、完全一致する顧客情報を取得する。
     */
    public ArrayList<Customer> searchCustomerByTel(String tel) throws Exception {
        ArrayList<Customer> list = new ArrayList<Customer>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = createConnection();
            String sql = "SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM CUSTOMER WHERE TEL = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tel);
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("CUSTID"),
                    rs.getString("CUSTNAME"),
                    rs.getString("KANA"),
                    tel,
                    rs.getString("ADDRESS")
                );
                list.add(customer);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (ps != null) try { ps.close(); } catch (SQLException e) {}
            closeConnection(con);
        }

        return list;
    }

    /**
     * 引数kanaを条件にしたSELECT文を実行し、部分一致する顧客情報を取得する。
     */
    public ArrayList<Customer> searchCustomerByKana(String kana) throws Exception {
        ArrayList<Customer> list = new ArrayList<Customer>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = createConnection();
            String sql = "SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS FROM CUSTOMER WHERE KANA LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + kana + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("CUSTID"),
                    rs.getString("CUSTNAME"),
                    rs.getString("KANA"),
                    rs.getString("TEL"),
                    rs.getString("ADDRESS")
                );
                list.add(customer);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (ps != null) try { ps.close(); } catch (SQLException e) {}
            closeConnection(con);
        }

        return list;
    }

    /**
     * 引数tel（完全一致）かつ引数kana（部分一致）を条件に顧客情報を取得する。
     */
    public ArrayList<Customer> searchCustomer(String tel, String kana) throws Exception {
        ArrayList<Customer> list = new ArrayList<Customer>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = createConnection();
            String sql = "SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM CUSTOMER WHERE TEL = ? AND KANA LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tel);
            ps.setString(2, "%" + kana + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("CUSTID"),
                    rs.getString("CUSTNAME"),
                    rs.getString("KANA"),
                    tel,
                    rs.getString("ADDRESS")
                );
                list.add(customer);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (ps != null) try { ps.close(); } catch (SQLException e) {}
            closeConnection(con);
        }

        return list;
    }
}
