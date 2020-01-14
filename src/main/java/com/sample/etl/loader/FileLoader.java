package com.sample.etl.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import com.sample.etl.Transaction;
import com.sample.etl.processor.IProcessor;

public class FileLoader implements ILoader {

	public void loadFile(String filename) throws Exception {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
		String transLine = null;
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		while( (transLine = bufferedReader.readLine() )!=null ){
			Transaction transaction = Transaction.parseTransaction(transLine);
			if( transaction != null ){
				transactions.add(transaction);
			}
		}
		IncomingEvent(transactions);
	}
	
	private IProcessor transactionProcessor;
	
	public void IncomingEvent(Collection<Transaction> trans) {
		transactionProcessor.processTransaction(trans);
		
	}

	public void RegisterProcessor(IProcessor proc) {
		// TODO Auto-generated method stub
		if( proc != null ){
			this.transactionProcessor = proc;
		}
	}
}
