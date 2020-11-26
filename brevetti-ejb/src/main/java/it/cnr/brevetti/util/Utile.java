package it.cnr.brevetti.util;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;



@SuppressWarnings("rawtypes")
public class Utile {

	public static final long DAY 	= 86400000L;
	public static final long HOUR 	= 3600000L;
	public static final long MINUTE = 60000L;
	public static final long MONTH 	= 2592000000L;
	public static final long SECOND = 1000L;
	public static final long WEEK 	= 604800000L;
	public static final long YEAR 	= 31536000000L;	
	public static final String DATA_FORMAT_DEFAULT = "dd.MM.yyyy (HH:mm:ss)";


	public static boolean isEmptyOrNull(Object[] a) {
		return a == null || a.length == 0 ? true : false;
	}
	public static boolean isNotEmptyOrNull(Object[] a) {
		return ! isNotEmptyOrNull(a);
	}
	/** ritorna la risorsa indicata come input stream utilizzando il class loader */
	public static InputStream getResource(String resourceName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader != null) {
			return loader.getResourceAsStream(resourceName);
		}
		// attempt to load from the system classpath
		return ClassLoader.getSystemResourceAsStream(resourceName);
	}
	/** ritorna il contenuto dello stream in formato stringa */
	public static String getResourceAsString(String resourceName) throws Exception {
		return IOUtils.toString(Utile.getResource(resourceName), Charset.defaultCharset());
	}
	/** ritorna true se l'email address indicato  valido */
	public static boolean isEmail(String address) {
		return GenericValidator.isEmail(address);
	}
	/** ritorna true se il testo indicato  null o vuoto */
	public static boolean isBlankOrNull(String text) {
		return GenericValidator.isBlankOrNull(text);
	}
	public static boolean isNotBlankOrNull(String text) {
		return !isBlankOrNull(text);
	}
	/** sostituisce in text s1 con s2 */
	public static String replace(String text, String s1, Object s2) {
		String replace = s2 == null ? "" : s2.toString();
		return StringUtils.replace(text, s1, replace);
	}	
	/** ritorna il tempo trascorso da start ad ora in formato stringa */
	public static String getElapsed(long start) {
		long milliseconds = new Date().getTime() - start;
		int timeInSeconds = (int) (milliseconds / 1000);
		milliseconds = milliseconds - timeInSeconds * 1000;
		int hours, minutes, seconds;
		hours = timeInSeconds / 3600;
		timeInSeconds = timeInSeconds - (hours * 3600);
		minutes = timeInSeconds / 60;
		timeInSeconds = timeInSeconds - (minutes * 60);
		seconds = timeInSeconds;
		StringBuffer sb = new StringBuffer();
		if (hours > 0) sb.append(hours + "h:");
		if (minutes > 0) sb.append(minutes + "m:");
		if (seconds > 0) sb.append(seconds + "s:");
		if (milliseconds > 0) sb.append(milliseconds + "ms");
		return sb.toString();
	}
	/** ritorna il formato data di default **/
	public static String getDefaultDateFormat(Date date) {
		String pattern = System.getProperty("it.cnr.siper.default.date.format");
		if (null == pattern) pattern = DATA_FORMAT_DEFAULT;
		DateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	/** ritorna il formato data di default **/
	public static String getDefaultShortDateFormat(Date date) {
		String pattern = "dd.MM.yy (HH:mm:ss)";
		DateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	/** ritorna il formato data di default **/
	public static String getDateFormat(Date date, String pattern) {
		if (null == pattern) pattern = DATA_FORMAT_DEFAULT;
		DateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	/** ritorna il nome della prima eccezione nello stack */
	public static String getExceptionName(Throwable t) {
		String name = t.getClass().getSimpleName();
		Throwable cause = t.getCause();
		while(cause != null) {
			name = cause.getClass().getSimpleName();
			cause = cause.getCause();
		}		
		return name;
	}
	/** ritorna il messaggio della prima eccezione nello stack */
	public static String getExceptionMessage(Throwable t) {
		String message = t.getClass().getSimpleName();
		Throwable cause = t.getCause();
		while(cause != null) {
			message = cause.getClass().getSimpleName();
			cause = cause.getCause();
		}		
		return message;
	}
	/** ritorna gli stacktrace dell'eccezione indicata in formato stringa */
	public static String getExceptionStack(Throwable t) {
		StringBuilder sb = new StringBuilder(t.toString());
		StackTraceElement[] ste = t.getStackTrace();
		if (null !=ste) {
			for (int i = 0; i < ste.length; i++) {
				sb.append("\n\tat " + ste[i]);
			}
		}		
		Throwable parent = t;
        Throwable child;

        // Print the stack trace for each nested exception.
        while((child = parent.getCause()) != null) {
            if (child != null) {
                sb.append("\nCaused by: "  + child.toString());
                ste = child.getStackTrace();
        		if (null !=ste) {
        			for (int i = 0; i < ste.length; i++) {
        				sb.append("\n\tat " + ste[i]);
        			}
        		}
                parent = child;
            }
        }
		return sb.toString();
	}
	public static int getAnno(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	/** ritorna vero se il font indicate  presente nel sistema */
	public static boolean findFont(String name) {
		if (StringUtils.isBlank(name)) return false;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();
		int j = fontNames.length;
		for (int i = 0; i < j; i++) {
			if (name.equalsIgnoreCase(fontNames[i])) {
				return true;
			}
		}
		return false;
	}
	/** rimuove gli spazi all'interno di una stringa */
	public static String eliminaSpazi(String string) {
		if (string == null) return null;
		String text = string.trim();
		StringBuilder sb = new StringBuilder();
		boolean found = false;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (!Character.isSpaceChar(c)) {
				sb.append(c);
				found = false;
			} else if (!found) {
				sb.append(c);
				found = true;
			}
		}
		return sb.toString();
	}
	/** ritorna una stringa riempita a destra di spazi */
	public static String rightPad(String what, int size) {
		return StringUtils.rightPad(what, size);
	}
	/** ritorna true/false in base al contenuto di Boolean */
	public static boolean isTrue(Boolean b) {
		return b == null ? false : b.booleanValue();
	}
	/** ritorna true/false in base al contenuto di Boolean */
	public static boolean isFalse(Boolean b) {
		return !isTrue(b);
	}
	/** ritorna vero se l'anno indicato  bisestile */
	public static boolean isBisestile(int year) {
		return (new GregorianCalendar()).isLeapYear(year);
	}
	/** ritorna il formato canonico gg/mm/aaaa **/
	public static String getCanonicDateFormat(Date date) {
		if (date == null) return "";
		String pattern = "dd/MM/yyyy";
		DateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	public static boolean isEmptyOrNull(Collection c) {
		if (c == null) return true;
		return c.isEmpty();
	}
	public static boolean isNotEmptyOrNull(Collection c) {
		return !isEmptyOrNull(c);
	}
	public static Date getUltimoDelMese(Date date) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));
		return c.getTime();
	}
	public static Date getPrimoDelMese(Date date) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
		return c.getTime();
	}
	public static Date sommaAnni(Date date, int anni) {
		if (date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, anni);
		return calendar.getTime();
	}
	public static Date sommaMesi(Date date, int mesi) {
		if (date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, mesi);
		return calendar.getTime();
	}
	public static Date getDate(int giorno, int mese, int anno) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, giorno);
		calendar.set(Calendar.MONTH, mese - 1);
		calendar.set(Calendar.YEAR, anno);
		return calendar.getTime();
	}
	public static Date getCapodanno(int anno) {
		Date date = getDate(1,1,anno);
		return getPrimoDelMese(date);
	}
	public static Date getFineAnno(int anno) {
		Date date = getDate(31,12,anno);
		return getUltimoDelMese(date);
	}
	/** 
	 * ritorna vero se la data indicata cade all'interno di left e right
	 * i valori null agli estremi sono considerati infiniti
	 * se la data indicata  null e considerata fuori dal range (false)
	 */
	public static boolean between(Date date, Date left, Date right) {
		if (date == null) return false;
		if (right == null && date.after(left)) return true;
		if (right == null) return false; // implicito date <= left
		if (date.before(right) && left == null) return true;
		if (date.before(right) && date.after(left)) return true;
		return false;
	}
	/** ritorna la data indicata meno un giorno */
	public static Date getIeri(Date date) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}
	/** ritorna la data indicata pi un giorno */
	public static Date getDomani(Date date) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, +1);
		return c.getTime();
	}
	public static String getLongDateFormat(Date date) {
		if (date == null) return "";
		DateFormat formatter = new SimpleDateFormat("d MMMMM yyyy", Locale.ITALIAN);
		return formatter.format(date);
	}
	public static String getLongMeseAnno(Date date) {
		if (date == null) return "";
		DateFormat formatter = new SimpleDateFormat("MMMMM yyyy", Locale.ITALIAN);
		return formatter.format(date);
	}
	public static String getCompressedDate(Date date) {
		if (date == null) return "";
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}
	public static Date getDateFromCompressedDate(String date) {
		if (isBlankOrNull(date)) return null;
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	public static void serializza(Serializable object, String path) throws Exception {
		byte[] data = SerializationUtils.serialize(object);
		File file = new File(path + "/" + object.getClass().getName());
		FileUtils.writeByteArrayToFile(file, data);
	}
	public static Serializable deserializza(Class clazz, String path) throws Exception {
		File file = new File(path + "/" + clazz.getName());
		byte[] data = FileUtils.readFileToByteArray(file);
		return (Serializable) SerializationUtils.deserialize(data);
	}
	public static Date getDateNoTime(Date date) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	public static String getFileName(String path) {
		return FilenameUtils.getName(path);
	}
	public static String concatena(String path, String filename) {
		return FilenameUtils.concat(path, filename);
	}
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}
	public static String getMd5Hex(String s){
		return DigestUtils.md5Hex(s.getBytes());
	}
	public static String getMd5(String s) {
		byte[] res1 = DigestUtils.md5(s);
		byte[] res2 = Base64.encodeBase64(res1);
		return "{MD5}" + new String(res2);		
	}
}
