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
String act = (String)request.getAttribute("act");
String tipo = (String)request.getAttribute("tipo");
String id1 = (String)request.getAttribute("id1");
String nome1 = (String)request.getAttribute("nome1");
Integer sel1 = (Integer)request.getAttribute("sel1");
String id2 = (String)request.getAttribute("id2");
String nome2 = (String)request.getAttribute("nome2");
Integer sel2 = (Integer)request.getAttribute("sel2");
String id3 = (String)request.getAttribute("id3");
String nome3 = (String)request.getAttribute("nome3");
Integer sel3 = (Integer)request.getAttribute("sel3");
String altraClassificazione = (String)request.getAttribute("altraClassificazione");
String classificazioneInternDepId = (String)request.getAttribute("id");
boolean ipc = (id3!=null);	
%>
var id1 = new Array(<%=id1%>);
var nome1 = new Array(<%=nome1%>);
var id2 = new Array(<%=id2%>);
var nome2 = new Array(<%=nome2%>);
var id3 = new Array(<%=id3%>);
var nome3 = new Array(<%=nome3%>);

function popolate(){
<%
if(act.startsWith("close")){
%>
	window.opener.eval("aggiornaCla();");
	window.close();
<%
	}else{
%>
	for(var i=0; i<id1.length; i++){
		document.form.liv1.options[i]=new Option(nome1[i],id1[i]);
		if(id1[i]=='<%=sel1%>'){
			document.form.liv1.options[i].selected = true;
		}
	}
	for(var i=0; i<id2.length; i++){
		document.form.liv2.options[i]=new Option(nome2[i],id2[i]);
		if(id2[i]=='<%=sel2%>'){
			document.form.liv2.options[i].selected = true;
		}
	}
<%
		if(ipc){
%>
	for(var i=0; i<id3.length; i++){
		document.form.liv3.options[i]=new Option(nome3[i],id3[i]);
		if(id3[i]=='<%=sel3%>'){
			document.form.liv3.options[i].selected = true;
		}
	}
<%
		}
	}
%>
}

function doFilter() {
	document.form.aggiorna.value = '1';
	document.form.submit();
}

function doSubmit(){
	if((<%=!ipc%> && !document.form.liv1.options[0].selected) || !document.form.liv3.options[0].selected){
		document.form.aggiorna.value = '0';
		document.form.submit();
	}
}

//-->
</script>
</head>
<body onload='popolate();'>
<form name="form" method="post" action='SelClasse.do'>
	<input type="hidden" name="aggiorna" value="0" />
	<input type="hidden" name="tipo" value="<%=tipo %>" />
	<input type="hidden" name="id" value="<%=classificazioneInternDepId %>" />
  <table width="542" cellspacing="8">
    <tr>
		<td class='text'>Seleziona</td>
	</tr>
	<tr>
		
      <td><select name="liv1" onchange="doFilter();">
        </select></td>
	</tr>
	<tr>
		
      <td><select name="liv2" onchange="doFilter();" style="font-family: Arial, Helvetica, sans-serif; font-size: 11px;width: 900px;">
        </select></td>
	</tr>
<%
	if(ipc){
%>
	<tr>
		
      <td><select name="liv3"style="font-family: Arial, Helvetica, sans-serif; font-size: 11px;width: 900px;">
        </select></td>
	</tr>
	<tr>
		<td>
			<input type="text" name="altraClassificazione" value="<%=altraClassificazione %>" />
		</td>
	</tr>
<%
	}
%>
	<tr>
		<td align='center'><input type="button" value="Seleziona" onclick="doSubmit();" /> &nbsp;&nbsp;&nbsp;
		<input type="button" value="Annulla" onclick='window.close();' /></td>
	</tr>
</table>
</form>
</body>
</html>
