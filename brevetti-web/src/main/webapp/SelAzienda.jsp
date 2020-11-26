<%@page import="it.cnr.brevetti.util.Version"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<title>Selezione Azienda</title>
<script language="JavaScript">
<!--
	var rifs = new Array();
	var tmp = new Array();
	
	function inizialize() {
		rifs = document.forms[0].aziendaId.options;
		for(var i=0; i<rifs.length; i++){
			tmp[i]=rifs[i];
		}
		if(document.all){
			rifs.remove(0);
		} else {
			rifs[0] = null;
		}
	}
	
	function popolate(){
		for(var i=0; i<tmp.length; i++){
			rifs[i]=tmp[i];
		}
	}
	
	function doFilter(patt) {
		var el = "";
		popolate();
		var eln = rifs.length;
		for(var i=0; i<eln; i++){
			el = rifs[i].text.toUpperCase();
			if(el.indexOf(patt.toUpperCase())==-1){
				if(document.all){
					rifs.remove(i);
				} else {
					rifs[i] = null;
				}
				i--;
				eln--;
			}
		}
		if(eln==0) rifs[0]=new Option('Nessun elemento trovato','§');
	}
	
	function doSubmit(){
		var ndx = document.forms[0].aziendaId.selectedIndex;
		if(ndx<0 || document.forms[0].aziendaId.options[ndx].value=='§')
			fade('errors',true);
		else{
			var re = new RegExp("\'", "g");
			window.opener.eval("param3='" +  document.forms[0].aziendaId.options[ndx].value+ "|" + document.forms[0].aziendaId.options[ndx].text.replace(re,"\\\'") + "';");
			window.opener.eval("aggiornaAzienda();");
			self.close();
		}
		
	}

//-->
</script>
</head>
<body onload="inizialize()">
<html:form method="post" action='GesVal.do'>
<table class="area">
   <tr>
    <td align="left">
    	Aziende:<br />
		<html:select property="aziendaId" style="font-family: Arial, Helvetica, sans-serif; font-size: 12px;width: 600px;">
  			<html:options collection="aziende" property="value" labelProperty="label" />
    	</html:select><br />
    	<input type="text" name="filter" onchange="doFilter(this.value);" onkeyup="doFilter(this.value);" size="30" /> (filtro)
    </td>
  </tr>

	<tr>
		<td align="left"><input type="button" value="Seleziona" onclick="doSubmit()"/> &nbsp;&nbsp;&nbsp;
		<input type="button" value="Annulla" onclick='window.close();' /></td>
	</tr>
</table>
</html:form>
</body>
</html>
