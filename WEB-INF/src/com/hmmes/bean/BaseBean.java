package com.hmmes.bean;

import java.sql.Timestamp;

import com.hmmes.bean.SiteScore.ScoreType;

/**
 * 
 * @author luozejun
 *
 */
public class BaseBean {
	
	 /**
	  * ״̬ö��
	  * @author lu
	  *
	  */
	 public static enum STATE {
		 	ENABLE(0, "����"), DISABLE(1,"����");
			public int key;
			public String value;
			private STATE(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static STATE get(int key) {
				STATE[] values = STATE.values();
				for (STATE object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}
	 	
	 	/**
	 	 * ɾ��ö��
	 	 * @author lu
	 	 *
	 	 */
	 	public static enum DELETED {
			NO(0, "δɾ��"), YES(1,"��ɾ��");
			public int key;
			public String value;
			private DELETED(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static DELETED get(int key) {
				DELETED[] values = DELETED.values();
				for (DELETED object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}
	
}
