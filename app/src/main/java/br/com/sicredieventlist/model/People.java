package br.com.sicredieventlist.model;

public class People {

    private Long id;
    private Long idEnvet;
    private String name;
    private String picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEnvet() {
        return idEnvet;
    }

    public void setIdEnvet(Long idEnvet) {
        this.idEnvet = idEnvet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}