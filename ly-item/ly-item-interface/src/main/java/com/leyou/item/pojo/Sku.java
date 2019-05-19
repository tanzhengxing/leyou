package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author tan
 * @date 2019/5/19 17:26
 */
@Data
@Table(name = "tb_sku")
public class Sku {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Long id;
  private Long spuId;
  private String title;
  private String images;
  private Long price;
  private String indexes;
  private String ownSpec;
  private Boolean enable;
  private Date createTime;
  private Date lastUpdateTime;
  @Transient
  private String stock;
}
