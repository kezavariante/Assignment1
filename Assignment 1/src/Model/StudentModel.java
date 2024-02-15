package Model;

public class StudentModel {
    private int id;
    private String name;

    // Constructors
    public StudentModel() {
        // Default constructor
    }

    public StudentModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudentModel(String name) {
        this.name = name;
    }

    // Getters and setters
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
}
