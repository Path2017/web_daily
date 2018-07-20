'use strict'
$(function () {
  /**
   * 初始化列表
   */
  let option = {
    data: {
      'userType': 1, // 用户类型1，设计师。2，展装公司,必填
      'countryId': '',
      'provinceId': '',
      'cityId': '',
      'name': '',
      'mobile': '',
      'status': '',
      'startDateTime': '',
      'endDateTime': '',
      'pageIndex': 1 // 第几页数据开始
    },
    // url: config.useraudit.url.list,
    url: baseurl, //请求路径
    listwp: '#list',
    paginationwp: '.pagination-fa'
  }
  utils.sendajax(option, userauditList)
  /**
   * 初始化列表结束
   */
  // 点击检索
  $('#search-btn').click(() => {
    let startDateTime = utils.DateToUnix($('.start-time').val())
    let endDateTime = utils.DateToUnix($('.end-time').val())
    let ismobile = $('.ismobile').val()
    let name = ''
    let mobile = ''
    if (ismobile == 1) {
      name = $('.searchname').val()
    } else {
      mobile = $('.searchname').val()
    }
    let provinceId =$('.prov').val()
    let cityId = $('.city').val()
    let countryId = $('.country').val()
    option = {
      data: {
        'userType': 1,
        'countryId': countryId,
        'provinceId': provinceId,
        'cityId': cityId,
        'name': name,
        'mobile': mobile,
        'status': '',
        'startDateTime': startDateTime,
        'endDateTime': endDateTime,
        'pageIndex': 1
      },
      // url: config.useraudit.url.list,
      url: baseurl
    }
    utils.sendajax(option, userauditList)
  })
  // 分页查询
  $('.pagination-fa').on('click', '.pagination li', function () {
    var page = $(this).find('a').attr('data-page')
    option.data.pageIndex = page
    utils.sendajax(option, userauditList)
  })
})
/*!
 * 审核管理
 * 设计师入驻审核列表
 */
function userauditList(data) {
  let time = utils.UnixToDate(parseInt(data.createTime))
  let str = `<tr>
                <td>${data.cityName}</td>
                <td>${data.name}</td>
                <td>${data.mobile}</td>
                <td>${data.statusRemark}</td>
                <td>${data.auditRemark}</td>
                <td>${time}</td>
                <td>
                  <a class="btn btn-primary" href="verify_checkin_info.html?id=${data.id}">
                    <i class="fa fa-search"></i> 查看
                  </a>
                </td>
              </tr>`
  return str
}