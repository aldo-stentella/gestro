<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.sigla.fatture.attive.FatturaAttiva"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="it.cnr.brevetti.util.UtilityFunctions"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/generic2.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<%@page import="it.cnr.brevetti.util.ApplicationProperties"%>
<%@page import="it.cnr.brevetti.util.Version"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<!-- InstanceBeginEditable name="doctitle" -->
<title>Brevetti CNR - Fatture</title>
<!-- InstanceEndEditable -->
<c:set var="sessioneUtente" value="${sessionScope.sessioneUtente}" />
<c:set var="dipartimento" value="${sessionScope.dipartimento}" />
<c:set var="MultiDipartimento" value="${sessionScope.multiDipartimento}" />

<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" language="JavaScript1.4">
var openSession = false;

function inizialize() {
	renderNote();
}

function renderNote(){
        var str = GetObj('note').value.replace(/^\s+|\s+$/g, "");
        if(str.length>0){
                GetObj('inot').src='images/note.gif';
        } else {
                GetObj('inot').src='images/hnote.gif';
        }
}

function switchNote(){
	if(GetObj('note').style.visibility=="hidden"){
		GetObj('note').style.visibility="visible";
		GetObj('note').rows="4";
		GetObj('note').focus();
	} else {
		GetObj('note').style.visibility="hidden";
		GetObj('note').rows="1";
	}
}

</script>
<!-- InstanceEndEditable -->

</head>
<body onload="inizialize()">
<div class="center">
  <table width="100%" class="pagetable">
    <tr>
      <td colspan="2" id="header" align="left"><img src="images/cnr_logo.gif" width="900" height="100" border="0" />
      	<span class="highlighted"><%=application.getInitParameter("mode") %></span></td>
    </tr>
    <tr>
      <td width="1%" valign="top">
      	<div id="login">utente: 
	      	<b><c:out value="${sessioneUtente.utente.nomeUtente}" /></b>
      	</div>
        <div class="departs">[<c:out value="${dipartimento.descrizione}" />]
        	<c:if test="${MultiDipartimento}"><br />
	    		<a href="javascript:abandon('SwitchDip.do')">Cambia dipartimento</a>
			</c:if>
        </div>
      <table width="100%" id="menu">
        <tr>
          <td class="menuGroups"><ul>
              <li><a href="javascript:openHelp('#')">Aiuto</a></li>
              <script type="text/javascript">
			  var acerchiata = 64;
              document.write('<li><a href="mailto:oil.support'+String.fromCharCode(acerchiata)+'amministrazione.cnr.it?subject=[segnalazione BREVETTI]">Contattaci</a></li>\n');
              </script>
              <li><a href="javascript:abandon('logout.do')">Uscita</a></li>
            </ul></td>
        </tr>
        <%@ include file="/WEB-INF/jsp/layout/menu.jsp"%>
        <tr>
          <td><img src="images/spacer.gif" width="140" height="1" /></td>
        </tr>        
      </table></td>
      <td width="100%" class="pagetable">
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Fatture Attive<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
<%
	List<FatturaAttiva>list = (List)session.getAttribute("datiFattura");
	FatturaAttiva fattura = new FatturaAttiva();
	if(list != null && list.size()>0)
		fattura = list.get(0);
	    String numero = fattura.getEsercizio()+" "+fattura.getCd_cds_origine()+" "+fattura.getCd_uo_origine()+" "+fattura.getPg_fattura_attiva();
