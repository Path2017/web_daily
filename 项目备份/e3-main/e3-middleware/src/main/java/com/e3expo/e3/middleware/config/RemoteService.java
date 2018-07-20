package com.e3expo.e3.middleware.config;

import com.caucho.hessian.io.SerializerFactory;
import com.e3expo.e3.middleware.config.hessian.serializer.BigDecimalSerializerFactory;
import com.e3expo.e3.middleware.exporter.AdminOrderExporter;
import com.e3expo.e3.middleware.exporter.AdminUserExporter;
import com.e3expo.e3.middleware.exporter.DesignerBidExporter;
import com.e3expo.e3.middleware.exporter.DicExporter;
import com.e3expo.e3.middleware.exporter.OrderExporter;
import com.e3expo.e3.middleware.exporter.OsUserExporter;
import com.e3expo.e3.middleware.exporter.RfpExporter;
import com.e3expo.e3.service.interfaces.IAdminOrder;
import com.e3expo.e3.service.interfaces.IAdminUser;
import com.e3expo.e3.service.interfaces.IDesignerBid;
import com.e3expo.e3.service.interfaces.IDic;
import com.e3expo.e3.service.interfaces.IOrder;
import com.e3expo.e3.service.interfaces.IOsUser;
import com.e3expo.e3.service.interfaces.IRfp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import com.e3expo.e3.common.ServiceConst;
import com.e3expo.e3.middleware.exporter.TestExporter;
import com.e3expo.e3.middleware.exporter.UserExporter;
import com.e3expo.e3.middleware.exporter.UtilExporter;
import com.e3expo.e3.service.interfaces.ITest;
import com.e3expo.e3.service.interfaces.IUser;
import com.e3expo.e3.service.interfaces.IUtil;

@Configuration
public class RemoteService {

    @Bean(name = "/test")
    public HessianServiceExporter testExporter(TestExporter test) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(test);
        exporter.setServiceInterface(ITest.class);
        return exporter;
    }

    @Bean(name = ServiceConst.USER_LOGIN)
    public HessianServiceExporter queryUser(UserExporter user) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(user);
        exporter.setServiceInterface(IUser.class);
        return exporter;
    }

    @Bean(name = ServiceConst.OS_USER)
    public HessianServiceExporter osUser(OsUserExporter osUserExporter) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(osUserExporter);
        exporter.setServiceInterface(IOsUser.class);
        return exporter;
    }

    @Bean(name = ServiceConst.RFP)
    public HessianServiceExporter rfp(RfpExporter rfpExporter) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(rfpExporter);
        exporter.setServiceInterface(IRfp.class);
        // Set SerializerFactory
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new BigDecimalSerializerFactory());
        exporter.setSerializerFactory(serializerFactory);
        return exporter;
    }
    @Bean(name = ServiceConst.DIC_QUERY)
    public HessianServiceExporter dic(DicExporter dicExporter) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(dicExporter);
        exporter.setServiceInterface(IDic.class);
        return exporter;
    }
    @Bean(name = ServiceConst.ADMIN_USER)
    public HessianServiceExporter adminUser(AdminUserExporter adminUserExporter) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(adminUserExporter);
        exporter.setServiceInterface(IAdminUser.class);
        return exporter;
    }

    @Bean(name = ServiceConst.DESIGNER_BID)
    public HessianServiceExporter designerBid(DesignerBidExporter designerBidExporter) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(designerBidExporter);
        exporter.setServiceInterface(IDesignerBid.class);
        // Set SerializerFactory
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new BigDecimalSerializerFactory());
        exporter.setSerializerFactory(serializerFactory);
        return exporter;
    }
    @Bean(name = ServiceConst.ADMIN_ORDER)
    public HessianServiceExporter adminOrder(AdminOrderExporter adminOrderExporter) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(adminOrderExporter);
        exporter.setServiceInterface(IAdminOrder.class);
        return exporter;
    }

    @Bean(name = ServiceConst.UTIL)
    public HessianServiceExporter util(UtilExporter utilExporter) {
    	   HessianServiceExporter exporter = new HessianServiceExporter();
           exporter.setService(utilExporter);
           exporter.setServiceInterface(IUtil.class);
           return exporter;
    }

    @Bean(name = ServiceConst.ORDER)
    public HessianServiceExporter order(OrderExporter orderExporter) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(orderExporter);
        exporter.setServiceInterface(IOrder.class);
        // Set SerializerFactory
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new BigDecimalSerializerFactory());
        exporter.setSerializerFactory(serializerFactory);
        return exporter;
    }
}
