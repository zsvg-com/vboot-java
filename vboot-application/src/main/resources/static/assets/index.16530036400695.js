import{s as i,E as t,g as a,r as o}from"./index.1653003640069.js";const l=()=>{let u=[],n="0123456789abcdef";for(let e=0;e<36;e++)u[e]=n.substr(Math.floor(Math.random()*16),1);return u[14]="4",u[19]=n.substr(u[19]&3|8,1),u.join("")};async function s(u){const n=await i({url:u.url,method:"get",params:u.form});u.list=n.items,u.total=n.total,u.loading=!1}function d(u,n){n.ids=u.map(e=>e.id),n.single=u.length!==1,n.multiple=!u.length}async function g(u){const n=u.ids.join(",");if(n.length<=0){t.warning("\u8BF7\u9009\u62E9\u540E\u518D\u8FDB\u884C\u5220\u9664");return}a.confirm("\u786E\u8BA4\u5220\u9664\u5DF2\u9009\u4E2D\u7684\u6570\u636E\u9879?","\u8B66\u544A",{confirmButtonText:"\u786E\u5B9A",cancelButtonText:"\u53D6\u6D88",type:"warning"}).then(async()=>{await i({url:u.url+"/"+n,method:"delete"}),await s(u)}).catch(()=>t.info("\u5DF2\u53D6\u6D88\u5220\u9664"))}async function r(u){u.list=await i({url:u.url+"/tree",method:"get",params:u.form}),u.loading=!1}function f(u,n){n.ids=u.map(e=>e.id),n.single=u.length!==1,n.multiple=!u.length}async function D(u){const n=u.ids.join(",");if(n.length<=0){t.warning("\u8BF7\u9009\u62E9\u540E\u518D\u8FDB\u884C\u5220\u9664");return}a.confirm("\u786E\u8BA4\u5220\u9664\u5DF2\u9009\u4E2D\u7684\u6570\u636E\u9879?","\u8B66\u544A",{confirmButtonText:"\u786E\u5B9A",cancelButtonText:"\u53D6\u6D88",type:"warning"}).then(async()=>{await i({url:u.url+"/"+n,method:"delete"}),await r(u)}).catch(()=>t.info("\u5DF2\u53D6\u6D88\u5220\u9664"))}const h=async(u,n)=>{a.confirm("\u786E\u8BA4\u8981\u5220\u9664\u5417?","\u8B66\u544A",{confirmButtonText:"\u786E\u5B9A",cancelButtonText:"\u53D6\u6D88",type:"warning"}).then(async()=>{await i({url:u.url+"/"+n,method:"delete"}),await r(u)}).catch(()=>t.info("\u5DF2\u53D6\u6D88\u5220\u9664"))},p=(u,n)=>{n||(n={}),n.uuid=l(),o.push({path:u+"/edit",query:n})},m=(u,n)=>{o.push({path:u+"/edit",query:{id:n}})},w=u=>{window.open("#/page/"+u)},B=u=>{window.open("#/page/"+u)},E=u=>{window.open("#/page/"+u)};export{g as a,d as b,m as c,r as d,D as e,h as f,f as g,B as h,E as i,s as l,w as p,p as t};
