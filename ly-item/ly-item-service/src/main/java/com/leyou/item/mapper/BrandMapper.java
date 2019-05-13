package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author tan
 * @date 2019/5/11 22:45
 */
public interface BrandMapper extends Mapper<Brand> {

  @Delete("delete from tb_category_brand where brand_id = #{bid}")
  Integer deleteByBid(@Param("bid") Long bid);

  @Insert("insert into tb_category_brand (category_id,brand_id) values (#{cid},#{bid})")
  public Integer insertTbCategoryBrand(@Param("cid") Long cid, @Param("bid")Long bid);

}
