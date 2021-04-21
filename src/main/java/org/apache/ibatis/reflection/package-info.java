/*
 * 反射模块
 *
 *    抽象了JDK反射机制,提供简易的接口使用并可以缓存反射生成类信息提高反射效率
 */

package org.apache.ibatis.reflection;

/*
 * 此包涉及的设计模式
 *
 *    抽象工厂模式
 *
 *        ReflectorFactory定义了基础的反射工厂需要的功能
 *
 *        DefaultReflectorFactory作为子工厂用于生成Reflector对象
 */
