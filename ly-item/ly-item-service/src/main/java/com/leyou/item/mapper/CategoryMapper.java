package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/11 15:27
 */
public interface CategoryMapper extends Mapper<Category> {

  @Select("select * from tb_category where id in (select category_id from tb_category_brand where brand_id = #{bid})")
  List<Category> queryCategoriesByBid(@Param("bid")Long bid);
}
