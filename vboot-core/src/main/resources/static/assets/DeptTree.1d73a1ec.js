var y=(b,d,o)=>new Promise((h,f)=>{var v=n=>{try{l(o.next(n))}catch(s){f(s)}},e=n=>{try{l(o.throw(n))}catch(s){f(s)}},l=n=>n.done?h(n.value):Promise.resolve(n.value).then(v,e);l((o=o.apply(b,d)).next())});import{ah as E}from"./index.1f50be75.js";import{A as T,r as C,P as N,bG as m,_ as V,a0 as x,B as $,D as B,H as g,w as a,u as p,a6 as D,J as K,$ as S}from"./vendor.8e08a5be.js";const j={class:"bg-white m-4 mr-0 overflow-hidden zdept"},z={class:"flex px-2 py-1.5 items-center basic-tree-header"},A={class:"flex flex-1 justify-self-stretch items-center cursor-pointer"},F=g("hr",null,null,-1),U=T({emits:["select"],setup(b,{emit:d}){const o=C({}),h=({column:c,row:t,triggerTreeNode:u})=>{u||(o.value.toggleTreeExpand(t),d("select",{id:t.id,name:t.name}))};function f(){d("select",null)}function v(){s()}C("");const e=N({filterName:"",tableData:[],originData:[],tableTreeConfig:{children:"children",rowKey:"id",indent:15,parentKey:"pid",iconOpen:"vxe-icon--arrow-bottom",iconClose:"vxe-icon--arrow-right"}}),l=()=>{const c=m.toValueString(e.filterName).trim();if(c){const t={children:"children"},u=["name"];e.tableData=m.searchTree(e.originData,r=>u.some(_=>m.toValueString(r[_]).indexOf(c)>-1),t),S(()=>{o.value.setAllTreeExpand(!0)})}else e.tableData=e.originData},n=m.debounce(function(){l()},500,{leading:!1,trailing:!0});function s(){return y(this,null,function*(){e.originData=yield E.get({url:"/gen/org/dept/tree"}),l()})}return V(()=>{s()}),(c,t)=>{const u=x("vxe-input"),r=x("vxe-button"),_=x("vxe-column"),w=x("vxe-table");return $(),B("div",j,[g("div",z,[g("div",A,[a(u,{modelValue:p(e).filterName,"onUpdate:modelValue":t[0]||(t[0]=i=>p(e).filterName=i),placeholder:"\u641C\u7D22",style:{width:"120px"},type:"type",onKeyup:p(n)},null,8,["modelValue","onKeyup"]),a(r,{icon:"vxe-icon--refresh",title:"\u5237\u65B0",circle:"",onClick:v,style:{"margin-left":"4px","margin-right":"2px"}}),a(r,{icon:"vxe-icon--dot",title:"\u6839\u76EE\u5F55",circle:"",onClick:f,style:{"margin-left":"2px","margin-right":"2px"}}),a(r,{icon:"vxe-icon--caret-bottom",title:"\u5C55\u5F00",circle:"",onClick:t[1]||(t[1]=i=>c.$refs.xDept.setAllTreeExpand(!0)),style:{"margin-left":"2px","margin-right":"2px"}}),a(r,{icon:"vxe-icon--caret-top",title:"\u6298\u53E0",circle:"",onClick:t[2]||(t[2]=i=>c.$refs.xDept.clearTreeExpand()),style:{"margin-left":"2px","margin-right":"2px"}})])]),F,a(w,{ref:(i,k)=>{k.xDept=i,o.value=i},resizable:"","show-overflow":"","row-key":"",border:"none",size:"mini",height:"auto","highlight-current-row":"","highlight-hover-row":"","show-header":!1,onCellClick:h,"row-config":{height:26},"tree-config":p(e).tableTreeConfig,data:p(e).tableData},{default:D(()=>[a(_,{field:"name",title:"Name","tree-node":""},{default:D(({row:i})=>[g("span",null,K(i.name),1)]),_:1})]),_:1},8,["tree-config","data"])])}}});export{U as _};