package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tan
 * @date 2019/5/11 22:42
 */
@Table(name = "tb_brand")
@Data
public class Brand implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;// 品牌名称
  private String image;// 品牌图片
  private Character letter;
  // getter setter 略
}
