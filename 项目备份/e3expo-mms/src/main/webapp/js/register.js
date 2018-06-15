/* register -- start */
function isChinese (temp) {
  var re = /[^\u4e00-\u9fa5]/
  if (re.test(temp)) return false
  return true
}
function notChinesename (chinesename) {
  // var chinesename = $('.chinesename').val()
  if (!isChinese(chinesename)) { // 不是中文
    $('.nameerr').show()
    $('.chinesename').addClass('redborder')
    return false
  }
}

$('#register-btn').click(function () { //点击提交按钮
  var chinesename = $('.chinesename').val()
  var phoneNumber = $('#phonenumber').val()
  console.log(phoneNumber)
  notChinesename(chinesename)
  $.ajax({
    type: 'get',
    url: reqUrl.searchPhoneNumber + phoneNumber,
    dataType: 'json', // 请求数据类型
    crossDomain: true,
    cache: true,
    success: function (data) {
        // console.log(data.code)
        if (data.code == 200) {
            $('.phoneerr').hide()
            if(isChinese(chinesename)){
                $('#registerform').submit()
            }
        } else {
          $('.phoneerr').show()
        }
    },
    error: function (data) {
      console.log(data)
    }
  })

})
$('.chinesename').change(function () {
  var chinesename2 = $('.chinesename').val()
  if (isChinese(chinesename2)) { // 是中文
    console.log('是中文')
    $('.nameerr').hide()
    $('.chinesename').removeClass('redborder')
  }
})
// 查看密码
// $('.see-pwd').mousedown(function () {
//   $('#signupInputPassword1').attr('type', 'text')
// })
// $('.see-pwd').mouseout(function () {
//   $('#signupInputPassword1').attr('type', 'password')
// })
/* register -- end */
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
$('#left-nav-user').addClass('active')