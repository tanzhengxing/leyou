package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tan
 * @date 2019/5/19 14:55
 */
@Data
@Table(name = "tb_spec_param")
public class SpecParam {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Long id;
  private Long cid;
  private Long groupId;
  private String name;
  @Column(name = "`numeric`")
  private Boolean numeric;
  private String unit;
  private Boolean generic;
  private Boolean searching;
  private String segments;
}
