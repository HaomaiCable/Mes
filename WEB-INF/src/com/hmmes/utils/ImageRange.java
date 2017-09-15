package com.hmmes.utils;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * ͼƬ|���|����ͼ200�ĸ߶�|���㹤����
 * @author Administrator
 *
 */
public class ImageRange {
	private int width;
	private int height;

	
	/**
	 * ���������ͼƬ�Ŀ� ��
	 * @param picUrl
	 */
	public ImageRange(String picUrl) throws Exception{
		InputStream input = null;
		try {
			input = JMagickUtils.Utils.ofUrl(picUrl,picUrl).getInput();
			size(input);
		} catch (Exception e) {
//			System.out.println("�����쳣��"+picUrl);
			input = JMagickUtils.Utils.ofUrl(picUrl).getInput();
			size(input);
		}
		
	}
	
	/**
	 * �����InputStreamͼƬ�Ŀ��
	 * @param input
	 */
	public ImageRange(InputStream input) throws Exception{
		size(input);
	}
	
	/**
	 * 
	 * @param srcPath
	 * @return
	 * @throws FileNotFoundException
	 */
	private  void size(InputStream input) throws Exception{
		BufferedImage image = null;
		try {
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			input = null; //�����ڴ�
			image = null; //�����ڴ�
		}
	}
	
	/**
	 * ���� ���������ͼ�ĸ߶�
	 */
	public int getMinHeight(){
		int h = 0 , target_width = 200;
		h = (int) Math.round((height * target_width * 1.0 / width));
		return h; 
	}
	
	/**
	 * ��ȡ�߶�
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	
	/**
	 * ��ȡ���
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	
}
