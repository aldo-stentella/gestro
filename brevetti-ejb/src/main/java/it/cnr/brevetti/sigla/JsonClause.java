package it.cnr.brevetti.sigla;

import java.io.Serializable;
/**
 * Rappresenta una clause da inviare a sigla
 * 
 * @author Aurelio D'Amico
 * @version 1.0 [Sep 19, 2016]
 *
 */
public class JsonClause implements Serializable {
	private static final long serialVersionUID = 1L;
	private String condition;
	private String fieldName;
	private String operator;
	private Object fieldValue;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

}
