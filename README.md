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
