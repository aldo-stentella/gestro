<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.SortedMap"%>
<%@page import="it.cnr.brevetti.ejb.entities.Classificazione"%>
<%@page import="it.cnr.brevetti.ejb.entities.Dipartimento"%>
<%@page import="it.cnr.brevetti.ejb.entities.Trovato"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/Gcatalogo.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Consiglio Nazionale delle Ricerche - Catalogo Brevetti e Propriet&agrave; Intellettuale</title>
<!-- InstanceEndEditable -->
<link href="css/standard.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<link href="css/reports.css?<%=Version.getVersion()%>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="head" -->
<script language="JavaScript">
<!--
<%
ArrayList list = (ArrayList)request.getAttribute("list");
SortedMap ser = (SortedMap)request.getAttribute("ser");
Integer allDip = (Integer)request.getAttribute("allDip");
Integer allCat = (Integer)request.getAttribute("allCat");
	boolean italian = true;
%>
function inizialize(){
	if(GetObj('bar')!=null){
		GetObj('hscore').style.visibility='visible';
	}
	if(GetObj('Godd-row')!=null){
		GetObj('hdetails').style.visibility='visible';
	}
}

function openDetail(nsrif, dep) {
	var ref = window.open('<%= italian?"InfoCatalogo.do":"InfoTrovatoEn.jsp" %>?nsrif='+nsrif+'&dip=0','popup_trovato','height=980,width=980,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}

function doSubmit(x){
	if(x==1){
		document.form.dipartimenti.value = 0;
		document.form.submit();
	}
	if(x==2){
		document.form.liv1.value = 0;
		document.form.liv2.value = 0;
		document.form.liv3.value = 0;
		document.form.liv4.value = 0;
		document.form.submit();
	}
}
//-->
</script>
<!-- InstanceEndEditable -->
</head>
<body onload="inizialize();">
<table width="950px" border="0" align="center" cellpadding="0" cellspacing="6">
  <tr>
    <td valign="bottom" id="Gheader"><hr align="right" width="775" size="1" noshade color="#ffffff" class="GdottedLine" />
    <div id="link2cnr"><a href="http://www.cnr.it">&laquo; back to cnr.it</a></div></td>
  </tr>
  <tr>
    <td valign="top"><!-- InstanceBeginEditable name="content" -->
<table width="100%" border="0" cellspacing="10" cellpadding="0">
  <tr>
    <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="Gtab">
	        <tr>
	          <td width="81%"><p><br />Risultati della ricerca</p></td>
	          <td width="19%"><img src="images/Catalog/nuova-ricerca.png" class="GImgOver" onclick="window.location='Catalogo.do'"/></td>
            </tr>
            <tr>
        		<td colspan="2" class="Gscheda-top">&nbsp;</td>
	        </tr>
	        <tr>
	        <td colspan="2" class="Gscheda-mid"><table width="94%" align="center" cellpadding="0" cellspacing="0">
				<tr><td><ul id="summary"><li>
<%			
			int size = (list!=null?list.size():ser!=null?ser.size():0);
			String temp = size==0?"Nessun ":size+" ";
			temp = temp.concat("brevett"+(size<=1?"o ":"i "));
			temp = temp.concat("individuat"+(size<=1?"o ":"i "));
			temp = temp.concat("per i criteri specificati");
			out.println(temp);
			if(allDip!=null || allCat!=null){
				temp = "<span>Ripeti la ricerca</span> <ul>";
				temp = temp.concat(allDip!=null?"<li><a href='#' onclick='doSubmit(1)'>in tutti i dipartimenti         ( "+allDip+" risultati )</a> <img src='images/next2.gif' onclick='doSubmit(1)'/></li>\n":"&nbsp;"); 
				temp = temp.concat(allCat!=null?"<li><a href='#' onclick='doSubmit(2)'>in tutte le categorie e settori ( "+allCat+" risultati )</a> <img src='images/next2.gif' onclick='doSubmit(2)'/></li>\n":"&nbsp;"); 
				temp = temp.concat("</ul>");
				out.println(temp);
			}
%>
					</li>
				</ul>
		<form name="form" method="post" action="Catalogo.do">
		<input type="hidden" name="keywords" value="<%=request.getAttribute("keywords") %>" />
		<input type="hidden" name="liv1" value="<%=request.getAttribute("liv1") %>" />
		<input type="hidden" name="liv2" value="<%=request.getAttribute("liv2") %>" />
		<input type="hidden" name="liv3" value="<%=request.getAttribute("liv3") %>" />
		<input type="hidden" name="liv4" value="<%=request.getAttribute("liv4") %>" />
		<input type="hidden" name="dipartimenti" value="<%=request.getAttribute("dipartimenti") %>" />
		</form>
				</td>
				<td valign="bottom"><img src='images/spacer.gif' height=54 width=1 />
				  <div id="hscore" style="visibility:hidden;">Rilevanza</div></td>
                <td valign="bottom" width="7%"><div id="hdetails" style="visibility:hidden;">Dettagli</div></td>
			</tr>
<%
	int q = 1;
	if(list!=null && list.size()>0){
		for(Iterator i = list.iterator(); i.hasNext();){
		Trovato t = (Trovato)i.next();
%>
	<tr id='<%=(q==0)?"Geven-row":"Godd-row" %>'>
		<td colspan="2">
        <img src="images/Catalog/pallino-grigio.png" align="absmiddle" onclick='openDetail(<%=t.getNsrif() %>);' />
				<%= italian?t.getTitolo():t.getTitoloInglese() %>
        </td>
        <td align="right"><img src="images/Catalog/dettagli.png" width="46" height="46" border="0" align="middle" onclick='openDetail(<%=t.getNsrif() %>);' /></td>
	</tr>
    <tr><td colspan="3">&nbsp;</td></tr>
<%
		q = 1 - q;
		} 
	} else if(ser!=null && ser.size()>0){
		Double maxScore = new Double(0);
		boolean flag = true;
		for(Iterator i = ser.keySet().iterator(); i.hasNext();){
			Double chiave = (Double)i.next();
			maxScore = Math.max(maxScore, chiave);
			if(chiave<maxScore/10 && flag){
				flag = false;
%>	 
				<tr><td id="message" colspan="3"><i>Sono state omesse le voci con una bassa rilevanza.<br/>
					 <a href="#" onclick="GetObj('minorityReport').style.display='block'; GetObj('message').style.display='none';">Visualizza la lista completa</a></i></td></tr>
					 </table>
				  <table id="minorityReport" width="94%" align="center" cellpadding="0" cellspacing="0" style="display: none;">
<%
				
			}
			Trovato t = (Trovato)ser.get(chiave);
%>
<tr id='<%=(q==0)?"Geven-row":"Godd-row" %>'>
<td width="90%">
        <img src="images/Catalog/pallino-grigio.png" align="absmiddle" onclick='openDetail(<%=t.getNsrif() %>);' />
		<%= italian?t.getTitolo():t.getTitoloInglese() %></td>
<td>
<div id="bar" title="<%= chiave*100 %>% - powered by Solr" style="width: 70px; height: 10px; border: 1px solid black; background-color: #B2CDE0;">
     <div style="height: 6px; width: <%= Math.min(94,chiave*100) %>%; background-color: #0f295b; border: 1px solid gray; padding:1px;"><img height="100%" width="100%" src="images/spacer.gif" alt="<%= chiave*100 %>% - powered by Solr" title="<%= chiave*100 %>% - powered by Solr" /></div>
</div></td>
<td align="right" width="7%"><img src="images/Catalog/dettagli.png" width="46" height="46" border="0" align="middle"  onclick='openDetail(<%=t.getNsrif() %>);' /></td>
</tr>
<tr><td colspan="3">&nbsp;</td></tr>
<%
			q = 1 - q;
		} 
	}
%>       
	        	</table>
	        	</td>
	      </tr>
          <tr><td colspan="2" class="Gscheda-bot">&nbsp;</td></tr>
	    </table>    </td>
  </tr>
</table>
<!-- InstanceEndEditable --></td>
  </tr>
  <tr>
    <td align="right" class="Gfooter"><hr align="right" width="775" size="1" noshade color="#880010" />fonte dati: &quot;Gestione Trovati&quot; | <a href="https://brevetti.cnr.it" target="_blank">brevetti.cnr.it</a> | <a href="http://www.cnr.it" target="_blank">www.cnr.it</a> | Consiglio Nazionale delle Ricerche |  P.le   Aldo Moro, 7 | 00185 Roma</td>
  </tr>
</table>
</body>
<!-- InstanceEnd --></html>
