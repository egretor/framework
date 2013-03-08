package unknown.framework.module.annotation;

/**
 * 注解类型枚举
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
	UUID,
	/**
	 * 字符串类型
	 * 
	 * java: java.lang.String
	 * 
	 * MySQL: TEXT
	 * 
	 * Oracle: VARCHAR(4000)
	 */
	STRING,
	/**
	 * 文本类型
	 * 
	 * java: java.lang.String
	 * 
	 * MySQL: LONGTEXT
	 * 
	 * Oracle: CLOB
	 */
	TEXT,
	/**
	 * 布尔类型
	 * 
	 * java: java.lang.Boolean(boolean)
	 * 
	 * MySQL: BIT
	 * 
	 * Oracle: NUMBER(1)
	 */
	BOOLEAN,
	/**
	 * 日期类型
	 * 
	 * java: java.util.Date
	 * 
	 * MySQL: DATETIME
	 * 
	 * Oracle: TIMESTAMP
	 */
	DATE,
	/**
	 * 整数类型
	 * 
	 * java: java.lang.Integer(int)
	 * 
	 * MySQL: INT
	 * 
	 * Oracle: NUMBER(16)
	 */
	INTEGER,
	/**
	 * 长整数类型
	 * 
	 * java: java.lang.LONG(long)
	 * 
	 * MySQL: BIGINT
	 * 
	 * Oracle: NUMBER(32)
	 */
	LONG,
	/**
	 * 浮点数类型
	 * 
	 * java: java.lang.Double(double)
	 * 
	 * MySQL: DOUBLE
	 * 
	 * Oracle: NUMBER(29,9)
	 */
	DOUBLE,
	/**
	 * 货币类型（分）
	 * 
	 * java: java.lang.LONG(long)
	 * 
	 * MySQL: BIGINT
	 * 
	 * Oracle: NUMBER(32)
	 */
	CURRENCY
}
