package ApacheCamelDemo.ApacheCamelDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ApacheCamelDemo.ApacheCamelDemo.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
  Vehicle findByModelCode(String modelCode);
}
