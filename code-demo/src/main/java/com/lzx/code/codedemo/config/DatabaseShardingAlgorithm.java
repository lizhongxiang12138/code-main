package com.lzx.code.codedemo.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述:数据源分配算法
 *
 * 这里我们只用了一个数据源，所以所有的都只返回了数据源0
 *
 * @Auther: lzx
 * @Date: 2019/9/9 15:27
 */
@Component
public class DatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm {

    @Autowired
    private Database0Config database0Config;


    /**
     *  = 条件时候返回的数据源
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public String doEqualSharding(Collection collection, ShardingValue shardingValue) {
        return database0Config.getDatabaseName();
    }

    /**
     *  IN 条件返回的数据源
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doInSharding(Collection collection, ShardingValue shardingValue) {
        List<String> result = new ArrayList<String>();
        result.add(database0Config.getDatabaseName());
        return result;
    }

    /**
     * BETWEEN 条件放回的数据源
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doBetweenSharding(Collection collection, ShardingValue shardingValue) {
        List<String> result = new ArrayList<String>();
        result.add(database0Config.getDatabaseName());
        return result;
    }
}
