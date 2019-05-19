package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

  @Autowired
  private SpecificationService specificationService;

  @DeleteMapping("group/{id}")
  public ResponseEntity<Void> deleteGroup(@PathVariable("id") Long id) {
    specificationService.deleteGroup(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }


  @DeleteMapping("param/{id}")
  public ResponseEntity<Void> deleteParam(@PathVariable Long id) {
    specificationService.deleteParam(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }


  @GetMapping("groups/{cid}")
  public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid) {
    return ResponseEntity.ok(specificationService.queryGroupsByCid(cid));
  }


  @GetMapping("params")
  public ResponseEntity<List<SpecParam>> queryparams(
      @RequestParam(value = "cid", required = false) Long cid,
      @RequestParam(value = "gid", required = false) Long gid,
      @RequestParam(value = "searching", required = false) Boolean searching
  ) {
    return ResponseEntity.ok(specificationService.querypParams(cid, gid, searching));
  }


  @PostMapping("group")
  public ResponseEntity<Void> saveGroup(@RequestBody SpecGroup specGroup) {
    specificationService.saveGroup(specGroup);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


  @PostMapping("param")
  public ResponseEntity<Void> saveParams(@RequestBody SpecParam specParam) {
    specificationService.saveParams(specParam);
    return ResponseEntity.status(HttpStatus.OK).build();
  }


  @PutMapping("group")
  public ResponseEntity<Void> updateGroup(@RequestBody SpecGroup specGroup) {
    specificationService.updateGroup(specGroup);
    return ResponseEntity.status(HttpStatus.OK).build();
  }


  @PutMapping("param")
  public ResponseEntity<Void> updateParams(@RequestBody SpecParam specParam) {
    specificationService.updateParams(specParam);
    return ResponseEntity.status(HttpStatus.OK).build();
  }


}
