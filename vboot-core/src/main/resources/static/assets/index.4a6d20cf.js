var te=Object.defineProperty,oe=Object.defineProperties;var ne=Object.getOwnPropertyDescriptors;var I=Object.getOwnPropertySymbols;var ae=Object.prototype.hasOwnProperty,re=Object.prototype.propertyIsEnumerable;var M=(e,t,n)=>t in e?te(e,t,{enumerable:!0,configurable:!0,writable:!0,value:n}):e[t]=n,E=(e,t)=>{for(var n in t||(t={}))ae.call(t,n)&&M(e,n,t[n]);if(I)for(var n of I(t))re.call(t,n)&&M(e,n,t[n]);return e},x=(e,t)=>oe(e,ne(t));var K=(e,t,n)=>new Promise((F,l)=>{var $=r=>{try{i(n.next(r))}catch(H){l(H)}},p=r=>{try{i(n.throw(r))}catch(H){l(H)}},i=r=>r.done?F(r.value):Promise.resolve(r.value).then($,p);i((n=n.apply(e,t)).next())});import{a as X,q as se,_ as q,ab as ie,j as ce,ac as ue,p as d,w as J}from"./index.1f50be75.js";import{A as L,B as k,D,H as N,a7 as y,K as W,X as U,_ as ge,$ as T,bt as le,r as B,u as C,bu as fe,S as Z,bv as de,R as pe,j as b,o as G,a0 as O,a1 as Q,aD as he,ap as me,a6 as z,aC as He,aS as Ce,ab as be,ae as Fe,J as $e,a4 as ve,ad as Y}from"./vendor.8e08a5be.js";/* empty css               *//* empty css                */import{u as ye}from"./useWindowSizeFn.2016176c.js";import{a as _e}from"./useContentViewHeight.78015564.js";const Pe=L({name:"PageFooter",inheritAttrs:!1,setup(){const{prefixCls:e}=X("page-footer"),{getCalcContentWidth:t}=se();return{prefixCls:e,getCalcContentWidth:t}}});function Se(e,t,n,F,l,$){return k(),D("div",{class:W(e.prefixCls),style:U({width:e.getCalcContentWidth})},[N("div",{class:W(`${e.prefixCls}__left`)},[y(e.$slots,"left",{},void 0,!0)],2),y(e.$slots,"default",{},void 0,!0),N("div",{class:W(`${e.prefixCls}__right`)},[y(e.$slots,"right",{},void 0,!0)],2)],6)}var ee=q(Pe,[["render",Se],["__scopeId","data-v-7984b8f2"]]);function we(e){let t;ge(()=>{e(),T(()=>{t=!0})}),le(()=>{t&&e()})}function Re(e,t,n,F,l=0,$=B(0)){const p=B(null),{footerHeightRef:i}=_e();let r={useLayoutFooter:!0};const H=a=>{r=a};function A(){T(()=>{R()})}function _(a,P="all"){var g,m,S,v;function h(o){return Number(o.replace(/[^\d]/g,""))}let u=0;const f="0px";if(a){const o=getComputedStyle(a),c=h((g=o==null?void 0:o.marginTop)!=null?g:f),s=h((m=o==null?void 0:o.marginBottom)!=null?m:f),j=h((S=o==null?void 0:o.paddingTop)!=null?S:f),V=h((v=o==null?void 0:o.paddingBottom)!=null?v:f);P==="all"?(u+=c,u+=s,u+=j,u+=V):P==="top"?(u+=c,u+=j):(u+=s,u+=V)}return u}function w(a){return a==null?null:a instanceof HTMLDivElement?a:a.$el}function R(){return K(this,null,function*(){var v;if(!e.value)return;yield T();const a=w(C(t));if(!a)return;const{bottomIncludeBody:P}=ie(a);let h=0;n.forEach(o=>{var c,s;h+=(s=(c=w(C(o)))==null?void 0:c.offsetHeight)!=null?s:0});let u=(v=_(a))!=null?v:0;F.forEach(o=>{u+=_(w(C(o)))});let f=0;function g(o,c){if(o&&c){const s=o.parentElement;s&&(ce(c)?s.classList.contains(c)?f+=_(s,"bottom"):(f+=_(s,"bottom"),g(s,c)):ue(c)&&c>0&&(f+=_(s,"bottom"),g(s,--c)))}}fe(l)?g(a,C(l)):g(a,l);let m=P-C(i)-C($)-h-u-f;const S=()=>{var o;(o=r.elements)==null||o.forEach(c=>{var s,j;m+=(j=(s=w(C(c)))==null?void 0:s.offsetHeight)!=null?j:0})};r.useLayoutFooter&&C(i)>0,S(),p.value=m})}return we(()=>{T(()=>{R()})}),ye(()=>{R()},50,{immediate:!0}),Z(()=>[i.value],()=>{R()},{flush:"post",immediate:!0}),{redoHeight:A,setCompensation:H,contentHeight:p}}const Be=L({name:"PageWrapper",components:{PageFooter:ee,PageHeader:de},inheritAttrs:!1,props:{title:d.string,dense:d.bool,ghost:d.bool,content:d.string,contentStyle:{type:Object},contentBackground:d.bool,contentFullHeight:d.bool,contentClass:d.string,fixedHeight:d.bool,upwardSpace:d.oneOfType([d.number,d.string]).def(0)},setup(e,{slots:t,attrs:n}){const F=B(null),l=B(null),$=B(null),p=B(null),{prefixCls:i}=X("page-wrapper");pe(We,b(()=>e.fixedHeight));const r=b(()=>e.contentFullHeight),H=b(()=>e.upwardSpace),{redoHeight:A,setCompensation:_,contentHeight:w}=Re(r,F,[l,p],[$],H);_({useLayoutFooter:!0,elements:[p]});const R=b(()=>{var g;return[i,{[`${i}--dense`]:e.dense},(g=n.class)!=null?g:{}]}),a=b(()=>e.content||(t==null?void 0:t.headerContent)||e.title||h.value.length),P=b(()=>(t==null?void 0:t.leftFooter)||(t==null?void 0:t.rightFooter)),h=b(()=>Object.keys(G(t,"default","leftFooter","rightFooter","headerContent"))),u=b(()=>{const{contentFullHeight:g,contentStyle:m,fixedHeight:S}=e;if(!g)return E({},m);const v=`${C(w)}px`;return E(x(E({},m),{minHeight:v}),S?{height:v}:{})}),f=b(()=>{const{contentBackground:g,contentClass:m}=e;return[`${i}-content`,m,{[`${i}-content-bg`]:g}]});return Z(()=>[P.value],()=>{A()},{flush:"post",immediate:!0}),{getContentStyle:u,wrapperRef:F,headerRef:l,contentRef:$,footerRef:p,getClass:R,getHeaderSlots:h,prefixCls:i,getShowHeader:a,getShowFooter:P,omit:G,getContentClass:f}}});function je(e,t,n,F,l,$){const p=O("PageHeader"),i=O("PageFooter");return k(),D("div",{class:W(e.getClass),ref:"wrapperRef"},[e.getShowHeader?(k(),Q(p,ve({key:0,ghost:e.ghost,title:e.title},e.omit(e.$attrs,"class"),{ref:"headerRef"}),he({default:z(()=>[e.content?(k(),D(be,{key:0},[Fe($e(e.content),1)],64)):y(e.$slots,"headerContent",{key:1})]),_:2},[me(e.getHeaderSlots,r=>({name:r,fn:z(H=>[y(e.$slots,r,He(Ce(H||{})))])}))]),1040,["ghost","title"])):Y("",!0),N("div",{class:W(["overflow-hidden",e.getContentClass]),style:U(e.getContentStyle),ref:"contentRef"},[y(e.$slots,"default")],6),e.getShowFooter?(k(),Q(i,{key:1,ref:"footerRef"},{left:z(()=>[y(e.$slots,"leftFooter")]),right:z(()=>[y(e.$slots,"rightFooter")]),_:3},512)):Y("",!0)],2)}var ke=q(Be,[["render",je]]);J(ee);const Ie=J(ke),We="PageWrapperFixedHeight";export{Ie as P,We as a,we as o};