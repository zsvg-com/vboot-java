import{B as f}from"./TableImg.6db171de.js";import{T as b}from"./BasicForm.4740433f.js";import{u as j}from"./useTable.407be098.js";import{a as x}from"./system.a8e97c15.js";import{u as h}from"./index.7c24f3ba.js";import{D as g,c as C,s as _}from"./DeptModal.4da4ccd4.js";import{_ as T}from"./index.1f50be75.js";import{A as D,a0 as t,B,D as E,w as n,a6 as r,ae as M}from"./vendor.8e08a5be.js";/* empty css                *//* empty css              */import"./useForm.6f5945bb.js";import"./index.4a6d20cf.js";/* empty css               *//* empty css                */import"./useWindowSizeFn.2016176c.js";import"./useContentViewHeight.78015564.js";/* empty css                *//* empty css                *//* empty css               */import"./uuid.2b29000c.js";/* empty css               */import"./useSortable.4b691ef1.js";/* empty css                *//* empty css                *//* empty css               *//* empty css               */import"./index.4765eeb8.js";/* empty css                *//* empty css                *//* empty css                *//* empty css                */import"./download.1020b52a.js";import"./base64Conver.08b9f4ec.js";import"./index.b2563f35.js";const S=D({name:"DeptManagement",components:{BasicTable:f,DeptModal:g,TableAction:b},setup(){const[e,{openModal:i}]=h(),[d,{reload:l}]=j({title:"\u90E8\u95E8\u5217\u8868",api:x,columns:C,formConfig:{labelWidth:120,schemas:_},pagination:!1,striped:!1,useSearchForm:!0,showTableSetting:!0,bordered:!0,showIndexColumn:!1,canResize:!1,actionColumn:{width:80,title:"\u64CD\u4F5C",dataIndex:"action",slots:{customRender:"action"},fixed:void 0}});function m(){i(!0,{isUpdate:!1})}function c(o){i(!0,{record:o,isUpdate:!0})}function s(o){console.log(o)}function a(){l()}return{registerTable:d,registerModal:e,handleCreate:m,handleEdit:c,handleDelete:s,handleSuccess:a}}}),w=M(" \u65B0\u589E\u90E8\u95E8 ");function F(e,i,d,l,m,c){const s=t("a-button"),a=t("TableAction"),o=t("BasicTable"),u=t("DeptModal");return B(),E("div",null,[n(o,{onRegister:e.registerTable},{toolbar:r(()=>[n(s,{type:"primary",onClick:e.handleCreate},{default:r(()=>[w]),_:1},8,["onClick"])]),action:r(({record:p})=>[n(a,{actions:[{icon:"clarity:note-edit-line",onClick:e.handleEdit.bind(null,p)},{icon:"ant-design:delete-outlined",color:"error",popConfirm:{title:"\u662F\u5426\u786E\u8BA4\u5220\u9664",confirm:e.handleDelete.bind(null,p)}}]},null,8,["actions"])]),_:1},8,["onRegister"]),n(u,{onRegister:e.registerModal,onSuccess:e.handleSuccess},null,8,["onRegister","onSuccess"])])}var me=T(S,[["render",F]]);export{me as default};
