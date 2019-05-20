package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author tan
 * @date 2019/5/20 21:10
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
