package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tan
 * @date 2019/5/19 17:24
 */
@Data
@Table(name = "tb_stock")
public class Stock {
  @Id
  private Long sku_id;
  private Integer seckill_stock;
  private Integer seckill_total;
  private Integer stock;
}
