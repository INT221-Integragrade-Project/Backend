package int221.inegrated.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private long productid;
  @Getter
  @Setter
  private String productname;
  @Getter
  @Setter
  private double price;
  @Getter
  @Setter
  private long warranty;
  @Getter
  @Setter
  private java.sql.Date menufacturrerdate;
  @Getter
  @Setter
  private long capacity;
  @Getter
  @Setter
  private String description;
  @Getter
  @Setter
  private String images;
  @Getter
  @Setter
  private long brandid;
  @ManyToOne
  @JoinColumn(name = "brandid",insertable = false,updatable = false)
  private Brand brand;
  @OneToMany(mappedBy = "product")
  Set<Productcolor> productcolor;

}
