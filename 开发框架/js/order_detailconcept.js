'use strict'

// 概念设计阶段
$(function () { 
  let nowOrderstatus = 0
  
  // 点击结束报价
  // let currentDesign = true
  $('.closequotation-btn').on('click', function () {
    $(this).addClass('none')
    $('.waitquotation-wp').addClass('none')
    $('.choose-designer').removeClass('none')
    // 进度条的变化
    $('.od-schedulenav').children('div').removeClass('od-menucurrent')
    $('.od-schedulenav').children('div').eq(1).addClass('od-menucurrent')
    // 状态的变化
    $('.design-stage').children('div.od-newstatusbox').removeClass('current-status')
    $('.design-stage').children('div.od-newstatusbox').eq(1).addClass('current-status')
  })

  // 选择设计师
  $('.choose-designer').on('click', function () {
    $('.choose-designer').removeClass('choosed')
    $(this).addClass('choosed')
    $('.payment-wp').removeClass('none')
  })
  // 点击立即支付
  $('.pay-btn').on('click', function () {
    $('#quote-wraper, .payment-wp').addClass('none')
    $('.wait-designpic-wp ,.ds-choosed-mg').removeClass('none')
    // 支付成功 进度条变化
    // 进度条的变化
    $('.od-schedulenav').children('div').removeClass('od-menucurrent')
    $('.od-schedulenav').children('div').eq(2).addClass('od-menucurrent')
    // 状态的变化
    $('.design-stage').children('div.od-newstatusbox').removeClass('current-status')
    $('.design-stage').children('div.od-newstatusbox').eq(2).addClass('current-status')
  })
  // 有稿件上传时
  $('.wait-designpic-wp').on('click', function () {
    $('.wait-designpic-wp .no-quote').addClass('none')
    $('.designdraft-notend').removeClass('none')
  })
  // 点击申请改稿
  $('.apply-change').on('click', function () {
    $('.suggestion-form').removeClass('none')
  })
  // 点击改稿提交
  $('.change-submit').on('click', function () {
    $('.dsdraft-change-wp').removeClass('none')
    $(this).parents('.designdraft').css('height', '44px')
    $('#current-design').find('span').html('展开')
    $('#current-design').find('i').attr('class','arrow')
    // currentDesign = false
  })
  $('#current-design').on('click', function () {
    var boo = $(this).find('span').html()
    if (boo === '收起') {
      $(this).parents('.designdraft').css('height', '44px')
      $(this).find('span').html('展开')
      $(this).find('i').attr('class','arrow')
      // currentDesign = false
    } else {
      $(this).parents('.designdraft').css('height', 'auto')
      $(this).find('span').html('收起')
      $(this).find('i').attr('class','arrow-up')
      // currentDesign = true
    }
  })
  $('.old-draft').on('click', function () {
    var boo = $(this).find('span').html()
    if (boo === '展开') {
      $(this).parents('.designdraft2').css('height', 'auto')
      $(this).find('span').html('收起')
      $(this).find('i').attr('class','arrow-up')
    } else {
      $(this).parents('.designdraft2').css('height', '44px')
      $(this).find('span').html('展开')
      $(this).find('i').attr('class','arrow')
    }
  })
  $('.suggestion').on("keyup", function () {
    $('.now-num').text($('.suggestion').val().length) 
    if ($('.suggestion').val().length > 200) {
      $('.now-num').text(200)
      $('.suggestion').val($('.suggestion').val().substring(0, 200))
    }
  })
  // $('.now-num').text($('.suggestion').val().length) //这句是在刷新的时候仍然显示字数

  // 取消订单弹框的选择
  $('.dec-list').find('label').click(function (e) {
    e.stopPropagation()
    console.log($(this).children('input').prop('checked'))
    if ($(this).children('input').prop('checked')) {
      $(this).children('span.dec-checkbox').addClass('dec-current')
    } else {
      $(this).children('span.dec-checkbox').removeClass('dec-current')
    }
  })
  // 确认取消
  $('#odsureBtn').click(function () {
    $('#decancelbox').fadeOut()
  })
  // 再想想
  $('#odconsiderBtn').click(function () {
    $('#decancelbox').fadeOut()
  })
})