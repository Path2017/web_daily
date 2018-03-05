// router
function Router() {
  this.routes = {};
  this.currentURL = '';
}

Router.prototype.route = function (path, callback) {
  this.routes[path] = callback || function () {};
}

Router.prototype.refresh = function () {
  this.currentURL = location.hash.slice(1) || '/en';
  this.routes[this.currentURL]();
}

Router.prototype.init = function () {
  window.addEventListener('load', this.refresh.bind(this), false);
  window.addEventListener('hashchange', this.refresh.bind(this), false);
}

window.Router = new Router();
Router.route('/en', function () {
  window.location.href = '../index_en.html'
})
Router.route('/cn', function () {
  window.location.href = '../index.html'
})
window.Router.init();