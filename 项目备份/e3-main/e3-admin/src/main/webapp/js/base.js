'use strict'
// 公共方法

class Utils {
  constructor() {
    this.countdown = 60
    this.popups()
  }
  /*!
   * 获取验证码倒计时
   * @param {object} $el 显示倒计时的按钮
   */
  settime($el) {
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
  popups() {
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
  bomb(content = '参数错误', title = '提醒', btnText = '知道了') {
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
   * val 图片大小限制
   * callback 成功回调
   * sizefailcallback 大小不满足回调
   * formatfailcallback 格式不满足回调
   * distimg 显示图片
   */
  filetype(options, callback) {
    var target = options.target
    var distimg = options.distimg
    var maxSize = options.maxSize ? 50 : options.maxSize
    let isIE = /msie/i.test(navigator.userAgent) && !window.opera
    let filepath = $(target).val()
    let extStart = filepath.lastIndexOf('.')
    let ext = filepath.substring(extStart, filepath.length).toUpperCase()
    let fileSize = 0
    // let url = ''
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
    let formatArr = ['.PNG', '.PDF', '.JPG', '.JPEG']
    let data = {}
    if (formatArr.indexOf(ext) === -1) {
      $(target).val('')
      data.msg = '格式不对'
      callback(data.msg)
      console.log($(target).val())
      distimg.attr('src', '')
      $(target).siblings('.delete').hide()
      return false
    } else if (size > maxSize) {
      $(target).val('')
      data.msg = '大小不对'
      callback(data.msg)
      $(target).siblings('.delete').hide()
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
  createImgli(wp, len) {
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
  cancelOrder() {
    $('#decancelbox').fadeIn()
  }
 
  /* 获取参数 */
  getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)")
    var r = window.location.search.substr(1).match(reg)
    if (r != null) return unescape(r[2])
    return null
  }
  /*
   * 分页方法
   * @param {count} 总条数
   * @param {page} 当前页
   * @param {limit=10} 每页条数
   */
  getPageArr(count, page, limit = 10) {
    const pages = Math.ceil(count / limit)
    let result = []
    if (pages <= 3 || page <= 1) {
      for(let i = 1;i <= pages && i<= 3;i++){
        result[i-1] = i
      }
    } else if (page >= pages - 1) {
      result = [pages-2, pages - 1, pages]
    }else {
      result = [page - 1, page, page + 1]
    }
    return result
  }
  /*
   * 生成分页HTML
   * @param {pageArr} 分页数组
   * @param {page} 当前页
   */
  createPageHtml(count, page, limit = 10) {
    let pageArr = this.getPageArr(count, page)
    let pageHtml = `<ul class="pagination pagination-sm">
        <li><a data-page="1" href="javascript:;">«</a></li>`
    for (let item in pageArr) {
      let active = pageArr[item] === page ? 'active' : ''
      pageHtml += `<li class="${active}"><a data-page="${pageArr[item]}" href="javascript:;">${pageArr[item]}</a></li>`
    }
    pageHtml += `<li><a data-page="${Math.ceil(count / limit)}" href="javascript:;">»</a></li>
      </ul>`
    return pageHtml
  }

  /**         
   * 时间戳转换日期               
   * @param <int> unixTime    待时间戳(毫秒)               
   * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)               
   * @param <int>  timeZone   时区               
   */

  UnixToDate(unixTime, isFull) {
    var time = new Date(unixTime)
    var ymdhis = ""
    ymdhis += time.getUTCFullYear() + "-"
    ymdhis += (time.getUTCMonth() + 1) + "-"
    ymdhis += time.getUTCDate()
    if (isFull === true) {
      ymdhis += " " + time.getUTCHours() + ":"
      ymdhis += time.getUTCMinutes() + ":"
      ymdhis += time.getUTCSeconds()
    }
    return ymdhis
  }
  /**       
   * 日期 转换为 Unix时间戳
   * @param <string> 2014-01-01 20:20:20 日期格式       
   * @return <int>    unix时间戳(毫秒)       
   */
  // DateToUnix (string) {
  //   var f = string.split(' ', 2);
  //   var d = (f[0] ? f[0] : '').split('-', 3);
  //   var t = (f[1] ? f[1] : '').split(':', 3);
  //   return (new Date(
  //     parseInt(d[0], 10) || null,
  //     (parseInt(d[1], 10) || 1) - 1,
  //     parseInt(d[2], 10) || null,
  //     parseInt(t[0], 10) || null,
  //     parseInt(t[1], 10) || null,
  //     parseInt(t[2], 10) || null
  //   )).getTime()
  // }
  DateToUnix(string) {
    return new Date(string).valueOf()
  }
  /**
   * 发送ajax请求
   * @param {*} option 
   * @param {*} listwp 列表父级
   * @param {*} paginationwp 分页父级
   */
  sendajax (option,callback) {
    let listwp = option.listwp
    let paginationwp = option.paginationwp
    // let that = this
    $.ajax({
      type: 'GET',
      url: option.url,
      async: false,
      dataType: 'json',
      data: option.data, // 参数
      success: function (data) {
        console.log(data)
        let count = data.total // 总条数
        let page = data.pageIndex
        let str = utils.createPageHtml(count, page) //生成分页
        $(paginationwp).html('').append(str)
        let list = data.data
        let len = list.length
        $(listwp).html('')
        for (let i=0;i<len;i++){
          let str = callback(list[i])
          $(listwp).append(str)
        }
      }
    })
  }
  /*
   * 获取用户列表
   * @param {userType} 用户类型 1，设计师 2，展装公司
   * @param {countryId} 国家
   * @param {provinceId} 省份
   * @param {cityId} 城市
   * @param {name} 名字
   * @param {mobile} 手机号
   * @param {status} 状态
   * @param {startTime} 开始时间
   * @param {endTime} 结束时间
   * @param {pageIndex} 第几页
   */
  getUserlist(reqUrl,userType,countryId,provinceId,cityId,name,mobile,status,startTime,endTime,pageIndex) {
    $.ajax({
      type: 'post',
      url: reqUrl,
      dataType: 'json',
      data: {
        'userType': userType,
        'countryId': countryId,
        'provinceId': provinceId,
        'cityId': cityId,
        'name': name,
        'mobile': mobile,
        'status': status,
        'startTime': startTime,
        'endTime': endTime,
        'pageIndex': pageIndex
      },
      success: function (data) {
        return data
      }
    })
  }
}


/* eslint-disable */
const utils = new Utils()
/* eslint-enable */

$(function () {
  $('#datetimepicker').datetimepicker({
    icons: {
      time: 'fa fa-clock-o',
      date: 'fa fa-calendar',
      up: 'fa fa-chevron-up',
      down: 'fa fa-chevron-down',
      previous: 'fa fa-chevron-left',
      next: 'fa fa-chevron-right',
      today: 'fa fa-crosshairs',
      clear: 'fa fa-trash'
    },
    format: 'YYYY-MM-DD'
  })
  $('#datetimepicker2').datetimepicker({
    icons: {
      time: 'fa fa-clock-o',
      date: 'fa fa-calendar',
      up: 'fa fa-chevron-up',
      down: 'fa fa-chevron-down',
      previous: 'fa fa-chevron-left',
      next: 'fa fa-chevron-right',
      today: 'fa fa-crosshairs',
      clear: 'fa fa-trash'
    },
    format: 'YYYY-MM-DD'
  })
})