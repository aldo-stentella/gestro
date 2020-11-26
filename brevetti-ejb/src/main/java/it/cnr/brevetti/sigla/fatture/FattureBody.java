package it.cnr.brevetti.sigla.fatture;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
/**
 * Tracciato del body JSON relativo alle fatture attive e passive
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 21, 2016]
 *
 */
public class FattureBody implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("totalNumItems")
	private int totalNumItems;
	@SerializedName("maxItemsPerPage")
	private int maxItemsPerPage;
	@SerializedName("activePage")
	private int activePage;
	public int getTotalNumItems() {
		return totalNumItems;
	}
	public void setTotalNumItems(int totalNumItems) {
		this.totalNumItems = totalNumItems;
	}
	public int getMaxItemsPerPage() {
		return maxItemsPerPage;
	}
	public void setMaxItemsPerPage(int maxItemsPerPage) {
		this.maxItemsPerPage = maxItemsPerPage;
	}
	public int getActivePage() {
		return activePage;
	}
	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}		
}