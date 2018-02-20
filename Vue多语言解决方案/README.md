## Vue中多语言解决方案

```ruby
使用vue-i18n实现多语言
```

### 构建Vue项目
```ruby 
vue init webpack vuedir

cd vuedir

npm run dev
```
* autoOpenBrowser: true, // 自动打开浏览器
### 详解vue-i18n实现多语言
1. 前言
```ruby
(1) 需求

Vue工程，需要实现多语言切换功能。
(2) vue-i18n插件

npm中对vue-i18n的描述及文档
`https://www.npmjs.com/package/vue-i18n`
将使用这个插件实现多语言
(3) 兼容性

支持Vue.js 2.x以上版本
```
2. 实战
```ruby
(1) 安装

npm install vue-i18n
(2) 工程中使用

[1] 在main.js中引入vue-i18n

import VueI18n from 'vue-i18n'

Vue.use(VueI18n)
[2] 语言资源
```