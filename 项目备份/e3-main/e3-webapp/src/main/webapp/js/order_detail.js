'use strict'
$(function () {
  var step2 = true
  var step3 = true
  $('.designstep1-switch').click(function () {
    // 概念设计图的显示和隐藏
    var isClose = $(this).find('span').html()
    if( isClose === '收起'){
      // $('.designstep1').addClass('none')
      $(this).parent('p').siblings('.designdraft').addClass('none')
      $(this).find('span').html('展开')
      $(this).find('i').attr('class', 'switch-icon')
      $(this).parent('p').addClass('od-title-line')
    }else{
      $('.designstep1').removeClass('none')
      $(this).find('span').html('收起')
      $(this).find('i').attr('class', 'switch-icon2')
      $(this).parent('p').removeClass('od-title-line')
    }
  })
  $('.designstep2-switch').click(function () {
    // 深化设计稿的显示和隐藏
    if (step2) {
      // $('.designstep2').removeClass('none')
      $(this).parent('p').siblings('.designdraft').removeClass('none')
      $('.swithstep2').html('收起')
      $(this).find('i').attr('class', 'switch-icon2')
      step2 = !step2
    } else {
      $('.designstep2').addClass('none')
      $('.swithstep2').html('展开')
      $(this).find('i').attr('class', 'switch-icon')
      step2 = !step2
    }
  })
  $('.designstep3-switch').click(function () {
    // 深化设计稿的显示和隐藏
    if (step3) {
      $('.designstep3').removeClass('none')
      $('.swithstep3').html('收起')
      $(this).find('i').attr('class', 'switch-icon2')
      $(this).parent('p').removeClass('od-title-line')
      step3 = !step3
    } else {
      $('.designstep3').addClass('none')
      $('.swithstep3').html('展开')
      $(this).find('i').attr('class', 'switch-icon')
      $(this).parent('p').addClass('od-title-line')
      step3 = !step3
    }
  })
  var viewSwiper = new Swiper('.view .swiper-container', {
    // pagination : '#swiper-pagination',
    // paginationType : 'fraction',
    onSlideChangeStart: function () {
      updateNavPosition()
    }
  })

  $('.view .arrow-left,.preview .arrow-left').on('click', function (e) {
    e.preventDefault()
    console.log(viewSwiper.activeIndex)
    if (viewSwiper.activeIndex === 0) {
      viewSwiper.swipeTo(viewSwiper.slides.length - 1, 1000)
      return
    }
    viewSwiper.swipePrev()
  })
  $('.view .arrow-right,.preview .arrow-right').on('click', function (e) {
    e.preventDefault()
    console.log(viewSwiper.activeIndex)
    if (viewSwiper.activeIndex === viewSwiper.slides.length - 1) {
      viewSwiper.swipeTo(0, 1000)
      return
    }
    viewSwiper.swipeNext()
  })

  var previewSwiper = new Swiper('.preview .swiper-container', {
    visibilityFullFit: true,
    slidesPerView: 'auto',
    onlyExternal: true,
    onSlideClick: function () {
      viewSwiper.swipeTo(previewSwiper.clickedSlideIndex)
    }
  })

  function updateNavPosition () {
    $('.preview .active-nav').removeClass('active-nav')
    var activeNav = $('.preview .swiper-slide').eq(viewSwiper.activeIndex).addClass('active-nav')
    if (!activeNav.hasClass('swiper-slide-visible')) {
      if (activeNav.index() > previewSwiper.activeIndex) {
        var thumbsPerNav = Math.floor(previewSwiper.width / activeNav.width()) - 1
        previewSwiper.swipeTo(activeNav.index() - thumbsPerNav)
      } else {
        previewSwiper.swipeTo(activeNav.index())
      }
    }
  }
  // console.log(viewSwiper.slides.length)
})
