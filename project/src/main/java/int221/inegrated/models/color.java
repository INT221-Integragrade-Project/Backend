package int221.inegrated.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class color {
  @Id
  @Getter
  @Setter
  private long colorid;
  @Getter
  @Setter
  private String colorname;
  @Getter
  @Setter
  private String colorcode;
  @OneToMany(mappedBy = "color")
  Set<int221.inegrated.models.productcolor> productcolor;

}
