var K=Object.defineProperty,P=Object.defineProperties;var X=Object.getOwnPropertyDescriptors;var k=Object.getOwnPropertySymbols;var Y=Object.prototype.hasOwnProperty,Z=Object.prototype.propertyIsEnumerable;var E=(p,n,d)=>n in p?K(p,n,{enumerable:!0,configurable:!0,writable:!0,value:d}):p[n]=d,B=(p,n)=>{for(var d in n||(n={}))Y.call(n,d)&&E(p,d,n[d]);if(k)for(var d of k(n))Z.call(n,d)&&E(p,d,n[d]);return p},z=(p,n)=>P(p,X(n));import{a as $,aB as G,j as F,a3 as L,ag as Q,m as W,B as ee,R as te,S as t,a2 as _,o as le,Z as e,u as a,e as r,J as oe,T as ae,U as se,Y as g,X as h,l as ne,ap as ue}from"./vue.1653003640069.js";import{e as de,a as re,t as ie}from"./edit.16530036400692.js";import{O as _e}from"./OrgModal.1653003640069.js";import{s as pe}from"./index.1653003640069.js";import"./DeptTree.1653003640069.js";const me=r("div",{style:{"line-height":"32px"}},"\u89D2\u8272\u4FE1\u606F",-1),ce=h("\u4FDD \u5B58"),fe=h("\u5173 \u95ED"),ve={style:{"margin-top":"8px","margin-bottom":"8px"}},Fe={class:"zinput"},ge={class:"zinput",style:{height:"auto"}},he={class:"zinput"},ye={class:"zinput"},be={class:"zinput",style:{height:"auto"}},Ae={class:"zinput"},xe={class:"zinput"},Ve={class:"zinput"},we={class:"zinput"},Ce={style:{"margin-bottom":"10px"}},De=h("\u5C55 \u5F00"),ke=h("\u6298 \u53E0"),Ee={style:{border:"1px solid #eee"}},Be={name:"sysPermRole"},Se=$(z(B({},Be),{setup(p){const n=G(),d=F(),{proxy:x}=ne(),V=F("tab1"),y=L({url:"/sys/perm/role",params:{path:"",query:""},form:{avtag:!0,menus:[]},treeData:[]}),{form:l,treeData:w}=Q(y);W(async()=>{await de(y,n),await S()});const N=()=>{for(let s=0;s<c.value.store._getAllNodes().length;s++)c.value.store._getAllNodes()[s].expanded=!0},R=()=>{for(let s=0;s<c.value.store._getAllNodes().length;s++)c.value.store._getAllNodes()[s].expanded=!1},c=F(),M=async()=>{const s=c.value.getCheckedNodes(),o=[];for(const u of s)o.push({id:u.id,name:u.name});l.value.menus=o,await ie(d.value,y,x,n)},C=F(),U=()=>{C.value.openModal({orgType:10,selectMode:2,orgs:ue(l.value.orgs)})},O=s=>{l.value.orgs=s.orgs},b=ee(()=>{let s="";if(l.value.orgs&&l.value.orgs.length>0)for(const o of l.value.orgs)s+=o.name+"\uFF1B";return s});async function S(){w.value=await pe({url:"/sys/perm/menu/tree",method:"get"});const s=[];for(const o of l.value.menus)s.push(o.id);c.value.setCheckedKeys(s)}return(s,o)=>{const u=_("el-col"),v=_("el-button"),f=_("el-row"),A=_("el-input"),m=_("el-form-item"),I=_("el-input-number"),T=_("el-switch"),D=_("el-tab-pane"),q=_("el-tree"),j=_("el-tabs"),H=_("el-form"),J=_("el-card");return le(),te(J,{class:"box-card","body-style":{padding:"2px 8px"},shadow:"never"},{header:t(()=>[e(f,null,{default:t(()=>[e(u,{span:10},{default:t(()=>[me]),_:1}),e(u,{span:14,style:{"text-align":"right"}},{default:t(()=>[e(v,{type:"success",onClick:M,plain:""},{default:t(()=>[ce]),_:1}),e(v,{type:"info",onClick:o[0]||(o[0]=i=>a(re)(a(x),a(n))),plain:""},{default:t(()=>[fe]),_:1})]),_:1})]),_:1})]),default:t(()=>[r("div",ve,[e(H,{ref_key:"formRef",ref:d,class:"zform",model:a(l),"label-width":"140px"},{default:t(()=>[e(j,{type:"card",modelValue:V.value,"onUpdate:modelValue":o[6]||(o[6]=i=>V.value=i)},{default:t(()=>[e(D,{label:"\u57FA\u672C\u4FE1\u606F",name:"tab1"},{default:t(()=>[e(f,{style:{"border-top":"1px solid #d2d2d2"}},{default:t(()=>[e(u,{span:24},{default:t(()=>[e(m,{label:"\u89D2\u8272\u540D\u79F0\uFF1A",prop:"name",rules:[{required:!0,message:"\u540D\u79F0\u4E0D\u80FD\u4E3A\u7A7A"}]},{default:t(()=>[r("div",Fe,[e(A,{modelValue:a(l).name,"onUpdate:modelValue":o[1]||(o[1]=i=>a(l).name=i)},null,8,["modelValue"])])]),_:1})]),_:1})]),_:1}),e(f,null,{default:t(()=>[e(u,{span:24},{default:t(()=>[e(m,{label:"\u6307\u6D3E\u7528\u6237\uFF1A"},{default:t(()=>[r("div",ge,[e(A,{type:"textarea",rows:4,modelValue:a(b),"onUpdate:modelValue":o[2]||(o[2]=i=>oe(b)?b.value=i:null),readonly:"",onClick:U},null,8,["modelValue"])])]),_:1})]),_:1})]),_:1}),e(f,null,{default:t(()=>[e(u,{span:12},{default:t(()=>[e(m,{label:"\u6392\u5E8F\u53F7\uFF1A"},{default:t(()=>[r("div",he,[e(I,{modelValue:a(l).ornum,"onUpdate:modelValue":o[3]||(o[3]=i=>a(l).ornum=i),"controls-position":"right",style:{width:"100%"}},null,8,["modelValue"])])]),_:1})]),_:1}),e(u,{span:12},{default:t(()=>[e(m,{label:"\u662F\u5426\u53EF\u7528\uFF1A"},{default:t(()=>[r("div",ye,[e(T,{modelValue:a(l).avtag,"onUpdate:modelValue":o[4]||(o[4]=i=>a(l).avtag=i)},null,8,["modelValue"])])]),_:1})]),_:1})]),_:1}),e(f,null,{default:t(()=>[e(u,{span:24},{default:t(()=>[e(m,{label:"\u5907\u6CE8\uFF1A"},{default:t(()=>[r("div",be,[e(A,{style:{"font-family":"'Courier New', Helvetica, Arial, sans-serif","font-size":"16px"},type:"textarea",rows:4,modelValue:a(l).notes,"onUpdate:modelValue":o[5]||(o[5]=i=>a(l).notes=i)},null,8,["modelValue"])])]),_:1})]),_:1})]),_:1}),ae(e(f,null,{default:t(()=>[e(u,{span:6},{default:t(()=>[e(m,{label:"\u521B\u5EFA\u4EBA\uFF1A"},{default:t(()=>[r("div",Ae,g(a(l).crman?a(l).crman.name:""),1)]),_:1})]),_:1}),e(u,{span:6},{default:t(()=>[e(m,{label:"\u521B\u5EFA\u65F6\u95F4\uFF1A"},{default:t(()=>[r("div",xe,g(a(l).crtim),1)]),_:1})]),_:1}),e(u,{span:6},{default:t(()=>[e(m,{label:"\u66F4\u65B0\u4EBA\uFF1A"},{default:t(()=>[r("div",Ve,g(a(l).upman?a(l).upman.name:""),1)]),_:1})]),_:1}),e(u,{span:6},{default:t(()=>[e(m,{label:"\u66F4\u65B0\u65F6\u95F4\uFF1A"},{default:t(()=>[r("div",we,g(a(l).uptim),1)]),_:1})]),_:1})]),_:1},512),[[se,a(l).crtim]])]),_:1}),e(D,{label:"\u83DC\u5355\u5206\u914D",name:"tab3"},{default:t(()=>[r("div",Ce,[e(v,{onClick:N,plain:""},{default:t(()=>[De]),_:1}),e(v,{onClick:R,plain:""},{default:t(()=>[ke]),_:1})]),r("div",Ee,[e(q,{ref_key:"treeRef",ref:c,data:a(w),"show-checkbox":"","node-key":"id",props:{children:"children",label:"name"}},null,8,["data"])])]),_:1})]),_:1},8,["modelValue"])]),_:1},8,["model"])]),e(_e,{ref_key:"orgModal",ref:C,onClose:O},null,512)]),_:1})}}}));export{Se as default};
