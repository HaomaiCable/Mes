package com.hmmes.utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import magick.CompositeOperator;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ͼƬ������
 * @author Vowo
 *
 */
public class JMagickUtils {
	
	

	private String toPath; 
	
	private InputStream input;

//	private final static String hmmes_LOGO_BG ="d:\\t2\\watermark_bg.png";
//	private final static String hmmes_LOGO_LOGO = "d:\\t2\\watermark_logo.png";
	private final static String hmmes_LOGO_BG = PathUtil.getRootPath()+"/static/common/images/watermark_bg.png" ; //ˮӡ����ͼƬ ��ɫ��͸��
	private final static String hmmes_LOGO_LOGO = PathUtil.getRootPath()+"/static/common/images/watermark_logo.png" ;//ˮӡͼƬ ����+��ַ

	
	public JMagickUtils(){
	    //����©���������Ȼjmagick.jar��·���Ҳ���   
		System.setProperty("jmagick.systemclassloader","no"); 
	}
	
	public static JMagickUtils  Utils= new JMagickUtils();

	/**
	 * ��ȡ����ͼƬ
	 * @param url
	 * @return
	 */
	public JMagickUtils ofUrl(String picUrl) throws IOException{
		URL url = new URL(picUrl);
		URLConnection urlConnection = url.openConnection();//��url����
		InputStream is = urlConnection.getInputStream();
		urlConnection.getContentType();
		return of(is);
	}

