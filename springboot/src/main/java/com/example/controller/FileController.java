package com.example.controller;


import cn.hutool.core.io.FileUtil;
import com.example.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;


@RestController
@RequestMapping("/files")
public class FileController {
    // 获取配置文件中的路由
    @Value("${ip}")
    private String ip;

    @Value("${server.port}")
    private String port;


    //指定文件上传路径
    private static final String ROOT_PATH = System.getProperty("user.dir") + "/files";

    //文件上传
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename(); // 获取到原属文件名
        long flag = System.currentTimeMillis();
        String file_name = flag + "_" + originalFilename;
        File finalFile = new File(ROOT_PATH + "/" + file_name);

        if (!finalFile.getParentFile().exists()) { //如果父级目录不存在，需要创建
            finalFile.getParentFile().mkdir();


        }
        file.transferTo(finalFile);
        String url = "http://" + ip + ":" + port + "/files/download?fileName=" + file_name;
        return Result.success(url);
    }

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(ROOT_PATH + "/" + fileName);
        ServletOutputStream os = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/octet-stream");
        FileUtil.writeToStream(file, os);
        os.flush();
        os.close();

    }


}
