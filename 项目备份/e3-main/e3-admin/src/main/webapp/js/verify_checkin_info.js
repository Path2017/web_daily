'use strict'
/* 上传图片 上传工作室营业执照,身份证 */
$('.front-id, .backend-id, .educationimg, .jobnameimg, .awardimg').on('change', function (e) {
  let that = this
  let options = {
    target: that,
    maxSize: 50,
    distimg: $(that).prev('img')
  }
  utils.filetype(options, function (err, that) {
    if (err) {
      utils.bomb(err)
      return false
    }
    $(that).siblings('.delete').show()
    // utils.createImgli('.educationimg', 1)
  })
})
/* 上传作品 */
$('.selectcase-ul').on('change', '.worksimg-li-must input', function (e) {
  let that = this
  let options = {
    target: that,
    maxSize: 50,
    distimg: $(that).prev('img')
  }
  utils.filetype(options, function (err, that) {
    if (err) {
      utils.bomb(err)
      return false
    }
    $(that).siblings('.delete').show()
    var val1 = $('.selectcase-ul .worksimg-li-must:eq(0)').find('input').val()
    var val2 = $('.selectcase-ul .worksimg-li-must:eq(1)').find('input').val()
    var src = $(that).siblings('img').attr('src')
    if (val1 && val2 && src === '') {
      utils.createImgli('.selectcase-ul', 10)
    }
  })
})
  
// 删除图片
$('.upload-ul').on('click', '.delete', function () {
  $(this).siblings('img').attr('src', '')
  $(this).siblings('input').val('')
  $(this).hide()
})

//点击审批通过的验证
$('#pass-btn').on('click',function () {
  let len = $('.selectcase-ul li').length
  let num = 0
  let frontId = $('.front-id').val()
  let backendId = $('.backend-id').val()
  for (var i = 0; i < len; i++) {
    var inputVal = $('.selectcase-ul li').eq(i).find('input').val()
    if (inputVal !== '') {
      num++
    }
  }
  if (frontId === '' || backendId === '') {
    utils.bomb('请上传身份证的正反面照片')
  }else if (num < 2) {
    utils.bomb('请上传2-10个代表作品案例')
  }else{
    $('#verify-form').submit()
  }
})

  // 城市选择
  $('#city').citySelect({
    // prov: "上海",
    // city: "嘉定区"
  });

 /* 审核通过之后 或 没通过 */

  // 用户审核-获取设计师或者展装公司详情详情
  // let userAuditId = utils.getUrlParam ('id')
  // console.log(userAuditId)
  // $.ajax({
  //   type: 'GET',
  //   url: baseurl,
  //   async: false,
  //   dataType: 'json',
  //   // data: {
  //   //   userAuditId : userAuditId
  //   // },
  //   success: function (data) {
  //     console.log(data)
  //     let list = data.userAuditPagedData
  //     let len = list.length
  //     let detail = {}
  //     for (let i=0;i<len;i++){
  //       console.log(list[i].id)
  //       console.log(userAuditId)
  //       if(list[i].id==userAuditId){
  //         detail = list[i].userAuditDetailView
  //         console.log(detail)
  //         let status = detail.status
  //         if(status===0){ //若审核通过或者未通过
  //          $('.no-audit-name').html(detail.name)
  //          $('.no-audit-mobile').html(detail.mobile)
  //          $('.no-audit-contact').html(detail.qq)
  //          $('.no-audit-workyear').html(detail.qq)
  //          $('.no-audit-email').html(detail.email)
  //         }
  //       }
  //     }
      
  //   }
  // })
