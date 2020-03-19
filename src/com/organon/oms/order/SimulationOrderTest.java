package com.organon.oms.order;

import java.math.BigDecimal;

import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.SecurityExchange;
import quickfix.field.SecurityID;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.fix42.NewOrderSingle;

public class SimulationOrderTest {

	public static void main(String[] args) {

		

	}
	
	private static NewOrderSingle createFixNewOrder()
    {
        NewOrderSingle fixOrder = new NewOrderSingle();
 //       fixOrder.set(new Date());
        fixOrder.set(new HandlInst('1'));
        fixOrder.set(new Account("wendel"));
        fixOrder.set(new Symbol("GOOGL.O"));
        fixOrder.set(new SecurityID("1212"));
        fixOrder.set(new SecurityExchange("NSQ"));
        fixOrder.set(new Price(new BigDecimal("100.0").doubleValue()));
        fixOrder.set(new Side(Side.BUY));
        fixOrder.set(new OrderQty(new BigDecimal("100.0").longValue()));
        fixOrder.set(new OrdType(OrdType.LIMIT));
        fixOrder.set(new ClOrdID("123456"));
        
        return fixOrder;
    }

}
