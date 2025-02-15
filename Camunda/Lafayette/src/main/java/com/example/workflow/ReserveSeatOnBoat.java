package com.example.workflow;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("reserveSeatOnBoat")
public class ReserveSeatOnBoat implements JavaDelegate{
	@Named
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		// TODO Auto-generated method stub
		
		String money = "0.0";
		String ticketType = "Coach";
		
		money = (String) delegateExecution.getVariable("money");
		double moneyDouble = Double.parseDouble(money);
		
		if (moneyDouble >= 10000) {
			ticketType = "First class";
		} else if (moneyDouble >= 5000) {
			ticketType = "Bussiness class";
		} else if (moneyDouble <= 10) {
            ticketType = "Stowaway Class";
            throw new BpmnError("Fall_Overboard", "A Cheap ticket has led to a small wave throwing you overboard."); //parameters: errorCode, message
		}
		
		delegateExecution.setVariable("ticketType", ticketType);
	}

}
