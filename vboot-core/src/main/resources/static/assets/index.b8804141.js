import{_ as x,F as _,Q as T,bd as b,a as L,Y as j}from"./index.6733effe.js";import{A as k,cW as F,j as l,u as o,a0 as s,B as n,D as w,w as B,a1 as m,ad as p,K as v,ab as y}from"./vendor.af2fe88b.js";import{c as S,u as C}from"./index.96ddb116.js";import D from"./SessionTimeoutLogin.d95f6715.js";import"./index.3e8be6a8.js";import"./useWindowSizeFn.4124f9cf.js";import"./useContentViewHeight.b4e0fa30.js";/* empty css               *//* empty css               */import"./useSortable.8f85f589.js";import"./lock.8b63196e.js";import"./Login.a30e29b3.js";import"./LoginForm.b2a0ef49.js";/* empty css              *//* empty css               *//* empty css               *//* empty css               */import"./LoginFormTitle.0295dfea.js";import"./ForgetPasswordForm.75234210.js";import"./index.f5b97fd5.js";import"./RegisterForm.978a7367.js";import"./index.6073de15.js";import"./MobileForm.ccdad618.js";import"./QrCodeForm.fd66f5ae.js";import"./index.a849f79a.js";import"./download.891c7c9b.js";import"./base64Conver.08b9f4ec.js";const P=k({name:"LayoutFeatures",components:{BackTop:F,LayoutLockPage:S(()=>_(()=>import("./index.17d3e766.js"),["assets/index.17d3e766.js","assets/vendor.af2fe88b.js","assets/vendor.7659cf59.css","assets/LockPage.6a2a6b7b.js","assets/LockPage.2bff9ea2.css","assets/index.6733effe.js","assets/index.1bc5817c.css","assets/lock.8b63196e.js","assets/header.d801b988.js"])),SettingDrawer:S(()=>_(()=>import("./index.ba5c05fe.js").then(function(e){return e.i}),["assets/index.ba5c05fe.js","assets/index.17eb4c41.css","assets/index.22b4830b.js","assets/index.5c7227e9.css","assets/index.00780982.css","assets/index.6733effe.js","assets/index.1bc5817c.css","assets/vendor.af2fe88b.js","assets/vendor.7659cf59.css","assets/index.96ddb116.js","assets/index.e9934e3f.css","assets/index.729da5f2.css","assets/index.3e8be6a8.js","assets/index.0935299d.css","assets/useWindowSizeFn.4124f9cf.js","assets/useContentViewHeight.b4e0fa30.js","assets/useSortable.8f85f589.js","assets/lock.8b63196e.js"])),SessionTimeoutLogin:D},setup(){const{getUseOpenBackTop:e,getShowSettingButton:d,getSettingButtonPosition:u,getFullContent:c}=T(),g=b(),{prefixCls:f}=L("setting-drawer-fearure"),{getShowHeader:i}=C(),r=l(()=>g.getSessionTimeout),a=l(()=>{if(!o(d))return!1;const t=o(u);return t===j.AUTO?!o(i)||o(c):t===j.FIXED});return{getTarget:()=>document.body,getUseOpenBackTop:e,getIsFixedSettingDrawer:a,prefixCls:f,getIsSessionTimeout:r}}});function E(e,d,u,c,g,f){const i=s("LayoutLockPage"),r=s("BackTop"),a=s("SettingDrawer"),t=s("SessionTimeoutLogin");return n(),w(y,null,[B(i),e.getUseOpenBackTop?(n(),m(r,{key:0,target:e.getTarget},null,8,["target"])):p("",!0),e.getIsFixedSettingDrawer?(n(),m(a,{key:1,class:v(e.prefixCls)},null,8,["class"])):p("",!0),e.getIsSessionTimeout?(n(),m(t,{key:2})):p("",!0)],64)}var re=x(P,[["render",E]]);export{re as default};
