package com.sample.etl.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sample.etl.Trade;
import com.sample.etl.TradeCacheUtil;
import com.sample.etl.Transaction;
import com.sample.etl.enricher.*;
import com.sample.etl.publisher.*;

public class ETLProcessor implements IProcessor{
	private List<IEnricher> enrichers = new ArrayList<>();
	private List<IPublisher> publishers = new ArrayList<>();

	public List<IEnricher> getEnrichers() {
		return enrichers;
	}

	public List<IPublisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<IPublisher> publishers) {
		this.publishers = publishers;
	}

	public void setEnrichers(List<IEnricher> enrichers) {
		this.enrichers = enrichers;
	}

	@Override
	public void processTransaction(Collection<Transaction> trans) {
		// TODO Auto-generated method stub
		for(Transaction t: trans){
			for(IEnricher e: enrichers){
				List<Trade> trades = e.HandleTransaction(t);
				if( trades != null ){
					for( Trade td: trades) {
						TradeCacheUtil.CacheTrade(td);
						publishers.stream().forEach(publisher -> publisher.publishTrade(td));
					}
				}
			}
		}
		
	}
	
	
}
