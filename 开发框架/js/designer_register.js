'use strict'

$(function () {
  // 第一步
  // $('#firstStep').click(function () {
  //   // 验证手机号和验证码
  //   $.ajax({
  //     type: 'post',
  //     url: config.checkvericode.url.checkvericode,
  //     dataType: 'json',
  //     contentType: 'application/json',
  //     data: JSON.stringify({
  //       'veriCode': $('#datacode').val(),
  //       'mobile': $('#dataphone').val()
  //     }),
  //     success: function (data) {
  //       if (data.code === 200) {
  //         $('#registerform').parsley()
  //           .whenValidate({
  //             group: 'step1'
  //           })
  //           .done(function () {
  //             $('.first-infobox').hide()
  //             $('.second-infobox').show()
  //             $('.regtopmenu').children('li').removeClass('menu-select')
  //             $('.regtopmenu').children('li').eq(1).addClass('menu-select')
  //           })
  //       }
  //       console.log(data)
  //     }
  //   })
  // })
  // 第一步
  $('#firstStep').click(function () {
    $('#registerform').parsley()
      .whenValidate({
        group: 'step1'
      })
      .done(function () {
        let checkbox = $('.ischeck').is(':checked')
        if(!checkbox){
          utils.bomb('请已阅读并接受《易宜益设计师服务协议》')
        }else{
          $('.first-infobox').hide()
          $('.second-infobox').show()
          $('.regtopmenu').children('li').removeClass('menu-select')
          $('.regtopmenu').children('li').eq(1).addClass('menu-select')
          // 验证手机号和验证码
          $.ajax({
            type: 'post',
            url: config.checkvericode.url.checkvericode,
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
              'veriCode': $('#datacode').val(),
              'mobile': $('#dataphone').val()
            }),
            success: function (data) {
              if (data.code === 200) {
                console.log(data)
              }
            }
          })
        }
      })
  })
  // 第二步
  $('#secondStep').click(function () {
    let len = $('.worksimg-ul li').length
    let num = 0
    let frontId = $('.front-id').val()
    let backendId = $('.backend-id').val()
    for (let i = 0; i < len; i++) {
      let inputVal = $('.worksimg-ul li').eq(i).find('input').val()
      if (inputVal !== '') {
        num++
      }
    }
    if (frontId === '' || backendId === '') {
      utils.bomb('请上传身份证的正反面照片')
    } else if (num < 2) {
      utils.bomb('请上传2-10个代表作品案例')
    } else {
      $('#registerform').parsley()
        .whenValidate({
          group: 'step2'
        })
        .done(function () {
          $('.first-infobox').hide()
          $('.second-infobox').hide()
          $('.third-infobox').show()
          $('.regtopmenu').children('li').removeClass('menu-select')
          $('.regtopmenu').children('li').eq(2).addClass('menu-select')
        })
    }
  })

  // 获取验证码
  let vericodeFlag = 1
  $('#getMescode').click(function () {
    if (vericodeFlag !== 1) {
      return false
    }
    vericodeFlag = 2
    utils.settime($('#getMescode'))
    console.log(config.vericode.url.vericode)
    $.ajax({
      type: 'post',
      url: config.vericode.url.vericode,
      dataType: 'json',
      contentType: 'application/json',
      data: JSON.stringify({
        'mobile': $('#dataphone').val()
      }),
      success: function (data) {
        console.log(data)
      }
    })
  })
  /* 上传图片 身份证 */
  $('.front-id, .backend-id').on('change', function (e) {
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
    })
  })
  /* 上传图片 代表作品 */
  $('.worksimg-ul').on('change', '.worksimg-li-must input', function (e) {
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
      var val1 = $('.worksimg-ul .worksimg-li-must:eq(0)').find('input').val()
      var val2 = $('.worksimg-ul .worksimg-li-must:eq(1)').find('input').val()
      var src = $(that).siblings('img').attr('src')
      if (val1 && val2 && src === '') {
        utils.createImgli('.worksimg-ul', 10)
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
  /* 上传图片 职称证明 */
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
  let success = `<i class="success-icon2"></i>`
  $('#registerform').parsley()
  .on('field:success',function(){
    this.$element.siblings('.success-icon2').remove()
    this.$element.after($(success))
  })
  .on('field:error',function(el){
    el.$element.siblings('.success-icon2').remove()
  })
   // 城市选择
  //  $('#city').citySelect({
  //   // prov: "上海",
  //   // city: "嘉定区"
  // })
})
