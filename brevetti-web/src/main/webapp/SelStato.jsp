<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<script language="JavaScript">
<!--
<%
String cs = (String)request.getAttribute("cs");
String id = (String)request.getAttribute("id");
String selected = (String)request.getAttribute("selected");
if(cs==null){
	if(selected!=null){
%>
  window.opener.eval('param1=<%="\""+selected.replaceAll("\'","\\\\\'")+"\"" %>');
  window.opener.eval('param2="<%=id %>"');
  window.opener.eval("aggiornaSta();");
<%
	}
%>
  function popolate(){  
	  window.close();
  }
<%
} else {
%>
var arr = new Array(<%=cs%>);
function popolate(){
	for(var i=0; i<arr.length; i++){
		document.form.combo.options[i]=new Option(arr[i].substring(0,arr[i].indexOf("|")),arr[i]);
	}
}
function doFilter(patt) {
	var el = "";
	popolate();
	var eln = document.form.combo.options.length;
	for(var i=0; i<eln; i++){
		el = document.form.combo.options[i].text.toUpperCase();
		if(el.indexOf(patt.toUpperCase())==-1){
			if(document.all){
				document.form.combo.options.remove(i);
			} else {
				document.form.combo.options[i] = null;
			}
			i--;
			eln--;
		}
	}
	if(eln==0) document.form.combo.options[0]=new Option('Nessun elemento trovato','');
}
function doSubmit(){
	var list = document.form.combo;
	var current = list.options[list.selectedIndex].value;
	if(list.selectedIndex>-1 && current.length>0){
		document.form.submit();
	}
}
<%
} 
%>
//-->
</script>
</head>
<body>
<!-- #BeginEditable "Detail" -->
<!-- meccanismo per leggere la lista in apertura di pagina -->
<img src="images/leaf.gif" height="1" width="1" onload='popolate();' /> 
<form name="form" method="post" action='SelStato.do'>
<table width="800">
	<tr>
		<td class='text'>Seleziona</td>
	</tr>
	<tr>
		<td><select name="combo" size="8">
		</select></td>
	</tr>
	<tr>
		<td class='text'>Ricerca</td>
	</tr>
	<tr>
		<td><input name="filter" type="text" onchange="doFilter(this.value);" onkeyup="doFilter(this.value);" /></td>
	</tr>
	<tr>
		<td align='center'><input type="button" value="Seleziona" onclick="doSubmit()"/> &nbsp;&nbsp;&nbsp;
		<input type="button" value="Annulla" onclick='window.close();' /></td>
	</tr>
</table>
</form>
</body>
</html>
