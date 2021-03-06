'use strict'
$(function () {
  // 第一步
  $('#firstStep').click(function () {
    $('#registedform').parsley()
    .whenValidate({ group: 'step1' })
    .done(function () {
      $('.first-infobox').hide()
      $('.second-infobox').show()
      $('.regtopmenu').children('li').removeClass('menu-select')
      $('.regtopmenu').children('li').eq(1).addClass('menu-select')
    })
  })
  // 第二步
  $('#secondStep').click(function () {
    var businesslicense = $('.businesslicense').val()
    var frontId = $('.front-id').val()
    var backendId = $('.backend-id').val()
    var len = $('.selectcase-ul li').length
    var num = 0
    for (var i = 0; i < len; i++) {
      var inputVal = $('.selectcase-ul li').eq(i).find('input').val()
      if (inputVal !== '') {
        num++
      }
    }
    if (businesslicense === '') {
      utils.bomb('请上传工作室营业执照')
    } else if (frontId === '' || backendId === '') {
      utils.bomb('请上传身份证的正反面照片')
    } else if (num < 2) {
      utils.bomb('请上传2-10个代表作品案例')
    } else {
      $('#registedform').parsley()
      .whenValidate({ group: 'step2' })
      .done(function () {
        $('.first-infobox').hide()
        $('.second-infobox').hide()
        $('.third-infobox').show()
        $('.regtopmenu').children('li').removeClass('menu-select')
        $('.regtopmenu').children('li').eq(2).addClass('menu-select')
      })
    }
  })
  /* 上传图片 上传工作室营业执照,身份证 */
  $('.businesslicense, .front-id, .backend-id').on('change', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 50,
      distimg: $(that).prev('img')
    }
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).siblings('.delete').show()
      // utils.createImgli('.educationimg', 1)
    })
  })
  /* 上传精选案例 */
  $('.selectcase-ul').on('change', '.worksimg-li-must input', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 50,
      distimg: $(that).prev('img')
    }
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).siblings('.delete').show()
      var val1 = $('.selectcase-ul .worksimg-li-must:eq(0)').find('input').val()
      var val2 = $('.selectcase-ul .worksimg-li-must:eq(1)').find('input').val()
      var src = $(that).siblings('img').attr('src')
      if (val1 && val2 && src === '') {
        utils.createImgli('.selectcase-ul', 10)
      }
    })
  })
    /* 上传图片 学历证明 */
  $('.educationimg').on('change', '.worksimg-li-must input', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 50,
      distimg: $(that).prev('img')
    }
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).siblings('.delete').show()
      utils.createImgli('.educationimg', 3)
    })
  })
    /* 上传图片 职称 */
  $('.jobnameimg').on('change', '.worksimg-li-must input', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 50,
      distimg: $(that).prev('img')
    }
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).siblings('.delete').show()
      utils.createImgli('.jobnameimg', 5)
    })
  })
     /* 上传图片 获奖证书 */
  $('.awardimg').on('change', '.worksimg-li-must input', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 50,
      distimg: $(that).prev('img')
    }
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).siblings('.delete').show()
      utils.createImgli('.awardimg', 5)
    })
  })
  // 删除图片
  $('.upload-ul').on('click', '.delete', function () {
    $(this).siblings('img').attr('src', '')
    $(this).siblings('input').val('')
    $(this).hide()
  })
  // 获取验证码
  $('#getMescode').click(function () {
    utils.settime($('#getMescode'))
  })
 // 城市选择
  $('#city').citySelect({
    // prov: "上海",
    // city: "嘉定区"
  })
  // 日期选择
  $('.date_picker').date_input()
  // 选填资料的显示隐藏
  var boor = true
  $('.switch-btn').on('click', function () {
    if (boor) {
      $('.choice-mainbox').hide()
      $('.switch-btn span').html('展开')
      $('.switch-btn i').attr('class', 'switch-icon')
      boor = !boor
    } else {
      $('.choice-mainbox').show()
      $('.switch-btn span').html('收起')
      $('.switch-btn i').attr('class', 'switch-icon2')
      boor = !boor
    }
  })
})
