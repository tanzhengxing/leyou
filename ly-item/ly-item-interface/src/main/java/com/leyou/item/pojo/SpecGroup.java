package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tan
 * @date 2019/5/19 14:53
 */
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Long id;
  private Long cid;
  private String name;

}
