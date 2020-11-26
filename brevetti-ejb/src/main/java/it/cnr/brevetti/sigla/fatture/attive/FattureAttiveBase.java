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
public class FattureAttiveBase extends FattureBody {
	private static final long serialVersionUID = 1L;
	private List<FatturaAttivaBase> elements = new ArrayList<FatturaAttivaBase>();
	public List<FatturaAttivaBase> getElements() {
		return elements;
	}
	public void setElements(List<FatturaAttivaBase> elements) {
		this.elements = elements;
	}
}
