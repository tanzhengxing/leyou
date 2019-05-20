package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/20 19:49
 */
@RequestMapping("category")
public interface CategoryApi {
  @GetMapping("list/ids")
  public List<Category> queryCategoryByIds(@RequestParam("ids") List<Long> ids);
}
