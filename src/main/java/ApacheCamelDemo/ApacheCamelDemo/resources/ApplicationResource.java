package ApacheCamelDemo.ApacheCamelDemo.resources;

import java.util.List;
import java.util.function.Supplier;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import ApacheCamelDemo.ApacheCamelDemo.entity.Vehicle;
import ApacheCamelDemo.ApacheCamelDemo.processors.AddVehicleProcessor;
import ApacheCamelDemo.ApacheCamelDemo.processors.DeleteVehicleProcessor;
import ApacheCamelDemo.ApacheCamelDemo.processors.GetVehicleProcessor;
import ApacheCamelDemo.ApacheCamelDemo.processors.UpdateVehicleProcessor;
import ApacheCamelDemo.ApacheCamelDemo.service.VehicleService;

@Component
public class ApplicationResource extends RouteBuilder {

  @Autowired
  private VehicleService vehicleService;
  @BeanInject
  private AddVehicleProcessor addVehicleProcessor;
  @BeanInject
  private GetVehicleProcessor getVehicleProcessor;
  @BeanInject
  private UpdateVehicleProcessor updateVehicleProcessor;
  @BeanInject
  private DeleteVehicleProcessor deleteVehicleProcessor;

  @Override
  public void configure() throws Exception {
    restConfiguration().component("servlet")
        .port(8080)
        .host("localhost")
        .bindingMode(RestBindingMode.json);

    rest().get("/vehicle/{modelCode}").produces(MediaType.APPLICATION_JSON_VALUE).route().process(getVehicleProcessor);

    rest().get("/vehicle/all").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(allVehicleSupplier);

    rest().post("/vehicle/add").consumes(MediaType.APPLICATION_JSON_VALUE)
        .type(Vehicle.class).outType(String.class).route().process(addVehicleProcessor);

    rest().put("/vehicle/update").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
        .type(Vehicle.class).outType(Vehicle.class).route().process(updateVehicleProcessor);

    rest().delete("/vehicle/{modelCode}").produces(MediaType.APPLICATION_JSON_VALUE).route()
        .process(deleteVehicleProcessor);
  }

  private Supplier<List<Vehicle>> allVehicleSupplier = () -> vehicleService.getAllVehicle();

}

// Lambda Implementation
//  @Override
//  public void configure() throws Exception {
//    restConfiguration().component("servlet").port(8080).host("localhost").bindingMode(RestBindingMode.json);
//
//    rest().get("/vehicle/{modelCode}").produces(MediaType.APPLICATION_JSON_VALUE).route().process(exchange -> {
//      exchange.getIn().setBody(vehicleService.getVehicle(exchange.getIn().getHeader("modelCode").toString()));
//    });
//
//    rest().get("/vehicle/all").produces(MediaType.APPLICATION_JSON_VALUE).route()
//        .setBody(() -> vehicleService.getAllVehicle());
//
//    rest().post("/vehicle/add").consumes(MediaType.APPLICATION_JSON_VALUE).type(Vehicle.class).outType(String.class)
//        .route().process(exchange -> {
//      vehicleService.addVehicle(exchange.getIn().getBody(Vehicle.class));
//    });
//
//    rest().put("/vehicle/update").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
//        .type(Vehicle.class).outType(Vehicle.class).route().process(exchange -> {
//      exchange.getIn().setBody(vehicleService.updateVehicle(exchange.getIn().getBody(Vehicle.class)));
//    });
//
//    rest().delete("/vehicle/{modelCode}").produces(MediaType.APPLICATION_JSON_VALUE).route().process(exchange -> {
//      exchange.getIn().setBody(vehicleService.deleteVehicle(exchange.getIn().getHeader("modelCode").toString()));
//    });
//  }
//