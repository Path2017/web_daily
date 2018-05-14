(function () {
  // r,R,h2取值表
  var listNum = [
    {r: 0.4,R: 1,h2: 0.5},
    {r: 0.5,R: 1.2,h2: 0.6},
    {r: 0.6,R: 1.5,h2: 0.7},
    {r: 0.7,R: 1.75,h2: 0.8},
    {r: 0.8,R: 1.95,h2: 0.9},
    {r: 0.9,R: 2.2,h2: 1.0},
  ];
  // L的取值表
  var numlist = [30, 35, 40, 45, 50, 55, 60];

  /* 计算 得到h1  调用outputH1
    @param: R
    @param: r
    @param: h 对应的是h2
  */
  function outputH1(R, r, h) {
    var V = (Math.PI / 3) * (Math.pow(R, 2) + R * r + Math.pow(r, 2)) * h;
    var h1 = V / (Math.PI * Math.pow(r, 2));
    return h1;
  }
  // 遍历 L的值 分别计算C
  numlist.forEach(function (item, index) {
    var numlist = item;
    listNum.forEach(function (item, index) {
      // 得到每一个h1
      var h1 = outputH1(item.R, item.r, item.h2).toFixed(4);
      // 得到每一个C
      var C = (942 / (h1 + numlist)).toFixed(0);
      var outputlist = '<div>当r=' + item.r + ',R=' + item.R + ',h2=' + item.h2 + '以及L=' + numlist + '时,C = ' + C + '</div>';
      document.write(outputlist);
    })
  })
})()