	/**
	 * ��ȡ������ͼƬ
	 * @param url
	 * @param refererURL
	 * @return
	 * @throws IOException
	 */
	public JMagickUtils ofUrl(String url,String refererURL) throws IOException{
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("referer", refererURL);
		conn.setRequestProperty("cookie", "data");
		InputStream is = conn.getInputStream();
		return of(is);
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public JMagickUtils of(String path) throws IOException{
		File file =  new File(path);
		return of(file);
	}
	
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public JMagickUtils of(File file) throws IOException{
		InputStream input = new FileInputStream(file);
		return of(input);
	}
	
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public JMagickUtils of(InputStream input ) throws IOException{
		this.input = input;
		return this;
	} 
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public JMagickUtils to(String path){
		this.toPath = path;
		return this;
	}
	
	/**
	 * ����ͼƬ��Ĭ������ͼƬlogoˮӡ
	 * @throws Exception
	 */
	public void create() throws Exception{
		create(true);
	}
	
	/**
	 *  ����ͼƬ
	 * @param isAddLogo  �Ƿ�����ˮӡ 
	 * @throws Exception
	 */
	public void create(boolean isAddLogo) throws Exception{
		createDir();// �����ļ���
		ImageInfo info = null;   
        MagickImage image = null;
        MagickImage mask = null;//ˮӡ 
        int width,height;
        try{   
        	 info = new ImageInfo();   
             image = new MagickImage(info, getBytes());   
	         image.setFileName(toPath);   
	         width = (int)image.getDimension().getWidth();
	         height = (int)image.getDimension().getHeight();
	        //���ˮӡ start
	 		File fileBg =  new File(hmmes_LOGO_BG);
	 		File fileLogo =  new File(hmmes_LOGO_LOGO);
	 		if(fileBg.exists() && fileLogo.exists() && isAddLogo){
	 			mask = new MagickImage(new ImageInfo(hmmes_LOGO_BG));  
	 			image.compositeImage(CompositeOperator.AtopCompositeOp, mask,0, height-30);  
	 			mask = new MagickImage(new ImageInfo(hmmes_LOGO_LOGO));  
	 			image.compositeImage(CompositeOperator.AtopCompositeOp, mask, 0,  height-30);  
	 		}
	 		//���ˮӡ end
	        image.writeImage(info);   
	        System.out.println("����ͼƬ��"+toPath);
        }finally{   
            if(image != null){   
            	image.destroyImages();   
            }   
            info = null;   
            image = null;
            mask = null;
            destroy();
        }     
	}
	
	/**
	 * ����ͼƬ��ָ��ͼƬ��ȣ��߶��Զ�����������
	 * @param width  ���
	 * @throws MagickException
	 */
	public void create(int width) throws Exception{
		createDir();// �����ļ���
		ImageInfo info = null;   
        MagickImage image = null;   
        Dimension imageDim = null;   
        MagickImage scaled = null;   
        try{  
             info = new ImageInfo();   
             image = new MagickImage(info, getBytes());   
	         imageDim = image.getDimension();  
	         int imageWidth = (int)imageDim.getWidth();
	         int imageHeight = (int)imageDim.getHeight();
	         int target_width = width;
	    	 int w = target_width;
	    	 int h = (int) Math.round((imageHeight * target_width * 1.0 / imageWidth));
	         scaled = image.scaleImage(w, h);// minImage.cropImage(rect);
             scaled.setFileName(toPath);
             scaled.writeImage(info);   
             System.out.println("����ͼƬ��"+toPath);
        }finally{   
            if(scaled != null){   
                scaled.destroyImages();   
            }   
            info = null;   
            image = null;   
            imageDim = null;   
            scaled = null;   
            destroy();
        }     
	}
	
	/**
	 * ����ͼƬ
	 * @param width  ָ��ͼƬ���
	 * @param height ָ��ͼƬ�߶�
	 * @throws MagickException
	 */
	public void create(int width,int height) throws Exception{
		createDir();// �����ļ���
		ImageInfo info = null;   
        MagickImage image = null;   
        Dimension imageDim = null;   
        MagickImage scaled = null;
        MagickImage minImage = null; 
        Rectangle rect = null;
        int  x = 0 ,y = 0,w = 0,h = 0,imageWidth = 0,imageHeight = 0;
        try{  
             info = new ImageInfo();   
             image = new MagickImage(info, getBytes());   
	         imageDim = image.getDimension();  
	         imageWidth = (int)imageDim.getWidth();
	         imageHeight = (int)imageDim.getHeight();
	         
	         // �õ����ʵ�ѹ����С����������
    		 if (imageWidth <= imageHeight) {
    			w = width;
    			h = (int) Math.round((imageHeight * width * 1.0 / imageWidth));
    			if(width > h   ){
    				y = (h - height) / 2;
    			}
    		 } else {
    			h = height;
    			w = (int) Math.round((imageWidth * height * 1.0 / imageHeight));
    			x = (w - width) / 2;
    		 }
    		 minImage  = image.scaleImage(w, h);
    		 rect = new Rectangle(x,y,width,height);
	         scaled = minImage.cropImage(rect);
             scaled.setFileName(toPath);   
             scaled.writeImage(info);   
             System.out.println("����ͼƬ��"+toPath);
        }finally{   
            if(scaled != null){   
            	image.destroyImages();
                scaled.destroyImages();
                destroy();
            }
            info = null;   
            image = null;   
            imageDim = null;   
            scaled = null;
            minImage = null;
        }     
	}
	
	
    public static void main(String[] args) throws MagickException {
    	String filePath="D:\\t\\3.jpg";
    	String toPath="D:\\t2\\001_magick.jpg";
    	try {
			String url = "http://www.eale.cc/UploadFiles/20127910293960.jpg";
			url="http://www.9441.com/uploads/allimg/c120703/13412UYTZ10-13YK.jpg";
			//��ȡ����ͼƬ
			JMagickUtils.Utils.ofUrl(url).to("D:\\t2\\1.jpg").create();
			//��ȡ����ͼƬ
			JMagickUtils.Utils.ofUrl(url,"http://www.9441.com/").to("D:\\t2\\1.jpg").create();
			
			JMagickUtils.Utils.of("d:\\src.jpg").to("D:\\t2\\1.jpg").create();
			//������ͼƬ
			JMagickUtils.Utils.of("d:\\src.jpg").to("D:\\t2\\1.jpg").create(200, 200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] getBytes() throws IOException {
		byte[] bytes  = IOUtils.toByteArray(input);
		return bytes;
	}
	

	/**
	 * ����InputStream
	 * @return InputStream
	 */
	public InputStream getInput(){
		return input;
	}
	
	
	public BufferedImage getBuffer() throws IOException{
		BufferedImage buffer = ImageIO.read(input);
		return buffer;
	}


	/**
	 * �����ļ���
	 * @param path
	 */
	private void createDir() throws Exception{
		if(StringUtils.isBlank(toPath)){
			throw new Exception("δָ���ļ�·��");
		}
		File file = new File(toPath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
	}
	
	public void destroy(){
		this.input = null;
	}

	  
}
