// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)
Vue.config.productionTip = false

// 语言资源
const messages = {
  // 英文
  en: {
    message: {
      hello: 'hello',
      about: 'about',
      welcome: 'Welcome'
    }
  },
  // 简体中文
  zhCHS: {
    message: {
      hello: '你好',
      about: '关于',
      welcome: '欢迎'
    }
  },
  // 繁体中文
  zhCHT: {
    message: {
      hello: '妳好',
      about: '關於',
      welcome: '歡迎'
    }
  }
}
// VueI18n实例
const i18n = new VueI18n({
  // 定义默认语言
  locale: 'en',
  messages
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  // 挂载到Vue的实例上
  i18n,
  components: { App },
  template: '<App/>'
})
