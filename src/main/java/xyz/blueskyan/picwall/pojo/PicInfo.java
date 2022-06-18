package xyz.blueskyan.picwall.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
@Data
@DynamicUpdate
public class PicInfo {
    @Id
    @GeneratedValue()
    private Integer id;

    private String title;

    private String description;

    private String fullsLocation;

    private String thumbsLocation;
}
