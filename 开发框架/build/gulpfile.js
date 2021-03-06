'use strict'
const fs = require('fs')
const path = require('path')
const gulp = require('gulp')
const sequence = require('gulp-sequence')
const uglify = require('gulp-uglify')
const rename = require('gulp-rename')
const imagemin = require('gulp-imagemin')
const babel = require('gulp-babel')
const postcss = require('gulp-postcss')
// const stylelint = require('stylelint')
const cssnext = require('postcss-cssnext')
const precss = require('precss')
const cssnano = require('cssnano')
const bs = require('browser-sync').create()
const del = require('del')
const same = require('gulp-concat-same')
const htmlReplace = require('gulp-html-replace')
const config = require('./config')

/*
 * 编译js
 */

gulp.task('babel', () =>
gulp.src(config.js.src)
  .pipe(babel({
    presets: ['env']
  }))
  .pipe(rename({suffix: '.min'}))
  .pipe(gulp.dest(config.js.dest))
)

/*
 * 压缩混淆js
 */
gulp.task('minifyJs', () =>
  gulp.src(config.js.dest + '/*.min.js')
    .pipe(uglify({
      mangle: {
        toplevel: true  // 混淆
      }
    }))
    .pipe(gulp.dest(config.js.dest))
)

/*
 * 使用postcss编译css
 */
gulp.task('postcss', () =>
  gulp.src(config.css.src)
    .pipe(same({
      suffix: ''
    }))
    .pipe(postcss([
      precss(),
      cssnext({
        warnForDuplicates: false
      })
    ]))
    .pipe(rename({extname: '.min.css'}))
    .pipe(gulp.dest(config.css.dest))
)

/*
 * 压缩css
 */
gulp.task('minifyCss', () =>
  gulp.src(config.css.dest + '/*.min.css')
    .pipe(postcss([
      cssnano({ autoprefixer: false })
    ]))
    .pipe(gulp.dest(config.css.dest))
)

/*
 * 压缩图片
 */
gulp.task('minifyImg', () =>
  gulp.src(config.img.src)
    .pipe(imagemin([
      imagemin.gifsicle({interlaced: true}),
      imagemin.jpegtran({progressive: true}),
      imagemin.optipng({optimizationLevel: 5}),
      imagemin.svgo({plugins: [{removeViewBox: true}]})
    ], {verbose: true}))
    // .pipe(rename({suffix: '.min'}))
    .pipe(gulp.dest(config.img.dest))
)
/*
 * 生成HTML
 */
gulp.task('html', () => {
  let htmlReplaceTask = {}
  fs.readdirSync('../WEB-INF/view/part').forEach(file => {
    let attr = path.parse(file).name
    htmlReplaceTask[attr] = {
      src: fs.readFileSync('../WEB-INF/view/part/' + file),
      tpl: '%s'
    }
  })
  return gulp.src(config.html.src)
    .pipe(htmlReplace(htmlReplaceTask, {
      keepUnassigned: true,
      keepBlockTags: true
    }))
    .pipe(gulp.dest(config.html.dest))
})

/*
 * 删除生成的.min文件
 */
gulp.task('del', () =>
  del(config.clean.src, {
    force: true
  })
)

/*
 * 删除HTML生成内容
 */
gulp.task('delHtml', () => {
  let htmlReplaceTask = {}
  fs.readdirSync('../WEB-INF/view/part').forEach(file => {
    let attr = path.parse(file).name
    htmlReplaceTask[attr] = {
      src: '',
      tpl: '%s'
    }
  })
  return gulp.src(config.html.src)
    .pipe(htmlReplace(htmlReplaceTask, {
      keepUnassigned: true,
      keepBlockTags: true
    }))
    .pipe(gulp.dest(config.html.dest))
})

/* 组合任务 */

/*
 * 编译js和css
 */
gulp.task('translate', ['babel', 'postcss'])

/*
 * 压缩js、css、img
 */
gulp.task('minify', () => {
  sequence(['translate'], ['minifyImg', 'minifyCss', 'minifyJs'])((err) => {
    if (err) console.log(err)
  })
})

/*
 * 删除所有生成内容
 */
gulp.task('clean', ['del', 'delHtml'])

/*
 * 默认所有都执行
 */
gulp.task('default', ['clean'], () =>
  gulp.start(['minify'])
)

/*
 * 监控
 */
gulp.task('watch', () =>
  gulp.watch(config.watch.src, (event) => {
    if (event.type !== 'changed') return
    sequence(['lint'], ['translate'])((err) => {
      if (err) console.log(err)
    })
  })
)

/*
 * 启动服务器
 */
gulp.task('server', ['translate'], () => {
  bs.init({
    server: {
      baseDir: ['../WEB-INF/view', '../'],
      directory: true,
      index: 'index.html'
    },
    logPrefix: 'Browser Sync',
    logFileChanges: true
  })
  gulp.watch(config.css.src, ['postcss'])
  gulp.watch(config.js.src, ['babel'])
  gulp.watch(config.html.watch, ['html'])
  bs.watch(config.server.src, () => bs.reload())
})
