package com.jt.service;


import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/image.properties")
public class FileServiceImpl implements FileService{

    @Value("${image.localDirPath}")
    private String localDirPath;
    @Value("${image.preUrl}")
    private String preUrl;

    @Override
    public ImageVO upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        fileName = fileName.toLowerCase();
        if (!fileName.matches("^.+\\.(jpg|png|gif)$")){
            return null;
        }

        try {

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            if (height == 0 || width == 0){
                return null;
            }

            String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
            String dateDirPath = localDirPath + dateDir;
            File datePath = new File(dateDirPath);
            if (!datePath.exists()){
                datePath.mkdirs();
            }

            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = uuid + fileType;

            File filePath = new File(newFileName);
            file.transferTo(filePath);

            String virtualPath = dateDir + newFileName;
            String url = preUrl + virtualPath;
            return new ImageVO(virtualPath, url, newFileName);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteFile(String virtualPath) {
        String filePath = localDirPath + virtualPath;
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }
    }
}
