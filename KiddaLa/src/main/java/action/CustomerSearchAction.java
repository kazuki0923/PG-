package action;

import java.util.ArrayList;

import model.Customer;
import model.OrderControlUtility;
import dao.CustomerSearchDBAccess;

/**
 * 顧客情報検索処理を管理するActionクラス
 */
public class CustomerSearchAction {

    /**
     * 顧客情報を検索し、検索結果表示用データを返却する。
     */
    public String[][] execute(String[] data) throws Exception {
        // 電話番号とカナの半角スペース・全角スペースを取り除く
        data[0] = data[0].replace(" ", "").replace("　", "");
        data[1] = data[1].replace(" ", "").replace("　", "");

        CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
        ArrayList<Customer> list = null;
        String[][] tableData = null;

        // 電話番号のみ指定
        if (!data[0].equals("") && data[1].equals("")) {
            list = dao.searchCustomerByTel(data[0]);
        }
        // カナのみ指定
        else if (data[0].equals("") && !data[1].equals("")) {
            list = dao.searchCustomerByKana(data[1]);
        }
        // 電話番号とカナの両方を指定
        else if (!data[0].equals("") && !data[1].equals("")) {
            list = dao.searchCustomer(data[0], data[1]);
        }

        // 顧客情報リストが取得できた場合、検索結果表示用データに変換する
        if (list != null && list.size() != 0) {
            tableData = OrderControlUtility.customerToArray(list);
        }

        return tableData;
    }
}
