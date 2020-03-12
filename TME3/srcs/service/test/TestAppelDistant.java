package service.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.ServeurMultiThread;
import services.Service;
import services.Annuaire;
import services.AnnuaireAppelDistant;
import services.AnnuaireProxy;
import services.Calculatrice;
import services.Calculatrice.ResDiv;
import services.CalculatriceAppelDistant;
import services.CalculatriceProxy;

public class TestAppelDistant {

	public static int portannuaire=4234;
	public static int portcalculette=14234;
	
	private Thread annuaire;
	private Thread calculette;
	
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		annuaire =new Thread( () -> {
			try {
				new ServeurMultiThread(portannuaire, AnnuaireAppelDistant.class).listen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		calculette =new Thread( () -> {
			try {
				new ServeurMultiThread(portcalculette, (Class<? extends Service>) CalculatriceAppelDistant.class).listen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		annuaire.start();
		calculette.start();
		Thread.sleep(200);
	}

	@After
	public void tearDown() throws Exception {
		annuaire.interrupt();
		calculette.interrupt();
		portannuaire++;
		portcalculette++;
	}

	@Test
	public void testCalculatrice() {
		Calculatrice calc = new CalculatriceProxy("localhost", portcalculette);
		
		assertEquals(new Integer(0), calc.add(4, -4));
		assertEquals(new Integer(9), calc.add(4,5));
		
		assertEquals(new Integer(3), calc.sous(4, 1));
		assertEquals(new Integer(8), calc.sous(4, -4));
		
		assertEquals(new Integer(12), calc.mult(3, 4));
		assertEquals(new Integer(-16), calc.mult(4, -4));
		
		ResDiv res = calc.div(5, 3);
		assertEquals(1, res.getQuotient());
		assertEquals(2, res.getReste());
		
		
	}
	
	@Test
	public void testAnnuaire() {
		String nom = "nomtest";
		String value="valeurtest";
		Annuaire annuaire = new AnnuaireProxy("localhost", portannuaire);
		annuaire.bind(nom, value);
		assertEquals(value, annuaire.lookup(nom));
		annuaire.unbind(nom);
		assertEquals("", annuaire.lookup(nom));
		
	}

}
