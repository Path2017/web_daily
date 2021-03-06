# web_daily
A knowledge point every day.

## HTML部分
---
1. 常用那几种浏览器测试？有哪些内核(Layout Engine)?
```ruby
(Q1) 浏览器：IE，Chrome，FireFox，Safari，Opera。
1. IE:Internet Explorer，是微软公司推出的一款网页浏览器。
2. Chrome: Google Chrome是一款由Google公司开发的网页浏览器，该浏览器基于其他开源软件撰写，包括WebKit，
目标是提升稳定性、速度和安全性，并创造出简单且有效率的使用者界面。
3. FireFox: Mozilla Firefox，中文俗称“火狐”（正式缩写为Fx或fx，非正式缩写为FF），是一个自由及开放源代码的网页浏览器，
使用Gecko排版引擎，支持多种操作系统，如Windows、Mac OS X及GNU/Linux等。
4. Safari: 是苹果计算机的操作系统Mac OS中的浏览器，使用了KDE的KHTML作为浏览器的运算核心。
5. Opera浏览器，是一款挪威Opera Software ASA公司制作的支持多页面标签式浏览的网络浏览器，
是跨平台浏览器可以在Windows、Mac和Linux三个操作系统平台上运行。

(Q2) 内核：Trident，Gecko，Presto，Webkit。
1. Trident:Trident(IE内核)：该内核程序在1997年的IE4中首次被采用，是微软在Mosaic代码的基础之上修改而来的，
并沿用到IE11，也被普遍称作”IE内核”。Trident实际上是一款开放的内核，其接口内核设计的相当成熟，因此才有许多采用IE内核而非IE的浏览器(壳浏览器)涌现。
2. Gecko(Firefox内核)：Netscape6开始采用的内核，后来的Mozilla FireFox(火狐浏览器) 也采用了该内核，Gecko的特点是代码完全公开，
因此，其可开发程度很高，全世界的程序员都可以为其编写代码，增加功能。
因为这是个开源内核，因此受到许多人的青睐，Gecko内核的浏览器也很多，这也是Gecko内核虽然年轻但市场占有率能够迅速提高的重要原因。
3. Presto: Presto(Opera前内核) (已废弃)： Opera12.17及更早版本曾经采用的内核，现已停止开发并废弃，该内核在2003年的Opera7中首次被使用，该款引擎的特点就是渲染速度的优化达到了极致，然而代价是牺牲了网页的兼容性。
4. Webkit: Webkit(Safari内核,Chrome内核原型,开源):它是苹果公司自己的内核，也是苹果的Safari浏览器使用的内核。 
Webkit引擎包含WebCore排版引擎及JavaScriptCore解析引擎，均是从KDE的KHTML及KJS引擎衍生而来，它们都是自由软件，在GPL条约下授权，
同时支持BSD系统的开发。所以Webkit也是自由软件，同时开放源代码。在安全方面不受IE、Firefox的制约，所以Safari浏览器在国内还是很安全的。

```
2. 说下行内元素和块级元素的区别？行内块元素的兼容性使用？（IE8 以下）
```ruby
(Q1) 行内元素：会在水平方向排列，不能包含块级元素，设置width无效，height无效(可以设置line-height)，margin上下无效，padding上下无效。

块级元素：各占据一行，垂直方向排列。从新行开始结束接着一个断行。

(Q2) 兼容性：display:inline-block;*display:inline;*zoom:1;
```
3. 清除浮动有哪些方式？比较好的方式是哪一种？
```ruby
(Q1)
（1）父级div定义height。
（2）结尾处加空div标签clear:both。
（3）父级div定义伪类:after和zoom。
（4）父级div定义overflow:hidden。
（5）父级div定义overflow:auto。
（6）父级div也浮动，需要定义宽度。
（7）父级div定义display:table。
（8）结尾处加br标签clear:both。
(Q2) 
比较好的是第3种方式，好多网站都这么用。
```
4. box-sizing常用的属性有哪些？分别有什么作用？
```ruby
(Q1)box-sizing: content-box|border-box|inherit;

(Q2)content-box:宽度和高度分别应用到元素的内容框。在宽度和高度之外绘制元素的内边距和边框(元素默认效果)。
border-box:元素指定的任何内边距和边框都将在已设定的宽度和高度内进行绘制。
通过从已设定的宽度和高度分别减去边框和内边距才能得到内容的宽度和高度。
```
5. Doctype作用？标准模式与兼容模式各有什么区别?
```ruby
(Q1) 告知浏览器的解析器用什么文档标准解析这个文档。DOCTYPE不存在或格式不正确会导致文档以兼容模式呈现。

(Q2) 标准模式的排版和JS运作模式都是以该浏览器支持的最高标准运行。在兼容模式中，页面以宽松的向后兼容的方式显示,模拟老式浏览器的行为以防止站点无法工作。
```
6. HTML5 为什么只需要写<!DOCTYPE html>?
```ruby
HTML5不基于 SGML，因此不需要对DTD进行引用，但是需要doctype来规范浏览器的行为（让浏览器按照它们应该的方式来运行）。

而HTML4.01基于SGML,所以需要对DTD进行引用，才能告知浏览器文档所使用的文档类型。
```
7. 页面导入样式时，使用link和@import有什么区别？
```ruby
（1）link属于XHTML标签，除了加载CSS外，还能用于定义RSS, 定义rel连接属性等作用；而@import是CSS提供的，只能用于加载CSS;
（2）页面被加载的时，link会同时被加载，而@import引用的CSS会等到页面被加载完再加载;
（3）import是CSS2.1 提出的，只在IE5以上才能被识别，而link是XHTML标签，无兼容问题。
```
8. 介绍一下你对浏览器内核的理解？
```ruby
主要分成两部分：渲染引擎(layout engineer或Rendering Engine)和JS引擎。

渲染引擎：负责取得网页的内容（HTML、XML、图像等等）、整理讯息（例如加入CSS等），以及计算网页的显示方式，然后会输出至显示器或打印机。
浏览器的内核的不同对于网页的语法解释会有不同，所以渲染的效果也不相同。
所有网页浏览器、电子邮件客户端以及其它需要编辑、显示网络内容的应用程序都需要内核。

JS引擎则：解析和执行javascript来实现网页的动态效果。

最开始渲染引擎和JS引擎并没有区分的很明确，后来JS引擎越来越独立，内核就倾向于只指渲染引擎。
```
9. html5有哪些新特性？如何处理HTML5新标签的浏览器兼容问题？如何区分 HTML 和 HTML5？
```ruby
(Q1)
HTML5 现在已经不是 SGML 的子集，主要是关于图像，位置，存储，多任务等功能的增加。
(1)绘画 canvas;
(2)用于媒介回放的 video 和 audio 元素;
(3)本地离线存储 localStorage 长期存储数据，浏览器关闭后数据不丢失;
(4)sessionStorage 的数据在浏览器关闭后自动删除;
(5)语意化更好的内容元素，比如 article、footer、header、nav、section;
(6)表单控件，calendar、date、time、email、url、search;
(7)新的技术webworker, websocket, Geolocation;
(Q2)
IE8/IE7/IE6支持通过document.createElement方法产生的标签，

可以利用这一特性让这些浏览器支持HTML5新标签，

浏览器支持新标签后，还需要添加标签默认的样式。

当然也可以直接使用成熟的框架、比如html5shim
```
10. 简述一下你对HTML语义化的理解？
```ruby
　　html语义化让页面的内容结构化，结构更清晰，便于对浏览器、搜索引擎解析;

　　即使在没有样式CSS情况下也以一种文档格式显示，并且是容易阅读的;

　　搜索引擎的爬虫也依赖于HTML标记来确定上下文和各个关键字的权重，利于SEO;

　　使阅读源代码的人对网站更容易将网站分块，便于阅读维护理解。
```
11. XHTML和HTML有什么区别
```ruby
HTML是一种基本的WEB网页设计语言，XHTML是一个基于XML的置标语言
最主要的不同：
XHTML 元素必须被正确地嵌套。
XHTML 元素必须被关闭。
标签名必须用小写字母。
XHTML 文档必须拥有根元素。
```
12. 请描述一下 cookies，sessionStorage 和 localStorage 的区别？
```ruby
cookie在浏览器和服务器间来回传递。 sessionStorage和localStorage不会
sessionStorage和localStorage的存储空间更大；
sessionStorage和localStorage有更多丰富易用的接口；
sessionStorage和localStorage各自独立的存储空间；
```
13. 如何实现浏览器内多个标签页之间的通信?
```ruby
调用localstorge、cookies等本地存储方式
```
14. HTML5 为什么只需要写 !DOCTYPE HTML？
```ruby
HTML5 不基于 SGML，因此不需要对DTD进行引用，但是需要doctype来规范浏览器的行为（让浏览器按照它们应该的方式来运行）；
而HTML4.01基于SGML,所以需要对DTD进行引用，才能告知浏览器文档所使用的文档类型。
```
15. Doctype? 严格模式与混杂模式-如何触发这两种模式，区分它们有何意义?
```ruby
用于声明文档使用那种规范（html/Xhtml）一般为 严格 过度 基于框架的html文档。
加入XMl声明可触发，解析方式更改为IE5.5 拥有IE5.5的Bug。
```
16. XML和JSON的区别？
```ruby
(1).数据体积方面。

JSON相对于XML来讲，数据的体积小，传递的速度更快些。

(2).数据交互方面。

JSON与JavaScript的交互更加方便，更容易解析处理，更好的数据交互。

(3).数据描述方面。

JSON对数据的描述性比XML较差。

(4).传输速度方面。

JSON的速度要远远快于XML。
```
17. 说说你对作用域链的理解
```ruby
作用域链的作用是保证执行环境里有权访问的变量和函数是有序的，作用域链的变量只能向上访问，变量访问到window对象即被终止，作用域链向下访问变量是不被允许的。
```
## CSS部分
---
1. position的值， relative和absolute分别是相对于谁进行定位的？
```ruby
absolute :生成绝对定位的元素， 相对于最近一级的 定位不是 static 的父元素来进行定位。

fixed （老IE不支持）生成绝对定位的元素，通常相对于浏览器窗口或 frame 进行定位。

relative 生成相对定位的元素，相对于其在普通流中的位置进行定位。

static 默认值。没有定位，元素出现在正常的流中

sticky 生成粘性定位的元素，容器的位置根据正常文档流计算得出
```
2. 
## JavaScript部分
---
1. 介绍js的基本数据类型
```javascript
Undefined、Null、Boolean、Number、String
```
2. js有哪些内置对象？
```javascript
数据封装类对象：Object、Array、Boolean、Number 和 String

其他对象：Function、Arguments、Math、Date、RegExp、Error
```
3. this对象的理解
```javascript
this总是指向函数的直接调用者（而非间接调用者）；

如果有new关键字，this指向new出来的那个对象；

在事件中，this指向触发这个事件的对象，特殊的是，IE中的attachEvent中的this总是指向全局对象Window。
```
4. eval是做什么的？
```javascript
它的功能是把对应的字符串解析成JS代码并运行；

应该避免使用eval，不安全，非常耗性能（2次，一次解析成js语句，一次执行）。

由JSON字符串转换为JSON对象的时候可以用eval，var obj =eval('('+ str +')')。
```
5. DOM怎样添加、移除、移动、复制、创建和查找节点
```javascript
创建新节点

createDocumentFragment()  创建一个DOM片段

createElement()  创建一个具体的元素

createTextNode()  创建一个文本节点

添加、移除、替换、插入

 appendChild()

 removeChild()

 replaceChild()

 insertBefore()  在已有的子节点前插入一个新的子节点

 查找

 getElementsByTagName()  通过标签名称

 getElementsByName()  通过元素的Name属性的值(IE容错能力较强，会得到一个数组，其中包括id等于name值的)

getElementById()  通过元素Id，唯一性
```
6. null和undefined的区别？
```javascript
null是一个表示"无"的对象，转为数值时为0；undefined是一个表示"无"的原始值，转为数值时为NaN。

undefined：

（1）变量被声明了，但没有赋值时，就等于undefined。

（2) 调用函数时，应该提供的参数没有提供，该参数等于undefined。

（3）对象没有赋值的属性，该属性的值为undefined。

（4）函数没有返回值时，默认返回undefined。

null：

（1） 作为函数的参数，表示该函数的参数不是对象。

（2） 作为对象原型链的终点。
```
7. new操作符具体干了什么呢?
```javascript
（1）创建一个空对象，并且 this 变量引用该对象，同时还继承了该函数的原型。

（2）属性和方法被加入到 this 引用的对象中。

（3）新创建的对象由 this 所引用，并且最后隐式的返回 this 。
```
8. JSON 的了解？
```javascript
JSON(JavaScript Object Notation) 是一种轻量级的数据交换格式。它是基于JavaScript的一个子集。数据格式简单, 易于读写, 占用带宽小。

格式：采用键值对，例如：{'age':'12', 'name':'back'}
```
9. call() 和 apply() 的区别和作用？
```javascript
apply()函数有两个参数：第一个参数是上下文，第二个参数是参数组成的数组。如果上下文是null，则使用全局对象代替。

如：function.apply(this,[1,2,3]);

call()的第一个参数是上下文，后续是实例传入的参数序列。

如：function.call(this,1,2,3);
```
10. 如何获取UA？
```javascript
function whatBrowser() {
  document.Browser.Name.value=navigator.appName;
  document.Browser.Version.value=navigator.appVersion;
  document.Browser.Code.value=navigator.appCodeName;
  document.Browser.Agent.value=navigator.userAgent;
　　}
```
11. 创建ajax过程
```ruby
(1)创建XMLHttpRequest对象,也就是创建一个异步调用对象.
(2)创建一个新的HTTP请求,并指定该HTTP请求的方法、URL及验证信息.
(3)设置响应HTTP请求状态变化的函数.
(4)发送HTTP请求.
(5)获取异步调用返回的数据.
(6)使用JavaScript和DOM实现局部刷新.
```
## 其他
1. HTTP状态码知道哪些？
```ruby
100 Continue 继续，一般在发送post请求时，已发送了http header之后服务端将返回此信息，表示确认，之后发送具体参数信息

200 OK 正常返回信息

201 Created 请求成功并且服务器创建了新的资源

202 Accepted 服务器已接受请求，但尚未处理

301 Moved Permanently 请求的网页已永久移动到新位置。

302 Found 临时性重定向。

303 See Other 临时性重定向，且总是使用 GET 请求新的 URI。

304 Not Modified 自从上次请求后，请求的网页未修改过。

400 Bad Request 服务器无法理解请求的格式，客户端不应当尝试再次使用相同的内容发起请求。

401 Unauthorized 请求未授权。

403 Forbidden 禁止访问。

404 Not Found 找不到如何与 URI 相匹配的资源。

500 Internal Server Error 最常见的服务器端错误。

503 Service Unavailable 服务器端暂时无法处理请求（可能是过载或维护）。
```
2. 性能优化的方法？
```ruby
（1） 减少http请求次数：CSS Sprites, JS、CSS源码压缩、图片大小控制合适；网页Gzip，CDN托管，data缓存 ，图片服务器。

（2） 前端模板 JS+数据，减少由于HTML标签导致的带宽浪费，前端用变量保存AJAX请求结果，每次操作本地变量，不用请求，减少请求次数

（3） 用innerHTML代替DOM操作，减少DOM操作次数，优化javascript性能。

（4） 当需要设置的样式很多时设置className而不是直接操作style。

（5） 少用全局变量、缓存DOM节点查找的结果。减少IO读取操作。

（6） 避免使用CSS Expression（css表达式)又称Dynamic properties(动态属性)。

（7） 图片预加载，将样式表放在顶部，将脚本放在底部 加上时间戳。
```
3. 什么叫优雅降级和渐进增强？
```ruby
优雅降级：Web站点在所有新式浏览器中都能正常工作，如果用户使用的是老式浏览器，则代码会检查以确认它们是否能正常工作。
由于IE独特的盒模型布局问题，针对不同版本的IE的hack实践过优雅降级了,为那些无法支持功能的浏览器增加候选方案，
使之在旧式浏览器上以某种形式降级体验却不至于完全失效。

渐进增强：从被所有浏览器支持的基本功能开始，逐步地添加那些只有新式浏览器才支持的功能,向页面增加无害于基础浏览器的额外样式和功能的。
当浏览器支持时，它们会自动地呈现出来并发挥作用。
```
4. 哪些常见操作会造成内存泄漏？
```ruby
内存泄漏指任何对象在您不再拥有或需要它之后仍然存在。

垃圾回收器定期扫描对象，并计算引用了每个对象的其他对象的数量。如果一个对象的引用数量为 0（没有其他对象引用过该对象），
或对该对象的惟一引用是循环的，那么该对象的内存即可回收。

setTimeout 的第一个参数使用字符串而非函数的话，会引发内存泄漏。

闭包、控制台日志、循环（在两个对象彼此引用且彼此保留时，就会产生一个循环）。
```
5. 线程与进程的区别
```ruby
一个程序至少有一个进程,一个进程至少有一个线程。

线程的划分尺度小于进程，使得多线程程序的并发性高。

另外，进程在执行过程中拥有独立的内存单元，而多个线程共享内存，从而极大地提高了程序的运行效率。

线程在执行过程中与进程还是有区别的。每个独立的线程有一个程序运行的入口、顺序执行序列和程序的出口。
但是线程不能够独立执行，必须依存在应用程序中，由应用程序提供多个线程执行控制。

从逻辑角度来看，多线程的意义在于一个应用程序中，有多个执行部分可以同时执行。
但操作系统并没有将多个线程看做多个独立的应用，来实现进程的调度和管理以及资源分配。这就是进程和线程的重要区别。
```

