import{B as f}from"./TableImg.6db171de.js";import{T as b}from"./BasicForm.4740433f.js";import{u as j}from"./useTable.407be098.js";import{c as x}from"./system.a8e97c15.js";import{a as h}from"./index.b3a760e0.js";import{M as C,c as g,s as T}from"./MenuDrawer.9a934daa.js";import{_ as w}from"./index.1f50be75.js";import{A as _,a0 as n,B as D,D as S,w as t,a6 as d,$ as F,ae as B}from"./vendor.8e08a5be.js";/* empty css                *//* empty css              */import"./useForm.6f5945bb.js";import"./index.4a6d20cf.js";/* empty css               *//* empty css                */import"./useWindowSizeFn.2016176c.js";import"./useContentViewHeight.78015564.js";/* empty css                *//* empty css                *//* empty css               */import"./uuid.2b29000c.js";import"./index.7c24f3ba.js";/* empty css               */import"./useSortable.4b691ef1.js";/* empty css                *//* empty css                *//* empty css               *//* empty css               */import"./index.4765eeb8.js";/* empty css                *//* empty css                *//* empty css                *//* empty css                */import"./download.1020b52a.js";import"./base64Conver.08b9f4ec.js";import"./index.b2563f35.js";/* empty css               */const M=_({name:"MenuManagement",components:{BasicTable:f,MenuDrawer:C,TableAction:b},setup(){const[e,{openDrawer:s}]=h(),[u,{reload:m,expandAll:l}]=j({title:"\u83DC\u5355\u5217\u8868",api:x,columns:g,formConfig:{labelWidth:120,schemas:T},isTreeTable:!0,pagination:!1,striped:!1,useSearchForm:!0,showTableSetting:!0,bordered:!0,showIndexColumn:!1,canResize:!1,actionColumn:{width:80,title:"\u64CD\u4F5C",dataIndex:"action",slots:{customRender:"action"},fixed:void 0}});function p(){s(!0,{isUpdate:!1})}function i(o){s(!0,{record:o,isUpdate:!0})}function r(o){console.log(o)}function a(){m()}function c(){F(l)}return{registerTable:u,registerDrawer:e,handleCreate:p,handleEdit:i,handleDelete:r,handleSuccess:a,onFetchSuccess:c}}}),k=B(" \u65B0\u589E\u83DC\u5355 ");function v(e,s,u,m,l,p){const i=n("a-button"),r=n("TableAction"),a=n("BasicTable"),c=n("MenuDrawer");return D(),S("div",null,[t(a,{onRegister:e.registerTable,onFetchSuccess:e.onFetchSuccess},{toolbar:d(()=>[t(i,{type:"primary",onClick:e.handleCreate},{default:d(()=>[k]),_:1},8,["onClick"])]),action:d(({record:o})=>[t(r,{actions:[{icon:"clarity:note-edit-line",onClick:e.handleEdit.bind(null,o)},{icon:"ant-design:delete-outlined",color:"error",popConfirm:{title:"\u662F\u5426\u786E\u8BA4\u5220\u9664",confirm:e.handleDelete.bind(null,o)}}]},null,8,["actions"])]),_:1},8,["onRegister","onFetchSuccess"]),t(c,{onRegister:e.registerDrawer,onSuccess:e.handleSuccess},null,8,["onRegister","onSuccess"])])}var pe=w(M,[["render",v]]);export{pe as default};