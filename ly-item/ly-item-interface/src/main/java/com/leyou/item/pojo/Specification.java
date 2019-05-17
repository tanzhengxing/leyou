package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tan
 * @date 2019/5/14 13:18
 */
@Data
@Table(name = "tb_specification")
public class Specification implements Serializable {

  @Id
  private Long categoryId;
  private String specifications;

}
