package com.sample.etl.enricher;

import com.sample.etl.Trade;
import com.sample.etl.TradeCacheUtil;
import com.sample.etl.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UpdateEnricher implements IEnricher {

	public List<Trade> HandleTransaction(Transaction trans) {
		// TODO Auto-generated method stub
		if( trans != null && trans.getVersion() > 1 && "UPDATE".equalsIgnoreCase(trans.getAction())){
			ArrayList<Trade> trades = new ArrayList<Trade>();
			Trade originalTrade = TradeCacheUtil.getOriginalTrade(trans.getTradeID());
			Trade trade = new Trade();
			trade.setSecurityCode(trans.getSecurityCode());
			trade.setTradeId(trans.getTradeID());
			trade.setQuantity("SELL".equalsIgnoreCase(trans.getLongShort())? -trans.getQuantity():trans.getQuantity());
			trades.add(trade);
			if( originalTrade!= null && !originalTrade.getSecurityCode().equalsIgnoreCase(trade.getSecurityCode())){
				Trade trade1 = new Trade();
				trade1.setTradeId(originalTrade.getTradeId());
				trade1.setSecurityCode(originalTrade.getSecurityCode());
				trade1.setQuantity(0);
				trades.add(trade1);
			}
			return trades;
		}
		return null;
	}


}
