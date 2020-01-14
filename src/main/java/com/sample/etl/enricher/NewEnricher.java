package com.sample.etl.enricher;

import com.sample.etl.Trade;
import com.sample.etl.Transaction;

import java.util.Collections;
import java.util.List;

public class NewEnricher implements IEnricher {

	public List<Trade> HandleTransaction(Transaction trans) {
		// TODO Auto-generated method stub
		if(trans != null ){
			if(trans.getVersion() == 1){
				Trade trade = new Trade();
				trade.setQuantity("SELL".equalsIgnoreCase(trans.getLongShort())? -trans.getQuantity(): trans.getQuantity());
				trade.setSecurityCode(trans.getSecurityCode());
				trade.setTradeId(trans.getTradeID());
				return Collections.singletonList(trade);
			}
		}
		return null;
	}

}
