package srcs.log;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embeddable;

@Embeddable
public class DateLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int année;
	private int mois;
	private int jour;
	private int heure;
	private int minutes;
	private int secondes;
	private int millisecondes;
	
	
	
	public DateLog() {
		super();
		this.année = Calendar.getInstance().get(Calendar.YEAR);
		this.mois = Calendar.getInstance().get(Calendar.MONTH);
		this.jour = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		this.heure = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		this.minutes = Calendar.getInstance().get(Calendar.MINUTE);
		this.secondes = Calendar.getInstance().get(Calendar.SECOND);
		this.millisecondes = Calendar.getInstance().get(Calendar.MILLISECOND);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + année;
		result = prime * result + heure;
		result = prime * result + jour;
		result = prime * result + millisecondes;
		result = prime * result + minutes;
		result = prime * result + mois;
		result = prime * result + secondes;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateLog other = (DateLog) obj;
		if (année != other.année)
			return false;
		if (heure != other.heure)
			return false;
		if (jour != other.jour)
			return false;
		if (millisecondes != other.millisecondes)
			return false;
		if (minutes != other.minutes)
			return false;
		if (mois != other.mois)
			return false;
		if (secondes != other.secondes)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "DateLog [année=" + année + ", mois=" + mois + ", jour=" + jour + ", heure=" + heure + ", minutes="
				+ minutes + ", secondes=" + secondes + ", millisecondes=" + millisecondes + "]";
	}
	
	
}
