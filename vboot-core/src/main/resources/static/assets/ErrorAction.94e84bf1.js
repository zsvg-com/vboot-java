import{A as l,a9 as m,ce as d,a as _,j as f,a0 as e,B as g,a1 as E,a6 as a,w as c}from"./vendor.8e08a5be.js";/* empty css                */import{_ as C,I as L,av as h,b as B,P as b}from"./index.1f50be75.js";const v=l({name:"ErrorAction",components:{Icon:L,Tooltip:m,Badge:d},setup(){const{t:o}=B(),{push:n}=_(),t=h(),r=f(()=>t.getErrorLogListCount);function s(){n(b.ERROR_LOG_PAGE).then(()=>{t.setErrorLogListCount(0)})}return{t:o,getCount:r,handleToErrorList:s}}});function I(o,n,t,r,s,T){const i=e("Icon"),u=e("Badge"),p=e("Tooltip");return g(),E(p,{title:o.t("layout.header.tooltipErrorLog"),placement:"bottom",mouseEnterDelay:.5,onClick:o.handleToErrorList},{default:a(()=>[c(u,{count:o.getCount,offset:[0,10],overflowCount:99},{default:a(()=>[c(i,{icon:"ion:bug-outline"})]),_:1},8,["count"])]),_:1},8,["title","mouseEnterDelay","onClick"])}var R=C(v,[["render",I]]);export{R as default};
