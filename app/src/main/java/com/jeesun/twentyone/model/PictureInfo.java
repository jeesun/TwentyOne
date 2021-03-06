package com.jeesun.twentyone.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by simon on 2017/12/9.
 */
@Data
public class PictureInfo implements Serializable {
    private static final long serialVersionUID = 3894593466238158063L;
    private Integer pid;
    private Integer cid;
    private Integer dl_cnt;
    private Date c_t;
    private Integer imgcut;
    private String url;
    private String tempdata;
    private Integer fav_total;

    public PictureInfo() {
    }

    public PictureInfo(String url) {
        this.url = url;
    }

}
