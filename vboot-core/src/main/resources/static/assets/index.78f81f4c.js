var ye=Object.defineProperty;var x=Object.getOwnPropertySymbols;var Be=Object.prototype.hasOwnProperty,he=Object.prototype.propertyIsEnumerable;var X=(e,o,r)=>o in e?ye(e,o,{enumerable:!0,configurable:!0,writable:!0,value:r}):e[o]=r,k=(e,o)=>{for(var r in o||(o={}))Be.call(o,r)&&X(e,r,o[r]);if(x)for(var r of x(o))he.call(o,r)&&X(e,r,o[r]);return e};import{B as Ge,a as Pe}from"./index.b3a760e0.js";import{A as v,j as We,u as n,w as i,b2 as a,ab as b,a4 as me,a0 as j,B as Fe,D as we}from"./vendor.8e08a5be.js";/* empty css               */import{F as c,be as K,bf as h,bg as M,M as f,m as R,bh as $,b as V,a0 as U,V as Q,bi as Y,bj as Z,Q as q,bk as He,bl as xe,bm as Xe,bn as ke,$ as ve,q as je,d as Ke,bo as $e,bp as Ve,bq as Qe,_ as Ye,I as Ze}from"./index.1f50be75.js";import{c as A,u as qe,a as ze}from"./index.03324be0.js";const Je=A(()=>c(()=>import("./TypePicker.6ad7e5f3.js"),["assets/TypePicker.6ad7e5f3.js","assets/TypePicker.3d15c309.css","assets/vendor.8e08a5be.js","assets/vendor.7659cf59.css","assets/index.1f50be75.js","assets/index.60934752.css"])),G=A(()=>c(()=>import("./ThemeColorPicker.94bb3cd2.js"),["assets/ThemeColorPicker.94bb3cd2.js","assets/ThemeColorPicker.8eb61909.css","assets/index.00780982.css","assets/index.17eb4c41.css","assets/index.729da5f2.css","assets/index.1f50be75.js","assets/index.60934752.css","assets/vendor.8e08a5be.js","assets/vendor.7659cf59.css","assets/index.b3a760e0.js","assets/index.5c7227e9.css","assets/index.03324be0.js","assets/index.e9934e3f.css","assets/index.39c53391.js","assets/index.0935299d.css","assets/useWindowSizeFn.2016176c.js","assets/useContentViewHeight.78015564.js","assets/useSortable.4b691ef1.js","assets/lock.1d41c0c8.js"])),et=A(()=>c(()=>import("./SettingFooter.10be210f.js"),["assets/SettingFooter.10be210f.js","assets/SettingFooter.0be25544.css","assets/index.1f50be75.js","assets/index.60934752.css","assets/vendor.8e08a5be.js","assets/vendor.7659cf59.css"])),_=A(()=>c(()=>import("./SwitchItem.9c7dfc1a.js"),["assets/SwitchItem.9c7dfc1a.js","assets/SwitchItem.24688c5a.css","assets/index.dbb17ce2.css","assets/index.00780982.css","assets/index.17eb4c41.css","assets/index.729da5f2.css","assets/vendor.8e08a5be.js","assets/vendor.7659cf59.css","assets/index.1f50be75.js","assets/index.60934752.css","assets/index.b3a760e0.js","assets/index.5c7227e9.css","assets/index.03324be0.js","assets/index.e9934e3f.css","assets/index.39c53391.js","assets/index.0935299d.css","assets/useWindowSizeFn.2016176c.js","assets/useContentViewHeight.78015564.js","assets/useSortable.4b691ef1.js","assets/lock.1d41c0c8.js"])),I=A(()=>c(()=>import("./SelectItem.9ac610bf.js"),["assets/SelectItem.9ac610bf.js","assets/SelectItem.3b3b269f.css","assets/index.00780982.css","assets/index.17eb4c41.css","assets/index.729da5f2.css","assets/vendor.8e08a5be.js","assets/vendor.7659cf59.css","assets/index.1f50be75.js","assets/index.60934752.css","assets/index.b3a760e0.js","assets/index.5c7227e9.css","assets/index.03324be0.js","assets/index.e9934e3f.css","assets/index.39c53391.js","assets/index.0935299d.css","assets/useWindowSizeFn.2016176c.js","assets/useContentViewHeight.78015564.js","assets/useSortable.4b691ef1.js","assets/lock.1d41c0c8.js"])),z=A(()=>c(()=>import("./InputNumberItem.5941ddc4.js"),["assets/InputNumberItem.5941ddc4.js","assets/InputNumberItem.5677bd64.css","assets/index.1446549d.css","assets/index.00780982.css","assets/index.17eb4c41.css","assets/index.729da5f2.css","assets/vendor.8e08a5be.js","assets/vendor.7659cf59.css","assets/index.1f50be75.js","assets/index.60934752.css","assets/index.b3a760e0.js","assets/index.5c7227e9.css","assets/index.03324be0.js","assets/index.e9934e3f.css","assets/index.39c53391.js","assets/index.0935299d.css","assets/useWindowSizeFn.2016176c.js","assets/useContentViewHeight.78015564.js","assets/useSortable.4b691ef1.js","assets/lock.1d41c0c8.js"])),{t:l}=V();var t;(function(e){e[e.CHANGE_LAYOUT=0]="CHANGE_LAYOUT",e[e.CHANGE_THEME_COLOR=1]="CHANGE_THEME_COLOR",e[e.CHANGE_THEME=2]="CHANGE_THEME",e[e.MENU_HAS_DRAG=3]="MENU_HAS_DRAG",e[e.MENU_ACCORDION=4]="MENU_ACCORDION",e[e.MENU_TRIGGER=5]="MENU_TRIGGER",e[e.MENU_TOP_ALIGN=6]="MENU_TOP_ALIGN",e[e.MENU_COLLAPSED=7]="MENU_COLLAPSED",e[e.MENU_COLLAPSED_SHOW_TITLE=8]="MENU_COLLAPSED_SHOW_TITLE",e[e.MENU_WIDTH=9]="MENU_WIDTH",e[e.MENU_SHOW_SIDEBAR=10]="MENU_SHOW_SIDEBAR",e[e.MENU_THEME=11]="MENU_THEME",e[e.MENU_SPLIT=12]="MENU_SPLIT",e[e.MENU_FIXED=13]="MENU_FIXED",e[e.MENU_CLOSE_MIX_SIDEBAR_ON_CHANGE=14]="MENU_CLOSE_MIX_SIDEBAR_ON_CHANGE",e[e.MENU_TRIGGER_MIX_SIDEBAR=15]="MENU_TRIGGER_MIX_SIDEBAR",e[e.MENU_FIXED_MIX_SIDEBAR=16]="MENU_FIXED_MIX_SIDEBAR",e[e.HEADER_SHOW=17]="HEADER_SHOW",e[e.HEADER_THEME=18]="HEADER_THEME",e[e.HEADER_FIXED=19]="HEADER_FIXED",e[e.HEADER_SEARCH=20]="HEADER_SEARCH",e[e.TABS_SHOW_QUICK=21]="TABS_SHOW_QUICK",e[e.TABS_SHOW_REDO=22]="TABS_SHOW_REDO",e[e.TABS_SHOW=23]="TABS_SHOW",e[e.TABS_SHOW_FOLD=24]="TABS_SHOW_FOLD",e[e.LOCK_TIME=25]="LOCK_TIME",e[e.FULL_CONTENT=26]="FULL_CONTENT",e[e.CONTENT_MODE=27]="CONTENT_MODE",e[e.SHOW_BREADCRUMB=28]="SHOW_BREADCRUMB",e[e.SHOW_BREADCRUMB_ICON=29]="SHOW_BREADCRUMB_ICON",e[e.GRAY_MODE=30]="GRAY_MODE",e[e.COLOR_WEAK=31]="COLOR_WEAK",e[e.SHOW_LOGO=32]="SHOW_LOGO",e[e.SHOW_FOOTER=33]="SHOW_FOOTER",e[e.ROUTER_TRANSITION=34]="ROUTER_TRANSITION",e[e.OPEN_PROGRESS=35]="OPEN_PROGRESS",e[e.OPEN_PAGE_LOADING=36]="OPEN_PAGE_LOADING",e[e.OPEN_ROUTE_TRANSITION=37]="OPEN_ROUTE_TRANSITION"})(t||(t={}));const tt=[{value:K.FULL,label:l("layout.setting.contentModeFull")},{value:K.FIXED,label:l("layout.setting.contentModeFixed")}],nt=[{value:h.CENTER,label:l("layout.setting.topMenuAlignRight")},{value:h.START,label:l("layout.setting.topMenuAlignLeft")},{value:h.END,label:l("layout.setting.topMenuAlignCenter")}],ot=e=>[{value:U.NONE,label:l("layout.setting.menuTriggerNone")},{value:U.FOOTER,label:l("layout.setting.menuTriggerBottom")},...e?[]:[{value:U.HEADER,label:l("layout.setting.menuTriggerTop")}]],it=[M.ZOOM_FADE,M.FADE,M.ZOOM_OUT,M.FADE_SIDE,M.FADE_BOTTOM,M.FADE_SCALE].map(e=>({label:e,value:e})),st=[{title:l("layout.setting.menuTypeSidebar"),mode:f.INLINE,type:R.SIDEBAR},{title:l("layout.setting.menuTypeMix"),mode:f.INLINE,type:R.MIX},{title:l("layout.setting.menuTypeTopMenu"),mode:f.HORIZONTAL,type:R.TOP_MENU},{title:l("layout.setting.menuTypeMixSidebar"),mode:f.INLINE,type:R.MIX_SIDEBAR}],_t=[{value:$.HOVER,label:l("layout.setting.triggerHover")},{value:$.CLICK,label:l("layout.setting.triggerClick")}];function rt(e,o){const r=Q(),O=lt(e,o);r.setProjectConfig(O),e===t.CHANGE_THEME&&(Y(),Z())}function lt(e,o){const r=Q(),{getThemeColor:O,getDarkMode:D}=q();switch(e){case t.CHANGE_LAYOUT:const{mode:C,type:N,split:u}=o;return{menuSetting:k({mode:C,type:N,collapsed:!1,show:!0,hidden:!1},u===void 0?{split:u}:{})};case t.CHANGE_THEME_COLOR:return O.value===o?{}:(ke(o),{themeColor:o});case t.CHANGE_THEME:return D.value===o?{}:(Xe(o),{});case t.MENU_HAS_DRAG:return{menuSetting:{canDrag:o}};case t.MENU_ACCORDION:return{menuSetting:{accordion:o}};case t.MENU_TRIGGER:return{menuSetting:{trigger:o}};case t.MENU_TOP_ALIGN:return{menuSetting:{topMenuAlign:o}};case t.MENU_COLLAPSED:return{menuSetting:{collapsed:o}};case t.MENU_WIDTH:return{menuSetting:{menuWidth:o}};case t.MENU_SHOW_SIDEBAR:return{menuSetting:{show:o}};case t.MENU_COLLAPSED_SHOW_TITLE:return{menuSetting:{collapsedShowTitle:o}};case t.MENU_THEME:return Z(o),{menuSetting:{bgColor:o}};case t.MENU_SPLIT:return{menuSetting:{split:o}};case t.MENU_CLOSE_MIX_SIDEBAR_ON_CHANGE:return{menuSetting:{closeMixSidebarOnChange:o}};case t.MENU_FIXED:return{menuSetting:{fixed:o}};case t.MENU_TRIGGER_MIX_SIDEBAR:return{menuSetting:{mixSideTrigger:o}};case t.MENU_FIXED_MIX_SIDEBAR:return{menuSetting:{mixSideFixed:o}};case t.OPEN_PAGE_LOADING:return r.setPageLoading(!1),{transitionSetting:{openPageLoading:o}};case t.ROUTER_TRANSITION:return{transitionSetting:{basicTransition:o}};case t.OPEN_ROUTE_TRANSITION:return{transitionSetting:{enable:o}};case t.OPEN_PROGRESS:return{transitionSetting:{openNProgress:o}};case t.LOCK_TIME:return{lockTime:o};case t.FULL_CONTENT:return{fullContent:o};case t.CONTENT_MODE:return{contentMode:o};case t.SHOW_BREADCRUMB:return{showBreadCrumb:o};case t.SHOW_BREADCRUMB_ICON:return{showBreadCrumbIcon:o};case t.GRAY_MODE:return xe(o),{grayMode:o};case t.SHOW_FOOTER:return{showFooter:o};case t.COLOR_WEAK:return He(o),{colorWeak:o};case t.SHOW_LOGO:return{showLogo:o};case t.TABS_SHOW_QUICK:return{multiTabsSetting:{showQuick:o}};case t.TABS_SHOW:return{multiTabsSetting:{show:o}};case t.TABS_SHOW_REDO:return{multiTabsSetting:{showRedo:o}};case t.TABS_SHOW_FOLD:return{multiTabsSetting:{showFold:o}};case t.HEADER_THEME:return Y(o),{headerSetting:{bgColor:o}};case t.HEADER_SEARCH:return{headerSetting:{showSearch:o}};case t.HEADER_FIXED:return{headerSetting:{fixed:o}};case t.HEADER_SHOW:return{headerSetting:{show:o}};default:return{}}}const{t:s}=V();var at=v({name:"SettingDrawer",setup(e,{attrs:o}){const{getContentMode:r,getShowFooter:O,getShowBreadCrumb:D,getShowBreadCrumbIcon:C,getShowLogo:N,getFullContent:u,getColorWeak:y,getGrayMode:J,getLockTime:ee,getShowDarkModeToggle:P,getThemeColor:te}=q(),{getOpenPageLoading:ne,getBasicTransition:oe,getEnableTransition:W,getOpenNProgress:ie}=ve(),{getIsHorizontal:B,getShowMenu:m,getMenuType:F,getTrigger:se,getCollapsedShowTitle:_e,getMenuFixed:re,getCollapsed:w,getCanDrag:le,getTopMenuAlign:ae,getAccordion:Ee,getMenuWidth:ge,getMenuBgColor:Oe,getIsTopMenu:ue,getSplit:p,getIsMixSidebar:E,getCloseMixSidebarOnChange:Se,getMixSideTrigger:Te,getMixSideFixed:de}=je(),{getShowHeader:S,getFixed:ce,getHeaderBgColor:Me,getShowSearch:Ae}=qe(),{getShowMultipleTab:L,getShowQuick:Ne,getShowRedo:Re,getShowFold:Ie}=ze(),g=We(()=>n(m)&&!n(B));function De(){return i(b,null,[i(Je,{menuTypeList:st,handler:T=>{rt(t.CHANGE_LAYOUT,{mode:T.mode,type:T.type,split:n(B)?!1:void 0})},def:n(F)},null)])}function Ce(){return i(G,{colorList:Ve,def:n(Me),event:t.HEADER_THEME},null)}function pe(){return i(G,{colorList:Qe,def:n(Oe),event:t.MENU_THEME},null)}function Le(){return i(G,{colorList:$e,def:n(te),event:t.CHANGE_THEME_COLOR},null)}function be(){let T=n(se);const H=ot(n(p));return H.some(d=>d.value===T)||(T=U.FOOTER),i(b,null,[i(_,{title:s("layout.setting.splitMenu"),event:t.MENU_SPLIT,def:n(p),disabled:!n(g)||n(F)!==R.MIX},null),i(_,{title:s("layout.setting.mixSidebarFixed"),event:t.MENU_FIXED_MIX_SIDEBAR,def:n(de),disabled:!n(E)},null),i(_,{title:s("layout.setting.closeMixSidebarOnChange"),event:t.MENU_CLOSE_MIX_SIDEBAR_ON_CHANGE,def:n(Se),disabled:!n(E)},null),i(_,{title:s("layout.setting.menuCollapse"),event:t.MENU_COLLAPSED,def:n(w),disabled:!n(g)},null),i(_,{title:s("layout.setting.menuDrag"),event:t.MENU_HAS_DRAG,def:n(le),disabled:!n(g)},null),i(_,{title:s("layout.setting.menuSearch"),event:t.HEADER_SEARCH,def:n(Ae),disabled:!n(S)},null),i(_,{title:s("layout.setting.menuAccordion"),event:t.MENU_ACCORDION,def:n(Ee),disabled:!n(g)},null),i(_,{title:s("layout.setting.collapseMenuDisplayName"),event:t.MENU_COLLAPSED_SHOW_TITLE,def:n(_e),disabled:!n(g)||!n(w)||n(E)},null),i(_,{title:s("layout.setting.fixedHeader"),event:t.HEADER_FIXED,def:n(ce),disabled:!n(S)},null),i(_,{title:s("layout.setting.fixedSideBar"),event:t.MENU_FIXED,def:n(re),disabled:!n(g)||n(E)},null),i(I,{title:s("layout.setting.mixSidebarTrigger"),event:t.MENU_TRIGGER_MIX_SIDEBAR,def:n(Te),options:_t,disabled:!n(E)},null),i(I,{title:s("layout.setting.topMenuLayout"),event:t.MENU_TOP_ALIGN,def:n(ae),options:nt,disabled:!n(S)||n(p)||!n(ue)&&!n(p)||n(E)},null),i(I,{title:s("layout.setting.menuCollapseButton"),event:t.MENU_TRIGGER,def:T,options:H,disabled:!n(g)||n(E)},null),i(I,{title:s("layout.setting.contentMode"),event:t.CONTENT_MODE,def:n(r),options:tt},null),i(z,{title:s("layout.setting.autoScreenLock"),min:0,event:t.LOCK_TIME,defaultValue:n(ee),formatter:d=>parseInt(d)===0?`0(${s("layout.setting.notAutoScreenLock")})`:`${d}${s("layout.setting.minute")}`},null),i(z,{title:s("layout.setting.expandedMenuWidth"),max:600,min:100,step:10,event:t.MENU_WIDTH,disabled:!n(g),defaultValue:n(ge),formatter:d=>`${parseInt(d)}px`},null)])}function fe(){return i(b,null,[i(_,{title:s("layout.setting.breadcrumb"),event:t.SHOW_BREADCRUMB,def:n(D),disabled:!n(S)},null),i(_,{title:s("layout.setting.breadcrumbIcon"),event:t.SHOW_BREADCRUMB_ICON,def:n(C),disabled:!n(S)},null),i(_,{title:s("layout.setting.tabs"),event:t.TABS_SHOW,def:n(L)},null),i(_,{title:s("layout.setting.tabsRedoBtn"),event:t.TABS_SHOW_REDO,def:n(Re),disabled:!n(L)},null),i(_,{title:s("layout.setting.tabsQuickBtn"),event:t.TABS_SHOW_QUICK,def:n(Ne),disabled:!n(L)},null),i(_,{title:s("layout.setting.tabsFoldBtn"),event:t.TABS_SHOW_FOLD,def:n(Ie),disabled:!n(L)},null),i(_,{title:s("layout.setting.sidebar"),event:t.MENU_SHOW_SIDEBAR,def:n(m),disabled:n(B)},null),i(_,{title:s("layout.setting.header"),event:t.HEADER_SHOW,def:n(S)},null),i(_,{title:"Logo",event:t.SHOW_LOGO,def:n(N),disabled:n(E)},null),i(_,{title:s("layout.setting.footer"),event:t.SHOW_FOOTER,def:n(O)},null),i(_,{title:s("layout.setting.fullContent"),event:t.FULL_CONTENT,def:n(u)},null),i(_,{title:s("layout.setting.grayMode"),event:t.GRAY_MODE,def:n(J)},null),i(_,{title:s("layout.setting.colorWeak"),event:t.COLOR_WEAK,def:n(y)},null)])}function Ue(){return i(b,null,[i(_,{title:s("layout.setting.progress"),event:t.OPEN_PROGRESS,def:n(ie)},null),i(_,{title:s("layout.setting.switchLoading"),event:t.OPEN_PAGE_LOADING,def:n(ne)},null),i(_,{title:s("layout.setting.switchAnimation"),event:t.OPEN_ROUTE_TRANSITION,def:n(W)},null),i(I,{title:s("layout.setting.animationType"),event:t.ROUTER_TRANSITION,def:n(oe),options:it,disabled:!n(W)},null)])}return()=>i(Ge,me(o,{title:s("layout.setting.drawerTitle"),width:330,class:"setting-drawer"}),{default:()=>[n(P)&&i(a,null,{default:()=>s("layout.setting.darkMode")}),n(P)&&i(Ke,{class:"mx-auto"},null),i(a,null,{default:()=>s("layout.setting.navMode")}),De(),i(a,null,{default:()=>s("layout.setting.sysTheme")}),Le(),i(a,null,{default:()=>s("layout.setting.headerTheme")}),Ce(),i(a,null,{default:()=>s("layout.setting.sidebarTheme")}),pe(),i(a,null,{default:()=>s("layout.setting.interfaceFunction")}),be(),i(a,null,{default:()=>s("layout.setting.interfaceDisplay")}),fe(),i(a,null,{default:()=>s("layout.setting.animation")}),Ue(),i(a,null,null),i(et,null,null)]})}});const Et=v({name:"SettingButton",components:{SettingDrawer:at,Icon:Ze},setup(){const[e,{openDrawer:o}]=Pe();return{register:e,openDrawer:o}}});function gt(e,o,r,O,D,C){const N=j("Icon"),u=j("SettingDrawer");return Fe(),we("div",{onClick:o[0]||(o[0]=y=>e.openDrawer(!0))},[i(N,{icon:"ion:settings-outline"}),i(u,{onRegister:e.register},null,8,["onRegister"])])}var Ot=Ye(Et,[["render",gt]]),Nt=Object.freeze({__proto__:null,[Symbol.toStringTag]:"Module",default:Ot});export{rt as b,Nt as i};
