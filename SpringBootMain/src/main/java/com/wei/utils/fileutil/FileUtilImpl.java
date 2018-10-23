package com.wei.utils.fileutil;


import com.wei.exceptions.FileServiceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author:wei.peng
 * @Description:文件上传
 * @Date:Created in 2018/10/15
 * @Modified By:
 */
@Service
public class FileUtilImpl implements FileUtil{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param
     * @Date:2018/10/15 10:01
     * @Return:
     * @Modified By:
     */
    @Override
    public String upload(MultipartFile multipartFiles, String folder){
        String prefix = multipartFiles.getOriginalFilename().substring(multipartFiles.getOriginalFilename().lastIndexOf(".") + 1);
        if(prefix.equals("")|| StringUtils.isBlank(prefix)){
            prefix = multipartFiles.getName().substring(multipartFiles.getName().lastIndexOf(".") + 1);
        }
        try {
            String key = UUID.randomUUID().toString().replace("-", "")+"."+prefix;

            String filePath = request.getSession().getServletContext().getRealPath("");
            SaveFileFromInputStream(multipartFiles.getInputStream(),filePath+folder,key);
            return folder+key;
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new FileServiceException("文件服务器出错");
        } catch (FileServiceException e) {
            logger.error(e.getMessage());
            throw new FileServiceException("文件服务器出错");
        }
    }

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param
     * @Date:2018/10/15 10:01
     * @Return:
     * @Modified By:
     */
    public void SaveFileFromInputStream(InputStream stream,String filePath, String filename) throws IOException {
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
            file.createNewFile();
        }
        FileOutputStream fs=new FileOutputStream(filePath + filename);
        byte [] buffer  = new byte [ 1024 * 1024 ];
        int  bytesum  = 0 ;
        int  byteread  = 0 ;
        while((byteread = stream.read(buffer)) != - 1 ){
            bytesum += byteread;
            fs.write(buffer,0 ,byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    /**
     * @Author:wei.peng
     * @Desicription:
     * @param
     * @Date:2018/10/15 10:01
     * @Return:
     * @Modified By:
     */
    @Override
    public Boolean deleteFile(String key) {
        String filePath = request.getSession().getServletContext().getRealPath("");
        try {
            File file = new File(filePath+key);
            if(!file.exists()){
                return false;
            }
            file.delete();
        } catch (FileServiceException e) {
            logger.error(e.getMessage());
            throw new FileServiceException("文件服务器出错");
        }
        return true;
    }
}
