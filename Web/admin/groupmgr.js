phprpc_client.create('rpc_client');
rpc_client.use_service("svr.jsp");

function addNewuser(){
  $("#div_newuser").fadeIn("fast", function(){ 
  	$("#u_id").val(-1);
    $("#full_name").val("");
    document.getElementById("user_name").disabled = false;
    $("#user_name").attr({disable:"false"});
    $("#user_name").val("");
    $("#no_type").val("");
    $("#no_value").val("");
  });
}
function closediv(str){
  $(str).fadeOut("fast", function(){});
}
function delUser(id)
{
  alert("Do you want to remove app "+ id + "?");
}
function editUser(id)
{
  $("#user_group option").each(function(){
    if(this.value==$("#g_"+id).html()){
      this.selected = true;
    }
  });
  $("#div_newuser").fadeIn("fast", function(){
  	$("#u_id").val(id);
    $("#user_name").val($("#u_"+id).html());
    $("#user_name").attr({disabled:"true"});
    $("#full_name").val($("#f_"+id).html());
    $("#no_type").val($("#t_"+id).html());
    $("#no_value").val($("#v_"+id).html());
  });
}

$(document).ready(function(){
  $("#submit").click(function(){
  	if($("#pwd1").val()!=$("#pwd2").val()&&
  		($("#pwd1").val()!=null&&$("#pwd2").val()!=null)){
  		alert("two password must be coincided!");
  		return;
  	}
  	if($("#user_name").val()==null){
  		alert("Login name shouldn't be null.");
  		return;
  	}
  	if($("#user_name").val()!=null&&$("#pwd1").val()==null){
  		alert("You must input password.");
  	}
  	var uid      = Number($("#u_id").val());
  	var pwd      = $("#pwd1").val();
  	var name     = $("#user_name").val();
  	var fullname = $("#full_name").val();
  	var gid      = Number($("#user_group").val());
  	var no_type  = Number($("#no_type").val());
  	var no_value = $("#no_value").val();

  	if (rpc_client.ready) {    
			rpc_client.editUsr(uid, name, pwd, fullname, gid, no_type, no_value, function(result){
      if(result == 200){
      	alert("You have modified user's information.");
        closediv('#div_newuser');
      }else{
        alert(result);
      }
    });
		}else{
			
		}
  });
});
function checkform()
{
  if($("#pwd1").val()!=$("#pwd2").val()&&($("#pwd1").val()!=null&&$("#pwd2").val()!=null)){
    alert("two password must be coincided!");
    return false;
  }else{
    return true;
  }
}