<html>
<head>
<title>Manager login</title>
<style>
  @import 'style.css';
</style>
<script src="jquery.js"></script>
<script src="phprpc_client.js"></script>
<script>
phprpc_client.create('rpc_client');
rpc_client.use_service("http://127.0.0.1:8080/uso/mgrauth.do");
$(document).ready(function(){
  $("#submit").click(function(){
    var usr = $("#mgrname").val();
    var pwd = $("#mgrpass").val();
    if (rpc_client.ready) {
      rpc_client.getMgr(usr, pwd, function(result){
        if(result == 1)
          window.location = "manager";
      });
    }else{
      //window.setTimeout('send(u,g);', 200);
      alert("fffff");
    }
  });
});
</script>
</head>
<body>
<h1 style="margin-top:80px;text-align:center">Manager Login</h1>
<%=session.getId()%>
<table align="center">
<tr><td class="td_title">Username: </td><td><input type="text" id="mgrname" size="30" /></td></tr>
<tr><td class="td_title">Password: </td><td><input type="password" id="mgrpass" size="30" /></td></tr>
<tr><td></td><td><input type="button" id="submit" value="submit" /></td></tr>
</table>
</body>
</html>