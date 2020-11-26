<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Brevetti CNR - Dettaglio valorizzazione</title>
<script type="text/javascript" language="javascript1.4">
</script>
</head>
<body>
<html:form action="InfoVal.do">
  <table width="500" border="0" align="center" cellpadding="5" class="area">
  <tr>
    <td colspan="2" align="center" class="titolo">Valorizzazione</td>
    </tr>
  <tr>
    <td>Azione</td>
    <td width="309">
    	<html:text property="tipoValorizzazioneNome" readonly="true" size="40" />
    </td>
  </tr>
  <tr>
    <td>Nome progetto</td>
    <td><html:text property="nomeProgetto" readonly="true" size="40" /></td>
  </tr>
  <tr>
    <td>Referente</td>
    <td><html:text property="referente" readonly="true" size="40" /></td>
  </tr>
  <tr>
    <td>Data inizio</td>
    <td><html:text property="dataInizio" readonly="true" size="10" /></td>
  </tr>
  <tr>
    <td>Data fine</td>
    <td><html:text property="dataFine" readonly="true" size="10" /></td>
  </tr>
  <tr>
    <td colspan="2"><hr /></td>
    </tr>
  <tr>
    <td>Azienda</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>Nome</td>
    <td><html:text property="aziendaNome" readonly="true" size="40" /></td>
  </tr>
  <tr>
    <td>Citt&agrave;</td>
    <td><html:text property="aziendaCitta" readonly="true" size="30" /></td>
  </tr>
  <tr>
    <td>Regione / Nazione</td>
    <td><html:text property="aziendaRegione" readonly="true" size="30" /></td>
  </tr>
  <tr>
    <td colspan="2"><hr /></td>
    </tr>
  <tr>
    <td>Note</td>
    <td><html:textarea property="note" rows="5" cols="50" readonly="true" /></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input type="button" value="Chiudi" onclick="self.close()" /></td>
    </tr>
  </table>
</html:form>
</body>
</html>
