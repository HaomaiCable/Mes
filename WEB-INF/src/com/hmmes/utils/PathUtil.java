package com.hmmes.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * <br>
 * <b>���ܣ�</b>��ϸ�Ĺ�������<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 23, 2011 <br>
 * <b>�����ߣ�</b><br>
 * <b>���ڣ�</b> <br>
 * <b>�������ݣ�</b><br>
 */
public class PathUtil {
	
	private static Logger log =Logger.getLogger(PathUtil.class);
	
	/**
	 * ��ȡ��Ŀ�������ڸ�Ŀ¼  �磺F:\openwork\mn606\WebRoot
	 * @return
	 */
	public static String getRootPath(){
		
//		String rootPath ="";
//		try{
//			 File file = new File(PathUtil.class.getResource("/").getFile());
//			 rootPath = file.getParentFile().getParent();
//			 rootPath = java.net.URLDecoder.decode(rootPath,"utf-8");
//			 return rootPath;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		return Constant.WORK_ROOT_PATH;
	}
	
	
	/**
	 * ��վ��Ŀ·�� http://www.yy606.com/
	 * @return
	 */
	public static String getBasePath(){
		String str = Constant.SSI_WEBSITE_URL;
    	if(StringUtils.isNotBlank(str)){
            try {
				return URLDecoder.decode(str,"utf-8");
			} catch (UnsupportedEncodingException e) {
				log.error("��ȡ��·���쳣��",e);
			}
        }
		return str;
	}

	/**
	 * ��վ��Ŀ·�� http://www.yy606.com/
	 * @return
	 */
	public static String getBasePath(String patth){
		return getBasePath()+patth;
	}
	
    
	/**
     * ��ȡ�����URL
     * @param classifyId ����id
     * @param pageId ��ҳ���
     * @return http://localhost:8080/mn606/classify/2012061009115579058-1.html
     */
	public static String classifyUrl(Long classifyId,Integer pageId){
		if(pageId == null || pageId < 1){
			pageId = 1;
		}
	    StringBuffer url = new StringBuffer(getBasePath());
	    //�жϷ���id�Ƿ�Ϊ��
	    if(classifyId != null && classifyId > 1){
	    	url.append("classify/").append(classifyId).append("-");
	    }else{
	    	url.append("list/");
	    }
	    url.append(pageId).append(".html");
		return url.toString();
	}
	    
	  /**
     * ��ȡ����URL
     * @param albumId ���Id
     * @param pageId ��ҳ���
     * @return http://localhost:8080/mn606/album/2012061009115579058-1.html
     */
    public static String albumUrl(Long albumId,Integer pageId){
    	if(pageId == null || pageId < 1){
			pageId = 1;
		}
    	StringBuffer url = new StringBuffer(getBasePath());
  	    url.append("album/").append(albumId);
  	    url.append("-").append(pageId).append(".html");
  		return url.toString();
    }
    
    /**
     * ͼƬ��ϸҳ���URL
     * @param picId ҳ��
     * @return http://localhost:8080/mn606/pic/2012061723135819196.html
     */
    public static String picPageUrl(Long picId){
    	  StringBuffer url = new StringBuffer(getBasePath());
  	    url.append("pic/").append(picId).append(".html");
  		return url.toString();
    }
	
    /**
     * ����ǽ��URL
     * @param picId ҳ��
     * @return http://localhost:8080/mn606/message/1.html
     */
    public static String messageUrl(Integer pageId){
    	StringBuffer url = new StringBuffer(getBasePath());
  	    url.append("message/");
  	    if(pageId > 1 ){
  	    	url.append(pageId).append(".html");
  	    }
  		return url.toString();
    }
	
	/**
	 * 
	 * <br>
	 * <b>���ܣ�</b>��ȡͼƬ��·��<br>
	 * <b>���ߣ�</b>�����<br>
	 * <b>���ڣ�</b> Dec 23, 2011 <br>
	 * @param picId
	 * @return http://image.ssi.com/upload/image/
	 */
	public static String getImageBaseURL(){
		return Constant.UPLOAD_URL;
//		return null;
	}
	
	
	/**
	 * ��ȡͼƬURL ��������
	 * @param picUrl ���ݿ�picUrl�ֶ����ݣ���������
	 * @return
	 */
	public static String pic(String picUrl){
		if(StringUtils.isNotBlank(picUrl)){
    		//��"\"�滻��"/"
    		picUrl = picUrl.replaceAll("\\\\","/");
    		return PathUtil.getImageURL(picUrl);
		}
        return "";
	}
	
	/**
	 * ��ȡͼƬURL ��������
	 * @param picUrl ���ݿ�picUrl�ֶ����ݣ���������
	 * @param cropArea ͼƬ�ߴ� ��ʽ��50_50 ��������鿴�����ļ�appkey.properties
	 * @return 
	 */
	
	 public static String minPic(String picUrl, String size){
		if (StringUtils.isNotBlank(size)) {
			picUrl = PathUtil.minPicPath(picUrl, size);
		}
		return pic(picUrl);
	}
	
	
	/**
	 * 
	 * <br>
	 * <b>���ܣ�</b>ͼƬ·��<br>
	 * <b>���ߣ�</b>�����<br>
	 * <b>���ڣ�</b> Dec 23, 2011 <br>
	 * 
	 * @param picId
	 * @return ����ͼƬ·�� http://image.ssi.com/upload/image/photo/1111.jpg
	 */
	public static String getImageURL(String picUrl){
		if(picUrl.indexOf("http://")<0){
			return getImageBaseURL()+picUrl;
		}
		return picUrl;
	}
	

