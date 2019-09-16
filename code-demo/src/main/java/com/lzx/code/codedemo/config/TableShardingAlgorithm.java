package com.lzx.code.codedemo.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 描述: 数据表分配算法的实现
 *
 * @Auther: lzx
 * @Date: 2019/9/9 16:19
 */
@Component
public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {


    /**
     * = 条件时候返回的数据源
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        for (String eaach:collection) {
            Long value = shardingValue.getValue();
            value = value >> 22;
            if(eaach.endsWith(value%10+"")){
                return eaach;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * IN 条件返回的数据源
     * @param tableNames
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                value = value >> 22;
                if (tableName.endsWith(value % 10 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    /**
     * BETWEEN 条件放回的数据源
     * @param tableNames
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                Long value = i >> 22;
                if (each.endsWith(i % 10 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
