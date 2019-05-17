package com.leyou.item.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tan
 * @date 2019/5/14 13:24
 */
@Service
public class SpecificationService {

  @Autowired
  private SpecificationMapper specificationMapper;

  public void deleteSpecParam(Long cid, String paramName, String specGroup) {
    Specification specification = specificationMapper.selectByPrimaryKey(cid);
    List<Map> mapSource = JSONArray.parseArray(specification.getSpecifications(), Map.class);
    Map tempMap = null;
    for (Map map : mapSource) {
      if (map.get("group").equals(specGroup)) {
        List<Map> params = (List<Map>) map.get("params");
        for (Map param : params) {
          if (param.get("k").equals(paramName)) {
            tempMap = param;
            break;
          }
        }
        if (tempMap != null) {
          params.remove(tempMap);
          break;
        }
      }
    }
    String targetStr = JSON.toJSONString(mapSource);
    specification.setSpecifications(targetStr);
    try {
      int count = specificationMapper.updateByPrimaryKey(specification);
      if (count != 1) {
        throw new LyException(ExceptionEnum.SPECIFICATION_DELETE_ERROR);
      }
    } catch (Exception e) {
      throw new LyException(ExceptionEnum.SPECIFICATION_DELETE_ERROR);
    }
  }


  public void deleteSpecificationGroup(String specGroup, Long cid) {
    Specification specification = specificationMapper.selectByPrimaryKey(cid);
    List<Map> mapSource = JSONArray.parseArray(specification.getSpecifications(), Map.class);
    Map temp = null;
    for (Map map : mapSource) {
      if (map.get("group").equals(specGroup)) {
        temp = map;
        break;
      }
    }
    mapSource.remove(temp);
    String targetStr = JSON.toJSONString(mapSource);
    specification.setSpecifications(targetStr);
    try {
      int count = specificationMapper.updateByPrimaryKey(specification);
      if (count != 1) {
        throw new LyException(ExceptionEnum.SPECIFICATION_DELETE_ERROR);
      }
    } catch (Exception e) {
      throw new LyException(ExceptionEnum.SPECIFICATION_DELETE_ERROR);
    }
  }


  /**
   * @param specName
   * @return java.lang.String
   * @Description 查出规格参数集合
   */
  public String querySpecParamsBySpecName(String specName, Long cid) {
    Specification specification = specificationMapper.selectByPrimaryKey(cid);
    List<Map> maps = JSONArray.parseArray(specification.getSpecifications(), Map.class);
    String params = "[]";
    for (Map map : maps) {
      if (map.get("group").equals(specName)) {
        params = JSON.toJSONString(map.get("params"));
        break;
      }
    }
    return params;
  }


  public String querySpecificationsByCid(Long cid) {
    Specification specification = specificationMapper.selectByPrimaryKey(cid);
    if (specification == null) {
      throw new LyException(ExceptionEnum.SPECIFICATION_NOT_FOUND);
    }
    return specification.getSpecifications();
  }


  public void saveSpecParams(Specification specification) {
    Specification specFromDb = specificationMapper.selectByPrimaryKey(specification.getCategoryId());
    List<Map> mapsFormDb = JSONArray.parseArray(specFromDb.getSpecifications(), Map.class);
    Map mapArgs = (Map) JSONObject.parse(specification.getSpecifications());
    for (Map map : mapsFormDb) {
      if (map.get("group").equals(mapArgs.get("group"))) {
        map.put("params", mapArgs.get("params"));
        break;
      }
    }
    String targetStr = JSON.toJSONString(mapsFormDb);
    //将修改后参数存入对象中
    specification.setSpecifications(targetStr);
    int count = specificationMapper.updateByPrimaryKey(specification);
    if (count != 1) {
      throw new LyException(ExceptionEnum.SPECIFICATION_SAVE_ERROR);
    }

  }


