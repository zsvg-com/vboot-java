var m=Object.defineProperty;var i=Object.getOwnPropertySymbols;var c=Object.prototype.hasOwnProperty,d=Object.prototype.propertyIsEnumerable;var n=(r,e,t)=>e in r?m(r,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[e]=t,p=(r,e)=>{for(var t in e||(e={}))c.call(e,t)&&n(r,t,e[t]);if(i)for(var t of i(e))d.call(e,t)&&n(r,t,e[t]);return r};import{u as h}from"./useECharts.1252346d.js";import{b as u}from"./props.f48aca0b.js";import{A as f,r as b,_ as y,B as x,D as g,X as _}from"./vendor.8e08a5be.js";import"./index.1f50be75.js";const C=f({props:p({},u),setup(r){const e=b(null),{setOptions:t}=h(e);return y(()=>{t({tooltip:{trigger:"axis",axisPointer:{lineStyle:{width:1,color:"#019680"}}},grid:{left:"1%",right:"1%",top:"2  %",bottom:0,containLabel:!0},xAxis:{type:"category",data:[...new Array(12)].map((a,s)=>`${s+1}\u6708`)},yAxis:{type:"value",max:8e3,splitNumber:4},series:[{data:[3e3,2e3,3333,5e3,3200,4200,3200,2100,3e3,5100,6e3,3200,4800],type:"bar",barMaxWidth:80}]})}),(a,s)=>(x(),g("div",{ref:(o,l)=>{l.chartRef=o,e.value=o},style:_({height:a.height,width:a.width})},null,4))}});export{C as default};
