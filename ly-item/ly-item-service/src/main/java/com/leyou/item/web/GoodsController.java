package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/17 21:23
 */
@RestController
@RequestMapping("spu")
public class GoodsController {

  @Autowired
  private GoodsService goodsService;

  @GetMapping("page")
  public ResponseEntity<PageResult> querySpuList(
      @RequestParam(value = "key",required = false) String key,
      @RequestParam(value = "saleable", required = false) Boolean saleable,
      @RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "rows", defaultValue = "5") Integer rows
      ){
      return ResponseEntity.ok(goodsService.querySpuList(key,saleable,page,rows));
  }

}
