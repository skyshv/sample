package com.sample.etl.loader;

import java.util.Collection;

import com.sample.etl.Transaction;
import com.sample.etl.processor.IProcessor;

public interface ILoader {
	public void IncomingEvent(Collection<Transaction> trans);
	public void RegisterProcessor(IProcessor proc);
}