	/**
	 * ��ȡ�ϴ�ͼƬ·��
	 * @param fileName �ļ����� 2012061717081386488.jpg
	 * @param sizeDir �ߴ�Ŀ¼ ��ʽ:100_100 ԭͼ:ori
	 * @return  \images\2012\06\17\100_100\1111.jpg
	 */
	public static String uploadPicPath(String fileName,String sizeDir){
		Date now = new Date(); //��ǰ����
		StringBuffer path = new StringBuffer(); 
	//	path.append(Constant.UPLOAD_PATH); //�ϴ���Ŀ¼
		path.append("images").append(File.separatorChar);//ͼƬ��Ŀ¼
		path.append(DateUtil.convertDateToYear(now)).append(File.separatorChar); //�� 2012
		path.append(DateUtil.convertDateToMonth(now)).append(File.separatorChar); //�� 06
		path.append(DateUtil.convertDateToDay(now)).append(File.separatorChar); //�� 17
		path.append(sizeDir).append(File.separatorChar); // ͼƬ
		path.append(fileName);
		return path.toString();
	}
	
	/**
	 * ��ȡ�ϴ�ͼƬ·��
	 * @param fileName �ļ����� 2012061717081386488.jpg
	 * @param sizeDir �ߴ�Ŀ¼ ��ʽ:100_100 ԭͼ:ori
	 * @return  \images\2012\06\17\100_100\1111.jpg
	 */
	public static String uploadPath(String fileName,String dir,String sizeDir){
		Date now = new Date(); //��ǰ����
		StringBuffer path = new StringBuffer(); 
		path.append(Constant.UPLOAD_PATH); //�ϴ���Ŀ¼
		path.append(dir).append(File.separatorChar);//ͼƬ��Ŀ¼
		path.append(DateUtil.convertDateToYear(now)).append(File.separatorChar); //�� 2012
		path.append(DateUtil.convertDateToMonth(now)).append(File.separatorChar); //�� 06
		path.append(DateUtil.convertDateToDay(now)).append(File.separatorChar); //�� 17
		path.append(sizeDir).append(File.separatorChar); // ͼƬ
		path.append(fileName);
		return path.toString();
	}
	
	/**
	 * ��ȡСͼ����·����ori �滻��ָ�� �ߴ�Ŀ¼
	 * @param filePath  \images\2012\06\17\ori\2012061717081386488.jpg
	 * @param sizeDir 200_200
	 * @return ���ؽ�� \images\2012\06\17\200_200\2012061717135895318.jpg
	 */
	public static String minPicPath(String picPath,String sizeDir){
		if(StringUtils.isBlank(picPath)){
			return "";
		}
		String path  = picPath.replace("ori", sizeDir);  
		return path;
	}
	
	public static String searchUrl(String url){
		
		return url.toString();
	}
	
	/**
	 * ��������ͼƬURL
	 * @param picUrl
	 * @param pageId 
	 * @return
	 */
	public static String searchSimUrl(String picUrl,String title,Integer pageId){
		StringBuffer url = new StringBuffer(Constant.SEARCH_URL);
		url.append("similar");
		if(StringUtils.isNotBlank(picUrl)){
			if(picUrl.indexOf("http://") < 0){
				picUrl = pic(picUrl);
			}
			url.append("?picUrl=").append(picUrl);
		}else{
			return url.toString();
		}
		if(pageId != null & pageId > 1){
			url.append("&page=").append(pageId);
		}
		if(StringUtils.isNotBlank(title)){
			url.append("&title=").append(title);
		}
		return url.toString();
	}
	
	/****************** ilook MM  url start*******************************/
	/**
     * ��ȡ�����URL
     * @param classifyId ����id
     * @param pageId ��ҳ���
     * @return http://localhost:8080/mn606/app/classify/2012061009115579058-1.html
     */
	public static String ilookListUrl(Long classifyId,Integer pageId){
		if(pageId == null || pageId < 1){
			pageId = 1;
		}
	    StringBuffer url = new StringBuffer(Constant.LOOK_URL);
	    //�жϷ���id�Ƿ�Ϊ��
	    if(classifyId != null && classifyId > 1){
	    	url.append("classify/").append(classifyId).append("-");
	    }else{
	    	url.append("list/");
	    }
	    url.append(pageId).append(".html");
		return url.toString();
	}
	
	/**
     * ��ȡ�����URL
     * @param classifyId ����id
     * @param pageId ��ҳ���
     * @return http://localhost:8080/mn606/classify/2012061009115579058-1.html
     */
	public static String ilookItemUrl(Long picId){
		StringBuffer url = new StringBuffer(Constant.LOOK_URL);
  	    url.append("item/").append(picId).append(".html");
  		return url.toString();
	}
	/****************** ilook MM  url end*******************************/
	 public static void main(String[] args) {
//		 String t = uploadPicPath(MethodUtil.getInit().getLongId()+".jpg","ori");
//		 t = minPicPath(t,"200_200");
		 System.out.println(getRootPath());
	 }

	
	
}
