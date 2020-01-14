package com.sample.etl;

public class Trade {
	private long tradeId;
	private String securityCode;
	private long quantity;

	public long getTradeId() {
		return tradeId;
	}

	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Trade{" +
				"tradeId=" + tradeId +
				", securityCode='" + securityCode + '\'' +
				", quantity=" + quantity +
				'}';
	}
}
