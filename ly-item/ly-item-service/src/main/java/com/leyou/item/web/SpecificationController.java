package com.leyou.item.web;

import com.leyou.item.pojo.Specification;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tan
 * @date 2019/5/14 13:23
 */
@RestController
@RequestMapping("spec")
public class SpecificationController {

  @Autowired
  private SpecificationService specificationService;

  /**
   * @param specGroup
   * @param cid
   * @return org.springframework.http.ResponseEntity<java.lang.Void>
   * @Description 删除规格组
   */
  @DeleteMapping("group")
  public ResponseEntity<Void> deleteSpecificationGroup(@RequestParam("specGroup") String specGroup, @RequestParam("cid") Long cid) {
    specificationService.deleteSpecificationGroup(specGroup, cid);
    return ResponseEntity.status(HttpStatus.OK).build();
  }


  /**
   * @param cid
   * @return org.springframework.http.ResponseEntity<java.util.List       <       java.lang.String>>
   * @Description 根据分类查询出所有的规格主体信息
   */
  @GetMapping("groups/{cid}")
  public ResponseEntity<String> querySpecificationsByCid(@PathVariable("cid") Long cid) {
    return ResponseEntity.ok(specificationService.querySpecificationsByCid(cid));
  }

  /**
   * @param specification
   * @return org.springframework.http.ResponseEntity<java.lang.Void>
   * @Description 新增规格字段
   */
  @PostMapping("group")
  public ResponseEntity<Void> saveSpecificationGroup(@RequestBody Specification specification) {
    specificationService.saveSpecificationGroup(specification);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * @param specification
   * @param lastSpecName
   * @return org.springframework.http.ResponseEntity<java.lang.Void>
   * @Description 根据第一次原先的组名来进行修改
   */
  @PutMapping("group")
  public ResponseEntity<Void> updateSpecificationGroup(@RequestBody Specification specification, @RequestParam("lastSpecName") String lastSpecName) {
    specificationService.updateSpecificationGroup(specification, lastSpecName);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * @param specName
   * @return org.springframework.http.ResponseEntity<java.lang.String>
   * @Description 各组中的参数集合
   */
  @GetMapping("param")
  public ResponseEntity<String> querySpecParamsBySpecName(@RequestParam("specName") String specName, @RequestParam("cid")Long cid) {
    String specifications = specificationService.querySpecParamsBySpecName(specName,cid);
    return ResponseEntity.ok(specifications);
  }

  /**
   * @param specification	
   * @Description 保存规格参数
   * @return org.springframework.http.ResponseEntity<java.lang.Void>
   */
  @PostMapping("param")
  public ResponseEntity<Void> saveSpecParams(@RequestBody Specification specification){
    specificationService.saveSpecParams(specification);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("param/{lastParamName}")
  public ResponseEntity<Void> updateSpecParam(@RequestBody Specification specification,@PathVariable("lastParamName")String lastParamName){
    specificationService.updateSpecParam(specification,lastParamName);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * @param cid
   * @param paramName
   * @Description 删除某个规格参数
   * @return org.springframework.http.ResponseEntity<java.lang.Void>
   */
  @DeleteMapping("param")
  public ResponseEntity<Void> deleteSpecParam(@RequestParam("cid") Long cid,@RequestParam("paramName") String paramName,@RequestParam("specName")String specName){
    specificationService.deleteSpecParam(cid,paramName,specName);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("params")
  public ResponseEntity<String> queryParamsByCid(@RequestParam("cid")Long cid){
    return ResponseEntity.ok(specificationService.querySpecificationsByCid(cid));
  }


}
