import{I as _}from"./index.5f918564.js";import{B as b}from"./TableImg.ae08a7e1.js";import"./BasicForm.84475e4b.js";import{P as B}from"./index.0248b5d5.js";import{_ as S}from"./index.6733effe.js";import{A as h,r as F,a0 as s,B as i,a1 as l,a6 as c,w as f,D as g,ap as C,ab as E,ae as I}from"./vendor.af2fe88b.js";import"./index.df186f99.js";import"./useWindowSizeFn.4124f9cf.js";import"./useForm.da4c94f2.js";/* empty css                *//* empty css              *//* empty css                *//* empty css                *//* empty css               */import"./uuid.2b29000c.js";/* empty css               */import"./useSortable.8f85f589.js";/* empty css                *//* empty css                *//* empty css               *//* empty css               */import"./index.f5b97fd5.js";/* empty css                *//* empty css                *//* empty css                *//* empty css                */import"./download.891c7c9b.js";import"./base64Conver.08b9f4ec.js";import"./index.6073de15.js";/* empty css               *//* empty css                */import"./useContentViewHeight.b4e0fa30.js";const v=h({components:{BasicTable:b,ImpExcel:_,PageWrapper:B},setup(){const e=F([]);function m(a){e.value=[],console.log(a);for(const p of a){const{header:d,results:u,meta:{sheetName:r}}=p,t=[];for(const o of d)t.push({title:o,dataIndex:o});e.value.push({title:r,dataSource:u,columns:t})}}return{loadDataSuccess:m,tableListRef:e}}}),w=I(" \u5BFC\u5165Excel ");function D(e,m,a,p,d,u){const r=s("a-button"),t=s("ImpExcel"),o=s("BasicTable"),j=s("PageWrapper");return i(),l(j,{title:"excel\u6570\u636E\u5BFC\u5165\u793A\u4F8B"},{default:c(()=>[f(t,{onSuccess:e.loadDataSuccess,dateFormat:"YYYY-MM-DD"},{default:c(()=>[f(r,{class:"m-3"},{default:c(()=>[w]),_:1})]),_:1},8,["onSuccess"]),(i(!0),g(E,null,C(e.tableListRef,(n,x)=>(i(),l(o,{key:x,title:n.title,columns:n.columns,dataSource:n.dataSource},null,8,["title","columns","dataSource"]))),128))]),_:1})}var ce=S(v,[["render",D]]);export{ce as default};
