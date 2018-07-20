package com.e3expo.e3.middleware.config.aop.aspect;

import com.e3expo.e3.enumration.OrderOperationEnum;
import com.e3expo.e3.middleware.config.aop.annotation.OrderOperationLog;
import com.e3expo.e3.model.OrderLog;
import com.e3expo.e3.model.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//@Component
//@Aspect
public class OrderLogAspect {

    @Pointcut("(execution(* com.e3expo.e3.middleware.service.RfpService.insertOrder(..)))")
    public void orderLogPointCut(){}

    @After("orderLogPointCut()")
    public void doAfterCreateOrder(JoinPoint joinPoint) {
        // 创建订单后，将操作插入到
        Class<?> clazz = joinPoint.getTarget().getClass();
        Method[] methods = clazz.getMethods();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Order order = (Order) args[0];
        OrderOperationEnum operation = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.getParameterCount() == args.length) {
                operation = method.getAnnotation(OrderOperationLog.class).value();
            }
        }
//        OrderLog orderLog = new OrderLog(operation.getId(), order.getUserId(), System.currentTimeMillis());


    }
}
