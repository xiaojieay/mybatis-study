package org.apache.ibatis.builder;

/*
 * 配置解析
 *
 *    通过解析Mybatis配置文件以及Mapper接口对应的数据库文件保存到内存中
 */

/*
 * 此包涉及的设计模式
 *
 *    builder建造器模式
 *
 *        BaseBuilder作为builder基类
 *
 *        通过XMLConfigBuilder、XMLMapperBuilder、XMLStatementBuilder构建出Mybatis运行时Configuration配置类
 */
