package unknown.framework.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 备注注解类
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Remark {
	/**
	 * 备注内容
	 * 
	 * @return 备注内容
	 */
	public String value();
}
