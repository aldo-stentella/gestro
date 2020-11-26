package it.cnr.brevetti.sigla;

import java.io.Serializable;
import java.util.List;

/**
 * Rappresenta la richiesta da inviare a sigla
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 19, 2016]
 *
 */
public class JsonBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer activePage;
	private Integer maxItemsPerPage;
	private List<JsonClause> clauses;
	private JsonContext context;
	
	//{"activePage":0,"maxItemsPerPage":100,"clauses":[...],"context":{...}}
	public JsonBody() {
		setActivePage(0);
		setMaxItemsPerPage(100);
	}

	public JsonBody(List<JsonClause> clauses) {
		this();
		setClauses(clauses);
		setContext(new JsonContext());
	}
	
	public Integer getActivePage() {
		return activePage;
	}

	public void setActivePage(Integer activePage) {
		this.activePage = activePage;
	}

	public Integer getMaxItemsPerPage() {
		return maxItemsPerPage;
	}

	public void setMaxItemsPerPage(Integer maxItemsPerPage) {
		this.maxItemsPerPage = maxItemsPerPage;
	}

	public List<JsonClause> getClauses() {
		return clauses;
	}

	public void setClauses(List<JsonClause> clauses) {
		this.clauses = clauses;
	}

	public JsonContext getContext() {
		return context;
	}

	public void setContext(JsonContext context) {
		this.context = context;
	}
		
}
