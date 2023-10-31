package org.example.util;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphTransaction;

public class NodeLoader {
    private static final String[] col = new String[]{"identity","label","type","code","icao","desc","region","runways","longest","elev","country","city","lat","lon","author","date"};

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