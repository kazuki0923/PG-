<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String telNo = request.getAttribute("telNo") == null
        ? ""
        : (String) request.getAttribute("telNo");
String customerName = request.getAttribute("customerName") == null
        ? ""
        : (String) request.getAttribute("customerName");
String errorCode = (String) request.getAttribute("errorCode");
String[][] customerData = (String[][]) session.getAttribute("customerData");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>《顧客情報検索》KIDDA-LA業務システム</title>
<style>
body { margin:0; font-family:-apple-system,BlinkMacSystemFont,"Segoe UI",sans-serif; background:#f5f5f5; color:#222; }
.screen { width:980px; max-width:94vw; min-height:600px; margin:26px auto; background:#fff; border:1px solid #ddd; box-shadow:0 4px 18px rgba(0,0,0,.08); }
.header { text-align:center; padding:24px 16px 12px; background:linear-gradient(90deg,#f6fff9,#fff8ef); }
.header h1 { margin:0; font-size:28px; }
.header h2 { margin:6px 0 0; font-size:17px; font-weight:500; }
.content { padding:26px 34px 34px; display:grid; grid-template-columns:330px 1fr; gap:28px; }
.panel { background:#fafafa; border:1px solid #ddd; border-radius:8px; padding:18px; }
.field { margin-bottom:16px; }
.field label { display:block; background:#1cc6df; color:#fff; padding:7px 10px; border-radius:5px 5px 0 0; font-size:14px; }
.field input { width:100%; box-sizing:border-box; padding:10px; border:1px solid #ccc; border-top:0; border-radius:0 0 5px 5px; font-size:15px; }
.actions { display:grid; grid-template-columns:1fr 1fr; gap:10px; margin-top:12px; }
.actions .search { grid-column:1 / 3; background:#12b7c9; color:#fff; }
button { border:0; border-radius:18px; padding:10px 16px; cursor:pointer; font-size:15px; }
.gray { background:#666; color:#fff; }
.close { background:#8b8b8b; color:#fff; }
.result { min-height:210px; }
.result h3 { margin:0; padding:8px 12px; background:#ffc107; font-size:15px; }
table { width:100%; border-collapse:collapse; font-size:14px; }
th, td { border:1px solid #ddd; padding:8px; text-align:left; }
th { background:#fff7d7; }
.rowButton { background:none; border:0; color:#1565c0; padding:0; cursor:pointer; text-decoration:underline; }
.empty { color:#888; padding:18px 8px; }
</style>
<script>
function clearFormAndResult() {
  document.getElementById('telNo').value = '';
  document.getElementById('customerName').value = '';
  var result = document.getElementById('resultArea');
  if (result) result.innerHTML = '<div class="empty">検索結果はありません。</div>';
}
</script>
</head>
<body>
<% if (errorCode != null) { %>
<script>
window.addEventListener('load', function() {
  alert('エラーメッセージ（コード：<%= errorCode %>）');
});
</script>
<% } %>
<div class="screen">
  <div class="header">
    <h1>KIDDA-LA 業務システム</h1>
    <h2>《顧客情報検索》</h2>
  </div>
  <div class="content">
    <div class="panel">
      <form method="post" action="KiddaLaController">
        <input type="hidden" name="command" value="CustomerSearch">
        <div class="field">
          <label for="telNo">電話番号（ハイフンなし）</label>
          <input id="telNo" name="telNo" type="text" value="<%= telNo %>" placeholder="例：09012345678">
        </div>
        <div class="field">
          <label for="customerName">氏名カナ（全角カタカナ）</label>
          <input id="customerName" name="customerName" type="text" value="<%= customerName %>" placeholder="例：ヤマダタロウ">
        </div>
        <div class="actions">
          <button class="search" type="submit">検索</button>
          <button class="gray" type="button" onclick="clearFormAndResult();">入力消去</button>
          <button class="gray" type="button" onclick="location.href='KiddaLaController';">戻る</button>
          <button class="close" type="button" onclick="window.close();">閉じる</button>
        </div>
      </form>
    </div>

    <div class="panel result">
      <h3>検索結果</h3>
      <div id="resultArea">
      <% if (customerData != null && customerData.length > 0) { %>
        <table>
          <thead>
            <tr><th>ID</th><th>氏名</th><th>カナ</th><th>住所</th></tr>
          </thead>
          <tbody>
          <% for (int i = 0; i < customerData.length; i++) { %>
            <tr>
              <td>
                <form method="post" action="KiddaLaController" style="margin:0;">
                  <input type="hidden" name="command" value="CustomerSelect">
                  <input type="hidden" name="custId" value="<%= customerData[i][0] %>">
                  <button class="rowButton" type="submit"><%= customerData[i][0] %></button>
                </form>
              </td>
              <td><%= customerData[i][1] %></td>
              <td><%= customerData[i][2] %></td>
              <td><%= customerData[i][3] %></td>
            </tr>
          <% } %>
          </tbody>
        </table>
      <% } else { %>
        <div class="empty">検索条件を入力して「検索」をクリックしてください。</div>
      <% } %>
      </div>
    </div>
  </div>
</div>
</body>
</html>
