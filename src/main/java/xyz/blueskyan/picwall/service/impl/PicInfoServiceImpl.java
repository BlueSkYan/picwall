package xyz.blueskyan.picwall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.blueskyan.picwall.pojo.PicInfo;
import xyz.blueskyan.picwall.respository.PicInfoRespository;
import xyz.blueskyan.picwall.service.PicInfoService;

import java.util.List;
@Service
public class PicInfoServiceImpl implements PicInfoService {

    @Autowired
    PicInfoRespository respository;

    @Override
    public List<PicInfo> findAll() {
        return respository.findAll();
    }

    @Override
    public PicInfo findOneById(Integer id) {
        return respository.findById(id).orElse(null);
    }

    @Override
    public PicInfo save(PicInfo picInfo) {
        return respository.saveAndFlush(picInfo);
    }
}
