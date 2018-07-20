package com.e3expo.e3.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.e3expo.e3.model.form.DesignerRegisterForm;

@Component
@EnableRabbit
public class RegisterOldListener {
	
	@RabbitListener(queues="queue-register-old")
	public void makeRegisterOld(DesignerRegisterForm registerForm) {
		
	}

}
