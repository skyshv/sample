package com.sample.etl.enricher;

import com.sample.etl.Trade;
import com.sample.etl.TradeCacheUtil;
import com.sample.etl.Transaction;

import java.util.Collections;
import java.util.List;

public class CancelEnricher implements IEnricher {

	public List<Trade> HandleTransaction(Transaction trans) {
		// TODO Auto-generated method stub
		if(trans != null && "CANCEL".equalsIgnoreCase(trans.getAction())){
			Trade originalTrade = TradeCacheUtil.getOriginalTrade(trans.getTradeID());
			Trade trade = new Trade();
			trade.setQuantity(0);
			trade.setSecurityCode(originalTrade.getSecurityCode());
			trade.setTradeId(trans.getTradeID());
			return Collections.singletonList(trade);
		}
		return null;
	}

}
