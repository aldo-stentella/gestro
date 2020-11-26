package it.cnr.brevetti.gas;

import java.util.Comparator;

/**
 * Strumento per ordinare le risorse in ordine di nome decrescente
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [16-May-08]
 */
public class RisorsaComparator implements Comparator<Risorsa> {

	public int compare(Risorsa o1, Risorsa o2) {
		return o2.getNome().compareTo(o1.getNome());
	}
}
