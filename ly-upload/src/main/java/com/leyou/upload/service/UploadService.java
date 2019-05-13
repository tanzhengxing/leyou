package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.config.UploadConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author tan
 * @date 2019/5/13 10:08
 */
@Service
@Slf4j
@EnableConfigurationProperties(UploadConfig.class)
public class UploadService {

  @Autowired
  private UploadConfig props;

  @Autowired
  private FastFileStorageClient storageClient;

  /**
   * @param file
   * @return java.lang.String
   * @Description 上传图片
   */
  public String uploadImage(MultipartFile file) {
    //判断文件类型，getContentType会自动把类型进行转换
    String contentType = file.getContentType();
    if (!props.getAllowTypes().contains(contentType)) {
      throw new LyException(ExceptionEnum.UPLOAD_IMAGE_TYPE_ERROR);
    }
    //判断文件内容是否正确
    try {
      BufferedImage image = ImageIO.read(file.getInputStream());
      if (image == null) {
        throw new LyException(ExceptionEnum.UPLOAD_IMAGE_CONTENT_IS_NOT);
      }
    } catch (IOException e) {
      log.error("文件内容有误，可能是病毒", e);
    }

    try {
      String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
      //文件上传到fdfs目标文件夹
      StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
      return props.getBaseUrl() + storePath.getFullPath();
    } catch (IOException e) {
      log.error("上传文件失败", e);
      throw new LyException(ExceptionEnum.UPLOAD_IMAGE_ERROR);
    }
  }


}
