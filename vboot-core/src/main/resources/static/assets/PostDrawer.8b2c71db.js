var V=(w,_,m)=>new Promise((x,e)=>{var p=d=>{try{c(m.next(d))}catch(i){e(i)}},f=d=>{try{c(m.throw(d))}catch(i){e(i)}},c=d=>d.done?x(d.value):Promise.resolve(d.value).then(p,f);c((m=m.apply(w,_)).next())});import{a as T,B as k}from"./index.f06a4b68.js";import{u as S}from"./index.5f1c2524.js";import{ah as N}from"./index.49385d3b.js";import{O}from"./OrgModal.179b5c8d.js";import{h as P}from"./edit.2a1803df.js";import{A as z,P as A,j as h,a0 as g,B as $,D as H,w as a,a5 as r,u as l,bt as j,ad as D,J as C,a4 as q,aa as I,t as E}from"./vendor.a4547d8e.js";/* empty css               */import"./useWindowSizeFn.01f243df.js";/* empty css               *//* empty css               *//* empty css              */import"./Tree.vue_vue&type=style&index=0&lang.156930fd.js";/* empty css               */import"./index.0aef37d2.js";/* empty css               *//* empty css               */import"./useContentViewHeight.7c57433e.js";const se=z({emits:["success","register"],setup(w,{emit:_}){const[m,{openModal:x}]=S(),e=A({loadingText:"",url:"/sys/org/post",data:{},rules:{name:[{required:!0,message:"\u8BF7\u8F93\u5165\u540D\u79F0"}]}}),p=h(()=>{let o="";if(e.data.users&&e.data.users.length>0)for(const n of e.data.users)o+=n.name+"\uFF1B";return o}),f=h(()=>e.data.dept?e.data.dept.name:""),[c,{setDrawerProps:d,changeLoading:i,closeDrawer:y}]=T(o=>V(this,null,function*(){e.loadingText="\u52A0\u8F7D\u4E2D",i(!0),o.record.id?e.data=yield N.get({url:e.url+"/one/"+o.record.id}):(e.data=o.record,e.data.dept&&e.data.dept.id===""&&(e.data.dept=null),e.data.avtag=!0),i(!1)}));function B(){x(!0,{opener:"users",orgType:8,selectMode:2,orgs:e.data.users?[...E(e.data.users)]:[]})}function b(){x(!0,{opener:"dept",orgType:2,selectMode:1,orgs:e.data.dept&&e.data.dept.id?[E(e.data.dept)]:[]})}function R(o){o.opener=="users"?e.data.users=o.orgs:o.opener=="dept"&&(e.data.dept=o.orgs[0])}return(o,n)=>{const F=g("vxe-input"),s=g("vxe-form-item"),U=g("vxe-switch"),v=g("vxe-textarea"),M=g("vxe-form");return $(),H(I,null,[a(l(k),q(o.$attrs,{loadingText:l(e).loadingText,showFooter:"",title:"\u5C97\u4F4D\u4FE1\u606F",width:"70%",onOk:n[2]||(n[2]=t=>l(P)(o.$refs.xForm,l(e),_,l(d),l(i),l(y))),onRegister:l(c)}),{default:r(()=>[a(M,{"title-colon":"",ref:(t,u)=>{u.xForm=t},"title-align":"right","title-width":"100",data:l(e).data,rules:l(e).rules,loading:l(e).loading},{default:r(()=>[a(s,{title:"\u5C97\u4F4D\u540D\u79F0",field:"name","item-render":{},span:"12","title-width":"150"},{default:r(({data:t})=>[a(F,{modelValue:t.name,"onUpdate:modelValue":u=>t.name=u,clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(s,{title:"\u6240\u5C5E\u90E8\u95E8",field:"dept","item-render":{},span:"12","title-width":"150"},{default:r(({data:t})=>[a(F,{modelValue:l(f),"onUpdate:modelValue":n[0]||(n[0]=u=>j(f)?f.value=u:null),"suffix-icon":"fa fa-search",readonly:"",onClick:b},null,8,["modelValue"])]),_:1}),a(s,{title:"\u662F\u5426\u53EF\u7528",field:"avtag","item-render":{},span:"12","title-width":"150"},{default:r(({data:t})=>[a(U,{modelValue:t.avtag,"onUpdate:modelValue":u=>t.avtag=u,"open-label":"\u662F","close-label":"\u5426"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(s,{title:"\u6392\u5E8F\u53F7",field:"ornum","item-render":{},span:"12","title-width":"150"},{default:r(({data:t})=>[a(F,{modelValue:t.ornum,"onUpdate:modelValue":u=>t.ornum=u,type:"number"},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(s,{title:"\u5458\u5DE5\u6210\u5458",field:"users","item-render":{},span:"24","title-width":"150"},{default:r(({data:t})=>[a(v,{modelValue:l(p),"onUpdate:modelValue":n[1]||(n[1]=u=>j(p)?p.value=u:null),placeholder:"\u8BF7\u9009\u62E9\u6210\u5458",autosize:{minRows:4,maxRows:6},onClick:B,readonly:""},null,8,["modelValue"])]),_:1}),a(s,{title:"\u5C97\u4F4D\u5907\u6CE8",field:"notes","item-render":{},span:"24","title-width":"150"},{default:r(({data:t})=>[a(v,{modelValue:t.notes,"onUpdate:modelValue":u=>t.notes=u,placeholder:"\u8BF7\u8F93\u5165\u5907\u6CE8",autosize:{minRows:4,maxRows:6},clearable:""},null,8,["modelValue","onUpdate:modelValue"])]),_:1}),a(s,{title:"\u521B\u5EFA\u65F6\u95F4",field:"crtim","item-render":{},span:"12","title-width":"150"},{default:r(({data:t})=>[D(C(t.crtim),1)]),_:1}),a(s,{title:"\u66F4\u65B0\u65F6\u95F4",field:"uptim","item-render":{},span:"12","title-width":"150"},{default:r(({data:t})=>[D(C(t.uptim),1)]),_:1})]),_:1},8,["data","rules","loading"])]),_:1},16,["loadingText","onRegister"]),a(O,{onRegister:l(m),onCloseModal:R},null,8,["onRegister"])],64)}}});export{se as default};
