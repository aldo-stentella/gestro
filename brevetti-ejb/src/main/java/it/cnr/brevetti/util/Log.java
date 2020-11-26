package it.cnr.brevetti.util;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.logging.LogFactory;
public final class Log {
   private org.apache.commons.logging.Log realLog;
   @SuppressWarnings("unchecked")
public static Log getInstance(Class clazz) {
       return new Log( LogFactory.getLog(clazz) );
   }
   private Log(org.apache.commons.logging.Log realLog) {
       this.realLog = realLog;
   }
   public final void fatal(Throwable throwable, Object... messageParts) {
       if (this.realLog.isFatalEnabled()) {
           this.realLog.fatal(combineParts(messageParts), throwable);
       }
   }
   public final void error(Throwable throwable, Object... messageParts) {
       if (this.realLog.isErrorEnabled()) {
           this.realLog.error(combineParts(messageParts), throwable);
       }
   }
   public final void warn(Throwable throwable, Object... messageParts) {
       if (this.realLog.isWarnEnabled()) {
           this.realLog.warn(combineParts(messageParts), throwable);
       }
   }
   public final void info(Throwable throwable, Object... messageParts) {
       if (this.realLog.isInfoEnabled()) {
           this.realLog.info(combineParts(messageParts), throwable);
       }
   }
   public final void debug(Throwable throwable, Object... messageParts) {
       if (this.realLog.isDebugEnabled()) {
           this.realLog.debug(combineParts(messageParts), throwable);
       }
   }
   public final void trace(Throwable throwable, Object... messageParts) {
       if (this.realLog.isTraceEnabled()) {
           this.realLog.trace(combineParts(messageParts), throwable);
       }
   }
   public final void fatal(Object... messageParts) {
       if (this.realLog.isFatalEnabled()) {
           this.realLog.fatal(combineParts(messageParts));
       }
   }
   public final void error(Object... messageParts) {
       if (this.realLog.isErrorEnabled()) {
           this.realLog.error(combineParts(messageParts));
       }
   }
   public final void warn(Object... messageParts) {
       if (this.realLog.isWarnEnabled()) {
           this.realLog.warn(combineParts(messageParts));
       }
   }
   public final void info(Object... messageParts) {
       if (this.realLog.isInfoEnabled()) {
           this.realLog.info(combineParts(messageParts));
       }
   }
   public final void debug(Object... messageParts) {
       if (this.realLog.isDebugEnabled()) {
           this.realLog.debug(combineParts(messageParts));
       }
   }
   public final void trace(Object... messageParts) {
       if (this.realLog.isTraceEnabled()) {
           this.realLog.trace(combineParts(messageParts));
       }
   }
   private String combineParts(Object[] messageParts) {
       StringBuilder builder = new StringBuilder(128);
       for (Object part : messageParts) {
           if (part instanceof Object[]) {
               builder.append( Arrays.toString((Object[]) asObjectArray(part) ));
           }
           else {
               builder.append(part);
           }
       }

       return builder.toString();
   }
   private static Object[] asObjectArray(Object in) {
       if (in == null || !in.getClass().isArray()) {
           throw new IllegalArgumentException("Parameter to asObjectArray must be a non-null array.");
       }
       else if (in instanceof Object[]) {
           return (Object[]) in;
       }
       else {
           int length = Array.getLength(in);
           Object[] out = new Object[length];
           for (int i=0; i<length; ++i) {
               out[i] = Array.get(in, i);
           }

           return out;
       }
   }
}