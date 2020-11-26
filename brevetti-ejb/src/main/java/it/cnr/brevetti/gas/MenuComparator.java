package it.cnr.brevetti.gas;

import java.util.Comparator;

/**
 * Strumento per ordinare i menu in ordine posizionale
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [26-Apr-08]
 */
public class MenuComparator implements Comparator<Menu> {

	public int compare(Menu o1, Menu o2) {
		if (o1.getPosizione().intValue() < o2.getPosizione().intValue())
			return -1;
		
		if (o1.getPosizione().intValue() > o2.getPosizione().intValue())
			return 1;
		
		return 0;
	}

}
