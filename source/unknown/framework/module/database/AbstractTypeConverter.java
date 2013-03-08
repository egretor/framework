package unknown.framework.module.database;


/**
 * 类型转换器抽象类
 */
public abstract class AbstractTypeConverter {
	/**
	 * 获取Java值
	 * 
	 * @param fieldMap
	 *            字段映射
	 * @param value
	 *            数据库值
	 * @return Java值
	 */
	public abstract Object getJava(FieldMap fieldMap, Object value);

	/**
	 * 获取数据库值
	 * 
	 * @param value
	 *            Java值
	 * @return 数据库值
	 */
	public abstract Object getDatabase(Object value);
}
