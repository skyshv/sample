package com.sample.etl.publisher;

import java.util.Collection;

import com.sample.etl.Trade;
import com.sample.etl.TradeCacheUtil;

public class ConsolePublisher implements IPublisher {

	@Override
	public void publishTrade(Trade trades) {
		// TODO Auto-generated method stub
		if( trades == null ) return;
		System.out.println(trades.getSecurityCode() + ":" + TradeCacheUtil.getPostion(trades.getSecurityCode()));
	}


}
