package com.example.shardingjdbc.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 这里使用的都是单键分片策略
 * 示例分库策略是：
 * goods_type < 100 使用database0库
 * 其余使用database1库
 * @author yangyang
 * @date 2019/1/30
 */
@Component
public class AlgorithmDatabaseSharding implements SingleKeyDatabaseShardingAlgorithm<Long> {

    @Autowired
    private DataSource0 dataSource0;
    @Autowired
    private DataSource1 dataSource1;

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Long value = shardingValue.getValue();
        if (value < 100){
            return dataSource0.getDbname();
        } else {
            return dataSource1.getDbname();
        }
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            if (value < 100){
                result.add(dataSource0.getDbname());
            } else {
                result.add(dataSource1.getDbname());
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
                                                ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
            if (value < 100){
                result.add(dataSource0.getDbname());
            } else {
                result.add(dataSource1.getDbname());
            }
        }
        return result;
    }
}
