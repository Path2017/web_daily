'use strict'

$(function () {
  // 第一步
  $('#firstStep').click(function () {
    $('#form').parsley()
      .whenValidate({
        group: 'step1'
      })
      .done(function () {
        let checkbox = $('.ischeck').is(':checked')
        if(!checkbox){
          utils.bomb('请已阅读并接受《易宜益用户服务协议》')
        }else{
          $('.first-infobox').hide()
          $('.second-infobox').show()
          $('.regtopmenu').children('li').removeClass('menu-select')
          $('.regtopmenu').children('li').eq(1).addClass('menu-select')
        } 
      })
  })
  // 第二步
  $('#secondStep').click(function () {
    $('#form').parsley()
      .whenValidate({
        group: 'step2'
      })
      .done(function () {

      })
  })
  /* 倒计时 */
  $('.getcode').on('click', function () {
    // console.log()
    utils.settime($(this))
    console.log($(this))
  })
  let success = `<i class="success-icon2"></i>`
  $('#form').parsley()
  .on('field:success',function(){
    this.$element.siblings('.success-icon2').remove()
    this.$element.after($(success))
  })
  .on('field:error',function(el){
    el.$element.siblings('.success-icon2').remove()
  })
})
