package org.example.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphTransaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EdgeLoader {
    public static void LoadAllEdge(JanusGraph graph, String edgeFile) throws IOException, CsvValidationException {
        long startTime = System.currentTimeMillis();
        CSVReader reader = new CSVReader(new FileReader(edgeFile));
        JanusGraphTransaction tx = graph.newTransaction();
        /* Load edge from json */
        String[] nextLine;
        reader.readNext();
        while ((nextLine = reader.readNext()) != null) {
            tx = graph.newTransaction();
            Vertex vertex = tx.traversal().V()
                    .has("identity", nextLine[1])
                    .next();
            Vertex v2 = tx.traversal().V().has("identity",nextLine[2]).next();

            Edge e =  vertex.addEdge(nextLine[3],v2);
            e.property("identity",nextLine[0]);
            e.property("dist",nextLine[4]);
            tx.commit();

        }
        long edgeFinished = System.currentTimeMillis();
        System.out.printf("Edge finished take %f s\n",0.001*(edgeFinished-startTime) );
    }
}