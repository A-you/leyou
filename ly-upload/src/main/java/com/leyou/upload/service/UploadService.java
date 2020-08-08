package com.leyou.upload.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UploadService {
    private static  final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg","image/jpg","image/png","image/bmp");

    public static String handleFileName(String fileName){
        String postFix  = fileName.substring(fileName.lastIndexOf("."));
        String newName = UUID.randomUUID().toString().replace("-","")+postFix;
        return newName;
    }

    public String saveImage(MultipartFile file, String name) {
        System.out.println("进入了");

        if(!ALLOW_TYPES.contains(file.getContentType())){
            throw new LyException(ExceptionEnum.NOT_FILE_TYPES);
        }
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image==null){
                throw new LyException(ExceptionEnum.NOT_FILE_TYPES);
            }
            String newName = handleFileName(file.getOriginalFilename());
            File dest = new File("/Users/admin/Desktop/java_code/upload",newName );
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传失败",e);
        }
        return null;
    }
}
