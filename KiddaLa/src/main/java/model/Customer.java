package model;

import java.io.Serializable;

/**
 * 顧客情報を保持するデータ転送オブジェクト（DTO）
 */
public class Customer implements Serializable {
    
    // 5つのprivate属性
    private int custId;        // 顧客ID
    private String custName;   // 顧客名
    private String kana;       // カナ
    private String tel;        // 電話番号
    private String address;    // 住所

    // 引数なしコンストラクタ
    public Customer() {
    }

    // 引数ありコンストラクタ
    public Customer(int custId, String custName, String kana, String tel, String address) {
        this.custId = custId;
        this.custName = custName;
        this.kana = kana;
        this.tel = tel;
        this.address = address;
    }

    // getter/setterメソッド（10個）
    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
