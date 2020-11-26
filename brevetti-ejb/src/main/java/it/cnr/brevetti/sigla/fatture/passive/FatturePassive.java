package it.cnr.brevetti.sigla.fatture.passive;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import it.cnr.brevetti.sigla.fatture.FattureBody;
/**
 * Tracciato mappatura REST -> fatture passive
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 21, 2016]
 *
 */
public class FatturePassive extends FattureBody {
	private static final long serialVersionUID = 1L;

	@SerializedName("elements")
	private List<FatturaPassiva> elements = new ArrayList<FatturaPassiva>();
	
	public List<FatturaPassiva> getElements() {
		return elements;
	}
	public void setElements(List<FatturaPassiva> elements) {
		this.elements = elements;
	}
	
}