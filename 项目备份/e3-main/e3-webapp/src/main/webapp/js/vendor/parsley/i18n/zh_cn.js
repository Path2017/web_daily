// Validation errors messages for Parsley
// Load this after Parsley

Parsley.addMessages('zh-cn', {
  defaultMessage: "不正确的值",
  type: {
    email:        "<i class='erricon'></i>请输入一个有效的电子邮箱地址",
    url:          "<i class='erricon'></i>请输入一个有效的链接",
    number:       "<i class='erricon'></i>请输入正确的数字",
    integer:      "<i class='erricon'></i>请输入正确的整数",
    digits:       "<i class='erricon'></i>请输入正确的号码",
    alphanum:     "<i class='erricon'></i>请输入字母或数字"
  },
  notblank:       "请输入值",
  required:       "<i class='erricon'></i>必填项",
  pattern:        "<i class='erricon'></i>格式不正确",
  min:            "<i class='erricon'></i>输入值请大于或等于 %s",
  max:            "<i class='erricon'></i>输入值请小于或等于 %s",
  range:          "<i class='erricon'></i>输入值应该在 %s 到 %s 之间",
  minlength:      "<i class='erricon'></i>请输入至少 %s 个字符",
  maxlength:      "<i class='erricon'></i>请输入至多 %s 个字符",
  length:         "<i class='erricon'></i>字符长度应该在 %s 到 %s 之间",
  mincheck:       "<i class='erricon'></i>请至少选择 %s 个选项",
  maxcheck:       "<i class='erricon'></i>请选择不超过 %s 个选项",
  check:          "<i class='erricon'></i>请选择 %s 到 %s 个选项",
  equalto:        "<i class='erricon'></i>输入值不同"
});

Parsley.setLocale('zh-cn');
