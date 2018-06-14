// pages/share/share.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    shareImage: '',//生成图片的地址
    showSharePic: false,// 显示分享按钮
    qrCodePath: '',
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    let that = this
    wx.getImageInfo({
      src: '../../images/wechat.jpg',//服务器返回的带参数的小程序码地址
      success: function (res) {
        //res.path是网络图片的本地地址
        that.qrCodePath = res.path;
        console.log(that.qrCodePath)
      },
      fail: function (res) {
        //失败回调
      }
    });
    // 屏幕宽高
    let windowWidth = wx.getSystemInfoSync().windowWidth;
    let windowHeight = wx.getSystemInfoSync().windowHeight;
    //  绘制文字区域背景 - begin
    const ctx = wx.createCanvasContext('shareCanvas');
    //画完整屏
    ctx.fillStyle = "#f6f6f6";
    var calheight = 0.86 * windowHeight;
    ctx.fillRect(0, 0, windowWidth, calheight+20);
    // 画文字区域
    var caleft = (windowWidth - 350) / 2;
    ctx.fillStyle = "#fff";
    ctx.fillRect(caleft, 10, 350, calheight);
    //  绘制文字区域背景 - end
    ctx.setFontSize(16);
    ctx.fillStyle = "#858585";
    ctx.setTextAlign('justify');
    ctx.lineWidth = 1;
    var str = "用于测试微信小程序标题，标题内容需换行,将图片置入进去。引用函数context.drawImage()函数即可，但是要调整图片大小；";
    var titleHeight = 65; // 标题的高度
    var canvasWidth = 310;//计算canvas的宽度
    var initHeight = 35;//绘制字体距离canvas顶部初始的高度

    // 标题border-bottom 线距顶部距离
    titleHeight = this.drawText(ctx, str, initHeight, titleHeight, canvasWidth, 24);
    ctx.moveTo(25, titleHeight)
    ctx.lineTo(325, titleHeight)
    ctx.stroke() //绘制已定义的路径
    titleHeight = titleHeight + 40;

    // 内容部分
    var detstr = "absolute定位是布局中最常用到的定位，其生成的位置是相对于带有position属性的父（父...)级来定位；如果父级都没有position属性，则相对于document来定位；使用absolute定位后，定位元素是脱离文档流的，这时候父级会检测不到定位元素的宽高。inline元素使用absolute定位之后，可以对其设置宽高；元素是不可以同时使用绝对定位和浮动的。";
    titleHeight = this.drawText(ctx, detstr, titleHeight, titleHeight, canvasWidth, 24);
    var imgurl = '../../images/wechat.jpg';
    // 计算图片的偏移量
    var carimgleft = (windowWidth - 100) / 2;
    console.log(carimgleft)
    ctx.drawImage(imgurl, carimgleft, 400, 100, 100);
    ctx.draw();

    // 延时生成图片
    //绘制之后加一个延时去生成图片，如果直接生成可能没有绘制完成，导出图片会有问题。
    setTimeout(function () {
      let that = this
      wx.canvasToTempFilePath({
        x: 0,
        y: 0,
        width: windowWidth,
        height: windowHeight,
        destWidth: windowWidth,
        destHeight: windowHeight,
        canvasId: 'shareCanvas',
        success: function (res) {
          that.setData({
            shareImage: res.tempFilePath,
            showSharePic: true
          })
          wx.hideLoading();
          console.log(res.tempFilePath)
        }.bind(that)
      })
    }.bind(this), 2000)
  },
  /**
 * 绘制多行文本
 * @param str 文本内容
 * @param initHeight 文本绘制的初始高度
 * @param titleHeight 绘制文本的高度
 * @param canvasWidth 绘制文本区域画布宽度
 * @param lineHeight 文字行高
 */
  drawText: function (ctx, str, initHeight, titleHeight, canvasWidth, lineHeight) {
    var lineWidth = 0;
    var lastSubStrIndex = 0; //每次开始截取的字符串的索引
    for (let i = 0; i < str.length; i++) {
      lineWidth += ctx.measureText(str[i]).width;
      if (lineWidth > canvasWidth) {
        ctx.fillText(str.substring(lastSubStrIndex, i), 25, initHeight);//绘制截取部分
        initHeight += lineHeight;//20为字体的高度
        lineWidth = 0;
        lastSubStrIndex = i;
        titleHeight += lineHeight;
      }
      if (i == str.length - 1) {//绘制剩余部分
        ctx.fillText(str.substring(lastSubStrIndex, i + 1), 25, initHeight);
      }
    }
    // 标题border-bottom 线距顶部距离
    titleHeight = titleHeight + 10;
    return titleHeight
  },
  // 保存图片
  linkImg: function () {
    console.log(this.data.shareImage)
    wx.saveImageToPhotosAlbum({
      filePath: this.data.shareImage,
      success(result) {
        wx.showToast({
          title: '保存成功',
          icon: 'success',
          duration: 2000
        })
      }
    })
  }

})