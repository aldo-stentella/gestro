package it.cnr.brevetti.sigla.fatture.passive;

import java.util.ArrayList;
import java.util.List;

import it.cnr.brevetti.sigla.fatture.FattureBody;
/**
 * Tracciato risposta REST per la richiesta di fatture passive di base
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 21, 2016]
 *
 */
public class FatturePassiveBase extends FattureBody {
	private static final long serialVersionUID = 1L;
	private List<FatturaPassivaBase> elements = new ArrayList<FatturaPassivaBase>();
	public List<FatturaPassivaBase> getElements() {
		return elements;
	}
	public void setElements(List<FatturaPassivaBase> elements) {
		this.elements = elements;
	}
}