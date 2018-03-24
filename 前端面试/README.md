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

```






