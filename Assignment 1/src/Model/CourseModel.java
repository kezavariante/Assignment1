package Model;

public class CourseModel {
    private int id;
    private String name;

    // Constructors
    public CourseModel() {
        // Default constructor
    }

    public CourseModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseModel(String name) {
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
