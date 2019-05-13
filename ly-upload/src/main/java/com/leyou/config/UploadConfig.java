package com.leyou.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author tan
 * @date 2019/5/13 19:48
 */
@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadConfig {
  private String baseUrl;
  private List<String> allowTypes;
}
