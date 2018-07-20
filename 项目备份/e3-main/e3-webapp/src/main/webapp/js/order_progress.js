'use strict'

$(function () {
  let nowstatusFlag = 0;
  $('#btnDesnext').click(function () {
    nowstatusFlag = nowstatusFlag + 1
    if (nowstatusFlag > 1) {
      return false
    }
    $('.op-deschedulenav').children('div').removeClass('od-menucurrent')
    $('.deepenDesign').children('div').removeClass('current-status')
    $('.op-deschedulenav').children('div').eq(nowstatusFlag).addClass('od-menucurrent')
    $('.deepenDesign').children('div').eq(nowstatusFlag).addClass('current-status')
    if (nowstatusFlag === 1) {
      $('.od-designupload-wp').removeClass('none')
      $('.od-designupload-wp').removeClass('none')
    }
  })
  /* 上传图片 */
  $('.ds-upload-ul .inputbox input').on('change', function (e) {
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
  /* 上传压缩图片 */
  $('.zip-input').on('change', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 300,
      distimg: $(that).prev('img'),
      format: ['.ZIP','.RAR']
    }
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).parent('li').css('background-image','url(../img/3dmaxsuccess.png)')
      $(that).siblings('.deletezip').show()
    })
  })
  // 删除图片
  $('.ds-upload-ul').on('click', '.delete', function () {
    $(this).siblings('img').attr('src', '')
    $(this).siblings('input').val('')
    $(this).hide()
  })
  $('.deletezip').on('click',function(){
    $(this).siblings('input').val('')
    $(this).hide()
    $(this).parent('li').css('background-image','url(../img/3dmax.png)')
  })
  // 点击显示隐藏
  $('.pull-arrow').on('click',function(){
    let isClose = $(this).find('span').html()
    if(isClose==='展开'){
      $(this).find('span').html('收起')
      $(this).parent('p').siblings('.history-ct').removeClass('none')
      $(this).find('i').attr('class','arrow-up')
    }else{
      $(this).find('span').html('展开')
      $(this).parent('p').siblings('.history-ct').addClass('none')
      $(this).find('i').attr('class','arrow')
    }
  })
  $('.designstep1-switch').click(function () {
    // 概念设计图的显示和隐藏
    var isClose = $(this).find('span').html()
    if( isClose === '收起'){
      $('.history-draft-wp').addClass('none')
      $(this).find('span').html('展开')
      $(this).find('i').attr('class', 'switch-icon')
      $(this).parent('p').addClass('od-title-line')
    }else{
      $('.history-draft-wp').removeClass('none')
      $(this).find('span').html('收起')
      $(this).find('i').attr('class', 'switch-icon2')
      $(this).parent('p').removeClass('od-title-line')
    }
  })
  $('.designstep2-switch').on('click',function(){
    var isClose = $(this).find('span').html()
    if( isClose === '收起'){
      $('.constructin-completed').addClass('none')
      $(this).find('span').html('展开')
      $(this).find('i').attr('class', 'switch-icon')
      $(this).parent('p').addClass('od-title-line')
    }else{
      $('.constructin-completed').removeClass('none')
      $(this).find('span').html('收起')
      $(this).find('i').attr('class', 'switch-icon2')
      $(this).parent('p').removeClass('od-title-line')
    }
  })

  // 上传进度
  // 上传进度
  var progressNum = 0
  var settime = setInterval(function(){
    progressNum ++
    if(progressNum>100){
      clearInterval(settime)
      return false
    }
    progress(progressNum)
  },100)   
  function progress(progressNum){
    var progress = -135+progressNum*3.6 
    var progress2 = -225+progressNum*3.6-180 
    $('.progress-txt').html(progressNum+'%')
    if(progress<= 45){
      $('.progress-right span').css('transform', "rotate("+ progress + "deg)")
    }else{
      $('.progress-right span').css('transform', "rotate(45deg)")
      $('.progress-left span').css('transform', "rotate(" + progress2 + "deg)")
    }
  }
})