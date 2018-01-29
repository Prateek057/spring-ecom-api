package com.ms.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Category {

    @Id
    @NotNull
    private String id;
    private String name;
    private String parent;
    private String slug;
    private List<Ancestor> ancestors;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}