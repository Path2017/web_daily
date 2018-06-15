module.exports = {
  css: {
    src: ['../css/mms.less'],
    dest: '../css'
  },
  img: {
    src: '../img/**/*.**',
    dest: '../img'
  },
  js: {
    src: ['../js/index.js'],
    dest: '../js'
  },
  clean: {
    src: ['../js/index.min.js', '../css/mms.min.css']
  },
  watch: {
    src: '../+(js|css)/**/*.!(min)**'
  },
  server: {
    src: ['../+(js|css)/**/*.min.**', 'WEB-INF/**/*.html']
  }
}
