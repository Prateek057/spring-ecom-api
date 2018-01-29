package com.ms.models;

import org.springframework.data.annotation.Id;

class Ancestor {

    @Id
    private String _id;
    private String name;
    private String slug;

    public Ancestor() {}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


}
