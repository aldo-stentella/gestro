/*
 * $Id: MessageTag.java 471754 2006-11-06 14:55:09Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package it.cnr.brevetti.util;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

import it.cnr.brevetti.trovati.actionForms.TrovatoForm;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.CharConversionException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeMap;

/**
 * Custom tag that retrieves an internationalized messages string (with
 * optional parametric replacement) from the <code>ActionResources</code>
 * object stored as a context attribute by our associated
 * <code>ActionServlet</code> implementation.
 *
 * @version $Date: 20-02-2008
 * @author astentella
 * 
 */
public class MessageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	/**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources(
            "org.apache.struts.taglib.bean.LocalStrings");

    // ------------------------------------------------------------- Properties

    /**
     * The servlet context attribute key for our resources.
     */
    protected String bundle = null;

    /**
     * The message key of the message to be retrieved.
     */
    protected String key = null;

    /**
     * Name of the bean that contains the message key.
     */
    protected String name = null;

    /**
     * Name of the property to be accessed on the specified bean.
     */
    protected String property = null;

    /**
     * The scope to be searched to retrieve the specified bean.
     */
    protected String scope = null;

    /**
     * The session scope key under which our Locale is stored.
     */
    protected String localeKey = Globals.LOCALE_KEY;


    public String getBundle() {
        return (this.bundle);
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getKey() {
        return (this.key);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return (this.property);
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getLocale() {
        return (this.localeKey);
    }

    public void setLocale(String localeKey) {
        this.localeKey = localeKey;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Process the start tag.
     *
     * @throws JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	String key; 
    	String tabs = "<table width='100%' border='0' cellpadding='0' cellspacing='0'>\n<tr id='tabs'>";
    	Integer tabNumber = (Integer)pageContext.getRequest().getAttribute("tabNumber");				//tab attivo
    	if(tabNumber==null || tabNumber.intValue()==0)	tabNumber= new Integer(1);
    	TrovatoForm dform = (TrovatoForm)pageContext.getSession().getAttribute("datiTrovato");
    	String[] bundleArray = (String[]) pageContext.getSession().getAttribute("bundleArray");
    	String tBundle = this.bundle;
    	if(dform!=null) 
    		tBundle = bundleArray[dform.getTipiTrovatoId()];
    	ActionMapping mapping = (ActionMapping)pageContext.getRequest().getAttribute("org.apache.struts.action.mapping.instance");
    	ArrayList fwds = new ArrayList(Arrays.asList(mapping.findForwards()));
    	Collections.sort(fwds);
    	for(int k=0;k<fwds.size();){
    		key = "tabs." + fwds.get(k);
    		// Retrieve the message string we are looking for
    		if(Character.isDigit(key.charAt(5))){
	    		String message = TagUtils.getInstance().message(pageContext, tBundle, this.localeKey, key);
	    		if (message == null) {
	    			Locale locale = TagUtils.getInstance().getUserLocale(pageContext, this.localeKey);
	    			String localeVal = (locale == null) ? "default locale" : locale.toString();
	    			JspException e = new JspException(messages.getMessage("message.message","\""+key+"\"",
	    						"\"" + ((bundle == null) ? "(default bundle)" : bundle)+ "\"", localeVal));
	    			TagUtils.getInstance().saveException(pageContext, e);
	    			throw e;
	    		}
	    		if(tabNumber.intValue()==(k+1)){
	    			tabs = tabs.concat("<td id='tabspacer' width='5'><img src='images/spacer.gif' width='5' height='1' /></td>\n" +
	    					"<td id='tabon'>"+ message + "</td>");
	    		} else {
	    			tabs = tabs.concat("<td id='tabspacer' width='5'><img src='images/spacer.gif' width='5' height='1' /></td>\n" +
	    					"<td id='taboff'><div onclick='goTab("+ (k+1) +");'>"+ message + "</div></td>");
	    		}
	    		k++;
    		} else
    			fwds.remove(k);
    	}
    	tabs = tabs.concat("<td id='tabspacer'>&nbsp;</td>\n</tr>\n</table>");
    	TagUtils.getInstance().write(pageContext, tabs);    		

        return (SKIP_BODY);
    }

    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        bundle = Globals.MESSAGES_KEY;
        key = null;
        name = null;
        property = null;
        scope = null;
        localeKey = Globals.LOCALE_KEY;
    }
}
