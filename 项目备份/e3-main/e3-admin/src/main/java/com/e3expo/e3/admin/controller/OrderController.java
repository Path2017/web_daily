package com.e3expo.e3.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.e3expo.e3.admin.controller.rest.RestfulJsonModelAndView;
import com.e3expo.e3.enumration.EnumOrderAdminStatus;
import com.e3expo.e3.enumration.EnumUserType;
import com.e3expo.e3.model.Order;
import com.e3expo.e3.model.form.OrderAdminParam;
import com.e3expo.e3.model.view.OrderDetailView;
import com.e3expo.e3.model.view.OrderView;
import com.e3expo.e3.model.view.PagedData;
import com.e3expo.e3.service.interfaces.IAdminOrder;
import com.e3expo.e3.util.DateUtils;

@Controller
public class OrderController extends BaseController {
	@Autowired
	private IAdminOrder iAdminOrder;

	/**
	 * @api {get} /pagedOrder
	 * @apiGroup Order
	 * @apiDescription 订单管理-获取订单列表(api)
	 * @apiParam {string} exhibitionCity 参展城市
	 * @apiParam {string} rfpNo 订单号
	 * @apiParam {string} companyName 展览公司
	 * @apiParam {string} linkManName 联系人姓名
	 * @apiParam {string} linkManPhone 联系人手机号
	 * @apiParam {string} statusKey 手机号
	 * @apiParam {long} startTime 状态
	 * @apiParam {long} endTime 开始时间
	 * @apiParam {int} pageIndex 第几页，必填
	 * @apiSuccess {OrderView} OrderView 系统用户信息
	 * @apiSuccess {int} OrderView.id id 订单编号
	 * @apiSuccess {String} OrderView.exhibitionCity 举办城市
	 * @apiSuccess {String} OrderView.rfpNo 订单号
	 * @apiSuccess {String} OrderView.companyName 展览公司名称
	 * @apiSuccess {String} OrderView.linkManName 联系人姓名
	 * @apiSuccess {String} OrderView.linkManPhone 联系人手机号
	 * @apiSuccess {int} totalNum 总条数
	 * 
	 */
	@GetMapping("/pagedOrder")
	public ModelAndView pagedOrderView(OrderAdminParam param) {

		param=iAdminOrder.getPagedOrderByAdmin(param);
		return RestfulJsonModelAndView.buildJsonModelAndView(param);
	}

	/**
	 * @api {get} /orderDetailView
	 * @apiGroup Order
	 * @apiDescription 订单管理-获取订单详情(themeLeaf)
	 * @apiParam {int} orderId 订单编号(必填)
	 * @apiSuccess {OrderDetailView} OrderDetailView
	 * @apiSuccess {int} OrderDetailView.id id 订单编号
	 * @apiSuccess {int} OrderDetailView.userId 订单的创建人
	 * @apiSuccess {String} OrderDetailView.nodeId 订单目前的节点编号
	 * @apiSuccess {String} OrderDetailView.updateTime 订单目前的更新时间
	 * @apiSuccess {String} OrderDetailView.status 订单状态(0：正常；1：已取消；2：已终止；3：已完成)
	 * @apiSuccess {String} OrderDetailView.rfpId 需求编号
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.id
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.rfpNo
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.companyName
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.exhibitorName
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.companyWebsite
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.exhibitionName
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.exhibitionCity
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.exhibitionHall
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.exhibitionHall
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.boothNo
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.boothWidth
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.boothLength
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.createTime
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.updateTime
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.status
	 * @apiSuccess {Rfp} OrderDetailView.rfpView.userId
	 * @apiSuccess {RfpDetail} OrderDetailView.rfpView.rfpDetail 需求具体描述
	 * @apiSuccess {OrderDesignerLog} OrderDetailView.orderDesignerLogs 订单操作日志(集合)
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.id 操作编号
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.nodeId 操作时对应的流程节点编号
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.price 价钱，0表示正常流程节点，非零表示付钱节点
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.type 表示1:创作；2：修改
	 * @apiSuccess {UploadFile} OrderDetailView.orderDesignerLogs.uploadFiles 表示该节点上传的文件信息(集合)
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.uploadFiles.id 上传附件的编号
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.uploadFiles.fileType 附件类型
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.uploadFiles.filePath  附件路径
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.uploadFiles.nodeId 附件对应的流程节点
	 * @apiSuccess {String} OrderDetailView.orderDesignerLogs.uploadFiles.uploadId  操作的id
	 * @apiSuccess {DesignerBid} OrderDetailView.designerBids 订单对应的竞标信息(集合)
	 * @apiSuccess {String} OrderDetailView.designerBids.id
	 * @apiSuccess {String} OrderDetailView.designerBids.orderId
	 * @apiSuccess {String} OrderDetailView.designerBids.isSuccess 0,未成功.1,成功竞标
	 * @apiSuccess {String} OrderDetailView.designerBids.bidTime 投标时间
	 * @apiSuccess {String} OrderDetailView.designerBids.isValid 是否有效
	 * @apiSuccess {OrderDesignerPrice}  OrderDetailView.designerBids.orderDesignerPrices 每个流程节点的标价(集合)
	 * @apiSuccess {String} OrderDetailView.designerBids.orderDesignerPrices.id
	 * @apiSuccess {String} OrderDetailView.designerBids.orderDesignerPrices.nodeId
	 * @apiSuccess {String} OrderDetailView.designerBids.orderDesignerPrices.price
	 * @apiSuccess {String} OrderDetailView.designerBids.orderDesignerPrices.type
	 * @apiSuccess {String} OrderDetailView.designerBids.orderDesignerPrices.updateNum 允许更改的次数
	 * @apiSuccess {User} OrderDetailView.designerBids.user 竞标人信息
	 * @apiSuccess {String} OrderDetailView.designerBids.user.id 竞标人标号
	 * @apiSuccess {String} OrderDetailView.designerBids.user.jobNumber 竞标人工号
	 * @apiSuccess {String} OrderDetailView.designerBids.user.mobile 竞标人手机号
	 * @apiSuccess {OrderNode} OrderDetailView.designerBids.user.orderNodes 走过的流程节点(集合)
	 * @apiSuccess {String} OrderDetailView.designerBids.user.orderNodes.id 流程节点的编号
	 * @apiSuccess {String} OrderDetailView.designerBids.user.orderNodes.node  流程节点的名称
	 * @apiSuccess {String} OrderDetailView.designerBids.user.orderNodes.pid 上一个流程节点的名称
	 */
	@GetMapping("/orderDetailView")
	public ModelAndView getOrderDetailView(Integer orderId) {
		OrderDetailView orderDetailView = iAdminOrder.getOrderDetailView(orderId);
		return RestfulJsonModelAndView.buildJsonModelAndView(orderDetailView);
	}

	/**
	 * @api {post} /updateOrder
	 * @apiGroup Order
	 * @apiDescription 订单管理-强制取消订单
	 * @apiParam {int} id 订单编号(必填)
	 * @apiParam {int} status 0：正常；1：已取消；2：已终止；3：已完成(必填)
	 */
	@PostMapping("/updateOrder")
	public ModelAndView updateOrder(Order record) {
		this.iAdminOrder.updateOrder(record);
		return RestfulJsonModelAndView.buildJsonModelAndView();
	}

}
