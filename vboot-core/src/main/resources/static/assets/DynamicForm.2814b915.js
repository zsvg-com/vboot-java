var B=(e,l,n)=>new Promise((p,r)=>{var c=o=>{try{t(n.next(o))}catch(s){r(s)}},u=o=>{try{t(n.throw(o))}catch(s){r(s)}},t=o=>o.done?p(o.value):Promise.resolve(o.value).then(c,u);t((n=n.apply(e,l)).next())});import{B as h}from"./BasicForm.4740433f.js";import{u as f}from"./useForm.6f5945bb.js";import{_ as C,af as F}from"./index.1f50be75.js";import{P as _}from"./index.4a6d20cf.js";import{A as j,a0 as d,B as P,a1 as g,a6 as a,H as x,w as i,ae as m}from"./vendor.8e08a5be.js";/* empty css               *//* empty css               *//* empty css               *//* empty css                *//* empty css              *//* empty css               *//* empty css                *//* empty css                */import"./index.4765eeb8.js";/* empty css                */import"./index.7c24f3ba.js";import"./useWindowSizeFn.2016176c.js";/* empty css                *//* empty css                *//* empty css                *//* empty css                */import"./uuid.2b29000c.js";import"./download.1020b52a.js";import"./base64Conver.08b9f4ec.js";import"./index.b2563f35.js";/* empty css               *//* empty css                */import"./useContentViewHeight.78015564.js";const b=[{field:"field1",component:"Input",label:"\u5B57\u6BB51",colProps:{span:8},show:({values:e})=>!!e.field5},{field:"field2",component:"Input",label:"\u5B57\u6BB52",colProps:{span:8},ifShow:({values:e})=>!!e.field6},{field:"field3",component:"DatePicker",label:"\u5B57\u6BB53",colProps:{span:8},dynamicDisabled:({values:e})=>!!e.field7},{field:"field4",component:"Select",label:"\u5B57\u6BB54",colProps:{span:8},dynamicRules:({values:e})=>e.field8?[{required:!0,message:"\u5B57\u6BB54\u5FC5\u586B"}]:[],componentProps:{options:[{label:"\u9009\u98791",value:"1",key:"1"},{label:"\u9009\u98792",value:"2",key:"2"}]}},{field:"field11",component:"DatePicker",label:"\u5B57\u6BB511",colProps:{span:8}},{field:"field5",component:"Switch",label:"\u662F\u5426\u663E\u793A\u5B57\u6BB51(css\u63A7\u5236)",colProps:{span:8},labelWidth:200},{field:"field6",component:"Switch",label:"\u662F\u5426\u663E\u793A\u5B57\u6BB52(dom\u63A7\u5236)",colProps:{span:8},labelWidth:200},{field:"field7",component:"Switch",label:"\u662F\u5426\u7981\u7528\u5B57\u6BB53",colProps:{span:8},labelWidth:200},{field:"field8",component:"Switch",label:"\u5B57\u6BB54\u662F\u5426\u5FC5\u586B",colProps:{span:8},labelWidth:200}],w=[{field:"f1",component:"Input",label:"F1",colProps:{span:12},labelWidth:200,componentProps:({formModel:e})=>({placeholder:"\u540C\u6B65f2\u7684\u503C\u4E3Af1",onChange:l=>{e.f2=l.target.value}})},{field:"f2",component:"Input",label:"F2",colProps:{span:12},labelWidth:200,componentProps:{disabled:!0}},{field:"f3",component:"Input",label:"F3",colProps:{span:12},labelWidth:200,componentProps:({formActionType:e})=>({placeholder:"\u503C\u6539\u53D8\u65F6\u6267\u884C\u67E5\u8BE2,\u67E5\u770B\u63A7\u5236\u53F0",onChange:()=>B(void 0,null,function*(){const{validate:l}=e,n=yield l();console.log(n)})})}],k=j({components:{BasicForm:h,CollapseContainer:F,PageWrapper:_},setup(){const[e,{setProps:l,updateSchema:n,appendSchemaByField:p,removeSchemaByFiled:r}]=f({labelWidth:120,schemas:b,actionColOptions:{span:24}}),[c]=f({labelWidth:120,schemas:w,actionColOptions:{span:24}});function u(){n({field:"field3",label:"\u5B57\u6BB53 New"})}function t(){n([{field:"field3",label:"\u5B57\u6BB53 New++"},{field:"field4",label:"\u5B57\u6BB54 New++"}])}function o(){p({field:"field10",label:"\u5B57\u6BB510",component:"Input",colProps:{span:8}},"field3")}function s(){r("field11")}return{register:e,register1:c,schemas:b,setProps:l,changeLabel3:u,changeLabel34:t,appendField:o,deleteField:s}}}),A={class:"mb-4"},W=m(" \u66F4\u6539\u5B57\u6BB53label "),v=m(" \u540C\u65F6\u66F4\u6539\u5B57\u6BB53,4label "),S=m(" \u5F80\u5B57\u6BB53\u540E\u9762\u63D2\u5165\u5B57\u6BB510 "),D=m(" \u5220\u9664\u5B57\u6BB511 ");function E(e,l,n,p,r,c){const u=d("a-button"),t=d("BasicForm"),o=d("CollapseContainer"),s=d("PageWrapper");return P(),g(s,{title:"\u52A8\u6001\u8868\u5355\u793A\u4F8B"},{default:a(()=>[x("div",A,[i(u,{onClick:e.changeLabel3,class:"mr-2"},{default:a(()=>[W]),_:1},8,["onClick"]),i(u,{onClick:e.changeLabel34,class:"mr-2"},{default:a(()=>[v]),_:1},8,["onClick"]),i(u,{onClick:e.appendField,class:"mr-2"},{default:a(()=>[S]),_:1},8,["onClick"]),i(u,{onClick:e.deleteField,class:"mr-2"},{default:a(()=>[D]),_:1},8,["onClick"])]),i(o,{title:"\u52A8\u6001\u8868\u5355\u793A\u4F8B,\u52A8\u6001\u6839\u636E\u8868\u5355\u5185\u5176\u4ED6\u503C\u6539\u53D8"},{default:a(()=>[i(t,{onRegister:e.register},null,8,["onRegister"])]),_:1}),i(o,{class:"mt-5",title:"componentProps\u52A8\u6001\u6539\u53D8"},{default:a(()=>[i(t,{onRegister:e.register1},null,8,["onRegister"])]),_:1})]),_:1})}var ae=C(k,[["render",E]]);export{ae as default};
