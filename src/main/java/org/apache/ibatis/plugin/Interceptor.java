package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * 拦截器
 *
 * @author Clinton Begin
 */
public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    default Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    default void setProperties(Properties properties) {}

}
