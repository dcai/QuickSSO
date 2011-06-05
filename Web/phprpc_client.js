function utf16to8(str){var out,i,j,len,c,c2;out=[];len=str.length;for(i=0,j=0;i<len;i++,j++){c=str.charCodeAt(i);if(c<=0x7f){out[j]=str.charAt(i);}
else if(c<=0x7ff){out[j]=String.fromCharCode(0xc0|(c>>>6),0x80|(c&0x3f));}
else if(c<0xd800||c>0xdfff){out[j]=String.fromCharCode(0xe0|(c>>>12),0x80|((c>>>6)&0x3f),0x80|(c&0x3f));}
else{if(++i<len){c2=str.charCodeAt(i);if(c<=0xdbff&&0xdc00<=c2&&c2<=0xdfff){c=((c&0x03ff)<<10|(c2&0x03ff))+0x010000;if(0x010000<=c&&c<=0x10ffff){out[j]=String.fromCharCode(0xf0|((c>>>18)&0x3f),0x80|((c>>>12)&0x3f),0x80|((c>>>6)&0x3f),0x80|(c&0x3f));}
else{out[j]='?';}}
else{i--;out[j]='?';}}
else{i--;out[j]='?';}}}
return out.join('');}
function utf8to16(str){var out,i,j,len,c,c2,c3,c4,s;out=[];len=str.length;i=j=0;while(i<len){c=str.charCodeAt(i++);switch(c>>4){case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:out[j++]=str.charAt(i-1);break;case 12:case 13:c2=str.charCodeAt(i++);out[j++]=String.fromCharCode(((c&0x1f)<<6)|(c2&0x3f));break;case 14:c2=str.charCodeAt(i++);c3=str.charCodeAt(i++);out[j++]=String.fromCharCode(((c&0x0f)<<12)|((c2&0x3f)<<6)|(c3&0x3f));break;case 15:switch(c&0xf){case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:c2=str.charCodeAt(i++);c3=str.charCodeAt(i++);c4=str.charCodeAt(i++);s=((c&0x07)<<18)|((c2&0x3f)<<12)|((c3&0x3f)<<6)|(c4&0x3f)-0x10000;if(0<=s&&s<=0xfffff){out[j]=String.fromCharCode(((s>>>10)&0x03ff)|0xd800,(s&0x03ff)|0xdc00);}
else{out[j]='?';}
break;case 8:case 9:case 10:case 11:i+=4;out[j]='?';break;case 12:case 13:i+=5;out[j]='?';break;}}
j++;}
return out.join('');}
var base64EncodeChars=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9","+","/"];var base64DecodeChars=[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,62,-1,-1,-1,63,52,53,54,55,56,57,58,59,60,61,-1,-1,-1,-1,-1,-1,-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,-1,-1,-1,-1,-1,-1,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,-1,-1,-1,-1,-1];function base64encode(str){var out,i,j,len;var c1,c2,c3;len=str.length;i=j=0;out=[];while(i<len){c1=str.charCodeAt(i++)&0xff;if(i==len)
{out[j++]=base64EncodeChars[c1>>2];out[j++]=base64EncodeChars[(c1&0x3)<<4];out[j++]="==";break;}
c2=str.charCodeAt(i++)&0xff;if(i==len)
{out[j++]=base64EncodeChars[c1>>2];out[j++]=base64EncodeChars[((c1&0x03)<<4)|((c2&0xf0)>>4)];out[j++]=base64EncodeChars[(c2&0x0f)<<2];out[j++]="=";break;}
c3=str.charCodeAt(i++)&0xff;out[j++]=base64EncodeChars[c1>>2];out[j++]=base64EncodeChars[((c1&0x03)<<4)|((c2&0xf0)>>4)];out[j++]=base64EncodeChars[((c2&0x0f)<<2)|((c3&0xc0)>>6)];out[j++]=base64EncodeChars[c3&0x3f];}
return out.join('');}
function base64decode(str){var c1,c2,c3,c4;var i,j,len,out;len=str.length;i=j=0;out=[];while(i<len){do{c1=base64DecodeChars[str.charCodeAt(i++)&0xff];}while(i<len&&c1==-1);if(c1==-1)break;do{c2=base64DecodeChars[str.charCodeAt(i++)&0xff];}while(i<len&&c2==-1);if(c2==-1)break;out[j++]=String.fromCharCode((c1<<2)|((c2&0x30)>>4));do{c3=str.charCodeAt(i++)&0xff;if(c3==61)return out.join('');c3=base64DecodeChars[c3];}while(i<len&&c3==-1);if(c3==-1)break;out[j++]=String.fromCharCode(((c2&0x0f)<<4)|((c3&0x3c)>>2));do{c4=str.charCodeAt(i++)&0xff;if(c4==61)return out.join('');c4=base64DecodeChars[c4];}while(i<len&&c4==-1);if(c4==-1)break;out[j++]=String.fromCharCode(((c3&0x03)<<6)|c4);}
return out.join('');}
function serialize(o){var p=0,sb=[],ht=[],hv=1;function classname(o){if(typeof(o)=="undefined"||typeof(o.constructor)=="undefined")return'';var c=o.constructor.toString();c=utf16to8(c.substr(0,c.indexOf('(')).replace(/(^\s*function\s*)|(\s*$)/ig,''));return((c=='')?'Object':c);}
function is_int(n){var s=n.toString(),l=s.length;if(l>11)return false;for(var i=(s.charAt(0)=='-')?1:0;i<l;i++){switch(s.charAt(i)){case'0':case'1':case'2':case'3':case'4':case'5':case'6':case'7':case'8':case'9':break;default:return false;}}
return!(n<-2147483648||n>2147483647);}
function in_ht(o){for(k in ht)if(ht[k]===o)return k;return false;}
function ser_null(){sb[p++]='N;';}
function ser_boolean(b){sb[p++]=(b?'b:1;':'b:0;');}
function ser_integer(i){sb[p++]='i:'+i+';';}
function ser_double(d){if(isNaN(d))d='NAN';else if(d==Number.POSITIVE_INFINITY)d='INF';else if(d==Number.NEGATIVE_INFINITY)d='-INF';sb[p++]='d:'+d+';';}
function ser_string(s){var utf8=utf16to8(s);sb[p++]='s:'+utf8.length+':"';sb[p++]=utf8;sb[p++]='";';}
function ser_array(a){sb[p++]='a:';var lp=p;sb[p++]=0;sb[p++]=':{';for(var k in a){if(typeof(a[k])!='function'){is_int(k)?ser_integer(k):ser_string(k);__serialize(a[k]);sb[lp]++;}}
sb[p++]='}';}
function ser_object(o){var cn=classname(o);if(cn=='')ser_null();else if(typeof(o.serialize)!='function'){sb[p++]='O:'+cn.length+':"';sb[p++]=cn;sb[p++]='":';var lp=p;sb[p++]=0;sb[p++]=':{';if(typeof(o.__sleep)=='function'){var a=o.__sleep();for(var kk in a){ser_string(a[kk]);__serialize(o[a[kk]]);sb[lp]++;}}
else{for(var k in o){if(typeof(o[k])!='function'){ser_string(k);__serialize(o[k]);sb[lp]++;}}}
sb[p++]='}';}
else{var cs=o.serialize();sb[p++]='C:'+cn.length+':"';sb[p++]=cn;sb[p++]='":'+cs.length+':{';sb[p++]=cs;sb[p++]="}";}}
function ser_pointref(R){sb[p++]="R:"+R+";";}
function ser_ref(r){sb[p++]="r:"+r+";";}
function __serialize(o){if(o==null||o.constructor==Function){hv++;ser_null();}
else switch(o.constructor){case Boolean:{hv++;ser_boolean(o);break;}
case Number:{hv++;is_int(o)?ser_integer(o):ser_double(o);break;}
case String:{hv++;ser_string(o);break;}/*@cc_on@*//*@if(@_jscript)
case VBArray:{o=o.toArray();}@end@*/case Array:{var r=in_ht(o);if(r){ser_pointref(r);}
else{ht[hv++]=o;ser_array(o);}
break;}
default:{var r=in_ht(o);if(r){hv++;ser_ref(r);}
else{ht[hv++]=o;ser_object(o);}
break;}}}
__serialize(o);return sb.join('');}
function unserialize(ss){var p=0,ht=[],hv=1;r=null;function unser_null(){p++;return null;}
function unser_boolean(){p++;var b=(ss.charAt(p++)=='1');p++;return b;}
function unser_integer(){p++;var i=parseInt(ss.substring(p,p=ss.indexOf(';',p)));p++;return i;}
function unser_double(){p++;var d=ss.substring(p,p=ss.indexOf(';',p));switch(d){case'NAN':d=NaN;break;case'INF':d=Number.POSITIVE_INFINITY;break;case'-INF':d=Number.NEGATIVE_INFINITY;break;default:d=parseFloat(d);}
p++;return d;}
function unser_string(){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var s=utf8to16(ss.substring(p,p+=l));p+=2;return s;}
function unser_array(){p++;var n=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var a=[];ht[hv++]=a;for(var i=0;i<n;i++){var k;switch(ss.charAt(p++)){case'i':k=unser_integer();break;case's':k=unser_string();break;case'U':k=unser_unicode_string();break;default:return false;}
a[k]=__unserialize();}
p++;return a;}
function unser_object(){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var cn=utf8to16(ss.substring(p,p+=l));p+=2;var n=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;if(eval(['typeof(',cn,') == "undefined"'].join(''))){eval(['function ',cn,'(){}'].join(''));}
var o=eval(['new ',cn,'()'].join(''));ht[hv++]=o;for(var i=0;i<n;i++){var k;switch(ss.charAt(p++)){case's':k=unser_string();break;case'U':k=unser_unicode_string();break;default:return false;}
if(k.charAt(0)=='\0'){k=k.substring(k.indexOf('\0',1)+1,k.length);}
o[k]=__unserialize();}
p++;if(typeof(o.__wakeup)=='function')o.__wakeup();return o;}
function unser_custom_object(){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var cn=utf8to16(ss.substring(p,p+=l));p+=2;var n=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;if(eval(['typeof(',cn,') == "undefined"'].join(''))){eval(['function ',cn,'(){}'].join(''));}
var o=eval(['new ',cn,'()'].join(''));ht[hv++]=o;if(typeof(o.unserialize)!='function')p+=n;else o.unserialize(ss.substring(p,p+=n));p++;return o;}
function unser_unicode_string(){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var sb=[];for(var i=0;i<l;i++){if((sb[i]=ss.charAt(p++))=='\\'){sb[i]=String.fromCharCode(parseInt(ss.substring(p,p+=4),16));}}
p+=2;return sb.join('');}
function unser_ref(){p++;var r=parseInt(ss.substring(p,p=ss.indexOf(';',p)));p++;return ht[r];}
function __unserialize(){switch(ss.charAt(p++)){case'N':return ht[hv++]=unser_null();case'b':return ht[hv++]=unser_boolean();case'i':return ht[hv++]=unser_integer();case'd':return ht[hv++]=unser_double();case's':return ht[hv++]=unser_string();case'U':return ht[hv++]=unser_unicode_string();case'r':return ht[hv++]=unser_ref();case'a':return unser_array();case'O':return unser_object();case'C':return unser_custom_object();case'R':return unser_ref();default:return false;}}
return __unserialize();}
function mul(a,b){var n=a.length,m=b.length,nm=n+m,i,j,c=Array(n);for(i=0;i<nm;i++)c[i]=0;for(i=0;i<n;i++){for(j=0;j<m;j++){c[i+j]+=a[i]*b[j];c[i+j+1]+=(c[i+j]>>16)&0xffff;c[i+j]&=0xffff;}}
return c;}
function div(a,b,is_mod){var n=a.length,m=b.length,i,j,d,tmp,qq,rr,c=Array();d=Math.floor(0x10000/(b[m-1]+1));a=mul(a,[d]);b=mul(b,[d]);for(j=n-m;j>=0;j--){tmp=a[j+m]*0x10000+a[j+m-1];rr=tmp%b[m-1];qq=Math.round((tmp-rr)/b[m-1]);if(qq==0x10000||(m>1&&qq*b[m-2]>0x10000*rr+a[j+m-2])){qq--;rr+=b[m-1];if(rr<0x10000&&qq*b[m-2]>0x10000*rr+a[j+m-2])qq--;}
for(i=0;i<m;i++){tmp=i+j;a[tmp]-=b[i]*qq;a[tmp+1]+=Math.floor(a[tmp]/0x10000);a[tmp]&=0xffff;}
c[j]=qq;if(a[tmp+1]<0){c[j]--;for(i=0;i<m;i++){tmp=i+j;a[tmp]+=b[i];if(a[tmp]>0xffff){a[tmp+1]++;a[tmp]&=0xffff;}}}}
if(!is_mod)return c;b=Array();for(i=0;i<m;i++)b[i]=a[i];return div(b,[d]);}
function pow_mod(a,b,c){var n=b.length,p=[1],i,j,tmp;for(i=0;i<n-1;i++){tmp=b[i];for(j=0;j<0x10;j++){if(tmp&1)p=div(mul(p,a),c,1);tmp>>=1;a=div(mul(a,a),c,1);}}
tmp=b[i];while(tmp){if(tmp&1)p=div(mul(p,a),c,1);tmp>>=1;a=div(mul(a,a),c,1);}
return p;}
function zerofill(str,num){var n=num-str.toString().length,i,s='';for(i=0;i<n;i++)s+='0';return s+str;}
function dec2num(str){var n=str.length,a=[0],i,j,m;n+=4-(n%4);str=zerofill(str,n);n>>=2;for(i=0;i<n;i++){a=mul(a,[10000]);a[0]+=parseInt(str.substring(4*i,4*(i+1)),10);m=a.length;j=a[m]=0;while(j<m&&a[j]>0xffff){a[j++]&=0xffff;a[j]++;}
while(a.length>1&&!a[a.length-1])a.length--;}
return a;}
function num2dec(a){var n=2*a.length,b=Array(),i;for(i=0;i<n;i++){b[i]=zerofill(div(a,[10000],1)[0],4);a=div(a,[10000]);}
while(b.length>1&&!parseInt(b[b.length-1],10))b.length--;n=b.length-1;b[n]=parseInt(b[n],10);b=b.reverse().join('');return b;}
function is_decimal(str){var n=str.length;if(!n)return 0;str=str.split('');while(n--)if(isNaN(parseInt(str[n],10)))return 0;return 1;}
function str2num(str){var len=str.length;if(len&1){str="\0"+str;len++;}
len>>=1;var result=Array();for(var i=0;i<len;i++){result[len-i-1]=str.charCodeAt(i<<1)<<8|str.charCodeAt((i<<1)+1);}
return result;}
function num2str(num){var n=num.length;var s=Array();for(var i=0;i<n;i++){s[n-i-1]=String.fromCharCode(num[i]>>8&0xff,num[i]&0xff);}
return s.join('');}
function rand(n,s){var lowBitMasks=new Array(0x0000,0x0001,0x0003,0x0007,0x000f,0x001f,0x003f,0x007f,0x00ff,0x01ff,0x03ff,0x07ff,0x0fff,0x1fff,0x3fff,0x7fff);var r=n%16;var q=n>>4;var result=Array();for(var i=0;i<q;i++){result[i]=Math.floor(Math.random()*0xffff);}
if(r!=0){result[q]=Math.floor(Math.random()*lowBitMasks[r]);if(s){result[q]|=1<<(r-1);}}
else if(s){result[q-1]|=0x8000;}
return result;}
function long2str(v,w){var vl=v.length;var sl=v[vl-1]&0xffffffff;for(var i=0;i<vl;i++)
{v[i]=String.fromCharCode(v[i]&0xff,v[i]>>>8&0xff,v[i]>>>16&0xff,v[i]>>>24&0xff);}
if(w){return v.join('').substring(0,sl);}
else{return v.join('');}}
function str2long(s,w){var len=s.length;var v=[];for(var i=0;i<len;i+=4)
{v[i>>2]=s.charCodeAt(i)|s.charCodeAt(i+1)<<8|s.charCodeAt(i+2)<<16|s.charCodeAt(i+3)<<24;}
if(w){v[v.length]=len;}
return v;}
function xxtea_encrypt(str,key){if(str==""){return"";}
var v=str2long(str,true);var k=str2long(key,false);if(k.length<4)
{k.length=4;}
var n=v.length-1;var z=v[n],y=v[0],delta=0x9E3779B9;var mx,e,q=Math.floor(6+52/(n+1)),sum=0;while(0<q--){sum=sum+delta&0xffffffff;e=sum>>>2&3;for(var p=0;p<n;p++){y=v[p+1];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);z=v[p]=v[p]+mx&0xffffffff;}
y=v[0];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);z=v[n]=v[n]+mx&0xffffffff;}
return long2str(v,false);}
function xxtea_decrypt(str,key){if(str==""){return"";}
var v=str2long(str,false);var k=str2long(key,false);if(k.length<4)
{k.length=4;}
var n=v.length-1;var z=v[n-1],y=v[0],delta=0x9E3779B9;var mx,e,q=Math.floor(6+52/(n+1)),sum=q*delta&0xffffffff;while(sum!=0){e=sum>>>2&3;for(var p=n;p>0;p--){z=v[p-1];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);y=v[p]=v[p]-mx&0xffffffff;}
z=v[n];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);y=v[0]=v[0]-mx&0xffffffff;sum=sum-delta&0xffffffff;}
return long2str(v,true);}
function phprpc_error(errno,errstr){this.errno=errno;this.errstr=errstr;}
function phprpc_client(){this.__url='';this.__encrypt=false;this.encrypt=0;this.ready=false;this.ajax=true;this.__get_mode=function(url){var protocol=null;var host=null;if(url.substr(0,7)=="http://"){protocol="http:";host=url.substring(7,url.indexOf('/',7));}
else if(url.substr(0,8)=="https://"){protocol="https:";host=url.substring(8,url.indexOf('/',8));}
if((protocol==null&&host==null)||(protocol==window.location.protocol&&host==window.location.host)){try{var xmlhttp=this.__create_xmlhttp();delete(xmlhttp);this.ajax=true;}
catch(e){this.ajax=false;}}
else{this.ajax=false;}}
this.use_service=function(url,encrypt){if(typeof(encrypt)=="undefined"){encrypt=this.__encrypt;}
else if(typeof(encrypt)=="boolean"){this.__encrypt=encrypt;}
if(typeof(this.__name)=="undefined"){return false;}
this.__url=url;this.ready=false;this.__get_mode(url);if(this.ajax){var xmlhttp=this.__create_xmlhttp();var __rpc=this;var __run=false;if(encrypt===true){xmlhttp.onreadystatechange=function(){if(xmlhttp.readyState==4&&xmlhttp.status==200&&__run==false){__run=true;if(xmlhttp.responseText){eval(xmlhttp.responseText);if(typeof(phprpc_encrypt)=="undefined"){__rpc.__encrypt=false;__rpc.use_service(__rpc.__url);}
else{__rpc.use_service(__rpc.__url,num2dec(__rpc.__get_key(__rpc)).replace(/\+/g,'%2B'));}}
delete(xmlhttp);}}}
else{var __run=false;xmlhttp.onreadystatechange=function(){if(xmlhttp.readyState==4&&xmlhttp.status==200&&__run==false){__run=true;if(xmlhttp.responseText){eval(xmlhttp.responseText);__rpc.__create_functions(__rpc);}}
delete(xmlhttp);}}
xmlhttp.open("GET",this.__url+'?phprpc_encrypt='+encrypt+'&phprpc_encode=false',true);xmlhttp.send(null);}
else{var id=this.__create_id();var callback;if(this.__encrypt){callback=base64encode(utf16to8([this.__name,".__switch_key('",id,"');"].join('')));}
else{callback=base64encode(utf16to8([this.__name,".__get_functions('",id,"');"].join('')));}
var request=['phprpc_encrypt=',this.__encrypt,'&phprpc_callback=',callback,'&phprpc_encode=false'].join('');this.__append_script(id,request);}
return true;}
this.__get_key=function(rpc){rpc.__encrypt=unserialize(phprpc_encrypt);rpc.__encrypt['p']=dec2num(rpc.__encrypt['p']);rpc.__encrypt['g']=dec2num(rpc.__encrypt['g']);rpc.__encrypt['y']=dec2num(rpc.__encrypt['y']);rpc.__encrypt['x']=rand(127,1);var key=pow_mod(rpc.__encrypt['y'],rpc.__encrypt['x'],rpc.__encrypt['p']);key=num2str(key);var n=16-key.length;var k=[];for(var i=0;i<n;i++){k[i]='\0';}
k[n]=key;rpc.__encrypt['k']=k.join('');return pow_mod(rpc.__encrypt['g'],rpc.__encrypt['x'],rpc.__encrypt['p']);}
this.__switch_key=function(id){if(typeof(phprpc_encrypt)=="undefined"){this.__encrypt=false;this.__get_functions(id);}
else{this.__remove_script(id);var callback=base64encode(utf16to8([this.__name,".__get_functions('",id,"');"].join('')));var request=['phprpc_encrypt=',num2dec(this.__get_key(this)),'&phprpc_callback=',callback,'&phprpc_encode=false'].join('');this.__append_script(id,request);}}
this.__create_functions=function(rpc){var functions=unserialize(phprpc_functions);var func=[];var n=functions.length;for(var i=0;i<n;i++){func[i]=[rpc.__name,".",functions[i]," = function () {\r\n    this.__call('",functions[i],"', this.__args_to_array(arguments));\r\n}\r\n",rpc.__name,".",functions[i],".ref = false;\r\n"].join('');}
eval(func.join(''));rpc.ready=true;if(typeof(rpc.onready)=="function"){rpc.onready();}}
this.__get_functions=function(id){this.__create_functions(this);this.__remove_script(id);}
this.__call=function(func,args){var __args=serialize(args);if((this.__encrypt!==false)&&(this.encrypt>0)){__args=xxtea_encrypt(__args,this.__encrypt['k']);}
__args=base64encode(__args);var request=['phprpc_func=',func,'&phprpc_args=',__args,'&phprpc_encode=false','&phprpc_encrypt=',this.encrypt];var ref=eval(this.__name+"."+func+".ref");if(!ref){request[request.length]='&phprpc_ref=false';}
if(this.ajax){var xmlhttp=this.__create_xmlhttp();var session={'args':args,'ref':ref,'encrypt':this.encrypt,'run':false};var __rpc=this;xmlhttp.onreadystatechange=function(){if(xmlhttp.readyState==4&&xmlhttp.status==200&&session.run==false){session.run=true;if(xmlhttp.responseText){eval(xmlhttp.responseText);var callback=eval(__rpc.__name+"."+func+"_callback");__rpc.__get_result(session.encrypt,session.ref,session.args,callback);}
delete(xmlhttp);}}
xmlhttp.open("POST",this.__url,true);xmlhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');xmlhttp.send(request.join('').replace(/\+/g,'%2B'));}
else{var id=this.__create_id();var callback=this.__name+"."+func+"_callback";if(typeof(eval(callback))!="function"){callback="null";}
callback=this.__name+".__callback('"+id+"', "+callback+");"
request[request.length]='&phprpc_callback=';request[request.length]=base64encode(utf16to8(callback));this.__append_script(id,request.join(''),args,ref);}};this.__get_result=function(encrypt,ref,args,callback){if(args[args.length-1].constructor==Function){callback=args[args.length-1];}
if(typeof(callback)=="function"){phprpc_warning=null;if((phprpc_errno!=1)&&(phprpc_errno!=16)&&(phprpc_errno!=64)&&(phprpc_errno!=256)){if((this.__encrypt!==false)&&(encrypt>0)){if(encrypt>1){phprpc_result=xxtea_decrypt(phprpc_result,this.__encrypt['k']);}
if(ref){phprpc_args=xxtea_decrypt(phprpc_args,this.__encrypt['k']);}}
phprpc_result=unserialize(phprpc_result);if(ref){phprpc_args=unserialize(phprpc_args);}
else{phprpc_args=args;}
phprpc_warning=new phprpc_error(phprpc_errno,phprpc_errstr);}
else{phprpc_result=new phprpc_error(phprpc_errno,phprpc_errstr);phprpc_args=args;}
callback(phprpc_result,phprpc_args,phprpc_output,phprpc_warning);}}
this.__callback=function(id,callback){var script=document.getElementById("script_"+id);this.__get_result(script.encrypt,script.ref,script.args,callback);this.__remove_script(id);}
this.__create_xmlhttp=function(){if(window.XMLHttpRequest){var objXMLHttp=new XMLHttpRequest();if(objXMLHttp.readyState==null){objXMLHttp.readyState=0;objXMLHttp.addEventListener("load",function(){objXMLHttp.readyState=4;if(typeof(objXMLHttp.onreadystatechange)=="function"){objXMLHttp.onreadystatechange();}},false);}
return objXMLHttp;}
else{var MSXML=['MSXML2.XMLHTTP.5.0','MSXML2.XMLHTTP.4.0','MSXML2.XMLHTTP.3.0','MSXML2.XMLHTTP','Microsoft.XMLHTTP'];var n=MSXML.length;for(var i=0;i<n;i++){try{return new ActiveXObject(MSXML[i]);}
catch(e){}}
throw new Error("Your browser does not support xmlhttp objects");}};this.__create_id=function(){return(new Date()).getTime().toString(36)+Math.floor(Math.random()*100000000).toString(36);}
this.__append_script=function(id,request,args,ref){var script=document.createElement("script");script.id="script_"+id;script.src=this.__url+"?"+request.replace(/\+/g,'%2B');script.defer=true;script.type="text/javascript";script.args=args;script.ref=ref;script.encrypt=this.encrypt;var head=document.getElementsByTagName("head").item(0);head.appendChild(script);}
this.__remove_script=function(id){var script=document.getElementById("script_"+id);var head=document.getElementsByTagName("head").item(0);head.removeChild(script);}
this.__args_to_array=function(args){var n=args.length;var argArray=new Array(n);for(i=0;i<n;i++){argArray[i]=args[i];}
return argArray;}}
phprpc_client.create=function(name,encrypt){eval([name,' = new phprpc_client();',name,'.__name = "',name,'";'].join(''));if(typeof(encrypt)=="boolean"){encrypt=true;eval([name,'.__encrypt = ',encrypt,';'].join(''));}}