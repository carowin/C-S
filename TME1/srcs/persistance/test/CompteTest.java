package srcs.persistance.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static srcs.persistances.PersistanceTools.loadCompte;
import static srcs.persistances.PersistanceTools.saveCompte;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import srcs.banque.Compte;
public class CompteTest {
	
	@Test
	public void testCompte() throws IOException {
		try {
			String fichier = "/tmp/compte";
			Compte cpt = new Compte("cpt1");
			cpt.crediter(5);
			cpt.debiter(2);
			assertEquals(3,cpt.getSolde(),0);
			saveCompte(fichier, cpt);
			Compte bis = loadCompte(fichier);
			assertEquals(cpt, bis);
			assertEquals(3,bis.getSolde(),0);
		}catch(FileNotFoundException e) {
			assertTrue(false);
		}
	}
}
