var m=(e,d,o)=>new Promise((r,a)=>{var s=t=>{try{n(o.next(t))}catch(i){a(i)}},u=t=>{try{n(o.throw(t))}catch(i){a(i)}},n=t=>t.done?r(t.value):Promise.resolve(t.value).then(s,u);n((o=o.apply(e,d)).next())});import{B as l}from"./TableImg.6db171de.js";import"./BasicForm.4740433f.js";import{u as p}from"./useTable.407be098.js";import{o as c,t as f}from"./tree.83a603ba.js";import{d as b}from"./table.54c6f344.js";import{_ as F,h as C}from"./index.1f50be75.js";import{A as x,a0 as j,B as E,D as B,w as D}from"./vendor.8e08a5be.js";/* empty css                *//* empty css              */import"./useForm.6f5945bb.js";import"./index.4a6d20cf.js";/* empty css               *//* empty css                */import"./useWindowSizeFn.2016176c.js";import"./useContentViewHeight.78015564.js";/* empty css                *//* empty css                *//* empty css               */import"./uuid.2b29000c.js";import"./index.7c24f3ba.js";/* empty css               */import"./useSortable.4b691ef1.js";/* empty css                *//* empty css                *//* empty css               *//* empty css               */import"./index.4765eeb8.js";/* empty css                *//* empty css                *//* empty css                *//* empty css                */import"./download.1020b52a.js";import"./base64Conver.08b9f4ec.js";import"./index.b2563f35.js";const h=[{title:"\u8F93\u5165\u6846",dataIndex:"name",edit:!0,editComponentProps:{prefix:"$"},width:200},{title:"\u9ED8\u8BA4\u8F93\u5165\u72B6\u6001",dataIndex:"name7",edit:!0,editable:!0,width:200},{title:"\u8F93\u5165\u6846\u6821\u9A8C",dataIndex:"name1",edit:!0,editRule:!0,width:200},{title:"\u8F93\u5165\u6846\u51FD\u6570\u6821\u9A8C",dataIndex:"name2",edit:!0,editRule:e=>m(void 0,null,function*(){return e==="2"?"\u4E0D\u80FD\u8F93\u5165\u8BE5\u503C":""}),width:200},{title:"\u6570\u5B57\u8F93\u5165\u6846",dataIndex:"id",edit:!0,editRule:!0,editComponent:"InputNumber",width:200},{title:"\u4E0B\u62C9\u6846",dataIndex:"name3",edit:!0,editComponent:"Select",editComponentProps:{options:[{label:"Option1",value:"1"},{label:"Option2",value:"2"}]},width:200},{title:"\u8FDC\u7A0B\u4E0B\u62C9",dataIndex:"name4",edit:!0,editComponent:"ApiSelect",editComponentProps:{api:c,resultField:"list",labelField:"name",valueField:"id"},width:200},{title:"\u8FDC\u7A0B\u4E0B\u62C9\u6811",dataIndex:"name71",edit:!0,editComponent:"ApiTreeSelect",editRule:!1,editComponentProps:{api:f,resultField:"list"},width:200},{title:"\u65E5\u671F\u9009\u62E9",dataIndex:"date",edit:!0,editComponent:"DatePicker",editComponentProps:{valueFormat:"YYYY-MM-DD",format:"YYYY-MM-DD"},width:200},{title:"\u65F6\u95F4\u9009\u62E9",dataIndex:"time",edit:!0,editComponent:"TimePicker",editComponentProps:{valueFormat:"HH:mm",format:"HH:mm"},width:200},{title:"\u52FE\u9009\u6846",dataIndex:"name5",edit:!0,editComponent:"Checkbox",editValueMap:e=>e?"\u662F":"\u5426",width:200},{title:"\u5F00\u5173",dataIndex:"name6",edit:!0,editComponent:"Switch",editValueMap:e=>e?"\u5F00":"\u5173",width:200}],w=x({components:{BasicTable:l},setup(){const[e]=p({title:"\u53EF\u7F16\u8F91\u5355\u5143\u683C\u793A\u4F8B",api:b,columns:h,showIndexColumn:!1,bordered:!0}),{createMessage:d}=C();function o({record:u,index:n,key:t,value:i}){return console.log(u,n,t,i),!1}function r({value:u,key:n,id:t}){return d.loading({content:`\u6B63\u5728\u6A21\u62DF\u4FDD\u5B58${n}`,key:"_save_fake_data",duration:0}),new Promise(i=>{setTimeout(()=>{u===""?(d.error({content:"\u4FDD\u5B58\u5931\u8D25\uFF1A\u4E0D\u80FD\u4E3A\u7A7A",key:"_save_fake_data",duration:2}),i(!1)):(d.success({content:`\u8BB0\u5F55${t}\u7684${n}\u5DF2\u4FDD\u5B58`,key:"_save_fake_data",duration:2}),i(!0))},2e3)})}function a(me){return m(this,arguments,function*({record:u,index:n,key:t,value:i}){return console.log("\u5355\u5143\u683C\u6570\u636E\u6B63\u5728\u51C6\u5907\u63D0\u4EA4",{record:u,index:n,key:t,value:i}),yield r({id:u.id,key:t,value:i})})}function s(){console.log("cancel")}return{registerTable:e,handleEditEnd:o,handleEditCancel:s,beforeEditSubmit:a}}}),A={class:"p-4"};function _(e,d,o,r,a,s){const u=j("BasicTable");return E(),B("div",A,[D(u,{onRegister:e.registerTable,onEditEnd:e.handleEditEnd,onEditCancel:e.handleEditCancel,beforeEditSubmit:e.beforeEditSubmit},null,8,["onRegister","onEditEnd","onEditCancel","beforeEditSubmit"])])}var re=F(w,[["render",_]]);export{re as default};