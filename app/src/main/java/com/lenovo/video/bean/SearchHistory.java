package com.lenovo.video.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;

/**
 * Created by KERWIN on 2017-11-27.
 */

@Entity
public class SearchHistory {

    @NotNull
    @Unique
    @Id
    private String name;

    @NotNull
    @OrderBy("desc")
    private Date time;

    @Transient
    private boolean isDelete = false;

    @Generated(hash = 254339932)
    public SearchHistory(@NotNull String name, @NotNull Date time) {
        this.name = name;
        this.time = time;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
