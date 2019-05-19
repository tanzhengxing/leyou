package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/11 22:47
 */
@RestController
@RequestMapping("brand")
public class BrandController {

  @Autowired
  private BrandService brandService;

  @GetMapping("page")
  public ResponseEntity<PageResult<Brand>> queryBrandByPage(
      @RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "rows", defaultValue = "10") Integer rows,
      @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
      @RequestParam(value = "sortBy", required = false) String sortBy,
      @RequestParam(value = "key", required = false) String key
  ) {
    return ResponseEntity.ok(brandService.queryBrandByPage(page, rows, desc, sortBy, key));
  }


  @PostMapping
  public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
    brandService.saveBrand(brand,cids);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping
  public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids")List<Long> cids){
    brandService.updateBrand(brand,cids);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("deleteBrand/{bid}")
  public ResponseEntity<Void> deleteBrand(@PathVariable("bid")Long bid){
    brandService.deleteBrand(bid);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("cid/{cid}")
  public ResponseEntity<List<Brand>> queryBrandListByCid(@PathVariable("cid") Long cid){
    return ResponseEntity.ok(brandService.queryBrandListByCid(cid));
  }

}
