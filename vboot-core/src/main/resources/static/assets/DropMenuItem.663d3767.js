import{A as i,az as u,j as d,a0 as a,B as l,a1 as _,a5 as I,H as r,w as f,J as y,ah as k}from"./vendor.a4547d8e.js";import{_ as M,I as g,p as s}from"./index.49385d3b.js";const x=i({name:"DropdownMenuItem",components:{MenuItem:u.Item,Icon:g},props:{key:s.string,text:s.string,icon:s.string},setup(e){const n=k();return{itemKey:d(()=>{var t,o;return e.key||((o=(t=n==null?void 0:n.vnode)==null?void 0:t.props)==null?void 0:o.key)})}}}),h={class:"flex items-center"};function v(e,n,c,t,o,B){const p=a("Icon"),m=a("MenuItem");return l(),_(m,{key:e.itemKey},{default:I(()=>[r("span",h,[f(p,{icon:e.icon,class:"mr-1"},null,8,["icon"]),r("span",null,y(e.text),1)])]),_:1})}var j=M(x,[["render",v]]);export{j as default};
