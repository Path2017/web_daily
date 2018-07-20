'use strict'

$(function () {
  // 获取展装公司用户管理-获取用户列表 用户类型'1'设计师;'2'展装公司
  var reqUrl = config.managelist.url.managelist
  var userType = 2
  var countryId = 1
  var provinceId = 1
  var cityId = 1
  var name = '张三'
  var mobile = '15000895907'
  var status = '1'
  var startTime ='12345678910'
  var endTime = '12345678910'
  var pageIndex = '1'
  var getUserlist = utils.getUserlist(reqUrl,userType,countryId,provinceId,cityId,name,mobile,status,startTime,endTime,pageIndex);
  console.log(getUserlist)

  $.getJSON('../../js/manage.json', function (data) {
    $('#exhibitionList').empty()
    for (var i = 0; i < data.userlist.length; i++) {
      var nowdata = data.userlist[i]
      var exhibitionlist = '<tr><td>' + nowdata.countryName + '-' + nowdata.provinceName + '-' + nowdata.cityName + '</td><td>' + nowdata.name + '</td><td>' + nowdata.id + '</td><td>' + nowdata.mobile + '</td><td>3~5年</td><td>停用</td><td>2017-9-8</td><td><a href="manage_exhibition_detail.html?id=' + nowdata.id + '">认证资料</a>&nbsp;&nbsp;<a href="manage_designer_shop.html?id=' + nowdata.id + '">店铺资料</a></td></tr>'
      $('#exhibitionList').append(exhibitionlist)
    }
  })
  // 检索
  $('#searchUser').click(function(){
    console.log('检索')
    countryId = $('#userArea').val()
    provinceId = $('#userPro').val()
    cityId = $('#userCity').val()
    name = $('#userName').val()
    status = $('#userStatus').val()
    startTime = Date.parse($('#datetimepicker input.form-control').val())
    endTime = Date.parse($('#datetimepicker2 input.form-control').val())
    getUserlist = utils.getUserlist(reqUrl,userType,countryId,provinceId,cityId,name,mobile,status,startTime,endTime,pageIndex);
    console.log(getUserlist)
  })
})