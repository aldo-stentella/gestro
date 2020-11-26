<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Brevetti CNR - Dettaglio fattura</title>
<script type="text/javascript" language="javascript1.4">
var arr = new Array(); 

function inizialize(){
	for(var i=0; i<document.forms[0].statiId.options.length; i++){
		arr[i]=document.forms[0].statiId.options[i];
	}
}

function popolate(){
	for(var i=0; i<arr.length; i++){
		document.forms[0].statiId.options[i]= arr[i];
	}
}

function doFilter(patt) {
	var el = "";
	popolate();
	var eln = document.forms[0].statiId.options.length;
	for(var i=0; i<eln; i++){
		el = document.forms[0].statiId.options[i].text.toUpperCase();
		if(el.indexOf(patt.toUpperCase())!=0){
			if(document.all){
				document.forms[0].statiId.options.remove(i);
			} else {
				document.forms[0].statiId.options[i] = null;
			}
			i--;
			eln--;
		}
	}
	if(eln==0) document.forms[0].statiId.options[0]=new Option('Nessun elemento trovato','');
}

function calculate(){
	var fciva = parseFloat(document.forms[0].anticipazione.value);
	var imponibile = parseFloat(document.forms[0].onorari.value);
	//var imposta = parseFloat(document.forms[0].imposta.value);
	var iva = parseFloat(document.forms[0].iva.value); //Math.round(imponibile * imposta) / 100;
	//document.forms[0].iva.value = iva;
	document.forms[0].parzialeEuro.value = Math.round((fciva + imponibile + iva)*100)/100 ;
}

</script>
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
<body onload="inizialize();">
<html:form action="AddVoce.do" method="POST">
	<html:hidden property="id" />
	<html:hidden property="idRiga" />
	<html:hidden property="fattureId" />
	<html:hidden property="percentuale" />
	<html:hidden property="imposta" />
            <table width="500" border="0" align="center" cellpadding="5" class="area">
  <tr>
    <td>Trovato di riferimento</td>
    <td>
    	<html:text property="nsrif" readonly="true" style="background-color:#FFFF00" />
    </td>
  </tr>
  <tr>
    <td>Causale</td>
    <td><html:select property="causaliId">
	    <html:options collection="causali" property="value" labelProperty="label" />
    </html:select></td>
  </tr>
  <tr>
    <td>N&deg; di annualit&agrave; </td>
    <td><html:text property="n" /></td>
  </tr>
  <tr>
    <td>Nazione</td>
    <td width="309" align="left">
      <html:select property="statiId" style="width: 390px;">
        <html:options collection="nazioni" property="value" labelProperty="label" />
      </html:select><br />
      <span class="imaction" onclick="doFilter('');">#</span>
      <span class="imaction" onclick="doFilter('a');">A</span>
      <span class="imaction" onclick="doFilter('b');">B</span>
      <span class="imaction" onclick="doFilter('c');">C</span>
      <span class="imaction" onclick="doFilter('d');">D</span>
      <span class="imaction" onclick="doFilter('e');">E</span>
      <span class="imaction" onclick="doFilter('f');">F</span>
      <span class="imaction" onclick="doFilter('g');">G</span>
      <span class="imaction" onclick="doFilter('h');">H</span>
      <span class="imaction" onclick="doFilter('i');">I</span>
      <span class="imaction" onclick="doFilter('j');">J</span>
      <span class="imaction" onclick="doFilter('k');">K</span>
      <span class="imaction" onclick="doFilter('l');">L</span>
      <span class="imaction" onclick="doFilter('m');">M</span>
      <span class="imaction" onclick="doFilter('n');">N</span>
      <span class="imaction" onclick="doFilter('o');">O</span>
      <span class="imaction" onclick="doFilter('p');">P</span>
      <span class="imaction" onclick="doFilter('q');">Q</span>
      <span class="imaction" onclick="doFilter('r');">R</span>
      <span class="imaction" onclick="doFilter('s');">S</span>
      <span class="imaction" onclick="doFilter('t');">T</span>
      <span class="imaction" onclick="doFilter('u');">U</span>
      <span class="imaction" onclick="doFilter('v');">V</span>
      <span class="imaction" onclick="doFilter('w');">W</span>
      <span class="imaction" onclick="doFilter('x');">X</span>
      <span class="imaction" onclick="doFilter('y');">Y</span>
      <span class="imaction" onclick="doFilter('z');">Z</span>    </td>
  </tr>
  <tr>
    <td>F.C.IVA</td>
    <td><html:text property="anticipazione" /></td>
  </tr>
  <tr>
    <td>Imponibile</td>
    <td><html:text property="onorari" /></td>
  </tr>
  <tr>
    <td>IVA</td>
    <td><html:text property="iva" /></td>
  </tr>
  <tr>
    <td>Totale parziale</td>
    <td><html:text property="parzialeEuro" style="background-color:#FFFF00" readonly="true" />
       &nbsp;
      <input type="button" name="calc" value="&plusmn; Ricalcola" onclick="calculate()" /></td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td><html:submit value="Invia" />
     &nbsp;
      <input type="button" value="Annulla" onclick="self.close()" /></td>
  </tr>
</table>
</html:form>
</body>
</html>
