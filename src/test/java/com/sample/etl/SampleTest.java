package com.sample.etl;

import com.sample.etl.enricher.CancelEnricher;
import com.sample.etl.enricher.NewEnricher;
import com.sample.etl.enricher.UpdateEnricher;
import com.sample.etl.loader.FileLoader;
import com.sample.etl.processor.ETLProcessor;
import com.sample.etl.publisher.ConsolePublisher;
import com.sample.etl.publisher.IPublisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)


public class SampleTest {
    @Test
    public void testTransactions(){
        FileLoader fileLoader = new FileLoader();
        ETLProcessor etlProcessor = new ETLProcessor();
        ConsolePublisher consolePublisher = new ConsolePublisher();

        NewEnricher newEnricher = new NewEnricher();
        UpdateEnricher updateEnricher = new UpdateEnricher();
        CancelEnricher cancelEnricher = new CancelEnricher();

        etlProcessor.setEnrichers(Arrays.asList(newEnricher, updateEnricher, cancelEnricher));
        MapPublisher mapPublisher = new MapPublisher();
        etlProcessor.setPublishers(Arrays.asList(consolePublisher, mapPublisher));

        fileLoader.RegisterProcessor(etlProcessor);

        try {
            fileLoader.loadFile("transaction.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(mapPublisher.getV().get("REL").longValue()==60);
        Assert.assertTrue(mapPublisher.getV().get("ITC").longValue() == 0);
        Assert.assertTrue(mapPublisher.getV().get("INF").longValue() == 50);
    }

    @Test(timeout = 2)
    public void testPerformance(){
        FileLoader fileLoader = new FileLoader();
        ETLProcessor etlProcessor = new ETLProcessor();
        ConsolePublisher consolePublisher = new ConsolePublisher();

        NewEnricher newEnricher = new NewEnricher();
        UpdateEnricher updateEnricher = new UpdateEnricher();
        CancelEnricher cancelEnricher = new CancelEnricher();

        etlProcessor.setEnrichers(Arrays.asList(newEnricher, updateEnricher, cancelEnricher));
        MapPublisher mapPublisher = new MapPublisher();
        etlProcessor.setPublishers(Arrays.asList(consolePublisher, mapPublisher));

        fileLoader.RegisterProcessor(etlProcessor);

        try {
            for( int i = 0; i < 100000; i++ ) {
                fileLoader.loadFile("transaction.txt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static class MapPublisher implements IPublisher{
        private Map<String, Long> v = new HashMap<>();
        @Override
        public void publishTrade(Trade trades) {
            System.out.println("MapPb" + trades);
            v.put(trades.getSecurityCode(), TradeCacheUtil.getPostion(trades.getSecurityCode()));
        }

        public Map<String, Long> getV() {
            return v;
        }

        public void setV(Map<String, Long> v) {
            this.v = v;
        }
    }
}
