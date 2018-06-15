$('#left-nav-download').addClass('active')
$('.notsure-btn').click(function () {
  $('.alertB').hide()
})
$('.zhuxiao-btn').on('click', function () {
  $('.alertB').show()
  $('#resetpassword').hide()
  $('#zhuxiao').show()
})
$('.xiugaimima-btn').on('click', function () {
  $('.alertB').show()
  $('#resetpassword').show()
  $('#zhuxiao').hide()
})
$('.sure-btn').click(function () {
  if($('.resetpwd').val()===''){
    $('.resetpwd-noneerr').show()
    return false
  }
})
$('.resetpwd').focus(function () {
  $('.resetpwd-noneerr').hide()
})