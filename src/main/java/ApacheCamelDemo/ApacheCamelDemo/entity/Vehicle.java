package ApacheCamelDemo.ApacheCamelDemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String id;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "model_code", nullable = false)
  private String modelCode;

  @Column(name = "brand_name")
  private String brandName;

  @Override
  public String toString() {
    return "Vehicle{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", modelCode='" + modelCode + '\''
        + ", brandName='" + brandName + '\'' + '}';
  }
}
