### 变量类型与计算
----
1. Js中使用typeof能得到哪些类型
2. 何时使用 === 何时使用 ==
3. Js中有哪些内置函数
4. Js按变量存储方式区分为哪些类型，并描述其特点
5. 如何理解JSON

#### 基础知识点
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
```





