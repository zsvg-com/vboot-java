var V=(x,c,t)=>new Promise((f,p)=>{var i=u=>{try{l(t.next(u))}catch(n){p(n)}},_=u=>{try{l(t.throw(u))}catch(n){p(n)}},l=u=>u.done?f(u.value):Promise.resolve(u.value).then(i,_);l((t=t.apply(x,c)).next())});import{a as v,B as h}from"./index.f06a4b68.js";import{ah as B}from"./index.49385d3b.js";import{h as b}from"./edit.2a1803df.js";import{A as j,P as D,a0 as m,B as U,a1 as E,a5 as d,w as o,u as r,a4 as A}from"./vendor.a4547d8e.js";/* empty css               */const $=j({emits:["success","register"],setup(x,{emit:c}){const t=D({loadingText:"",url:"/sys/job/main",data:{},rules:{name:[{required:!0,message:"\u8BF7\u8F93\u5165\u540D\u79F0"}]}}),[f,{setDrawerProps:p,changeLoading:i,closeDrawer:_}]=v(l=>V(this,null,function*(){t.loadingText="\u52A0\u8F7D\u4E2D",i(!0),l.record.id?t.data=yield B.get({url:t.url+"/one/"+l.record.id}):(t.data=l.record,t.data.avtag=!0),i(!1)}));return(l,u)=>{const n=m("vxe-input"),s=m("vxe-form-item"),g=m("vxe-switch"),w=m("vxe-textarea"),F=m("vxe-form");return U(),E(r(h),A(l.$attrs,{loadingText:r(t).loadingText,showFooter:"",title:"\u5B9A\u65F6\u4EFB\u52A1",width:"70%",onOk:u[0]||(u[0]=e=>r(b)(l.$refs.xForm,r(t),c,r(p),r(i),r(_))),onRegister:r(f)}),{default:d(()=>[o(F,{"title-colon":"",ref:(e,a)=>{a.xForm=e},"title-align":"right","title-width":"100",data:r(t).data,rules:r(t).rules},{default:d(()=>[o(s,{title:"\u4EFB\u52A1\u540D\u79F0",field:"name","item-render":{},span:"12","title-width":"150"},{default:d(({data:e})=>[o(n,{modelValue:e.name,"onUpdate:modelValue":a=>e.name=a,clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),o(s,{title:"\u4EFB\u52A1\u65B9\u6CD5",field:"code","item-render":{},span:"12","title-width":"150"},{default:d(({data:e})=>[o(n,{modelValue:e.jid,"onUpdate:modelValue":a=>e.jid=a,clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),o(s,{title:"\u4EFB\u52A1\u7C7B",field:"avtag","item-render":{},span:"12","title-width":"150"},{default:d(({data:e})=>[o(g,{modelValue:e.jgroup,"onUpdate:modelValue":a=>e.jgroup=a,"open-label":"\u662F","close-label":"\u5426"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),o(s,{title:"\u6392\u5E8F\u53F7",field:"ornum","item-render":{},span:"12","title-width":"150"},{default:d(({data:e})=>[o(n,{modelValue:e.ornum,"onUpdate:modelValue":a=>e.ornum=a,type:"number"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),o(s,{title:"\u4EFB\u52A1\u5907\u6CE8",field:"notes","item-render":{},span:"24","title-width":"150"},{default:d(({data:e})=>[o(w,{modelValue:e.notes,"onUpdate:modelValue":a=>e.notes=a,placeholder:"\u8BF7\u8F93\u5165\u5907\u6CE8",autosize:{minRows:4,maxRows:6},clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1})]),_:1},8,["data","rules"])]),_:1},16,["loadingText","onRegister"])}}});export{$ as default};
