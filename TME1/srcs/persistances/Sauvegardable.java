package srcs.persistances;

import java.io.IOException;
import java.io.OutputStream;

/**
 *  Abstrait la capacité à un objet quelconque
 *  de se sauvegarder et de s’instancier en se
 *  chargeant à partir d’un flux d’I/O.
 */
public interface Sauvegardable {

	void save(OutputStream out) throws IOException;
	
}
