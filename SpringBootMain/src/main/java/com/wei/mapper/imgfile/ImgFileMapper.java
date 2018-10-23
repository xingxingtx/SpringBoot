package com.wei.mapper.imgfile;


import com.wei.model.imgfile.ImgFileModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author:wei.peng
 * @Description:
 * @Date:Created in 2018/10/15
 * @Modified By:
 */
@Mapper
@Component
public interface ImgFileMapper {

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param 
     * @Date:2018/10/15 11:09
     * @Return:
     * @Modified By:
     */
    int addImgFile(ImgFileModel model);

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param 
     * @Date:2018/10/15 11:35
     * @Return:
     * @Modified By:
     */
    List getImgFile(Map<String, Object> map);

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param
     * @Date:2018/10/15 13:27
     * @Return:
     * @Modified By:
     */
    int deleteImgFile(Map<String, Object> map);

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param 
     * @Date:2018/10/15 13:28
     * @Return:
     * @Modified By:
     */
    ImgFileModel getImgFileById(Map<String, Object> map);
}
