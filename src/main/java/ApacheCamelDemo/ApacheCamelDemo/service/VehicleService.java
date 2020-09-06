package ApacheCamelDemo.ApacheCamelDemo.service;


import java.util.List;
import ApacheCamelDemo.ApacheCamelDemo.entity.Vehicle;

public interface VehicleService {
  String addVehicle(Vehicle vehicle);
  Vehicle updateVehicle(Vehicle vehicle);
  List<Vehicle> getAllVehicle();
  Vehicle getVehicle(String modelCode);
  String deleteVehicle(String modelCode);
}
