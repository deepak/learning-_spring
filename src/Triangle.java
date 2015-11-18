public class Triangle {
    private String type;
    private Integer height;
    private String label;

    public Triangle() {
    }

    public Triangle(Integer height) {
        this.height = height;
    }

    public Triangle(String type) {
        System.out.println("constructor called: " + type);
        this.type = type;
    }

    public Triangle(String type, Integer height) {
        System.out.println("constructor called: " + type + " height: " + height);
        this.type = type;
        this.height = height;
    }

    public Triangle(String type, String label) {
        System.out.println("constructor called: " + type + " label: " + label);
        this.type = type;
        this.label = label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Integer getHeight() {
        return height;
    }

    public String getLabel() {
        return label;
    }

    public void draw() {
        System.out.println("draw " + type + " triangle (label: " + label + ") of height " + height);
    }
}
