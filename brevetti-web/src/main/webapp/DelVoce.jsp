<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Brevetti CNR - Dettaglio fattura</title>

<%
	String act = (String)request.getAttribute("act");
	if(act!=null && act.startsWith("close")){

%>
	<script type="text/javascript" language="javascript1.4">
		window.opener.eval("aggiorna();");
		window.close();
	</script>
<%
	}
%>
</head>
<body>
<html:form action="DelVoce.do" method="POST">
	<html:hidden property="id" />
	<html:hidden property="idRiga" />
            <table width="500" border="0" align="center" cellpadding="5" class="area">
  <tr>
  	<td colspan="2">Eliminare la seguente riga ?</td>
  </tr>
  <tr>
    <td>Trovato di riferimento</td>
    <td>
    	<html:text property="nsrif" readonly="true" />
    </td>
  </tr>
  <tr>
    <td>Causale</td>
    <td>
    	<html:text property="causaleDescrizione" readonly="true" />
	</td>
  </tr>
  <tr>
    <td>N&deg; di annualit&agrave; </td>
    <td><html:text property="n" readonly="true" /></td>
  </tr>
  <tr>
    <td>Nazione</td>
    <td width="309" align="left">
      <html:text property="statoDescrizione" readonly="true" />
	</td>
  </tr>
  <tr>
    <td>F.C.IVA</td>
    <td><html:text property="anticipazione" readonly="true" /></td>
  </tr>
  <tr>
    <td>Imponibile</td>
    <td><html:text property="onorari" readonly="true" /></td>
  </tr>
  <tr>
    <td>IVA</td>
    <td>
    	<html:text property="iva" readonly="true" />
    </td>
  </tr>
  <tr>
    <td>Totale parziale</td>
    <td>
    	<html:text property="parzialeEuro" readonly="true" />
    </td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td><html:submit value="Elimina" />
     &nbsp;
      <input type="button" value="Annulla" onclick="self.close()" /></td>
  </tr>
</table>
</html:form>
</body>
</html>
