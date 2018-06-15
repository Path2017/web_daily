/* zhanghaoguanli -- start */
$('.resign-btn').on('click', function () { //设为离职
    $('.alertB').show();
    $('#zhuxiao, #resetpassword, #notResignAlert').hide();
    $('#resignAlert').show()
});
$('.not-resign-btn').on('click', function () { //设为在职
    $('.alertB').show();
    $('#zhuxiao, #resetpassword, #resignAlert').hide();
    $('#notResignAlert').show()
});
$('.notsure-btn').click(function () {
    $('.alertB').hide()
});
$('.zhuxiao-btn').on('click', function () {
    $('.alertB').show();
    $('#resignAlert, #notResignAlert, #resetpassword').hide();
    $('#zhuxiao').show()
});
$('.xiugaimima-btn').on('click', function () {
  $('.alertB').show();
  $('#resetpassword').show();
  $('#resignAlert, #notResignAlert,#zhuxiao').hide()
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

$('#confirmResignBtn').on('click', function () { // 设为离职
    var userId = $("#resignBtn").attr('userId');
    $.ajax({
        type: 'get',
        url: reqUrl.resignUser + '?userID=' + userId + '&userIsResigned=' + 1,
        dataType: 'text', // 请求数据类型
        cache: true,
        success: function (data) {
            console.log(data.msg);
            window.location.reload();
            //$('#isResignedSpan').html('<button userId="'+userId+'" id="notResignBtn" type="button" class="btn btn-danger not-resign-btn">设为在职</button>');
            //$('.alertB').hide()
        },
        error: function (data) {
            alert('失败')
        }
    })
})
$('#confirmNotResignBtn').on('click', function () { // 设为在职
    var userId = $("#notResignBtn").attr('userId');
    $.ajax({
        type: 'get',
        url: reqUrl.resignUser + '?userID=' + userId + '&userIsResigned=' + 0,
        dataType: 'text', // 请求数据类型
        cache: true,
        success: function (data) {
            console.log(data.msg)
            window.location.reload()
            //$('#isResignedSpan').html('<button userId="'+userId+'" id="resignBtn" type="button" class="btn btn-danger resign-btn">设为离职</button>');
            //$('.alertB').hide()
        },
        error: function (data) {
            alert('失败')
        }
    })
})
$('#left-nav-user').addClass('active')
/* zhanghaoguanli -- end */
