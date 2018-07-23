'use strict'
const gulp = require('gulp')
const rename = require('gulp-rename')
const imagemin = require('gulp-imagemin')
const tinypng = require('gulp-tinypng-compress')
const del = require('del')

/*
 * 压缩图片
 */
gulp.task('minifyImg', () =>
  //gulp.src('**/{img,images}/*.**')
  gulp.src('temp5/img/*.**')
    .pipe(imagemin([
      imagemin.gifsicle({interlaced: true,quality:'100'}),
      imagemin.jpegtran({progressive: true,quality:'100'}),
      imagemin.optipng({optimizationLevel: 7,quality:'100'}),
      imagemin.svgo({plugins: [{removeViewBox: true}]})
    ], {verbose: true}))
    //.pipe(rename({suffix: '.min'}))
    .pipe(gulp.dest('temp5/img/'))
)

/*
 * tinypng压缩
 */
gulp.task('tinypng', () =>
//gulp.src('**/{img,images}/*.**')
gulp.src('temp5/img/*.**')
  .pipe(tinypng({
    key: 'L5Svo7BTVpJf3frlWqJ_j7t-atlGzEFf',
    sigFile: 'img/.tinypng-sigs',
    sameDest: true,
    log: true
  }))
  //.pipe(rename({suffix: '.min'}))
  .pipe(gulp.dest('temp5/img/'))
)

/*
 * 删除生成文件
 */
gulp.task('del', () =>
  del('**/**/*.min.**')
)

/*
 * 默认执行
 */
gulp.task('default', ['minifyImg'])
