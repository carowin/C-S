package srcs.persistances;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import srcs.banque.Compte;

public class PersistanceTools {

	/**
	 * Sauvegarde l'ensemble des entiers de tab dans le fichier f
	 * @throws IOException 
	 */
	public static void saveArrayInt(String f, int[] tab) throws IOException{
		FileOutputStream os = new FileOutputStream(f);
		//OutputStream out = new OutputStream(new FileOutputStream (f));
		for(int i=0; i<tab.length; i++) {
			os.write(tab[i]);
 		}
		os.close();
	}
	
	/**
	 * charge à partir du fichier passé en param un tableau d'entier
	 * @throws FileNotFoundException 
	 */
	public static int[] loadArrayInt(String fichier) throws IOException {
		FileInputStream is = new FileInputStream(fichier);
		FileInputStream is2 = new FileInputStream(fichier);
		int i=0;
		int cpt = 0;
		//int[] tab = new int[5];
		int entier;
		
		while(is.read()!=-1) {
			cpt ++;
		}
		
		int []tab = new int[cpt];
		System.out.println("CPT = " + cpt + "\n");
		is.close();
		
		//PB tab[i] = 0
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
	 * Sauvegarde compte dans un fichier
	 */
	public static void saveCompte(String f, Compte e) throws IOException{
		FileOutputStream isf = new FileOutputStream(f);
		DataOutputStream dos = new DataOutputStream(isf);
		dos.writeUTF(e.getId());
	}
	
	/**
	 * Instancie un Compte à partir des données du fichier
	 */
	public static Compte loadCompte(String f)throws IOException{
		FileInputStream is = new FileInputStream(f);
		DataInputStream dos = new DataInputStream(is);
		String id =dos.readUTF();
		
		return new Compte(id);
	}

	
}