package com.hmmes.utils;

import java.io.OutputStream;  
import java.util.List;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;  
import java.lang.reflect.Field; 
import java.io.File;
  
import jxl.Workbook;  
import jxl.format.Alignment;  
import jxl.format.Border;  
import jxl.format.BorderLineStyle;  
import jxl.format.VerticalAlignment;  
import jxl.write.Label;  
import jxl.write.WritableCellFormat;  
import jxl.write.WritableFont;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  

/*** 
 * @author 
 */  
public class ExportExcel
{  
 /*************************************************************************** 
  * @param fileName EXCEL�ļ����� 
  * @param reporttitle �����
  * @param listTitle EXCEL�ļ���һ���б��⼯�� 
  * @param listContent EXCEL�ļ��������ݼ��� 
  * @return 
  */  

  public   String exportExcel(HttpSession session,String fileName,String reporttitle,String[] Title, List<Object> listContent,Integer colWidth)
  {  

     // ���¿�ʼ�����EXCEL  
     String result;    
  	  try 
	  {       
  
/**
	   //������������Ա�򿪱���Ի���______________________begin  
      //HttpServletResponse response=ServletActionContext.getResponse();  

	   //response=ServletActionContext.getResponse();
        response.setContentType("text/html;charset=gbk");
		// ��ȡ�����
		PrintWriter os = response.getWriter();
       //response.reset();// ��������    
	   //OutputStream os = response.getOutputStream();// ȡ�������    

       //response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));  
       // �趨����ļ�ͷ        
       //response.setContentType("application/msexcel");// �����������   
	   response.setContentType("APPLICATION/DOWNLOAD");
	   //response.setContentType("application/octet-stream");
       response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));  
       //response.flushBuffer();

       //wb:HSSFWorkbook
       //wb.write(response.getOutputStream()); 
       //������������Ա�򿪱���Ի���_______________________end  
//System.out.println("����ɽ��exportExcle="+fileName);  
*/
/**
//��������һ��������InputStream stream = *****;
OutputStream os = null;
response.setContentType("APPLICATION/DOWNLOAD");
response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"),"ISO8859-1")); );
//***���ļ���
response.setContentLength(stream.available());
os = response.getOutputStream();
int iBytesRead = 0;
byte[] buffer = new byte[8192];
while ((iBytesRead = stream.read(buffer, 0, 8192)) != -1)
	{os.write(buffer, 0, iBytesRead);}
os.close();
response.flushBuffer();
*/

       /** **********����������************ */  
	   String path = session.getServletContext().getRealPath("/uploadfiles/");
  	   String serverFilePath=path+File.separator+ fileName;
		//String  serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\"+fileName;
	   	//String  serverFilePath="d:\\���۶�������\\"+fileName;
       //WritableWorkbook workbook = Workbook.createWorkbook(new File("c:\\"+fileName));  //
        WritableWorkbook workbook = Workbook.createWorkbook(new File(serverFilePath));    
       /** **********����������************ */  
  
       WritableSheet sheet = workbook.createSheet("Sheet1", 0);  
  
       /** **********�����ݺ��ӡ��Ĭ��Ϊ�ݴ򣩡���ӡֽ***************** */  
       jxl.SheetSettings sheetset = sheet.getSettings();  
       sheetset.setProtected(false);  
  
 
       /** ************���õ�Ԫ������************** */  
       WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
       WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
  
       /** ************�����������ֵ�Ԫ����ʽ������************ */  
       // ���ڱ������  
       WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
       wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // ����  
       wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // ���ִ�ֱ����  
       wcf_center.setAlignment(Alignment.CENTRE); // ����ˮƽ����  
       wcf_center.setWrap(false); // �����Ƿ���  
     
       // �������ľ���  
       WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
       wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // ����  
       wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // ���ִ�ֱ����  
       wcf_left.setAlignment(Alignment.LEFT); // ����ˮƽ����  
       wcf_left.setWrap(false); // �����Ƿ���     
   
 
      /** ***************������EXCEL��ͷ�����********************* */  
      sheet.mergeCells(0, 0, colWidth-1, 0);  
      sheet.addCell(new Label(0, 0, reporttitle, wcf_center));  
      /** ***************������EXCEL��һ���б���********************* */  
      for (int i = 0; i < Title.length; i++)
	  {  
           sheet.addCell(new Label(i, 1,Title[i],wcf_center));  
 
      }  

      /** ***************������EXCEL��������********************* */  
      Field[] fields=null;  
      int i=2;  
      for(Object obj : listContent)
      //for(int ii=listContent.size()-1;ii>=0;ii--)
	  { 
		  //Object obj = listContent.get(ii);
          fields=obj.getClass().getDeclaredFields();  
          int j=0; 
          for(Field v:fields)
		  {  
               v.setAccessible(true);  
               Object va=v.get(obj);  
//System.out.println("����ɽ��exportExcle="+va);  
              if(va==null)
			  {  
                  va="";  
              }  
              sheet.addCell(new Label(j, i,va.toString(),wcf_left));  
              j++;  
          }  
         i++;  
      }  
      /** **********�����ϻ����е�����д��EXCEL�ļ���******** */  
      workbook.write();  
      /** *********�ر��ļ�************* */  
      workbook.close();   


    } 
   catch (Exception e) 
   {  
      result="ϵͳ��ʾ��Excel�ļ�����ʧ�ܣ�ԭ��"+ e.toString();  
      System.out.println(result);   
      e.printStackTrace();  
  }  
   result="ϵͳ��ʾ��Excel�ļ������ɹ���";
   //result="ϵͳ��ʾ��Excel�ļ������ɹ����ļ�λ�á�\\\\U8\\���۶�������\\"+fileName+"��,��ӡ������ھӡ�����ʹ�á�";
   System.out.println(result);   
   return result;  
  }  
 
}  
