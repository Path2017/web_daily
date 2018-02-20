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
```javascript
(1) 安装

npm install vue-i18n
(2) 工程中使用

[1] 在main.js中引入vue-i18n

import VueI18n from 'vue-i18n'

Vue.use(VueI18n)
[2] 语言资源

const messages = {
  //英文
  en: {
    message: {
      hello: 'hello',
      about: 'about',
      welcome: "Welcome"
    }
  },
  //简体中文
  zhCHS: {
    message: {
      hello: '你好',
      about: '关于',
      welcome: "欢迎"
    }
  },
  //繁体中文
  zhCHT: {
    message: {
      hello: '妳好',
      about: '關於',
      welcome: "歡迎"
    }
  }
[3] VueI18n实例

const i18n = new VueI18n({
  //定义默认语言
  locale: 'en', 
  messages
})
[4] 挂载到Vue的实例上

new Vue({
  el: '#app',
  router,
  i18n, //<====
  template: '<App/>',
  components: { App }
})
[5] 标记在HTML中

注意：这里是$t
h3 {{ $t("message.hello") }}
完成上述功能后，运行，可以看到内容显示为hello，修改上述第三步的locale为zhCHS后运行，可以看到页面变为了你好。
[6] 标记在js中

computed:{
    welcomeMessage(){
       return this.username + ', '+ this.$t("message.welcome");
     } 
},
```