### 变量类型与计算
----
1. Js中使用typeof能得到哪些类型
2. 何时使用 === 何时使用 ==
3. Js中有哪些内置函数
4. Js按变量存储方式区分为哪些类型，并描述其特点
5. 如何理解JSON

#### 知识点
* 变量类型
```ruby
变量类型分为：值类型和引用类型
typeof运算符详解
```
```javascript
值类型
var a = 100;
var b = a;
a = 200;
console.log(b) // 100

值类型的特点：每个变量可以存储各自的值，互相之间不影响
```
```javascript
引用类型
var a = {age:20}
var b =a
b.age = 21
console.log(a.age) // 21
从内存中来说，值类型把每一个值存储在不同的位置，变量直接不影响。
而引用类型，在赋值的时候，把{age:20}对象存储在内存中，变量a的指针指向对象的位置，而b=a的赋值时候，又把b的指针指向对象。变量只是对象的指针而已。
引用类型有3类型：对象、数组、函数；引用类型的特点，可以扩展属性。
引用类型的优势，节省内存空间。
```
* typeof运算符
```javascript
6种类型
typeof undefined  // undefined
typeof 'abc'  // string
typeof 12  // number 
typeof true  // boolean
typeof {}  // object
typeof []  // object
typeof null // object
typeof function // function

typeof 只能区分值类型的详细类型，对于引用类型无法区分具体类型。
但对于js函数而言，函数是一个特殊的引用类型，在js中有很高的地位，所以typeof能区分出来
```
* 变量计算-强制类型转换
```javascript
类型转换：
字符串拼接
== 运算符
if 语句
逻辑运算

// 字符串拼接时，类型转换
var a = 100 + 10 // 110
var b = 100 + '10' // 10010
// == 运算符
100 == '100'  // true 
0 == ''   // true 0和空字符串都转换成false
null == undefined // true
// if语句
var a = true
if(a){
  ...
}

var b = 100
if(b){ // b转换成true
  ...
}

var c = ''
if(c){ // c转换成false
  ...
}

// 逻辑运算
console.log(10&&0) // 0
console.log(''||'abc') // 'abc'
console.log(!window.abc)  // true

判断一个变量会被当作ture还是false的方法
var d = 100
console.log(!!d) // true
```

#### 解答
---
1. Js中使用typeof能得到哪些类型
```javascript
6种类型
typeof '123' //string
typeof 12 // number
typeof undefined // undefined
typeof true // boolean
typeof {} // object
typeof [] // object
typeof null // object
typeof function // function
```
2. 何时使用 === 何时使用 ==
```javascript
除了判断一个引用类型是否具有某个属性用 == 其余均使用 ===

if(obj.a == null){
  // 这里相当于 obj.a == null || obj.a == undefined,简写形式
  // 这里是jquery 源码推荐写法
}
```
3. Js中有哪些内置函数--数据封装类对象
```javascript
总共9个函数
Object
Array
Boolean
Number
String
Function
Date
RegExp
Error
```
4. Js按变量存储方式区分为哪些类型，并描述其特点
```javascript
值类型和引用类型
值类型变量单独使用存储空间
引用类型多个变量共用一个存储空间，使用时，是将变量指针指向对象
```
5. 如何理解JSON
```javascript
JSON 是一个js的对象也是一种数据格式
具有2个API
JSON.stringfy({a:12,b:20}) // 把对象变成字符串
JSON.parse('{"a":12,"b":20}') //把字符串转换成对象
```

### 原型和原型链
---
1. 如何准确判断一个变量是数组类型
2. 写一个原型链继承的例子
3. 描述new一个对象的过程
4. zepto (或其他框架) 源码中如何使用原型链

