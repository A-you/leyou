package com.leyou.upload;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FdfsTest {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void testUpload() throws FileNotFoundException{
        File file = new File("/Users/admin/Desktop/java_code/upload/1111.jpeg");
        StorePath jpeg = this.storageClient.uploadFile(new FileInputStream(file), file.length(), "jpeg", null);
        //带分组的路径
        System.out.println(jpeg.getFullPath());
        //不带分组的路径
        System.out.println(jpeg.getPath());

    }

    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException {
        File file = new File("/Users/admin/Desktop/java_code/upload/1111.jpeg");
        StorePath jpeg = this.storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(), "jpeg", null);
        System.out.println(jpeg.getFullPath());
        System.out.println(jpeg.getPath());

        //获取缩略图
        String thumbImagePath = thumbImageConfig.getThumbImagePath(jpeg.getPath());
        System.out.println(thumbImagePath);

    }
}
