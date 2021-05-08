package int221.inegrated.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class brand {
  @Id
  @Getter
  @Setter
  private long brandid;
  @Getter
  @Setter
  private String brandname;
  @OneToMany(mappedBy = "brand")
  Set<int221.inegrated.models.product> product;

}
