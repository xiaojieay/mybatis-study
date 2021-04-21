package org.apache.ibatis.binding;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

/**
 * Mapper接口跟代理之间注册关系
 *
 * @author Clinton Begin
 * @author Eduardo Macarron
 * @author Lasse Voss
 */
public class MapperRegistry {

    private final Configuration config;

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration config) {

        this.config = config;
    }

    /**
     * 获取代理
     *
     * @param type
     * @param sqlSession
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {

        // 获取代理工厂
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>)knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            // 创建真正的代理对象
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> boolean hasMapper(Class<T> type) {

        return knownMappers.containsKey(type);
    }

    /**
     * 绑定Mapper关系
     *
     * @param type
     * @param <T>
     */
    public <T> void addMapper(Class<T> type) {

        // 接口类型
        if (type.isInterface()) {
            // 已经绑定过了
            if (hasMapper(type)) {
                throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                // 绑定关系中添加类型+代理工厂
                knownMappers.put(type, new MapperProxyFactory<>(type));
                // It's important that the type is added before the parser is run
                // otherwise the binding may automatically be attempted by the
                // mapper parser. If the type is already known, it won't try.
                MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
                parser.parse();
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }

    /**
     * Gets the mappers.
     *
     * @return the mappers
     * @since 3.2.2
     */
    public Collection<Class<?>> getMappers() {

        return Collections.unmodifiableCollection(knownMappers.keySet());
    }

    /**
     * Adds the mappers.
     *
     * 添加mapper信息
     *
     * @param packageName
     *            the package name
     * @param superType
     *            the super type
     * @since 3.2.2
     */
    public void addMappers(String packageName, Class<?> superType) {

        ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<>();
        resolverUtil.find(new ResolverUtil.IsA(superType), packageName);
        Set<Class<? extends Class<?>>> mapperSet = resolverUtil.getClasses();
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

    /**
     * Adds the mappers.
     *
     * @param packageName
     *            the package name
     * @since 3.2.2
     */
    public void addMappers(String packageName) {

        addMappers(packageName, Object.class);
    }

}
