var p=(_,s,n)=>new Promise((r,d)=>{var l=t=>{try{o(n.next(t))}catch(i){d(i)}},e=t=>{try{o(n.throw(t))}catch(i){d(i)}},o=t=>t.done?r(t.value):Promise.resolve(t.value).then(l,e);o((n=n.apply(_,s)).next())});import{ah as x}from"./index.49385d3b.js";import{A as h,r as b,P as w,_ as C,a0 as f,B as T,D as y,w as c,a5 as u,H as k,J as D,u as g,ad as m}from"./vendor.a4547d8e.js";const E={style:{margin:"20px"}},B=m("\u5C55\u5F00\u6240\u6709"),N=m("\u5173\u95ED\u6240\u6709"),z=h({setup(_){const s=b({}),n=({column:l,row:e,triggerTreeNode:o})=>{console.log(e),o||s.value.toggleTreeExpand(e)},r=w({tableData:[],tableTreeConfig:{children:"children",rowKey:"id",indent:15,parentKey:"pid",iconOpen:"vxe-icon--arrow-bottom ",iconClose:"vxe-icon--arrow-right "}});function d(){return p(this,null,function*(){r.tableData=yield x.get({url:"/gen/org/dept/tree"})})}return C(()=>{d()}),(l,e)=>{const o=f("vxe-button"),t=f("vxe-column"),i=f("vxe-table");return T(),y("div",E,[c(o,{onClick:e[0]||(e[0]=a=>l.$refs.xTable1.setAllTreeExpand(!0))},{default:u(()=>[B]),_:1}),c(o,{onClick:e[1]||(e[1]=a=>l.$refs.xTable1.clearTreeExpand())},{default:u(()=>[N]),_:1}),c(i,{ref:(a,v)=>{v.xTable1=a,s.value=a},resizable:"","show-overflow":"","row-key":"",border:"none",size:"mini",height:"600","highlight-current-row":"","highlight-hover-row":"","show-header":!1,onCellClick:n,"row-config":{height:200},"tree-config":g(r).tableTreeConfig,data:g(r).tableData},{default:u(()=>[c(t,{field:"name",title:"Name","tree-node":""},{default:u(({row:a})=>[k("span",null,D(a.name),1)]),_:1})]),_:1},8,["tree-config","data"])])}}});export{z as default};
