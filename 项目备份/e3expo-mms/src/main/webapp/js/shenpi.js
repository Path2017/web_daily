$('#left-nav-application').addClass('active')
$('.notsure-btn').click(function () {
  $('.alertB').hide()
})
$('.zhuxiao-btn').on('click', function () {
  $('.alertB').show()
  $('#resetpassword,#pass,#notpass').hide()
  $('#zhuxiao').show()
})
$('.xiugaimima-btn').on('click', function () {
  $('.alertB').show()
  $('#resetpassword').show()
  $('#zhuxiao,#pass,#notpass').hide()
})
$('.sure-btn').click(function () {
  if($('.resetpwd').val()===''){
    $('.resetpwd-noneerr').show()
    return false
  }
})
$('.resetpwd').focus(function () {
  $('.resetpwd-noneerr').hide()
})
/* shenpi -- start */
$('.agree-btn').on('click', function () { //审批通过
  var that = this
  $('.alertB').show()
  $('#pass').show()
  $('#zhuxiao,#resetpassword,#notpass').hide()
  var applicationId = $(that).attr('applicationId')
  $('#applicationPass').click(function () { //发送请求
    console.log(applicationId)
    $.ajax({
      type: 'get',
      url: reqUrl.applicationApprove + applicationId,
      dataType: 'text', // 请求数据类型
      cache: true,
      success: function (data) {
        console.log(data)
        $(that).parent('td').html('审批已通过')
        $('.alertB').hide()
      },
      error: function (data) {
        alert('失败')
      }
    })
  })
})
$('.disagree-btn').on('click', function () {
  $('.alertB').show()
  $('#notpass').show()
  $('#zhuxiao,#resetpassword,#pass').hide()
  var that = this
  var applicationId = $(that).attr('applicationId')

  $('#applicationFail').click(function () {
    $.ajax({
      type: 'get',
      url: reqUrl.applicationReject + applicationId,
      dataType: 'text', // 请求数据类型
      cache: true,
      success: function (data) {
        $(that).parent('td').html('审批未通过')
        $('.alertB').hide()
      },
      error: function (data) {
        console.log(data)
      }
    })
  })
})
$('#datetimepicker').datetimepicker({
  icons: {
    time: 'fa fa-clock-o',
    date: 'fa fa-calendar',
    up: 'fa fa-chevron-up',
    down: 'fa fa-chevron-down',
    previous: 'fa fa-chevron-left',
    next: 'fa fa-chevron-right',
    today: 'fa fa-crosshairs',
    clear: 'fa fa-trash'
  },
  format: 'YYYY-MM-DD'
})
$('#datetimepicker2').datetimepicker({
  icons: {
    time: 'fa fa-clock-o',
    date: 'fa fa-calendar',
    up: 'fa fa-chevron-up',
    down: 'fa fa-chevron-down',
    previous: 'fa fa-chevron-left',
    next: 'fa fa-chevron-right',
    today: 'fa fa-crosshairs',
    clear: 'fa fa-trash'
  },
  format: 'YYYY-MM-DD'
})
/* shenpi -- end */

