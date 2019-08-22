package com.leyou.upload.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.web.UploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg", "image/png", "iamge/bmp");
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    public String uploadImage(MultipartFile file) {

        try {

            // 校验文件
            String contentType = file.getContentType();

            if (!ALLOW_TYPES.contains(contentType)) {
                throw new LyException(ExceptionEnum.INVLID_FILE_TYPE);
            }

            //校验文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                throw new LyException(ExceptionEnum.INVLID_FILE_TYPE);
            }
//            read.getWidth();
            File dir = new File("D:\\heima\\upload");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(new File(dir,file.getOriginalFilename()));
            // 2.3、拼接图片地址
            String url = "http://image.leyou.com/upload/" + file.getOriginalFilename();

            return url;
        } catch (IOException e) {

            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }


    }
}
