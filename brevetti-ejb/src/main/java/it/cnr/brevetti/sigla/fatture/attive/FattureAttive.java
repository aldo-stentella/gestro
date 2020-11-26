package it.cnr.brevetti.sigla.fatture.attive;

import java.util.ArrayList;
import java.util.List;

import it.cnr.brevetti.sigla.fatture.FattureBody;
/**
 * Tracciato risposta REST per la richiesta di fatture attive di base
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 22, 2016]
 *
 */
public class FattureAttive extends FattureBody {
	private static final long serialVersionUID = 1L;
	private List<FatturaAttiva> elements = new ArrayList<FatturaAttiva>();
	public List<FatturaAttiva> getElements() {
		return elements;
	}
	public void setElements(List<FatturaAttiva> elements) {
		this.elements = elements;
	}
}
