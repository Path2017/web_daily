'use strict'

$(function () {
  $('.ours-wp .items>li').hover(function(){
    let index = $(this).find('span').text()
    $('.active').removeClass('active')
    $(this).find('span').addClass('active')
    $('#jqStepImg').attr('src', '../../img/ours_0'+ index +'.jpg')
  })
})