package it.cnr.brevetti.util;
import org.apache.commons.lang.StringUtils;
public class KeyFactory {
	private static final String KEY = ApplicationProperties.getInstance().getEncryptKey();

	public static String getKey(final int pos, final int len) {
		String key = KEY + KEY;
		int start = Math.abs((pos + KEY.hashCode()) % KEY.length());
		int lenght = len > KEY.length() ? 0x10 : len;
		return StringUtils.reverse(StringUtils.mid(key, start, lenght));
	}
}