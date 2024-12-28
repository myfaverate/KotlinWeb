package edu.tyut.datasource

import com.alibaba.druid.pool.DruidDataSource
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory

class DruidDataSourceFactory : UnpooledDataSourceFactory(){
    init {
        this.dataSource = DruidDataSource()
    }
}