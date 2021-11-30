package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public SysResult upload(MultipartFile file){
        ImageVO imageVO = fileService.upload(file);
        if (imageVO == null){
            return SysResult.fail();
        }
        return SysResult.success(imageVO);
    }

    @DeleteMapping("deleteFile")
    public SysResult deleteFile(String virtualPath){
        fileService.deleteFile(virtualPath);
        return SysResult.success();
    }
    
}
