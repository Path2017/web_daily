'use strict'
// 公共方法

class Utils {
  constructor () {
    this.countdown = 60
    this.popups()
  }
  /*!
   * 获取验证码倒计时
   * @param {object} $el 显示倒计时的按钮
   */
  settime ($el) {
    let that = this
    if (that.countdown === 0) {
      $el.prop('disabled', false).removeClass('getcode-active').addClass('getcode-normal').empty().prepend('获取验证码')
      that.countdown = 60
    } else {
      $el.addClass('getcode-active').removeClass('getcode-normal').prop('disabled', true)
      let valValue = that.countdown + 's后重发'
      $el.empty().prepend(valValue)
      that.countdown--
      setTimeout(function () {
        that.settime($el)
      }, 1000)
    }
  }
  popups () {
    $('[data-toggle]').click(function () {
      let el = $(this).data('toggle')
      $(el).show()
      $(el).find('[data-close]').click(() => {
        $(el).hide()
      })
    })
  }
  /*!
   * 最简单弹框
   * @param {content} 内容
   * @param {title} 标题
   * @param {btnText} 按钮文字
   */
  bomb (content = '参数错误', title = '提醒', btnText = '知道了') {
    let $el = $(`<div class="bomb-wp">
                    <div class="bomb-ct">
                      <p class="title"><i></i>${title}</p>
                      <p class="cue">${content}</p>
                      <div class="knowbtn" data-close>${btnText}</div>
                    </div>
                  </div>`)
    $el.appendTo('body').show()
    $el.find('[data-close]').click(() => {
      $el.remove()
    })
  }
  /*!
   * 上传图片格式验证
   * target 点击的input标签
   * maxSize 图片大小限制
   * callback 成功回调
   * zipimgsrc 压缩图片路径
   * distimg 显示图片
   * formatArr 上传图片格式
   */
  filetype (options, callback) {
    var target = options.target
    var distimg = options.distimg
    var zipimgsrc = options.zipimgsrc
    var maxSize = options.maxSize ? options.maxSize : 50
    var formatArr = options.format ? options.format : ['.PNG', '.PDF', '.JPG', '.JPEG']
    // console.log(formatArr)
    let isIE = /msie/i.test(navigator.userAgent) && !window.opera
    let filepath = $(target).val()
    let extStart = filepath.lastIndexOf('.')
    let ext = filepath.substring(extStart, filepath.length).toUpperCase()
    let fileSize = 0
    if (isIE && target.files.length > 0) {
      // IE浏览器
      let filePath = target.value // 获得上传文件的绝对路径
      let fileSystem = new ActiveXObject('Scripting.FileSystemObject')
      let file = fileSystem.GetFile(filePath)
      fileSize = file.Size // 文件大小，单位：b
    } else if (!isIE && target.files.length > 0) {
      // 非IE浏览器
      fileSize = target.files[0].size
    } else {
      return false
    }
    let size = fileSize / 1024 / 1024
    console.log(ext)
    let data = {}
    if (formatArr.indexOf(ext) === -1) {
      $(target).val('')
      data.msg = '格式不对'
      callback(data.msg)
      console.log($(target).val())
      distimg.attr('src', '')
      $(target).siblings('.delete').hide()
      return false
    }else if (size > maxSize) {
      $(target).val('')
      data.msg = '大小不对'
      callback(data.msg)
      $(target).siblings('.delete').hide()
      return false
    } else if(ext==='.RAR' || ext==='.ZIP'){
      distimg.attr('src', zipimgsrc)
      callback('', target)
      return false
    } else {
      let file = target.files[0] // 获取file对象单张
      let reader = new FileReader() // 创建filereader对象
      reader.readAsDataURL(file) // 转换数据
      reader.onload = function (e) {
        // 加载ok时触发的事件
        // url = e.target.result
        distimg.attr('src', e.target.result) // 给图片地址,显示缩略图
        distimg.css('display', 'inline-block') // 样式显示
      }
      callback('', target)
    }
  }
  /*!
   * 创建上传图片框
   * wp 上传框容器
   * len 上窗框总数量
   */
  createImgli (wp, len) {
    var str = '<li class="inputbox worksimg-li-must">' +
              '<span class="delete">×</span>' +
              '<span class="input-add">+</span>' +
              '<img src="" alt="">' +
              '<input type="file">' +
              '</li>'
    var num = $(wp).find('img').length
    var imgArr = []
    for (var i = 0; i < num; i++) {
      var li = $(wp).find('li').eq(i)
      var img = li.find('img')
      if (img.attr('src') !== '') {
        imgArr.push(1)
      }
    }
    // console.log($(wp).find('img').attr('src'))
    console.log(num)
    if (num < len && imgArr.length === num - 1) {
      $(wp).append(str)
    } else {
      return false
    }
  }
  /*!
   * 取消订单
   * 发布需求后订单详情进度页
   * 取消订单
   */
  cancelOrder () {
    $('#decancelbox').fadeIn()
  }
}

/* eslint-disable */
const utils = new Utils()
/* eslint-enable */
