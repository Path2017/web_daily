'use strict'
$(function(){
  let success = `<i class="success-icon"></i>`
  $('#login-form').parsley()
  .on('field:success',function(){
    this.$element.siblings('.success-icon').remove()
    this.$element.after($(success))
  })
  .on('field:error',function(el){
    el.$element.siblings('.success-icon').remove()
  })
})
