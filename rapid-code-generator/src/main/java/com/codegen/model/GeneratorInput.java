package com.codegen.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneratorInput {

    private String className;
    private List<Field> fields;

    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    
    public static class Field {
    	@JsonProperty("name")
        private String name;
    	@JsonProperty("type")
        private String type;
    	@JsonProperty("primary")
        private Boolean primary; 

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getPrimary() {  
            return primary;
        }

        public void setPrimary(Boolean primary) {  
            this.primary = primary;
        }
    }

}
