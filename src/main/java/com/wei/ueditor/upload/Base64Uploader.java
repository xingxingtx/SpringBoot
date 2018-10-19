package com.wei.ueditor.upload;


import com.wei.ueditor.PathFormat;
import com.wei.ueditor.define.AppInfo;
import com.wei.ueditor.define.BaseState;
import com.wei.ueditor.define.FileType;
import com.wei.ueditor.define.State;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public final class Base64Uploader {
    static Logger logger = LoggerFactory.getLogger(Base64Uploader.class);
	public static State save(String content, Map<String, Object> conf) {
		
		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));
		String localSavePathPrefix = PathFormat.parse((String) conf.get("localSavePathPrefix"),
                (String) conf.get("filename"));
		savePath = savePath + suffix;
		localSavePathPrefix = localSavePathPrefix + suffix;
		String physicalPath = localSavePathPrefix;
		logger.info("Base64Uploader physicalPath:{},savePath:{}",localSavePathPrefix,savePath);
		State storageState = StorageManager.saveBinaryFile(data, physicalPath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(StringUtils.getBytesUtf8(content));
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
	
}