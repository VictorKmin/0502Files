import java.io.Serializable;

public class Car implements Serializable {
    int id;
    String model;

    public Car() {
    }

    public Car(int id, String model) {
        this.id = id;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
