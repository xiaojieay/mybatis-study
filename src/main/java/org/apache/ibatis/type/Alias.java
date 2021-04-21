package org.apache.ibatis.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify alias name.
 *
 * 别名注解
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * &#064;Alias("Email")
 * public class UserEmail {
 *     // ...
 * }
 * </pre>
 *
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Alias {

    /**
     * Return the alias name.
     *
     * @return the alias name
     */
    String value();
}
