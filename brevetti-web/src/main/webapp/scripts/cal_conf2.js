
//Define calendar(s): addCalendar ("Unique Calendar Name", "Window title", "Form element's name", Form name")
addCalendar("dataRichiesta", "Seleziona Data Richiesta", "dataRichiesta", "datiTrovato");
addCalendar("dataDeposito", "Seleziona Data", "dataDepositoX", "datiDepEst");
addCalendar("dataRilascio", "Seleziona Data", "dataRilascioDepX", "datiDepEst");
addCalendar("dataPubblicazione", "Seleziona Data", "dataPubblicazioneX", "datiDepEst");
addCalendar("dataAbbandono", "Seleziona Data", "dataAbbandonoX", "datiDepEst");
addCalendar("dataDeposito3", "Seleziona Data", "dataDepositoX", "datiDepoWiz");
addCalendar("dataRilascio3", "Seleziona Data", "dataRilascioDepX", "datiDepoWiz");
addCalendar("dataPubblicazione3", "Seleziona Data", "dataPubblicazioneX", "datiDepoWiz");
addCalendar("dataAbbandono3", "Seleziona Data", "dataAbbandonoX", "datiDepoWiz");
addCalendar("dataDeposito1", "Seleziona Data", "dataDeposito1", "trovatoQuery");
addCalendar("dataDeposito2", "Seleziona Data", "dataDeposito2", "trovatoQuery");
addCalendar("dataRilascio1", "Seleziona Data", "dataRilascio1", "trovatoQuery");
addCalendar("dataRilascio2", "Seleziona Data", "dataRilascio2", "trovatoQuery");
addCalendar("dataAbbandono1", "Seleziona Data", "dataAbbandono1", "trovatoQuery");
addCalendar("dataAbbandono2", "Seleziona Data", "dataAbbandono2", "trovatoQuery");
addCalendar("dataAbbandono2", "Seleziona Data", "dataAbbandono2", "trovatoQuery");
addCalendar("_dataFattura", "Seleziona Data", "_dataFattura", "datiFattura");
addCalendar("_dataImpegno", "Seleziona Data", "_dataImpegno", "datiFattura");
addCalendar("_dataFattura1", "Seleziona Data", "_dataFattura1", "fatturaQuery");
addCalendar("_dataFattura2", "Seleziona Data", "_dataFattura2", "fatturaQuery");
addCalendar("dataInizio", "Seleziona Data", "dataInizio", "iform");
addCalendar("dataFine", "Seleziona Data", "dataFine", "iform");
addCalendar("dataTrasm1", "Seleziona Data", "dadata", "iform");
addCalendar("dataTrasm2", "Seleziona Data", "adata", "iform");
addCalendar("dataVerbale", "Seleziona Data", "dataVerbale", "iform");

// default settings for English
// Uncomment desired lines and modify its values
// setFont("verdana", 9);
setWidth(90, 1, 15, 1);
// setColor("#cccccc", "#cccccc", "#ffffff", "#ffffff", "#333333", "#cccccc", "#333333");
// setFontColor("#333333", "#333333", "#333333", "#ffffff", "#333333");
setFormat("dd/mm/yyyy");
// setSize(200, 200, -200, 16);

// setWeekDay(0);
// setMonthNames("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
setMonthNames("Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre");

// setDayNames("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
// setLinkNames("[Close]", "[Clear]");
setLinkNames("[Chiudi]", "[Azzera campo]");
