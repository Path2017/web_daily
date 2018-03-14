## 项目构建
```ruby
Vue项目构建过程

创建项目
Vue init webpack drc_rebuild

项目引入jquery
cnpm install jquery –save-dev

在build文件夹下 webpack.base.conf.js文件中
const webpack = require("webpack")
module里面加入
plugins: [
    new webpack.optimize.CommonsChunkPlugin('common.js'),
    new webpack.ProvidePlugin({
      jQuery: "jquery",
      $: "jquery"
    })
  ]
src文件夹入口文件main.js中 引入jquery
import $ from 'jquery'

引入vue-i18n插件
cnpm install vue-i18n –save-dev
在main.js中添加配置
import VueI18n from 'vue-i18n'
Vue.use(VueI18n)

实例中引入
new Vue({
  el: '#app',
  router,
  i18n,
  components: {
    App
  },
  template: '<App/>'
})

npm查询jquery版本
npm view jquery versions
安装指定版本: (jquery)
npm install jquery@1.7.2

利用axios进行数据请求
先下载依赖  npm install axios –save-dev
// 引入axios
import axios from 'axios'
Vue.prototype.$http = axios

$.ajax({
  type: 'post',
  url: 'http://web.dev.rcitech.cn/web/due/v_dueSlidePicture/api/list',
  dataType: 'json',
  success: function (data) {
    console.log(data)
    var getlist = data.body.list;
    for (var i = 0; i < getlist.length; i++) {
      this.imglist.push(getlist[i]);
    }
  }.bind(this),
  error: function (data) {
    console.log('Request error');
  }
})

this.$http.post('http://web.dev.rcitech.cn/web/due/v_dueSlidePicture/api/list')
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```