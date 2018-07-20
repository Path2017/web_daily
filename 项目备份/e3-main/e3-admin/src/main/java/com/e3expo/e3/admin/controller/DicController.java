package com.e3expo.e3.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.domain.AlipayEcoMycarCarlibInfoPushModel;
import com.e3expo.e3.admin.controller.rest.RestfulJsonModelAndView;
import com.e3expo.e3.enumration.EnumAuditStatus;
import com.e3expo.e3.enumration.EnumOrderAdminStatus;
import com.e3expo.e3.model.City;
import com.e3expo.e3.model.Country;
import com.e3expo.e3.model.DesignerWorkingYears;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.view.DicView;
import com.e3expo.e3.service.interfaces.IDic;

@Controller
public class DicController extends BaseController {
	@Autowired
	private IDic iDic;

	/**
	 * @api {get} /city
	 * @apiGroup Dic
	 * @apiDescription 字典信息-获取城市名称(api)
	 * @apiParam {int} provinceId 必填
	 * @apiSuccess {City} city (list)
	 * @apiSuccess {int} city.id id
	 * @apiSuccess {String} city.name 城市名称
	 */
	@GetMapping("/city")
	public ModelAndView getCities(City record) {
		List<City> cities = this.iDic.getCityByProvinceId(record);
		return RestfulJsonModelAndView.buildJsonModelAndView(cities);
	}

	/**
	 * @api {get} /province
	 * @apiGroup Dic
	 * @apiDescription 字典信息-获取省份信息(api)
	 * @apiParam {int} countryId 必填
	 * @apiSuccess {Province} Province (list)
	 * @apiSuccess {int} Province.id
	 * @apiSuccess {String} Province.name 省份名称
	 */
	@GetMapping("/province")
	public ModelAndView getCities(Province record) {
		List<Province> provinces = this.iDic.getProvinceByCountryId(record);
		return RestfulJsonModelAndView.buildJsonModelAndView(provinces);
	}

	/**
	 * @api {get} /country
	 * @apiGroup Dic
	 * @apiDescription 字典信息-获取国家信息(api)
	 * @apiSuccess {Country} country (list)
	 * @apiSuccess {int} country.id
	 * @apiSuccess {String} country.name 国家名称
	 */
	@GetMapping("/country")
	public ModelAndView getCities() {
		List<Country> countries = this.iDic.getCountries();
		return RestfulJsonModelAndView.buildJsonModelAndView(countries);
	}

	/**
	 * @api {get} /auditStatus
	 * @apiGroup Dic
	 * @apiDescription 字典信息-获取用户审核状态列表(api)	
	 * @apiSuccess {DicView} dicView (list)
	 * @apiSuccess {int} dicView.key id
	 * @apiSuccess {String} dicView.remark 状态说明
	 */
	@GetMapping("/auditStatus")
	public ModelAndView getStatus() {
		List<DicView> dicViews=new ArrayList<>();
		for (EnumAuditStatus item : EnumAuditStatus.values()) {
			DicView dicView=new DicView();
			dicView.setKey(item.getValue());
			dicView.setRemark(item.getName());
			dicViews.add(dicView);
		}
		return RestfulJsonModelAndView.buildJsonModelAndView(dicViews);
	}

	/**
	 * @api {get} /workYears
	 * @apiGroup Dic
	 * @apiDescription 字典信息-获取设计师工作年限列表(api)
	 * @apiSuccess {DesignerWorkingYear} designerWorkingYears (list)
	 * @apiSuccess {int} designerWorkingYears.id id
	 * @apiSuccess {int} designerWorkingYears.splitPoint 工作年数的分隔数字
	 * @apiSuccess {String} designerWorkingYears.name 工作年数的中文说明
	 */
	@GetMapping("/workyears")
	public ModelAndView getWorkYears() {
		List<DesignerWorkingYears> workYears = iDic.getWorkYear();
		return RestfulJsonModelAndView.buildJsonModelAndView(workYears);
	}
	/**
	 * @api {get} /orderStatus
	 * @apiGroup Dic
	 * @apiDescription 字典信息-获取订单状态信息(api)
	 * @apiSuccess {DicView} dicView (list)
	 * @apiSuccess {int} dicView.key id
	 * @apiSuccess {String} dicView.remark 订单状态说明
	 */
	@GetMapping("/orderStatus")
	public ModelAndView getOrderAdminStatus() {
		List<DicView> dicViews=new ArrayList<>();
		for (EnumOrderAdminStatus item : EnumOrderAdminStatus.values()) {
			DicView dicView=new DicView();
			dicView.setKey(item.getKey());
			dicView.setRemark(item.getRemark());
			dicViews.add(dicView);
		}
		return RestfulJsonModelAndView.buildJsonModelAndView(dicViews);
	}
}
