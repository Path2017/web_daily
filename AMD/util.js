define(function() {
  var util = {
    getFormatDate: function(date,type){
      if(type === 1){
        return 'date1'
      }
      if(type === 2){
        return 'date2'
      }
    }
  }
  return util
});