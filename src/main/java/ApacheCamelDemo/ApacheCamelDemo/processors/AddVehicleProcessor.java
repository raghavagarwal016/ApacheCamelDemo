package ApacheCamelDemo.ApacheCamelDemo.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ApacheCamelDemo.ApacheCamelDemo.entity.Vehicle;
import ApacheCamelDemo.ApacheCamelDemo.service.VehicleService;

@Component
public class AddVehicleProcessor implements Processor {

  @Autowired
  private VehicleService vehicleService;

  @Override
  public void process(Exchange exchange) throws Exception {
    vehicleService.addVehicle(exchange.getIn().getBody(Vehicle.class));
  }

}
