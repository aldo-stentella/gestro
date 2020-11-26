 <?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<c:set var="tti" value="${sessionScope.datiTrovato.tipiTrovatoId}" />
<c:set var="bundle" value="${sessionScope.bundleArray[tti]}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Selezione <bean:message key="labels.inventore00" bundle="${bundle}" /> del trovato</title>
<script language="JavaScript" type="text/JavaScript">

<%
	String cs = (String)request.getAttribute("cs");	//"'Nessun elemento trovato (0)'";
	String status = ""+request.getAttribute("status");
	String error = (String)request.getAttribute("error");
	if(error!=null){
		out.write("alert('"+error+"');");
		
	}
	if(status.startsWith("scegli")) {
%>
		
		var arr = new Array(<%=cs%>); 
		
		function on_load(){
			for(var i=0; i < arr.length; i++){
				document.form.combo.options[i]=new Option(arr[i].substring(0,arr[i].indexOf(" (")),arr[i]);
			}
			change(2);
			document.form.tipi_inventore[0].disabled = true;
			document.form.tipi_inventore[1].checked = true;
			document.form.esterni.disabled = true;
			document.form.combo.style.visibility="visible";
			document.form.nuovo.style.visibility="visible";
			document.form.invia.value = 'Mostra dettagli';
		}
		
		function on_submit(){
			if(document.form.combo.selectedIndex>-1){
				document.form.act.value='seleziona';
				document.form.submit();
			}
		}
		function on_add(){
			document.form.act.value='nuovo';
			document.form.submit();
		}
		
		function on_cancel(){
				window.close();
		}
<%
	} else if(status.startsWith("inizia")) {
%> 
			function on_load(){
				document.form.invia.value = 'Ricerca';
				document.form.nuovo.style.visibility="<%=(error!=null)?"visible":"hidden"%>";
			}
			
			function on_submit(){
				if(document.form.matricola.value.length>0 || document.form.esterni.value.length>0){
					document.form.act.value='cerca';
					document.form.submit();
				}
			}
			
			function on_add(){
				document.form.act.value='nuovo';
				document.form.submit();
			}
			
			function on_cancel(){
					window.close();
			}

<%
	 }
%>


function change(n){
	if(n==1){
		document.form.matricola.style.visibility="visible";
		document.form.esterni.style.visibility="hidden";
		document.form.esterni.value="";
	} else if(n==2){
		document.form.matricola.style.visibility="hidden";
		document.form.esterni.style.visibility="visible";
	}
}

</script>
</head>
<body onload="on_load();">
<form name="form" action="SelInventore.do" method="post">
  <input type="hidden" name="act" />
  <table width="752" border="1" cellspacing="15">
    <tr> 
      <td colspan="4"><b>Selezionare la tipologia di <bean:message key="labels.inventore00" bundle="${bundle}" />:</b></td>
    </tr>
    <tr> 
      <td colspan="2" bgcolor="#A35052"> <input name="tipi_inventore" value="1"
			type="radio" checked="checked" onclick="change(1);" /> <strong><font color="#FFFFFF"> 
        Dipendente CNR (per matricola)</font></strong></td>
      <td colspan="2" rowspan="4" bgcolor="#999999"> <select name="combo" size="8" style="visibility:hidden" >
        </select> </td>
    </tr>
    <tr> 
      <td colspan="2" bgcolor="#999999"> <input name="matricola" type="text" size="40" /></td>
    </tr>
    <tr> 
      <td colspan="2" bgcolor="#A35052"><strong><font color="#FFFFFF"> 
        <input name="tipi_inventore" value="2" type="radio"
			onclick="change(2);" />
        <bean:message key="labels.inventore00" bundle="${bundle}" /> esterno (per cognome o parte di esso)</font></strong></td>
    </tr>
    <tr> 
      <td colspan="2" valign="bottom" bgcolor="#999999"> <input type="text" name="esterni" size="40" style="visibility:hidden" /> 
      </td>
    </tr>

    <tr> 
      <td colspan="4" align="center"> <input type="button" name="invia" value="Submit" onclick="on_submit();" /> 
        &nbsp;&nbsp;<input type="button" name="annulla" value="Annulla" onclick='on_cancel();' /> 
        &nbsp;&nbsp;<input type="button" name="nuovo" value="Nuovo <bean:message key="labels.inventore00" bundle="${bundle}" />" onclick='on_add();' style="visibility:hidden" /> 
      </td>
    </tr>
  </table>
</form>
</body>
</html>
