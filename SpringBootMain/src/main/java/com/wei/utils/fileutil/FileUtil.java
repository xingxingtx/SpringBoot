package com.wei.utils.fileutil;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:wei.peng
 * @Description:
 * @Date:Created in 2018/4/14
 * @Modified By:
 */
public interface FileUtil {

    String upload(MultipartFile multipartFiles, String folder);

    Boolean deleteFile(String key);

}
