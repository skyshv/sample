package com.sample.etl;

import com.sample.etl.enricher.CancelEnricher;
import com.sample.etl.enricher.NewEnricher;
import com.sample.etl.enricher.UpdateEnricher;
import com.sample.etl.loader.FileLoader;
import com.sample.etl.processor.ETLProcessor;
import com.sample.etl.publisher.ConsolePublisher;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String []args){
        FileLoader fileLoader = new FileLoader();
        ETLProcessor etlProcessor = new ETLProcessor();
        ConsolePublisher consolePublisher = new ConsolePublisher();

        NewEnricher newEnricher = new NewEnricher();
        UpdateEnricher updateEnricher = new UpdateEnricher();
        CancelEnricher cancelEnricher = new CancelEnricher();


        etlProcessor.setEnrichers(Arrays.asList(newEnricher, updateEnricher, cancelEnricher));
        etlProcessor.setPublishers(Collections.singletonList(consolePublisher));

        fileLoader.RegisterProcessor(etlProcessor);

        try {
            fileLoader.loadFile("transaction.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
