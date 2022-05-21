var h=Object.defineProperty;var b=Object.getOwnPropertySymbols;var $=Object.prototype.hasOwnProperty,w=Object.prototype.propertyIsEnumerable;var x=(e,n,t)=>n in e?h(e,n,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[n]=t,E=(e,n)=>{for(var t in n||(n={}))$.call(n,t)&&x(e,t,n[t]);if(b)for(var t of b(n))w.call(n,t)&&x(e,t,n[t]);return e};import{P as F,Q as D,R as g,T as c,U as f,V,W as I}from"./index.1653003640069.js";import{a as O,a3 as q,j as B,m as J,k as L,Z as o,P,ap as k,X as d,A as M}from"./vue.1653003640069.js";import{B as p,T as A}from"./store.1653003640069.js";const m=e=>JSON.parse(JSON.stringify(e));var v=O({props:{modelValue:{type:Array,default:()=>[],required:!0},columns:{type:Array,required:!0},model:{type:Object,default:()=>Object.assign({})},rules:{type:Object,default:()=>null},tableProps:{type:Object,default:()=>({stripe:!0,border:!0,size:"small","empty-text":"\u6CA1\u6709\u6570\u636E"})},addTitle:{type:String,default:()=>"+ \u6DFB\u52A0"}},emits:["update:modelValue"],setup(e,n){const t=q({data:e.modelValue?JSON.parse(JSON.stringify(e.modelValue)):[],editing:!1,editItem:{},editIndex:void 0,isNew:!1,sublistForm:null}),l=B();J(()=>{t.sublistForm=l.value});const r=()=>{t.data=e.modelValue?JSON.parse(JSON.stringify(e.modelValue)):[],t.editing=!1,t.editItem=void 0,t.editIndex=void 0,t.isNew=!1};L(()=>e.modelValue,()=>{r()});const i=()=>{t.data.push(m(e.model)),t.editIndex=t.data.length-1,t.editing=!0,t.editItem=m(m(e.model)),t.isNew=!0},u=R(t,n);return{sublistState:t,addData:i,form:l,actionColumnProps:u}},render(){const e=this.$props,n=this.sublistState,t=m(e.tableProps),l={size:"default",inline:!0,"inline-message":!0,"show-message":!0,rules:e.rules,model:n.editItem};return o("div",{class:"sublist-div"},[n.data&&o(F,P({ref:"form"},l),{default:()=>[o(D,P(t,{data:n.data}),{default:()=>[e.columns.map(r=>{const i=k(r);if(n.editing&&i.type!=="index"){const u=i.editComponent||_();return o(f,i,{default:s=>{const y=s.row[s.column.property],T=()=>s.column.formatter?s.column.formatter(s.row,s.column,y,s.$index):y;return n.editIndex===s.$index?u(s,n):T()}})}else return o(f,i,null)}),o(f,this.actionColumnProps,this.actionColumnProps.vSlots)]})]}),!n.editing&&o("div",{class:"sublist-add",onClick:()=>this.addData()},[e.addTitle])])}});const R=(e,n)=>{function t(a){e.editIndex=a.$index,e.editing=!0,e.editItem=m(a.row)}function l(a){var s;(s=e==null?void 0:e.data)==null||s.splice(a,1),n.emit("update:modelValue",e.data)}function r(){e.sublistForm.validate(a=>{if(a)typeof(e==null?void 0:e.editIndex)=="number"&&e.data.splice(e==null?void 0:e.editIndex,1,m(k(e.editItem))),e.editIndex=void 0,e.editItem=void 0,e.editing=!1,n.emit("update:modelValue",e.data);else return!1})}function i(){e.isNew&&e.data.splice(e.data.length-1,1),e.editItem=void 0,e.editing=!1,e.editIndex=void 0}function u(){return e.editing?"readonly":""}return{align:"center",label:"\u64CD\u4F5C",vSlots:{default:a=>o("div",{class:"sublist-actions"},[e.editing&&a.$index===e.editIndex?o("div",null,[o("span",{class:"sublist-confirm sublist-btn",onClick:()=>r()},[d("\u786E\u8BA4")]),o("span",{class:"sublist-split"},[d("|")]),o("span",{class:"sublist-cancel sublist-btn",onClick:()=>i()},[d("\u53D6\u6D88")])]):o("div",null,[o("span",{class:`${u()} sublist-edit sublist-btn`,onClick:()=>{e.editing||t(a)}},[d("\u7F16\u8F91")]),o("span",{class:`${u()} `},[d("|")]),o("span",{class:`${u()} sublist-delete sublist-btn`,onClick:()=>{e.editing||l(a.$index)}},[d("\u5220\u9664")])])])}}};function _(){return function(e,n){return o(g,{class:"sublist-form-item",label:e.column.name,prop:e.column.property},{default:()=>[o(c,{label:e.column.label,modelValue:n.editItem[e.column.property],"onUpdate:modelValue":t=>n.editItem[e.column.property]=t},null)]})}}const G={id:{component:c,placeholder:"\u8282\u70B9ID",vSlots:{prepend:()=>o("div",null,[d("\u8282\u70B9ID")])},setValue(e,n,t){const l=t,r=t||" ",i=p.getShape();p.getModeling().updateProperties(i,{[n]:l?r.trim():r})}},name:{component:c,placeholder:"\u8282\u70B9\u540D\u79F0",vSlots:{prepend:()=>o("div",null,[d("\u8282\u70B9\u540D\u79F0")])}}},W={name:"\u57FA\u7840\u4FE1\u606F",icon:"el-icon-info",properties:E({},G)},X={name:"\u5143\u7D20\u6587\u6863",icon:"el-icon-document",properties:{"documentation.text":{component:c,type:"textarea",getValue:e=>{var n,t;return(t=(n=e.documentation)==null?void 0:n[0])==null?void 0:t.text},setValue(e,n,t){p.createElement("bpmn:Documentation","documentation",{text:t},!0)}}}},U=[{label:"\u5F00\u59CB",value:"start"},{label:"\u7ED3\u675F",value:"end"}],C=[{label:"java\u7C7B",value:"class"},{label:"\u8C03\u7528\u8868\u8FBE\u5F0F",value:"expression"},{label:"\u6CE8\u5165\u8868\u8FBE\u5F0F",value:"delegateExpression"}];function S(e){return typeof e=="function"||Object.prototype.toString.call(e)==="[object Object]"&&!M(e)}const N=Object.keys(A),Y=function(e){const n=e.eventOptions||U;return{name:e.name||"\u76D1\u542C\u5668",icon:e.icon||"el-icon-bell",properties:{"extensionElements.listeners":{component:v,columns:[{type:"index",label:"\u5E8F\u53F7",align:"center"},{prop:"event",label:"\u4E8B\u4EF6",align:"center",formatter:(t,l)=>n.filter(r=>r.value===t[l.property])[0].label,editComponent:function(t,l){let r;return o(g,{class:"sublist-form-item",label:t.column.name,prop:t.column.property},{default:()=>[o(V,{modelValue:l.editItem.event,"onUpdate:modelValue":i=>l.editItem.event=i},S(r=n.map(i=>o(I,{key:i.value,label:i.label,value:i.value},null)))?r:{default:()=>[r]})]})}},{prop:"type",label:"\u6267\u884C\u7C7B\u578B",align:"center",formatter:(t,l)=>C.filter(r=>r.value===t[l.property])[0].label,editComponent:function(t,l){let r;return o(g,{class:"sublist-form-item",label:t.column.name,prop:t.column.property},{default:()=>[o(V,{modelValue:l.editItem.type,"onUpdate:modelValue":i=>l.editItem.type=i},S(r=C.map(i=>o(I,{key:i.value,label:i.label,value:i.value},null)))?r:{default:()=>[r]})]})}},{prop:"content",label:"\u6267\u884C\u5185\u5BB9",align:"center"}],rules:{event:[{required:!0,message:"\u4E8B\u4EF6\u4E0D\u80FD\u4E3A\u7A7A"}],type:[{required:!0,message:"\u7C7B\u578B\u4E0D\u80FD\u4E3A\u7A7A"}],content:[{required:!0,message:"\u6267\u884C\u5185\u5BB9\u4E0D\u80FD\u4E3A\u7A7A"}]},getValue:t=>{var r,i,u;const l=N.includes(t.$type)?"activiti:TaskListener":"activiti:ExecutionListener";return(u=(i=(r=t==null?void 0:t.extensionElements)==null?void 0:r.values)==null?void 0:i.filter(a=>a.$type===l))==null?void 0:u.map(a=>{const s=a.expression?"expression":a.delegateExpression?"delegateExpression":"class";return{event:a.event,type:s,content:a[s]}})},setValue(t,l,r){const i=p;console.warn("activeBusinessObject",t);const u=i.getModeler().get("moddle"),a=N.includes(t.$type)?"activiti:TaskListener":"activiti:ExecutionListener";i.updateExtensionElements(a,r.map(s=>u.create(a,{event:s.event,[s.type]:s.content})))}}}}},Z={name:"\u6269\u5C55\u5C5E\u6027",icon:"el-icon-document-add",properties:{"extensionElements.properties":{component:v,columns:[{type:"index",label:"\u5E8F\u53F7",align:"center"},{prop:"name",label:"\u5C5E\u6027\u540D",align:"center"},{prop:"value",label:"\u5C5E\u6027\u503C",align:"center"}],rules:{name:[{required:!0,message:"\u5C5E\u6027\u540D\u4E0D\u80FD\u4E3A\u7A7A"}],value:[{required:!0,message:"\u5C5E\u6027\u503C\u4E0D\u80FD\u4E3A\u7A7A"}]},getValue:e=>{var n,t,l;return(l=(t=(n=e==null?void 0:e.extensionElements)==null?void 0:n.values)==null?void 0:t.filter(r=>r.$type==="activiti:Properties")[0])==null?void 0:l.values.map(r=>({name:r.name,value:r.value}))},setValue(e,n,t){const l=p,r=l.getModeler().get("moddle"),i=r.create("activiti:Properties",{values:t.map(u=>r.create("activiti:Property",{name:u.name,value:u.value}))});l.updateExtensionElements("activiti:Properties",i)}}}},H={name:"\u8868\u5355\u4FE1\u606F",icon:"el-icon-edit",properties:{formKey:{component:c,placeholder:"\u8868\u5355key",vSlots:{prepend:()=>o("div",null,[d("\u8868\u5355key")])}},"extensionElements.formProperty":{component:v,columns:[{prop:"id",label:"\u7F16\u7801",align:"center"},{prop:"type",label:"\u7C7B\u578B",align:"center"},{prop:"name",label:"\u540D\u79F0",align:"center"}],rules:{id:[{required:!0,message:"\u7F16\u7801\u4E0D\u80FD\u4E3A\u7A7A"}],type:[{required:!0,message:"\u7C7B\u578B\u4E0D\u80FD\u4E3A\u7A7A"}],name:[{required:!0,message:"\u540D\u79F0\u4E0D\u80FD\u4E3A\u7A7A"}]},getValue:e=>{var n,t;return(t=(n=e==null?void 0:e.extensionElements)==null?void 0:n.values)==null?void 0:t.filter(l=>l.$type==="activiti:FormProperty").map(l=>{var r;return console.warn("elem",l),{id:l==null?void 0:l.id,type:l.type,name:(r=l==null?void 0:l.$attrs)==null?void 0:r.name}})},setValue(e,n,t){const l=p,r=l.getModeler().get("moddle"),i=t.map(u=>r.create("activiti:FormProperty",{id:u.id,name:u.name,type:u.type}));l.updateExtensionElements("activiti:FormProperty",i)}}}};export{W as C,X as D,Z as E,H as F,Y as g};
