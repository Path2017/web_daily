## 详解css问题

1. CSS实现垂直水平居中方法
* HTML结构：
``` html
<div class="wrapper">
    <div class="content"></div>
</div>
```
* CSS：
```css
.wrapper{
  position:relative;
  }
.content{
  background-color:#6699FF;
  width:200px;
  height:200px;
  position: absolute;        //父元素需要相对定位
  top: 50%;
  left: 50%;
  margin-top:-100px ;   //二分之一的height,width
  margin-left: -100px;
  }
```
