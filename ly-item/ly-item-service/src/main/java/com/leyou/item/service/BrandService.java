package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/11 22:46
 */
@Service
public class BrandService {

  @Autowired
  private BrandMapper brandMapper;

  /**
   * @param bid
   * @return void
   * @Description 删除品牌和中间表信息
   */
  @Transactional
  public void deleteBrand(Long bid) {
    Brand brand = new Brand();
    brand.setId(bid);
    int delete1 = brandMapper.delete(brand);
    //删除中间表

    int delete2 = brandMapper.deleteByBid(bid);
    if (delete1 == 0 || delete2 == -1) {
      throw new LyException(ExceptionEnum.BRAND_NOT_DELETE);
    }
  }


  public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, Boolean desc, String sortBy, String key) {
    //分页
    PageHelper.startPage(page, rows);
    //过滤
    Example example = new Example(Brand.class);
    if (StringUtils.isNotBlank(key)) {
      example.createCriteria().orLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
    }
    //排序
    if (StringUtils.isNotBlank(sortBy)) {
      String orderByClause = sortBy + (desc ? " DESC" : " ASC");
      example.setOrderByClause(orderByClause);
    }
    //查询
    List<Brand> list = brandMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(list)) {
      throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
    }
    PageInfo<Brand> info = new PageInfo<>(list);
    return new PageResult<Brand>(info.getTotal(), info.getPages(), info.getList());
  }


  @Transactional
  public void saveBrand(Brand brand, List<Long> cids) {
    int num = brandMapper.insert(brand);
    if (num != 1) {
      throw new LyException(ExceptionEnum.BRAND_NOT_SAVE);
    }
    for (Long cid : cids) {
      Integer resultNum = brandMapper.insertTbCategoryBrand(cid, brand.getId());
      if (resultNum != 1) {
        throw new LyException(ExceptionEnum.Category_Brand_NOT_SAVE);
      }
    }

  }


  /**
   * @param brand
   * @param cids
   * @return void
   * @Description 修改品牌和中间表
   */
  @Transactional
  public void updateBrand(Brand brand, List<Long> cids) {
    int count = brandMapper.updateByPrimaryKey(brand);
    if (count == 0) {
      throw new LyException(ExceptionEnum.BRAND_NOT_UPDATE);
    }

    //因为是根据bid来修改的，所以无法直接根据bid来进行修改中间表；我们使用先删除后插入的方式
    //删除
    Integer delCount = brandMapper.deleteByBid(brand.getId());
    if (delCount == 0) {
      throw new LyException(ExceptionEnum.Category_Brand_NOT_UPDATE);
    }
    //插入
    for (Long cid : cids) {
      Integer inseCount = brandMapper.insertTbCategoryBrand(cid, brand.getId());
      if (inseCount == 0) {
        throw new LyException(ExceptionEnum.Category_Brand_NOT_UPDATE);
      }
    }
  }


}
