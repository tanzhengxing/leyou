package com.leyou.item.mapper;

import com.leyou.item.pojo.Sku;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author tan
 * @date 2019/5/19 17:39
 */
public interface SkuMapper extends Mapper<Sku>,InsertListMapper<Sku>,IdListMapper<Sku,Long> {
}
