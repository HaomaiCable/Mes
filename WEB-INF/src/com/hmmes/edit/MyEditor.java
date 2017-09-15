package com.hmmes.edit;

import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;
/**
 * 
 * @author �̶��� int 2011-4-9 ����ת����
 *
 */
public class MyEditor extends PropertyEditorSupport  {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if(text == null ||text.equals(""))
				text = "0";
			if ( !StringUtils.hasText(text)) {
			
				setValue(null);
			}
			else {
				setValue(Integer.parseInt(text));//��仰������Ҫ�ģ�����Ŀ����ͨ�����������������ƥ����Ӧ��databind
			}
		}
		/**
		 * Format the Date as String, using the specified DateFormat.
		 */
		@Override
		public String getAsText() {
			
			return getValue().toString();
		}
}