var O=Object.defineProperty,z=Object.defineProperties;var H=Object.getOwnPropertyDescriptors;var $=Object.getOwnPropertySymbols;var R=Object.prototype.hasOwnProperty,q=Object.prototype.propertyIsEnumerable;var k=(d,l,o)=>l in d?O(d,l,{enumerable:!0,configurable:!0,writable:!0,value:o}):d[l]=o,y=(d,l)=>{for(var o in l||(l={}))R.call(l,o)&&k(d,o,l[o]);if($)for(var o of $(l))q.call(l,o)&&k(d,o,l[o]);return d},j=(d,l)=>z(d,H(l));var C=(d,l,o)=>new Promise((e,b)=>{var w=f=>{try{h(o.next(f))}catch(x){b(x)}},p=f=>{try{h(o.throw(f))}catch(x){b(x)}},h=f=>f.done?e(f.value):Promise.resolve(f.value).then(w,p);h((o=o.apply(d,l)).next())});import{P as M}from"./index.0248b5d5.js";import{A,r as B,P as S,a0 as g,B as U,D as W,w as t,a6 as s,u as a,a4 as I,ab as J,bG as V,_ as K,$ as N,a1 as G,H as L,J as X,bH as Q,bI as Y,bJ as Z,ae as T,bA as ee}from"./vendor.af2fe88b.js";import{u as te,B as ae,a as ne}from"./index.22b4830b.js";import{u as oe}from"./index.df186f99.js";import{ah as E}from"./index.6733effe.js";import{h as le}from"./edit.e0eb6616.js";import{_ as re}from"./TreeModal.720af3a3.js";import{a as P,e as ie}from"./list.fd3e66c9.js";/* empty css               *//* empty css                */import"./useWindowSizeFn.4124f9cf.js";import"./useContentViewHeight.b4e0fa30.js";/* empty css               */const ue=A({emits:["success","register"],setup(d,{emit:l}){oe();const o=B({}),e=S({loadingText:"",url:"/wf/tem/cate",data:{},rules:{name:[{required:!0,message:"\u8BF7\u8F93\u5165\u540D\u79F0"}],type:[{required:!0,message:"\u8BF7\u9009\u62E9\u7C7B\u578B"}]}}),[b,{setDrawerProps:w,changeLoading:p,closeDrawer:h}]=te(u=>C(this,null,function*(){e.loadingText="\u52A0\u8F7D\u4E2D",p(!0),u.record.id?(e.data=yield E.get({url:e.url+"/one/"+u.record.id}),e.data.parent!=null&&(e.data.pname=e.data.parent.name)):(e.data=u.record,e.data.avtag=!0,u.record.pid!=null&&(e.data.parent={id:u.record.pid,name:u.record.pname})),p(!1)}));function f(){o.value.open({url:"/wf/tem/cate/tree"})}function x(u){e.data.parent=u,u!=null?(e.data.pname=u.name,e.data.pid=u.id):(e.data.pname=null,e.data.pid=null)}return(u,m)=>{const i=g("vxe-input"),v=g("vxe-form-item"),_=g("vxe-switch"),D=g("vxe-form");return U(),W(J,null,[t(a(ae),I(u.$attrs,{loadingText:a(e).loadingText,showFooter:"",title:"\u5206\u7C7B\u540D\u79F0",width:"70%",onOk:m[0]||(m[0]=n=>a(le)(u.$refs.xForm,a(e),l,a(w),a(p),a(h))),onRegister:a(b)}),{default:s(()=>[t(D,{"title-colon":"",ref:(n,c)=>{c.xForm=n},"title-align":"right","title-width":"100",data:a(e).data,rules:a(e).rules},{default:s(()=>[t(v,{title:"\u5206\u7C7B\u540D\u79F0",field:"name","item-render":{},span:"12","title-width":"150"},{default:s(({data:n})=>[t(i,{modelValue:n.name,"onUpdate:modelValue":c=>n.name=c,clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),t(v,{title:"\u4E0A\u7EA7\u5206\u7C7B",field:"pname","item-render":{},span:"12","title-width":"150"},{default:s(({data:n})=>[t(i,{modelValue:n.pname,"onUpdate:modelValue":c=>n.pname=c,"suffix-icon":"vxe-icon--search",readonly:"",onClick:f},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),t(v,{title:"\u6392\u5E8F\u53F7",field:"ornum","item-render":{},span:"12","title-width":"150"},{default:s(({data:n})=>[t(i,{modelValue:n.ornum,"onUpdate:modelValue":c=>n.ornum=c,placeholder:"\u6570\u503C\u7C7B\u578B",type:"number"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),t(v,{title:"\u662F\u5426\u542F\u7528",field:"avtag","item-render":{},span:"12","title-width":"150"},{default:s(({data:n})=>[t(_,{modelValue:n.avtag,"onUpdate:modelValue":c=>n.avtag=c,"open-label":"\u662F","close-label":"\u5426"},null,8,["modelValue","onUpdate:modelValue"])]),_:1})]),_:1},8,["data","rules"])]),_:1},16,["loadingText","onRegister"]),t(re,{ref:(n,c)=>{c.xTree=n,o.value=n},onSelect:x},null,512)],64)}}}),se=T("\u67E5 \u8BE2"),de=T("\u65B0 \u589E"),me=T("\u5C55 \u5F00"),ce=T("\u6298 \u53E0"),fe=["onClick"],pe={name:"WfTemCate",inheritAttrs:!1,customOptions:{}};function xe(d){const l=B({}),o=B({}),e=S({filterName:"",loading:!1,originData:[],tableData:[],formData:{name:""}}),b=()=>{const m=V.toValueString(e.filterName).trim();if(m){const i={children:"children"},v=["name"];e.tableData=V.searchTree(e.originData,_=>v.some(D=>V.toValueString(_[D]).indexOf(m)>-1),i),N(()=>{l.value.setAllTreeExpand(!0)})}else e.tableData=e.originData},w=V.debounce(function(){b()},500,{leading:!1,trailing:!0});function p(){return C(this,null,function*(){e.originData=yield E.get({url:"/wf/tem/cate"}),b()})}K(()=>C(this,null,function*(){yield p(),N(()=>{const m=l.value,i=o.value;m.connect(i)})}));function h(m){return C(this,null,function*(){(yield ee.modal.confirm("\u60A8\u786E\u5B9A\u8981\u5220\u9664\u5417\uFF1F"))==="confirm"&&(yield E.delete({url:"/wf/tem/cate/"+m}),yield p())})}const[f,{openDrawer:x}]=ne();function u(){p()}return(m,i)=>{const v=g("vxe-input"),_=g("vxe-button"),D=g("vxe-toolbar"),n=g("vxe-column"),c=g("vxe-table");return U(),G(a(M),{class:"ztable",contentFullHeight:""},{default:s(()=>[t(D,{ref:(r,F)=>{F.xToolbar=r,o.value=r},custom:""},{buttons:s(()=>[t(v,{modelValue:a(e).filterName,"onUpdate:modelValue":i[0]||(i[0]=r=>a(e).filterName=r),placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0",onKeyup:a(w)},null,8,["modelValue","onKeyup"]),t(_,{status:"primary"},{default:s(()=>[se]),_:1}),t(_,{onClick:i[1]||(i[1]=r=>a(P)({},a(x)))},{default:s(()=>[de]),_:1}),t(_,{onClick:i[2]||(i[2]=r=>m.$refs.xTree.setAllTreeExpand(!0))},{default:s(()=>[me]),_:1}),t(_,{onClick:i[3]||(i[3]=r=>m.$refs.xTree.clearTreeExpand())},{default:s(()=>[ce]),_:1})]),_:1},512),t(c,{ref:(r,F)=>{F.xTree=r,l.value=r},"max-height":"600",loading:a(e).loading,data:a(e).tableData,"tree-config":{children:"children"}},{default:s(()=>[t(n,{field:"name",title:"\u540D\u79F0","tree-node":""},{default:s(({row:r})=>[L("span",{style:{cursor:"pointer",color:"#3e9ece"},onClick:F=>m.$refs.xTree.toggleTreeExpand(r)},X(r.name),9,fe)]),_:1}),t(n,{field:"crtim",title:"\u521B\u5EFA\u65F6\u95F4",width:"260"}),t(n,{field:"uptim",title:"\u66F4\u65B0\u65F6\u95F4",width:"260"}),t(n,{title:"\u64CD\u4F5C",width:"100","show-overflow":"","header-align":"center",align:"right"},{default:s(({row:r})=>[t(a(Q),{onClick:F=>a(P)({pid:r.id,pname:r.name},a(x)),style:{"font-size":"15px","margin-left":"10px",color:"#52C41A"}},null,8,["onClick"]),t(a(Y),{onClick:F=>a(ie)({id:r.id},a(x)),style:{"font-size":"15px","margin-left":"11px",color:"#2874C5"}},null,8,["onClick"]),t(a(Z),{onClick:F=>h(r.id),style:{"font-size":"15px","margin-left":"10px",color:"#ED6F6F"}},null,8,["onClick"])]),_:1})]),_:1},8,["loading","data"]),t(ue,{onRegister:a(f),onSuccess:u},null,8,["onRegister"])]),_:1})}}const ke=A(j(y({},pe),{setup:xe}));export{ke as default};
