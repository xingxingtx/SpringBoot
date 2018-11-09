package com.api.model.user;

import com.api.model.base.BaseModel;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/7.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends BaseModel implements Serializable{

    private String userName;

    private String sex;

    private String address;

    private String phone;
}
