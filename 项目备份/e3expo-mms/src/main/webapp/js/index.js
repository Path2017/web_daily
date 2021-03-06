'use strict';

// Custom jQuery
// -----------------------------------

(function (window, document, $) {
  /* tukuregister -- start */
  $(function () {
    // document ready
    $('#tukurg-btn-sure').click(function () {
      $('.alertB2').hide();
    });
    $('.notsure-btn').click(function () {
      $('.alertB').hide();
    });
    $('.zhuxiao-btn').on('click', function () {
      $('.alertB').show();
      $('#resetpassword').hide();
      $('#zhuxiao').show();
    });
    $('.xiugaimima-btn').on('click', function () {
      $('.alertB').show();
      $('#resetpassword').show();
      $('#zhuxiao').hide();
    });
    $('.sure-btn').click(function () {
      if ($('.resetpwd').val() === '') {
        $('.resetpwd-noneerr').show();
        return false;
      }
    });
    $('.resetpwd').focus(function () {
      $('.resetpwd-noneerr').hide();
    });

    function imgUpload(e, d) {
      var file = e.target.files[0]; // 获取file对象单张
      var reader = new FileReader(); // 创建filereader对象
      reader.readAsDataURL(file); // 转换数据
      reader.onload = function (e) {
        // 加载ok时触发的事件
        d.attr('src', e.target.result); // 给图片地址,显示缩略图
        d.css('display', 'inline-block'); // 样式显示
        // console.log(e.target.result)
      };
    }
    // 上传格式判断
    function filetype(target, e, val) {
      var filepath = $(target).val();
      var extStart = filepath.lastIndexOf('.');
      var ext = filepath.substring(extStart, filepath.length).toUpperCase();
      lessFlag = true;
      var fileSize = 0;
      if (isIE && !target.files) {
        // IE浏览器
        var filePath = target.value; // 获得上传文件的绝对路径
        var fileSystem = new ActiveXObject('Scripting.FileSystemObject');
        var file = fileSystem.GetFile(filePath);
        fileSize = file.Size; // 文件大小，单位：b
      } else {
        // 非IE浏览器
        fileSize = target.files[0].size;
      }
      var size = fileSize / 1024 / 1024;
      console.log(ext);
      if (ext !== '.BMP' && ext !== '.PNG' && ext !== '.GIF' && ext !== '.JPG' && ext !== '.JPEG') {
        $('.alertB2').show();
        $('.alertB2 .alert-title').html('图片限于bmp,png,gif,jpeg,jpg格式');
        return false;
      } else if (size > val) {
        var alertCont = '附件不能大于' + val + 'M';
        $('.alertB2').show();
        $('.alertB2 .alert-title').html(alertCont);
        lessFlag = false;
        return lessFlag;
      } else {
        lessFlag = true;
        $(target).parents('.upload-fa').next('.upload-fa').show();
        $(target).siblings('.delete').show();
        $(target).siblings('.fa-plus').hide();
        $(target).parent().next('.fenmianbtn-wp').show();
        imgUpload(e, $(target).prev('img'));
      }
      return lessFlag;
    }
    function filetype2(target, val) {
      var filepath = $(target).val();
      var extStart = filepath.lastIndexOf('.');
      var ext = filepath.substring(extStart, filepath.length).toUpperCase();
      lessFlag = true;
      var fileSize = 0;
      if (isIE && !target.files) {
        // IE浏览器
        var filePath = target.value; // 获得上传文件的绝对路径
        var fileSystem = new ActiveXObject('Scripting.FileSystemObject');
        var file = fileSystem.GetFile(filePath);
        fileSize = file.Size; // 文件大小，单位：b
      } else {
        // 非IE浏览器
        fileSize = target.files[0].size;
      }
      var size = fileSize / 1024 / 1024;
      console.log(ext);
      if (ext !== '.RAR' && ext !== '.ZIP') {
        $('.alertB2').show();
        $('.alertB2 .alert-title').html('文件限于rar,zip格式');
        return false;
      } else if (size > val) {
        var alertCont = '附件不能大于' + val + 'M';
        $('.alertB2').show();
        $('.alertB2 .alert-title').html(alertCont);
        lessFlag = false;
        return lessFlag;
      }
      return lessFlag;
    }

    // 判断是否为IE浏览器： /msie/i.test(navigator.userAgent) 为一个简单正则
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    var lessFlag = true;
    // 上传图片
    $('.upload-fa label input').change(function (e) {
      filetype(this, e, 10);
    });
    // 删除图片
    $('.delete').click(function () {
      // $(this).parents('.upload-fa').hide()
      $(this).hide();
      $(this).siblings('.fa-plus').show();
      $(this).siblings('input').attr('value', '');
      $(this).next('img').attr('src', '');
      $(this).parent().next('.fenmianbtn-wp').hide();
      return false;
    });
    // 上传附件
    $('#fujian-upload').change(function () {
      filetype2(this, 200);
    });
    // 点击保存按钮
    $('#tukuregister-btn').click(function () {
      var imgfile = $('.upload-fa label img').attr('src');
      var file = $('#fujian-upload').val();
      if (imgfile === '') {
        $('.alertB2').show();
        $('.alertB2 .alert-title').html('请上传平面示意图');
        return false;
      } else if (file === '') {
        $('.alertB2').show();
        $('.alertB2 .alert-title').html('请上传包含3DS MAX、和贴图的打包文件');
        return false;
      } else {
        // $('#form').submit()
        var formData = new FormData($("#form")[0]);
        var url = $("#form").attr('action');
        console.log(url);
        console.log(formData);
        $.ajax({
          type: 'post',
          url: url,
          dataType: 'json', // 请求数据类型
          crossDomain: true,
          cache: true,
          data: formData,
          contentType: false,
          processData: false,
          success: function (data) {
            $('.upload-progress').css('width', 0);
            var uploadStatus = setInterval(function () {
              $.ajax({
                type: 'get',
                url: reqUrl.uploadStatus,
                dataType: 'json', // 请求数据类型
                crossDomain: true,
                cache: true,
                success: function success(data) {
                  console.log(data)
                  if (data.code === 500) {
                    $('.upload-progress').hide()
                    $('.download3d-wp').show()
                    clearInterval(uploadStatus)
                    window.location.href = "../list"
                    return false;
                  }
                  var totalBytes = data.data.totalBytes;
                  var bytesUploaded = data.data.bytesUploaded;
                  var progress = bytesUploaded / totalBytes;
                  var width = bytesUploaded / totalBytes * 102;
                  if (progress >= 0) {
                    $('.upload-progress').show()
                  } else if (progress >= 1) {
                    $('.upload-progress').hide()
                    $('.download3d-wp').show()
                  }
                  $('.upload-progress').css('width', width)
                },
                error: function error(data) {
                  console.log(data)
                }
              });
            }, 1000);
          },
          error: function (data) {
            console.log(data)
          }
        });
      }
    });
    // 点击设为封面按钮
    $('.upload-wp .btn-primary').click(function () {
      // var val1 = $(this).parent('.fenmianbtn-wp').prev('label').find('input').val()
      var imgsrc1 = $(this).parent('.fenmianbtn-wp').prev('label').find('img').attr('src');
      // var val2 = $('.upload-wp .upload-fa:first').find('input').val()
      var imgsrc2 = $('.upload-wp .upload-fa:first').find('img').attr('src');
      $('.upload-wp .upload-fa:first').find('img').attr('src', imgsrc1);
      $(this).parent('.fenmianbtn-wp').prev('label').find('img').attr('src', imgsrc2);
      // console.log(val1)
    });
    /* tukuregister -- end */
    $('#left-nav-design').addClass('active');
  });
})(window, document, window.jQuery);