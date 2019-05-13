package com.leyou.upload.web;

import com.leyou.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tan
 * @date 2019/5/13 9:53
 */
@RestController
@RequestMapping("upload")
public class UploadController {

  @Autowired
  private UploadService uploadService;

  @PostMapping("image")
  public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
    return ResponseEntity.ok(uploadService.uploadImage(file));
  }
}
