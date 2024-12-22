package fr.insa.helperapp.whoIsTheResolverMS.model;

public class Resolver {
    private int id;
    private String resolver;

    // Constructeur avec paramètres pour initialiser id et resolver
    public Resolver(int id, String resolver) {
        this.id = id;
        this.resolver = resolver;
    }
    
    public Resolver() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getResolver() {
        return resolver;
    }
    
    public void setResolver(String resolver) {
        this.resolver = resolver;
    }
}
