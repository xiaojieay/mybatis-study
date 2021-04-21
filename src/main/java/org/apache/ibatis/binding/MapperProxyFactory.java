package org.apache.ibatis.binding;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.binding.MapperProxy.MapperMethodInvoker;
import org.apache.ibatis.session.SqlSession;

/**
 * Mapper代理工厂
 *
 * @author Lasse Voss
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    private final Map<Method, MapperMethodInvoker> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {

        return mapperInterface;
    }

    public Map<Method, MapperMethodInvoker> getMethodCache() {
        return methodCache;
    }

    /**
     * 生成代理实例
     *
     * @param mapperProxy
     * @return
     */
    @SuppressWarnings("unchecked")
    protected T newInstance(MapperProxy<T> mapperProxy) {

        // 利用JDK自带的Proxy生成代理
        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] {mapperInterface}, mapperProxy);
    }

    /**
     * 创建Mapper接口代理类
     *
     * @param sqlSession
     * @return
     */
    public T newInstance(SqlSession sqlSession) {

        // Mapper接口代理类
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface, methodCache);
        return newInstance(mapperProxy);
    }

}
