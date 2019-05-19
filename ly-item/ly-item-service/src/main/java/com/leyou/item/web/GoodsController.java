package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/17 21:23
 */
@RestController

public class GoodsController {

  @Autowired
  private GoodsService goodsService;

  @GetMapping("spu/page")
  public ResponseEntity<PageResult> querySpuList(
      @RequestParam(value = "key", required = false) String key,
      @RequestParam(value = "saleable", required = false) Boolean saleable,
      @RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "rows", defaultValue = "5") Integer rows
  ) {
    return ResponseEntity.ok(goodsService.querySpuList(key, saleable, page, rows));
  }


  /**
   * @param spu
   * @Description 保存商品信息
   * @return org.springframework.http.ResponseEntity<java.lang.Void>
   */
  @PostMapping("goods")
  public ResponseEntity<Void> saveGoods(@RequestBody Spu spu) {
    goodsService.saveGoods(spu);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * @param spu
   * @Description 修改商品信息
   * @return
   */
  @PutMapping("goods")
  public ResponseEntity<Void> updateGoods(@RequestBody Spu spu) {
    goodsService.updateGoods(spu);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("spu/detail/{id}")
  public ResponseEntity<SpuDetail> queryDetail(@PathVariable("id") Long spuId){
    return ResponseEntity.ok(goodsService.queryDetail(spuId));
  }

  @GetMapping("sku/list")
  public ResponseEntity<List<Sku>> querySkuList(@RequestParam("id") Long spuId){
    return ResponseEntity.ok(goodsService.querySkuList(spuId));
  }

  @DeleteMapping("goods/{id}")
  public ResponseEntity<Void> deleteGoods(@PathVariable("id")Long spuId){
    goodsService.deleteGoods(spuId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("spu")
  public ResponseEntity<Void> JudgeGoods(@RequestParam("spuId")Long spuId,@RequestParam("saleable") Boolean saleable){
    goodsService.judgeGoods(spuId,saleable);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }


}
