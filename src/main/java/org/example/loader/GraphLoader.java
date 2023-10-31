package org.example.loader;

/**
 * Interface for for graph loader worker
 */
public interface GraphLoader {
    /**
     * @apiNote Method for loading scheme
    * */
    void loadScheme();
    /**
     * @apiNote Method for loading edge
     * @param EdgeFile File for edge info
     * */
    void loadEdge(String EdgeFile);
    /**
     * @apiNote Method for loading Node
     * @param NodeFile file for node info
     * */
    void loadNode(String NodeFile);
    /**
     * @apiNote Method for printing scheme
     * */
    void printScheme();
    /**
     * @apiNote Method for closing graph
     * */
    void close();
    /**
     * @apiNote Method for printing status
     * */
    void getStatus();
}
