package org.apache.ibatis.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify java types to map {@link TypeHandler}.
 *
 * <p>
 * <b>How to use:</b>
 * <pre>
 * &#064;MappedTypes(String.class)
 * public class StringTrimmingTypeHandler implements TypeHandler&lt;String&gt; {
 *   // ...
 * }
 * </pre>
 * @author Eduardo Macarron
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MappedTypes {
  /**
   * Returns java types to map {@link TypeHandler}.
   *
   * @return java types
   */
  Class<?>[] value();
}
