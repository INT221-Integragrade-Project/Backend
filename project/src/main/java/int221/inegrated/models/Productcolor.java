package int221.inegrated.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Productcolor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private long productcolorid;
  @Getter
  @Setter
  private long productid;
  @Getter
  @Setter
  private long colorid;
  @ManyToOne
  @JoinColumn(name = "productid",updatable = false,insertable = false)
  private Product product;
  @ManyToOne
  @JoinColumn(name = "colorid",insertable = false,updatable = false)
  private Color color;

}