---
### 如何解决跨域问题
1. JSONP：
```javascript
原理是：动态插入script标签，通过script标签引入一个js文件，这个js文件载入成功后会执行我们在url参数中指定的函数，
并且会把我们需要的json数据作为参数传入。

由于同源策略的限制，XmlHttpRequest只允许请求当前源（域名、协议、端口）的资源，为了实现跨域请求，可以通过script标签实现跨域请求，
然后在服务端输出JSON数据并执行回调函数，从而解决了跨域的数据请求。

优点是兼容性好，简单易用，支持浏览器与服务器双向通信。缺点是只支持GET请求。

JSONP：json+padding（内填充），顾名思义，就是把JSON填充到一个盒子里
<script>
    function createJs(sUrl){

        var oScript = document.createElement('script');
        oScript.type = 'text/javascript';
        oScript.src = sUrl;
        document.getElementsByTagName('head')[0].appendChild(oScript);
    }

    createJs('jsonp.js');

    box({
       'name': 'test'
    });

    function box(json){
        alert(json.name);
    }
</script>
```
2. CORS
```ruby
服务器端对于CORS的支持，主要就是通过设置Access-Control-Allow-Origin来进行的。
如果浏览器检测到相应的设置，就可以允许Ajax进行跨域的访问。
```
3. 通过修改document.domain来跨子域
```ruby
将子域和主域的document.domain设为同一个主域.
前提条件：这两个域名必须属于同一个基础域名!
而且所用的协议，端口都要一致，否则无法利用document.domain进行跨域

主域相同的使用document.domain
```
4. 使用window.name来进行跨域
```ruby
window对象有个name属性，该属性有个特征：即在一个窗口(window)的生命周期内,
窗口载入的所有的页面都是共享一个window.name的，每个页面对window.name都有读写的权限，
window.name是持久存在一个窗口载入过的所有页面中的
```
5. 使用HTML5中新引进的window.postMessage方法来跨域传送数据
```ruby
还有flash、在服务器上设置代理页面等跨域方式。个人认为window.name的方法既不复杂，
也能兼容到几乎所有浏览器，这真是极好的一种跨域方法。
```
---
### 谈谈你对webpack的看法
```javascript
WebPack 是一个模块打包工具，你可以使用WebPack管理你的模块依赖，并编绎输出模块们所需的静态文件。
它能够很好地管理、打包Web开发中所用到的HTML、JavaScript、CSS以及各种静态文件（图片、字体等），让开发过程更加高效。
对于不同类型的资源，webpack有对应的模块加载器。webpack模块打包器会分析模块间的依赖关系，最后 生成了优化且合并后的静态资源。

webpack的两大特色：

1.code splitting（可以自动完成）

2.loader 可以处理各种类型的静态文件，并且支持串联操作
webpack 是以commonJS的形式来书写脚本滴，但对 AMD/CMD 的支持也很全面，方便旧项目进行代码迁移。

webpack具有requireJs和browserify的功能，但仍有很多自己的新特性：

1. 对 CommonJS 、 AMD 、ES6的语法做了兼容
2. 对js、css、图片等资源文件都支持打包
3. 串联式模块加载器以及插件机制，让其具有更好的灵活性和扩展性，例如提供对CoffeeScript、ES6的支持
4. 有独立的配置文件webpack.config.js
5. 可以将代码切割成不同的chunk，实现按需加载，降低了初始化时间
6. 支持 SourceUrls 和 SourceMaps，易于调试
7. 具有强大的Plugin接口，大多是内部插件，使用起来比较灵活
8. webpack 使用异步 IO 并具有多级缓存。这使得 webpack 很快且在增量编译上更加快
```
---
### 说说TCP传输的三次握手四次挥手策略
```ruby
为了准确无误地把数据送达目标处，TCP协议采用了三次握手策略。
用TCP协议把数据包送出去后，TCP不会对传送 后的情况置之不理，它一定会向对方确认是否成功送达。握手过程中使用了TCP的标志：SYN和ACK。

发送端首先发送一个带SYN标志的数据包给对方。接收端收到后，回传一个带有SYN/ACK标志的数据包以示传达确认信息。 
最后，发送端再回传一个带ACK标志的数据包，代表“握手”结束。 
若在握手过程中某个阶段莫名中断，TCP协议会再次以相同的顺序发送相同的数据包。
```
* 断开一个TCP连接则需要“四次握手”：
```ruby
第一次挥手：主动关闭方发送一个FIN，用来关闭主动方到被动关闭方的数据传送，
也就是主动关闭方告诉被动关闭方：我已经不会再给你发数据了(当然，在fin包之前发送出去的数据，如果没有收到对应的ack确认报文，主动关闭方依然会重发这些数据)，
但是，此时主动关闭方还可 以接受数据。

第二次挥手：被动关闭方收到FIN包后，发送一个ACK给对方，确认序号为收到序号+1（与SYN相同，一个FIN占用一个序号）。

第三次挥手：被动关闭方发送一个FIN，用来关闭被动关闭方到主动关闭方的数据传送，也就是告诉主动关闭方，我的数据也发送完了，不会再给你发数据了。

第四次挥手：主动关闭方收到FIN后，发送一个ACK给被动关闭方，确认序号为收到序号+1，至此，完成四次挥手。
```
---
### TCP和UDP的区别
```ruby
TCP（Transmission Control Protocol，传输控制协议）是基于连接的协议，也就是说，在正式收发数据前，必须和对方建立可靠的连接。
一个TCP连接必须要经过三次“对话”才能建立起来

UDP（User Data Protocol，用户数据报协议）是与TCP相对应的协议。它是面向非连接的协议，它不与对方建立连接，而是直接就把数据包发送过去！ 
UDP适用于一次只传送少量数据、对可靠性要求不高的应用环境。
```
---
### 渐进增强和优雅降级
```ruby
渐进增强 ：针对低版本浏览器进行构建页面，保证最基本的功能，然后再针对高级浏览器进行效果、交互等改进和追加功能达到更好的用户体验。
优雅降级 ：一开始就构建完整的功能，然后再针对低版本浏览器进行兼容。
```
---
### 常见web安全及防护原理
1. sql注入原理
```javascript
就是通过把SQL命令插入到Web表单递交或输入域名或页面请求的查询字符串，最终达到欺骗服务器执行恶意的SQL命令。

总的来说有以下几点：
1.永远不要信任用户的输入，要对用户的输入进行校验，可以通过正则表达式，或限制长度，对单引号和双"-"进行转换等。

2.永远不要使用动态拼装SQL，可以使用参数化的SQL或者直接使用存储过程进行数据查询存取。

3.永远不要使用管理员权限的数据库连接，为每个应用使用单独的权限有限的数据库连接。

4.不要把机密信息明文存放，请加密或者hash掉密码和敏感的信息。
```
2. XSS原理及防范
```javascript
Xss(cross-site scripting)攻击指的是攻击者往Web页面里插入恶意 html标签或者javascript代码。
比如：攻击者在论坛中放一个看似安全的链接，骗取用户点击后，窃取cookie中的用户私密信息；或者攻击者在论坛中加一个恶意表单，

当用户提交表单的时候，却把信息传送到攻击者的服务器中，而不是用户原本以为的信任站点。
```
3. XSS防范方法
```javascript
首先代码里对用户输入的地方和变量都需要仔细检查长度和对”<”,”>”,”;”,”’”等字符做过滤；其次任何内容写到页面之前都必须加以encode，
避免不小心把html tag 弄出来。这一个层面做好，至少可以堵住超过一半的XSS 攻击。

首先，避免直接在cookie 中泄露用户隐私，例如email、密码等等。

其次，通过使cookie 和系统ip 绑定来降低cookie 泄露后的危险。这样攻击者得到的cookie 没有实际价值，不可能拿来重放。

如果网站不需要再浏览器端对cookie 进行操作，可以在Set-Cookie 末尾加上HttpOnly 来防止javascript 代码直接获取cookie 。

尽量采用POST 而非GET 提交表单
```
4. XSS与CSRF有什么区别吗？
```javascript
XSS是获取信息，不需要提前知道其他用户页面的代码和数据包。CSRF是代替用户完成指定的动作，需要知道其他用户页面的代码和数据包。

要完成一次CSRF攻击，受害者必须依次完成两个步骤：
登录受信任网站A，并在本地生成Cookie。

在不登出A的情况下，访问危险网站B。
```
5. CSRF的防御
```javascript
服务端的CSRF方式方法很多样，但总的思想都是一致的，就是在客户端页面增加伪随机数。

通过验证码的方法
```