#### 知识点
* 构造函数
* 构造函数-扩展
* 原型规则和示例
* 原型链
* instanceof
---
1. 构造函数
```javascript 
// 构造函数名首字母大写
function Foo(name,age){
  this.name = name
  this.age = age
  this.cllass = 'class-1'
  // return this  //默认有return this
}
var f = new Foo('zhangsan',20) // 通过构造函数 可以new创建很多个对象

new一个构造函数返回一个对象的过程：
1.把参数传到构造函数，如果没有参数的话就不用传到构造函数
2.首先把this变成一个空对象
3.属性的赋值
4.return this

这个时候返回的对象就具有构造函数的属性
```
2. 构造函数-扩展
```javascript
· var a = {} 其实是 var a = new Object()的语法糖
· var a =  [] 其实是 var a = new Array()的语法糖
· function Foo{...} 其实是 var Foo = new Function(...)
· 使用instanceof 判断一个函数是否是一个变量的构造函数
```
3. 原型规则和示例
```javascript
5条原型规则
原型规则是学习原型链的基础
1.所有引用类型（数组、对象、函数），都具有对象特性，即可以自由扩展属性（除了“null”之外）
---
var obj = {};obj.a = 100;
var arr = [];arr.a = 100;
function fn (){

}
fn.a = 100;
---
2.所有的引用类型（数组、对象、函数），都有一个__proto__属性，属性值是一个普通的对象
---
__proto__ ：隐式原型
console.log(obj.__proto__)
console.log(arr.__proto__)
console.log(fn.__proto__)
---
3.所有的函数，都有一个prototype属性，属性值也是一个普通对象
---
prototype ：显式原型
console.log(fn.prototype)
---
4.所有的引用类型（数组、对象、函数），__proto__属性值指向它的构造函数的"prototype"属性值
---
console.log(obj.__proto__ === Object.prototype)
---
5.当试图得到一个对象的某个属性时，如果这个对象本身没有这个属性，那么会去它的__proto__（即它的构造函数的prototype）中去寻找
---
// 构造函数
function Foo(name,age){
  this.name = name;
  this.age = age;
  return this
}
Foo.prototype.alertName = function(){
  alert(this.name)
}
// 创建实例
var f = new Foo('zhangsan)
f.printName = function(){
  console.log(this.name)
}
// 测试
f.printName()
f.alertName()
---
this的指向 调用的对象自身 this指向f
// 遍历对象具有的属性
var item
for(item in f){
  // 高级浏览器已经在 for in 中屏蔽了来自原型的属性
  // 但是这里建议还是加上这个判断，保证程序的健壮性
  if(f.hasOwnProperty(item)){
    console.log(item)
  }
}
```
4. 原型链
```javascript
f.toString()
f自身没有toString这个方法，所以去它的__proto__中找，即去Foo的prototype中找，
Foo的protype中没有这个属性，则去Foo的__proto__中寻找，即去Foo的构造函数Object的prototype中找
```
5. instanceof
```javascript
用于判断引用类型属于哪个构造函数

var a = {}
a instancsof Object  // true

判断逻辑
f instanceof Foo
f的__proto__一层一层往上，能否对应到Foo.prototype
再试着判断 f instanceof Object
```
#### 解答
---
1. 如何准确判断一个变量是数组类型
```javascript
var arr = []
arr instanceof Array // true
typeof arr // object
```
2. 写一个原型链继承的例子
```javascript
function Animal(){
	this.eat = function(){
		console.log('animal eat')
	}
}
function Dog(){
	this.bark = function(){
		console.log('dog bark')
	}
}
Dog.prototype = new Animal()
var hashiqi = new Dog()
hashiqi.eat() // animal eat
```
3. 描述new一个对象的过程
```javascript
· 创建一个新对象
· this指向这个对象
· 执行代码，即对this赋值
· 返回this
```
4. zepto (或其他框架) 源码中如何使用原型链
```javascript
阅读源码是高效提高技能的方式
<<zepto设计与源码分析>>
```
### 作用域和闭包
----
### 函数表达式与函数声明
```javascript
fn() // 声明提前
function fn(){
  // 声明
}
var fn1 = function(){
  // 表达式
}
fn1()
```
---
1. 说一下对变量提升的理解
2. 说明this几种不同的使用场景
3. 创建10个</a/>标签，点击时候弹出来对应的序号
4. 如何理解作用域
5. 实际开发中闭包的应用
---
#### 知识点
1. 执行上下文
```javascript
范围：一段<script>或者一个函数
全局：变量定义、函数声明
函数：变量定义、函数声明、this、arguments
```
2. this
```javascript
this 要在执行时才能确认值，定义时无法确认
exp:
var a = {
  name: 'name',
  fn:function(){
    console.log(this.name)
  }
}
a.fn()  // this === a
a.fn.call({name:'addname'}) // this === {name:'addname'}
var fn1 = a.fn
fn1() // this === window

this 使用场景
作为构造函数执行
作为对象属性执行
作为普通函数执行
call apply bind

1.作为构造函数
function Foo(name){
  this.name = name
}
var f = new Foo('path')
扩展：
new一个构造函数返回一个对象的过程：
1).把参数传到构造函数，如果没有参数的话就不用传到构造函数
2).首先把this变成一个空对象
3).属性的赋值
4).return this
这个时候返回的对象就具有构造函数的属性
2.作为对象属性
var obj = {
  name:'A',
  printName:function(){
    console.log(this.name)
  }
}
obj.printName()
3.普通函数
function fn(){
  console.log(this) // 指向window
}
fn()
4.call apply bind
funtion fn(name){
  console.log(name)
  console.log(this)
}
fn.call({x:100},"name":"zhang") // this== {x:100}
```
3. 作用域
```javascript
js没有块级作用域
它只有函数和全局作用域

无块级作用域
if(true){
  var tag = 'test'
}
console.log(tag) // test  在java中因为块级作用域问题 这个地方会输出undefined
只有函数和全局作用域
var a = 101
function fn(){
  var a = 201
  console.log('fn',a) // 输出201
}
console.log('global',a) // 输出101
fn()
```
4. 作用域链
```javascript
var a = 100;
function fn(){
  // 当前作用域没有定义的变量，称为自由变量 此处a在函数作用域内未定义
  // 在其父级作用域  全局作用域定义
  console.log(a) // 输出100
  console.log(b) // 输出200 函数声明里面 变量提升
  var b =200
  function f(){
    var c = 300
    console.log(a) // 输出100
    console.log(b) // 输出200
    console.log(c) // 输出300
  }
}
fn()
```
5. 闭包
```javascript
例子1
function F1(){
  var a = 100;
  // 返回函数作为返回值
  return function(){
    console.log(a) // 在这个函数中a是自由变量  变量的定义去父级作用域中查找
  }
}
// f1得到一个函数  变量的作用域是在定义时候确认，而不是执行时确认
var f1 = F1()
var a = 200
f1() // 输出 100 一个函数变量的作用域是在定义时候的作用域 而不是执行时候的作用域

闭包使用场景：
函数作为返回值
函数作为参数传值


例子2：
function F1(){
  var a = 100;
  return function(){
    console.log(a)
  }
}
var f1 = F1()
function F2(fn){
  var a =200;
  fn()
}
F2(f1) // 结果为100
```
#### 题目解答
1. 说一下对变量提升的理解
```javascript
变量定义
函数声明(注意和函数表达式的区别)
```
2. 说明this几种不同的使用场景
```javascript
作为构造函数执行
作为对象属性执行
作为普通函数执行
call apply bind
```
3. 创建10个</a/>标签，点击时候弹出来对应的序号
```javascript
利用自执行的函数
```
4. 如何理解作用域
```javascript
自由变量
作用域链，即自由变量的查找
闭包的两个场景
```
5. 实际开发中闭包的应用
```javascript
// 封装变量 收敛权限
举个例子，变量的第一次执行
function isFirstload(){
  var _list = []
  return function(id){
    if(_list.indexOf(id)>=0){
      return false
    }else{
      _list.push(id)
      return true
    }
  }
}
var firstLoad = isFirstload()
firstLoad(10) // true
firstLoad(10) // false
firstLoad(20) // true

// 闭包应用的意义，用户无法修改_list中的值
```

