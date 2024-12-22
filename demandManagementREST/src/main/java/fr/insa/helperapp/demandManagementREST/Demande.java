package fr.insa.helperapp.demandManagementREST;

public class Demande {
    private int id;
    private String name;
    private String description;
    private String author;
    private String status;
    private String resolver;

    // Constructeur sans argument (par défaut)
    public Demande() {
    }

    public Demande(int id, String name, String description, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.status = "en attente"; // Statut par défaut
        this.resolver = null; // Pas de resolver initialement
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }
}
