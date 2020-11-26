package it.cnr.brevetti.config;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.lang.StringUtils;

import it.cnr.brevetti.ejb.entities.Property;
import it.cnr.brevetti.util.Utile;

/**
 * Classe per il caricamento e la gestione delle proprietà
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [03-07-2017]
 *
 */
public class Settings implements Serializable {
	public static final String KEY = Settings.class.getName();
	private static final long serialVersionUID = 1L;
	private static Settings instance;
	private Properties properties;
	private Settings() {}
	public static synchronized Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
	public Properties getProperties() {
		return properties;
	}
	public void initialize(List<Property> list) {
		// imposta le proprietà utilizzando le regole commons configuration
		Configuration config = new BaseConfiguration();
		Configuration system = new BaseConfiguration();
		if (list==null) return;
		// caricamento delle properties
		for (Property x : list) {
			String key = x.getId();
			String value = x.getValore();
			if (Utile.isNotBlankOrNull(value)) { 
				if (x.isSistema()) 
					system.setProperty(key,value);
				else 
					config.setProperty(key, value);
			}
		}
		if (!config.isEmpty())
			properties = ConfigurationConverter.getProperties(config);
		if (!system.isEmpty());
			setSystemProperties(ConfigurationConverter.getProperties(system));
	}
	@SuppressWarnings("rawtypes")
	private void setSystemProperties(Properties props) {
		for (Entry x : props.entrySet()) {
			System.setProperty(x.getKey().toString(), x.getValue().toString());
		}		
	}
	public String getValue(String key) {
		return (String) (isEmpty() ? null : properties.getProperty(key));
	}
	// ritorna il valore di key secondo le regole di Properties
	public String get(String key) {
		return isEmpty() ? null : properties.getProperty(key);
	}
	public String toString() {
		return isEmpty() ? "" : ConfigurationUtils.toString(getConfiguration());
	}
	public boolean isEmpty() {
		return properties == null ? false : properties.isEmpty();
	}
	/** Returns a commons.Configuration wrapper of the properties */
	public Configuration getConfiguration() {
		return ConfigurationConverter.getConfiguration(properties); 
	}
	@SuppressWarnings("rawtypes")
	/** returns a subset of properties with key starting with prefix */
	public Properties getSubset(String prefix) {		
		Properties props = null;
		if (!isEmpty()) {
			 props = new Properties();
			for (Entry x : properties.entrySet()) {
				if (StringUtils.startsWithIgnoreCase(x.getKey().toString(), prefix))
					props.put(x.getKey(), x.getValue());
			}
		}
		return props;
	}
}
