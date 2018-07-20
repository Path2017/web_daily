define({ "api": [
  {
    "type": "get",
    "url": "/auditStatus",
    "title": "",
    "group": "Dic",
    "description": "<p>字典信息-获取用户审核状态列表(api)</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "DicView",
            "optional": false,
            "field": "dicView",
            "description": "<p>(list)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "dicView.key",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "dicView.remark",
            "description": "<p>状态说明</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/DicController.java",
    "groupTitle": "Dic",
    "name": "GetAuditstatus"
  },
  {
    "type": "get",
    "url": "/city",
    "title": "",
    "group": "Dic",
    "description": "<p>字典信息-获取城市名称(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "provinceId",
            "description": "<p>必填</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "City",
            "optional": false,
            "field": "city",
            "description": "<p>(list)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "city.id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "city.name",
            "description": "<p>城市名称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/DicController.java",
    "groupTitle": "Dic",
    "name": "GetCity"
  },
  {
    "type": "get",
    "url": "/country",
    "title": "",
    "group": "Dic",
    "description": "<p>字典信息-获取国家信息(api)</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Country",
            "optional": false,
            "field": "country",
            "description": "<p>(list)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "country.id",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "country.name",
            "description": "<p>国家名称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/DicController.java",
    "groupTitle": "Dic",
    "name": "GetCountry"
  },
  {
    "type": "get",
    "url": "/orderStatus",
    "title": "",
    "group": "Dic",
    "description": "<p>字典信息-获取订单状态信息(api)</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "DicView",
            "optional": false,
            "field": "dicView",
            "description": "<p>(list)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "dicView.key",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "dicView.remark",
            "description": "<p>订单状态说明</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/DicController.java",
    "groupTitle": "Dic",
    "name": "GetOrderstatus"
  },
  {
    "type": "get",
    "url": "/province",
    "title": "",
    "group": "Dic",
    "description": "<p>字典信息-获取省份信息(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "countryId",
            "description": "<p>必填</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Province",
            "optional": false,
            "field": "Province",
            "description": "<p>(list)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "Province.id",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "Province.name",
            "description": "<p>省份名称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/DicController.java",
    "groupTitle": "Dic",
    "name": "GetProvince"
  },
  {
    "type": "get",
    "url": "/workYears",
    "title": "",
    "group": "Dic",
    "description": "<p>字典信息-获取设计师工作年限列表(api)</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "DesignerWorkingYear",
            "optional": false,
            "field": "designerWorkingYears",
            "description": "<p>(list)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "designerWorkingYears.id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "designerWorkingYears.splitPoint",
            "description": "<p>工作年数的分隔数字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "designerWorkingYears.name",
            "description": "<p>工作年数的中文说明</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/DicController.java",
    "groupTitle": "Dic",
    "name": "GetWorkyears"
  },
  {
    "type": "post",
    "url": "/fileUpload",
    "title": "",
    "group": "FileOperation",
    "description": "<p>上传单个文件(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "MultipartFile",
            "optional": false,
            "field": "attachment",
            "description": "<p>文件的媒体数据</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "data",
            "description": "<p>图片在服务器的路径</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "code",
            "description": "<p>200成功</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/CommonController.java",
    "groupTitle": "FileOperation",
    "name": "PostFileupload"
  },
  {
    "type": "post",
    "url": "/fileUploads",
    "title": "",
    "group": "FileOperation",
    "description": "<p>上传多个文件(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "MultipartFile[]",
            "optional": false,
            "field": "attachment",
            "description": "<p>文件的媒体数据(数组，多个文件)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "string[]",
            "optional": false,
            "field": "data",
            "description": "<p>数组.图片在服务器的路径</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "code",
            "description": "<p>200成功</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/CommonController.java",
    "groupTitle": "FileOperation",
    "name": "PostFileuploads"
  },
  {
    "type": "get",
    "url": "/orderDetailView",
    "title": "",
    "group": "Order",
    "description": "<p>订单管理-获取订单详情(themeLeaf)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "orderId",
            "description": "<p>订单编号(必填)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "OrderDetailView",
            "optional": false,
            "field": "OrderDetailView",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "OrderDetailView.id",
            "description": "<p>id 订单编号</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "OrderDetailView.userId",
            "description": "<p>订单的创建人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.nodeId",
            "description": "<p>订单目前的节点编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.updateTime",
            "description": "<p>订单目前的更新时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.status",
            "description": "<p>订单状态(0：正常；1：已取消；2：已终止；3：已完成)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.rfpId",
            "description": "<p>需求编号</p>"
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.id",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.rfpNo",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.companyName",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.exhibitorName",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.companyWebsite",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.exhibitionName",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.exhibitionCity",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.exhibitionHall",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.boothNo",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.boothWidth",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.boothLength",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.createTime",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.updateTime",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.status",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Rfp",
            "optional": false,
            "field": "OrderDetailView.rfpView.userId",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "RfpDetail",
            "optional": false,
            "field": "OrderDetailView.rfpView.rfpDetail",
            "description": "<p>需求具体描述</p>"
          },
          {
            "group": "Success 200",
            "type": "OrderDesignerLog",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs",
            "description": "<p>订单操作日志(集合)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.id",
            "description": "<p>操作编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.nodeId",
            "description": "<p>操作时对应的流程节点编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.price",
            "description": "<p>价钱，0表示正常流程节点，非零表示付钱节点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.type",
            "description": "<p>表示1:创作；2：修改</p>"
          },
          {
            "group": "Success 200",
            "type": "UploadFile",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.uploadFiles",
            "description": "<p>表示该节点上传的文件信息(集合)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.uploadFiles.id",
            "description": "<p>上传附件的编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.uploadFiles.fileType",
            "description": "<p>附件类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.uploadFiles.filePath",
            "description": "<p>附件路径</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.uploadFiles.nodeId",
            "description": "<p>附件对应的流程节点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.orderDesignerLogs.uploadFiles.uploadId",
            "description": "<p>操作的id</p>"
          },
          {
            "group": "Success 200",
            "type": "DesignerBid",
            "optional": false,
            "field": "OrderDetailView.designerBids",
            "description": "<p>订单对应的竞标信息(集合)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.id",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.orderId",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.isSuccess",
            "description": "<p>0,未成功.1,成功竞标</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.bidTime",
            "description": "<p>投标时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.isValid",
            "description": "<p>是否有效</p>"
          },
          {
            "group": "Success 200",
            "type": "OrderDesignerPrice",
            "optional": false,
            "field": "OrderDetailView.designerBids.orderDesignerPrices",
            "description": "<p>每个流程节点的标价(集合)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.orderDesignerPrices.id",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.orderDesignerPrices.nodeId",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.orderDesignerPrices.price",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.orderDesignerPrices.type",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.orderDesignerPrices.updateNum",
            "description": "<p>允许更改的次数</p>"
          },
          {
            "group": "Success 200",
            "type": "User",
            "optional": false,
            "field": "OrderDetailView.designerBids.user",
            "description": "<p>竞标人信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.user.id",
            "description": "<p>竞标人标号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.user.jobNumber",
            "description": "<p>竞标人工号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.user.mobile",
            "description": "<p>竞标人手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "OrderNode",
            "optional": false,
            "field": "OrderDetailView.designerBids.user.orderNodes",
            "description": "<p>走过的流程节点(集合)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.user.orderNodes.id",
            "description": "<p>流程节点的编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.user.orderNodes.node",
            "description": "<p>流程节点的名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderDetailView.designerBids.user.orderNodes.pid",
            "description": "<p>上一个流程节点的名称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "GetOrderdetailview"
  },
  {
    "type": "get",
    "url": "/pagedOrder",
    "title": "",
    "group": "Order",
    "description": "<p>订单管理-获取订单列表(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "exhibitionCity",
            "description": "<p>参展城市</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "rfpNo",
            "description": "<p>订单号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "companyName",
            "description": "<p>展览公司</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "linkManName",
            "description": "<p>联系人姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "linkManPhone",
            "description": "<p>联系人手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "statusKey",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "startTime",
            "description": "<p>状态</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "endTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "pageIndex",
            "description": "<p>第几页，必填</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "OrderView",
            "optional": false,
            "field": "OrderView",
            "description": "<p>系统用户信息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "OrderView.id",
            "description": "<p>id 订单编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderView.exhibitionCity",
            "description": "<p>举办城市</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderView.rfpNo",
            "description": "<p>订单号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderView.companyName",
            "description": "<p>展览公司名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderView.linkManName",
            "description": "<p>联系人姓名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "OrderView.linkManPhone",
            "description": "<p>联系人手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "totalNum",
            "description": "<p>总条数</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "GetPagedorder"
  },
  {
    "type": "post",
    "url": "/updateOrder",
    "title": "",
    "group": "Order",
    "description": "<p>订单管理-强制取消订单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>订单编号(必填)</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>0：正常；1：已取消；2：已终止；3：已完成(必填)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostUpdateorder"
  },
  {
    "type": "Get",
    "url": "/logout",
    "title": "",
    "group": "SystemManage",
    "description": "<p>登录-退出系统(link)</p>",
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/OsUserController.java",
    "groupTitle": "SystemManage",
    "name": "GetLogout"
  },
  {
    "type": "post",
    "url": "/loginCheck",
    "title": "",
    "group": "SystemManage",
    "description": "<p>登录-登录系统(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号,必填</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码,必填</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "code",
            "description": "<p>200状态码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/OsUserController.java",
    "groupTitle": "SystemManage",
    "name": "PostLogincheck"
  },
  {
    "type": "get",
    "url": "/userAuditDetailView",
    "title": "",
    "group": "UserAudit",
    "description": "<p>用户审核-获取设计师或者展装公司详情详情(themeLeaf)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userAuditId",
            "description": "<p>审核编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "UserAuditDetailView",
            "optional": false,
            "field": "userAuditDetailView",
            "description": "<p>设计师所有审核信息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userAuditDetailView.id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "userAuditDetailView.createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userAuditDetailView.status",
            "description": "<p>用户状态</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.statusRemark",
            "description": "<p>状态说明</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.qq",
            "description": "<p>QQ</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.name",
            "description": "<p>姓名</p>"
          },
          {
            "group": "Success 200",
            "type": "userAuditFile",
            "optional": false,
            "field": "userAuditDetailView.images",
            "description": "<p>附件</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.images.fileName",
            "description": "<p>附件名称</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.images.filePath",
            "description": "<p>附件路径</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.images.fileType",
            "description": "<p>附件类型</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userAuditDetailView.images.userAuditId",
            "description": "<p>审核id</p>"
          },
          {
            "group": "Success 200",
            "type": "country",
            "optional": false,
            "field": "userAuditDetailView.country",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userAuditDetailView.country.id",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.country.name",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "province",
            "optional": false,
            "field": "userAuditDetailView.province",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "city",
            "optional": false,
            "field": "userAuditDetailView.city",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userAuditDetailView.city.id",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.city.name",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "UserAuditRemarkLog",
            "optional": false,
            "field": "userAuditDetailView.userAuditRemarkLogs",
            "description": "<p>审核日志</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.userAuditRemarkLogs.remark",
            "description": "<p>评价</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "userAuditDetailView.userAuditRemarkLogs.auditCreateTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "OsUser",
            "optional": false,
            "field": "userAuditDetailView.userAuditRemarkLogs.osUser",
            "description": "<p>审核人</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userAuditDetailView.userAuditRemarkLogs.osUser.id",
            "description": "<p>审核人Id</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "userAuditDetailView.userAuditRemarkLogs.osUser.name",
            "description": "<p>审核人姓名</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserAudit",
    "name": "GetUserauditdetailview"
  },
  {
    "type": "get",
    "url": "/userAuditPagedData",
    "title": "",
    "group": "UserAudit",
    "description": "<p>用户审核-获取用户的审核列表(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userType",
            "description": "<p>用户类型1，设计师。2，展装公司,必填</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "countryId",
            "description": "<p>国家ID</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "provinceId",
            "description": "<p>省份ID</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "cityId",
            "description": "<p>城市ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "pageIndex",
            "description": "<p>第几页，必填</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "UserView",
            "optional": false,
            "field": "userView",
            "description": "<p>用户信息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userView.id",
            "description": "<p>id 用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.countryName",
            "description": "<p>国家</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.provinceName",
            "description": "<p>省份</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.cityName",
            "description": "<p>城市</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.name",
            "description": "<p>名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.statusRemark",
            "description": "<p>状态的中文描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.auditRemark",
            "description": "<p>审核的评价内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "userView.createTime",
            "description": "<p>申请时间</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "totalNum",
            "description": "<p>总条数</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserAudit",
    "name": "GetUserauditpageddata"
  },
  {
    "type": "post",
    "url": "/userAudit",
    "title": "",
    "group": "UserAudit",
    "description": "<p>用户审核-修改审核信息(点击审核通过，不通过时，将上面编辑的所有信息保存)(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "qq",
            "description": "<p>QQ</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "country_id",
            "description": "<p>国家id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "province_id",
            "description": "<p>省份ID</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "city_id",
            "description": "<p>城市Id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "userAuditRemark",
            "description": "<p>备注信息</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "code",
            "description": "<p>200成功</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserAudit",
    "name": "PostUseraudit"
  },
  {
    "type": "post",
    "url": "/userAuditRemark",
    "title": "",
    "group": "UserAudit",
    "description": "<p>用户审核-添加备注(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "userAuditRemark",
            "description": "<p>备注内容</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "code",
            "description": "<p>200成功</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserAudit",
    "name": "PostUserauditremark"
  },
  {
    "type": "get",
    "url": "/designerUserView",
    "title": "",
    "group": "UserManage",
    "description": "<p>用户管理-获取设计师或者展装公司详情详情(themeLeaf)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userId",
            "description": "<p>设计师编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "desingerUserView",
            "optional": false,
            "field": "desingerUserView",
            "description": "<p>设计师所有信息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "desingerUserView.id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.mobile",
            "description": "<p>手机</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "desingerUserView.createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "desingerUserView.status",
            "description": "<p>用户状态</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.statusRemark",
            "description": "<p>用户状态说明</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.qq",
            "description": "<p>QQ</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.name",
            "description": "<p>名字</p>"
          },
          {
            "group": "Success 200",
            "type": "DesignerImage",
            "optional": false,
            "field": "desingerUserView.images",
            "description": "<p>附件信息</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.images.fileName",
            "description": "<p>附件名字</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.images.filePath",
            "description": "<p>附件路径</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.images.fileType",
            "description": "<p>附件类型</p>"
          },
          {
            "group": "Success 200",
            "type": "country",
            "optional": false,
            "field": "desingerUserView.country",
            "description": "<p>国家</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "desingerUserView.country.id",
            "description": "<p>国家编号</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.country.name",
            "description": "<p>国家名字</p>"
          },
          {
            "group": "Success 200",
            "type": "province",
            "optional": false,
            "field": "desingerUserView.province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Success 200",
            "type": "city",
            "optional": false,
            "field": "desingerUserView.city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "desingerUserView.city.id",
            "description": "<p>城市编号</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.city.name",
            "description": "<p>城市名字</p>"
          },
          {
            "group": "Success 200",
            "type": "auditLogs",
            "optional": false,
            "field": "desingerUserView.auditLogs",
            "description": "<p>审核日志</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.auditLogs.remark",
            "description": "<p>审核备注</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "desingerUserView.auditLogs.status",
            "description": "<p>审核时用户的状态</p>"
          },
          {
            "group": "Success 200",
            "type": "OsUser",
            "optional": false,
            "field": "desingerUserView.auditLogs.osUser",
            "description": "<p>审核人</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "desingerUserView.auditLogs.osUser.id",
            "description": "<p>审核人编号</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "desingerUserView.auditLogs.osUser.name",
            "description": "<p>审核人姓名</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserManage",
    "name": "GetDesigneruserview"
  },
  {
    "type": "get",
    "url": "/pagedUserView",
    "title": "",
    "group": "UserManage",
    "description": "<p>用户管理-获取用户列表(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userType",
            "description": "<p>用户类型1，设计师。2，展装公司。必填</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "countryId",
            "description": "<p>国家ID</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "provinceId",
            "description": "<p>省份ID</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "cityId",
            "description": "<p>城市ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "long",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "pageIndex",
            "description": "<p>第几页，必填</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "UserView",
            "optional": false,
            "field": "userView",
            "description": "<p>用户信息(list)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "userView.id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.countryName",
            "description": "<p>国家</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.provinceName",
            "description": "<p>省份</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.cityName",
            "description": "<p>城市</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.name",
            "description": "<p>名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.statusRemark",
            "description": "<p>状态的中文描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userView.auditRemark",
            "description": "<p>审核的评价内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "userView.createTime",
            "description": "<p>申请时间</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "totalNum",
            "description": "<p>总条数</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserManage",
    "name": "GetPageduserview"
  },
  {
    "type": "post",
    "url": "/remarkUser",
    "title": "",
    "group": "UserManage",
    "description": "<p>用户管理-用户详情-管理员备注用户(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userId",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注信息</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserManage",
    "name": "PostRemarkuser"
  },
  {
    "type": "post",
    "url": "/startUser",
    "title": "",
    "group": "UserManage",
    "description": "<p>用户管理-用户详情-启用或者停用用户(api)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "isValid",
            "description": "<p>启用或者停用标志 -1 停用。1启用</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userId",
            "description": "<p>用户编号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "D:/Pico/E3/e3-main/e3-admin/src/main/java/com/e3expo/e3/admin/controller/UserController.java",
    "groupTitle": "UserManage",
    "name": "PostStartuser"
  }
] });
