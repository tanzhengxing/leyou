package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/11 15:30
 */
@Service
public class CategoryService {

  @Autowired
  private CategoryMapper categoryMapper;

  /**
   * @param bid
   * @return java.util.List<com.leyou.item.pojo.Category>
   * @Description 通过品牌id查查询中间表找出关联的商品分类
   */
  public List<Category> queryCategoriesByBid(Long bid) {
    List<Category> categories = categoryMapper.queryCategoriesByBid(bid);
    if (CollectionUtils.isEmpty(categories)) {
      throw new LyException(ExceptionEnum.CATEGORY_BRAND_NOT_SHOW);
    }
    return categories;
  }


  public List<Category> queryCategoryListByPid(Long pid) {
    Category category = new Category();
    category.setParentId(pid);
    List<Category> list = categoryMapper.select(category);
    if (CollectionUtils.isEmpty(list)) {
      throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
    }
    return list;
  }


}