### 同步和异步
---
1. 同步和异步的区别是什么？分别举一个同步和异步的例子
2. 一个关于setTimeout的笔试题
3. 签单使用异步的场景有哪些
#### 知识点
1. 什么是异步
2. 前端使用异步的场景
3. 异步和单线程

1. 什么是异步
```javascript
异步例子
console.log(100)
setTimeout(function(){
  console.log(200)
},100)
console.log(300) // 打印顺序为 100 300 200
同步例子
console.log(100)
alert(200)
console.log(300) // alert为同步函数  需要alert点击确认 程序才能往下执行；同步会阻塞代码执行

什么时候需要异步：
1. 在可能需要等待的情况
2. 等待过程中不能像alert一样阻塞程序执行
3. 因此，所有需要“等待的情况”都需要异步
```
2. 前端使用异步的场景
```javascript
1. 定时任务：setTimeout,setInverval
2. 网络请求：ajax请求,动态<img>加载
3. 事件绑定

ajax请求代码示例
console.log('start')
$.get('./data.json',function(data){
  console.log('data)
})
console.log('end') // 打印顺序 start end data

img加载示例
console.log('start')
var img = doucument.createElement('img')
img.onload = function(){
  console.log('loaded)
}
img.src = './xxx.png'
console.log('end') // start end loaded

事件绑定示例
console.log('start')
document.getElementById('btn').addEventListener('click',function(){
  alert('clicked')
})
console.log('end') // 顺序为  start end 按钮点击时  弹出alert框

```
3. 异步和单线程
```javascript
console.log(100)
setTimeout(function(){
  console.log(200)
},100)
console.log(300) // 打印顺序为 100 300 200

所以异步的函数在执行时，程序先会将异步函数抽离出线程放在一旁，等代码执行完
再来执行异步函数

代码执行的过程：
1. 执行第一行，打印100
2. 执行setTimeout后，传入setTimeout的函数会被暂存起来，不会立即执行（单线程的特点，不能同时做两件事情）
3. 执行最后一行，打印300
4. 待所有程序执行完，处于空闲状态时，会立马看有没有缓存起来的要执行
5. 发现暂存起来的setTimeout中的函数无需等待时间，就立即来执行

同步和异步的区别是什么
1. 同步会阻塞代码执行，而异步不会
2. alert是同步，setTimeout是异步
```
#### 其他
1. 获取当前时间的年月日
2. 获取随机数，要求是长度一致的字符串格式
3. 写一个能遍历对象和数组通用forEach函数
```javascript
日期
Math
数组API
对象API

日期api
Date.now() // 获取到的是 当前的时间戳
var dt = new Date() // 获取当前的时间对象
dt.getTime() // 获取毫秒数
dt.getFullYear() // 年
dt.getMonth() // 月 0-11
dt.getDate() // 日
dt.getHours() // 小时
dt.getMinutes() // 分钟
dt.getSeconds() // 秒

Math
获取随机数  Math.random() 
随机数在前端中可添加在请求链接后面，每次请求不一样，从而达到清除缓存的功能

数组api
forEach 遍历所有的元素
every 判断所有元素是否都符合条件
some 判断是否有至少一个元素符合条件
sort 排序
map 对元素重新组装，生成新数组
filter 过滤符合条件元素

forEach
var arr = [1,2,3]
arr.forEach(function(item,index){
  // 遍历数组中所有的元素
  console.log(index,item)
})

every
var arr = [1,2,3]
var result = arr.every(function(item,index){
  // 用来判断数组中所有元素都满足一个条件
  if(item<4){
    return true
  }
})
console.log(result) // true

some
var arr = [1,2,3]
var result = arr.some(function(item,index){
  // 用来判断数组中,只要有一个元素满足条件
  if(item<2){
    return true
  }
})
console.log(result) // true

sort
var arr = [1,2,3]
var arr2 = arr.sort(function(a,b){

  // 从小到大排序
  return a - b
  // 从大到小排序
  return b - a
})
console.log(arr2)

map
var arr = [1,2,3,4]
var arr2 = arr.map(function(item,index){
  // 将元素重新组装，并返回
  return '<b>' + item + '</b>'
})
console.log(arr2) // ["<b>1</b>", "<b>2</b>", "<b>3</b>", "<b>4</b>"]

filter
var arr = [1,2,3]
var arr2 = arr.filter(function(item,index){
  // 通过某一个条件过滤数组
  if(item>=2){
    return true
  }
})
console.log(arr2) //[2, 3]

对象api
var obj = {
  x:100,
  y:200,
  z:300
}
var key
for(key in obj){
  // hasOwnProperty 是过滤自身的属性 而不是原型中属性
  if(obj.hasOwnProperty(key)){
    console.log(key,obj[key])
  }
}
```

### 总结
---
```javascript
js中3座大山：原型和原型链、作用域和闭包、同步和异步

```

### js-web-api
1. 回顾Js基础知识
```javascript
变量类型和计算
原型和原型链
闭包和作用域
异步和单线程
其他（如日期、Math、）
```
2. JS-Web-API
3. 总结



### js原生ajax请求

```javascript
var xhr = new XMLHttpRequest()
xhr.open('GET','./api',false)
xhr.onreadystatechange = function(){
  // 异步执行
  if(xhr.readyState == 4){
    if(xhr.status == 200){
      console.log(xhr.responseText)
    }
  }
}
xhr.send(null)
```




