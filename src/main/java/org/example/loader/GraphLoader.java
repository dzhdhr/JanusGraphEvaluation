package org.example.loader;

public interface GraphLoader {
    void loadScheme();
    void loadEdge(String EdgeFile);
    void loadNode(String NodeFile);
    void printScheme();
    void close();
    void getStatus();
}
