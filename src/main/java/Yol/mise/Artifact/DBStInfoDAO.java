import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "station_info")
public class DBStInfoDAO implements Serializable{
  public DBStInfoDATO() {}
  
   @Id @GenerataeValue(strategy = GenerationType.IDENTITY)
   @Column(name = "station_name", updatable = true, nullable = false)
   public String station_name;
   
   @Column(name = "station_location", updatable = true, nullable = false)
   public String station_location;
   
   @Column(name = "station_x", updatable = true, nullable = true)
   public float station_x;
   
   @Column(name = "station_y", updatable = true, nallable = true)
   public float station_y;
   
}
  
