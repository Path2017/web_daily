define(function() {
  var util = {
    getFormatDate: function(date,type){
      if(type === 1){
        return '2018-5-4'
      }
      if(type === 2){
        return '2018年5月4日'
      }
    }
  }
  return util
});