package com.wei.ueditor.upload;


import com.wei.config.FileuploadSetting;
import com.wei.ueditor.PathFormat;
import com.wei.ueditor.define.AppInfo;
import com.wei.ueditor.define.BaseState;
import com.wei.ueditor.define.FileType;
import com.wei.ueditor.define.State;


import com.wei.ueditor.utils.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@Configuration
public class BinaryUploader {
    static Logger logger = LoggerFactory.getLogger(BinaryUploader.class);
    @Autowired
	private static FileuploadSetting fileuploadSetting;
	public static final State save(HttpServletRequest request,
								   Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

		if ( isAjaxUpload ) {
			upload.setHeaderEncoding( "UTF-8" );
		}

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("upfile");
			String savePath = (String) conf.get("savePath");
			String localSavePathPrefix = (String) conf.get("localSavePathPrefix");
			String originFileName = file.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			savePath = PathFormat.parse(savePath, originFileName);
			/*将文件上传到根目录前一级==========*/
			String[] rootpaths=((String) conf.get("rootPath")).split("/");
			StringBuilder rootpath=new StringBuilder();
			for(int i=0;i<rootpaths.length-1;i++){
				rootpath.append(rootpaths[i]).append("/");
			}

			String physicalPath = rootpath.toString() + savePath;
			/*将文件上传到根目录前一级==========end*/
			/*if(fileuploadSetting==null){
				fileuploadSetting=(FileuploadSetting)SpringContextHolder.getBean("fileuploadSetting");
			}*/
			//关闭默认的上传文件方式,如果要限制图片属性，可以开启

			/*State storageState = StorageManager.saveFileByInputStream(is,
					physicalPath, maxSize);*/
			//开启自定义上传文件方式
			//String filePath=UploadFile(file,(int)((Math.random()*9+1)*10000)+suffix,request);
			String filePath=new FileUtils().uploadFileToLocal(file,request);

			State storageState =  new  BaseState(true);
			//上传完成后填写返回信息
			if (storageState.isSuccess()) {
				File targetFile = new File(physicalPath);
				storageState.putInfo("size", targetFile.length());
				storageState.putInfo("title",targetFile.getName());
				storageState.putInfo("url", PathFormat.format(filePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}
			return storageState;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		}
	}
	/*public static String UploadImgBybyte(byte[] filebyte,
										 String fileName,
										 HttpServletRequest request) {
		//本地上传
		try {
			//System.out.println("编码格式为："+getEncoding(fileName));
			fileName =(int)((Math.random()*9+1)*10000) +System.currentTimeMillis()+ fileName;
			String perfileName = fileName.substring(0,fileName.lastIndexOf("."));
			//md5加密
			String md5PerfileName = Utils.getMd5DigestAsHex(perfileName);
			String lastfileName = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = md5PerfileName+lastfileName;
			String newMaxFileName = md5PerfileName+"_max"+lastfileName;
			//开发环境使用
			//String filePath = request.getSession().getServletContext().getRealPath(fileuploadSetting.getSavepath());
			//测试环境使用
			String filePath = fileuploadSetting.getImgURL();
			//上传文件
			File targetFile = new File(filePath);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			FileOutputStream out = new FileOutputStream(filePath+newFileName);
			out.write(filebyte);
			out.flush();
			out.close();
			//String result = uploadFile(filebyte, filePath, newFileName);
			fileName =fileuploadSetting.getFileURL()+fileuploadSetting.getSavepath()+newFileName;

			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
