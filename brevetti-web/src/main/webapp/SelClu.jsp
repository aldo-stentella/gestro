<%@page import="it.cnr.brevetti.util.Version"%>
<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 	href="css/standard.css?<%=Version.getVersion()%>" type="text/css" />
<script type="text/javascript" src="scripts/standard.js?<%=Version.getVersion()%>"></script>
<title>Brevetti CNR - Selezione Trovato</title>
<style type="text/css">
#errors {
	top: 40px;
	left: 60px;
    position: absolute;
    width: 400px;
    z-index: 60;
    display: none;
}
#errors2 {
	top: 40px;
	left: 60px;
    position: absolute;
    width: 400px;
    z-index: 60;
    display: none;
}
</style>
<script type="text/javascript" language="JavaScript1.4">
	var rifs = new Array();
	var tmp = new Array();
	function inizialize() {
		rifs = document.forms[0].nsrif.options;
		for(var i=0; i<rifs.length; i++){
			tmp[i]=rifs[i];
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

	function do_submit(){
		var ndx = document.forms[0].nsrif.selectedIndex;
		if(ndx<0 || document.forms[0].nsrif.options[ndx].value=='§')
			fade('errors',true);
		else{
			var re = new RegExp("\'", "g");
			window.opener.eval("param3='" + document.forms[0].nsrif.options[ndx].text.substring(0,75).replace(re ,"\\\'") + "';");
			var ris = window.opener.eval("aggiornaCluster();");
			if(ris){
				self.close();
			} else {
				fade('errors2', true);
			}
		}
	}
</script>
</head>
<body onload="inizialize()">
			  <html:form method="post" action="GesVal.do">
                <table width="96%" border="0" cellpadding="5" class="area" align="center">
                  <tr>
                    <td width="12%" align="left">NSRIF trovato:</td>
                   </tr>
                   <tr>
                    <td width="74%" align="left">
                    	<html:select property="nsrif" size="8">
						    <html:options collection="trovati" property="value" labelProperty="label" />
					    </html:select><br /><input type="text" name="filter" onchange="doFilter(this.value);" onkeyup="doFilter(this.value);" size="30" /> 
					    (filtro)<br />
				    <input type="button" name="Submit" value="Inserisci" onclick="do_submit();" /> <input type="button" value="Annulla" onclick="window.close();" /></td>
                  </tr>
                </table>
 			  </html:form>
 			  <div id="errors" class="errorCloud" onclick="fade('errors',false);"><p align='right'><img src='images/close.gif' /></p><b>Attenzione</b>:<ul><li>E' obbligatorio selezionare un trovato</li></ul><br/></div>
 			  <div id="errors2" class="errorCloud" onclick="fade('errors2',false);"><p align='right'><img src='images/close.gif' /></p><b>Attenzione</b>:<ul><li>trovato gi&agrave; presente nella lista</li></ul><br/></div>
</body>
</html>
