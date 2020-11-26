<%@page import="java.math.BigDecimal"%>
<%@page import="it.cnr.brevetti.sigla.fatture.passive.FatturaPassiva"%>
<%@page import="java.util.SortedMap"%>
<%@page import="java.util.TreeMap"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="it.cnr.brevetti.ejb.entities.Fattura"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.cnr.brevetti.ejb.entities.VoceFattura"%>
<%@page import="it.cnr.brevetti.fatture.actionForms.FatturaForm"%>
<%@page import="java.util.HashMap"%>
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
<%
	boolean readOnly = ((FatturaForm)request.getAttribute("datiFattura")).isReadOnly();
%>
<script type="text/javascript" language="JavaScript1.4">
var openSession = <%= readOnly?"false":"true" %>;

function aggiorna() {
	self.document.forms[0].updDet.value='1';
	self.document.forms[0].submit();
}

function inizialize() {
	renderNote();
	if(!openSession){
		document.forms[0].annulla.value = 'Chiudi';
	}
}

function renderNote(){
        var str = document.forms[0].note.value.replace(/^\s+|\s+$/g, "");
        if(str.length>0){
                GetObj('inot').src='images/note.gif';
        } else {
                GetObj('inot').src='images/hnote.gif';
        }
}

function switchNote(){
	if(document.forms[0].note.style.visibility=="hidden"){
		document.forms[0].note.style.visibility="visible";
		document.forms[0].note.rows="4";
		document.forms[0].note.focus();
	} else {
		document.forms[0].note.style.visibility="hidden";
		document.forms[0].note.rows="1";
	}
}

