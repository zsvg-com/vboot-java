var j=(n,i,t)=>new Promise((l,r)=>{var o=e=>{try{a(t.next(e))}catch(p){r(p)}},s=e=>{try{a(t.throw(e))}catch(p){r(p)}},a=e=>e.done?l(e.value):Promise.resolve(e.value).then(o,s);a((t=t.apply(n,i)).next())});import{B as C}from"./BasicForm.4740433f.js";import{u as v}from"./useForm.6f5945bb.js";import{_ as h,af as F,aI as $}from"./index.1f50be75.js";import{A as g,ao as b,r as P,a0 as u,B as c,a1 as f,a6 as d,w as B,ad as _,ae as x}from"./vendor.8e08a5be.js";import{P as k}from"./index.4a6d20cf.js";/* empty css               *//* empty css               *//* empty css               *//* empty css                *//* empty css              *//* empty css               *//* empty css                *//* empty css                */import"./index.4765eeb8.js";/* empty css                */import"./index.7c24f3ba.js";import"./useWindowSizeFn.2016176c.js";/* empty css                *//* empty css                *//* empty css                *//* empty css                */import"./uuid.2b29000c.js";import"./download.1020b52a.js";import"./base64Conver.08b9f4ec.js";import"./index.b2563f35.js";/* empty css               *//* empty css                */import"./useContentViewHeight.78015564.js";const I=g({components:{BasicForm:C,CollapseContainer:F,PageWrapper:k,[b.name]:b,Button:$},setup(){const[n,{appendSchemaByField:i,removeSchemaByFiled:t,validate:l}]=v({schemas:[{field:"field0a",component:"Input",label:"\u5B57\u6BB50",colProps:{span:8},required:!0},{field:"field0b",component:"Input",label:"\u5B57\u6BB50",colProps:{span:8},required:!0},{field:"0",component:"Input",label:" ",colProps:{span:8},slot:"add"}],labelWidth:100,actionColOptions:{span:24}});function r(){return j(this,null,function*(){try{const e=yield l();console.log(e)}catch(e){console.log(e)}})}const o=P(1);function s(){i({field:`field${o.value}a`,component:"Input",label:"\u5B57\u6BB5"+o.value,colProps:{span:8},required:!0},""),i({field:`field${o.value}b`,component:"Input",label:"\u5B57\u6BB5"+o.value,colProps:{span:8},required:!0},""),i({field:`${o.value}`,component:"Input",label:" ",colProps:{span:8},slot:"add"},""),o.value++}function a(e){t([`field${e}a`,`field${e}b`,`${e}`]),o.value--}return{register:n,handleSubmit:r,add:s,del:a}}}),S=x("+"),w=x("-");function y(n,i,t,l,r,o){const s=u("Button"),a=u("BasicForm"),e=u("CollapseContainer"),p=u("PageWrapper");return c(),f(p,{title:"\u8868\u5355\u589E\u5220\u793A\u4F8B"},{default:d(()=>[B(e,{title:"\u8868\u5355\u589E\u5220"},{default:d(()=>[B(a,{onRegister:n.register,onSubmit:n.handleSubmit},{add:d(({field:m})=>[Number(m)===0?(c(),f(s,{key:0,onClick:n.add},{default:d(()=>[S]),_:1},8,["onClick"])):_("",!0),m>0?(c(),f(s,{key:1,onClick:W=>n.del(m)},{default:d(()=>[w]),_:2},1032,["onClick"])):_("",!0)]),_:1},8,["onRegister","onSubmit"])]),_:1})]),_:1})}var pe=h(I,[["render",y]]);export{pe as default};
