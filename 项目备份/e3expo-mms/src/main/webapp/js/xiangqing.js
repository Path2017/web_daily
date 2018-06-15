var winH = $('#log-ul').height()
var totalpages// 总页数，防止超过总页数继续滚动
console.log('winH' + winH)
var i = 1
var localUrl = window.location.href
var designidfront = localUrl.split('designID=')[1]
var designid = designidfront.split('&')[0]
console.log(designid)
getlog(designid, 1)
function scrolling () {
  if (i < totalpages) {
    var pageH = $('#log-ul ul').height()
    var scrollT = $('#log-ul').scrollTop() // 滚动条top
    // var page = scrollT / pageH
    // console.log(page + 'page')
    var aa = (pageH - winH - scrollT) / winH
    // console.log(aa)
    if (aa < -0.1) { // 滚动到底
      console.log('滚动到底了')
      getlog(designid, i)
      i++
      console.log(i)
    } else { // 否则显示无数据
      return false
    }
  }
}

$('#log-ul').scroll(function (e) {
  scrolling()
})
function logstring (timeStr, cityName, operationName, userName) {
  var str = '<li>' + '<span class="xq-time">' + timeStr + '&nbsp</span>' + '<span class="xq-space">' + cityName + '&nbsp</span>' + '<span class="xq-name">' + userName + '&nbsp</span>' + '<span class="xq-thing">' + operationName + '了该图库' + '</span>' + '</li>'
  $('#log-ul ul').append(str)
}
function getlog (designid, pageIndex) {
  $.ajax({
    type: 'get',
    url: reqUrl.designHistory + designid + '/' + pageIndex,
    dataType: 'json', // 请求数据类型
    crossDomain: true,
    cache: true,
    beforeSend: function () {
      $('.loading').show()
    },
    success: function (data) {
      $('.loading').hide()
      // console.log(data)
      var len = data.data.length
      for (var i = 0; i < len; i++) {
        // console.log(data.data[i])
        var timeStr = data.data[i].timeStr
        var cityName = data.data[i].city.name
        var operationName = data.data[i].operation.fullName
        var userName = data.data[i].user.name
        totalpages = data.totalPages
        logstring(timeStr, cityName, operationName, userName)
      }
    },
    error: function (data) {
      console.log(data)
    }
  })
}

/* xiangqing -- start */
$('.notsure-btn').on('click', function () {
  $('.alertB').hide()
})
$('.shenqingdw-btn').on('click', function () {
  $('.alertB').show()
  $('#applydownload').show()
  $('#resetpassword,#zhuxiao').hide()
})
$('.zhuxiao-btn').on('click', function () {
  $('.alertB').show()
  $('#resetpassword,#applydownload').hide()
  $('#zhuxiao').show()
})
$('.xiugaimima-btn').on('click', function () {
  $('.alertB').show()
  $('#resetpassword').show()
  $('#zhuxiao,#applydownload').hide()
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

$('#confirmCreateApplication').on('click' ,function () {
  $.ajax({
    type: 'get',
    url: reqUrl.applicationCreate + '?designId=' + designid ,
    dataType: 'text', // 请求数据类型
    cache: true,
    success: function (data) {
      $("#downloadStatusDiv").html('<button type="button" class="btn btn-default" disabled >下载申请中</button>');
      $('.alertB').hide()
    },
    error: function (data) {
      console.log(data)
    }
  })
})
$('#left-nav-design').addClass('active')
/* xiangqing -- end */