package com.sample.etl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TradeCacheUtil {
    private static ConcurrentHashMap<Long, Trade> trades = new ConcurrentHashMap<Long, Trade>();
    private static ConcurrentHashMap<String, Map<Long, Trade>> positions = new ConcurrentHashMap<>();
    public static Trade getOriginalTrade(long tradeId) { return trades.get(tradeId); }
    public static void CacheTrade(Trade t) {
        trades.put(t.getTradeId(), t);
        positions.computeIfAbsent(String.valueOf(t.getSecurityCode()), e -> new HashMap<>())
                .put(t.getTradeId(), t);
    }

    public static long getPostion(String securityCode){
        Map<Long, Trade> longTradeMap = positions.get(securityCode);
        if( longTradeMap == null ) return 0;
        long sum = 0;
        for( Trade t: longTradeMap.values()){
            sum += t.getQuantity();
        }
        return sum;
    }
}
