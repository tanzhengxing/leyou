package com.leyou.item.web;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/11 17:01
 */
@RestController
@RequestMapping("category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("list")
  public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam("pid")Long pid){
    List<Category> categoryList = categoryService.queryCategoryListByPid(pid);
    return ResponseEntity.ok(categoryList);
  }

  @GetMapping("bid/{bid}")
  public ResponseEntity<List<Category>> queryCategoriesByBid(@PathVariable("bid") Long bid){
    List<Category> categories = categoryService.queryCategoriesByBid(bid);
    return ResponseEntity.ok(categories);
  }

}
