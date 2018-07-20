/*
Navicat MySQL Data Transfer

Source Server         : 106.14.2.143
Source Server Version : 50637
Source Host           : 106.14.2.143:3306
Source Database       : e3expo-designreconstruct

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-11-13 10:06:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dic_bank`
-- ----------------------------
DROP TABLE IF EXISTS `dic_bank`;
CREATE TABLE `dic_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(200) DEFAULT NULL,
  `bank_branch_name` varchar(200) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_bank
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_city`
-- ----------------------------
DROP TABLE IF EXISTS `dic_city`;
CREATE TABLE `dic_city` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_city
-- ----------------------------
INSERT INTO `dic_city` VALUES ('110001', '北京市', '110000', null);
INSERT INTO `dic_city` VALUES ('120100', '天津市', '120000', null);
INSERT INTO `dic_city` VALUES ('130100', '石家庄市', '130000', null);
INSERT INTO `dic_city` VALUES ('130200', '唐山市', '130000', null);
INSERT INTO `dic_city` VALUES ('130300', '秦皇岛市', '130000', null);
INSERT INTO `dic_city` VALUES ('130400', '邯郸市', '130000', null);
INSERT INTO `dic_city` VALUES ('130500', '邢台市', '130000', null);
INSERT INTO `dic_city` VALUES ('130600', '保定市', '130000', null);
INSERT INTO `dic_city` VALUES ('130700', '张家口市', '130000', null);
INSERT INTO `dic_city` VALUES ('130800', '承德市', '130000', null);
INSERT INTO `dic_city` VALUES ('130900', '沧州市', '130000', null);
INSERT INTO `dic_city` VALUES ('131000', '廊坊市', '130000', null);
INSERT INTO `dic_city` VALUES ('131100', '衡水市', '130000', null);
INSERT INTO `dic_city` VALUES ('140100', '太原市', '140000', null);
INSERT INTO `dic_city` VALUES ('140200', '大同市', '140000', null);
INSERT INTO `dic_city` VALUES ('140300', '阳泉市', '140000', null);
INSERT INTO `dic_city` VALUES ('140400', '长治市', '140000', null);
INSERT INTO `dic_city` VALUES ('140500', '晋城市', '140000', null);
INSERT INTO `dic_city` VALUES ('140600', '朔州市', '140000', null);
INSERT INTO `dic_city` VALUES ('140700', '晋中市', '140000', null);
INSERT INTO `dic_city` VALUES ('140800', '运城市', '140000', null);
INSERT INTO `dic_city` VALUES ('140900', '忻州市', '140000', null);
INSERT INTO `dic_city` VALUES ('141000', '临汾市', '140000', null);
INSERT INTO `dic_city` VALUES ('141100', '吕梁市', '140000', null);
INSERT INTO `dic_city` VALUES ('150100', '呼和浩特市', '150000', null);
INSERT INTO `dic_city` VALUES ('150200', '包头市', '150000', null);
INSERT INTO `dic_city` VALUES ('150300', '乌海市', '150000', null);
INSERT INTO `dic_city` VALUES ('150400', '赤峰市', '150000', null);
INSERT INTO `dic_city` VALUES ('150500', '通辽市', '150000', null);
INSERT INTO `dic_city` VALUES ('150600', '鄂尔多斯市', '150000', null);
INSERT INTO `dic_city` VALUES ('150700', '呼伦贝尔市', '150000', null);
INSERT INTO `dic_city` VALUES ('150800', '巴彦淖尔市', '150000', null);
INSERT INTO `dic_city` VALUES ('150900', '乌兰察布市', '150000', null);
INSERT INTO `dic_city` VALUES ('152200', '兴安盟', '150000', null);
INSERT INTO `dic_city` VALUES ('152500', '锡林郭勒盟', '150000', null);
INSERT INTO `dic_city` VALUES ('152900', '阿拉善盟', '150000', null);
INSERT INTO `dic_city` VALUES ('210100', '沈阳市', '210000', null);
INSERT INTO `dic_city` VALUES ('210200', '大连市', '210000', null);
INSERT INTO `dic_city` VALUES ('210300', '鞍山市', '210000', null);
INSERT INTO `dic_city` VALUES ('210400', '抚顺市', '210000', null);
INSERT INTO `dic_city` VALUES ('210500', '本溪市', '210000', null);
INSERT INTO `dic_city` VALUES ('210600', '丹东市', '210000', null);
INSERT INTO `dic_city` VALUES ('210700', '锦州市', '210000', null);
INSERT INTO `dic_city` VALUES ('210800', '营口市', '210000', null);
INSERT INTO `dic_city` VALUES ('210900', '阜新市', '210000', null);
INSERT INTO `dic_city` VALUES ('211000', '辽阳市', '210000', null);
INSERT INTO `dic_city` VALUES ('211100', '盘锦市', '210000', null);
INSERT INTO `dic_city` VALUES ('211200', '铁岭市', '210000', null);
INSERT INTO `dic_city` VALUES ('211300', '朝阳市', '210000', null);
INSERT INTO `dic_city` VALUES ('211400', '葫芦岛市', '210000', null);
INSERT INTO `dic_city` VALUES ('220100', '长春市', '220000', null);
INSERT INTO `dic_city` VALUES ('220200', '吉林市', '220000', null);
INSERT INTO `dic_city` VALUES ('220300', '四平市', '220000', null);
INSERT INTO `dic_city` VALUES ('220400', '辽源市', '220000', null);
INSERT INTO `dic_city` VALUES ('220500', '通化市', '220000', null);
INSERT INTO `dic_city` VALUES ('220600', '白山市', '220000', null);
INSERT INTO `dic_city` VALUES ('220700', '松原市', '220000', null);
INSERT INTO `dic_city` VALUES ('220800', '白城市', '220000', null);
INSERT INTO `dic_city` VALUES ('222400', '延边朝鲜族自治州', '220000', null);
INSERT INTO `dic_city` VALUES ('230100', '哈尔滨市', '230000', null);
INSERT INTO `dic_city` VALUES ('230200', '齐齐哈尔市', '230000', null);
INSERT INTO `dic_city` VALUES ('230300', '鸡西市', '230000', null);
INSERT INTO `dic_city` VALUES ('230400', '鹤岗市', '230000', null);
INSERT INTO `dic_city` VALUES ('230500', '双鸭山市', '230000', null);
INSERT INTO `dic_city` VALUES ('230600', '大庆市', '230000', null);
INSERT INTO `dic_city` VALUES ('230700', '伊春市', '230000', null);
INSERT INTO `dic_city` VALUES ('230800', '佳木斯市', '230000', null);
INSERT INTO `dic_city` VALUES ('230900', '七台河市', '230000', null);
INSERT INTO `dic_city` VALUES ('231000', '牡丹江市', '230000', null);
INSERT INTO `dic_city` VALUES ('231100', '黑河市', '230000', null);
INSERT INTO `dic_city` VALUES ('231200', '绥化市', '230000', null);
INSERT INTO `dic_city` VALUES ('232700', '大兴安岭地区', '230000', null);
INSERT INTO `dic_city` VALUES ('310100', '上海市', '310000', null);
INSERT INTO `dic_city` VALUES ('320100', '南京市', '320000', null);
INSERT INTO `dic_city` VALUES ('320200', '无锡市', '320000', null);
INSERT INTO `dic_city` VALUES ('320300', '徐州市', '320000', null);
INSERT INTO `dic_city` VALUES ('320400', '常州市', '320000', null);
INSERT INTO `dic_city` VALUES ('320500', '苏州市', '320000', null);
INSERT INTO `dic_city` VALUES ('320600', '南通市', '320000', null);
INSERT INTO `dic_city` VALUES ('320700', '连云港市', '320000', null);
INSERT INTO `dic_city` VALUES ('320800', '淮安市', '320000', null);
INSERT INTO `dic_city` VALUES ('320900', '盐城市', '320000', null);
INSERT INTO `dic_city` VALUES ('321000', '扬州市', '320000', null);
INSERT INTO `dic_city` VALUES ('321100', '镇江市', '320000', null);
INSERT INTO `dic_city` VALUES ('321200', '泰州市', '320000', null);
INSERT INTO `dic_city` VALUES ('321300', '宿迁市', '320000', null);
INSERT INTO `dic_city` VALUES ('330100', '杭州市', '330000', null);
INSERT INTO `dic_city` VALUES ('330200', '宁波市', '330000', null);
INSERT INTO `dic_city` VALUES ('330300', '温州市', '330000', null);
INSERT INTO `dic_city` VALUES ('330400', '嘉兴市', '330000', null);
INSERT INTO `dic_city` VALUES ('330500', '湖州市', '330000', null);
INSERT INTO `dic_city` VALUES ('330600', '绍兴市', '330000', null);
INSERT INTO `dic_city` VALUES ('330700', '金华市', '330000', null);
INSERT INTO `dic_city` VALUES ('330800', '衢州市', '330000', null);
INSERT INTO `dic_city` VALUES ('330900', '舟山市', '330000', null);
INSERT INTO `dic_city` VALUES ('331000', '台州市', '330000', null);
INSERT INTO `dic_city` VALUES ('331100', '丽水市', '330000', null);
INSERT INTO `dic_city` VALUES ('340100', '合肥市', '340000', null);
INSERT INTO `dic_city` VALUES ('340200', '芜湖市', '340000', null);
INSERT INTO `dic_city` VALUES ('340300', '蚌埠市', '340000', null);
INSERT INTO `dic_city` VALUES ('340400', '淮南市', '340000', null);
INSERT INTO `dic_city` VALUES ('340500', '马鞍山市', '340000', null);
INSERT INTO `dic_city` VALUES ('340600', '淮北市', '340000', null);
INSERT INTO `dic_city` VALUES ('340700', '铜陵市', '340000', null);
INSERT INTO `dic_city` VALUES ('340800', '安庆市', '340000', null);
INSERT INTO `dic_city` VALUES ('341000', '黄山市', '340000', null);
INSERT INTO `dic_city` VALUES ('341100', '滁州市', '340000', null);
INSERT INTO `dic_city` VALUES ('341200', '阜阳市', '340000', null);
INSERT INTO `dic_city` VALUES ('341300', '宿州市', '340000', null);
INSERT INTO `dic_city` VALUES ('341400', '巢湖市', '340000', null);
INSERT INTO `dic_city` VALUES ('341500', '六安市', '340000', null);
INSERT INTO `dic_city` VALUES ('341600', '亳州市', '340000', null);
INSERT INTO `dic_city` VALUES ('341700', '池州市', '340000', null);
INSERT INTO `dic_city` VALUES ('341800', '宣城市', '340000', null);
INSERT INTO `dic_city` VALUES ('350100', '福州市', '350000', null);
INSERT INTO `dic_city` VALUES ('350200', '厦门市', '350000', null);
INSERT INTO `dic_city` VALUES ('350300', '莆田市', '350000', null);
INSERT INTO `dic_city` VALUES ('350400', '三明市', '350000', null);
INSERT INTO `dic_city` VALUES ('350500', '泉州市', '350000', null);
INSERT INTO `dic_city` VALUES ('350600', '漳州市', '350000', null);
INSERT INTO `dic_city` VALUES ('350700', '南平市', '350000', null);
INSERT INTO `dic_city` VALUES ('350800', '龙岩市', '350000', null);
INSERT INTO `dic_city` VALUES ('350900', '宁德市', '350000', null);
INSERT INTO `dic_city` VALUES ('360100', '南昌市', '360000', null);
INSERT INTO `dic_city` VALUES ('360200', '景德镇市', '360000', null);
INSERT INTO `dic_city` VALUES ('360300', '萍乡市', '360000', null);
INSERT INTO `dic_city` VALUES ('360400', '九江市', '360000', null);
INSERT INTO `dic_city` VALUES ('360500', '新余市', '360000', null);
INSERT INTO `dic_city` VALUES ('360600', '鹰潭市', '360000', null);
INSERT INTO `dic_city` VALUES ('360700', '赣州市', '360000', null);
INSERT INTO `dic_city` VALUES ('360800', '吉安市', '360000', null);
INSERT INTO `dic_city` VALUES ('360900', '宜春市', '360000', null);
INSERT INTO `dic_city` VALUES ('361000', '抚州市', '360000', null);
INSERT INTO `dic_city` VALUES ('361100', '上饶市', '360000', null);
INSERT INTO `dic_city` VALUES ('370100', '济南市', '370000', null);
INSERT INTO `dic_city` VALUES ('370200', '青岛市', '370000', null);
INSERT INTO `dic_city` VALUES ('370300', '淄博市', '370000', null);
INSERT INTO `dic_city` VALUES ('370400', '枣庄市', '370000', null);
INSERT INTO `dic_city` VALUES ('370500', '东营市', '370000', null);
INSERT INTO `dic_city` VALUES ('370600', '烟台市', '370000', null);
INSERT INTO `dic_city` VALUES ('370700', '潍坊市', '370000', null);
INSERT INTO `dic_city` VALUES ('370800', '济宁市', '370000', null);
INSERT INTO `dic_city` VALUES ('370900', '泰安市', '370000', null);
INSERT INTO `dic_city` VALUES ('371000', '威海市', '370000', null);
INSERT INTO `dic_city` VALUES ('371100', '日照市', '370000', null);
INSERT INTO `dic_city` VALUES ('371200', '莱芜市', '370000', null);
INSERT INTO `dic_city` VALUES ('371300', '临沂市', '370000', null);
INSERT INTO `dic_city` VALUES ('371400', '德州市', '370000', null);
INSERT INTO `dic_city` VALUES ('371500', '聊城市', '370000', null);
INSERT INTO `dic_city` VALUES ('371600', '滨州市', '370000', null);
INSERT INTO `dic_city` VALUES ('371700', '菏泽市', '370000', null);
INSERT INTO `dic_city` VALUES ('410100', '郑州市', '410000', null);
INSERT INTO `dic_city` VALUES ('410200', '开封市', '410000', null);
INSERT INTO `dic_city` VALUES ('410300', '洛阳市', '410000', null);
INSERT INTO `dic_city` VALUES ('410400', '平顶山市', '410000', null);
INSERT INTO `dic_city` VALUES ('410500', '安阳市', '410000', null);
INSERT INTO `dic_city` VALUES ('410600', '鹤壁市', '410000', null);
INSERT INTO `dic_city` VALUES ('410700', '新乡市', '410000', null);
INSERT INTO `dic_city` VALUES ('410800', '焦作市', '410000', null);
INSERT INTO `dic_city` VALUES ('410900', '濮阳市', '410000', null);
INSERT INTO `dic_city` VALUES ('411000', '许昌市', '410000', null);
INSERT INTO `dic_city` VALUES ('411100', '漯河市', '410000', null);
INSERT INTO `dic_city` VALUES ('411200', '三门峡市', '410000', null);
INSERT INTO `dic_city` VALUES ('411300', '南阳市', '410000', null);
INSERT INTO `dic_city` VALUES ('411400', '商丘市', '410000', null);
INSERT INTO `dic_city` VALUES ('411500', '信阳市', '410000', null);
INSERT INTO `dic_city` VALUES ('411600', '周口市', '410000', null);
INSERT INTO `dic_city` VALUES ('411700', '驻马店市', '410000', null);
INSERT INTO `dic_city` VALUES ('420100', '武汉市', '420000', null);
INSERT INTO `dic_city` VALUES ('420200', '黄石市', '420000', null);
INSERT INTO `dic_city` VALUES ('420300', '十堰市', '420000', null);
INSERT INTO `dic_city` VALUES ('420500', '宜昌市', '420000', null);
INSERT INTO `dic_city` VALUES ('420600', '襄樊市', '420000', null);
INSERT INTO `dic_city` VALUES ('420700', '鄂州市', '420000', null);
INSERT INTO `dic_city` VALUES ('420800', '荆门市', '420000', null);
INSERT INTO `dic_city` VALUES ('420900', '孝感市', '420000', null);
INSERT INTO `dic_city` VALUES ('421000', '荆州市', '420000', null);
INSERT INTO `dic_city` VALUES ('421100', '黄冈市', '420000', null);
INSERT INTO `dic_city` VALUES ('421200', '咸宁市', '420000', null);
INSERT INTO `dic_city` VALUES ('421300', '随州市', '420000', null);
INSERT INTO `dic_city` VALUES ('422800', '恩施土家族苗族自治州', '420000', null);
INSERT INTO `dic_city` VALUES ('429000', '省直辖县级行政区划', '420000', null);
INSERT INTO `dic_city` VALUES ('430100', '长沙市', '430000', null);
INSERT INTO `dic_city` VALUES ('430200', '株洲市', '430000', null);
INSERT INTO `dic_city` VALUES ('430300', '湘潭市', '430000', null);
INSERT INTO `dic_city` VALUES ('430400', '衡阳市', '430000', null);
INSERT INTO `dic_city` VALUES ('430500', '邵阳市', '430000', null);
INSERT INTO `dic_city` VALUES ('430600', '岳阳市', '430000', null);
INSERT INTO `dic_city` VALUES ('430700', '常德市', '430000', null);
INSERT INTO `dic_city` VALUES ('430800', '张家界市', '430000', null);
INSERT INTO `dic_city` VALUES ('430900', '益阳市', '430000', null);
INSERT INTO `dic_city` VALUES ('431000', '郴州市', '430000', null);
INSERT INTO `dic_city` VALUES ('431100', '永州市', '430000', null);
INSERT INTO `dic_city` VALUES ('431200', '怀化市', '430000', null);
INSERT INTO `dic_city` VALUES ('431300', '娄底市', '430000', null);
INSERT INTO `dic_city` VALUES ('433100', '湘西土家族苗族自治州', '430000', null);
INSERT INTO `dic_city` VALUES ('440100', '广州市', '440000', null);
INSERT INTO `dic_city` VALUES ('440200', '韶关市', '440000', null);
INSERT INTO `dic_city` VALUES ('440300', '深圳市', '440000', null);
INSERT INTO `dic_city` VALUES ('440400', '珠海市', '440000', null);
INSERT INTO `dic_city` VALUES ('440500', '汕头市', '440000', null);
INSERT INTO `dic_city` VALUES ('440600', '佛山市', '440000', null);
INSERT INTO `dic_city` VALUES ('440700', '江门市', '440000', null);
INSERT INTO `dic_city` VALUES ('440800', '湛江市', '440000', null);
INSERT INTO `dic_city` VALUES ('440900', '茂名市', '440000', null);
INSERT INTO `dic_city` VALUES ('441200', '肇庆市', '440000', null);
INSERT INTO `dic_city` VALUES ('441300', '惠州市', '440000', null);
INSERT INTO `dic_city` VALUES ('441400', '梅州市', '440000', null);
INSERT INTO `dic_city` VALUES ('441500', '汕尾市', '440000', null);
INSERT INTO `dic_city` VALUES ('441600', '河源市', '440000', null);
INSERT INTO `dic_city` VALUES ('441700', '阳江市', '440000', null);
INSERT INTO `dic_city` VALUES ('441800', '清远市', '440000', null);
INSERT INTO `dic_city` VALUES ('441900', '东莞市', '440000', null);
INSERT INTO `dic_city` VALUES ('442000', '中山市', '440000', null);
INSERT INTO `dic_city` VALUES ('445100', '潮州市', '440000', null);
INSERT INTO `dic_city` VALUES ('445200', '揭阳市', '440000', null);
INSERT INTO `dic_city` VALUES ('445300', '云浮市', '440000', null);
INSERT INTO `dic_city` VALUES ('450100', '南宁市', '450000', null);
INSERT INTO `dic_city` VALUES ('450200', '柳州市', '450000', null);
INSERT INTO `dic_city` VALUES ('450300', '桂林市', '450000', null);
INSERT INTO `dic_city` VALUES ('450400', '梧州市', '450000', null);
INSERT INTO `dic_city` VALUES ('450500', '北海市', '450000', null);
INSERT INTO `dic_city` VALUES ('450600', '防城港市', '450000', null);
INSERT INTO `dic_city` VALUES ('450700', '钦州市', '450000', null);
INSERT INTO `dic_city` VALUES ('450800', '贵港市', '450000', null);
INSERT INTO `dic_city` VALUES ('450900', '玉林市', '450000', null);
INSERT INTO `dic_city` VALUES ('451000', '百色市', '450000', null);
INSERT INTO `dic_city` VALUES ('451100', '贺州市', '450000', null);
INSERT INTO `dic_city` VALUES ('451200', '河池市', '450000', null);
INSERT INTO `dic_city` VALUES ('451300', '来宾市', '450000', null);
INSERT INTO `dic_city` VALUES ('451400', '崇左市', '450000', null);
INSERT INTO `dic_city` VALUES ('460100', '海口市', '460000', null);
INSERT INTO `dic_city` VALUES ('460200', '三亚市', '460000', null);
INSERT INTO `dic_city` VALUES ('469000', '省直辖县级行政区划', '460000', null);
INSERT INTO `dic_city` VALUES ('500001', '重庆市', '500000', null);
INSERT INTO `dic_city` VALUES ('510100', '成都市', '510000', null);
INSERT INTO `dic_city` VALUES ('510300', '自贡市', '510000', null);
INSERT INTO `dic_city` VALUES ('510400', '攀枝花市', '510000', null);
INSERT INTO `dic_city` VALUES ('510500', '泸州市', '510000', null);
INSERT INTO `dic_city` VALUES ('510600', '德阳市', '510000', null);
INSERT INTO `dic_city` VALUES ('510700', '绵阳市', '510000', null);
INSERT INTO `dic_city` VALUES ('510800', '广元市', '510000', null);
INSERT INTO `dic_city` VALUES ('510900', '遂宁市', '510000', null);
INSERT INTO `dic_city` VALUES ('511000', '内江市', '510000', null);
INSERT INTO `dic_city` VALUES ('511100', '乐山市', '510000', null);
INSERT INTO `dic_city` VALUES ('511300', '南充市', '510000', null);
INSERT INTO `dic_city` VALUES ('511400', '眉山市', '510000', null);
INSERT INTO `dic_city` VALUES ('511500', '宜宾市', '510000', null);
INSERT INTO `dic_city` VALUES ('511600', '广安市', '510000', null);
INSERT INTO `dic_city` VALUES ('511700', '达州市', '510000', null);
INSERT INTO `dic_city` VALUES ('511800', '雅安市', '510000', null);
INSERT INTO `dic_city` VALUES ('511900', '巴中市', '510000', null);
INSERT INTO `dic_city` VALUES ('512000', '资阳市', '510000', null);
INSERT INTO `dic_city` VALUES ('513200', '阿坝藏族羌族自治州', '510000', null);
INSERT INTO `dic_city` VALUES ('513300', '甘孜藏族自治州', '510000', null);
INSERT INTO `dic_city` VALUES ('513400', '凉山彝族自治州', '510000', null);
INSERT INTO `dic_city` VALUES ('520100', '贵阳市', '520000', null);
INSERT INTO `dic_city` VALUES ('520200', '六盘水市', '520000', null);
INSERT INTO `dic_city` VALUES ('520300', '遵义市', '520000', null);
INSERT INTO `dic_city` VALUES ('520400', '安顺市', '520000', null);
INSERT INTO `dic_city` VALUES ('522200', '铜仁地区', '520000', null);
INSERT INTO `dic_city` VALUES ('522300', '黔西南布依族苗族自治州', '520000', null);
INSERT INTO `dic_city` VALUES ('522400', '毕节地区', '520000', null);
INSERT INTO `dic_city` VALUES ('522600', '黔东南苗族侗族自治州', '520000', null);
INSERT INTO `dic_city` VALUES ('522700', '黔南布依族苗族自治州', '520000', null);
INSERT INTO `dic_city` VALUES ('530100', '昆明市', '530000', null);
INSERT INTO `dic_city` VALUES ('530300', '曲靖市', '530000', null);
INSERT INTO `dic_city` VALUES ('530400', '玉溪市', '530000', null);
INSERT INTO `dic_city` VALUES ('530500', '保山市', '530000', null);
INSERT INTO `dic_city` VALUES ('530600', '昭通市', '530000', null);
INSERT INTO `dic_city` VALUES ('530700', '丽江市', '530000', null);
INSERT INTO `dic_city` VALUES ('530800', '普洱市', '530000', null);
INSERT INTO `dic_city` VALUES ('530900', '临沧市', '530000', null);
INSERT INTO `dic_city` VALUES ('532300', '楚雄彝族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('532500', '红河哈尼族彝族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('532600', '文山壮族苗族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('532800', '西双版纳傣族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('532900', '大理白族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('533100', '德宏傣族景颇族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('533300', '怒江傈僳族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('533400', '迪庆藏族自治州', '530000', null);
INSERT INTO `dic_city` VALUES ('540100', '拉萨市', '540000', null);
INSERT INTO `dic_city` VALUES ('542100', '昌都地区', '540000', null);
INSERT INTO `dic_city` VALUES ('542200', '山南地区', '540000', null);
INSERT INTO `dic_city` VALUES ('542300', '日喀则地区', '540000', null);
INSERT INTO `dic_city` VALUES ('542400', '那曲地区', '540000', null);
INSERT INTO `dic_city` VALUES ('542500', '阿里地区', '540000', null);
INSERT INTO `dic_city` VALUES ('542600', '林芝地区', '540000', null);
INSERT INTO `dic_city` VALUES ('610100', '西安市', '610000', null);
INSERT INTO `dic_city` VALUES ('610200', '铜川市', '610000', null);
INSERT INTO `dic_city` VALUES ('610300', '宝鸡市', '610000', null);
INSERT INTO `dic_city` VALUES ('610400', '咸阳市', '610000', null);
INSERT INTO `dic_city` VALUES ('610500', '渭南市', '610000', null);
INSERT INTO `dic_city` VALUES ('610600', '延安市', '610000', null);
INSERT INTO `dic_city` VALUES ('610700', '汉中市', '610000', null);
INSERT INTO `dic_city` VALUES ('610800', '榆林市', '610000', null);
INSERT INTO `dic_city` VALUES ('610900', '安康市', '610000', null);
INSERT INTO `dic_city` VALUES ('611000', '商洛市', '610000', null);
INSERT INTO `dic_city` VALUES ('620100', '兰州市', '620000', null);
INSERT INTO `dic_city` VALUES ('620200', '嘉峪关市', '620000', null);
INSERT INTO `dic_city` VALUES ('620300', '金昌市', '620000', null);
INSERT INTO `dic_city` VALUES ('620400', '白银市', '620000', null);
INSERT INTO `dic_city` VALUES ('620500', '天水市', '620000', null);
INSERT INTO `dic_city` VALUES ('620600', '武威市', '620000', null);
INSERT INTO `dic_city` VALUES ('620700', '张掖市', '620000', null);
INSERT INTO `dic_city` VALUES ('620800', '平凉市', '620000', null);
INSERT INTO `dic_city` VALUES ('620900', '酒泉市', '620000', null);
INSERT INTO `dic_city` VALUES ('621000', '庆阳市', '620000', null);
INSERT INTO `dic_city` VALUES ('621100', '定西市', '620000', null);
INSERT INTO `dic_city` VALUES ('621200', '陇南市', '620000', null);
INSERT INTO `dic_city` VALUES ('622900', '临夏回族自治州', '620000', null);
INSERT INTO `dic_city` VALUES ('623000', '甘南藏族自治州', '620000', null);
INSERT INTO `dic_city` VALUES ('630100', '西宁市', '630000', null);
INSERT INTO `dic_city` VALUES ('632100', '海东地区', '630000', null);
INSERT INTO `dic_city` VALUES ('632200', '海北藏族自治州', '630000', null);
INSERT INTO `dic_city` VALUES ('632300', '黄南藏族自治州', '630000', null);
INSERT INTO `dic_city` VALUES ('632500', '海南藏族自治州', '630000', null);
INSERT INTO `dic_city` VALUES ('632600', '果洛藏族自治州', '630000', null);
INSERT INTO `dic_city` VALUES ('632700', '玉树藏族自治州', '630000', null);
INSERT INTO `dic_city` VALUES ('632800', '海西蒙古族藏族自治州', '630000', null);
INSERT INTO `dic_city` VALUES ('640100', '银川市', '640000', null);
INSERT INTO `dic_city` VALUES ('640200', '石嘴山市', '640000', null);
INSERT INTO `dic_city` VALUES ('640300', '吴忠市', '640000', null);
INSERT INTO `dic_city` VALUES ('640400', '固原市', '640000', null);
INSERT INTO `dic_city` VALUES ('640500', '中卫市', '640000', null);
INSERT INTO `dic_city` VALUES ('650100', '乌鲁木齐市', '650000', null);
INSERT INTO `dic_city` VALUES ('650200', '克拉玛依市', '650000', null);
INSERT INTO `dic_city` VALUES ('652100', '吐鲁番地区', '650000', null);
INSERT INTO `dic_city` VALUES ('652200', '哈密地区', '650000', null);
INSERT INTO `dic_city` VALUES ('652300', '昌吉回族自治州', '650000', null);
INSERT INTO `dic_city` VALUES ('652700', '博尔塔拉蒙古自治州', '650000', null);
INSERT INTO `dic_city` VALUES ('652800', '巴音郭楞蒙古自治州', '650000', null);
INSERT INTO `dic_city` VALUES ('652900', '阿克苏地区', '650000', null);
INSERT INTO `dic_city` VALUES ('653000', '克孜勒苏柯尔克孜自治州', '650000', null);
INSERT INTO `dic_city` VALUES ('653100', '喀什地区', '650000', null);
INSERT INTO `dic_city` VALUES ('653200', '和田地区', '650000', null);
INSERT INTO `dic_city` VALUES ('654000', '伊犁哈萨克自治州', '650000', null);
INSERT INTO `dic_city` VALUES ('654200', '塔城地区', '650000', null);
INSERT INTO `dic_city` VALUES ('654300', '阿勒泰地区', '650000', null);
INSERT INTO `dic_city` VALUES ('659000', '自治区直辖县级行政区划', '650000', null);

-- ----------------------------
-- Table structure for `dic_country`
-- ----------------------------
DROP TABLE IF EXISTS `dic_country`;
CREATE TABLE `dic_country` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_country
-- ----------------------------
INSERT INTO `dic_country` VALUES ('32', '2323');

-- ----------------------------
-- Table structure for `dic_designer_config`
-- ----------------------------
DROP TABLE IF EXISTS `dic_designer_config`;
CREATE TABLE `dic_designer_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `total_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_designer_config
-- ----------------------------
INSERT INTO `dic_designer_config` VALUES ('1', null, 'junior_designer', '1', null);
INSERT INTO `dic_designer_config` VALUES ('2', null, 'medium_designer', '2', null);
INSERT INTO `dic_designer_config` VALUES ('3', null, 'senior_designer', '3', null);
INSERT INTO `dic_designer_config` VALUES ('4', null, 'designer_studio', '4', null);
INSERT INTO `dic_designer_config` VALUES ('5', null, 'self_support_designer', '5', null);

-- ----------------------------
-- Table structure for `dic_designer_file_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_designer_file_type`;
CREATE TABLE `dic_designer_file_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `designer_type` int(11) DEFAULT NULL,
  `lower` int(11) DEFAULT NULL COMMENT '取值大于等于0，当前图片上传数量下限。0表示可以不传图片。',
  `upper` int(11) DEFAULT '100' COMMENT '取值大于0，当前图片上传上限。目前设置默认为100。',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_designer_file_type
-- ----------------------------
INSERT INTO `dic_designer_file_type` VALUES ('1', 'designer_representative_work', '1', '1', '2', '10', '设计师_代表作品');
INSERT INTO `dic_designer_file_type` VALUES ('2', 'designer_education', '1', '1', '0', '3', '设计师_学历证明');
INSERT INTO `dic_designer_file_type` VALUES ('3', 'designer_title', '1', '1', '0', '3', '设计师_职称证名');
INSERT INTO `dic_designer_file_type` VALUES ('4', 'designer_award', '1', '1', '0', '5', '设计师_获奖证明');
INSERT INTO `dic_designer_file_type` VALUES ('5', 'studio_license', '1', '4', '1', '1', '工作室_营业执照');
INSERT INTO `dic_designer_file_type` VALUES ('6', 'studio_legal_id_card_front', '1', '4', '1', '1', '工作室_法人身份证正面');
INSERT INTO `dic_designer_file_type` VALUES ('7', 'studio_legal_id_card_back', '1', '4', '1', '1', '工作室_法人身份证反面');
INSERT INTO `dic_designer_file_type` VALUES ('8', 'studio_representative_case', '1', '4', '2', '10', '工作室_代表案例');
INSERT INTO `dic_designer_file_type` VALUES ('9', 'studio_qualification', '1', '4', '0', '3', '工作室_资质证明');
INSERT INTO `dic_designer_file_type` VALUES ('10', 'studio_staff_title', '1', '4', '0', '5', '工作室_员工职称证名');
INSERT INTO `dic_designer_file_type` VALUES ('11', 'studio_award', '1', '4', '0', '5', '工作室_获奖证明');
INSERT INTO `dic_designer_file_type` VALUES ('12', 'designer_id_card_front', '1', null, '1', '1', '设计师_身份证正面');
INSERT INTO `dic_designer_file_type` VALUES ('13', 'designer_id_card_back', '1', null, '1', '1', '设计师_身份证背面');

-- ----------------------------
-- Table structure for `dic_designer_working_years`
-- ----------------------------
DROP TABLE IF EXISTS `dic_designer_working_years`;
CREATE TABLE `dic_designer_working_years` (
  `splict_point` int(11) NOT NULL COMMENT '分割点',
  UNIQUE KEY `unique_split_point` (`splict_point`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_designer_working_years
-- ----------------------------
INSERT INTO `dic_designer_working_years` VALUES ('1');
INSERT INTO `dic_designer_working_years` VALUES ('3');
INSERT INTO `dic_designer_working_years` VALUES ('5');
INSERT INTO `dic_designer_working_years` VALUES ('10');
INSERT INTO `dic_designer_working_years` VALUES ('15');

-- ----------------------------
-- Table structure for `dic_express`
-- ----------------------------
DROP TABLE IF EXISTS `dic_express`;
CREATE TABLE `dic_express` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_express
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_order_file_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_order_file_type`;
CREATE TABLE `dic_order_file_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_order_file_type
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_order_node`
-- ----------------------------
DROP TABLE IF EXISTS `dic_order_node`;
CREATE TABLE `dic_order_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `next_node` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_order_node
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_payment_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_payment_type`;
CREATE TABLE `dic_payment_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_payment_type
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_province`
-- ----------------------------
DROP TABLE IF EXISTS `dic_province`;
CREATE TABLE `dic_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=650001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_province
-- ----------------------------
INSERT INTO `dic_province` VALUES ('110000', '北京市', null, null);
INSERT INTO `dic_province` VALUES ('120000', '天津市', null, null);
INSERT INTO `dic_province` VALUES ('130000', '河北省', null, null);
INSERT INTO `dic_province` VALUES ('140000', '山西省', null, null);
INSERT INTO `dic_province` VALUES ('150000', '内蒙古', null, null);
INSERT INTO `dic_province` VALUES ('210000', '辽宁省', null, null);
INSERT INTO `dic_province` VALUES ('220000', '吉林省', null, null);
INSERT INTO `dic_province` VALUES ('230000', '黑龙江省', null, null);
INSERT INTO `dic_province` VALUES ('310000', '上海市', null, null);
INSERT INTO `dic_province` VALUES ('320000', '江苏省', null, null);
INSERT INTO `dic_province` VALUES ('330000', '浙江省', null, null);
INSERT INTO `dic_province` VALUES ('340000', '安徽省', null, null);
INSERT INTO `dic_province` VALUES ('350000', '福建省', null, null);
INSERT INTO `dic_province` VALUES ('360000', '江西省', null, null);
INSERT INTO `dic_province` VALUES ('370000', '山东省', null, null);
INSERT INTO `dic_province` VALUES ('410000', '河南省', null, null);
INSERT INTO `dic_province` VALUES ('420000', '湖北省', null, null);
INSERT INTO `dic_province` VALUES ('430000', '湖南省', null, null);
INSERT INTO `dic_province` VALUES ('440000', '广东省', null, null);
INSERT INTO `dic_province` VALUES ('450000', '广西', null, null);
INSERT INTO `dic_province` VALUES ('460000', '海南省', null, null);
INSERT INTO `dic_province` VALUES ('500000', '重庆市', null, null);
INSERT INTO `dic_province` VALUES ('510000', '四川省', null, null);
INSERT INTO `dic_province` VALUES ('520000', '贵州省', null, null);
INSERT INTO `dic_province` VALUES ('530000', '云南省', null, null);
INSERT INTO `dic_province` VALUES ('540000', '西藏', null, null);
INSERT INTO `dic_province` VALUES ('610000', '陕西省', null, null);
INSERT INTO `dic_province` VALUES ('620000', '甘肃省', null, null);
INSERT INTO `dic_province` VALUES ('630000', '青海省', null, null);
INSERT INTO `dic_province` VALUES ('640000', '宁夏', null, null);
INSERT INTO `dic_province` VALUES ('650000', '新疆', null, null);

-- ----------------------------
-- Table structure for `dic_user_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_user_type`;
CREATE TABLE `dic_user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_name` varchar(50) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_user_type
-- ----------------------------
INSERT INTO `dic_user_type` VALUES ('1', 'exhibition_company', '1', null);
INSERT INTO `dic_user_type` VALUES ('2', 'designer', '1', null);

-- ----------------------------
-- Table structure for `te_appeal`
-- ----------------------------
DROP TABLE IF EXISTS `te_appeal`;
CREATE TABLE `te_appeal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `designer_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `appeal_type` int(11) DEFAULT NULL,
  `appeal_msg` varchar(200) DEFAULT NULL,
  `link_men` varchar(50) DEFAULT NULL,
  `link_phone` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `handler_id` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_appeal
-- ----------------------------

-- ----------------------------
-- Table structure for `te_appeal_log`
-- ----------------------------
DROP TABLE IF EXISTS `te_appeal_log`;
CREATE TABLE `te_appeal_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appeal_id` int(11) DEFAULT NULL,
  `handler_id` int(11) DEFAULT NULL,
  `handler_remark` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `change_designer` int(11) DEFAULT NULL,
  `result` varchar(200) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_appeal_log
-- ----------------------------

-- ----------------------------
-- Table structure for `te_company_config`
-- ----------------------------
DROP TABLE IF EXISTS `te_company_config`;
CREATE TABLE `te_company_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` int(11) DEFAULT NULL,
  `rebate` float DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_company_config
-- ----------------------------

-- ----------------------------
-- Table structure for `te_company_config_log`
-- ----------------------------
DROP TABLE IF EXISTS `te_company_config_log`;
CREATE TABLE `te_company_config_log` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_company_config_log
-- ----------------------------

-- ----------------------------
-- Table structure for `te_designer`
-- ----------------------------
DROP TABLE IF EXISTS `te_designer`;
CREATE TABLE `te_designer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `designer_type` int(11) DEFAULT NULL,
  `working_years` varchar(20) DEFAULT NULL COMMENT '工作年限，例如，0-1，1-3，10- 。',
  `company_create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_designer
-- ----------------------------
INSERT INTO `te_designer` VALUES ('1', '104', null, null, null);
INSERT INTO `te_designer` VALUES ('2', '105', '1', null, null);

-- ----------------------------
-- Table structure for `te_designer_image`
-- ----------------------------
DROP TABLE IF EXISTS `te_designer_image`;
CREATE TABLE `te_designer_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) DEFAULT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `file_type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_designer_image
-- ----------------------------
INSERT INTO `te_designer_image` VALUES ('237', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201711/n2gpicg2.l0n13102017111453.jpg', '1', '97', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('238', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201711/kwi3oo3u.5xg13102017111753.jpg', '1', '97', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('239', '设计师_学历证明', 'http://localhost:8021/Uploads/zh-cn/Frontend/201711/dootwsn1.2m213102017112253.jpg', '2', '97', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('240', '设计师_职称证名', 'http://localhost:8021/Uploads/zh-cn/Frontend/201711/0kyrayew.yej13102017112453.jpg', '3', '97', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('241', '设计师_获奖证明', 'http://localhost:8021/Uploads/zh-cn/Frontend/201711/tdsaahjp.4a313102017112653.jpg', '4', '97', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('242', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201709/3n0dgwrs.aeh13062017090521.jpg', '1', '98', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('243', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201709/le4v3wb3.znv13062017091021.jpg', '1', '98', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('244', '设计师_学历证明', 'http://localhost:8021/Uploads/zh-cn/Frontend/201709/kysuz2zi.rj213062017091021.png', '2', '98', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('245', '设计师_职称证名', 'http://localhost:8021/Uploads/zh-cn/Frontend/201709/utkbm5ho.kk513062017091121.png', '3', '98', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('246', '设计师_获奖证明', 'http://localhost:8021/Uploads/zh-cn/Frontend/201709/w0wwliuq.fdd13062017091221.png', '4', '98', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('247', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/igej0akd.bnz09252017081855.jpg', '1', '99', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('248', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/rjc0mxut.10q09252017082055.jpg', '1', '99', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('249', '设计师_学历证明', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/ggduko1w.fbj09252017082355.jpg', '2', '99', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('250', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/3hyaoyat.v4w10242017085610.png', '1', '100', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('251', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/4k1kecqh.crm10242017085910.png', '1', '100', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('252', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/1wj4dmf4.ia011232017080555.png', '1', '101', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('253', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/zdmma0xw.5ju11232017080755.png', '1', '101', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('254', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/kz0zypod.qrp11222017080048.jpg', '1', '102', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('255', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/ih34ndv2.ifo11222017080448.jpg', '1', '102', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('256', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/wo4k4bny.lqk16182017080618.png', '1', '103', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('257', '设计师_代表作品', 'http://localhost:8021/Uploads/zh-cn/Frontend/201708/tee5o4nf.11c16182017080818.png', '1', '103', '1503044304000', '1503044304000');
INSERT INTO `te_designer_image` VALUES ('258', '1 - 副本 (2).jpg', '20171110/26774cb3-3543-414b-aef7-a161d5499196_1 - 副本 (2).jpg', '1', '105', '1510302682318', '1510302682318');
INSERT INTO `te_designer_image` VALUES ('259', '3 - 副本.jpg', '20171110/662e29ef-74fb-44fa-9d60-1f881548e496_3 - 副本.jpg', '1', '105', '1510302682318', '1510302682318');
INSERT INTO `te_designer_image` VALUES ('260', '3.jpg', '20171110/2f4692a3-d884-42f4-8421-376210b86672_3.jpg', '2', '105', '1510302682318', '1510302682318');

-- ----------------------------
-- Table structure for `te_designer_need`
-- ----------------------------
DROP TABLE IF EXISTS `te_designer_need`;
CREATE TABLE `te_designer_need` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rfp_id` int(11) DEFAULT NULL,
  `designer_type` int(11) DEFAULT NULL,
  `first_design_num` int(11) DEFAULT NULL,
  `detailed_design_num` int(11) DEFAULT NULL,
  `work_file_num` int(11) DEFAULT NULL,
  `first_design_price` float DEFAULT NULL,
  `detailed_design_price` float DEFAULT NULL,
  `work_file_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_designer_need
-- ----------------------------

-- ----------------------------
-- Table structure for `te_designer_order`
-- ----------------------------
DROP TABLE IF EXISTS `te_designer_order`;
CREATE TABLE `te_designer_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `designer_id` int(11) DEFAULT NULL,
  `designer_name` varchar(100) DEFAULT NULL,
  `designer_type` varchar(50) DEFAULT NULL,
  `designer_level` varchar(50) DEFAULT NULL,
  `designer_phone` varchar(20) DEFAULT NULL,
  `design_qq` varchar(20) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `audit_status` int(11) DEFAULT NULL,
  `is_lock` int(11) DEFAULT NULL,
  `node_id` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_designer_order
-- ----------------------------

-- ----------------------------
-- Table structure for `te_function`
-- ----------------------------
DROP TABLE IF EXISTS `te_function`;
CREATE TABLE `te_function` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_function
-- ----------------------------

-- ----------------------------
-- Table structure for `te_invoice`
-- ----------------------------
DROP TABLE IF EXISTS `te_invoice`;
CREATE TABLE `te_invoice` (
  `id` int(11) NOT NULL,
  `invoice_title` varchar(200) DEFAULT NULL,
  `code_issuer` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `addressee` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `rfp_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `express_id` int(11) DEFAULT NULL,
  `express_no` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_invoice
-- ----------------------------

-- ----------------------------
-- Table structure for `te_order`
-- ----------------------------
DROP TABLE IF EXISTS `te_order`;
CREATE TABLE `te_order` (
  `id` int(11) NOT NULL,
  `node_id` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `rfp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_order
-- ----------------------------

-- ----------------------------
-- Table structure for `te_order_audit_log`
-- ----------------------------
DROP TABLE IF EXISTS `te_order_audit_log`;
CREATE TABLE `te_order_audit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designer_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `audit_time` bigint(20) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `node_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_order_audit_log
-- ----------------------------

-- ----------------------------
-- Table structure for `te_os_user`
-- ----------------------------
DROP TABLE IF EXISTS `te_os_user`;
CREATE TABLE `te_os_user` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `user_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_os_user
-- ----------------------------

-- ----------------------------
-- Table structure for `te_payment`
-- ----------------------------
DROP TABLE IF EXISTS `te_payment`;
CREATE TABLE `te_payment` (
  `id` int(11) NOT NULL,
  `payee` varchar(200) DEFAULT NULL,
  `receivables_company` varchar(200) DEFAULT NULL,
  `bank_id` int(11) DEFAULT NULL,
  `receivables_account` varchar(50) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `node_id` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `serial_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_payment
-- ----------------------------

-- ----------------------------
-- Table structure for `te_payment_timer_config`
-- ----------------------------
DROP TABLE IF EXISTS `te_payment_timer_config`;
CREATE TABLE `te_payment_timer_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_design_pay` bigint(20) DEFAULT NULL,
  `detailed_design_pay` bigint(20) DEFAULT NULL,
  `work_file_pay` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_payment_timer_config
-- ----------------------------

-- ----------------------------
-- Table structure for `te_receivables`
-- ----------------------------
DROP TABLE IF EXISTS `te_receivables`;
CREATE TABLE `te_receivables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payee` varchar(100) DEFAULT NULL,
  `pay_company` varchar(200) DEFAULT NULL,
  `bank_id` int(11) DEFAULT NULL,
  `pay_account` varchar(50) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `node_id` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  ` update_time` bigint(20) DEFAULT NULL,
  `serial_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_receivables
-- ----------------------------

-- ----------------------------
-- Table structure for `te_rfp`
-- ----------------------------
DROP TABLE IF EXISTS `te_rfp`;
CREATE TABLE `te_rfp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rfp_no` varchar(50) DEFAULT NULL,
  `company_name` varchar(200) DEFAULT NULL,
  `link_men` varchar(100) DEFAULT NULL,
  `link_phone` varchar(20) DEFAULT NULL,
  `exhibitor_name` varchar(200) DEFAULT NULL,
  `company_website` varchar(100) DEFAULT NULL,
  `exhibition_name` varchar(300) DEFAULT NULL,
  `exhibition_city` varchar(50) DEFAULT NULL,
  `exhibition_hall` varchar(200) DEFAULT NULL,
  `booth_no` varchar(30) DEFAULT NULL,
  `booth_width` float DEFAULT NULL,
  `booth_length` float DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `structure` int(11) DEFAULT NULL,
  `hanging_point` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `wish_finish_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_rfp
-- ----------------------------

-- ----------------------------
-- Table structure for `te_role`
-- ----------------------------
DROP TABLE IF EXISTS `te_role`;
CREATE TABLE `te_role` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_role
-- ----------------------------

-- ----------------------------
-- Table structure for `te_single_timer_config`
-- ----------------------------
DROP TABLE IF EXISTS `te_single_timer_config`;
CREATE TABLE `te_single_timer_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_time_limit` int(11) DEFAULT NULL,
  `second_time_limit` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_single_timer_config
-- ----------------------------

-- ----------------------------
-- Table structure for `te_upload_file`
-- ----------------------------
DROP TABLE IF EXISTS `te_upload_file`;
CREATE TABLE `te_upload_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_id` int(11) DEFAULT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `node_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `upload_id` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `plan_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_upload_file
-- ----------------------------

-- ----------------------------
-- Table structure for `te_user`
-- ----------------------------
DROP TABLE IF EXISTS `te_user`;
CREATE TABLE `te_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `job_number` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `bank_account` varchar(30) DEFAULT NULL,
  `bank_user_name` varchar(200) DEFAULT NULL,
  `bank_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '设计师姓名',
  `company_name` varchar(200) DEFAULT NULL,
  `logo_url` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_user
-- ----------------------------
INSERT INTO `te_user` VALUES ('97', null, '111111', 'F3C70A24CDF0FC01278CE828E7A8BB80CE7F00E3', '1589387301@yy.com', '1510293258000', null, '1', '1510293258000', '2', '1589387301', '620000', '620100', null, null, null, '哈哈哈', null, 'http://localhost:8021/Uploads/zh-cn/Frontend/201711/dqt3v4z3.oag13102017115759.jpg');
INSERT INTO `te_user` VALUES ('98', null, '17317360310', 'FFCC7251C1BA2BB881B36ABE3BE0EBC8A409C952', 'ning.lu@e3-expo.com', '1504675278000', null, '1', '1504675278000', '2', '123123', '440000', '440300', null, null, null, '测试设计师', null, '');
INSERT INTO `te_user` VALUES ('99', null, '000000', 'F3C70A24CDF0FC01278CE828E7A8BB80CE7F00E3', '1589387301@qq.com', '1503626124000', null, '1', '1503626124000', '2', '1589387301', '630000', '632100', null, null, null, '李支洋', null, '');
INSERT INTO `te_user` VALUES ('100', null, '15527366855', 'F3C70A24CDF0FC01278CE828E7A8BB80CE7F00E3', 'xx_695513639@126.com', '1503540660000', null, '1', '1503540660000', '2', '15527366855', '360000', '360200', null, null, null, 'Morboz设计师', null, '');
INSERT INTO `te_user` VALUES ('101', null, '18888888888', 'F3C70A24CDF0FC01278CE828E7A8BB80CE7F00E3', 'micheal.xing@e3-expo.com', '1503460509000', null, '1', '1503460509000', '2', '15527366855', '360000', '360200', null, null, null, 'Morboz设计师', null, '');
INSERT INTO `te_user` VALUES ('102', null, '18866666666', 'F3C70A24CDF0FC01278CE828E7A8BB80CE7F00E3', '1589387301@qq.com', '1503373688000', null, '1', '1503373688000', '2', '1589387301', '150000', '152500', null, null, null, '李支洋', null, '');
INSERT INTO `te_user` VALUES ('103', null, '18822222222', 'F3C70A24CDF0FC01278CE828E7A8BB80CE7F00E3', '1589387301@qq.com', '1503044304000', null, '1', '1503044304000', '2', '15527366855', '340000', '340100', null, null, null, 'Morboz设计师', null, '');
INSERT INTO `te_user` VALUES ('104', '1510301874', '111', '976004C2E303151102F3806BA4AC712C45F9718B', null, '1510301874', '1', '1', '1510301874', '1', '4664', '1300', '3330', '1%2B632566', 'dafasfa', null, null, null, null);
INSERT INTO `te_user` VALUES ('105', '1510302681', '1112415', '976004C2E303151102F3806BA4AC712C45F9718B', null, '1510302681924', '1', '1', '1510302681924', '1', '4664', '1300', '3330', '1%252B632566', 'dafasfa', null, null, null, null);

-- ----------------------------
-- Table structure for `te_user_audit_log`
-- ----------------------------
DROP TABLE IF EXISTS `te_user_audit_log`;
CREATE TABLE `te_user_audit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `audit_user_id` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of te_user_audit_log
-- ----------------------------

-- ----------------------------
-- Table structure for `tr_role_function`
-- ----------------------------
DROP TABLE IF EXISTS `tr_role_function`;
CREATE TABLE `tr_role_function` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `function_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_role_function
-- ----------------------------

-- ----------------------------
-- Table structure for `tr_user_bank`
-- ----------------------------
DROP TABLE IF EXISTS `tr_user_bank`;
CREATE TABLE `tr_user_bank` (
  `id` int(11) NOT NULL,
  `bank_id` int(11) DEFAULT NULL,
  `bank_account` varchar(50) DEFAULT NULL,
  `bank_user_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_user_bank
-- ----------------------------

-- ----------------------------
-- Table structure for `tr_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tr_user_role`;
CREATE TABLE `tr_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_user_role
-- ----------------------------
