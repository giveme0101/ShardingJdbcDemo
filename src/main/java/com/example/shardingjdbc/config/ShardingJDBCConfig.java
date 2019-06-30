package com.example.shardingjdbc.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Config
 * @Date 2019/02/21 13:55
 */
@Configuration
public class ShardingJDBCConfig {

    @Autowired
    private DataSource0 dataSource0;
    @Autowired
    private DataSource1 dataSource1;

    @Autowired
    private SingleKeyTableShardingAlgorithm algorithmTableSharding;
    @Autowired
    private SingleKeyDatabaseShardingAlgorithm algorithmDatabaseSharding;

    @Bean
    public DataSource getDataSource() throws SQLException {

        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {

        //1.设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(dataSource0.getDbname(), dataSource0.createDataSource());
        dataSourceMap.put(dataSource1.getDbname(), dataSource1.createDataSource());

        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，
        // 但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
        DataSourceRule rule = new DataSourceRule(dataSourceMap, dataSource0.getDbname());

        //2.设置分表映射，将goods_0和goods_1两个实际的表映射到goods逻辑表
        TableRule orderTableRule = TableRule.builder("goods")
                .actualTables(Arrays.asList("goods_0", "goods_1"))
                .dataSourceRule(rule)
                .build();

        //3.具体的分库分表策略, goods_type 决定库，goods_id决定表
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(rule)
                .tableRules(Arrays.asList(orderTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("goods_type", algorithmDatabaseSharding))
                .tableShardingStrategy(new TableShardingStrategy("goods_id", algorithmTableSharding))
                .build();

        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }
}