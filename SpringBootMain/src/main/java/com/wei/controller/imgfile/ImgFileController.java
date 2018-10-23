package com.wei.controller.imgfile;


import com.wei.model.imgfile.ImgFileModel;
import com.wei.service.imgfile.ImgFileService;
import com.wei.utils.define.StatusDefine;
import com.wei.utils.define.StatusDefineMessage;
import com.wei.utils.json.JsonResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wei.peng
 * @Description:
 * @Date:Created in 2018-10-15 10:04
 * @Modified By:
 */
@RestController
@Api(description = "图片管理接口")
public class ImgFileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ImgFileService imgFileService;

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param
     * @Date:2018/10/15 11:00
     * @Return:
     * @Modified By:
     */
    @ApiOperation(value = "新增图片", httpMethod = "POST", response = String.class, notes = "新增图片")
    @RequestMapping(value = "/api/fileUpload", method = RequestMethod.POST)
    public String addImgFile(@ApiParam(value = "关联id", required = true) @RequestParam(value = "relationId", required = true) Integer relationId,
                             @ApiParam(value = "图片类型", required = true) @RequestParam(value = "type", required = true) Integer type,
                             @ApiParam(value = "图片", required = true) @RequestParam(value = "imgfile", required = true) List<MultipartFile> files,
                             @ApiParam(value = "创建人id", required = true) @RequestParam(value = "creater", required = true) Integer creater,
                            HttpServletRequest request) {

        try {
            ImgFileModel model = new ImgFileModel();
            model.setRelationId(relationId);
            model.setType(type);
            model.setCreater(creater);
            List<String> urlList = imgFileService.addImgFile(model,files);
            return new JsonResponseData(true,
                    StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), StatusDefine.SUCCESS,
                    "插入成功", urlList).toString();
        }catch (Exception e) {
            /**抛出异常返回异常信息*/
            logger.error("controller:ImgFileController. function:addImgFile..msg:Upload Thumbnail Exception. error:" + e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param
     * @Date:2018/10/15 11:00
     * @Return:
     * @Modified By:
     */
    @ApiOperation(value = "删除图片", httpMethod = "DELETE", response = String.class, notes = "删除图片")
    @RequestMapping(value = "/api/fileUpload", method = RequestMethod.DELETE)
    public String deleteImgFile(@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
                                HttpServletRequest request) {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            int ret = imgFileService.deleteImgFile(map);
            if (ret > 0) {
                return new JsonResponseData(true, "", StatusDefine.SUCCESS,
                        StatusDefineMessage.GetMessage(StatusDefine.SUCCESS), ret).toString();
            } else {
                return new JsonResponseData(false, "", StatusDefine.FAILURE,
                        StatusDefineMessage.GetMessage(StatusDefine.FAILURE), null).toString();
            }
        }catch (Exception e) {
            /**抛出异常返回异常信息*/
            logger.error("controller:ImgFileController. function:deleteImgFile..msg:Upload Thumbnail Exception. error:" + e.getMessage());
            return new JsonResponseData(false, StatusDefineMessage.GetMessage(StatusDefine.SYS_ERROR), StatusDefine.SYS_ERROR, "", null).toString();
        }
    }
}
