'use strict'

$(function () {
  // 用户管理-获取用户列表
  var reqUrl = config.managelist.url.managelist
  var userType = 1
  var countryId = 1
  var provinceId = 1
  var cityId = 1
  var name = '张三'
  var mobile = ''
  var status = '1'
  var startTime =''
  var endTime = ''
  var pageIndex = '1'
  var getUserlist = utils.getUserlist(reqUrl,userType,countryId,provinceId,cityId,name,mobile,status,startTime,endTime,pageIndex);
  console.log(getUserlist)
  
  $.getJSON('../../js/manage.json', function (data) {
    $('#designerlist').empty()
    for (var i = 0; i < data.userlist.length; i++) {
      var nowdata = data.userlist[i]
      var designerlist = '<tr><td>' + nowdata.countryName + '-' + nowdata.provinceName + '-' + nowdata.cityName + '</td><td>' + nowdata.name + '</td><td>' + nowdata.id + '</td><td>' + nowdata.mobile + '</td><td>3~5年</td><td>停用</td><td>2017-9-8</td><td><a href="manage_designer_certification.html?id=' + nowdata.id + '">认证资料</a>&nbsp;&nbsp;<a href="manage_designer_shop.html?id=' + nowdata.id + '">店铺资料</a></td></tr>'
      $('#designerlist').append(designerlist)
    }
    console.log(data.userlist)
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