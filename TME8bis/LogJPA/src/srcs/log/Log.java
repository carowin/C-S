package srcs.log;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Log
 *
 */
@Entity
@NamedQuery(name="Log.findAll", query="SELECT l FROM Log l")
public class Log implements Serializable {

	   
	@Id
	@GeneratedValue
	private Integer id;
	@Embedded
	private DateLog date;
	private String level;
	private Machine machine;
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
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", date=" + date + ", level=" + level + "]";
	}


   
}
