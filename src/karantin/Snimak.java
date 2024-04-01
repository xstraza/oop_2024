package karantin;

public class Snimak {
    private String name;

    public Snimak(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void prikazi() {
        System.out.println(name);
    }
}
