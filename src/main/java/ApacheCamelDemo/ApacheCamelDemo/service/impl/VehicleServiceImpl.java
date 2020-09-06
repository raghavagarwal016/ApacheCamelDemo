package ApacheCamelDemo.ApacheCamelDemo.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApacheCamelDemo.ApacheCamelDemo.entity.Vehicle;
import ApacheCamelDemo.ApacheCamelDemo.repository.VehicleRepository;
import ApacheCamelDemo.ApacheCamelDemo.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;

  @PostConstruct
  public void initDb() {
    IntStream.range(1, 21)
        .mapToObj(
            i -> new Vehicle(UUID.randomUUID().toString(),
                "TYPE" + i, "MODEL_CODE" + i, "BRAND" + i))
        .forEach(vehicle -> addVehicle(vehicle));
  }

  @Override
  public String addVehicle(Vehicle vehicle) {
    Vehicle savedVehicle = vehicleRepository.save(vehicle);
    return savedVehicle.getId();
  }


  @Override
  @Transactional
  public Vehicle updateVehicle(Vehicle vehicle) {
    Vehicle savedVehicle = vehicleRepository.getOne(getVehicle(vehicle.getModelCode()).getId());
    savedVehicle.setModelCode(vehicle.getModelCode());
    savedVehicle.setType(vehicle.getType());
    savedVehicle.setBrandName(vehicle.getBrandName());
    vehicleRepository.save(savedVehicle);
    return savedVehicle;
  }

  @Override
  public List<Vehicle> getAllVehicle() {
    return vehicleRepository.findAll();
  }

  @Override
  public Vehicle getVehicle(String modelCode) {
    return vehicleRepository.findByModelCode(modelCode);
  }

  @Override
  public String deleteVehicle(String modelCode) {
    Vehicle savedVehicle = getVehicle(modelCode);
    vehicleRepository.deleteById(savedVehicle.getId());
    return savedVehicle.getId();
  }
}
