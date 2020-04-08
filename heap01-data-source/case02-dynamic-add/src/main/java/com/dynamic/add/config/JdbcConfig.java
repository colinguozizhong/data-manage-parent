package com.dynamic.add.config;

import com.dynamic.add.entity.ConnectionEntity;
import com.dynamic.add.util.ConnectionUtil;
import com.dynamic.add.util.DataSourceType;
import org.springframework.stereotype.Component;
import java.sql.Connection;

@Component
public class JdbcConfig {

    /**
     * 获取数据源连接
     */
    public Connection getConnection (ConnectionEntity connectionEntity){
        String dataTypeName = connectionEntity.getDataTypeName();
        String driverClassName = DataSourceType.getDriver(dataTypeName) ;
        if (driverClassName == null){
            throw new RuntimeException("不支持该数据源类型") ;
        }
        connectionEntity.setDriverClassName(driverClassName);
        return ConnectionUtil.getConnect(connectionEntity.getDriverClassName(),
                connectionEntity.getUserName(),
                connectionEntity.getPassWord(),
                connectionEntity.getJdbcUrl()) ;
    }

}
