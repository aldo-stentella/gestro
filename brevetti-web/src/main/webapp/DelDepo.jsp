<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Brevetti CNR - </title>
<script type="text/javascript" language="javascript1.4">
function invia(){
	if (confirm("Conferma la cancellazione del Deposito '"+document.forms[0].numeroDeposito.value+"' ?")) {
		document.forms[0].submit();
	}
}
</script>
<%
	String act = (String)request.getAttribute("act");
	if(act!=null && act.startsWith("close")){

%>
	<script type="text/javascript" language="javascript1.4">
		window.opener.eval("aggiornaDep();");
		window.close();
	</script>
<%
	}
%>
</head>

<body >
<html:form action="DelDepo.do" method="POST">
            <html:hidden property="id" />
           	<html:hidden property="nsrif" />
           	<html:hidden property="key" />
           	<html:hidden property="tipoId" />
            <table width="500" border="0" align="center" cellpadding="5" class="area">
  <tr>
    <td colspan="2" align="right">Stato / Nazione    </td>
    <td width="309" align="left">
      <html:select property="statoId" style="width: 390px;">
        <html:options collection="nazioni" property="value" labelProperty="label" />
      </html:select>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="right">Lingua</td>
    <td align="left"><html:select property="idiomiId" >
    		<html:options collection="idiomi" property="value" labelProperty="label" />
    	</html:select></td>
    </td>
  </tr>
  <tr>
    <td height="10" colspan="3" align="center"><img src="images/spacer.gif" width="480" height="1" class="imaction" /></td>
    </tr>
  <tr>
    <td align="right" valign="middle">
    	<strong>Studio Brevettuale:</strong>
    </td>
    <td>Nome</td>
    <td><html:select property="studioBrevettualeId" style="font-size: 10px; width: 255px;" onchange="popolateSedi(this.value);">
      <html:options collection="denoms" property="value" labelProperty="label" />
    </html:select>
    </td>
  </tr>
  <tr>
    <td height="10" colspan="3" align="center"><img src="images/spacer.gif" width="480" height="1" class="imaction" /></td>
  </tr>
  <tr>
    <td width="117" rowspan="2" align="right" valign="middle"><strong>Deposito: </strong></td>
    <td width="32" align="right">N&deg;</td>
    <td align="left"><html:text property="numeroDeposito" size="15" readonly="true" /></td>
  </tr>
  <tr>
    <td align="right">data</td>
    <td align="left"><html:text property="dataDepositoX" size="10" readonly="true" /></td>
  </tr>
  <tr>
    <td height="10" colspan="3" align="center" valign="middle"><img src="images/spacer.gif" alt="" width="480" height="1" class="imaction" /></td>
  </tr>
  <tr>
    <td rowspan="2" align="right"><strong>Rilascio: </strong></td>
    <td align="right">N&deg;</td>
    <td align="left"><html:text property="numRilascioDep" size="15" readonly="true" /></td>
  </tr>
  <tr>
    <td align="right">data</td>
    <td align="left"><html:text property="dataRilascioDepX" size="10" readonly="true" /></td>
  </tr>
    <tr>
    <td height="10" colspan="3" align="center" valign="middle"><img src="images/spacer.gif" alt="" width="480" height="1" class="imaction" /></td>
    </tr>
  <tr>
    <td rowspan="2" align="right"><strong>Pubblicazione: </strong></td>
    <td align="right">N&deg;</td>
    <td align="left"><html:text property="numPubblicazione" size="15" readonly="true" /></td>
  </tr>
  <tr>
    <td align="right">data</td>
    <td align="left"><html:text property="dataPubblicazioneX" size="10" readonly="true" /></td>
  </tr>
    <tr>
    <td height="10" colspan="3" align="center" valign="middle"><img src="images/spacer.gif" alt="" width="480" height="1" class="imaction" /></td>
    </tr>
  <tr>
    <td rowspan="3" align="right"><strong>Estinzione titolarit&agrave;:</strong></td>
    <td align="right">data</td>
    <td align="left"><html:text property="dataAbbandonoX" size="10" readonly="true" /></td>
  </tr>
  <tr>
    <td align="right">causale</td>
    <td align="left"><html:text property="tipiEstinzioneId" size="10" readonly="true" /></td>
  </tr>
  <tr>
    <td align="right">nota</td>
    <td align="left">
       <html:textarea property="notaAbbandono" rows="4" cols="32" readonly="true" />
    </td>
  </tr>
  <tr>
    <td colspan="3" align="center"><input type="button" value="Elimina" onclick="invia();" />
&nbsp;&nbsp;
<input type="button" name="abort" value="Annulla" onclick="window.close();" /></td>
    </tr>
  </table>
</html:form>
</body>
</html>
