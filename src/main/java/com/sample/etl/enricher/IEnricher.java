package com.sample.etl.enricher;

import com.sample.etl.Trade;
import com.sample.etl.Transaction;
import com.sample.etl.processor.IProcessor;

import java.util.List;

public interface IEnricher {
	public List<Trade> HandleTransaction(Transaction trans);
}
