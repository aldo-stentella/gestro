package it.cnr.brevetti.validators;

import it.cnr.brevetti.ejb.entities.Validazione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;


/**
 * @author aldo stentella
 * 
 * 
 * Esempi di sintassi per il campo args:
 * 
 * 	#nomecampostringa == "'valore costante'" (doppio+singolo apice)
 *  #nomecampostringa != null
 *  #nomecampodata1 >= #nomecampodata2
 *  #nomecampodata >= '31/12/2013'
 *  #nomecampodata == null
 *  #nomecamponumerico != 9999.99
 *  #nomecamponumerico1 < #nomecamponumerico2
 */
public class RequiredWhen extends AbstractValidator implements Validation {

	public void validate(AbstractValidatorForm form, Validazione validazione, ActionErrors errors) throws ValidationException {
		Object value = getValue(form, validazione.getField());
		boolean condition = false;
		int compare;
		SimpleDateFormat itForm = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
		StrTokenizer st = new StrTokenizer(validazione.getArgs(),' ', '"');
		@SuppressWarnings("unchecked")
		ArrayList<String> args = new ArrayList<String>(st.getTokenList());
		String s1 = args.get(0);
		Object o1;
		switch (s1.charAt(0)) {
		case '#':			//nome campo form
			o1 = getValue(form,s1.substring(1));
			break;
		case '\'':			//stringa o data
			o1 = StringUtils.center(s1, 1);
			break;
		default:			//numerici
			o1 = NumberUtils.createNumber(s1);
			break;
		}
		String s2 = args.get(2);
		Object o2;
		switch (s2.charAt(0)) {
		case '#':			//nome campo form
			o2 = getValue(form,s2.substring(1));
			break;
		case '\'':			//stringa o data
			o2 = StringUtils.substringBetween(s2, "'");
			break;
		case 'n':			//NULL
		case 'N':
			o2 = null;
			break;
		default:			//numerici
			o2 = NumberUtils.createNumber(s2);
			break;
		}
		char s = args.get(1)
				.replace(">=","G").replace("<=","L").replace("!=","N").replace("==","E")
				.charAt(0);
		boolean b1 = (o1==null || StringUtils.isEmpty(o1.toString()));
		boolean b2 = (o2==null || StringUtils.isEmpty(o2.toString()));
		if(b1 || b2){
			switch (s){
			case '>': // Greater Than
			case '<': // Less Than
				condition =  false;
				break;
			case 'G': // >= Greater Than or Equal to.
			case 'L': // Less Than or Equal to.
			case 'E': // == Equal
				condition = (b1 && b2);
				break;
			case 'N': // != Not Equal
				condition = !(b1 && b2);
				break;
			default :
			}
		} else {
			if(o1 instanceof String)
				try {								//se sono date valide, le compara
					Date d1 = itForm.parse(o1.toString());
					Date d2 = itForm.parse(o2.toString());
					compare = d1.compareTo(d2);
				} catch (ParseException e) {		//altrimenti sono strighe
					compare = ((String) o1).compareTo((String) o2);
				}
			else							//numerici
				compare = ((Comparable)o1).compareTo(o2);
			switch (s){
			case '>': // Greater Than
				condition =  compare>0;
				break;
			case 'G': // >= Greater Than or Equal to.
				condition =  compare>=0;
				break;
			case '<': // Less Than
				condition =  compare<0;
				break;
			case 'L': // Less Than or Equal to.
				condition =  compare<=0;
				break;
			case 'E': // == Equal
				condition = compare==0;
				break;
			case 'N': // != Not Equal
				condition =  compare!=0;
				break;
			default :
			}
		}
		if(condition && StringUtils.isBlank(""+ value))
			errors.add(validazione.getMessage(), new ActionMessage(validazione.getMessage(),validazione.getField()));
	}

}
