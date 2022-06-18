package xyz.blueskyan.picwall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.blueskyan.picwall.pojo.PicInfo;
import xyz.blueskyan.picwall.service.PicInfoService;

import java.util.List;

@Controller
public class PicController {

    @Autowired
    private PicInfoService picInfoService;

    @RequestMapping("/")
    public String index(Model model) {
        List<PicInfo> picInfoList = picInfoService.findAll();
        model.addAttribute("picInfoList", picInfoList);
        return "index";
    }
}
