package xyz.blueskyan.picwall.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.blueskyan.picwall.pojo.PicInfo;

import java.util.Optional;

public interface PicInfoRespository extends JpaRepository<PicInfo,String> {
    Optional<PicInfo> findById(Integer id);
}
