package xyz.blueskyan.picwall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xyz.blueskyan.picwall.pojo.DataJson;
import xyz.blueskyan.picwall.pojo.PicInfo;
import xyz.blueskyan.picwall.service.MinioFileService;
import xyz.blueskyan.picwall.service.PicInfoService;
import xyz.blueskyan.picwall.utils.ChangeName;
import xyz.blueskyan.picwall.utils.FileToMultipartFile;
import xyz.blueskyan.picwall.utils.PicCompression;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private PicInfoService picInfoService;

    @Autowired
    private MinioFileService minioFileService;

    @Value("${path.tempPath}")
    private String tempPath;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.bucketName2}")
    private String bucketName2;

    @RequestMapping("text")
    public String uplaodText(@RequestParam("title") String title, @RequestParam("description") String description,
                             @RequestParam("fullsVirtualPath") String fullsVirtualPath,
                             @RequestParam("thumbsVirtualPath") String thumbsVirtualPath) throws IOException {
        PicInfo picInfo = new PicInfo();
        picInfo.setTitle(title);
        picInfo.setDescription(description);
        picInfo.setFullsLocation(fullsVirtualPath);
        picInfo.setThumbsLocation(thumbsVirtualPath);
        picInfoService.save(picInfo);
        return "redirect:/";
    }

    @RequestMapping("image")
    @ResponseBody
    public DataJson uploadImage(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String fileName = ChangeName.setUuid(file);
        minioFileService.uploadFile(file, bucketName, fileName);
        File newFile = new File(tempPath + fileName);
        PicCompression.PicCut(file, newFile);
        MultipartFile multipartFile = FileToMultipartFile.toMultipartFile(file, newFile);
        minioFileService.uploadFile(multipartFile, bucketName2, fileName);
        newFile.delete();
        String fullsUrl = endpoint + "/" + bucketName + "/" + fileName;
        String thumbsUrl = endpoint + "/" + bucketName2 + "/" + fileName;
        DataJson dataJson = new DataJson();
        if (!fileName.equals("empty")) {
            dataJson.setCode(1);
            dataJson.setMsg("上传成功");
            HashMap<String, String> map = new HashMap<>();
            map.put("fullsVirtualPath", fullsUrl);
            map.put("thumbsVirtualPath", thumbsUrl);
            dataJson.setData(map);
        } else {
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }
}
