var mt=Object.defineProperty;var Z=Object.getOwnPropertySymbols;var pt=Object.prototype.hasOwnProperty,_t=Object.prototype.propertyIsEnumerable;var j=(r,f,w)=>f in r?mt(r,f,{enumerable:!0,configurable:!0,writable:!0,value:w}):r[f]=w,U=(r,f)=>{for(var w in f||(f={}))pt.call(f,w)&&j(r,w,f[w]);if(Z)for(var w of Z(f))_t.call(f,w)&&j(r,w,f[w]);return r};import{a as Q,s as nt,aB as vt,aF as wt,a3 as Y,B as ot,ag as W,a2 as D,o as H,R as J,S as _,Z as d,e as I,M as at,Y as x,X as st,az as it,aA as rt,j as Ct,m as lt,b as X,W as q}from"./vue.1653003640069.js";import{B as Tt,C as bt,u as ut,_ as G,D as At,s as Dt,S as tt,F as et,G as Et,H as It,E as Bt,N as ht,z as Lt}from"./index.1653003640069.js";import{l as Pt}from"./logo-mini.1653003640069.js";var kt="assets/login-icon-two.1653003640069.svg";const yt=Q({name:"loginAccount",setup(){const{t:r}=Tt(),f=bt(),w=ut(),{themeConfig:M}=nt(w),T=vt(),E=wt(),v=Y({isShowPassword:!1,ruleForm:{userName:"sa",password:"1",code:"1234"},loading:{signIn:!1}}),b=ot(()=>At(new Date)),c=async()=>{const p=await Dt({url:"/login",method:"post",data:{username:v.ruleForm.userName,password:v.ruleForm.password}});v.loading.signIn=!0;let A=[],C=[],L=["admin"],P=["btn.add","btn.del","btn.edit","btn.link"],k=["common"],V=["btn.add","btn.link"];v.ruleForm.userName==="admin"?(A=L,C=P):(A=k,C=V),A=L,C=P;const F={userid:p.zuser.id,userName:v.ruleForm.userName,photo:v.ruleForm.userName==="admin"?"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1813762643,1914315241&fm=26&gp=0.jpg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=317673774,2961727727&fm=26&gp=0.jpg",time:new Date().getTime(),roles:A,authBtnList:C};tt.set("token",Math.random().toString(36).substr(0)),tt.set("userInfo",F),et.set("userid",F.userid),et.set("token","Bearer "+p.token),f.setUserInfos(U({},F)),M.value.isRequestRoutes?(await It(),m()):(await Et(),m())},m=()=>{var C,L,P,k;let p=b.value;(C=T.query)!=null&&C.redirect?E.push({path:(L=T.query)==null?void 0:L.redirect,query:Object.keys((P=T.query)==null?void 0:P.params).length>0?JSON.parse((k=T.query)==null?void 0:k.params):""}):E.push("/"),v.loading.signIn=!0;const A=r("message.signInText");Bt.success(`${p}\uFF0C${A}`),ht.start()};return U({onSignIn:c},W(v))}}),Rt=st("1234");function Mt(r,f,w,M,T,E){const v=D("ele-User"),b=D("el-icon"),c=D("el-input"),m=D("el-form-item"),p=D("ele-Unlock"),A=D("ele-Position"),C=D("el-col"),L=D("el-button"),P=D("el-form");return H(),J(P,{size:"large",class:"login-content-form"},{default:_(()=>[d(m,{class:"login-animation1"},{default:_(()=>[d(c,{type:"text",placeholder:r.$t("message.account.accountPlaceholder1"),modelValue:r.ruleForm.userName,"onUpdate:modelValue":f[0]||(f[0]=k=>r.ruleForm.userName=k),clearable:"",autocomplete:"off"},{prefix:_(()=>[d(b,{class:"el-input__icon"},{default:_(()=>[d(v)]),_:1})]),_:1},8,["placeholder","modelValue"])]),_:1}),d(m,{class:"login-animation2"},{default:_(()=>[d(c,{type:r.isShowPassword?"text":"password",placeholder:r.$t("message.account.accountPlaceholder2"),modelValue:r.ruleForm.password,"onUpdate:modelValue":f[2]||(f[2]=k=>r.ruleForm.password=k),autocomplete:"off"},{prefix:_(()=>[d(b,{class:"el-input__icon"},{default:_(()=>[d(p)]),_:1})]),suffix:_(()=>[I("i",{class:at(["iconfont el-input__icon login-content-password",r.isShowPassword?"icon-yincangmima":"icon-xianshimima"]),onClick:f[1]||(f[1]=k=>r.isShowPassword=!r.isShowPassword)},null,2)]),_:1},8,["type","placeholder","modelValue"])]),_:1}),d(m,{class:"login-animation3"},{default:_(()=>[d(C,{span:15},{default:_(()=>[d(c,{type:"text",maxlength:"4",placeholder:r.$t("message.account.accountPlaceholder3"),modelValue:r.ruleForm.code,"onUpdate:modelValue":f[3]||(f[3]=k=>r.ruleForm.code=k),clearable:"",autocomplete:"off"},{prefix:_(()=>[d(b,{class:"el-input__icon"},{default:_(()=>[d(A)]),_:1})]),_:1},8,["placeholder","modelValue"])]),_:1}),d(C,{span:1}),d(C,{span:8},{default:_(()=>[d(L,{class:"login-content-code"},{default:_(()=>[Rt]),_:1})]),_:1})]),_:1}),d(m,{class:"login-animation4"},{default:_(()=>[d(L,{type:"primary",class:"login-content-submit",round:"",onClick:r.onSignIn,loading:r.loading.signIn},{default:_(()=>[I("span",null,x(r.$t("message.account.accountBtnText")),1)]),_:1},8,["onClick","loading"])]),_:1})]),_:1})}var Nt=G(yt,[["render",Mt],["__scopeId","data-v-50de952d"]]);const St={userName:"",code:""},xt=Q({name:"loginMobile",setup(){const r=Y({ruleForm:St});return U({},W(r))}}),Ft=r=>(it("data-v-d513106e"),r=r(),rt(),r),Ot=Ft(()=>I("i",{class:"iconfont icon-dianhua el-input__icon"},null,-1)),$t={class:"font12 mt30 login-animation4 login-msg"};function Ht(r,f,w,M,T,E){const v=D("el-input"),b=D("el-form-item"),c=D("ele-Position"),m=D("el-icon"),p=D("el-col"),A=D("el-button"),C=D("el-form");return H(),J(C,{size:"large",class:"login-content-form"},{default:_(()=>[d(b,{class:"login-animation1"},{default:_(()=>[d(v,{type:"text",placeholder:r.$t("message.mobile.placeholder1"),modelValue:r.ruleForm.userName,"onUpdate:modelValue":f[0]||(f[0]=L=>r.ruleForm.userName=L),clearable:"",autocomplete:"off"},{prefix:_(()=>[Ot]),_:1},8,["placeholder","modelValue"])]),_:1}),d(b,{class:"login-animation2"},{default:_(()=>[d(p,{span:15},{default:_(()=>[d(v,{type:"text",maxlength:"4",placeholder:r.$t("message.mobile.placeholder2"),modelValue:r.ruleForm.code,"onUpdate:modelValue":f[1]||(f[1]=L=>r.ruleForm.code=L),clearable:"",autocomplete:"off"},{prefix:_(()=>[d(m,{class:"el-input__icon"},{default:_(()=>[d(c)]),_:1})]),_:1},8,["placeholder","modelValue"])]),_:1}),d(p,{span:1}),d(p,{span:8},{default:_(()=>[d(A,{class:"login-content-code"},{default:_(()=>[st(x(r.$t("message.mobile.codeText")),1)]),_:1})]),_:1})]),_:1}),d(b,{class:"login-animation3"},{default:_(()=>[d(A,{round:"",type:"primary",class:"login-content-submit"},{default:_(()=>[I("span",null,x(r.$t("message.mobile.btnText")),1)]),_:1})]),_:1}),I("div",$t,x(r.$t("message.mobile.msgText")),1)]),_:1})}var Ut=G(xt,[["render",Ht],["__scopeId","data-v-d513106e"]]),ft={exports:{}};(function(r,f){var w;(function(M,T){r.exports=T()})(Lt,function(){function M(t){this.mode=E.MODE_8BIT_BYTE,this.data=t,this.parsedData=[];for(var e=0,n=this.data.length;e<n;e++){var o=[],a=this.data.charCodeAt(e);a>65536?(o[0]=240|(a&1835008)>>>18,o[1]=128|(a&258048)>>>12,o[2]=128|(a&4032)>>>6,o[3]=128|a&63):a>2048?(o[0]=224|(a&61440)>>>12,o[1]=128|(a&4032)>>>6,o[2]=128|a&63):a>128?(o[0]=192|(a&1984)>>>6,o[1]=128|a&63):o[0]=a,this.parsedData.push(o)}this.parsedData=Array.prototype.concat.apply([],this.parsedData),this.parsedData.length!=this.data.length&&(this.parsedData.unshift(191),this.parsedData.unshift(187),this.parsedData.unshift(239))}M.prototype={getLength:function(t){return this.parsedData.length},write:function(t){for(var e=0,n=this.parsedData.length;e<n;e++)t.put(this.parsedData[e],8)}};function T(t,e){this.typeNumber=t,this.errorCorrectLevel=e,this.modules=null,this.moduleCount=0,this.dataCache=null,this.dataList=[]}T.prototype={addData:function(t){var e=new M(t);this.dataList.push(e),this.dataCache=null},isDark:function(t,e){if(t<0||this.moduleCount<=t||e<0||this.moduleCount<=e)throw new Error(t+","+e);return this.modules[t][e]},getModuleCount:function(){return this.moduleCount},make:function(){this.makeImpl(!1,this.getBestMaskPattern())},makeImpl:function(t,e){this.moduleCount=this.typeNumber*4+17,this.modules=new Array(this.moduleCount);for(var n=0;n<this.moduleCount;n++){this.modules[n]=new Array(this.moduleCount);for(var o=0;o<this.moduleCount;o++)this.modules[n][o]=null}this.setupPositionProbePattern(0,0),this.setupPositionProbePattern(this.moduleCount-7,0),this.setupPositionProbePattern(0,this.moduleCount-7),this.setupPositionAdjustPattern(),this.setupTimingPattern(),this.setupTypeInfo(t,e),this.typeNumber>=7&&this.setupTypeNumber(t),this.dataCache==null&&(this.dataCache=T.createData(this.typeNumber,this.errorCorrectLevel,this.dataList)),this.mapData(this.dataCache,e)},setupPositionProbePattern:function(t,e){for(var n=-1;n<=7;n++)if(!(t+n<=-1||this.moduleCount<=t+n))for(var o=-1;o<=7;o++)e+o<=-1||this.moduleCount<=e+o||(0<=n&&n<=6&&(o==0||o==6)||0<=o&&o<=6&&(n==0||n==6)||2<=n&&n<=4&&2<=o&&o<=4?this.modules[t+n][e+o]=!0:this.modules[t+n][e+o]=!1)},getBestMaskPattern:function(){for(var t=0,e=0,n=0;n<8;n++){this.makeImpl(!0,n);var o=c.getLostPoint(this);(n==0||t>o)&&(t=o,e=n)}return e},createMovieClip:function(t,e,n){var o=t.createEmptyMovieClip(e,n),a=1;this.make();for(var i=0;i<this.modules.length;i++)for(var l=i*a,s=0;s<this.modules[i].length;s++){var u=s*a,g=this.modules[i][s];g&&(o.beginFill(0,100),o.moveTo(u,l),o.lineTo(u+a,l),o.lineTo(u+a,l+a),o.lineTo(u,l+a),o.endFill())}return o},setupTimingPattern:function(){for(var t=8;t<this.moduleCount-8;t++)this.modules[t][6]==null&&(this.modules[t][6]=t%2==0);for(var e=8;e<this.moduleCount-8;e++)this.modules[6][e]==null&&(this.modules[6][e]=e%2==0)},setupPositionAdjustPattern:function(){for(var t=c.getPatternPosition(this.typeNumber),e=0;e<t.length;e++)for(var n=0;n<t.length;n++){var o=t[e],a=t[n];if(this.modules[o][a]==null)for(var i=-2;i<=2;i++)for(var l=-2;l<=2;l++)i==-2||i==2||l==-2||l==2||i==0&&l==0?this.modules[o+i][a+l]=!0:this.modules[o+i][a+l]=!1}},setupTypeNumber:function(t){for(var e=c.getBCHTypeNumber(this.typeNumber),n=0;n<18;n++){var o=!t&&(e>>n&1)==1;this.modules[Math.floor(n/3)][n%3+this.moduleCount-8-3]=o}for(var n=0;n<18;n++){var o=!t&&(e>>n&1)==1;this.modules[n%3+this.moduleCount-8-3][Math.floor(n/3)]=o}},setupTypeInfo:function(t,e){for(var n=this.errorCorrectLevel<<3|e,o=c.getBCHTypeInfo(n),a=0;a<15;a++){var i=!t&&(o>>a&1)==1;a<6?this.modules[a][8]=i:a<8?this.modules[a+1][8]=i:this.modules[this.moduleCount-15+a][8]=i}for(var a=0;a<15;a++){var i=!t&&(o>>a&1)==1;a<8?this.modules[8][this.moduleCount-a-1]=i:a<9?this.modules[8][15-a-1+1]=i:this.modules[8][15-a-1]=i}this.modules[this.moduleCount-8][8]=!t},mapData:function(t,e){for(var n=-1,o=this.moduleCount-1,a=7,i=0,l=this.moduleCount-1;l>0;l-=2)for(l==6&&l--;;){for(var s=0;s<2;s++)if(this.modules[o][l-s]==null){var u=!1;i<t.length&&(u=(t[i]>>>a&1)==1);var g=c.getMask(e,o,l-s);g&&(u=!u),this.modules[o][l-s]=u,a--,a==-1&&(i++,a=7)}if(o+=n,o<0||this.moduleCount<=o){o-=n,n=-n;break}}}},T.PAD0=236,T.PAD1=17,T.createData=function(t,e,n){for(var o=C.getRSBlocks(t,e),a=new L,i=0;i<n.length;i++){var l=n[i];a.put(l.mode,4),a.put(l.getLength(),c.getLengthInBits(l.mode,t)),l.write(a)}for(var s=0,i=0;i<o.length;i++)s+=o[i].dataCount;if(a.getLengthInBits()>s*8)throw new Error("code length overflow. ("+a.getLengthInBits()+">"+s*8+")");for(a.getLengthInBits()+4<=s*8&&a.put(0,4);a.getLengthInBits()%8!=0;)a.putBit(!1);for(;!(a.getLengthInBits()>=s*8||(a.put(T.PAD0,8),a.getLengthInBits()>=s*8));)a.put(T.PAD1,8);return T.createBytes(a,o)},T.createBytes=function(t,e){for(var n=0,o=0,a=0,i=new Array(e.length),l=new Array(e.length),s=0;s<e.length;s++){var u=e[s].dataCount,g=e[s].totalCount-u;o=Math.max(o,u),a=Math.max(a,g),i[s]=new Array(u);for(var h=0;h<i[s].length;h++)i[s][h]=255&t.buffer[h+n];n+=u;var B=c.getErrorCorrectPolynomial(g),y=new A(i[s],B.getLength()-1),N=y.mod(B);l[s]=new Array(B.getLength()-1);for(var h=0;h<l[s].length;h++){var R=h+N.getLength()-l[s].length;l[s][h]=R>=0?N.get(R):0}}for(var S=0,h=0;h<e.length;h++)S+=e[h].totalCount;for(var O=new Array(S),$=0,h=0;h<o;h++)for(var s=0;s<e.length;s++)h<i[s].length&&(O[$++]=i[s][h]);for(var h=0;h<a;h++)for(var s=0;s<e.length;s++)h<l[s].length&&(O[$++]=l[s][h]);return O};for(var E={MODE_NUMBER:1<<0,MODE_ALPHA_NUM:1<<1,MODE_8BIT_BYTE:1<<2,MODE_KANJI:1<<3},v={L:1,M:0,Q:3,H:2},b={PATTERN000:0,PATTERN001:1,PATTERN010:2,PATTERN011:3,PATTERN100:4,PATTERN101:5,PATTERN110:6,PATTERN111:7},c={PATTERN_POSITION_TABLE:[[],[6,18],[6,22],[6,26],[6,30],[6,34],[6,22,38],[6,24,42],[6,26,46],[6,28,50],[6,30,54],[6,32,58],[6,34,62],[6,26,46,66],[6,26,48,70],[6,26,50,74],[6,30,54,78],[6,30,56,82],[6,30,58,86],[6,34,62,90],[6,28,50,72,94],[6,26,50,74,98],[6,30,54,78,102],[6,28,54,80,106],[6,32,58,84,110],[6,30,58,86,114],[6,34,62,90,118],[6,26,50,74,98,122],[6,30,54,78,102,126],[6,26,52,78,104,130],[6,30,56,82,108,134],[6,34,60,86,112,138],[6,30,58,86,114,142],[6,34,62,90,118,146],[6,30,54,78,102,126,150],[6,24,50,76,102,128,154],[6,28,54,80,106,132,158],[6,32,58,84,110,136,162],[6,26,54,82,110,138,166],[6,30,58,86,114,142,170]],G15:1<<10|1<<8|1<<5|1<<4|1<<2|1<<1|1<<0,G18:1<<12|1<<11|1<<10|1<<9|1<<8|1<<5|1<<2|1<<0,G15_MASK:1<<14|1<<12|1<<10|1<<4|1<<1,getBCHTypeInfo:function(t){for(var e=t<<10;c.getBCHDigit(e)-c.getBCHDigit(c.G15)>=0;)e^=c.G15<<c.getBCHDigit(e)-c.getBCHDigit(c.G15);return(t<<10|e)^c.G15_MASK},getBCHTypeNumber:function(t){for(var e=t<<12;c.getBCHDigit(e)-c.getBCHDigit(c.G18)>=0;)e^=c.G18<<c.getBCHDigit(e)-c.getBCHDigit(c.G18);return t<<12|e},getBCHDigit:function(t){for(var e=0;t!=0;)e++,t>>>=1;return e},getPatternPosition:function(t){return c.PATTERN_POSITION_TABLE[t-1]},getMask:function(t,e,n){switch(t){case b.PATTERN000:return(e+n)%2==0;case b.PATTERN001:return e%2==0;case b.PATTERN010:return n%3==0;case b.PATTERN011:return(e+n)%3==0;case b.PATTERN100:return(Math.floor(e/2)+Math.floor(n/3))%2==0;case b.PATTERN101:return e*n%2+e*n%3==0;case b.PATTERN110:return(e*n%2+e*n%3)%2==0;case b.PATTERN111:return(e*n%3+(e+n)%2)%2==0;default:throw new Error("bad maskPattern:"+t)}},getErrorCorrectPolynomial:function(t){for(var e=new A([1],0),n=0;n<t;n++)e=e.multiply(new A([1,m.gexp(n)],0));return e},getLengthInBits:function(t,e){if(1<=e&&e<10)switch(t){case E.MODE_NUMBER:return 10;case E.MODE_ALPHA_NUM:return 9;case E.MODE_8BIT_BYTE:return 8;case E.MODE_KANJI:return 8;default:throw new Error("mode:"+t)}else if(e<27)switch(t){case E.MODE_NUMBER:return 12;case E.MODE_ALPHA_NUM:return 11;case E.MODE_8BIT_BYTE:return 16;case E.MODE_KANJI:return 10;default:throw new Error("mode:"+t)}else if(e<41)switch(t){case E.MODE_NUMBER:return 14;case E.MODE_ALPHA_NUM:return 13;case E.MODE_8BIT_BYTE:return 16;case E.MODE_KANJI:return 12;default:throw new Error("mode:"+t)}else throw new Error("type:"+e)},getLostPoint:function(t){for(var e=t.getModuleCount(),n=0,o=0;o<e;o++)for(var a=0;a<e;a++){for(var i=0,l=t.isDark(o,a),s=-1;s<=1;s++)if(!(o+s<0||e<=o+s))for(var u=-1;u<=1;u++)a+u<0||e<=a+u||s==0&&u==0||l==t.isDark(o+s,a+u)&&i++;i>5&&(n+=3+i-5)}for(var o=0;o<e-1;o++)for(var a=0;a<e-1;a++){var g=0;t.isDark(o,a)&&g++,t.isDark(o+1,a)&&g++,t.isDark(o,a+1)&&g++,t.isDark(o+1,a+1)&&g++,(g==0||g==4)&&(n+=3)}for(var o=0;o<e;o++)for(var a=0;a<e-6;a++)t.isDark(o,a)&&!t.isDark(o,a+1)&&t.isDark(o,a+2)&&t.isDark(o,a+3)&&t.isDark(o,a+4)&&!t.isDark(o,a+5)&&t.isDark(o,a+6)&&(n+=40);for(var a=0;a<e;a++)for(var o=0;o<e-6;o++)t.isDark(o,a)&&!t.isDark(o+1,a)&&t.isDark(o+2,a)&&t.isDark(o+3,a)&&t.isDark(o+4,a)&&!t.isDark(o+5,a)&&t.isDark(o+6,a)&&(n+=40);for(var h=0,a=0;a<e;a++)for(var o=0;o<e;o++)t.isDark(o,a)&&h++;var B=Math.abs(100*h/e/e-50)/5;return n+=B*10,n}},m={glog:function(t){if(t<1)throw new Error("glog("+t+")");return m.LOG_TABLE[t]},gexp:function(t){for(;t<0;)t+=255;for(;t>=256;)t-=255;return m.EXP_TABLE[t]},EXP_TABLE:new Array(256),LOG_TABLE:new Array(256)},p=0;p<8;p++)m.EXP_TABLE[p]=1<<p;for(var p=8;p<256;p++)m.EXP_TABLE[p]=m.EXP_TABLE[p-4]^m.EXP_TABLE[p-5]^m.EXP_TABLE[p-6]^m.EXP_TABLE[p-8];for(var p=0;p<255;p++)m.LOG_TABLE[m.EXP_TABLE[p]]=p;function A(t,e){if(t.length==null)throw new Error(t.length+"/"+e);for(var n=0;n<t.length&&t[n]==0;)n++;this.num=new Array(t.length-n+e);for(var o=0;o<t.length-n;o++)this.num[o]=t[o+n]}A.prototype={get:function(t){return this.num[t]},getLength:function(){return this.num.length},multiply:function(t){for(var e=new Array(this.getLength()+t.getLength()-1),n=0;n<this.getLength();n++)for(var o=0;o<t.getLength();o++)e[n+o]^=m.gexp(m.glog(this.get(n))+m.glog(t.get(o)));return new A(e,0)},mod:function(t){if(this.getLength()-t.getLength()<0)return this;for(var e=m.glog(this.get(0))-m.glog(t.get(0)),n=new Array(this.getLength()),o=0;o<this.getLength();o++)n[o]=this.get(o);for(var o=0;o<t.getLength();o++)n[o]^=m.gexp(m.glog(t.get(o))+e);return new A(n,0).mod(t)}};function C(t,e){this.totalCount=t,this.dataCount=e}C.RS_BLOCK_TABLE=[[1,26,19],[1,26,16],[1,26,13],[1,26,9],[1,44,34],[1,44,28],[1,44,22],[1,44,16],[1,70,55],[1,70,44],[2,35,17],[2,35,13],[1,100,80],[2,50,32],[2,50,24],[4,25,9],[1,134,108],[2,67,43],[2,33,15,2,34,16],[2,33,11,2,34,12],[2,86,68],[4,43,27],[4,43,19],[4,43,15],[2,98,78],[4,49,31],[2,32,14,4,33,15],[4,39,13,1,40,14],[2,121,97],[2,60,38,2,61,39],[4,40,18,2,41,19],[4,40,14,2,41,15],[2,146,116],[3,58,36,2,59,37],[4,36,16,4,37,17],[4,36,12,4,37,13],[2,86,68,2,87,69],[4,69,43,1,70,44],[6,43,19,2,44,20],[6,43,15,2,44,16],[4,101,81],[1,80,50,4,81,51],[4,50,22,4,51,23],[3,36,12,8,37,13],[2,116,92,2,117,93],[6,58,36,2,59,37],[4,46,20,6,47,21],[7,42,14,4,43,15],[4,133,107],[8,59,37,1,60,38],[8,44,20,4,45,21],[12,33,11,4,34,12],[3,145,115,1,146,116],[4,64,40,5,65,41],[11,36,16,5,37,17],[11,36,12,5,37,13],[5,109,87,1,110,88],[5,65,41,5,66,42],[5,54,24,7,55,25],[11,36,12],[5,122,98,1,123,99],[7,73,45,3,74,46],[15,43,19,2,44,20],[3,45,15,13,46,16],[1,135,107,5,136,108],[10,74,46,1,75,47],[1,50,22,15,51,23],[2,42,14,17,43,15],[5,150,120,1,151,121],[9,69,43,4,70,44],[17,50,22,1,51,23],[2,42,14,19,43,15],[3,141,113,4,142,114],[3,70,44,11,71,45],[17,47,21,4,48,22],[9,39,13,16,40,14],[3,135,107,5,136,108],[3,67,41,13,68,42],[15,54,24,5,55,25],[15,43,15,10,44,16],[4,144,116,4,145,117],[17,68,42],[17,50,22,6,51,23],[19,46,16,6,47,17],[2,139,111,7,140,112],[17,74,46],[7,54,24,16,55,25],[34,37,13],[4,151,121,5,152,122],[4,75,47,14,76,48],[11,54,24,14,55,25],[16,45,15,14,46,16],[6,147,117,4,148,118],[6,73,45,14,74,46],[11,54,24,16,55,25],[30,46,16,2,47,17],[8,132,106,4,133,107],[8,75,47,13,76,48],[7,54,24,22,55,25],[22,45,15,13,46,16],[10,142,114,2,143,115],[19,74,46,4,75,47],[28,50,22,6,51,23],[33,46,16,4,47,17],[8,152,122,4,153,123],[22,73,45,3,74,46],[8,53,23,26,54,24],[12,45,15,28,46,16],[3,147,117,10,148,118],[3,73,45,23,74,46],[4,54,24,31,55,25],[11,45,15,31,46,16],[7,146,116,7,147,117],[21,73,45,7,74,46],[1,53,23,37,54,24],[19,45,15,26,46,16],[5,145,115,10,146,116],[19,75,47,10,76,48],[15,54,24,25,55,25],[23,45,15,25,46,16],[13,145,115,3,146,116],[2,74,46,29,75,47],[42,54,24,1,55,25],[23,45,15,28,46,16],[17,145,115],[10,74,46,23,75,47],[10,54,24,35,55,25],[19,45,15,35,46,16],[17,145,115,1,146,116],[14,74,46,21,75,47],[29,54,24,19,55,25],[11,45,15,46,46,16],[13,145,115,6,146,116],[14,74,46,23,75,47],[44,54,24,7,55,25],[59,46,16,1,47,17],[12,151,121,7,152,122],[12,75,47,26,76,48],[39,54,24,14,55,25],[22,45,15,41,46,16],[6,151,121,14,152,122],[6,75,47,34,76,48],[46,54,24,10,55,25],[2,45,15,64,46,16],[17,152,122,4,153,123],[29,74,46,14,75,47],[49,54,24,10,55,25],[24,45,15,46,46,16],[4,152,122,18,153,123],[13,74,46,32,75,47],[48,54,24,14,55,25],[42,45,15,32,46,16],[20,147,117,4,148,118],[40,75,47,7,76,48],[43,54,24,22,55,25],[10,45,15,67,46,16],[19,148,118,6,149,119],[18,75,47,31,76,48],[34,54,24,34,55,25],[20,45,15,61,46,16]],C.getRSBlocks=function(t,e){var n=C.getRsBlockTable(t,e);if(n==null)throw new Error("bad rs block @ typeNumber:"+t+"/errorCorrectLevel:"+e);for(var o=n.length/3,a=[],i=0;i<o;i++)for(var l=n[i*3+0],s=n[i*3+1],u=n[i*3+2],g=0;g<l;g++)a.push(new C(s,u));return a},C.getRsBlockTable=function(t,e){switch(e){case v.L:return C.RS_BLOCK_TABLE[(t-1)*4+0];case v.M:return C.RS_BLOCK_TABLE[(t-1)*4+1];case v.Q:return C.RS_BLOCK_TABLE[(t-1)*4+2];case v.H:return C.RS_BLOCK_TABLE[(t-1)*4+3];default:return}};function L(){this.buffer=[],this.length=0}L.prototype={get:function(t){var e=Math.floor(t/8);return(this.buffer[e]>>>7-t%8&1)==1},put:function(t,e){for(var n=0;n<e;n++)this.putBit((t>>>e-n-1&1)==1)},getLengthInBits:function(){return this.length},putBit:function(t){var e=Math.floor(this.length/8);this.buffer.length<=e&&this.buffer.push(0),t&&(this.buffer[e]|=128>>>this.length%8),this.length++}};var P=[[17,14,11,7],[32,26,20,14],[53,42,32,24],[78,62,46,34],[106,84,60,44],[134,106,74,58],[154,122,86,64],[192,152,108,84],[230,180,130,98],[271,213,151,119],[321,251,177,137],[367,287,203,155],[425,331,241,177],[458,362,258,194],[520,412,292,220],[586,450,322,250],[644,504,364,280],[718,560,394,310],[792,624,442,338],[858,666,482,382],[929,711,509,403],[1003,779,565,439],[1091,857,611,461],[1171,911,661,511],[1273,997,715,535],[1367,1059,751,593],[1465,1125,805,625],[1528,1190,868,658],[1628,1264,908,698],[1732,1370,982,742],[1840,1452,1030,790],[1952,1538,1112,842],[2068,1628,1168,898],[2188,1722,1228,958],[2303,1809,1283,983],[2431,1911,1351,1051],[2563,1989,1423,1093],[2699,2099,1499,1139],[2809,2213,1579,1219],[2953,2331,1663,1273]];function k(){return typeof CanvasRenderingContext2D!="undefined"}function V(){var t=!1,e=navigator.userAgent;if(/android/i.test(e)){t=!0;var n=e.toString().match(/android ([0-9]\.[0-9])/i);n&&n[1]&&(t=parseFloat(n[1]))}return t}var F=function(){var t=function(e,n){this._el=e,this._htOption=n};return t.prototype.draw=function(e){var n=this._htOption,o=this._el,a=e.getModuleCount();Math.floor(n.width/a),Math.floor(n.height/a),this.clear();function i(h,B){var y=document.createElementNS("http://www.w3.org/2000/svg",h);for(var N in B)B.hasOwnProperty(N)&&y.setAttribute(N,B[N]);return y}var l=i("svg",{viewBox:"0 0 "+String(a)+" "+String(a),width:"100%",height:"100%",fill:n.colorLight});l.setAttributeNS("http://www.w3.org/2000/xmlns/","xmlns:xlink","http://www.w3.org/1999/xlink"),o.appendChild(l),l.appendChild(i("rect",{fill:n.colorLight,width:"100%",height:"100%"})),l.appendChild(i("rect",{fill:n.colorDark,width:"1",height:"1",id:"template"}));for(var s=0;s<a;s++)for(var u=0;u<a;u++)if(e.isDark(s,u)){var g=i("use",{x:String(u),y:String(s)});g.setAttributeNS("http://www.w3.org/1999/xlink","href","#template"),l.appendChild(g)}},t.prototype.clear=function(){for(;this._el.hasChildNodes();)this._el.removeChild(this._el.lastChild)},t}(),dt=document.documentElement.tagName.toLowerCase()==="svg",z=dt?F:k()?function(){function t(){this._elImage.src=this._elCanvas.toDataURL("image/png"),this._elImage.style.display="block",this._elCanvas.style.display="none"}if(this&&this._android&&this._android<=2.1){var e=1/window.devicePixelRatio,n=CanvasRenderingContext2D.prototype.drawImage;CanvasRenderingContext2D.prototype.drawImage=function(i,l,s,u,g,h,B,y,N){if("nodeName"in i&&/img/i.test(i.nodeName))for(var R=arguments.length-1;R>=1;R--)arguments[R]=arguments[R]*e;else typeof y=="undefined"&&(arguments[1]*=e,arguments[2]*=e,arguments[3]*=e,arguments[4]*=e);n.apply(this,arguments)}}function o(i,l){var s=this;if(s._fFail=l,s._fSuccess=i,s._bSupportDataURI===null){var u=document.createElement("img"),g=function(){s._bSupportDataURI=!1,s._fFail&&s._fFail.call(s)},h=function(){s._bSupportDataURI=!0,s._fSuccess&&s._fSuccess.call(s)};u.onabort=g,u.onerror=g,u.onload=h,u.src="data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";return}else s._bSupportDataURI===!0&&s._fSuccess?s._fSuccess.call(s):s._bSupportDataURI===!1&&s._fFail&&s._fFail.call(s)}var a=function(i,l){this._bIsPainted=!1,this._android=V(),this._htOption=l,this._elCanvas=document.createElement("canvas"),this._elCanvas.width=l.width,this._elCanvas.height=l.height,i.appendChild(this._elCanvas),this._el=i,this._oContext=this._elCanvas.getContext("2d"),this._bIsPainted=!1,this._elImage=document.createElement("img"),this._elImage.alt="Scan me!",this._elImage.style.display="none",this._el.appendChild(this._elImage),this._bSupportDataURI=null};return a.prototype.draw=function(i){var l=this._elImage,s=this._oContext,u=this._htOption,g=i.getModuleCount(),h=u.width/g,B=u.height/g,y=Math.round(h),N=Math.round(B);l.style.display="none",this.clear();for(var R=0;R<g;R++)for(var S=0;S<g;S++){var O=i.isDark(R,S),$=S*h,K=R*B;s.strokeStyle=O?u.colorDark:u.colorLight,s.lineWidth=1,s.fillStyle=O?u.colorDark:u.colorLight,s.fillRect($,K,h,B),s.strokeRect(Math.floor($)+.5,Math.floor(K)+.5,y,N),s.strokeRect(Math.ceil($)-.5,Math.ceil(K)-.5,y,N)}this._bIsPainted=!0},a.prototype.makeImage=function(){this._bIsPainted&&o.call(this,t)},a.prototype.isPainted=function(){return this._bIsPainted},a.prototype.clear=function(){this._oContext.clearRect(0,0,this._elCanvas.width,this._elCanvas.height),this._bIsPainted=!1},a.prototype.round=function(i){return i&&Math.floor(i*1e3)/1e3},a}():function(){var t=function(e,n){this._el=e,this._htOption=n};return t.prototype.draw=function(e){for(var n=this._htOption,o=this._el,a=e.getModuleCount(),i=Math.floor(n.width/a),l=Math.floor(n.height/a),s=['<table style="border:0;border-collapse:collapse;">'],u=0;u<a;u++){s.push("<tr>");for(var g=0;g<a;g++)s.push('<td style="border:0;border-collapse:collapse;padding:0;margin:0;width:'+i+"px;height:"+l+"px;background-color:"+(e.isDark(u,g)?n.colorDark:n.colorLight)+';"></td>');s.push("</tr>")}s.push("</table>"),o.innerHTML=s.join("");var h=o.childNodes[0],B=(n.width-h.offsetWidth)/2,y=(n.height-h.offsetHeight)/2;B>0&&y>0&&(h.style.margin=y+"px "+B+"px")},t.prototype.clear=function(){this._el.innerHTML=""},t}();function ct(t,e){for(var n=1,o=gt(t),a=0,i=P.length;a<i;a++){var l=0;switch(e){case v.L:l=P[a][0];break;case v.M:l=P[a][1];break;case v.Q:l=P[a][2];break;case v.H:l=P[a][3];break}if(o<=l)break;n++}if(n>P.length)throw new Error("Too long data");return n}function gt(t){var e=encodeURI(t).toString().replace(/\%[0-9a-fA-F]{2}/g,"a");return e.length+(e.length!=t?3:0)}return w=function(t,e){if(this._htOption={width:256,height:256,typeNumber:4,colorDark:"#000000",colorLight:"#ffffff",correctLevel:v.H},typeof e=="string"&&(e={text:e}),e)for(var n in e)this._htOption[n]=e[n];typeof t=="string"&&(t=document.getElementById(t)),this._htOption.useSVG&&(z=F),this._android=V(),this._el=t,this._oQRCode=null,this._oDrawing=new z(this._el,this._htOption),this._htOption.text&&this.makeCode(this._htOption.text)},w.prototype.makeCode=function(t){this._oQRCode=new T(ct(t,this._htOption.correctLevel),this._htOption.correctLevel),this._oQRCode.addData(t),this._oQRCode.make(),this._el.title=t,this._oDrawing.draw(this._oQRCode),this.makeImage()},w.prototype.makeImage=function(){typeof this._oDrawing.makeImage=="function"&&(!this._android||this._android>=3)&&this._oDrawing.makeImage()},w.prototype.clear=function(){this._oDrawing.clear()},w.CorrectLevel=v,w})})(ft);var Vt=ft.exports;const Qt=Q({name:"loginScan",setup(){const r=Ct(null),f=()=>{r.value.innerHTML="",new Vt(r.value,{text:"https://qm.qq.com/cgi-bin/qm/qr?k=RdUY97Vx0T0vZ_1OOu-X1yFNkWgDwbjC&jump_from=webapi",width:260,height:260,colorDark:"#000000",colorLight:"#ffffff"})};return lt(()=>{f()}),{qrcodeRef:r}}}),Gt={class:"login-scan-container"},Kt={ref:"qrcodeRef"},Xt={class:"font12 mt20 login-msg"};function Yt(r,f,w,M,T,E){return H(),X("div",Gt,[I("div",Kt,null,512),I("div",Xt,x(r.$t("message.scan.text")),1)])}var Wt=G(Qt,[["render",Yt],["__scopeId","data-v-6d912baa"]]);const Jt=Q({name:"loginIndex",components:{Account:Nt,Mobile:Ut,Scan:Wt},setup(){const r=ut(),{themeConfig:f}=nt(r),w=Y({tabsActiveName:"account",isScan:!1}),M=ot(()=>f.value);return lt(()=>{ht.done()}),U({logoMini:Pt,loginIconTwo:kt,getThemeConfig:M},W(w))}}),zt=r=>(it("data-v-19e68a7e"),r=r(),rt(),r),Zt={class:"login-container"},jt={class:"login-icon-group"},qt={class:"login-icon-group-title"},te=["src"],ee={class:"login-icon-group-title-text font25"},ne=["src"],oe={class:"login-content"},ae={class:"login-content-main"},se={class:"login-content-title ml15"},ie={key:0},re=zt(()=>I("div",{class:"login-content-main-sacn-delta"},null,-1));function le(r,f,w,M,T,E){const v=D("Account"),b=D("el-tab-pane"),c=D("Mobile"),m=D("el-tabs"),p=D("Scan");return H(),X("div",Zt,[I("div",jt,[I("div",qt,[I("img",{src:r.logoMini},null,8,te),I("div",ee,x(r.getThemeConfig.globalViceTitle),1)]),I("img",{src:r.loginIconTwo,class:"login-icon-group-icon"},null,8,ne)]),I("div",oe,[I("div",ae,[I("h4",se,x(r.getThemeConfig.globalTitle)+"\u540E\u53F0\u6A21\u677F",1),r.isScan?q("",!0):(H(),X("div",ie,[d(m,{modelValue:r.tabsActiveName,"onUpdate:modelValue":f[0]||(f[0]=A=>r.tabsActiveName=A)},{default:_(()=>[d(b,{label:r.$t("message.label.one1"),name:"account"},{default:_(()=>[d(v)]),_:1},8,["label"]),d(b,{label:r.$t("message.label.two2"),name:"mobile"},{default:_(()=>[d(c)]),_:1},8,["label"])]),_:1},8,["modelValue"])])),r.isScan?(H(),J(p,{key:1})):q("",!0),I("div",{class:"login-content-main-sacn",onClick:f[1]||(f[1]=A=>r.isScan=!r.isScan)},[I("i",{class:at(["iconfont",r.isScan?"icon-diannao1":"icon-barcode-qr"])},null,2),re])])])])}var ce=G(Jt,[["render",le],["__scopeId","data-v-19e68a7e"]]);export{ce as default};
