'use strict'
$(function () {
  // 施工图设计阶段
  let deepenFlag = 0
  $('#btnDeepennext').click(function () {
    deepenFlag++
    console.log(deepenFlag)
    if (deepenFlag >= 3) {
      deepenFlag = 3
      return false
    }
    if (deepenFlag >= 1) {
      $('#deepenDesign').hide()
    }
    if (deepenFlag >= 2) {
      $('#deepenDesign').hide()
      $('.construction-wrap').removeClass('none')
    }
    // 进度条的变化
    $('.od-deschedulenav').children('div').removeClass('od-menucurrent')
    $('.od-deschedulenav').children('div').eq(deepenFlag).addClass('od-menucurrent')
    // 状态的变化
    $('.deepenDesign').children('div.od-newstatusbox').removeClass('current-status')
    $('.deepenDesign').children('div.od-newstatusbox').eq(deepenFlag).addClass('current-status')
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
  // 点击立即支付
  $('.pay-btn').on('click', function () {
    location.href = 'order_detailconstruction_2.html'
  })
  // 设计图上传之后
  $('.wait-designpic-wp').on('click',function(){
    $('.construction-zip').removeClass('none')
    $('.construction-zip').siblings('.no-quote').addClass('none')
    // 进度条的变化
    // 进度条的变化
    deepenFlag = 2
    $('.od-deschedulenav').children('div').removeClass('od-menucurrent')
    $('.od-deschedulenav').children('div').eq(deepenFlag).addClass('od-menucurrent')
    // 状态的变化
    $('.deepenDesign').children('div.od-newstatusbox').removeClass('current-status')
    $('.deepenDesign').children('div.od-newstatusbox').eq(deepenFlag).addClass('current-status')
  })
  //点击确认收稿
  $('.overdraft').on('click',function(){
    location.href = 'order_detailconstruction_completed.html'
  })
})