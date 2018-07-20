'use strict'

$(function () {
  // $.ajax({
  //   type: 'post',
  //   url: config.orderlist.url.orderlist,
  //   dataType: 'json',
  //   data: {
  //     'exhibitionCity': '参展城市',
  //     'rfpNo': '订单号',
  //     'companyName': '展览公司',
  //     'linkManName': '联系人姓名',
  //     'linkManPhone': '联系人手机号',
  //     'statusKey': '状态',
  //     'startTime': '开始时间',
  //     'endTime': '结束时间',
  //     'pageIndex': '页数'
  //   },
  //   success: function (data) {

  //   }
  // })
  // 订单管理-获取订单列表
  var exhibitionCity = ''
  var rfpNo = ''
  var companyName = ''
  var linkManName = ''
  var linkManPhone = ''
  var statusKey = ''
  var startTime = ''
  var endTime = ''
  var pageIndex = ''
  var reqUrl = config.orderlist.url.orderlist
  // 得到请求到的数据
  var orderlists = getOrderlist(reqUrl, exhibitionCity, rfpNo, companyName, linkManName, linkManPhone, statusKey, startTime, endTime, pageIndex)
  // 检索
  $('.searchBtn').click(function () {
    exhibitionCity = $('#listCity').val()
    rfpNo = $('#listOrder').val()
    companyName = $('#listKeyword').val()
    statusKey = $('#listStatus').val()
    startTime = Date.parse($('#datetimepicker input.form-control').val())
    endTime = Date.parse($('#datetimepicker2 input.form-control').val())
    orderlists = getOrderlist(reqUrl, exhibitionCity, rfpNo, companyName, linkManName, linkManPhone, statusKey, startTime, endTime, pageIndex)
  })
  // 请求本地数据
  $.getJSON('../../js/manage.json', function (data) {
    $('#orderList').empty()
    for (var i = 0; i < data.orderlist.data.length; i++) {
      var nowdata = data.orderlist.data[i]
      var list = '<tr><td>'+nowdata.exhibitionCity+'</td><td>'+nowdata.rfpNo+'</td><td>'+nowdata.companyName+'</td><td>'+nowdata.linkManName+'</td><td>'+nowdata.linkManPhone+'</td><td>'+nowdata.statusRemark+'</td><td>'+new Date(nowdata.updateTime)+'</td><td><a href="manage_order_detail.html?id='+nowdata.id+'">查看详情</a></td></tr>'
      $('#orderList').append(list)
    }
    console.log(data.orderlist.data)
  })
})

/*
 * 获取订单列表
 * @param {reqUrl} 请求链接
 * @param {exhibitionCity} 参展城市
 * @param {rfpNo} 订单号
 * @param {companyName} 展览公司
 * @param {linkManName} 联系人姓名
 * @param {linkManPhone} 联系人手机号
 * @param {statusKey} 状态
 * @param {startTime} 开始时间
 * @param {endTime} 结束时间
 * @param {pageIndex} 页数
 */
function getOrderlist(reqUrl, exhibitionCity, rfpNo, companyName, linkManName, linkManPhone, statusKey, startTime, endTime, pageIndex) {
  $.ajax({
    type: 'post',
    url: config.orderlist.url.orderlist,
    dataType: 'json',
    data: {
      'exhibitionCity': '参展城市',
      'rfpNo': '订单号',
      'companyName': '展览公司',
      'linkManName': '联系人姓名',
      'linkManPhone': '联系人手机号',
      'statusKey': '状态',
      'startTime': '开始时间',
      'endTime': '结束时间',
      'pageIndex': '页数'
    },
    success: function (data) {
      return data
    }
  })
}