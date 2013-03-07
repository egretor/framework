package unknown.framework.module.annotation;

/**
 * 类型枚举
 */
public enum Types {
	/**
	 * 编号类型
	 * 
	 * java: java.lang.String
	 * 
	 * MySQL: VARCHAR(32)
	 * 
	 * Oracle: VARCHAR(32)
	 */
	Uuid,
	/**
	 * 字符串类型
	 * 
	 * java: java.lang.String
	 * 
	 * MySQL: TEXT
	 * 
	 * Oracle: VARCHAR(4000)
	 */
	String,
	/**
	 * 文本类型
	 * 
	 * java: java.lang.String
	 * 
	 * MySQL: LONGTEXT
	 * 
	 * Oracle: CLOB
	 */
	Text,
	/**
	 * 布尔类型
	 * 
	 * java: java.lang.Boolean(boolean)
	 * 
	 * MySQL: BIT
	 * 
	 * Oracle: NUMBER(1)
	 */
	Boolean,
	/**
	 * 日期类型
	 * 
	 * java: java.util.Date
	 * 
	 * MySQL: DATETIME
	 * 
	 * Oracle: TIMESTAMP
	 */
	Date,
	/**
	 * 整数类型
	 * 
	 * java: java.lang.Integer(int)
	 * 
	 * MySQL: INT
	 * 
	 * Oracle: NUMBER(16)
	 */
	Integer,
	/**
	 * 长整数类型
	 * 
	 * java: java.lang.LONG(long)
	 * 
	 * MySQL: BIGINT
	 * 
	 * Oracle: NUMBER(32)
	 */
	Long,
	/**
	 * 浮点数类型
	 * 
	 * java: java.lang.Double(double)
	 * 
	 * MySQL: DOUBLE
	 * 
	 * Oracle: NUMBER(29,9)
	 */
	Double,
	/**
	 * 货币类型（分）
	 * 
	 * java: java.lang.LONG(long)
	 * 
	 * MySQL: BIGINT
	 * 
	 * Oracle: NUMBER(32)
	 */
	Currency
}
