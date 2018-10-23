package com.wei.model.imgfile;

/**
 * @Author:wei.peng
 * @Description:
 * @Date:Created in 2018/10/15
 * @Modified By:
 */
public class ImgFileModel {
    private Integer id;
    private Integer relationId;
    private Integer type;
    private String url;
    private Integer creater;
    private Integer editer;
    private String createTime;
    private String editTime;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getEditer() {
        return editer;
    }

    public void setEditer(Integer editer) {
        this.editer = editer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
