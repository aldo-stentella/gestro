<%@ page language="java" %>
<%@ include file="/WEB-INF/jsp/layout/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Brevetti CNR - </title>
<script language="JavaScript" type="text/JavaScript">
function readNote(){
	document.forms[0].note.value = window.opener.currentNote.value;
}

function writeNote(){
	window.opener.eval("param3='" + document.forms[0].note.value.replace(/\n/g,"\\n").replace(/\'/g,"\\'") + "';");
	window.opener.eval("aggiornaNota();");
	self.close();
}
</script>
</head>

<body onload="readNote();">
<form action="">
<p align="center">
  <textarea name="note" cols="70" rows="10">
  </textarea>
</p>
<p align="center">
    <c:choose>
	    <c:when test="${sessionScope.amministraTrovati}">
		  <input type="button" name="abort" value="Salva" onclick="writeNote();" />
		  &nbsp;&nbsp;
		  <input type="button" name="abort" value="Annulla" onclick="window.close();" />
	    </c:when>
	    <c:otherwise>
		  <input type="button" name="close" value="Chiudi" onclick="window.close();" />
	    </c:otherwise>
	</c:choose>
</p>
</form>
</body>
</html>