  public void saveSpecificationGroup(Specification specification) {
    if (specification == null) {
      throw new LyException(ExceptionEnum.SPECIFICATION_SAVE_ERROR);
    }
    //先根据cid查询出规格对象
    Specification spec = specificationMapper.selectByPrimaryKey(specification.getCategoryId());
    if (spec != null) {
      //将规格字符串字段转为数组
      List<Map> sourceMap = JSONArray.parseArray(spec.getSpecifications(), Map.class);
      //将传过来的规格字符串参数转为数组
      List<Map> mapArgs = JSONArray.parseArray(specification.getSpecifications(), Map.class);
      //合并并转为字符串
      for (Map map : mapArgs) {
        sourceMap.add(map);
      }
      String targetStr = JSON.toJSONString(sourceMap);
      //更新规格字符串
      specification.setSpecifications(targetStr);
      try {
        int count = specificationMapper.updateByPrimaryKey(specification);
        if (count != 1) {
          throw new LyException(ExceptionEnum.SPECIFICATION_SAVE_ERROR);
        }
      } catch (Exception e) {
        throw new LyException(ExceptionEnum.SPECIFICATION_SAVE_ERROR);
      }
    }

  }


  public void updateSpecParam(Specification specification, String lastParamName) {
    Specification specFromDb = specificationMapper.selectByPrimaryKey(specification.getCategoryId());
    List<Map> mapsFormDb = JSONArray.parseArray(specFromDb.getSpecifications(), Map.class);
    Map mapArgs = (Map) JSONObject.parse(specification.getSpecifications());
    Map tempMap = null;
    Map argsMap = null;
    for (Map map : mapsFormDb) {
      //从数据库中找出map对象来
      if (map.get("group").equals(mapArgs.get("group"))) {
        List<Map> paramsFormDb = (List<Map>) map.get("params");
        for (Map paramMap : paramsFormDb) {
          if (paramMap.get("k").equals(lastParamName)) {
            tempMap = paramMap;
            break;
          }
        }
        //从参数中找出来
        List<Map> paramList = (List<Map>) mapArgs.get("params");
        for (Map param : paramList) {
          if (param.get("k").equals(lastParamName)) {
            argsMap = param;
            break;
          }
        }
        if (tempMap != null && argsMap != null) {
          //先把数据库中的移除，在把参数的map加上去
          paramsFormDb.remove(tempMap);
          paramsFormDb.add(argsMap);
        } else {
          throw new LyException(ExceptionEnum.SPECIFICATION_SAVE_ERROR);
        }
        break;
      }
    }
    String targetStr = JSON.toJSONString(mapsFormDb);
    //将修改后参数存入对象中
    specification.setSpecifications(targetStr);
    int count = specificationMapper.updateByPrimaryKey(specification);
    if (count != 1) {
      throw new LyException(ExceptionEnum.SPECIFICATION_SAVE_ERROR);
    }

  }


  public void updateSpecificationGroup(Specification specification, String lastSpecName) {
    if (specification == null) {
      throw new LyException(ExceptionEnum.SPECIFICATION_SAVE_ERROR);
    }
    //先根据cid查询出规格对象
    Specification spec = specificationMapper.selectByPrimaryKey(specification.getCategoryId());
    if (spec != null) {
      //将规格字符串字段转为数组
      List<Map> sourceMap = JSONArray.parseArray(spec.getSpecifications(), Map.class);
      //将传过来的规格参数转为Map集合
      List<Map> mapArgs = JSONArray.parseArray(specification.getSpecifications(), Map.class);
      if (mapArgs.size() == 1) {
        for (Map map : sourceMap) {
          if (map.get("group").equals(lastSpecName)) {
            //将修改的传过来的参数存入其中
            map.put("group", mapArgs.get(0).get("group"));
          }
        }
      }
      //转为字符串
      String targtStr = JSON.toJSONString(sourceMap);
      specification.setSpecifications(targtStr);
      try {
        int count = specificationMapper.updateByPrimaryKey(specification);
        if (count != 1) {
          throw new LyException(ExceptionEnum.SPECIFICATION_UPDATE_ERROR);
        }
      } catch (Exception e) {
        throw new LyException(ExceptionEnum.SPECIFICATION_UPDATE_ERROR);
      }
    }

  }


}
