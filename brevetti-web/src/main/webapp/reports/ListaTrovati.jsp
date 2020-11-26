<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.domain.Parametri"%>
<%@page import="it.cnr.brevetti.domain.TrovatoQuery"%>
<%@page import="java.util.Date"%>
<%@page import="it.cnr.brevetti.trovati.javabeans.TrovatoJB"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.Trovato"%>
<%
	TrovatoJB tjb = TrovatoJB.getInstance();
	Integer strutturaAfferenza = new Integer(0);
	boolean errore = false;
	boolean italian = true;
	try{
		strutturaAfferenza = Integer.decode(request.getParameter("dip"));
	} catch(NumberFormatException e){
		if(request.getParameter("dip")!=null){
			errore = true;
		}
	}
	
	if(request.getParameter("lang")!=null && request.getParameter("lang").equalsIgnoreCase("en"))
		italian = false;
	Parametri p = new Parametri();
	if(strutturaAfferenza.intValue()!=0) p.add(TrovatoQuery.DIPARTIMENTO_ID, strutturaAfferenza);
	p.add(TrovatoQuery.DATA_ABBANDONO, AbstractQuery.IS_NULL);
	p.add(TrovatoQuery.CESSIONE_DIRITTI, new Integer(1));
	//p.addRange(TrovatoQuery.DATA_ABBANDONO, new Date(), null);
	List list = tjb.getTrovati(p);
	p.add(TrovatoQuery.CESSIONE_DIRITTI, new Integer(2));
	list.addAll(tjb.getTrovati(p));
	p.add(TrovatoQuery.CESSIONE_DIRITTI, new Integer(4));
	list.addAll(tjb.getTrovati(p));
	
%>
<%@page import="it.cnr.brevetti.domain.AbstractQuery"%>
<script type="text/javascript">
<!--
	function openDetail(nsrif) {
		var ref = window.open('<%= italian?"InfoTrovato.jsp":"InfoTrovatoEn.jsp" %>?nsrif='+nsrif+'&dip=<%=strutturaAfferenza%>','popup_trovato','height=800,width=750,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		ref.focus();
	}
//-->
</script>

<table align="center" class="tabella">
<tr><td class="intestazione">
		<%= italian?"Titolo":"Title" %>
	</td></tr>
<%	int q = 1;
	if(!errore && list!=null)	for(Iterator i = list.iterator(); i.hasNext();){
		Trovato t = (Trovato)i.next();
		String tit = italian?t.getTitolo():t.getTitoloInglese();
		if(tit==null || tit.trim().length() == 0)
			tit = italian?"[titolo non ancora disponibile in lingua italiana]":"[title not yet available in english]";
%>
	<tr>
		<td onclick='openDetail(<%=t.getNsrif() %>);' class='<%=(q==0)?"even-row":"odd-row" %>'>
			<a href="#">
				<%= tit %>
			</a>
		</td>
	</tr>
<%
		q = 1 - q;
	} else {
%>
		<tr>
			<td class='even-row'>
				<font color="red"><strong>
					Errore nella determinazione del dipartimento
				</strong></font>
			</td>
		</tr>
<%		
	}
%>
</table>
