# web_daily
A knowledge point every day.
***
1. 常用那几种浏览器测试？有哪些内核(Layout Engine)?
```ruby
(Q1) 浏览器：IE，Chrome，FireFox，Safari，Opera。
(Q2) 内核：Trident，Gecko，Presto，Webkit。
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
border-box:元素指定的任何内边距和边框都将在已设定的宽度和高度内进行绘制。通过从已设定的宽度和高度分别减去边框和内边距才能得到内容的宽度和高度。
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

渲染引擎：负责取得网页的内容（HTML、XML、图像等等）、整理讯息（例如加入CSS等），以及计算网页的显示方式，然后会输出至显示器或打印机。浏览器的内核的不同对于网页的语法解释会有不同，所以渲染的效果也不相同。所有网页浏览器、电子邮件客户端以及其它需要编辑、显示网络内容的应用程序都需要内核。

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

