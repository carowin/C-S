package srcs.persistances;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import srcs.banque.Compte;

public class PersistanceTools {

	/**
	 * Sauvegarde l'ensemble des entiers d'un tableau dans un fichier
	 * Parcours du tableau puis ecriture dans le fichier
	 */
	public static void saveArrayInt(String f, int[] tab) throws IOException{
		//Creates a file output stream to write to the file with the specified name.
		FileOutputStream os = new FileOutputStream(f);
		for(int i=0; i<tab.length; i++) {
			os.write(tab[i]);
 		}
		os.close();
	}
	
	/**
	 * Enregistre dans un tableau les eléments contenant dans le fichier
	 * 2 input: -1 pour compter le nb d'element dans le tableau
	 * 			-1 pour lire ces éléments
	 */
	public static int[] loadArrayInt(String fichier) throws IOException {
		//Creates a FileInputStream by opening a connection to an actual file
		FileInputStream is = new FileInputStream(fichier);
		FileInputStream is2 = new FileInputStream(fichier);
		int i=0;
		int cpt = 0;
		int entier;
		
		while(is.read()!=-1) {
			cpt ++;
		}
		
		int []tab = new int[cpt];
		System.out.println("CPT = " + cpt + "\n");
		is.close();
		
		while((entier=is2.read()) !=-1) {
			System.out.println(" ENTIER = "+entier + "\n");
			tab[i] = entier;
			System.out.println(tab[i]+ "\n");
			i++;
		}
		is2.close();
		return tab;
	}
	
	/**
	 * Sauvegarde les données d'un compte dans un fichier (id && solde)
	 * 
	 * BUT de Data(Out/In)putStream => permet de recevoir et d'envoyer des données 
	 * de type primitif(int,long..)
	 * DataOutputStream(OutputStream out) / DataInputStream(InputStream in)
	 */
	public static void saveCompte(String f, Compte e) throws IOException{
		FileOutputStream isf = new FileOutputStream(f);
		DataOutputStream dos = new DataOutputStream(isf);
		dos.writeUTF(e.getId());
		dos.writeDouble(e.getSolde());
	}
	
	/**
	 * Instancie un Compte à partir des données du fichier format: string double
	 */
	public static Compte loadCompte(String f)throws IOException{
		return new Compte(new FileInputStream(f));
	}
	
	/**
	 * sauvegarder dans un fichier un objet Sauvegardable
	 * getClass() => recup instance de la classe
	 * getCanonicalName() => recup le nom de cette classe
	 */
	public void save(String fichier, Sauvegardable s) throws IOException {
		FileOutputStream os = new FileOutputStream(fichier);
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF(s.getClass().getCanonicalName());
		s.save(dos);
	}
	
	/**
	 *  instancie un Sauvegardable à partir des données d’un fichier
	 *  recupere le nom de la classe 
	 *  forName(string) => renvoie un objet de la classe à partir d'un string
	 *  asSubClass(Class) => représente une sous-classe de la classe représentée par l'objet de classe spécifié
	 *  getConstructor(parameters) => retourne un constructeur
	 */
	public Sauvegardable load(String fichier) throws IOException, ClassNotFoundException, InstantiationException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		FileInputStream is = new FileInputStream(fichier);
		DataInputStream dos = new DataInputStream(is);
		String name = dos.readUTF();
		Class<?> classe = Class.forName(name).asSubclass(Sauvegardable.class);
		
		
		return (Sauvegardable) classe.getConstructor(InputStream.class).newInstance(dos);
	}
	
	
	

	
}