package org.example.model;
import lombok.Data;

import java.util.List;
@Data
public class IndexModel {
    private String indexName;
    private String elementType;
    private List<String> propertyKeys;
    private String indexOnly;
    private boolean unique;

    // Getters and Setters for the fields


}