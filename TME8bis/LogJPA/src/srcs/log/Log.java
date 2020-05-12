package srcs.log;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Log
 *
 */
@Entity

public class Log implements Serializable {

	   
	@Id
	@GeneratedValue
	private Integer id;
	@Embedded
	private DateLog date;
	private static final long serialVersionUID = 1L;

	public Log() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public DateLog getDate() {
		return this.date;
	}

	public void setDate(DateLog date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", date=" + date + "]";
	}
   
}
