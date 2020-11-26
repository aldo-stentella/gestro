<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="it.cnr.brevetti.trovati.javabeans.TrovatoJB"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	String scheduler = "";
	boolean errore = false;
	TrovatoJB tjb = TrovatoJB.getInstance();
	if ((request.getMethod()).equals("POST")){
		scheduler = request.getParameter("scheduler");
		System.out.println("sched:"+scheduler);
		if(scheduler.startsWith("1")){
	    	String la = request.getRequestURL().toString();
	    	la = la.substring(0,la.indexOf(request.getRequestURI()))+request.getContextPath();
	    	System.out.println("localaddress:"+la);
	    	try {
				tjb.startIndicizzazione(request.getParameter("pattern"));
			} catch (Throwable e) {
				e.printStackTrace();
				errore=true;
			}
		} else if(scheduler.startsWith("0")){
			try {
				tjb.stopIndicizzazione();
			} catch (Throwable e) {
				e.printStackTrace();
				errore=true;
			}
		} else {
	    	tjb.indicizzazioneOra();
		}
	}
	try {
		scheduler = (tjb.listaTimerAttivi()!=null?"1":"0");
	} catch (Throwable e) {
		e.printStackTrace();
		errore=true;
	}
%>
<script language="JavaScript1.4" type="text/javascript">
	function doSubmit(val){
		document.forms[0].scheduler.value=val; 
		document.forms[0].submit();
	}
</script>
</head>
<body>
<form action="Indicizzatore.jsp" method="POST">
	<table width="100%" border="1">
		<tr><td width="386" class="text">Scheduler dell'indicizzatore:<br>
			<br/>
			Configurazione: 
			<br/>
<% 	
        	if(scheduler!=null && scheduler.startsWith("1")){
%>

				<br/>
				<input type='button' value="Interrompi" onclick='doSubmit("0");' />
				<br/>
				Timer attivi: <%= tjb.listaTimerAttivi() %>
<%

           	} else {
%> 
				<input type="text" name="pattern" size="30" value="0 7-20/3 * * *" /><br/>
				<input type='button' value="Schedula" onclick='doSubmit("1");' />
<%
			}
%>
				<input type='button' value="Avvia ora" onclick='doSubmit("9");' />
				<input type='hidden' name='scheduler' value='9' />
		</td></tr>
		<tr><td><strong><font color='red'>
			<%= errore?"Si è verificato un errore avviando/interrompendo lo scheduler":"&nbsp;"%>
		</font></strong></td></tr>
	</table>
</form>
</body>
</html>