function switchVoce(target){
	var stat = 0;
	for(var i = 1; i<30; i++){
		obj = GetObj(target+"."+i);
		if(obj==null)
			break;
		if(obj.style.display=="none"){
			obj.style.display="";
		}else{
			obj.style.display="none";
			stat = 1;
		}	
	}
	if(stat == 0){
		document.images['a.'+target].src="images/down.gif";
		document.images['a.'+target].alt="Comprimi";
	}else{
		document.images['a.'+target].src="images/right.gif";
		document.images['a.'+target].alt="Espandi";
	}
}
function searchFat(riga,prec){
	var ref = window.open('AddVoce.do?id='+riga+'&prec='+prec,'popup_trov','height=590,width=560,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}

function deleteFat(riga){
	var ref = window.open('DelVoce.do?idRiga='+riga,'popup_trov','height=590,width=560,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
}

function on_submit(){
	//if(validateDatiFattura(document.forms[0])){
		document.forms[0].write.value='1';
		document.forms[0].submit();
	//}
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
        <div class="titolo"><!-- InstanceBeginEditable name="title" -->Fatture Passive<!-- InstanceEndEditable --></div><br/>
        <!-- InstanceBeginEditable name="content" -->
        <html:form action="Fatture.do" method="POST">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
	          <td id="areaUp">
              	<div>Id fattura: <html:text property="id" size="10" readonly="true" /></div>
              </td>
          </tr>
          <tr>
            <td id="area"><table width="90%" border="0" align="center" cellspacing="0">
              <tr>
                <td width="44%" align="left">N&deg; fattura</td>
                <td width="1%" align="left">&nbsp;</td>
                <td width="27%" align="left">Data fattura</td>
                <td width="1%" align="left">&nbsp;</td>
                <td width="27%" align="left">Protocollo AMMCNT - CNR</td>
              </tr>
              <tr>
                <td align="left"><html:text property="numFattura" readonly="true" style="background-color:#FFFF00" /></td>
                <td align="left">&nbsp;</td>
                <td align="left"><html:text property="_dataFattura" size="10" readonly="true" style="background-color:#FFFF00" />
                </td>
                <td align="left">&nbsp;</td>
                <td align="left"><html:text property="protocollo" readonly="<%=readOnly %>" /></td>
              </tr>
              <tr>
                <td colspan="5" align="left"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
                <td align="left">Data impegno</td>
                <td align="left">&nbsp;</td>
                <td align="left">Impegno obbligazione</td>
                <td align="left">&nbsp;</td>
                <td align="left">Mandato</td>
              </tr>
              <tr>
                <td align="left"><html:text property="_dataImpegno" size="10" readonly="true" style="background-color:#FFFF00" /></td>
                <td align="left">&nbsp;</td>
                <td align="left"><html:text property="impegnoObbligazione" readonly="true" style="background-color:#FFFF00" /></td>
                <td align="left">&nbsp;</td>
                <td align="left"><html:text property="mandatoProtocollo" readonly="true" style="background-color:#FFFF00" /></td>
              </tr>
              <tr>
                <td colspan="5" align="left"><img src="images/spacer.gif" width="3" height="3" /></td>
                </tr>
              <tr>
                <td align="left">Studio Brevettuale</td>
                <td align="left">&nbsp;</td>
                <td align="left">Note <img src="images/note.gif" alt="nota" width="15" height="16" align="top" class="imaction" id="inot" onclick="switchNote();" /></td>
                <td align="left">&nbsp;</td>
                <td align="left"><strong>Totale fattura</strong></td>
              </tr>
              <tr>
                <td align="left" valign="top">
                	<html:text property="studioDescrizione" size="40" readonly="true" style="background-color:#FFFF00" />
                	<html:hidden property="studioBrevettualeId" />
                </td>
                <td align="left">&nbsp;</td>
                <td align="left" valign="top"><html:textarea property="note" rows="1" cols="40" style="font-size: 11px; visibility:hidden" onchange="renderNote();" readonly="<%=readOnly %>" /></td>
                <td align="left">&nbsp;</td>
                <td align="left" valign="top"><html:text property="importo" style="background-color:#FFFF00" readonly="true" /></td>
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
              <table align="center" width="806" id="voci">
                <tr>
                  <th width="18">&nbsp;</th>
                  <th width="45">NSRIF</th>
                  <th width="108">Causale</th>
                  <th width="32">n&deg;ann.</th>
                  <th width="178">Nazione</th>
                  <th width="70">F.C.IVA</th>
                  <th width="88">Imponibile</th>
                  <th width="62">IVA</th>
                  <th width="89">Tot.parz.</th>
                  <th width="45"><img src="images/null.gif" width="38" height="18" /></th>
                </tr>
<%
			TreeMap<Long, VoceFattura> vociFattura =(TreeMap<Long, VoceFattura>)session.getAttribute("vociFattura");
			HashMap<String, FatturaPassiva>map = (HashMap<String, FatturaPassiva>) session.getAttribute("datiSigla");
			if(vociFattura==null)	vociFattura= new TreeMap<Long, VoceFattura>();
			Long lastRow = null;
			int j = 1;
			long k = 0;
			int c = 1;
			String[] colors = {"odd","even"};
			BigDecimal[] parziali = new BigDecimal[3];
			for(Long i : vociFattura.keySet()){
				VoceFattura vf = (VoceFattura)vociFattura.get(i);
				Long currRow = vf.getVoceFatturaSigla().getProgressivoRiga();
				if(!currRow.equals(lastRow)){
					if(lastRow!=null){
						if(!readOnly){
%>
 			  <tr id="compact<%=lastRow+"."+j %>" style="display:none">
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>"><%=(j==2)?"<img src='images/null.gif' onload=\"GetObj('cancel."+lastRow+"').style.visibility='hidden'\" />":"" %>&nbsp;</td>
                <td class="<%=colors[c] %>"><%=(parziali[0].signum()!=0 || parziali[1].signum()!=0 || parziali[2].signum()!=0)?"<img src='images/warn.gif' />":"" %>&nbsp;</td>
                <td class="<%=colors[c] %>"><img src="images/plus.gif" alt="" width="16" height="16" class="imaction" onclick="searchFat(0,<%=k %>)" /></td>
              </tr>
<%
						}
						j=1;
						c=1-c;
					}
					FatturaPassiva siglaRow = map.get(vf.getVoceFatturaSigla().getEsercizio()+"|"+vf.getVoceFatturaSigla().getCdCds()+"|"+vf.getVoceFatturaSigla().getCdUnitaOrganizzativa()+"|"+vf.getVoceFatturaSigla().getPgFatturaPassiva()+"|"+vf.getVoceFatturaSigla().getProgressivoRiga());
					parziali[0] = siglaRow.getFcIva();
					parziali[1] = siglaRow.getIm_imponibile();
					parziali[2] = siglaRow.getIm_iva();
%>
	              <tr class="<%=colors[c] %>">
	                <td width="20"><img src="images/right.gif" alt="Espandi" name="a.compact<%=currRow%>" width="9" height="12" onclick="switchVoce('compact<%=currRow%>');" /></td>
	                <td><div><%= vf.getNsrif() %></div></td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td><div><%= siglaRow.getFcIva() %></div></td>
	                <td><div><%= siglaRow.getIm_imponibile() %></div></td>
	                <td><div><%= siglaRow.getIm_iva() %></div></td>
	                <td><div><%= siglaRow.getFcIva().add(siglaRow.getIm_imponibile().add(siglaRow.getIm_iva())) %></div></td>
	                <td>&nbsp;</td>
	              </tr>
<%
				}
				parziali[0] = parziali[0].add(vf.getAnticipazione().negate());
				parziali[1] = parziali[1].add(vf.getOnorari().negate());
				parziali[2] = parziali[2].add(vf.getIva().negate());
%>
                <tr id="compact<%=currRow+"."+j++ %>" style="display:none">
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td class="<%=colors[c] %>"><div><%=vf.getCausale()!=null?vf.getCausale().getNome():"NON DISP." %></div></td>
                  <td class="<%=colors[c] %>"><div><%=vf.getN()!=null?vf.getN():"" %></div></td>
                  <td class="<%=colors[c] %>"><div><%=vf.getStato()!=null?vf.getStato().getNome():"NON DISP." %></div></td>
                  <td class="<%=colors[c] %>"><div><%=vf.getAnticipazione() %></div></td>
                  <td class="<%=colors[c] %>"><div><%=vf.getOnorari() %></div></td>
                  <td class="<%=colors[c] %>"><div><%=vf.getIva() %></div></td>
                  <td class="<%=colors[c] %>"><div><%=vf.getParzialeEuro() %></div></td>
                  <td class="<%=colors[c] %>">
<%				if(!readOnly){
%>
                  		<img src="images/rtable.gif" alt="Ricerca" class="imaction" onclick="searchFat(<%= i %>,0)" />
                  	  	<img src="images/delete.gif" alt="Elimina" class="imaction" onclick="deleteFat(<%= i %>)" id="cancel.<%=currRow %>" />
<%				}else{
%>	
                  		&nbsp;
<%
				}
%>
                  </td>
                </tr>
<%
				lastRow = currRow;
				k=i.longValue();
			}
			if(!readOnly){
%>
 			  <tr id="compact<%=lastRow+"."+j %>" style="display:none">
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>">&nbsp;</td>
                <td class="<%=colors[c] %>"><%=(j==2)?"<img src='images/null.gif' onload=\"GetObj('cancel."+lastRow+"').style.visibility='hidden'\" />":"" %>&nbsp;</td>
                <td class="<%=colors[c] %>"><%=(parziali[0].signum()!=0 || parziali[1].signum()!=0 || parziali[2].signum()!=0)?"<img src='images/warn.gif' />":"" %>&nbsp;</td>
                <td class="<%=colors[c] %>"><img src="images/plus.gif" alt="" width="16" height="16" class="imaction" onclick="searchFat(0,<%=k %>)" /></td>
              </tr>
<%
			}
%>
              </table></td>
          </tr>
          <tr>
            <td height="3"><img src="images/spacer.gif" width="3" height="3" /></td>
          </tr>
          <tr>
            <td align="center">
<% 			if(!readOnly){ 
%>
	            <input type="button" value="Salva" onclick="on_submit();" />
<%			}
%>
	            &nbsp;&nbsp;        
	            <input type="button" name="annulla" value="Annulla" onclick="javascript:abandon('home.do')" />
	            &nbsp;&nbsp;&nbsp;&nbsp;
      			<input type="button" value="Ultima ricerca" onclick="javascript:abandon('GesFat.do?lastQBE=y')" />
	            
            </td>
          </tr>
        </table>
        <input type="hidden" name="write" value="0" />
        <html:hidden property="updDet"/>
        </html:form>
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
