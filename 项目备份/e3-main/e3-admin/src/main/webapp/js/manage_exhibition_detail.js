'use strict'

$(function () {
  // 用户管理-用户详情-启用或者停用用户
  var startinfo = ''
  $('#accountStatus').click(function () {
    if (startinfo === $(this).val()) {
      return false
    }
    startinfo = $(this).val()
    console.log(startinfo)
    $.ajax({
      type: 'post',
      url: config.startuser.url.startuser,
      dataType: 'json',
      data: {
        'userId': 2,
        'remark': startinfo
      },
      success: function (data) {

      }
    })
  })
  // 用户管理 备注展装公司用户
  $('#exremarkUser').click(function () {
    var remarkinfo = $('#exremarkInfo').val()
    console.log(remarkinfo)
    $.ajax({
      type: 'post',
      url: config.remarkuser.url.remarkuser,
      dataType: 'json',
      data: {
        'userId': 2,
        'remark': remarkinfo
      },
      success: function (data) {

      }
    })
  })
})