package com.leyou.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tan
 * @date 2019/5/11 22:43
 */
@Data
public class PageResult<T> implements Serializable {
  // 总条数
  private Long total;
  // 总页数
  private Integer totalPage;
  // 当前页数据
  private List<T> items;

  public PageResult(){
  }

  public PageResult(Long total, Integer pages, List<T> list) {
    this.total = total;
    this.totalPage = pages;
    this.items = list;
  }
}

