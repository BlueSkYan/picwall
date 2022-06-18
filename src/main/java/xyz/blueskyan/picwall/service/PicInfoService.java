package xyz.blueskyan.picwall.service;

import xyz.blueskyan.picwall.pojo.PicInfo;

import java.util.List;

public interface PicInfoService {
    List<PicInfo> findAll();

    PicInfo findOneById(Integer id);

    PicInfo save(PicInfo picInfo);
}
