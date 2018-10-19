package com.wei.ueditor.utils;


import com.wei.config.FileuploadSetting;
import com.wei.exceptions.FileServiceException;
import com.wei.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


@Component
public class FileUtils {
    @Autowired
    private FileuploadSetting fileuploadSetting;

    public static FileUtils fileUtils;
    @PostConstruct
    public void init(){
        fileUtils=this;
        fileUtils.fileuploadSetting=this.fileuploadSetting;

    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 上传文件到本地文件系统
     * @param file
     * @return 返回文件访问路径
     */
    public  String uploadFileToLocal(MultipartFile file, HttpServletRequest request){
       /* if(fileuploadSetting==null){
            fileuploadSetting=(FileuploadSetting)SpringContextHolder.getBean("fileuploadSetting");
        }*/
        /**本地上传*/
        try {
//            String contentType = imgfile.getContentType();
            String fileName = file.getOriginalFilename().toLowerCase();
            fileName =(int)((Math.random()*9+1)*10000) +System.currentTimeMillis()+ fileName;
            String perfileName = fileName.substring(0,fileName.lastIndexOf("."));
            /**md5加密*/
            String md5PerfileName = Utils.getMd5DigestAsHex(perfileName);
            String lastfileName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = md5PerfileName+lastfileName;
            String newMaxFileName = md5PerfileName+"_max"+lastfileName;
            /**本地上传启用*/
//            String filePath = request.getSession().getServletContext().getRealPath(fileUtils.fileuploadSetting.getSavepath());
            /**测试及生产上传启用*/
            String filePath = fileUtils.fileuploadSetting.getImgURL();
            String result=null;
            /** 如果是图片类型文件 */
            if (fileName.endsWith("jpg") || fileName.endsWith("jpeg")
                    || fileName.endsWith("png") || fileName.endsWith("gif")
                    || fileName.endsWith("bmp")) {
                File f = null;
                if (!(file.equals("") || file.getSize() <= 0)) {
                    /**上传压缩图片*/
                    InputStream ins = file.getInputStream();
                    f = new File(fileName);
                    FileUtils.inputStreamToFile(ins, f);
                    result = FileUtils.uploadImage(f, filePath, newFileName, FileUtils.getImgWidth(f), FileUtils.getImgHeight(f),
                            true);
                    File del = new File(f.toURI());
                    del.delete();
                    /**上传原图片*/
                    FileUtils.uploadFile(file.getBytes(), filePath, newMaxFileName);
                }
            }else {
                /**上传其它文件*/
                result = FileUtils.uploadFile(file.getBytes(), filePath, newFileName);
            }
            /**返回文件访问路径*/
            result =fileUtils.fileuploadSetting.getFileURL()+fileUtils.fileuploadSetting.getSavepath()+result;
            return result;
        } catch (Exception e) {
            logger.error("uploadFileToLocal error ");
            logger.error(e.getMessage());
            return null;
        }
    }
    /**
     * @Author:jzwx
     * @Desicription: 输出流转换为file文件类型
     * @param ins  上传图片的输出流
     * @param file 文件流
     * @Date:Created in 15:33 2018/5/22
     * @Return:
     * @Modified By:
     */
    public static void inputStreamToFile(InputStream ins, File file) throws Exception{
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                os.close();
            }
            if(ins != null){
                ins.close();
            }
        }
    }

    /**
     * @Author:jzwx
     * @Desicription: 上传图片方法
     * @param file 图片文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param outputWidth 图片宽度
     * @param outputHeight 图片高度
     * @param proportion 是否等比压缩
     * @Date:Created in 15:31 2018/5/22
     * @Return:
     * @Modified By:
     */
    public static String uploadImage(File file, String filePath, String fileName, int outputWidth,
                              int outputHeight, boolean proportion) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        Image img = ImageIO.read(file);
        // 判断图片格式是否正确
        if (img.getWidth(null) == -1) {
            return "no";
        } else {
            int newWidth;
            int newHeight;
            // 判断是否是等比缩放
            if (proportion == true) {
                // 为等比缩放计算输出的图片宽度及高度
                double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
                double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
                // 根据缩放比率大的进行缩放控制
                double rate = rate1 > rate2 ? rate1 : rate2;
                newWidth = (int) (((double) img.getWidth(null)) / rate);
                newHeight = (int) (((double) img.getHeight(null)) / rate);
            } else {
                newWidth = outputWidth; // 输出的图片宽度
                newHeight = outputHeight; // 输出的图片高度
            }
            BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight,
                    BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(img, 0, 0, newWidth, newHeight, null);
            ImageIO.write(tag, "jpg", new File(filePath + fileName));
        }
        return fileName;
    }

    /**
     * 获取图片宽度
     * @param file  图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 获取图片高度
     * @param file  图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * @author chaoqing.zhang
     * @date 2018/3/26 15:14
     * @param   file  文件
     * @param   filePath  文件路径
     * @param   fileName  文件名称
     * @return 路径
     */
    public  static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        FileOutputStream out = null;
        try {
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
        }catch (Exception e){
            throw new FileServiceException("文件上传出错！");
        }finally {
            if(out != null) {
                out.close();
            }
        }
        return fileName;
    }
}
