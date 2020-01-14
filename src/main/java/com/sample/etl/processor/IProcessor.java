package com.sample.etl.processor;

import java.util.Collection;

import com.sample.etl.Transaction;

public interface IProcessor {
	void processTransaction(Collection<Transaction> trans);
}
