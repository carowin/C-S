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
	private DateLog dateLog;
	private String level;
	private Machine machine;
	private String message;
	private static final long serialVersionUID = 1L;

	public Log() {
		super();
	} 

	public Integer getId() {
		return id;
	}

	public DateLog getDatelog() {
		return dateLog;
	}

	public String getLevel() {
		return level;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	public Machine getMachine() {
		return machine;
	}
	
	public String getNameClasse() {
		return machine.getNom();
	}
	
	public String getMessage() {
		return message;
	}

	public void setDatelog(DateLog dateLog) {
		this.dateLog = dateLog;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", dateLog=" + dateLog + ", level=" + level + ", machine=" + machine + ", message=" + message
				+ "]";
	}



   
}
