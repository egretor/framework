package unknown.framework.module.pojo;

import java.lang.reflect.Method;

import unknown.framework.module.annotation.Types;

/**
 * 字段映射类
 */
public class FieldMap {
	private String name;
	private Types type;
	private String remark;
	private Method getter;
	private Method setter;

	/**
	 * 名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 类型
	 * 
	 * @return 类型
	 */
	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	/**
	 * 备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 取值器
	 * 
	 * @return 取值器
	 */
	public Method getGetter() {
		return getter;
	}

	public void setGetter(Method getter) {
		this.getter = getter;
	}

	/**
	 * 赋值器
	 * 
	 * @return 赋值器
	 */
	public Method getSetter() {
		return setter;
	}

	public void setSetter(Method setter) {
		this.setter = setter;
	}
}
