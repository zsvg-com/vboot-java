var g=Object.defineProperty,E=Object.defineProperties;var x=Object.getOwnPropertyDescriptors;var d=Object.getOwnPropertySymbols;var F=Object.prototype.hasOwnProperty,P=Object.prototype.propertyIsEnumerable;var m=(e,o,t)=>o in e?g(e,o,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[o]=t,l=(e,o)=>{for(var t in o||(o={}))F.call(o,t)&&m(e,t,o[t]);if(d)for(var t of d(o))P.call(o,t)&&m(e,t,o[t]);return e},i=(e,o)=>E(e,x(o));import{g as S,E as y,D as v,C as u}from"./common.1653003640069.js";import{P as T}from"./PrefixLabelSelect.1653003640069.js";import{B as s}from"./store.1653003640069.js";import{W as V,T as M}from"./index.1653003640069.js";import{Z as a,F as h,X as k}from"./vue.1653003640069.js";const w=[{label:"\u666E\u901A\u987A\u5E8F\u6D41",value:"normal"},{label:"\u9ED8\u8BA4\u987A\u5E8F\u6D41",value:"default"},{label:"\u6761\u4EF6\u987A\u5E8F\u6D41",value:"condition"}],c=e=>{var o;return e!=null&&e.conditionExpression?"condition":(o=e==null?void 0:e.sourceRef)!=null&&o.default?"default":"normal"},B=i(l({},u),{properties:i(l({},u.properties),{"sequenceFlow.type":{component:T,prefixTitle:"\u987A\u5E8F\u6D41\u7C7B\u578B",predicate:e=>{var o;return((o=e==null?void 0:e.sourceRef)==null?void 0:o.$type)!=="bpmn:StartEvent"},vSlots:{default:()=>a(h,null,[w.map(e=>a(V,e,null))])},getValue(e){return c(e)},setValue(e,o,t){const r=s,n=r.getShape(),f=r.getModeler().get("elementRegistry").get(n.businessObject.sourceRef.id),p=r.getModeling();(!t||t==="normal")&&(p.updateProperties(n,{conditionExpression:null}),delete n.businessObject.conditionExpression),t==="default"&&(p.updateProperties(f,{default:n}),delete n.businessObject.conditionExpression),t==="condition"&&p.updateProperties(n,{conditionExpression:r.getModeler().get("moddle").create("bpmn:FormalExpression")})}},"conditionExpression.body":{component:M,placeholder:"\u6761\u4EF6\u8868\u8FBE\u5F0F",vSlots:{prepend:()=>a("div",null,[k("\u6761\u4EF6\u8868\u8FBE\u5F0F")])},predicate:e=>c(e)==="condition",getValue(e){var o;return(o=e==null?void 0:e.conditionExpression)==null?void 0:o.body},setValue(e,o,t){const r=s,n=r.getModeler().get("moddle");r.getModeling().updateProperties(r.getShape(),{conditionExpression:n.create("bpmn:FormalExpression",{body:t})})}}})});var D={"bpmn:SequenceFlow":[B,S({name:"\u6761\u4EF6\u76D1\u542C\u5668",eventOptions:[{label:"take",value:"take"}]}),y,v]};export{D as default};
