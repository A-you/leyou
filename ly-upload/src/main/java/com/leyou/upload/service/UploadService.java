package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {

    public static String getFilePostFix (String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public static String handleFileName(String fileName){
        String postFix  = fileName.substring(fileName.lastIndexOf("."));
        String newName = UUID.randomUUID().toString().replace("-","")+postFix;
        return newName;
    }

    @Autowired
    private FastFileStorageClient fileStorageClient;

    @Autowired
    private UploadProperties prop;

    public String saveImage(MultipartFile file, String name) {
        if(!prop.getAllowTypes().contains(file.getContentType())){
            throw new LyException(ExceptionEnum.NOT_FILE_TYPES);
        }
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image==null){
                throw new LyException(ExceptionEnum.NOT_FILE_TYPES);
            }
            String filePostFix = getFilePostFix(file.getOriginalFilename());
            StorePath storePath = fileStorageClient.uploadFile(file.getInputStream(), file.getSize(), filePostFix, null);
//            String newName = handleFileName(file.getOriginalFilename());
//            File dest = new File("/Users/admin/Desktop/java_code/upload",newName );
//            file.transferTo(dest);

            return prop.getBaseUrl()+ storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("[文件上传][上传失败]",e);
        }
        return null;
    }
}
