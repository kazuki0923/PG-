<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>《メインメニュー》KIDDA-LA業務システム</title>
<style>
body { margin:0; font-family:-apple-system,BlinkMacSystemFont,"Segoe UI",sans-serif; background:#f5f5f5; color:#222; }
.screen { width:900px; max-width:92vw; min-height:560px; margin:32px auto; background:#fff; border:1px solid #ddd; box-shadow:0 4px 18px rgba(0,0,0,.08); position:relative; overflow:hidden; }
.hero { min-height:520px; display:flex; flex-direction:column; align-items:center; justify-content:center; background:linear-gradient(145deg,#fff 0%,#fff8ef 45%,#f2f7f1 100%); }
h1 { margin:0 0 8px; font-size:30px; letter-spacing:.04em; }
h2 { margin:0 0 46px; font-size:18px; font-weight:500; }
.logo { font-size:74px; font-weight:900; letter-spacing:.06em; opacity:.12; position:absolute; }
.menu { position:relative; z-index:1; display:flex; flex-direction:column; gap:14px; width:320px; }
button { border:0; border-radius:24px; padding:13px 24px; font-size:17px; cursor:pointer; }
.primary { background:#13a56f; color:white; }
.secondary { background:#666; color:white; }
</style>
</head>
<body>
<div class="screen">
  <div class="hero">
    <div class="logo">KIDDA-LA</div>
    <h1>KIDDA-LA 業務システム</h1>
    <h2>《メインメニュー》</h2>
    <div class="menu">
      <form method="post" action="KiddaLaController">
        <input type="hidden" name="command" value="CustomerSearchDisplay">
        <button class="primary" type="submit" style="width:100%;">01 注文管理</button>
      </form>
      <button class="secondary" type="button" onclick="window.open('','_self'); window.close();">閉じる</button>
    </div>
  </div>
</div>
</body>
</html>
