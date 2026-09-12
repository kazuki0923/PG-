<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>KIDDA-LA 業務システム起動</title>
<style>
body { margin:0; font-family:-apple-system,BlinkMacSystemFont,"Segoe UI",sans-serif; background:#f5f5f5; color:#222; }
.screen { width:760px; max-width:92vw; min-height:420px; margin:60px auto; background:#fff; border:1px solid #ddd; box-shadow:0 4px 18px rgba(0,0,0,.08); display:flex; align-items:center; justify-content:center; }
.box { text-align:center; }
h1 { margin:0 0 18px; font-size:30px; }
p { color:#666; margin-bottom:28px; }
button { border:0; border-radius:24px; padding:14px 34px; font-size:17px; cursor:pointer; background:#13a56f; color:#fff; }
</style>
<script>
function launchKiddaLa() {
  var app = window.open(
    'KiddaLaController',
    'KiddaLaApp',
    'width=1100,height=760,resizable=yes,scrollbars=yes'
  );
  if (app) {
    app.focus();
  } else {
    alert('ポップアップがブロックされています。localhost のポップアップを許可してください。');
  }
}
</script>
</head>
<body>
<div class="screen">
  <div class="box">
    <h1>KIDDA-LA 業務システム</h1>
    <p>業務システムを起動します。</p>
    <button type="button" onclick="launchKiddaLa();">起動</button>
  </div>
</div>
</body>
</html>
