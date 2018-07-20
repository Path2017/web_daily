'use strict'
// 配置文件
// let baseurl = 'http://localhost:8082'
// let baseurl = "../../js/data.json"
// const config = {
//   'login': {
//     appName: '设计师闭环',
//     url: {
//       'login': baseurl + '/login'
//     }
//   },
//   'useraudit':{
//     appNmae: '用户审核',
//     url:{
//       'detail': baseurl + '/userAuditDetailView', // 获取设计师或者展装公司详情详情
//       'list' : baseurl + '/userAuditPagedData', // 获取用户的审核列表
//     }
//   }
// }

let baseurl = 'http://localhost:8082'
const config = {
  'login': {
    appName: '设计师闭环',
    url: {
      'login': baseurl + '/login'
    }
  },
  'managelist': {
    appName: '用户管理-获取用户列表',
    url: {
      'managelist': baseurl + '/pagedUserView'
    }
  },
  'startuser': {
    appName: '用户管理-用户详情-启用或者停用用户',
    url: {
      'startuser': baseurl + '/startUser'
    }
  },
  'remarkuser': {
    appName: '用户管理-用户详情-管理员备注用户',
    url: {
      'remarkuser': baseurl + '/remarkUser'
    }
  },
  'orderlist': {
    appName: '订单管理-获取订单列表',
    url: {
      'orderlist': baseurl + '/pagedOrder'
    }
  },
}
console.log(config)

