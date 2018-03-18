'use strict'
// 配置文件
let baseurl = 'http://localhost:8082'
const config = {
  'login': {
    appName: '设计师闭环',
    url: {
      'login': baseurl + '/login'
    }
  },
  'vericode': {
    appName: '注册获取验证码',
    url: {
      'vericode': baseurl + '/veriCode'
    }
  },
  'checkvericode': {
    appName: '校验验证码',
    url: {
      'checkvericode': baseurl + '/checkVeriCode'
    }
  }
}
console.log(config)
