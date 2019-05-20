package com.leyou.item.api;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/20 19:54
 */
public interface GoodsApi {
  @GetMapping("spu/page")
  public PageResult querySpuList(
      @RequestParam(value = "key", required = false) String key,
      @RequestParam(value = "saleable", required = false) Boolean saleable,
      @RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "rows", defaultValue = "5") Integer rows
  );

  @GetMapping("spu/detail/{id}")
  public SpuDetail queryDetail(@PathVariable("id") Long spuId);

  @GetMapping("sku/list")
  public List<Sku> querySkuList(@RequestParam("id") Long spuId);

}
