package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    static int id = 0;
    private List<Car> carData = new ArrayList<>();
    public Car create(Car car){
        if (car.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setProductId(uuid.toString());
        }
        carData.add(car);
        return car;
    }
    public Iterator<Car> findAll() {
        return carData.iterator();
    }
    public Car findById(String id) {
        for(Car car : carData) {
            if (car.getProductId().equals(id)) {
                return car;
            }
        }
        return null;
    }
    public Car update(String id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getProductId().equals(id)) {
                car.setProductName(updatedCar.getProductName());
                car.setCarColor(updatedCar.getCarColor());
                car.setProductQuantity(updatedCar.getProductQuantity());
                return car;
            }
        }
        return null;
    }
    public void delete(String id) {
        carData.removeIf(car -> car.getProductId().equals(id));
    }
}
