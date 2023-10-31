package org.example.model;

import groovy.transform.ToString;
import lombok.Data;
/***
 * Class Description: PropertyKey entity
 */
@Data
public class PropertyKeyModel {
    String name;
    String dataType;
    String cardinality;


}