package com.wei.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Administrator
 * @date 2018/11/12
 */
@Data
public class BaseModel implements Serializable{
    private String id;

    private String state;

    private String create;

    private String editor;

    private String createTime;

    private String editorTime;
}
