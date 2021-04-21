package org.apache.ibatis.binding;

/*
 * 接口绑定层
 *
 *    为业务系统DAO层Mapper接口生成代理,后续调用通过代理类执行
 */

/*
 * 此包涉及的设计模式
 *
 *
 *    代理模式(MapperProxy)
 *
 *        结构型设计模式,代理控制着对于原对象的访问,并允许在将请求提交给对象前后进行一些处理
 *
 *        Mapper作为服务接口、MapperProxy对Mapper接口进行代理,调用Mapper接口中的方法最终会调用MapperProxy中的invoke方法,利用JDK自带Proxy
 *
 *        核心优点
 *
 *            1、开闭原则,在不需要改动原有代码的情况下变更方法的行为
 *
 *        核心缺点
 *
 *            1、代理模式会多创建很多潜在的对象
 *
 *
 *    简单工厂模式(MapperProxyFactory、MapperProxy)
 *
 *        创建型设计模式,通过工厂创建实际需要的类型
 *
 *        MapperProxyFactory作为工厂,当需要创建MapperProxy时不会通过new MapperProxy的方式创建直接通过MapperProxyFactory
 *
 *        核心优点
 *
 *            1、开闭原则,在不需要改动原有代码的基础上新增新的创建方式或类型
 *
 *            2、单一原则,将创建MapperProxy统一在MapperProxyFactory中
 */
