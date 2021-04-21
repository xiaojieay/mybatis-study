package org.apache.ibatis.datasource;

/*
 * 数据源层
 *
 *    提供基础接口方便实现其它第三方数据源,spring中通常使用druid、hikaricp数据源
 */

/*
 * 此包涉及的设计模式
 *
 *    抽象工厂模式
 *
 *        创建型设计模式,创建一系列相关的对象
 *
 *        DataSourceFactory作为抽象工厂顶层,JndiDataSourceFactory、PooledDataSourceFactory、UnpooledDataSourceFactory作为特定子工厂
 *
 *        通过getDataSource获取最终的数据源
 *
 *        核心优点
 *
 *            1、单一职责原则,将产品生成代码抽取到同一位置,使得代码易于维护
 *
 *            2、开闭原则,添加新的实现时无需修改客户端代码
 */
