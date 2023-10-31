package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.example.loader.BerkeleyDBLoader;
import org.example.loader.GraphLoader;
import org.example.loader.MemoryLoader;
import org.example.model.EdgeModel;
import org.example.model.IndexModel;
import org.example.model.PropertyKeyModel;
import org.example.model.VertexModel;
import org.janusgraph.core.*;

import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.util.system.ConfigurationUtil;


import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.example.util.NodeLoader.LoadSingleNode;
import static org.example.util.SchamaLoader.*;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) {

        GraphLoader graphLoader = new BerkeleyDBLoader();
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
