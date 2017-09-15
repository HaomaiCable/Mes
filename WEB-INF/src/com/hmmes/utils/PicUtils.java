package com.hmmes.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import javax.imageio.ImageIO; 
import com.hmmes.exception.ServiceException;

/**
 * ͼƬ��������
 * @author Administrator
 *
 */
public class PicUtils  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Pic pic = new Pic();
	
	private final static String hmmes_LOGO_BG ="/static/common/images/watermark_bg.png" ;
	private final static String hmmes_LOGO_LOGO ="/static/common/images/watermark_logo.png" ;
	
	public static PicUtils  Utils= new PicUtils();
	
	/**
	 * ����Ŀ¼��Сͼ
	 * @param path
	 * @param width
	 * @param height
	 * @throws IOException
	 * @throws Exception
	 */
	public static void crop(String path,int width,int height) throws IOException, Exception{
		File file =new File(path);
		if(!file.exists()){
			System.out.println("Ŀ¼���ܴ��ڣ�");
			return;
		}
		if(!file.isDirectory()){
			System.out.println("������ȷ�ļ���");
			return;
		}
		for(File f : file.listFiles()){
			String minPath = PathUtil.minPicPath(f.getAbsolutePath(), width+"_"+height);
			PicUtils.Utils.of(f).to(minPath).createThumbnail(width,height);
			System.out.println("����Сͼ"+minPath);
		}
	}
	
	/**
	 * 
	 * @param url
	 * @param count
	 */
	public static void caiji(String url,int count){
		for(int i=0;i<count ;i++){
			String fileName = (i+1)+".jpg";
			String picUrl = url+fileName;
			String path = "C:\\Users\\Administrator\\Desktop\\pic\\0624\\02\\"+fileName;
			try {
				PicUtils.Utils.ofUrl(picUrl).to(path).create();
				System.out.println(picUrl);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	
	

	
	public static void main(String[] args) throws Exception {
		
		String filePath="D:\\t\\2.jpg";
    	String toPath="D:\\t\\2_magick.jpg";
    	System.out.println("���");

	}
	
	
	/**
	 * �����ļ���
	 * @param path
	 */
	private static void createDir(String path){
		File file = new File(path);
		createDir(file);
	}
	
	/**
	 * �����ļ���
	 * @param path
	 */
	private static void createDir(File file){		
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @return
	 * @throws FileNotFoundException
	 */
	public PicUtils ofUrl(String picUrl) throws Exception{
		HttpURLConnection conn = (HttpURLConnection) new URL(picUrl).openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("referer", picUrl);
		conn.setRequestProperty("cookie", "data");
		InputStream is = conn.getInputStream();
		return of(is);
	}
	
	/**
	 * ��ȡͼƬInputStream
	 * @param picUrl
	 * @return
	 * @throws IOException 
	 */
	public static InputStream toInStream(String path) throws IOException{
		InputStream stream = new FileInputStream(path);
		return stream;
	}
	
	
	/**
	 * ��ȡ����ͼƬ�� InputStream
	 * @param picUrl
	 * @return
	 * @throws IOException 
	 */
	public static InputStream urlToInStream(String picUrl)  throws ServiceException{
		
		try {
			URL url = new URL(picUrl);
			URLConnection urlConnection = url.openConnection();//��url����
			if(!checkPic(urlConnection.getContentType())){
				throw new ServiceException("URL"+picUrl + " is not (jpg|gif|png)");
			}
			InputStream stream  = urlConnection.getInputStream();
			return stream;
		} catch (IOException e) {
			throw new ServiceException("URL:"+picUrl + " is connect err"); 
		}
	}
	
	

	
	/**
	 * 
	 * @param srcPath
	 * @return
	 * @throws FileNotFoundException
	 */
	public PicUtils of(String srcPath) throws Exception{
		File imgfile = new File(srcPath);
		return of(imgfile);
	} 
	
	public PicUtils of(File srcFile) throws Exception{
		InputStream is = new FileInputStream(srcFile);
		return of(is);
	} 
	

	
	public PicUtils of(InputStream inputStream) throws Exception{
		pic.setInputStream(inputStream);
		return this;
	} 
	
	
	
	public PicUtils to(String targetPath) throws Exception{
		File file = new File(targetPath);
		return to(file);
	}
	
	
	public PicUtils to(File targetFile) throws Exception{
		createDir(targetFile);
		OutputStream os = new FileOutputStream(targetFile);
		return to(os);
	}
	
	public PicUtils to(OutputStream outputStream) throws Exception{
		pic.setOutputStream(outputStream);
		return this;
	}
	
	
	public void create() throws Exception{
		createJPG(pic.getInputStream(), pic.getOutputStream(),true);
		//BufferedImage src= ImageIO.read(pic.getInputStream());
	//	IOUtils.copy(pic.getInputStream(), pic.getOutputStream());
		System.out.println("�����ɹ�~~");
	}
	
	public void create(boolean isAddLogo) throws Exception{
		createJPG(pic.getInputStream(), pic.getOutputStream(),isAddLogo);
//		BufferedImage src= ImageIO.read(pic.getInputStream());
	//	IOUtils.copy(pic.getInputStream(), pic.getOutputStream());
//		System.out.println("�����ɹ�~~");
	}
	
	
	
	
	/**
	 * ����IO���������ļ�
	 * @param is
	 * @param os
	 * @param isLogo �Ƿ����ˮӡ true=���,�����=false
	 * @throws Exception
	 */
	public void createJPG(InputStream is,OutputStream os,boolean isAddLogo)throws Exception{
		BufferedImage src= ImageIO.read(is);
		int width = src.getWidth();
		int height = src.getHeight();
		
		Graphics2D g = src.createGraphics();
		//ˮӡ����ͼƬ
		File fileBg =  new File(PathUtil.getRootPath()+hmmes_LOGO_BG);
		File fileLogo =  new File(PathUtil.getRootPath()+hmmes_LOGO_LOGO);
		System.out.println(fileBg.getAbsolutePath());
		System.out.println(fileBg.exists() +": "+fileLogo.exists());
		if(fileBg.exists() && fileLogo.exists() && isAddLogo){
			//ˮӡ�ײ���͸��ͼƬ
			Image watermark_bg = ImageIO.read(fileBg);
			//ˮӡLogo
			Image watermark_logo = ImageIO.read(fileLogo);
			if(watermark_logo.getWidth(null)< width){
				g.drawImage(watermark_bg, 0, height-30,width,30, null);
				g.drawImage(watermark_logo, 0, height-30, null);
			}
		}
        g.dispose();
		ImageIO.write(src, "JPEG" ,os);
	}

	

	/**
	 * ��ͼƬת��InputStream  ��: PicUtils.Utils.of("c://1.jpg").inputStream();
	 * @return  ���� InputStream
	 * @throws Exception
	 */
	public InputStream inputStream() throws Exception{
		return pic.getInputStream();
	}
	
	/**
	 * ��ͼƬת��BufferedImage  ��: PicUtils.Utils.of("c://1.jpg").buffereImage();
	 * @return  ���� BufferedImage
	 * @throws Exception
	 */
	public BufferedImage buffereImage() throws Exception{
		return ImageIO.read(pic.getInputStream()); 
	}
	
	/**
	 * ��������ͼ 
	 * @param width ���
	 * @param height �߶�
	 * @return
	 * @throws IOException
	 */
	public PicUtils createThumbnail(int width,int height) throws IOException{
		int x = 0 , y = 0;
		return createThumbnail(x, y, width, height);
	}
	
	/**
	 * ��������ͼ
	 * @param x ����
	 * @param y ����
	 * @param width ���
	 * @param height �߶�
	 * @throws IOException
	 */
	public PicUtils createThumbnail(int x ,int y ,int width,int height) throws IOException{
		BufferedImage image = get(width, height);
		ImageIO.write(image, "JPEG" ,pic.getOutputStream());
		pic.setInputStream(null);
		pic.setOutputStream(null);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(pic.getOutputStream());  
//        encoder.encode(image);  
//        pic.getOutputStream().close();
		return this;
	}
	
	
	 
	public BufferedImage get(int width,int height)  throws IOException{
		BufferedImage srcImg  = pic.asBufferImg();
		int target_width = width;
		int target_height = height;
		int imageWidth = srcImg.getWidth();
		int imageHeight = srcImg.getHeight();
		
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		// �õ����ʵ�ѹ����С����������
		if (imageWidth <= imageHeight) {
			w = target_width;
			h = (int) Math.round((imageHeight * target_width * 1.0 / imageWidth));
			if(target_width > h   ){
				y = (h - height) / 2;
			}
		} else {
			h = target_height;
			w = (int) Math.round((imageWidth * target_height * 1.0 / imageHeight));
			//x = (w - width) / 2;
		}
		
		// ����ͼƬ����
		BufferedImage image = new BufferedImage(w,
				h, BufferedImage.TYPE_INT_RGB);
		// ������С���ͼ
		image.getGraphics().drawImage(srcImg, x, y, w, h, null);
		return image;
	}
	
	
	
	/** 
	  * ����ͼƬ����ͼ(�ȱ�����) 
	  * @param src ԴͼƬ�ļ�����·�� 
	  * @param dist Ŀ��ͼƬ�ļ�����·�� 
	  * @param width ���ŵĿ�� 
	  * @param height ���ŵĸ߶� 
	  */  
	 public void createThumbnail(String src,String dist,float width,float height){  
	  try{  
//	   File srcfile = new File(src);  
//	   if(!srcfile.exists()){  
//	    System.out.println("�ļ�������");  
//	    return;  
//	   }  
	   BufferedImage image = pic.asBufferImg();
	     
	   //������ŵı���  
	   double ratio = 1.0;  
	   //�ж�����ߡ����������趨ֵ���򲻴���  
	   if(image.getHeight()>height || image.getWidth()>width){   
	    if(image.getHeight() > image.getWidth()){  
	     ratio = height / image.getHeight();  
	    } else {  
	     ratio = width / image.getWidth();  
	    }  
	   }  
	   //�����µ�ͼ���Ⱥ͸߶�  
	   int newWidth =(int)(image.getWidth()*ratio);  
	   int newHeight =(int)(image.getHeight()*ratio);  
	     
	   BufferedImage bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);  
	   bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH),0,0,null);  
	     


    String formatName = dist.substring(dist.lastIndexOf(".") + 1);   
	   //FileOutputStream os = new FileOutputStream(dist);  
	  // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);  
	  // encoder.encode(bfImage);  
	 //  os.close();   
   
      ImageIO.write(bfImage, /*"GIF"*/ formatName /* format desired */ , new File(dist) /* target */ );  


	   System.out.println("��������ͼ�ɹ�");  
	  } catch(Exception e) {  
		  e.printStackTrace();
	   System.out.println("��������ͼ�����쳣"+e.getMessage());  
	  }  
	 }  
	 
	 
		/**
		 * ���ͼƬ�Ƿ���ȷ
		 * @param imgtype
		 * @return
		 */
		public static boolean  checkPic(String imgtype){
			if(imgtype.equalsIgnoreCase("image/gif")){
				return true;
			}else if(imgtype.equalsIgnoreCase("image/png")){
				return true;
			}else if(imgtype.equalsIgnoreCase("image/jpeg")){
				return true;
			}
			return false;
		}
		
	 
 	/**
	 * �������ͷ��غ�׺��
	 * @param imgtype
	 * @return
	 */
	public static String getPostfix(String imgtype){
		if(imgtype.equalsIgnoreCase("image/gif")){
			return ".gif";
		}else if(imgtype.equalsIgnoreCase("image/png")){
			return ".png";
		}else if(imgtype.equalsIgnoreCase("image/jpeg")){
			return ".jpg";
		}
		return ".jpg";
	}
	
	/**
	 * �������ͷ��غ�׺��
	 * @param imgtype
	 * @return
	 */
	public static String getPicfix(String url){
		if(url.endsWith(".gif")){
			return ".gif";
		}else if(url.endsWith(".png")){
			return ".png";
		}else if(url.endsWith(".jpg")){
			return ".jpg";
		}
		return ".jpg";
	}
	
}



class Pic{
	
	private  OutputStream outputStream;
	
	private  InputStream inputStream;
	
	

	/**
	 * ת��ΪBufferedImage
	 * @return
	 * @throws IOException
	 */
	public BufferedImage asBufferImg() throws IOException{
		if(inputStream == null){
			throw new IOException("file not exist!");
		}
		return ImageIO.read(inputStream);
	}
	
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	
	
}
