'use strict'
var lang = sessionStorage.getItem("Lang");
if (lang == null) {
  var lan = window.navigator.userLanguage || window.navigator.language;
  lan = lan.toLowerCase();
  var language;
  if (lan == "zh-cn") {
    language = "zh_CN";
  } else if (lan == "zh-tw") {
    language = "zh_TW";
  } else {
    language = "en_US";
  }
  sessionStorage.setItem("Lang", language);
}

//多语言通用方法
function GetI18N(key) {
  if (i18n && i18n[key]) {
    return i18n[key];
  } else {
    return "$";
  }
}

//国际化
function i18nSetValue() {

  var elem = document.querySelectorAll("[i18nId]");
  var len = elem.length;
  for (var i = 0; i < len; i++) {
    var el = elem[i];
    var key = el.getAttribute("i18nId");

    if (el.getAttribute("type") == "button") {
      el.innerHTML = GetI18N(key);
    } else {
      var p = el.getAttribute("placeholder");

      if (p != null) {
        el.setAttribute("placeholder", GetI18N(key));
      } else {
        el.innerHTML = GetI18N(key);
      }
    }
  }

  var elem2 = document.querySelectorAll("[title]");
  var len2 = elem2.length;
  for (var i = 0; i < len2; i++) {
    var el = elem2[i];
    var key = el.getAttribute("title");
    el.setAttribute("title", GetI18N(key));
  }
}

//加載多語言文件
document.write('<script type="text/javascript" src="./language/' + lang + '_lang.js"></script>');