package org.apache.ibatis.datasource;

import java.util.Properties;

import javax.sql.DataSource;

/**
 * datasource工厂
 *
 * @author Clinton Begin
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();
}
