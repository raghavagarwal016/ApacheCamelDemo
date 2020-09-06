package ApacheCamelDemo.ApacheCamelDemo.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import ApacheCamelDemo.ApacheCamelDemo.service.VehicleService;

@Component
public class DeleteVehicleProcessor implements Processor {

  @Autowired
  private VehicleService vehicleService;

  @Override
  public void process(Exchange exchange) throws Exception {
    exchange.getIn().setBody(vehicleService.deleteVehicle(exchange.getIn().getHeader("modelCode").toString()));
  }
}
