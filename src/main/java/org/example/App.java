package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.example.model.EdgeModel;
import org.example.model.IndexModel;
import org.example.model.PropertyKeyModel;
import org.example.model.VertexModel;
import org.janusgraph.core.*;

import org.janusgraph.core.schema.JanusGraphManagement;


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

    public static void main( String[] args )
    {
        System.out.println(System.getProperty("FDB_LIBRARY_PATH_FDB_C"));
        JanusGraph graph = JanusGraphFactory.open("src/main/resources/janusgraph-foundationdb.properties");
        ObjectMapper objectMapper = new ObjectMapper();
        graph.close();
//        try {
//            JanusGraphManagement management = graph.openManagement();
//            /* Load propertyKey from json*/
//            PropertyKeyModel[]propertyKeys = objectMapper.readValue(new File("src/main/resources/PropertyKey.json"), PropertyKeyModel[].class);
//            LoadKey(management,propertyKeys);
//
//            management = graph.openManagement();
//
//            /* Load Vertex from json*/
//            VertexModel[]vertexLabels = objectMapper.readValue(new File("src/main/resources/VertexLabels.json"),VertexModel[].class);
//            LoadVertexLabels(management,vertexLabels);
//
//
//            /* Load edge config from json*/
//            management = graph.openManagement();
//            EdgeModel[] edgeModels = objectMapper.readValue(new File("src/main/resources/EdgeLables.json"),EdgeModel[].class);
//            LoadEdgeProperty(management,edgeModels);
//
//            /* Load index from json */
//            IndexModel[] indexModels = objectMapper.readValue(new File("src/main/resources/index.json"),IndexModel[].class);
//            LoadIndices(graph,indexModels);
//
//            /* Load all vertex from csv */
//            management = graph.openManagement();
//            System.out.println(management.printSchema());
//            CSVReader reader = new CSVReader(new FileReader("data/air-routes-latest-nodes.csv"));
//            List<String[]> r = reader.readAll();
//            long startTime = System.currentTimeMillis();
//
//            //load node from csv
//            for(int i = 2;i<r.size();i++){
//                String[] cur = r.get(i);
//                LoadSingleNode(graph,cur);
//            }
//            long nodeFinished = System.currentTimeMillis();
//
//            System.out.printf("Node finished take %f s\n",0.001*(nodeFinished-startTime) );
//            JanusGraphTransaction tx = graph.newTransaction();
////            /* Load edge from json */
//            reader = new CSVReader(new FileReader("data/air-routes-latest-edges.csv"));
//            String[] nextLine;
//            reader.readNext();
//            int count = 0;
//            while ((nextLine = reader.readNext()) != null) {
//                tx = graph.newTransaction();
//                count++;
//                if(count%100==0){
//                    System.out.println("Finished"+count);
//                }
//
//                Vertex vertex = tx.traversal().V()
//                        .has("identity", nextLine[1])
//                        .next();
//                Vertex v2 = tx.traversal().V().has("identity",nextLine[2]).next();
//
//                Edge e =  vertex.addEdge(nextLine[3],v2);
//                e.property("identity",nextLine[0]);
//                e.property("dist",nextLine[4]);
//                tx.commit();
//            }
//            tx = graph.newTransaction();
//            long numberOfVertices = tx.traversal().V().count().next();
//            long numberOfEdge = tx.traversal().E().count().next();
//            tx.commit();
//            long edgeFinished = System.currentTimeMillis();
//            System.out.printf("Edge finished take %f s\n",0.001*(edgeFinished-nodeFinished) );
//            System.out.println("Number of Vertices: " +numberOfVertices);
//            System.out.println("Number of Edge: " +numberOfEdge);
//
//
//
//        } catch (Exception e) {
//            // Handle exceptions
//            e.printStackTrace();
//        } finally {
//            graph.close();
//        }


    }
}
