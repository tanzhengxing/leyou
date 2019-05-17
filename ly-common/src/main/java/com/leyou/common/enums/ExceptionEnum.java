package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author tan
 * @date 2019/5/6 23:07
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

  //这是商品分类的异常枚举
  CATEGORY_NOT_FOUND(404, "商品分类未找到"),

  //这是品牌异常枚举
  BRAND_NOT_FOUND(404, "品牌未找到"),

  //保存品牌
  BRAND_NOT_SAVE(500, "品牌不能新增"),

  //保存至商品分类中间表
  Category_Brand_NOT_SAVE(500, "品牌分类中间表不能新增"),

  //上传文件类型错误
  UPLOAD_IMAGE_TYPE_ERROR(500, "上传文件类型错误"),

  //上传文件内容为空
  UPLOAD_IMAGE_CONTENT_IS_NOT(500, "文件内容有误，可能是病毒"),

  //上传文件失败
  UPLOAD_IMAGE_ERROR(500, "上传文件失败"),

  //数据回显异常
  CATEGORY_BRAND_NOT_SHOW(404, "数据回显异常"),

  //品牌更新失败
  BRAND_NOT_UPDATE(500, "品牌更新失败"),

  //品牌分类中间表不能更新
  Category_Brand_NOT_UPDATE(500, "品牌分类中间表不能更新"),

  //品牌和中间表信息删除失败
  BRAND_NOT_DELETE(500, "品牌和中间表信息删除失败"),

  //找不到商品规格
  SPECIFICATION_NOT_FOUND(404, "找不到商品规格"),

  //规格新增失败
  SPECIFICATION_SAVE_ERROR(500, "规格新增失败"),

  //规格更新失败
  SPECIFICATION_UPDATE_ERROR(500, "规格更新失败"),

  //规格删除失败
  SPECIFICATION_DELETE_ERROR(500, "规格删除失败"),;


  private Integer code;
  private String msg;

}
