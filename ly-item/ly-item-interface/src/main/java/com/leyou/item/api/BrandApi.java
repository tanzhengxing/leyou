package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tan
 * @date 2019/5/20 19:52
 */
@RequestMapping("brand")
public interface BrandApi {
  @GetMapping("{id}")
  public Brand queryBrandById(@PathVariable("id") Long id);
}
