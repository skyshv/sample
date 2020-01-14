package com.sample.etl.publisher;

import java.util.Collection;

import com.sample.etl.Trade;

public interface IPublisher {
	public void publishTrade(Trade trades);
}
