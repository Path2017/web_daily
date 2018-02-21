// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueI18n from 'vue-i18n'
// 语言包
import LangEn from '../static/lang/en'
import LangZhCHS from '../static/lang/zhCHS'
import LangZhCHT from '../static/lang/zhCHT'

Vue.use(VueI18n)

Vue.config.productionTip = false
const i18n = new VueI18n({
  locale: 'en',
  messages: {
    'en': LangEn,
    'zhCHS': LangZhCHS,
    'zhCHT': LangZhCHT
  }
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  components: { App },
  template: '<App/>'
})
