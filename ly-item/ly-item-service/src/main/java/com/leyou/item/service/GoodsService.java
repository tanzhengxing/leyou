package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.*;
import com.leyou.item.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
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
  private SkuMapper skuMapper;
  @Autowired
  private SpuDetailMapper spuDetailMapper;
  @Autowired
  private SpuMapper spuMapper;
  @Autowired
  private StockMapper stockMapper;

  @Transactional
  public void deleteGoods(Long spuId) {
    spuMapper.deleteByPrimaryKey(spuId);
    Sku sku = new Sku();
    sku.setSpuId(spuId);
    List<Sku> skus = skuMapper.select(sku);
    List<Long> skuIds = skus.stream().map(Sku::getId).collect(Collectors.toList());
    skuMapper.deleteByIdList(skuIds);
    stockMapper.deleteByIdList(skuIds);
  }

  public void judgeGoods(Long spuId,Boolean saleable) {
    Spu spu = spuMapper.selectByPrimaryKey(spuId);
    spu.setSaleable(!saleable);
    int count = spuMapper.updateByPrimaryKey(spu);
    if(count != 1){
      throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
    }
  }

  public SpuDetail queryDetail(Long spuId) {
    SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
    if (spuDetail == null) {
      throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
    }
    return spuDetail;
  }


  public List<Sku> querySkuList(Long spuId) {
    Sku sku = new Sku();
    sku.setSpuId(spuId);
    List<Sku> skus = skuMapper.select(sku);
    //查询出库存数
    List<Long> skuIds = skus.stream().map(Sku::getId).collect(Collectors.toList());
    List<Stock> stockList = stockMapper.selectByIdList(skuIds);
    for (int i = 0; i < skus.size(); i++) {
      skus.get(i).setStock(stockList.get(i).getStock().toString());
    }
    if (CollectionUtils.isEmpty(skus)) {
      throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
    }
    return skus;
  }


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
    List<Spu> spuList = spuMapper.selectByExample(example);
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

  @Transactional
  public void saveGoods(Spu spu) {
    if (spu != null) {
      //保存至tb_spu表
      spu.setSaleable(true);
      spu.setValid(true);
      spu.setCreateTime(new Date());
      spu.setLastUpdateTime(new Date());
      spuMapper.insert(spu);
      //保存至tb_spu_detail
      SpuDetail spuDetail = spu.getSpuDetail();
      spuDetail.setSpuId(spu.getId());
      spuDetailMapper.insert(spuDetail);
      //保存至tb_sku表
      List<Sku> skus = spu.getSkus();
      for (Sku sku : skus) {
        sku.setSpuId(spu.getId());
        sku.setCreateTime(new Date());
        sku.setLastUpdateTime(new Date());
      }
      skuMapper.insertList(skus);
      for (Sku sku : skus) {
        Stock stock = new Stock();
        String stockStr = sku.getStock();
        stock.setStock(Integer.parseInt(stockStr));
        stock.setSku_id(sku.getId());
        stockMapper.insert(stock);
      }
    }
  }

  @Transactional
  public void updateGoods(Spu spu) {
    //修改spu
    spuMapper.updateByPrimaryKey(spu);
    //修改spu_detail
    spuDetailMapper.updateByPrimaryKey(spu.getSpuDetail());
    //先把stock表和sku表中的数据删除
    List<Sku> skus = spu.getSkus();
    List<Long> skuIds = skus.stream().map(Sku::getId).collect(Collectors.toList());
    stockMapper.deleteByIdList(skuIds);
    skuMapper.deleteByIdList(skuIds);
    //向sku表和stock表添加
    List<Stock> stocks = skus.stream().map(sku -> {
      sku.setSpuId(spu.getId());
      sku.setCreateTime(new Date());
      sku.setLastUpdateTime(new Date());
      skuMapper.insert(sku);
      Stock stock = new Stock();
      stock.setSku_id(sku.getId());
      stock.setStock(Integer.parseInt(sku.getStock()));
      return stock;
    }).collect(Collectors.toList());
    stockMapper.insertList(stocks);
  }


}
