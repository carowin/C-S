package srcs.banque;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import srcs.persistances.Sauvegardable;

public class Compte implements Sauvegardable{
	
	private final String id;
	private double solde;
	
	/**
	 * Constructeur de compte qui initialise l'id du compte et le solde
	 * du compte à partir du flux in 
	 */
	public Compte(InputStream in) throws IOException {
		DataInputStream dos = new DataInputStream(in);
		this.id = dos.readUTF();
		this.solde = dos.readDouble();
	}
	
	public Compte(String id) {
		this.id=id;	
		this.solde=0.0;
	}
		
	public String getId() {
		return id;
	}

	public double getSolde() {
		return solde;
	}

	public void crediter(double montant) {
		solde += montant;
	}

	public void debiter(double montant) {
		solde -= montant;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==this) return true;
		if(o==null) return false;
		if(!(o instanceof Compte)) return false;
		Compte other= (Compte) o;
		return other.id.equals(id);
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	/**
	 * Ecris l'instance dans le flux out
	 */
	public void save(OutputStream out) throws IOException {
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("Compte");
	}
	
}
