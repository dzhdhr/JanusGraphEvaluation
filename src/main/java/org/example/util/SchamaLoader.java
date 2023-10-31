package org.example.util;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.example.model.EdgeModel;
import org.example.model.IndexModel;
import org.example.model.PropertyKeyModel;
import org.example.model.VertexModel;
import org.janusgraph.core.*;
import org.janusgraph.core.schema.EdgeLabelMaker;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.core.schema.PropertyKeyMaker;
import org.janusgraph.graphdb.database.management.ManagementSystem;

public class SchamaLoader {
    public static void LoadKey(JanusGraphManagement management, PropertyKeyModel[] keys){

        for (PropertyKeyModel elem:keys){
            PropertyKeyMaker keyMaker = management.makePropertyKey(elem.getName());
            if("SINGLE".equals(elem.getCardinality())){
                keyMaker.cardinality(Cardinality.SINGLE);
            }
            else{
                //TODO create other loader
            }
            if("String".equals(elem.getDataType())){
                keyMaker.dataType(String.class);
            }
            else if("Double".equals(elem.getDataType())){
                keyMaker.dataType(Double.class);
            }
            else if("Integer".equals(elem.getDataType())){
                keyMaker.dataType(Integer.class);
            }
            else {
                //TODO create other loader
            }
            PropertyKey key = keyMaker.make();
        }
        management.commit();
    }
    //TODO write LoadIndices
    public static void LoadIndices(JanusGraph graph, IndexModel[]indexModels) throws InterruptedException {
        JanusGraphManagement management = graph.openManagement();
        for(IndexModel i :indexModels){
            JanusGraphManagement.IndexBuilder indexBuilder;

            if(i.getElementType().equals("vertex")){
                indexBuilder =  management.buildIndex(i.getIndexName(), Vertex.class);
            }
            else{
                indexBuilder =  management.buildIndex(i.getIndexName(), Edge.class);
            }
            if(i.isUnique()){
                indexBuilder.unique();
            }
            for(String elem:i.getPropertyKeys()){
                PropertyKey key = management.getPropertyKey(elem);
                if(key!=null){
                    indexBuilder.addKey(key);
                }
            }
            if(i.getIndexOnly()!=null){
                System.out.println(i.getIndexOnly());
                VertexLabel v = management.getVertexLabel(i.getIndexOnly());
                indexBuilder.indexOnly(v);
            }
            indexBuilder.buildCompositeIndex();

        }
        management.commit();
        for(IndexModel i: indexModels){
            ManagementSystem.awaitGraphIndexStatus(graph,i.getIndexName()).call();
        }

    }
    public static void LoadVertexLabels(JanusGraphManagement management, VertexModel[]vertexLabels){
        for (VertexModel elem : vertexLabels) {
            management.makeVertexLabel(elem.getName()).make();
        }
        management.commit();

    }
    public static void LoadEdgeProperty(JanusGraphManagement management, EdgeModel[]edgeModels){
        for (EdgeModel elem : edgeModels) {
            EdgeLabelMaker maker= management.makeEdgeLabel(elem.getName());
            if(elem.getDirected()){
                maker.directed();
            }
            if (elem.getMultiplicity().equals("MULTI")) {
                maker.multiplicity(Multiplicity.MULTI);
            }
            else{
                //TODO add more Multiplicity
            }
            maker.make();
        }
        management.commit();
    }
//    public static void Load
}