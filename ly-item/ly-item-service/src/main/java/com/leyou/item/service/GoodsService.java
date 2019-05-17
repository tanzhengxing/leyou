package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.mapper.GoodsMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.Spu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tan
 * @date 2019/5/17 21:24
 */
@Service
public class GoodsService {

  @Autowired
  private BrandMapper brandMapper;
  @Autowired
  private CategoryMapper categoryMapper;
  @Autowired
  private GoodsMapper goodsMapper;

  public PageResult<Spu> querySpuList(String key, Boolean saleable, Integer page, Integer rows) {
    //分页
    PageHelper.startPage(page, rows);
    //过滤
    Example example = new Example(Spu.class);
    Example.Criteria criteria = example.createCriteria();
    if (StringUtils.isNotBlank(key)) {
      criteria.andLike("title", "%" + key + "%");
    }
    if (saleable != null) {
      criteria.andEqualTo("saleable", saleable);
    }
    example.setOrderByClause(" last_update_time desc");
    //查询
    List<Spu> spuList = goodsMapper.selectByExample(example);
    loadCnameAndBname(spuList);

    if (CollectionUtils.isEmpty(spuList)) {
      throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
    }
    PageInfo<Spu> info = new PageInfo<>(spuList);

    return new PageResult<>(info.getTotal(), info.getPages(), info.getList());
  }


  private void loadCnameAndBname(List<Spu> spuList) {
    for (Spu spu : spuList) {
      List<String> names = categoryMapper.selectByIds(spu.getCid1().toString() + "," + spu.getCid2().toString() + "," + spu.getCid3().toString()).stream().map(Category::getName).collect(Collectors.toList());
      spu.setCname(StringUtils.join(names, "/"));
      Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
      if (brand != null) {
        spu.setBname(brand.getName());
      }
    }

  }


}
