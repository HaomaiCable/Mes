package com.hmmes.utils;

import java.util.ResourceBundle;

import org.apache.commons.lang.math.NumberUtils;

public class Constant {

	private static ResourceBundle res = ResourceBundle.getBundle("sysconfig");
	//��վ����
	public static String SSI_WEBSITE_NAME = res.getString("ssi.website.name");
	//��վ����
	public static String SSI_WEBSITE_DOMAIN = res.getString("ssi.website.domain");
	//��վ��ַ
	public static String SSI_WEBSITE_URL = res.getString("ssi.website.url");
	
	
	//��Ŀ��·�� ������tomcat�ģ����ؿ�����ɹ����ռ���Ŀ
	public static String WORK_ROOT_PATH  = res.getString("work.root.path");
	//ģ���Ŀ¼
	public static String WORK_TEMPLATE_PATH  = res.getString("work.template.path");
	
	//�ϴ���Ŀ¼��ַ http://image.mn606.com/
	public static String UPLOAD_URL = res.getString("upload.url");
	//����������
	public static String SEARCH_URL =  res.getString("search.url");
	
	//lookmn������
	public static String LOOK_URL =  res.getString("look.url");
	
	//����ͼƬ���˷���0.0-.1.0֮���С��
	public static String SEARCH_SCORE =  res.getString("search.score");
	
	public static String UPLOAD_PATH = res.getString("upload.path");
	
	public static String UPLOAD_PIC_SIZE = res.getString("upload.pic.size");
	
	//�����ļ���Ÿ�Ŀ¼
	public static String INDEX_BASE_DIR =res.getString("index.base.dir");
	
	

	/**
	 * ��������Ա����
	 * @author lu
	 *
	 */
	public static enum SuperAdmin {
		NO(0, "��"), YES(1,"��");
		public int key;
		public String value;
		private SuperAdmin(int key, String value) {
			this.key = key;
			this.value = value;
		}
		public static SuperAdmin get(int key) {
			SuperAdmin[] values = SuperAdmin.values();
			for (SuperAdmin object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

}