<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String custId = request.getAttribute("custId") == null ? "" : (String) request.getAttribute("custId");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文／配達確認／顧客情報変更 - KIDDA-LA業務システム</title>
<style>
body { margin:0; font-family:-apple-system,BlinkMacSystemFont,"Segoe UI",sans-serif; background:#f5f5f5; color:#222; }
.screen { width:900px; max-width:92vw; min-height:520px; margin:32px auto; background:#fff; border:1px solid #ddd; box-shadow:0 4px 18px rgba(0,0,0,.08); text-align:center; }
.header { padding:28px 16px 18px; background:linear-gradient(90deg,#f6fff9,#fff8ef); }
h1 { margin:0; font-size:27px; }
h2 { margin:7px 0 0; font-size:18px; font-weight:500; }
.content { padding:60px 30px; }
.info { font-size:18px; margin-bottom:30px; }
button { border:0; border-radius:20px; padding:11px 30px; background:#666; color:#fff; font-size:15px; cursor:pointer; }
</style>
</head>
<body>
<div class="screen">
  <div class="header">
    <h1>KIDDA-LA 業務システム</h1>
    <h2>《注文／配達確認／顧客情報変更》</h2>
  </div>
  <div class="content">
    <div class="info">選択顧客ID：<%= custId %></div>
    <button type="button" onclick="history.back();">戻る</button>
  </div>
</div>
</body>
</html>
