package org.example.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.example.model.EdgeModel;
import org.example.model.IndexModel;
import org.example.model.PropertyKeyModel;
import org.example.model.VertexModel;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.JanusGraphTransaction;
import org.janusgraph.core.schema.JanusGraphManagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.example.util.EdgeLoader.LoadAllEdge;
import static org.example.util.NodeLoader.LoadAllNode;
import static org.example.util.NodeLoader.LoadSingleNode;
import static org.example.util.SchamaLoader.*;
import static org.example.util.SchamaLoader.LoadIndices;

public class BerkeleyDBLoader implements GraphLoader{
    private final JanusGraph graph;
    ObjectMapper objectMapper ;
    public BerkeleyDBLoader(){
       objectMapper = new ObjectMapper();
       graph = JanusGraphFactory.open("src/main/resources/berkeleydb.properties");
    }
    @Override
    public void loadScheme() {
        try {
            JanusGraphManagement management = graph.openManagement();
            /* Load propertyKey from json*/
            PropertyKeyModel[]propertyKeys = objectMapper.readValue(new File("src/main/resources/PropertyKey.json"), PropertyKeyModel[].class);
            LoadKey(management,propertyKeys);

            management = graph.openManagement();

            /* Load Vertex from json*/
            VertexModel[]vertexLabels = objectMapper.readValue(new File("src/main/resources/VertexLabels.json"),VertexModel[].class);
            LoadVertexLabels(management,vertexLabels);


            /* Load edge config from json*/
            management = graph.openManagement();
            EdgeModel[] edgeModels = objectMapper.readValue(new File("src/main/resources/EdgeLables.json"),EdgeModel[].class);
            LoadEdgeProperty(management,edgeModels);

            /* Load index from json */
            IndexModel[] indexModels = objectMapper.readValue(new File("src/main/resources/index.json"),IndexModel[].class);
            LoadIndices(graph,indexModels);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadEdge(String edgeFile) {
        try {
            LoadAllEdge(graph,edgeFile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadNode(String nodeFile) {
        try {
            LoadAllNode(graph,nodeFile);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printScheme() {
        JanusGraphManagement management = graph.openManagement();
        System.out.println(management.printSchema());
    }

    @Override
    public void close() {
        this.graph.close();
    }

    @Override
    public void getStatus() {
        JanusGraphTransaction tx = graph.newTransaction();
        long numberOfVertices = tx.traversal().V().count().next();
        long numberOfEdge = tx.traversal().E().count().next();
        tx.commit();
        System.out.println("Number of Vertices: " +numberOfVertices);
        System.out.println("Number of Edge: " +numberOfEdge);
    }
}
