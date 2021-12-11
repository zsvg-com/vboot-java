var re=Object.defineProperty,ae=Object.defineProperties;var oe=Object.getOwnPropertyDescriptors;var R=Object.getOwnPropertySymbols;var ie=Object.prototype.hasOwnProperty,ce=Object.prototype.propertyIsEnumerable;var k=Math.pow,L=(r,e,t)=>e in r?re(r,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[e]=t,m=(r,e)=>{for(var t in e||(e={}))ie.call(e,t)&&L(r,t,e[t]);if(R)for(var t of R(e))ce.call(e,t)&&L(r,t,e[t]);return r},E=(r,e)=>ae(r,oe(e));import{p as v,a as le,_ as ue,w as he}from"./index.1f50be75.js";import{A as fe,ao as de,r as ge,j as pe,u as T,am as me,S as be,a0 as ye,B as P,D as Me,a1 as ke,aD as Ee,ap as Se,a6 as ve,a7 as Te,aC as we,aS as Ie,a4 as Ae,ad as De,H as N,K as w}from"./vendor.8e08a5be.js";const xe=r=>Object.keys(r).length===0,I=(r,e)=>r.push.apply(r,e),je=(r,e)=>r.split("").map(s=>e[s]||s).join(""),M=r=>r.sort((e,t)=>e.i-t.i||e.j-t.j),O=r=>{const e={};let t=1;return r.forEach(s=>{e[s]=t,t+=1}),e};var Ce={4:[[1,2],[2,3]],5:[[1,3],[2,3]],6:[[1,2],[2,4],[4,5]],7:[[1,3],[2,3],[4,5],[4,6]],8:[[2,4],[4,6]]};const $=2050,G=1e3,_e=Ce,Re=10,Le=1e4,Y=10,W=50,U=20,V=/^[A-Z\xbf-\xdf][^A-Z\xbf-\xdf]+$/,Pe=/^[^A-Z\xbf-\xdf]+[A-Z\xbf-\xdf]$/,Ne=/^[A-Z\xbf-\xdf]+$/,B=/^[^a-z\xdf-\xff]+$/,Oe=/^[a-z\xdf-\xff]+$/,$e=/^[^A-Z\xbf-\xdf]+$/,Ge=/[a-z\xdf-\xff]/,Ye=/[A-Z\xbf-\xdf]/,We=/[^A-Za-z\xbf-\xdf]/gi,Ue=/^\d+$/,A=new Date().getFullYear(),Ve={recentYear:/19\d\d|200\d|201\d|202\d/g};class Be{match({password:e}){const t=[...this.getMatchesWithoutSeparator(e),...this.getMatchesWithSeparator(e)],s=this.filterNoise(t);return M(s)}getMatchesWithSeparator(e){const t=[],s=/^(\d{1,4})([\s/\\_.-])(\d{1,2})\2(\d{1,4})$/;for(let n=0;n<=Math.abs(e.length-6);n+=1)for(let a=n+5;a<=n+9&&!(a>=e.length);a+=1){const o=e.slice(n,+a+1||9e9),i=s.exec(o);if(i!=null){const c=this.mapIntegersToDayMonthYear([parseInt(i[1],10),parseInt(i[3],10),parseInt(i[4],10)]);c!=null&&t.push({pattern:"date",token:o,i:n,j:a,separator:i[2],year:c.year,month:c.month,day:c.day})}}return t}getMatchesWithoutSeparator(e){const t=[],s=/^\d{4,8}$/,n=a=>Math.abs(a.year-A);for(let a=0;a<=Math.abs(e.length-4);a+=1)for(let o=a+3;o<=a+7&&!(o>=e.length);o+=1){const i=e.slice(a,+o+1||9e9);if(s.exec(i)){const c=[],u=i.length;if(_e[u].forEach(([f,d])=>{const p=this.mapIntegersToDayMonthYear([parseInt(i.slice(0,f),10),parseInt(i.slice(f,d),10),parseInt(i.slice(d),10)]);p!=null&&c.push(p)}),c.length>0){let f=c[0],d=n(c[0]);c.slice(1).forEach(p=>{const y=n(p);y<d&&(f=p,d=y)}),t.push({pattern:"date",token:i,i:a,j:o,separator:"",year:f.year,month:f.month,day:f.day})}}}return t}filterNoise(e){return e.filter(t=>{let s=!1;const n=e.length;for(let a=0;a<n;a+=1){const o=e[a];if(t!==o&&o.i<=t.i&&o.j>=t.j){s=!0;break}}return!s})}mapIntegersToDayMonthYear(e){if(e[1]>31||e[1]<=0)return null;let t=0,s=0,n=0;for(let a=0,o=e.length;a<o;a+=1){const i=e[a];if(i>99&&i<G||i>$)return null;i>31&&(s+=1),i>12&&(t+=1),i<=0&&(n+=1)}return s>=2||t===3||n>=2?null:this.getDayMonth(e)}getDayMonth(e){const t=[[e[2],e.slice(0,2)],[e[0],e.slice(1,3)]],s=t.length;for(let n=0;n<s;n+=1){const[a,o]=t[n];if(G<=a&&a<=$){const i=this.mapIntegersToDayMonth(o);return i!=null?{year:a,month:i.month,day:i.day}:null}}for(let n=0;n<s;n+=1){const[a,o]=t[n],i=this.mapIntegersToDayMonth(o);if(i!=null)return{year:this.twoToFourDigitYear(a),month:i.month,day:i.day}}return null}mapIntegersToDayMonth(e){const t=[e,e.slice().reverse()];for(let s=0;s<t.length;s+=1){const n=t[s],a=n[0],o=n[1];if(a>=1&&a<=31&&o>=1&&o<=12)return{day:a,month:o}}return null}twoToFourDigitYear(e){return e>99?e:e>50?e+1900:e+2e3}}var Fe={a:["4","@"],b:["8"],c:["(","{","[","<"],e:["3"],g:["6","9"],i:["1","!","|"],l:["1","|","7"],o:["0"],s:["$","5"],t:["+","7"],x:["%"],z:["2"]},D={warnings:{straightRow:"straightRow",keyPattern:"keyPattern",simpleRepeat:"simpleRepeat",extendedRepeat:"extendedRepeat",sequences:"sequences",recentYears:"recentYears",dates:"dates",topTen:"topTen",topHundred:"topHundred",common:"common",similarToCommon:"similarToCommon",wordByItself:"wordByItself",namesByThemselves:"namesByThemselves",commonNames:"commonNames",userInputs:"userInputs"},suggestions:{l33t:"l33t",reverseWords:"reverseWords",allUppercase:"allUppercase",capitalization:"capitalization",dates:"dates",recentYears:"recentYears",associatedYears:"associatedYears",sequences:"sequences",repeated:"repeated",longerKeyboardPattern:"longerKeyboardPattern",anotherWord:"anotherWord",useWords:"useWords",noNeed:"noNeed"},timeEstimation:{ltSecond:"ltSecond",second:"second",seconds:"seconds",minute:"minute",minutes:"minutes",hour:"hour",hours:"hours",day:"day",days:"days",month:"month",months:"months",year:"year",years:"years",centuries:"centuries"}};class He{constructor(){this.matchers={},this.l33tTable=Fe,this.dictionary={userInput:[]},this.rankedDictionaries={},this.translations=D,this.graphs={},this.availableGraphs=[],this.setRankedDictionaries()}setOptions(e={}){e.l33tTable&&(this.l33tTable=e.l33tTable),e.dictionary&&(this.dictionary=e.dictionary,this.setRankedDictionaries()),e.translations&&this.setTranslations(e.translations),e.graphs&&(this.graphs=e.graphs)}setTranslations(e){if(this.checkCustomTranslations(e))this.translations=e;else throw new Error("Invalid translations object fallback to keys")}checkCustomTranslations(e){let t=!0;return Object.keys(D).forEach(s=>{if(s in e){const n=s;Object.keys(D[n]).forEach(a=>{a in e[n]||(t=!1)})}else t=!1}),t}setRankedDictionaries(){const e={};Object.keys(this.dictionary).forEach(t=>{const s=this.dictionary[t];if(t==="userInputs"){const n=[];s.forEach(a=>{const o=typeof a;(o==="string"||o==="number"||o==="boolean")&&n.push(a.toString().toLowerCase())}),e[t]=O(n)}else e[t]=O(s)}),this.rankedDictionaries=e}addMatcher(e,t){this.matchers[e]?console.info("Matcher already exists"):this.matchers[e]=t}}var l=new He;class qe{constructor(e){this.defaultMatch=e}match({password:e}){const t=e.split("").reverse().join("");return this.defaultMatch({password:t}).map(s=>E(m({},s),{token:s.token.split("").reverse().join(""),reversed:!0,i:e.length-1-s.j,j:e.length-1-s.i}))}}class ze{constructor(e){this.defaultMatch=e}match({password:e}){const t=[],s=this.enumerateL33tSubs(this.relevantL33tSubtable(e,l.l33tTable));for(let n=0;n<s.length;n+=1){const a=s[n];if(xe(a))break;const o=je(e,a);this.defaultMatch({password:o}).forEach(c=>{const u=e.slice(c.i,+c.j+1||9e9);if(u.toLowerCase()!==c.matchedWord){const h={};Object.keys(a).forEach(d=>{const p=a[d];u.indexOf(d)!==-1&&(h[d]=p)});const f=Object.keys(h).map(d=>`${d} -> ${h[d]}`).join(", ");t.push(E(m({},c),{l33t:!0,token:u,sub:h,subDisplay:f}))}})}return t.filter(n=>n.token.length>1)}relevantL33tSubtable(e,t){const s={},n={};return e.split("").forEach(a=>{s[a]=!0}),Object.keys(t).forEach(a=>{const i=t[a].filter(c=>c in s);i.length>0&&(n[a]=i)}),n}enumerateL33tSubs(e){const t=Object.keys(e);return this.getSubs(t,[[]],e).map(n=>{const a={};return n.forEach(([o,i])=>{a[o]=i}),a})}getSubs(e,t,s){if(!e.length)return t;const n=e[0],a=e.slice(1),o=[];s[n].forEach(c=>{t.forEach(u=>{let h=-1;for(let f=0;f<u.length;f+=1)if(u[f][0]===c){h=f;break}if(h===-1){const f=u.concat([[c,n]]);o.push(f)}else{const f=u.slice(0);f.splice(h,1),f.push([c,n]),o.push(u),o.push(f)}})});const i=this.dedup(o);return a.length?this.getSubs(a,i,s):i}dedup(e){const t=[],s={};return e.forEach(n=>{const a=n.map((i,c)=>[i,c]);a.sort();const o=a.map(([i,c])=>`${i},${c}`).join("-");o in s||(s[o]=!0,t.push(n))}),t}}class Ke{constructor(){this.l33t=new ze(this.defaultMatch),this.reverse=new qe(this.defaultMatch)}match({password:e}){const t=[...this.defaultMatch({password:e}),...this.reverse.match({password:e}),...this.l33t.match({password:e})];return M(t)}defaultMatch({password:e}){const t=[],s=e.length,n=e.toLowerCase();return Object.keys(l.rankedDictionaries).forEach(a=>{const o=l.rankedDictionaries[a];for(let i=0;i<s;i+=1)for(let c=i;c<s;c+=1)if(n.slice(i,+c+1||9e9)in o){const u=n.slice(i,+c+1||9e9),h=o[u];t.push({pattern:"dictionary",i,j:c,token:e.slice(i,+c+1||9e9),matchedWord:u,rank:h,dictionaryName:a,reversed:!1,l33t:!1})}}),t}}class Ze{match({password:e,regexes:t=Ve}){const s=[];return Object.keys(t).forEach(n=>{const a=t[n];a.lastIndex=0;const o=a.exec(e);if(o){const i=o[0];s.push({pattern:"regex",token:i,i:o.index,j:o.index+o[0].length-1,regexName:n,regexMatch:o})}}),M(s)}}var b={nCk(r,e){let t=r;if(e>t)return 0;if(e===0)return 1;let s=1;for(let n=1;n<=e;n+=1)s*=t,s/=n,t-=1;return s},log10(r){return Math.log(r)/Math.log(10)},log2(r){return Math.log(r)/Math.log(2)},factorial(r){let e=1;for(let t=2;t<=r;t+=1)e*=t;return e}},Xe=({token:r})=>{let e=k(Re,r.length);e===Number.POSITIVE_INFINITY&&(e=Number.MAX_VALUE);let t;return r.length===1?t=Y+1:t=W+1,Math.max(e,t)},Je=({year:r,separator:e})=>{let s=Math.max(Math.abs(r-A),U)*365;return e&&(s*=4),s};const Qe=r=>{const e=r.split(""),t=e.filter(o=>o.match(Ge)).length,s=e.filter(o=>o.match(Ye)).length;let n=0;const a=Math.min(t,s);for(let o=1;o<=a;o+=1)n+=b.nCk(t+s,o);return n};var et=r=>{const e=r.replace(We,"");if(e.match($e)||e.toLowerCase()===e)return 1;const t=[V,Pe,B],s=t.length;for(let n=0;n<s;n+=1){const a=t[n];if(e.match(a))return 2}return Qe(e)};const tt=({subs:r,subbed:e,token:t})=>{const s=r[e],n=t.toLowerCase().split(""),a=n.filter(i=>i===e).length,o=n.filter(i=>i===s).length;return{subbedCount:a,unsubbedCount:o}};var st=({l33t:r,sub:e,token:t})=>{if(!r)return 1;let s=1;const n=e;return Object.keys(n).forEach(a=>{const{subbedCount:o,unsubbedCount:i}=tt({subs:n,subbed:a,token:t});if(o===0||i===0)s*=2;else{const c=Math.min(i,o);let u=0;for(let h=1;h<=c;h+=1)u+=b.nCk(i+o,h);s*=u}}),s},nt=({rank:r,reversed:e,l33t:t,sub:s,token:n})=>{const a=r,o=et(n),i=st({l33t:t,sub:s,token:n}),c=e&&2||1,u=a*o*i*c;return{baseGuesses:a,uppercaseVariations:o,l33tVariations:i,calculation:u}},rt=({regexName:r,regexMatch:e,token:t})=>{const s={alphaLower:26,alphaUpper:26,alpha:52,alphanumeric:62,digits:10,symbols:33};if(r in s)return k(s[r],t.length);switch(r){case"recentYear":return Math.max(Math.abs(parseInt(e[0],10)-A),U)}return 0},at=({baseGuesses:r,repeatCount:e})=>r*e,ot=({token:r,ascending:e})=>{const t=r.charAt(0);let s=0;return["a","A","z","Z","0","1","9"].includes(t)?s=4:t.match(/\d/)?s=10:s=26,e||(s*=2),s*r.length};const it=r=>{let e=0;return Object.keys(r).forEach(t=>{e+=r[t].filter(n=>!!n).length}),e/=Object.entries(r).length,e},ct=({token:r,graph:e,turns:t})=>{const s=Object.keys(l.graphs[e]).length,n=it(l.graphs[e]);let a=0;const o=r.length;for(let i=2;i<=o;i+=1){const c=Math.min(t,i-1);for(let u=1;u<=c;u+=1)a+=b.nCk(i-1,u-1)*s*k(n,u)}return a};var lt=({graph:r,token:e,shiftedCount:t,turns:s})=>{let n=ct({token:e,graph:r,turns:s});if(t){const a=e.length-t;if(t===0||a===0)n*=2;else{let o=0;for(let i=1;i<=Math.min(t,a);i+=1)o+=b.nCk(t+a,i);n*=o}}return Math.round(n)};const ut=(r,e)=>{let t=1;return r.token.length<e.length&&(r.token.length===1?t=Y:t=W),t},F={bruteforce:Xe,date:Je,dictionary:nt,regex:rt,repeat:at,sequence:ot,spatial:lt},ht=(r,e)=>F[r]?F[r](e):l.matchers[r]&&"scoring"in l.matchers[r]?l.matchers[r].scoring(e):0;var ft=(r,e)=>{const t={};if("guesses"in r&&r.guesses!=null)return r;const s=ut(r,e),n=ht(r.pattern,r);let a=0;typeof n=="number"?a=n:r.pattern==="dictionary"&&(a=n.calculation,t.baseGuesses=n.baseGuesses,t.uppercaseVariations=n.uppercaseVariations,t.l33tVariations=n.l33tVariations);const o=Math.max(a,s);return E(m(m({},r),t),{guesses:o,guessesLog10:b.log10(o)})};const g={password:"",optimal:{},excludeAdditive:!1,fillArray(r,e){const t=[];for(let s=0;s<r;s+=1){let n=[];e==="object"&&(n={}),t.push(n)}return t},makeBruteforceMatch(r,e){return{pattern:"bruteforce",token:this.password.slice(r,+e+1||9e9),i:r,j:e}},update(r,e){const t=r.j,s=ft(r,this.password);let n=s.guesses;e>1&&(n*=this.optimal.pi[s.i-1][e-1]);let a=b.factorial(e)*n;this.excludeAdditive||(a+=k(Le,e-1));let o=!1;Object.keys(this.optimal.g[t]).forEach(i=>{const c=this.optimal.g[t][i];parseInt(i,10)<=e&&c<=a&&(o=!0)}),o||(this.optimal.g[t][e]=a,this.optimal.m[t][e]=s,this.optimal.pi[t][e]=n)},bruteforceUpdate(r){let e=this.makeBruteforceMatch(0,r);this.update(e,1);for(let t=1;t<=r;t+=1){e=this.makeBruteforceMatch(t,r);const s=this.optimal.m[t-1];Object.keys(s).forEach(n=>{s[n].pattern!=="bruteforce"&&this.update(e,parseInt(n,10)+1)})}},unwind(r){const e=[];let t=r-1,s=0,n=1/0;const a=this.optimal.g[t];for(a&&Object.keys(a).forEach(o=>{const i=a[o];i<n&&(s=parseInt(o,10),n=i)});t>=0;){const o=this.optimal.m[t][s];e.unshift(o),t=o.i-1,s-=1}return e}};var H={mostGuessableMatchSequence(r,e,t=!1){g.password=r,g.excludeAdditive=t;const s=r.length;let n=g.fillArray(s,"array");e.forEach(c=>{n[c.j].push(c)}),n=n.map(c=>c.sort((u,h)=>u.i-h.i)),g.optimal={m:g.fillArray(s,"object"),pi:g.fillArray(s,"object"),g:g.fillArray(s,"object")};for(let c=0;c<s;c+=1)n[c].forEach(u=>{u.i>0?Object.keys(g.optimal.m[u.i-1]).forEach(h=>{g.update(u,parseInt(h,10)+1)}):g.update(u,1)}),g.bruteforceUpdate(c);const a=g.unwind(s),o=a.length,i=this.getGuesses(r,o);return{password:r,guesses:i,guessesLog10:b.log10(i),sequence:a}},getGuesses(r,e){const t=r.length;let s=0;return r.length===0?s=1:s=g.optimal.g[t-1][e],s}};class dt{match({password:e,omniMatch:t}){const s=[];let n=0;for(;n<e.length;){const a=this.getGreedyMatch(e,n),o=this.getLazyMatch(e,n);if(a==null)break;const{match:i,baseToken:c}=this.setMatchToken(a,o);if(i){const u=i.index+i[0].length-1,h=this.getBaseGuesses(c,t);s.push({pattern:"repeat",i:i.index,j:u,token:i[0],baseToken:c,baseGuesses:h,repeatCount:i[0].length/c.length}),n=u+1}}return s}getGreedyMatch(e,t){const s=/(.+)\1+/g;return s.lastIndex=t,s.exec(e)}getLazyMatch(e,t){const s=/(.+?)\1+/g;return s.lastIndex=t,s.exec(e)}setMatchToken(e,t){const s=/^(.+?)\1+$/;let n,a="";if(t&&e[0].length>t[0].length){n=e;const o=s.exec(n[0]);o&&(a=o[1])}else n=t,n&&(a=n[1]);return{match:n,baseToken:a}}getBaseGuesses(e,t){return H.mostGuessableMatchSequence(e,t.match(e)).guesses}}class gt{constructor(){this.MAX_DELTA=5}match({password:e}){const t=[];if(e.length===1)return[];let s=0,n=null;const a=e.length;for(let o=1;o<a;o+=1){const i=e.charCodeAt(o)-e.charCodeAt(o-1);if(n==null&&(n=i),i!==n){const c=o-1;this.update({i:s,j:c,delta:n,password:e,result:t}),s=c,n=i}}return this.update({i:s,j:a-1,delta:n,password:e,result:t}),t}update({i:e,j:t,delta:s,password:n,result:a}){if(t-e>1||Math.abs(s)===1){const o=Math.abs(s);if(o>0&&o<=this.MAX_DELTA){const i=n.slice(e,+t+1||9e9),{sequenceName:c,sequenceSpace:u}=this.getSequence(i);return a.push({pattern:"sequence",i:e,j:t,token:n.slice(e,+t+1||9e9),sequenceName:c,sequenceSpace:u,ascending:s>0})}}return null}getSequence(e){let t="unicode",s=26;return Oe.test(e)?(t="lower",s=26):Ne.test(e)?(t="upper",s=26):Ue.test(e)&&(t="digits",s=10),{sequenceName:t,sequenceSpace:s}}}class pt{constructor(){this.SHIFTED_RX=/[~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:"ZXCVBNM<>?]/}match({password:e}){const t=[];return Object.keys(l.graphs).forEach(s=>{const n=l.graphs[s];I(t,this.helper(e,n,s))}),M(t)}checkIfShifted(e,t,s){return!e.includes("keypad")&&this.SHIFTED_RX.test(t.charAt(s))?1:0}helper(e,t,s){let n;const a=[];let o=0;const i=e.length;for(;o<i-1;){let c=o+1,u=0,h=0;for(n=this.checkIfShifted(s,e,o);;){const f=e.charAt(c-1),d=t[f]||[];let p=!1,y=-1,j=-1;if(c<i){const se=e.charAt(c),ne=d.length;for(let S=0;S<ne;S+=1){const C=d[S];if(j+=1,C){const _=C.indexOf(se);if(_!==-1){p=!0,y=j,_===1&&(n+=1),u!==y&&(h+=1,u=y);break}}}}if(p)c+=1;else{c-o>2&&a.push({pattern:"spatial",i:o,j:c-1,token:e.slice(o,c),graph:s,turns:h,shiftedCount:n}),o=c;break}}}return a}}class mt{constructor(){this.matchers={date:Be,dictionary:Ke,regex:Ze,repeat:dt,sequence:gt,spatial:pt}}match(e){const t=[],s=[];return[...Object.keys(this.matchers),...Object.keys(l.matchers)].forEach(a=>{if(!this.matchers[a]&&!l.matchers[a])return;const o=this.matchers[a]?this.matchers[a]:l.matchers[a].Matching,c=new o().match({password:e,omniMatch:this});c instanceof Promise?(c.then(u=>{I(t,u)}),s.push(c)):I(t,c)}),s.length>0?new Promise(a=>Promise.all(s).then(()=>{a(M(t))})):M(t)}}const q=1,z=q*60,K=z*60,Z=K*24,X=Z*31,J=X*12,bt=J*100,x={second:q,minute:z,hour:K,day:Z,month:X,year:J,century:bt};class yt{translate(e,t){let s=e;t!==void 0&&t!==1&&(s+="s");const{timeEstimation:n}=l.translations;return n[s].replace("{base}",`${t}`)}estimateAttackTimes(e){const t={onlineThrottling100PerHour:e/(100/3600),onlineNoThrottling10PerSecond:e/10,offlineSlowHashing1e4PerSecond:e/1e4,offlineFastHashing1e10PerSecond:e/1e10},s={onlineThrottling100PerHour:"",onlineNoThrottling10PerSecond:"",offlineSlowHashing1e4PerSecond:"",offlineFastHashing1e10PerSecond:""};return Object.keys(t).forEach(n=>{const a=t[n];s[n]=this.displayTime(a)}),{crackTimesSeconds:t,crackTimesDisplay:s,score:this.guessesToScore(e)}}guessesToScore(e){const t=5;return e<1e3+t?0:e<1e6+t?1:e<1e8+t?2:e<1e10+t?3:4}displayTime(e){let t="centuries",s;const n=Object.keys(x),a=n.findIndex(o=>e<x[o]);return a>-1&&(t=n[a-1],a!==0?s=Math.round(e/x[t]):t="ltSecond"),this.translate(t,s)}}var Mt=()=>null,kt=()=>({warning:l.translations.warnings.dates,suggestions:[l.translations.suggestions.dates]});const Et=(r,e)=>{let t="";return e&&!r.l33t&&!r.reversed?r.rank<=10?t=l.translations.warnings.topTen:r.rank<=100?t=l.translations.warnings.topHundred:t=l.translations.warnings.common:r.guessesLog10<=4&&(t=l.translations.warnings.similarToCommon),t},St=(r,e)=>{let t="";return e&&(t=l.translations.warnings.wordByItself),t},vt=(r,e)=>e?l.translations.warnings.namesByThemselves:l.translations.warnings.commonNames,Tt=(r,e)=>{let t="";const s=r.dictionaryName,n=s==="lastnames"||s.toLowerCase().includes("firstnames");return s==="passwords"?t=Et(r,e):s.includes("wikipedia")?t=St(r,e):n?t=vt(r,e):s==="userInputs"&&(t=l.translations.warnings.userInputs),t};var wt=(r,e)=>{const t=Tt(r,e),s=[],n=r.token;return n.match(V)?s.push(l.translations.suggestions.capitalization):n.match(B)&&n.toLowerCase()!==n&&s.push(l.translations.suggestions.allUppercase),r.reversed&&r.token.length>=4&&s.push(l.translations.suggestions.reverseWords),r.l33t&&s.push(l.translations.suggestions.l33t),{warning:t,suggestions:s}},It=r=>r.regexName==="recentYear"?{warning:l.translations.warnings.recentYears,suggestions:[l.translations.suggestions.recentYears,l.translations.suggestions.associatedYears]}:{warning:"",suggestions:[]},At=r=>{let e=l.translations.warnings.extendedRepeat;return r.baseToken.length===1&&(e=l.translations.warnings.simpleRepeat),{warning:e,suggestions:[l.translations.suggestions.repeated]}},Dt=()=>({warning:l.translations.warnings.sequences,suggestions:[l.translations.suggestions.sequences]}),xt=r=>{let e=l.translations.warnings.keyPattern;return r.turns===1&&(e=l.translations.warnings.straightRow),{warning:e,suggestions:[l.translations.suggestions.longerKeyboardPattern]}};const Q={warning:"",suggestions:[]};class jt{constructor(){this.matchers={bruteforce:Mt,date:kt,dictionary:wt,regex:It,repeat:At,sequence:Dt,spatial:xt},this.defaultFeedback={warning:"",suggestions:[]},this.setDefaultSuggestions()}setDefaultSuggestions(){this.defaultFeedback.suggestions.push(l.translations.suggestions.useWords,l.translations.suggestions.noNeed)}getFeedback(e,t){if(t.length===0)return this.defaultFeedback;if(e>2)return Q;const s=l.translations.suggestions.anotherWord,n=this.getLongestMatch(t);let a=this.getMatchFeedback(n,t.length===1);return a!=null?(a.suggestions.unshift(s),a.warning==null&&(a.warning="")):a={warning:"",suggestions:[s]},a}getLongestMatch(e){let t=e[0];return e.slice(1).forEach(n=>{n.token.length>t.token.length&&(t=n)}),t}getMatchFeedback(e,t){return this.matchers[e.pattern]?this.matchers[e.pattern](e,t):l.matchers[e.pattern]&&"feedback"in l.matchers[e.pattern]?l.matchers[e.pattern].feedback(e,t):Q}}const ee=()=>new Date().getTime(),te=(r,e,t)=>{const s=new jt,n=new yt,a=H.mostGuessableMatchSequence(e,r),o=ee()-t,i=n.estimateAttackTimes(a.guesses);return E(m(m({calcTime:o},a),i),{feedback:s.getFeedback(i.score,a.sequence)})},Ct=r=>{const e=new mt,t=ee(),s=e.match(r);return s instanceof Promise?s.then(n=>te(n,r,t)):te(s,r,t)};const _t=fe({name:"StrengthMeter",components:{InputPassword:de.Password},props:{value:v.string,showInput:v.bool.def(!0),disabled:v.bool},emits:["score-change","change"],setup(r,{emit:e}){const t=ge(""),{prefixCls:s}=le("strength-meter"),n=pe(()=>{const{disabled:o}=r;if(o)return-1;const c=T(t)?Ct(T(t)).score:-1;return e("score-change",c),c});function a(o){t.value=o.target.value}return me(()=>{t.value=r.value||""}),be(()=>T(t),o=>{e("change",o)}),{getPasswordStrength:n,handleChange:a,prefixCls:s,innerValueRef:t}}}),Rt=["data-score"];function Lt(r,e,t,s,n,a){const o=ye("InputPassword");return P(),Me("div",{class:w([r.prefixCls,"relative"])},[r.showInput?(P(),ke(o,Ae({key:0},r.$attrs,{allowClear:"",value:r.innerValueRef,onChange:r.handleChange,disabled:r.disabled}),Ee({_:2},[Se(Object.keys(r.$slots),i=>({name:i,fn:ve(c=>[Te(r.$slots,i,we(Ie(c||{})),void 0,!0)])}))]),1040,["value","onChange","disabled"])):De("",!0),N("div",{class:w(`${r.prefixCls}-bar`)},[N("div",{class:w(`${r.prefixCls}-bar--fill`),"data-score":r.getPasswordStrength},null,10,Rt)],2)],2)}var Pt=ue(_t,[["render",Lt],["__scopeId","data-v-3bfd49a1"]]);const Gt=he(Pt);export{Gt as S};
