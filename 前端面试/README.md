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

typeof 只能区分值类型的详细类型，对于引用类型无法区分具体类型。但对于js函数而言，函数是一个特殊的引用类型，在js中有很高的地位，所以typeof能区分出来
```




