package com.hmmes.action;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;


import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*** 
 * @author 
 */  
@Controller
@RequestMapping("/downLoadManage") 
public class DownLoadAction
{  

      @RequestMapping("/downLoad")
      public ResponseEntity<byte[]>  downLoad(
			String fileName,HttpSession session) throws Exception{
		// �ϴ��ļ�·��
//System.out.println("����ɽ��downLoad="+fileName);
		String path = session.getServletContext().getRealPath("/uploadfiles/");
		//String path="d:\\���۶�������\\";
		//fileName="gxs.xls";
		// ���Ҫ�����ļ���File����
		File file = new File(path+File.separator+ fileName);
		// ����springframework��HttpHeaders����
		HttpHeaders headers = new HttpHeaders();  
        // ������ʾ���ļ������������������������  
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        // ֪ͨ�������attachment�����ط�ʽ����ͼƬ
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        // application/octet-stream �� �����������ݣ�������ļ����أ���
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                headers, HttpStatus.CREATED); 
	}
}  
