'use strict'

$(function () {
  $('.ours-wp .items>li').hover(function(){
    let index = $(this).find('span').text()
    $('.active').removeClass('active')
    $(this).find('span').addClass('active')
    $('#jqStepImg').attr('src', '../../img/ours_0'+ index +'.jpg')
  })
  
  $('#switchTab').children('span').click(function () {
    $(this).addClass('tab-active')
    $(this).siblings('span').removeClass('tab-active')
    let nowpage = $(this).index()
    if(nowpage === 0) {
      $('.find-design').show()
      $('.find-build').hide()
    }
    if(nowpage === 1) {
      $('.find-design').hide()
      $('.find-build').show()
    }
  })
})