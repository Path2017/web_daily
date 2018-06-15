var baseUrl = "http://192.168.20.156:8081";//本地服务器地址
//var baseUrl = "http://106.14.2.143:86/e3expo-mms";//开发服务器地址
// var baseUrl = "http://106.14.202.163:8081/e3expo-mms";//正式服务器地址
//function urlRequest() {
//  return {
//    // 后台请求接口
//    // "designHistory":baseUrl+"/design/history/5/2",//后台登入查询设计图历史的接口
//    "designHistory": baseUrl + "/design/history/",//后台登入查询设计图历史的接口
//    "applicationApprove": baseUrl + "/application/approve/",//同意审批申请
//    "applicationReject": baseUrl + "/application/reject/", //驳回审批申请
//    "applicationCreate": baseUrl + "/application/create" //创建下载申请
//  };
//}
var reqUrl = {
  // 后台请求接口
  // "designHistory":baseUrl+"/design/history/5/2",//后台登入查询设计图历史的接口
  "designHistory": baseUrl + "/design/history/",//后台登入查询设计图历史的接口
  "applicationApprove": baseUrl + "/application/approve/",//同意审批申请
  "applicationReject": baseUrl + "/application/reject/", //驳回审批申请
  "applicationCreate": baseUrl + "/application/create", //创建下载申请
  "appendSketch": baseUrl + "/design/appendSketch", //创建下载申请
  "resignUser": baseUrl + "/user/update/isResigned", //用户设为离(在)职
  "searchPhoneNumber": baseUrl + "/user/checkPhoneNumber/", //查询电话号码是否可用
  "updateimg": baseUrl + "/design/setSketch", //update-designer页面覆盖缩略图
  "uploadStatus": baseUrl + "/design/uploadStatus", //获取压缩包上传状态
  "setZipFile": baseUrl + "/design/setZipFile" //更新压缩包文件
};

/*分页跳转*/
$('.page-a').on('click',function () {
  var url = window.location.href
  var pageIpt = $('.page-ipt').val()
  if(pageIpt){
    var newUrl = changeURLArg(url,'pageIndex',pageIpt)
    window.location.href=newUrl
  }

})

function changeURLArg(url, arg, arg_val) {
  /// <param name="url">目标url </param>
  /// <param name="arg">需要替换的参数名称</param>
  ///<param name="arg_val">替换后的参数的值</param>
  /// <returns>参数替换后的url </returns>
  var pattern = arg + '=([^&]*)';
  var replaceText = arg + '=' + arg_val;
  if (url.match(pattern)) {
    var tmp = '/(' + arg + '=)([^&]*)/gi';
    tmp = url.replace(eval(tmp), replaceText);
    return tmp;
  } else {
    if (url.match('[\?]')) {
      return url + '&' + replaceText;
    } else {
      return url + '?' + replaceText;
    }
  }
  return url + '\n' + arg + '\n' + arg_val;
}
