package net.wangjifeng.controller;

import net.wangjifeng.dto.DTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import red.wjf.download.data.DataWriter;
import red.wjf.download.data.ImageWriter;
import red.wjf.download.downloader.ImageDownloader;
import red.wjf.download.downloader.WebDownloader;
import red.wjf.download.enums.ImgSuffix;
import red.wjf.download.utils.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author: WJF
 * @date: 2020/7/29
 * @description: FileController
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private WebDownloader webDownloader;

    @Resource
    private ImageDownloader imageDownloader;

    @RequestMapping("/upload")
    public void upload(MultipartFile file) throws IOException {
        init(file, 1);
    }

    @RequestMapping("/uploads")
    public void uploads(MultipartFile[] files, DTO dto) throws IOException {
        for (int i = 0; i < files.length; i++) {
            init(files[i], i);
        }
        System.out.println(dto.getParam());
    }

    @RequestMapping("/downloadTest")
    public void downloadTest(HttpServletRequest request, HttpServletResponse response, String fileName) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("test.docx");
        try {
            DataWriter writer = new DataWriter(is);
            webDownloader.defaultDownload(request, response, fileName, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/downloadImg")
    public void downloadImg(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("img.jpg");
        ImageWriter writer = new ImageWriter(is, ImgSuffix.JPG);
        imageDownloader.defaultDownload(request, response, writer);
    }

    /**
     * 下载文件初始化
     * @param file 文件
     * @param i 为了区别文件，防止被覆盖
     * @throws IOException
     */
    private void init(MultipartFile file, int i) throws IOException {
        String filename = file.getOriginalFilename();
        OutputStream os = new FileOutputStream(new File("C:\\Users\\WJF\\Desktop" + File.separator + i + filename));
        InputStream is = file.getInputStream();
        StreamUtils.copy(is, os);
        is.close();
        os.close();
    }

}