%>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr><td id="areaUp">&nbsp;</td></tr>
          <tr>
            <td id="area"><table width="90%" border="0" align="center" cellspacing="0">
              <tr>
                <td width="44%" align="left">N&deg; fattura</td>
                <td width="1%" align="left">&nbsp;</td>
                <td width="27%" align="left">Data emissione</td>
                <td width="1%" align="left">&nbsp;</td>
                <td width="27%" align="left">Data registrazione</td>
              </tr>
              <tr>
                <td align="left"><input type="text" name="numFattura" size="30" readonly="true" style="background-color:#FFFF00" value="<%= numero %>" /></td>
                <td align="left">&nbsp;</td>
                <td align="left"><input type="text" name="_dataFattura" size="10" readonly="true" style="background-color:#FFFF00" value="<%= UtilityFunctions.itFormat(fattura.getDt_emissione()) %>" />
                </td>
                <td align="left">&nbsp;</td>
                <td align="left"><input type="text" name="_dataRegFattura" size="10" readonly="true" style="background-color:#FFFF00" value="<%= UtilityFunctions.itFormat(fattura.getDt_registrazione()) %>" /></td>
              </tr>
              <tr>
                <td colspan="5" align="left"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
                <td align="left">Data emissione accertamento</td>
                <td align="left">&nbsp;</td>
                <td align="left">Numero accertamento</td>
                <td align="left">&nbsp;</td>
                <td align="left">Tipologia</td>
              </tr>
              <tr>
                <td align="left"><input type="text" name="_dataAccertam" size="10" readonly="true" style="background-color:#FFFF00" value="<%= UtilityFunctions.itFormat(fattura.getDt_emissione_accertamento()) %>" /></td>
                <td align="left">&nbsp;</td>
                <td align="left"><input type="text" name="_numAccertam" size="10" readonly="true" style="background-color:#FFFF00" value="<%= fattura.getPg_accertamento() %>" /></td>
                <td align="left">&nbsp;</td>
                <td align="left"><input type="text" name="tiFattura" size="15" readonly="true" style="background-color:#FFFF00" value="<%= fattura.getTi_fattura() %>" /></td>
              </tr>
              <tr>
                <td colspan="5" align="left"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
                <td align="left">Cliente</td>
                <td align="left">&nbsp;</td>
                <td align="left">Codice fiscale/Partita IVA cliente</td>
                <td align="left">&nbsp;</td>
                <td align="left">Nota fattura <img src="images/note.gif" alt="nota" width="15" height="16" align="top" class="imaction" id="inot" onclick="switchNote();" /></td>
              </tr>
              <tr>
                <td align="left" valign="top">
                	<input type="text" name="cliente" size="36" readonly="true" style="background-color:#FFFF00" value="<%= fattura.getRagione_sociale()!=null?fattura.getRagione_sociale():fattura.getCognome()+" "+fattura.getNome() %>" />
                </td>
                <td align="left">&nbsp;</td>
                <td align="left" valign="top"><input type="text" name="codFis" size="20" readonly="true" style="background-color:#FFFF00" value="<%= fattura.getCodice_fiscale()!=null?fattura.getCodice_fiscale():fattura.getPartita_iva() %>" /></td>
                <td align="left">&nbsp;</td>
                <td align="left" valign="top"><textarea id="note" rows="1" cols="35" style="font-size: 11px; visibility:hidden" onchange="renderNote();" readonly="true"><%= fattura.getDs_fattura_attiva() %></textarea></td>
              </tr>
              <tr>
                <td colspan="5"><img src="images/spacer.gif" alt="" width="3" height="3" /></td>
              </tr>
              <tr>
                <td align="center">&nbsp;</td>
                <td>&nbsp;</td>
                <td align="center">Dettaglio fattura:</td>
                <td>&nbsp;</td>
                <td align="right">&nbsp;</td>
              </tr>
            </table>
              <table align="center" width="488" id="voci">
                <tr>
                  <th width="18">&nbsp;</th>
                  <th width="45">NSRIF</th>
                  <th width="70">F.C.IVA</th>
                  <th width="88">Imponibile</th>
                  <th width="62">IVA</th>
                  <th width="89">Tot.parz.</th>
                  <th width="45"><img src="images/null.gif" width="38" height="18" /></th>
                </tr>
<%
			String[] colors = {"odd","even"};
			int c = 1;
			BigDecimal totale = new BigDecimal(0);
			for(FatturaAttiva fa:list){
%>
                <tr>
                  <td>&nbsp;</td>
                  <td class="<%=colors[c] %>"><div><%=fa.getPg_trovato() %></div></td>
                  <td class="<%=colors[c] %>"><div><%=fa.getFcIva() %></div></td>
                  <td class="<%=colors[c] %>"><div><%=fa.getIm_imponibile() %></div></td>
                  <td class="<%=colors[c] %>"><div><%=fa.getIm_iva() %></div></td>
                  <td class="<%=colors[c] %>"><div><%=fa.getIm_imponibile().add(fa.getIm_iva().add(fa.getFcIva())) %></div></td>
                  <td class="<%=colors[c] %>"><img src="images/info.gif" onclick="alert('<%= StringEscapeUtils.escapeHtml(fa.getDs_riga_fattura()) %>')"  /></td>
                </tr>
<%
				c=1-c;
				totale = totale.add(fa.getIm_imponibile().add(fa.getIm_iva().add(fa.getFcIva())));
			}
%>

 			  <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">Totale:</td>
                <td class="<%=colors[c] %>"><%=totale %>&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
              </tr>
              </table></td>
          </tr>
          <tr>
            <td height="3"><img src="images/spacer.gif" width="3" height="3" /></td>
          </tr>
          <tr>
            <td align="center">
	            <input type="button" name="annulla" value="Chiudi" onclick="javascript:abandon('home.do')" />
	            &nbsp;&nbsp;&nbsp;&nbsp;
      			<input type="button" value="Ultima ricerca" onclick="javascript:abandon('GesFat2.do?lastQBE=y')" />
            </td>
          </tr>
        </table>
        <!-- InstanceEndEditable -->
        </td>
    </tr>
    <tr>
      <td id="footer" colspan="2">&copy;2007-<%=ApplicationProperties.getInstance().getCopyright()%> <a href="http://www.cnr.it" target="_blank">Consiglio Nazionale delle Ricerche</a> | <a href="http://www.si.cnr.it" target="_blank"><%=ApplicationProperties.getInstance().getDept()%></a> | <a href="javascript:openHelp('staff.html')">Staff</a></td>
    </tr>
  </table>
</div>
</body>
<!-- InstanceEnd --></html>
