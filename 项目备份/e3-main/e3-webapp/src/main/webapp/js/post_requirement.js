'use strict'
$(function () {
  // 发布需求页面--第一步 项目详情切换到设计需求
  // $('#pqfirstep').click(function () {
  //   $('#step1form').parsley()
  //   .on('form:success',function(){
  //     window.location.href = './post_requirement_step2.html'
  //   })
  // })
  // 第二步 设计需求切换到设计师类型
  var day = new Date()
  let month = day.getMonth()+1
  let currentDay = day.getFullYear()+'-'+month+'-'+day.getDate()
  $('.date_picker').val(currentDay)
  $('#pqsenstep').click(function () {
    // window.location.href = './post_requirement_step3.html'
    let dstype1 = $('#dstype1').find('.nowselect').length
    let dstype3 = $('#dstype3').find('.nowselect').length
    let dstype4 = $('#dstype4').find('.nowselect').length
    var pmSelectlist = $('.pm-selectlist').find('.nowselect').length
    if(dstype1===0){
      utils.bomb('请选择展台开口')
      return false
    }else if(dstype3===0){
      utils.bomb('请选择主体结构')
      return false
    }else if(dstype4===0){
      utils.bomb('请选择材质')
      return false
    }else if(pmSelectlist===0){
      utils.bomb('请选择至少一项功能')
      return false
    }
  })
  // 返回上一步
  $('#backforward').click(function () {
    window.location.href = './post_requirement_step2.html'
  })
  // 展台开口选择
  $('#dstype1').children('div.select-box').click(function () {
    $(this).addClass('nowselect')
    $(this).siblings('div').removeClass('nowselect')
    // 索引号对应选择项
    console.log($(this).index())
  })
  // 第二步 begin
  // 特殊需求选择
  $('#dstype2').children('div.select-box').click(function () {
    $(this).addClass('nowselect')
    $(this).siblings('div').removeClass('nowselect')
    // 索引号对应选择项
    console.log($(this).index())
  })
  // 主体结构选择
  $('#dstype3').children('div.select-box').click(function () {
    $(this).addClass('nowselect')
    $(this).siblings('div').removeClass('nowselect')
    // 索引号对应选择项
    console.log($(this).index())
  })
  // 材质选择
  $('#dstype4').children('div.select-box').click(function () {
    $(this).addClass('nowselect')
    $(this).siblings('div').removeClass('nowselect')
    // 索引号对应选择项
    console.log($(this).index())
  })
  // 设计需求 选填资料的展开与合上
  var toggleFlag = 1
  $('#dsopen').click(function () {
    toggleFlag = toggleFlag * (-1)
    if (toggleFlag === 1) {
      $('#dsclose').show()
      $('#openstatus').removeClass('dsopen')
      $('#openstatus').addClass('dsclose')
    }
    if (toggleFlag === -1) {
      $('#dsclose').hide()
      $('#openstatus').removeClass('dsclose')
      $('#openstatus').addClass('dsopen')
    }
  })
  // 参展理念 选填资料的展开与合上
  var toggleFlag2 = 1
  $('#dsopen2').click(function () {
    toggleFlag2 = toggleFlag2 * (-1)
    if (toggleFlag2 === 1) {
      $('#dsclose2').show()
      $('#openstatus2').removeClass('dsopen')
      $('#openstatus2').addClass('dsclose')
    }
    if (toggleFlag2 === -1) {
      $('#dsclose2').hide()
      $('#openstatus2').removeClass('dsclose')
      $('#openstatus2').addClass('dsopen')
    }
  })
  // 功能区域选择
  $('#dstype5').find('div.select-box').click(function () {
    if ($(this).hasClass('nowselect')) {
      $(this).removeClass('nowselect')
      $(this).siblings('div.fe-checkbox').hide()
    } else {
      $(this).addClass('nowselect')
      $(this).siblings('div.fe-checkbox').css('display', 'inline-block')
    }
  })
  // 数量的增加与减少
  $('.fm-add').click(function () {
    var num = $(this).prev('span').html()
    num++
    $(this).prev('span').html(num)
    console.log(num)
  })
  $('.fm-des').click(function () {
    var num = $(this).next('span').html()
    num--
    if (num <= 0) {
      num = 0
    }
    $(this).next('span').html(num)
    console.log(num)
  })
  // 会议洽谈桌类型的选择
  $('#meetingType ').find('label').click(function () {
    $(this).siblings('label').children('span.dec-checkbox').removeClass('dec-current')
    $(this).children('span.dec-checkbox').addClass('dec-current')
  })
  // 其他设备的选择
  $('#checkOther').find('label').click(function () {
    if ($(this).children('input').prop('checked')) {
      $(this).children('span.dec-checkbox').addClass('dec-current')
    } else {
      $(this).children('span.dec-checkbox').removeClass('dec-current')
    }
  })
  // 展览目标的选择
  $('#dsTarget').find('label').click(function () {
    if ($(this).children('input').prop('checked')) {
      $(this).children('span.dec-checkbox').addClass('dec-current')
    } else {
      $(this).children('span.dec-checkbox').removeClass('dec-current')
    }
  })
  // 展览目标的选择
  $('#dsAudience').find('label').click(function () {
    if ($(this).children('input').prop('checked')) {
      $(this).children('span.dec-checkbox').addClass('dec-current')
    } else {
      $(this).children('span.dec-checkbox').removeClass('dec-current')
    }
  })
  // 第二步 end
  // 设计师类型选择
  $('#desCheck').find('label').click(function () {
    $(this).siblings('label').children('span.dec-checkbox').removeClass('dec-current')
    $(this).children('span.dec-checkbox').addClass('dec-current')
  })
  // 初稿期望交稿日期
  $('.date_picker').date_input()
  /* 上传展会平面图 */
  $('.tips-img').on('change', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 50,
      distimg: $(that).prev('img'),
      zipimgsrc:'../img/3dmaxsuccess.png'
    }
    console.log(options.zipimgsrc)
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).siblings('.delete').show()
    })
  })
  /* 上传相关logo AI/PNG/JPG/JPEG/PDF/ZIP/RAR */
  $('.ex-input').on('change', function (e) {
    let that = this
    let options = {
      target: that,
      maxSize: 200,
      distimg: $(that).prev('img'),
      format: ['.AI','.PNG', '.JPEG', '.JPG', '.JPEG','.ZIP','.RAR'],
      zipimgsrc:'../img/3dmaxsuccess.png'
    }
    utils.filetype(options, function (err, that) {
      if (err) {
        utils.bomb(err)
        return false
      }
      $(that).siblings('.delete').show()
    })
  })
  // 删除图片
  $('.delete').on('click', function () {
    $(this).siblings('img').attr('src', '')
    $(this).siblings('input').val('')
    $(this).hide()
  })
  $('.ds-prodes').on("keyup", function () {
    $('.current-num').find('span').text($('.ds-prodes').val().length) 
    if ($('.ds-prodes').val().length > 200) {
      $('.current-num').find('span').text(200)
      $('.ds-prodes').val($('.ds-prodes').val().substring(0, 200))
    }
  })
})