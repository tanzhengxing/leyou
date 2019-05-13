package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tan
 * @date 2019/5/11 15:17
 */

@Data
@Table(name = "tb_category")
public class Category implements Serializable {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Long id;
  private String name;
  private Long parentId;
  private Boolean isParent;
  private Integer sort;
}
