package srcs.log;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Machine
 *
 */
@Entity

public class Machine implements Serializable {

	   
	@Id
	@GeneratedValue
	private Integer id;
	private static final long serialVersionUID = 1L;

	public Machine() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Machine [id=" + id + "]";
	}
	
   
}
