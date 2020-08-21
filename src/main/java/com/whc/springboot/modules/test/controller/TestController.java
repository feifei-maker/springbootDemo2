package com.whc.springboot.modules.test.controller;

import com.whc.springboot.modules.test.entity.City;
import com.whc.springboot.modules.test.entity.Country;
import com.whc.springboot.modules.test.service.CityService;
import com.whc.springboot.modules.test.service.CountryService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TestController <br/>
 * Description: <br/>
 * date: 2020/8/11 18:37<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    /**
     * 127.0.0.1:81/test/indexSimple---get
     */
    @GetMapping("/indexSimple")
    public String indexSimpleTestPage(){
        return "indexSimple";
    }
    /**
     * 127.0.0.1:81/test/testMethod?paramKey=abc----get
     */
    @GetMapping("/testMethod")
    @ResponseBody
    public String testMethod(@RequestParam(value = "paramKey") String paramKey, HttpServletRequest request) {
        String paramValue = request.getParameter("paramKey");
        return "This is a testMethod " + paramKey + "==" + paramValue;
    }

    /**
     * 127.0.0.1:81/test/file ---- get
     */
    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        Resource resource = null;
        try {
            //将路径名和文件名组成的字符串转换成URL
            resource = new UrlResource(Paths.get("C:\\Users\\FEI FEI\\Pictures\\Saved Pictures\\" + fileName).toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        //设置下载的编码类型
                        .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                        //指定以附件的形式和下载文件的名字
                        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileName))
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
     */
    @RequestMapping("/download1")
    public void downloadFile1(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
        String filePath = "C:\\Users\\FEI FEI\\Pictures\\Saved Pictures" + File.separator + fileName;
        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            response.setContentType("application/octet-stream");
            response.setContentLength((int) downloadFile.length());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileName));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(downloadFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                LOGGER.debug(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                } catch (Exception e2) {
                    LOGGER.debug(e2.getMessage());
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * 以包装类 IOUtils 输出文件
     */
    @RequestMapping("/download2")
    public void downloadFile2(HttpServletRequest request,
                              HttpServletResponse response, @RequestParam String fileName) {
        String filePath = "C:\\Users\\FEI FEI\\Pictures\\Saved Pictures" + File.separator + fileName;
        File downloadFile = new File(filePath);
        try {
            if (downloadFile.exists()) {
                response.setContentType("application/octet-stream");
                response.setContentLength((int) downloadFile.length());
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", fileName));

                InputStream is = new FileInputStream(downloadFile);
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            }
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 127.0.0.1:81/test/file ---- post
     */
    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select file.");
            return "redirect:test/index";
        }
        try {
            String destFilePath = "C:\\Users\\FEI FEI\\Pictures\\Saved Pictures" + file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);
            // 使用工具类Files来上传文件
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(destFileName);
//			Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message", "Upload file success.");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Upload file failed.");
        }
        return "redirect:/test/index";
    }

    /**
     * 127.0.0.1:81/test/files----post
     */
    //在重定向时追加参数时使用RedirectAttributes
    @PostMapping(value = "/files", consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile[] files, RedirectAttributes redirectAttributes) {
        boolean empty = true;
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                } else {
                    String destFilePath = "C:\\Users\\FEI FEI\\Pictures\\Saved Pictures" + file.getOriginalFilename();
                    File destFile = new File(destFilePath);
                    file.transferTo(destFile);
                    empty = false;
                }
                if (empty) {
                    redirectAttributes.addFlashAttribute("message", "Please select file.");
                } else {
                    redirectAttributes.addFlashAttribute("message", "Upload file success.");
                }
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Upload file failed.");
            e.printStackTrace();
        }
        return "redirect:/test/index";
    }

    /**
     * 127.0.0.1:81/test/index
     */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap) {
        int countryId = 522;
        List<City> cityList = cityService.getCitiesByCountryId(countryId);
        cityList = cityList.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);
//        设置test/index里面的内容
        modelMap.addAttribute("thymeleafTitle1", "田家少闲月，五月人倍忙。");
        modelMap.addAttribute("thymeleafTitle2", "夜来南风起，小麦覆陇黄。");
        modelMap.addAttribute("checked1", true);
        modelMap.addAttribute("checked2", false);
        modelMap.addAttribute("currentNumber", 98);
        modelMap.addAttribute("thymeleafTitle3", null);
        modelMap.addAttribute("thymeleafTitle4", null);
        modelMap.addAttribute("thymeleafTitle5", null);
        modelMap.addAttribute("thymeleafTitle6", null);
        modelMap.addAttribute("thymeleafTitle7", null);
        modelMap.addAttribute("thymeleafTitle8", null);
        modelMap.addAttribute("thymeleafTitle9", null);
        modelMap.addAttribute("thymeleafTitle10", null);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cityList", cityList);
        modelMap.addAttribute("city", cityList.get(0));
        modelMap.addAttribute("internetUrl", "127.0.0.1:81/testController/testIndex2Page");
        modelMap.addAttribute("shopLogo", "/upload/1111.png");
        modelMap.addAttribute("updateCityUri", "/cityController/city");
//        给test/index设置变量
//        modelMap.addAttribute("template", "test/index");
//        返回到外层的index中
        return "index";
    }

    /**
     * 127.0.0.1:81/testController/testIndex2Page
     */
    @GetMapping("/testIndex2Page")
    public String testIndex2Page(ModelMap modelMap) {
        modelMap.addAttribute("template", "test/index2");
//        返回到外层的index中
        return "index";
    }
}
