package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.apache.commons.configuration2.PropertiesConfiguration;


import org.example.loader.GraphLoader;
import org.example.loader.MemoryLoader;



public class App 
{

    public static void main( String[] args ) {

        GraphLoader graphLoader = new MemoryLoader();
        try {
            graphLoader.loadScheme();
            graphLoader.printScheme();
            graphLoader.loadNode("data/air-routes-latest-nodes.csv");
            graphLoader.loadEdge("data/air-routes-latest-edges.csv");
            graphLoader.getStatus();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            graphLoader.close();
        }


    }
}
