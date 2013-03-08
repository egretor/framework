package unknown.framework.module.database;

import java.lang.reflect.Method;

import unknown.framework.module.annotation.Types;

/**
 * 数据库字段映射类
 */
public class FieldMap {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 注解类型
	 */
	private Types type;
	/**
	 * 取值器
	 */
	private Method getter;
	/**
	 * 赋值器
	 */
	private Method setter;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public Method getGetter() {
		return getter;
	}

	public void setGetter(Method getter) {
		this.getter = getter;
	}

	public Method getSetter() {
		return setter;
	}

	public void setSetter(Method setter) {
		this.setter = setter;
	}
}
