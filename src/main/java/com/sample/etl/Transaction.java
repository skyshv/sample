package com.sample.etl;

public class Transaction {
	private long transactionID;
	private long tradeID;
	private int version;
	private String SecurityCode;
	private long quantity;
	private String action;
	private String longShort;
	
	public static Transaction parseTransaction(String tranStr) throws Exception {
		if(tranStr == null ) {
			throw new Exception("wrong format of transaction. " + tranStr);
			
		}
		String []vals = tranStr.split(",");
		if( vals.length != 7 ) {
			throw new Exception("wrong fields of transaction. " + tranStr);
		}

		Transaction transaction = new Transaction();
		transaction.transactionID = Long.parseLong(vals[0]);
		transaction.tradeID = Long.parseLong(vals[1]);
		transaction.version = Integer.parseInt(vals[2]);
		transaction.SecurityCode = vals[3];
		transaction.quantity = Long.parseLong(vals[4]);
		transaction.action = vals[5];
		transaction.longShort = vals[6];
		return transaction;
	}

	public long getTransactionID() {
		return transactionID;
	}

	public long getTradeID() {
		return tradeID;
	}

	public int getVersion() {
		return version;
	}

	public String getSecurityCode() {
		return SecurityCode;
	}

	public long getQuantity() {
		return quantity;
	}

	public String getAction() {
		return action;
	}

	public String getLongShort() {
		return longShort;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transactionID=" + transactionID +
				", tradeID=" + tradeID +
				", version=" + version +
				", SecurityCode='" + SecurityCode + '\'' +
				", quantity=" + quantity +
				", action='" + action + '\'' +
				", longShort='" + longShort + '\'' +
				'}';
	}
}
