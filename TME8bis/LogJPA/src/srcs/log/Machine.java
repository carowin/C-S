package srcs.log;

import java.io.Serializable;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Machine
 * Une machine sera caractérisée par un identifiant unique et un nom
 * Il existera une relation 1-N et N-1 entre les deux tables.
 */
@Entity
@NamedQuery(name="Machine.findAll", query="SELECT m FROM Machine m")
public class Machine implements Serializable {

	   
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Collection<Log> logs;
	private static final long serialVersionUID = 1L;

	public Machine() {
		super();
		logs = new ArrayList<Log>();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy="machine")
	public Collection<Log> getLogs() {
		return logs;
	}
	
	public void setLogs(Collection<Log> logs) {
		this.logs = logs;
	}
	
	@Override
	public String toString() {
		return "Machine [id=" + id + ", name=" + name + "]";
	}
	

	
   
}
