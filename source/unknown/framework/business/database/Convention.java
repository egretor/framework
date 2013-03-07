package unknown.framework.business.database;

/**
 * 约定类
 */
public class Convention {
	/**
	 * 数据库标识符分隔符
	 */
	protected final static char SEPARATOR = '_';
	/**
	 * 类取值函数前缀
	 */
	protected final static String GET_METHOD_PREFIX = "get";
	/**
	 * 类赋值函数前缀
	 */
	protected final static String SET_METHOD_PREFIX = "set";
	/**
	 * 类布尔类型取值函数前缀
	 */
	protected final static String GET_BOOLEAN_METHOD_PREFIX = "is";
	/**
	 * 类布尔类型赋值函数前缀
	 */
	protected final static String SET_BOOLEAN_METHOD_PREFIX = "set";

	/**
	 * 数据库标识符编码为编程语言标识符
	 * 
	 * TEMPLATE_TABLE编码为TemplateTable
	 * 
	 * @param value
	 *            数据库标识符
	 * @param capitalize
	 *            首字母大写
	 * @return 编程语言标识符
	 */
	public String encodeName(String value, boolean capitalize) {
		String result = null;

		if (value != null) {
			if (!value.isEmpty()) {
				StringBuilder stringBuilder = new StringBuilder();
				boolean upper = capitalize;
				for (int i = 0; i < value.length(); i++) {
					char current = value.charAt(i);
					if ((i > 0) && (current == Convention.SEPARATOR)) {
						upper = true;
						continue;
					} else {
						if (upper) {
							current = Character.toUpperCase(current);
						} else {
							current = Character.toLowerCase(current);
						}
						stringBuilder.append(current);
						upper = false;
					}
				}
				result = stringBuilder.toString();
			}
		}

		return result;
	}

	/**
	 * 编程语言标识符解码为数据库标识符
	 * 
	 * @param value
	 *            编程语言标识符
	 * @param identifierCapital
	 *            标识符大写
	 * @return 数据库标识符
	 */
	public String decodeName(String value, boolean identifierCapital) {
		String result = null;

		if (value != null) {
			if (!value.isEmpty()) {
				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < value.length(); i++) {
					char current = value.charAt(i);
					if ((i > 0) && (Character.isUpperCase(current))) {
						stringBuilder.append(Convention.SEPARATOR);
					}
					stringBuilder.append(current);
				}
				if (identifierCapital) {
					result = stringBuilder.toString().toUpperCase();
				} else {
					result = stringBuilder.toString().toLowerCase();
				}
			}
		}

		return result;
	}

	/**
	 * 编码方法名称
	 * 
	 * @param value
	 *            POJO字段
	 * @param methodPrefix
	 *            方法前缀
	 * @return 方法名称
	 */
	protected String encodeMethodName(String value, String methodPrefix) {
		String result = null;

		if (value != null) {
			if (!value.isEmpty()) {
				StringBuilder stringBuilder = new StringBuilder();

				stringBuilder.append(methodPrefix);
				if (value.length() > 0) {
					stringBuilder.append(value.substring(0, 1).toUpperCase());
				}
				if (value.length() > 1) {
					stringBuilder.append(value.substring(1));
				}
				result = stringBuilder.toString();
			}
		}

		return result;
	}

	/**
	 * 解码方法名称
	 * 
	 * @param value
	 *            方法名称
	 * @param methodPrefix
	 *            方法前缀
	 * @return POJO字段
	 */
	protected String decodeMethodName(String value, String methodPrefix) {
		String result = null;

		if (value != null) {
			if (!value.isEmpty()) {
				StringBuilder stringBuilder = new StringBuilder();

				int prefixLength = methodPrefix.length();
				if (value.length() > prefixLength) {
					value = value.substring(prefixLength);
				}
				if (value.length() > 0) {
					stringBuilder.append(value.substring(0, 1).toLowerCase());
				}
				if (value.length() > 1) {
					stringBuilder.append(value.substring(1));
				}
				result = stringBuilder.toString();
			}
		}

		return result;
	}

	/**
	 * 编码取值方法名称
	 * 
	 * @param value
	 *            POJO字段
	 * @return 方法名称
	 */
	public String encodeGetMethodName(String value) {
		return this.encodeMethodName(value, Convention.GET_METHOD_PREFIX);
	}

	/**
	 * 解码取值方法名称
	 * 
	 * @param value
	 *            方法名称
	 * @return POJO字段
	 */
	public String decodeGetMethodName(String value) {
		return this.decodeMethodName(value, Convention.GET_METHOD_PREFIX);

	}

	/**
	 * 编码赋值方法名称
	 * 
	 * @param value
	 *            POJO字段
	 * @return 方法名称
	 */
	public String encodeSetMethodName(String value) {
		return this.encodeMethodName(value, Convention.SET_METHOD_PREFIX);

	}

	/**
	 * 解码赋值方法名称
	 * 
	 * @param value
	 *            方法名称
	 * @return POJO字段
	 */
	public String decodeSetMethodName(String value) {
		return this.decodeMethodName(value, Convention.SET_METHOD_PREFIX);
	}

	/**
	 * 编码布尔类型取值方法名称
	 * 
	 * @param value
	 *            POJO字段
	 * @return 方法名称
	 */
	public String encodeBooleanGetMethodName(String value) {
		return this.encodeMethodName(value,
				Convention.GET_BOOLEAN_METHOD_PREFIX);
	}

	/**
	 * 解码布尔类型取值方法名称
	 * 
	 * @param value
	 *            方法名称
	 * @return POJO字段
	 */
	public String decodeBooleanGetMethodName(String value) {
		return this.decodeMethodName(value,
				Convention.GET_BOOLEAN_METHOD_PREFIX);

	}

	/**
	 * 编码布尔类型赋值方法名称
	 * 
	 * @param value
	 *            POJO字段
	 * @return 方法名称
	 */
	public String encodeBooleanSetMethodName(String value) {
		return this.encodeMethodName(value,
				Convention.SET_BOOLEAN_METHOD_PREFIX);

	}

	/**
	 * 解码布尔类型赋值方法名称
	 * 
	 * @param value
	 *            方法名称
	 * @return POJO字段
	 */
	public String decodeBooleanSetMethodName(String value) {
		return this.decodeMethodName(value,
				Convention.SET_BOOLEAN_METHOD_PREFIX);
	}
}
