phprpc_client.create('rpc_client');
rpc_client.use_service("signin");
// remove space character in string object.
function Trim(str){return str.replace(/(^\s*)|(\s*$)/g, "");}
// get html element, this is a shortcut.
function el(e){return document.getElementById(e);}
// cheout if there is some forbidden character in string object. 
function checkout(str){str = Trim(str);var keyword = new Array('<','>','/','\\');for (j = 0; j < keyword.length; j++){if (str.indexOf(keyword[j]) != -1){alert("invaild chars.");return false;}}return true;}
// utility function to retrieve an expiration data in proper format;
function getExpDate(days, hours, minutes)
{
  var expDate = new Date();
  if(typeof(days) == "number" && typeof(hours) == "number" && typeof(hours) == "number")
  {
    expDate.setDate(expDate.getDate() + parseInt(days));
    expDate.setHours(expDate.getHours() + parseInt(hours));
    expDate.setMinutes(expDate.getMinutes() + parseInt(minutes));
    return expDate.toGMTString();
  }
}
//utility function called by getCookie()
function getCookieVal(offset)
{
  var endstr = document.cookie.indexOf(";", offset);
  if(endstr == -1)
  {
    endstr = document.cookie.length;
  }
  return unescape(document.cookie.substring(offset, endstr));
}
// primary function to retrieve cookie by name
function getCookie(name)
{
  var arg = name + "=";
  var alen = arg.length;
  var clen = document.cookie.length;
  var i = 0;
  while(i < clen)
  {
    var j = i + alen;
    if (document.cookie.substring(i, j) == arg)
    {
      return getCookieVal(j);
    }
    i = document.cookie.indexOf(" ", i) + 1;
    if(i == 0) break;
  }
  return;
}
// store cookie value with optional details as needed
function setCookie(name, value, now, hours)
{
  var expdate = new Date();
  expdate.setTime(now.getTime() + hours * 60 * 60 * 1000);
  document.cookie = name + "=" + escape(value) + 
    ((now) ? "; expires=" + expdate : "");
  /*
  document.cookie = name + "=" + escape(value) + 
    ((expires) ? "; expires=" + expires : "") + 
    ((path) ? "; path=" + path : "") + 
    ((domain) ? "; domain=" + domain : "") + 
    ((secure) ? "; secure" : "");
  */
}
// remove the cookie by setting ancient expiration date
function deleteCookie(name,path,domain)
{
  if(getCookie(name))
  {
    document.cookie = name + "=" + ((path) ? "; path=" + path : "") + ((domain) ? "; domain=" + domain : "") + "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}
var username = "";
var password = "";
var app_id = 0;
var remember = false;
var u;
var g;
function onEnter(ent){
  ent = ent?ent:window.event;
  if(ent.keyCode == 13)
    $("#submit").click();
}
// send guid to a special web service, the argue "url" indicates redirect url
// about guid, you know..
// this function called by doLogin
function send(url, guid){
  if(login.ready){
    login.doLogin(guid, function(result){
      if(result==200)
        window.location = url;
    });
  }else{
    window.setTimeout('send(u,g);', 200);
  }
  
}
// called by callback function of getAuthorization
// 
function doLogin(url, guid, ws)
{
  phprpc_client.create('login');
  login.use_service(ws);
  u=url;
  g=guid;
  send(u,g);
}
// this function handle onload event.
$(document).ready(function(){
  $("#goto").click(function(){
    if (rpc_client.ready) {
      rpc_client.getAuthorization(1, function(result){
        //for(key in result)
        //{
          //alert(key + " - " + result[key]);
        //}
        //alert(el("referer").value);
        if(result.uid != -1){
          doLogin(el("referer").value, result.ticket, result.ws);
        }
      });
    }
  });
  $("#goto").mouseover(function(){
    $("#redirect").html("Go to " + "<span class='color'>" + this.title + "</span>");
  }).mouseout(function(){$("#redirect").html("")});
  // when user decide login  :)
  $("#submit").click(function(){
    username = el("username").value;
    password = el("password").value;
    if(username==""||password=="")
      {alert("You must input username and password. ");return;}
    if(checkout(username)==false || checkout(password)==false)
      return;
    if($("#remember").attr("checked")==true)
      {remember = true;}
    if (rpc_client.ready) {
      rpc_client.getAuthorization(username, password, Number(app_id), remember, function(result){
        /*
        for(key in result)
        {
          alert(key + " : " + result[key]);
        }
        */
        if(result.uid != -1){
          //if(remember == true)
          //{
            //var now = new Date();
            //setCookie("USO_USR", username, now, 24*30);
            //setCookie("USO_PWD", result.pwd, now, 24*30);
          //}
          if(app_id == 0)
          {
            location.href = "admin";
          }else{
            doLogin(result.url, result.ticket, result.ws);
          }
        }else{
          alert(result.info);
          return;
        }
      });
    }
    else {
      alert("RPC is not ready.");
    }
  });
  //end submit action

  // auto focus cursor on current text element
  $("input[@type='text'], input[@type='password'], textarea").hover(function(){$(this).focus();$(this).css({"border":"1px solid red"})},function(){$(this).css({"border":"1px solid gray"})});
  // when user select other application
  $("input[@type='radio']").click(function(){app_id = $(this).val();});
  $("input[@type='checkbox']").click(function(){
    if($(this).attr("checked")==true)
    {remember=true;}else{remember=false;}
  });
});