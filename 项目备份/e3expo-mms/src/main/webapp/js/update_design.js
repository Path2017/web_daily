// $('.upload-fa input').change(function () {
//   var designId = $(this).attr('designId')
//   var sketchId = $(this).attr('sketchId')
//   var setSketchFile = $(this).val();
//   console.log('designId:'+designId+'sketchId:'+sketchId+'setSketchFile:'+setSketchFile)
//   $.ajax({
//     type: 'post',
//     url: reqUrl.updateimg,
//     dataType: 'json', // 请求数据类型
//     crossDomain: true,
//     cache: true,
//     success: function (data) {
//
//     },
//     error: function (data) {
//       console.log(data)
//     }
//   })
// })
// 判断压缩文件格式并且提交表单
function checkFileTypeAndSubmitForm (target, val, callback) {
  var filepath = $(target).val()
  var extStart = filepath.lastIndexOf('.')
  var ext = filepath.substring(extStart, filepath.length).toUpperCase()
  lessFlag = true
  var fileSize = 0
  if (isIE && !target.files) {    // IE浏览器
    var filePath = target.value // 获得上传文件的绝对路径
    var fileSystem = new ActiveXObject('Scripting.FileSystemObject')
    var file = fileSystem.GetFile(filePath)
    fileSize = file.Size    // 文件大小，单位：b
  } else {    // 非IE浏览器
    fileSize = target.files[0].size
  }
  var size = fileSize / 1024 / 1024
  console.log(ext)
  if (ext !== '.RAR' && ext !== '.ZIP') {
    $('.alertB2').show()
    $('.alertB2 .alert-title').html('文件限于rar,zip格式')
    return false
  } else if (size > val) {
    var alertCont = '附件不能大于' + val + 'M'
    $('.alertB2').show()
    $('.alertB2 .alert-title').html(alertCont)
    lessFlag = false
    return lessFlag
  } else {
    callback()
  }
  return lessFlag
}
// 更新附件
$('#update-design-fujian-upload').change(function () {
  console.log($(this).attr('designId'))
  var designId = $(this).attr('designId')
  var $this = this
  // var zipFile = $(this)[0].files
  checkFileTypeAndSubmitForm(this, 200,function () {
      var formData = new FormData($("#update-design-zipfile-form" )[0]);
    // var reader = new FileReader()// 创建filereader对象
    // reader.readAsDataURL($this.files[0])
    //
    //   reader.onload = function (e) { // 加载ok时触发的事件
        $.ajax({
            type: 'post',
            url: reqUrl.setZipFile,
            dataType: 'json', // 请求数据类型
            crossDomain: true,
            cache: true,
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $('.upload-progress').css('width',0)
                var uploadStatus = setInterval(function () {
                  $.ajax({
                    type: 'get',
                    url: reqUrl.uploadStatus,
                    dataType: 'json', // 请求数据类型
                    crossDomain: true,
                    cache: true,
                    success: function (data) {
                      console.log(data)
                      if(data.code===500){
                        $('.upload-progress').hide()
                        $('.download3d-wp').show()
                        clearInterval(uploadStatus)
                        return false
                      }
                      var totalBytes = data.data.totalBytes
                      var bytesUploaded = data.data.bytesUploaded
                      var progress = bytesUploaded/totalBytes
                      var width = bytesUploaded/totalBytes*102
                      if(progress >= 0){
                        $('.upload-progress').show()
                      }else if(progress >= 1){
                        $('.upload-progress').hide()
                        $('.download3d-wp').show()
                      }
                      $('.upload-progress').css('width',width)
                    },
                    error: function (data) {
                      console.log(data)
                    }
                  })
                },1000)
            },
            error: function (data) {
                console.log(data)
            }
        })
    // }
  })
  // filesize(this,200);
})
// 判断是否为IE浏览器： /msie/i.test(navigator.userAgent) 为一个简单正则
var isIE = /msie/i.test(navigator.userAgent) && !window.opera
var lessFlag = true

function imgUpload (e, d) {
  var file = e.target.files[0]// 获取file对象单张
  var reader = new FileReader()// 创建filereader对象
  reader.readAsDataURL(file)// 转换数据
  reader.onload = function (e) { // 加载ok时触发的事件
    d.attr('src', e.target.result)// 给图片地址,显示缩略图
    d.css('display', 'inline-block')// 样式显示
    // console.log(e.target.result)
  }
}

var xhrOnProgress=function(fun) {
  xhrOnProgress.onprogress = fun; //绑定监听
  //使用闭包实现监听绑
  return function() {
    //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
    var xhr = $.ajaxSettings.xhr();
    //判断监听函数是否为函数
    if (typeof xhrOnProgress.onprogress !== 'function')
      return xhr;
    //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
    if (xhrOnProgress.onprogress && xhr.upload) {
      xhr.upload.onprogress = xhrOnProgress.onprogress;
    }
    return xhr;
  }
}