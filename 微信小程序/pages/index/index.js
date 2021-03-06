//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    newslist:[
      {
        maintime: '今天',
        list: [
          {
            title: '说起小程序呢，不得不说，这是一个面向用户友好的“东西”，对于我们开发者来说',
            time: '2018/06/11 11:03',
            id: '001',
          },
          {
            title: '因为对于视图层的开发，确实也没有什么好讲的，如果说你还不会html的开发',
            time: '2018/06/11 10:52',
            id: '002',
          },
          {
            title: '那么小程序除了视图层，首先要说的就是api了，有人说应该先说数据绑定，当然，数据绑定也是小程序比较重要的一部分',
            time: '2018/06/11 10:35',
            id: '003',
          },
          {
            title: '但是我个人觉得，先从api开始学习，这样在学习api的过程中就能够补充数据绑定的知识',
            time: '2018/06/11 09:47',
            id: '004',
          },
          {
            title: '首先我们学习小程序api,那么就要知道什么是api',
            time: '2018/06/11 09:12',
            id: '005',
          }
        ]
      },
      {
        maintime: '昨天',
        list: [
          {
            title: '微信小程序面世以来受到的关注颇多，直到最近我才动手尝试进行了小程序的开发',
            time: '2018/06/10 19:12',
            id: '101',
          },
          {
            title: '一点不适应的就是要摆脱Web APP开发对DOM的操作',
            time: '2018/06/10 12:43',
            id: '102',
          },
          {
            title: '在这里我就把我是如何利用API开发微信小程序的过程写成教程',
            time: '2018/06/10 11:19',
            id: '103',
          },
          {
            title: '教大家快速上手体验一次微信小程序的开发',
            time: '2018/06/10 09:52',
            id: '104',
          },
          {
            title: '之前忘了把源码发上来，完成之后就已经放在Github上了',
            time: '2018/06/10 09:13',
            id: '105',
          },
        ]
      },
      {
        maintime: '前天',
        list: [
          {
            title: '如果能调用现成的API那是极好的，经过一番挑选',
            time: '2018/06/09 17:37',
            id: '201',
          },
          {
            title: '调用这个API获取数据，我们只要做2个页面就可以完全展示出来了',
            time: '2018/06/09 14:51',
            id: '202',
          },
          {
            title: '微信开发者工具的安装和使用在这里就不多作介绍了',
            time: '2018/06/09 11:45',
            id: '203',
          },
          {
            title: '然后我们来清理一下默认工程的目录结构，删除以下目录和文件',
            time: '2018/06/09 09:35',
            id: '204',
          },
          {
            title: '后面还会添加资源进去，但是整体结构还是这样不会改变的',
            time: '2018/06/09 08:14',
            id: '205',
          }
        ]
      }
    ]
  },
  // 跳转到新闻详情页
  linkNews: function (event) {
    // 新闻id
    var newid = event.target.dataset.id;
    wx.navigateTo({
      url: '../detail/detail?id=' + newid
      // url: '../share/share?id=' + newid
    })
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    
  }
})
