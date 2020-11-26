// JavaScript Document
function abandon(destinazione) {
	if (openSession){
		var alrt = (document.forms[0].updDoc!=null && document.forms[0].updDoc.value==1)?"\n ATTENZIONE, SONO STATI CARICATI OD ELIMINATI DEI DOCUMENTI,SE NON SI SALVA IL TROVATO RIMARRA' IN STATO INCONSISTENTE!":"";
		if (confirm("Dati non salvati, se si continua le modifiche verranno perse. Continuare?"+alrt)) {
			window.location.href=destinazione;
		}
	} else {
		window.location.href=destinazione;
	} 
}

function openHelp(targetURL) {
	var features = 	
	"resizable=yes,scrollbars=yes,modal=yes" +
	",top=100,left=100,width=820,height=750";
	//window.open(targetURL, "help", features).focus();
	window.open("Help.jsp?target="+targetURL, "help", features).focus();
}

var dom = (document.getElementById) ? true : false; 
var ns5 = ((navigator.userAgent.indexOf("Gecko")>-1) && dom) ? true: false; 
var ie5 = ((navigator.userAgent.indexOf("MSIE")>-1) && dom) ? true : false; 
var ns4 = (document.layers && !dom) ? true : false; 
var ie4 = (document.all && !dom) ? true : false; 

function GetObj(id) { 
  if (dom) return document.getElementById(id);
  return (ns4) ? document.layers[id] : (ie4) ? document.all[id] : (ie5||ns5) ? document.getElementById(id) : null; 
}

function DynWrite(id, S) {
	GetObj(id).innerHTML=S;
	return true;	
}

/* This script and many more are available free online at
The JavaScript Source!! http://www.javascriptsource.com
Created by: HunBug | http://www.astral-consultancy.co.uk/ */

var fadeOpacity  = new Array();
var fadeTimer    = new Array();
var fadeInterval = 100;  // milliseconds

function fade(o,d) {
  // o - Object to fade in or out.
  // d - Display, true =  fade in, false = fade out

  var obj = document.getElementById(o);

  if((fadeTimer[o])||(d&&obj.style.display!='block')||(!d&&obj.style.display=='block')) {
    if(fadeTimer[o])
      clearInterval(fadeTimer[o]);
    else
      if(d) fadeOpacity[o] = 0;
      else  fadeOpacity[o] = 9;
  
    obj.style.opacity = "."+fadeOpacity[o].toString();
    obj.style.filter  = "alpha(opacity="+fadeOpacity[o].toString()+"0)";
    
    if(d) {
      obj.style.display = 'block';
      fadeTimer[o] = setInterval('fadeAnimation("'+o+'",1);',fadeInterval);
    } else
      fadeTimer[o] = setInterval('fadeAnimation("'+o+'",-1);',fadeInterval);
  }
}

function fadeAnimation(o,i) {
  // o - o - Object to fade in or out.
  // i - increment, 1 = Fade In

  var obj = document.getElementById(o);
  fadeOpacity[o] += i;
  obj.style.opacity = "."+fadeOpacity[o].toString();
  obj.style.filter  = "alpha(opacity="+fadeOpacity[o].toString()+"0)";

  if((fadeOpacity[o]=='9')|(fadeOpacity[o]=='0')) {
    if(fadeOpacity[o]=='0')
      obj.style.display = 'none';
    else {
      obj.style.opacity = "1";
      obj.style.filter  = "alpha(opacity=100)";
    }

    clearInterval(fadeTimer[o]);
    delete(fadeTimer[o]);
    delete(fadeTimer[o]);
    delete(fadeOpacity[o]);
  }  
}

function sortSelect(selElem) {
    var tmpAry = new Array();
    for (var i=0;i<selElem.options.length;i++) {
        tmpAry[i] = new Array();
        tmpAry[i][0] = selElem.options[i].text;
        tmpAry[i][1] = selElem.options[i].value;
    }
    tmpAry.sort();
    while (selElem.options.length > 0) {
        selElem.options[0] = null;
    }
    for (var i=0;i<tmpAry.length;i++) {
        var op = new Option(tmpAry[i][0], tmpAry[i][1]);
        selElem.options[i] = op;
    }
    return;
}

function openDocs(nsrif, entita, tipoDocumentoId) {
	if(entita!=null && entita>=0){
		var ref = window.open('Documenti.do?nsrif='+nsrif+'&entita='+entita+'&tipoDocumentoId='+tipoDocumentoId,'popup_attach','height=500,width=500,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		ref.focus();
	} else {
		alert('E\' necessario effettuare prima un salvataggio dei dati per caricare l\'allegato');
	}
}

function openVDocs(nsrif, entita, tipoDocumentoId) {
	if(entita!=null && entita.length>0){
		var ref = window.open('Contratti.do?nsrif='+nsrif+'&entita='+entita+'&tipoDocumentoId='+tipoDocumentoId,'popup_attach','height=500,width=500,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
		ref.focus();
	} else {
		alert('E\' necessario effettuare prima un salvataggio dei dati per caricare l\'allegato');
	}
}

function readDocs(nsrif, entita, tipoDocumentoId) {
	var ref = window.open('Documenti.do?readOnly=true&nsrif='+nsrif+'&entita='+entita+'&tipoDocumentoId='+tipoDocumentoId,'popup_attach','height=500,width=500,scrollbars=yes,toolbar=no,status=no,location=no,screenX=100,screenY=20,top=20,left=100','fullscreen=no');
	ref.focus();
	
}

function parseDate(input) {
	if(input == null) return null;
	var parts = input.split('/');
	// new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
	return new Date(parts[2], parts[1]-1, parts[0]); // Note: months are 0-based
}

function renderDate(input) {
	if(input == null) return null;
	var dd = input.getDate();
	if ( dd < 10 ) dd = '0' + dd;
	var mm = input.getMonth()+1;
	if ( mm < 10 ) mm = '0' + mm;
	return dd+'/'+mm+'/'+input.getFullYear();
}
