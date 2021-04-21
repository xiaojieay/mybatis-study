package org.apache.ibatis;

/*
 * 核心流程
 *
 *    1、配置解析
 *
 *        (1)、将项目中对应数据库实体类对象注册到typeAliases
 *
 *        (2)、解析Mapper接口对应的数据库文件(resultMap以及sql语句)
 *
 *            解析resultMap标签(数据库column、字段、对应数据库类型)将结果映射关系保存到本地内存中
 *
 *            解析sql标签,通常会用sql标签保存数据库表相关所有字段方便sql引用,将sql标签解析保存到内存中
 *
 *            解析select、update等数据库语句标签生成相关SqlSource以及MappedStatement保存在Configuration的mappedStatements本地缓存中,同时绑定Mapper接口与MappedStatement关系
 *
 *        核心解析TypeAlias、mapper接口对应数据库文件,启动时执行
 *
 *    2、获取Mapper接口代理执行接口方法
 *
 *        knownMappers保存的是Mapper接口对应的MapperProxyFactory代理工厂,通过工厂使用JDK自带的Proxy代理创建真正Mapper接口代理类MapperProxy
 *        执行Mapper接口的方法等同执行MapperProxy.invoke最终通过MapperMethod.execute执行真正的数据库操作
 *
 *    3、SQL解析
 *
 *        获取配置解析中的SQL,根据SQL中存在的动态关键字进行动态解析(where、set、if判断等)
 *
 *    4、参数映射
 *
 *        根据传入的参数与数据库中字段进行一一映射进行赋值(从JAVA对象转换成JDBC类型)
 *
 *    5、SQL执行
 *
 *        通过调用JDBC底层方法执行SQL语句
 *
 *    6、结果映射(针对查询类请求)
 *
 *        查询后返回的结果与resultMap中字段一一映射或者resultType指定对象中的字段一一映射(从返回的JDBC类型转换成JAVA类型),数据绑定
 */
