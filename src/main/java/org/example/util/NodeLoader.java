package org.example.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphTransaction;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NodeLoader {
    private static final String[] col = new String[]{"identity","label","type","code","icao","desc","region","runways","longest","elev","country","city","lat","lon","author","date"};

    /**
     * @description: method for loading all nodes
     * @param graph target graph
     * @param nodeFile file of node info
     * */
    public static void LoadAllNode(JanusGraph graph,String nodeFile) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(nodeFile));
        List<String[]> r = reader.readAll();
        long startTime = System.currentTimeMillis();

        //load node from csv
        for(int i = 2;i<r.size();i++){
            String[] cur = r.get(i);
            LoadSingleNode(graph,cur);
        }
        long nodeFinished = System.currentTimeMillis();
        System.out.printf("Node finished take %f s\n",0.001*(nodeFinished-startTime) );
    }
    /**
     * @description: method for loading single node
     * @param graph target graph
     * @param cur string[] for node info including all value for col
     * */
    public static void LoadSingleNode(JanusGraph graph, String []cur){
        JanusGraphTransaction tx = graph.newTransaction();

        Vertex v  = tx.addVertex(cur[1]);
        for(int j = 0;j<cur.length;j++){
            if(!cur[j].isEmpty()&&j<col.length&&j!=1){
                String propertyName = col[j];
                if(propertyName.equals("runways")||propertyName.equals("longest")||propertyName.equals("elev")){
                    v.property(propertyName,Integer.parseInt(cur[j]));
                }
                else if(propertyName.equals("lat")||propertyName.equals("lon")){
                    v.property(propertyName,Double.parseDouble(cur[j]));
                }
                else{
                    v.property(propertyName,cur[j]);
                }
            }
        }
        tx.commit();
    }
}