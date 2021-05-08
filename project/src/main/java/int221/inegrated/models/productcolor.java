package int221.inegrated.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class productcolor {
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
  private int221.inegrated.models.product product;
  @ManyToOne
  @JoinColumn(name = "colorid",insertable = false,updatable = false)
  private int221.inegrated.models.color color;
}
