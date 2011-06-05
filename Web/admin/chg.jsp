<%@include file="../common.jsp"%>
<html><head><title></title><style>
@import '../style.css';
</style> 
<script src="../phprpc_client.js"></script>
<script>
function el(e){return document.getElementById(e);}
phprpc_client.create('rpc_client');
rpc_client.use_service("svr.jsp");
function chg()
{
  if (rpc_client.ready) {
    if(el("op").value=="" || el("np").value=="" || el("cp").value==""){
      alert("Please input your old password and new password. ");
      return;
    }
    if(el('np').value!=el('cp').value)
    {
      alert("You must input the same password ");
      return;
    }
    rpc_client.chgPwd(el('op').value, el('cp').value, function(result){
      if(result == 200){
        parent.window.location = "http://127.0.0.1:8080/uso";
      }else{
        alert(result);
      }
    });
  }
}
/*
if (rpc_client.ready) {
  if(el("op").value=="" || el("np").value=="" || el("cp").value==""){
    alert("Please input your old password and new password. ");
    return;
  }

  rpc_client.chgPwd(
}
*/
</script>
</head>
<body>
<h1 style="margin-top:80px">Change Password</h1>
<table width="40%">
<tr><td colspan="2"></td></tr>
<tr><td>Old password</td><td><input type="password" id="op" size="30" /></td></tr>
<tr><td>New Password</td><td><input type="password" id="np" size="30" /></td></tr>
<tr><td>Confirm Password</td><td><input type="password" id="cp" size="30" /></td></tr>
<tr><td></td><td><input type="button" id="submit" value="change" onclick="chg()" /></td></tr>
</table>
</body>
</html>