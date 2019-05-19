package com.leyou.item.mapper;

import com.leyou.item.pojo.Stock;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author tan
 * @date 2019/5/19 17:41
 */
public interface StockMapper extends Mapper<Stock>,IdListMapper<Stock,Long>,InsertListMapper<Stock> {
}
