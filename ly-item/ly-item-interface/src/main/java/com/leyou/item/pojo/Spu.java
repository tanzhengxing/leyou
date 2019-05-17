package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author tan
 * @date 2019/5/17 21:14
 */
@Data
@Table(name = "tb_spu")
public class Spu implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long brandId;
  private Long cid1;// 1级类目
  private Long cid2;// 2级类目
  private Long cid3;// 3级类目
  private String title;// 标题
  private String subTitle;// 子标题
  private Boolean saleable;// 是否上架
  private Boolean valid;// 是否有效，逻辑删除用
  private Date createTime;// 创建时间
  private Date lastUpdateTime;// 最后修改时间
  // 省略getter和setter
  @Transient
  private String cname;
  @Transient
  private String bname;
}
