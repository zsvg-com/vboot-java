import{_ as f,af as _}from"./index.6733effe.js";import{A as g,r as C,bk as m,a0 as r,B as h,a1 as w,a6 as o,w as t,ae as n,J as k,H as F}from"./vendor.af2fe88b.js";import{P as x}from"./index.0248b5d5.js";/* empty css               *//* empty css                */import"./useWindowSizeFn.4124f9cf.js";import"./useContentViewHeight.b4e0fa30.js";const S=g({components:{CollapseContainer:_,PageWrapper:x},setup(){const e=C(null),{enter:l,toggle:a,exit:i,isFullscreen:c}=m(),{toggle:d}=m(e);return{enter:l,toggleDom:d,toggle:a,isFullscreen:c,exit:i,domRef:e}}}),b=n(" Enter Window Full Screen "),j=n(" Toggle Window Full Screen "),y=n(" Exit Window Full Screen "),W=n(" Enter Dom Full Screen "),D={ref:"domRef",class:"flex items-center justify-center w-1/2 h-64 mx-auto mt-10 bg-white rounded-md"},B=n(" Exit Dom Full Screen ");function v(e,l,a,i,c,d){const s=r("a-button"),u=r("CollapseContainer"),p=r("PageWrapper");return h(),w(p,{title:"\u5168\u5C4F\u793A\u4F8B"},{default:o(()=>[t(u,{class:"w-full h-32 bg-white rounded-md",title:"Window Full Screen"},{default:o(()=>[t(s,{type:"primary",onClick:e.enter,class:"mr-2"},{default:o(()=>[b]),_:1},8,["onClick"]),t(s,{color:"success",onClick:e.toggle,class:"mr-2"},{default:o(()=>[j]),_:1},8,["onClick"]),t(s,{color:"error",onClick:e.exit,class:"mr-2"},{default:o(()=>[y]),_:1},8,["onClick"]),n(" Current State: "+k(e.isFullscreen),1)]),_:1}),t(u,{class:"w-full mt-5 bg-white rounded-md",title:"Dom Full Screen"},{default:o(()=>[t(s,{type:"primary",onClick:e.toggleDom,class:"mr-2"},{default:o(()=>[W]),_:1},8,["onClick"])]),_:1}),F("div",D,[t(s,{type:"primary",onClick:e.toggleDom,class:"mr-2"},{default:o(()=>[B]),_:1},8,["onClick"])],512)]),_:1})}var R=f(S,[["render",v]]);export{R as default};
