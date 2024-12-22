package fr.insa.helperapp.catalogDemandMS.model;

public class demandModel {
    private Long id;
    private String name;
    private String description;
    private String author; 

    public demandModel(Long id, String name, String description, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
