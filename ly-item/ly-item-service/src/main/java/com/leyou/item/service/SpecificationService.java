package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/14 13:24
 */
@Service
public class SpecificationService {

  @Autowired
  private SpecGroupMapper specGroupMapper;
  @Autowired
  private SpecParamMapper specParamMapper;

  public void deleteGroup(Long id) {
    specGroupMapper.deleteByPrimaryKey(id);
  }


  public void deleteParam(Long id) {
    specParamMapper.deleteByPrimaryKey(id);
  }


  public List<SpecGroup> queryGroupsByCid(Long cid) {
    SpecGroup specGroup = new SpecGroup();
    specGroup.setCid(cid);
    List<SpecGroup> lists = specGroupMapper.select(specGroup);
    if (CollectionUtils.isEmpty(lists)) {
      throw new LyException(ExceptionEnum.SPECIFICATION_NOT_FOUND);
    }
    return lists;
  }


  public List<SpecParam> querypParams(Long cid, Long gid, Boolean searching) {
    SpecParam specParam = new SpecParam();
    specParam.setCid(cid);
    specParam.setGroupId(gid);
    specParam.setSearching(searching);
    List<SpecParam> list = specParamMapper.select(specParam);
    if (CollectionUtils.isEmpty(list)) {
      throw new LyException(ExceptionEnum.SPECIFICATION_NOT_FOUND);
    }
    return list;
  }


  public void saveGroup(SpecGroup specGroup) {
    if (specGroup != null) {
      specGroupMapper.insert(specGroup);
    }
  }


  public void saveParams(SpecParam specParam) {
    if (specParam != null) {
      specParamMapper.insert(specParam);
    }
  }


  public void updateGroup(SpecGroup specGroup) {
    if (specGroup != null) {
      specGroupMapper.updateByPrimaryKey(specGroup);
    }
  }


  public void updateParams(SpecParam specParam) {
    if (specParam != null) {
      specParamMapper.updateByPrimaryKey(specParam);
    }
  }


}
