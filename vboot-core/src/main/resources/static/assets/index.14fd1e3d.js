var $=Object.defineProperty,S=Object.defineProperties;var T=Object.getOwnPropertyDescriptors;var B=Object.getOwnPropertySymbols;var P=Object.prototype.hasOwnProperty,q=Object.prototype.propertyIsEnumerable;var C=(i,n,t)=>n in i?$(i,n,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[n]=t,D=(i,n)=>{for(var t in n||(n={}))P.call(n,t)&&C(i,t,n[t]);if(B)for(var t of B(n))q.call(n,t)&&C(i,t,n[t]);return i},y=(i,n)=>S(i,T(n));var R=(i,n,t)=>new Promise((g,o)=>{var F=m=>{try{c(t.next(m))}catch(d){o(d)}},_=m=>{try{c(t.throw(m))}catch(d){o(d)}},c=m=>m.done?g(m.value):Promise.resolve(m.value).then(F,_);c((t=t.apply(i,n)).next())});import{P as z}from"./index.4a6d20cf.js";import{u as G,B as H,a as N}from"./index.b3a760e0.js";import{u as W}from"./index.7c24f3ba.js";import{ah as I}from"./index.1f50be75.js";import{O as J}from"./OrgModal.821e8224.js";import{h as L}from"./edit.6fd2a5d5.js";import{A as k,P as V,j as K,a0 as f,B as U,D as Q,w as a,a6 as s,u as e,bu as X,ae as h,J as E,a4 as A,ab as Y,t as Z,r as ee,a1 as te,H as oe}from"./vendor.8e08a5be.js";import{c as ae,a as ne,d as re,e as ue}from"./list.20bccedc.js";/* empty css               *//* empty css                */import"./useWindowSizeFn.2016176c.js";import"./useContentViewHeight.78015564.js";/* empty css               *//* empty css               *//* empty css               *//* empty css              */import"./index.fa31d1d6.js";import"./useContextMenu.6d6ea44d.js";/* empty css               */const le=k({emits:["success","register"],setup(i,{emit:n}){const[t,{openModal:g}]=W(),o=V({loadingText:"",url:"/sys/org/group",data:{},rules:{name:[{required:!0,message:"\u8BF7\u8F93\u5165\u540D\u79F0"}],members:[{required:!0,message:"\u8BF7\u9009\u62E9\u6210\u5458x"}]}}),[F,{setDrawerProps:_,changeLoading:c,closeDrawer:m}]=G(l=>R(this,null,function*(){o.loadingText="\u52A0\u8F7D\u4E2D",c(!0),l.record.id?o.data=yield I.get({url:o.url+"/one/"+l.record.id}):(o.data=l.record,o.data.avtag=!0),c(!1)}));function d(){g(!0,{opener:"members",orgType:10,selectMode:2,orgs:o.data.members?[...Z(o.data.members)]:[]})}const v=K(()=>{let l="";if(o.data.members&&o.data.members.length>0)for(const u of o.data.members)l+=u.name+"\uFF1B";return l});function w(l){l.opener=="members"&&(o.data.members=l.orgs),console.log(l)}return(l,u)=>{const b=f("vxe-input"),x=f("vxe-form-item"),j=f("vxe-textarea"),M=f("vxe-switch"),O=f("vxe-form");return U(),Q(Y,null,[a(e(H),A(l.$attrs,{loadingText:e(o).loadingText,showFooter:"",title:"\u7FA4\u7EC4\u540D\u79F0",width:"70%",onOk:u[1]||(u[1]=r=>e(L)(l.$refs.xForm,e(o),n,e(_),e(c),e(m))),onRegister:e(F)}),{default:s(()=>[a(O,{"title-colon":"",ref:(r,p)=>{p.xForm=r},"title-align":"right","title-width":"100",data:e(o).data,rules:e(o).rules},{default:s(()=>[a(x,{title:"\u7FA4\u7EC4\u540D\u79F0",field:"name","item-render":{},span:"12","title-width":"150"},{default:s(({data:r})=>[a(b,{modelValue:r.name,"onUpdate:modelValue":p=>r.name=p,clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(x,{title:"\u6210\u5458\u5217\u8868",field:"members","item-render":{},span:"24","title-width":"150"},{default:s(({data:r})=>[a(j,{modelValue:e(v),"onUpdate:modelValue":u[0]||(u[0]=p=>X(v)?v.value=p:null),placeholder:"\u8BF7\u9009\u62E9\u6210\u5458",autosize:{minRows:4,maxRows:6},onClick:d,readonly:""},null,8,["modelValue"])]),_:1}),a(x,{title:"\u662F\u5426\u53EF\u7528",field:"avtag","item-render":{},span:"12","title-width":"150"},{default:s(({data:r})=>[a(M,{modelValue:r.avtag,"onUpdate:modelValue":p=>r.avtag=p,"open-label":"\u662F","close-label":"\u5426"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(x,{title:"\u6392\u5E8F\u53F7",field:"ornum","item-render":{},span:"12","title-width":"150"},{default:s(({data:r})=>[a(b,{modelValue:r.ornum,"onUpdate:modelValue":p=>r.ornum=p,type:"number"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(x,{title:"\u7FA4\u7EC4\u5907\u6CE8",field:"notes","item-render":{},span:"24","title-width":"150"},{default:s(({data:r})=>[a(j,{modelValue:r.notes,"onUpdate:modelValue":p=>r.notes=p,placeholder:"\u8BF7\u8F93\u5165\u5907\u6CE8",autosize:{minRows:4,maxRows:6},clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(x,{title:"\u521B\u5EFA\u65F6\u95F4",field:"crtim","item-render":{},span:"12","title-width":"150"},{default:s(({data:r})=>[h(E(r.crtim),1)]),_:1}),a(x,{title:"\u66F4\u65B0\u65F6\u95F4",field:"uptim","item-render":{},span:"12","title-width":"150"},{default:s(({data:r})=>[h(E(r.uptim),1)]),_:1})]),_:1},8,["data","rules"])]),_:1},16,["loadingText","onRegister"]),a(J,{onRegister:e(t),onCloseModal:w},null,8,["onRegister"])],64)}}}),se=h("\u67E5 \u8BE2"),ie=h("\u65B0 \u589E"),de=h("\u5220 \u9664"),me=["onClick"],pe={name:"SysOrgGroup",inheritAttrs:!1};function ce(i){const n=ee({}),t=V({name:""}),g=V(ae("/sys/org/group",{},t,[{type:"checkbox",align:"center",width:42,fixed:"left"},{type:"seq",align:"center",width:50,fixed:"left"},{field:"name",title:"\u7FA4\u7EC4\u540D\u79F0",width:300,fixed:"left",slots:{default:"name_default"}},{field:"notes",title:"\u5907\u6CE8"},{field:"crtim",title:"\u521B\u5EFA\u65F6\u95F4",width:140},{field:"uptim",title:"\u66F4\u65B0\u65F6\u95F4",width:140}])),o=()=>{n.value.commitProxy("query")},[F,{openDrawer:_}]=N();function c(){o()}return(m,d)=>{const v=f("vxe-input"),w=f("vxe-button"),l=f("vxe-grid");return U(),te(e(z),{class:"ztable",contentFullHeight:""},{default:s(()=>[a(l,A({ref:(u,b)=>{b.xGrid=u,n.value=u}},e(g)),{tbtns:s(()=>[a(v,{modelValue:e(t).name,"onUpdate:modelValue":d[0]||(d[0]=u=>e(t).name=u),placeholder:"\u8F93\u5165\u540D\u79F0\u67E5\u8BE2"},null,8,["modelValue"]),a(w,{onClick:o,status:"primary"},{default:s(()=>[se]),_:1}),a(w,{onClick:d[1]||(d[1]=u=>e(ne)({},e(_)))},{default:s(()=>[ie]),_:1}),a(w,{onClick:d[2]||(d[2]=u=>e(re)(m.$refs.xGrid))},{default:s(()=>[de]),_:1})]),name_default:s(({row:u})=>[oe("span",{style:{cursor:"pointer",color:"#3e9ece"},onClick:b=>e(ue)({id:u.id},e(_))},E(u.name),9,me)]),_:1},16),a(le,{onRegister:e(F),onSuccess:c},null,8,["onRegister"])]),_:1})}}const Me=k(y(D({},pe),{setup:ce}));export{Me as default};
