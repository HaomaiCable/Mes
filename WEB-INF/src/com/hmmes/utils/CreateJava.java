package com.hmmes.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.velocity.VelocityContext;



/**
 * <br>
 * <b>���ܣ�</b>��ϸ�Ĺ�������<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 16, 2011 <br>
 * <b>�����ߣ�</b><br>
 * <b>���ڣ�</b> <br>
 * <b>�������ݣ�</b><br>
 */
public class CreateJava {
	private static ResourceBundle res = ResourceBundle.getBundle("DataSourceConfig");
	private static String url= res.getString("gpt.url"); 
	private static String username =  res.getString("gpt.username");
	private static String passWord = res.getString("gpt.password");

	 //��Ŀ��·��·�����˴��޸�Ϊ�����Ŀ·��
	private static String rootPath = "F:\\360����\\hmmesWork\\hmmes_SERVICE\\";//getRootPath();// "F:\\openwork\\open\\";
	private static String actionPath = "F:\\360����\\hmmesWork\\hmmes_MS\\src\\com\\hmmes\\";


	public static void main(String[] args) {
		 CreateBean createBean=new CreateBean();
		 createBean.setMysqlInfo(url, username, passWord);
		 /** �˴��޸ĳ���� ���� �� ����ע��***/
		 String tableName="sys_menu_btn"; //
		 String codeName ="�˵���ť";//����ע��  ��Ȼ����Ӣ��Ҳ�ǿ��Ե� 
		 String className= createBean.getTablesNameToClassName(tableName);
		 String lowerName =className.substring(0, 1).toLowerCase()+className.substring(1, className.length());
		 
		 //��·��
		 String srcPath = rootPath + "src\\";
		 //��·��
		 String pckPath = rootPath + "src\\com\\hmmes\\";
		 //ҳ��·�����ŵ�WEB-INF������Ϊ�˲����ֶ�����·������jspҳ�棬�𵽰�ȫ����
		 String webPath = rootPath + "WebRoot\\jsp\\sys\\"; 
		 
		 //java,xml�ļ�����
		 String modelPath = "model\\"+className+"Model.java";
		 String beanPath =  "bean\\"+className+".java";
		 String mapperPath = "mapper\\"+className+"Mapper.java";
		 String servicePath = "service\\"+className+"Service.java";
		 String controllerPath = "action\\"+className+"Action.java";
		 String sqlMapperPath = "mybatis\\"+className+"Mapper.xml";
		//jspҳ��·��
//		 String pageEditPath = lowerName+"\\"+lowerName+"Edit.jsp";
//		 String pageListPath = lowerName+"\\"+lowerName+"List.jsp";
		 
		
		VelocityContext context = new VelocityContext();
		context.put("className", className); //
		context.put("lowerName", lowerName);
		context.put("codeName", codeName);
		context.put("tableName", tableName);
		
		/******************************����bean�ֶ�*********************************/
		try{
			context.put("feilds",createBean.getBeanFeilds(tableName)); //����bean
		}catch(Exception e){
			e.printStackTrace();
		}

		/*******************************����sql���**********************************/
		try{
			Map<String,Object> sqlMap=createBean.getAutoCreateSql(tableName);
			context.put("columnDatas",createBean.getColumnDatas(tableName)); //����bean
			context.put("SQL",sqlMap);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		
//		
		//-------------------�����ļ�����---------------------/
		CommonPageParser.WriterPage(context, "TempBean.java",pckPath, beanPath);  //����ʵ��Bean
		CommonPageParser.WriterPage(context, "TempModel.java",pckPath,modelPath); //����Model
		CommonPageParser.WriterPage(context, "TempMapper.java", pckPath,mapperPath); //����MybatisMapper�ӿ� �൱��Dao
		CommonPageParser.WriterPage(context, "TempService.java", pckPath,servicePath);//����Service
		CommonPageParser.WriterPage(context, "TempMapper.xml",pckPath,sqlMapperPath);//����Mybatis xml�����ļ�
//		CommonPageParser.WriterPage(context, "TempController.java",actionPath, controllerPath);//����Controller �൱�ڽӿ�
//		��JSPҳ�棬�������Ҫ����ע�͵�
		context.put("basePath", "${e:basePath()}");
//		CommonPageParser.WriterPage(context, "TempList.jsp",webPath, pageListPath);//����Controller �൱�ڽӿ�
//		CommonPageParser.WriterPage(context, "TempEdit.jsp",webPath, pageEditPath);//����Controller �൱�ڽӿ�

		
		/*************************�޸�xml�ļ�*****************************/
		WolfXmlUtil xml=new WolfXmlUtil();
		Map<String,String> attrMap=new HashMap<String, String>();
		try{
		   /**   ���뵽mybatis-config.xml �����ļ� */
//			attrMap.clear();
			attrMap.put("resource","com/hmmes/mybatis/"+className+"Mapper.xml");
		    xml.getAddNode(rootPath+"conf/mybatis-config.xml", "/configuration/mappers", "mapper", attrMap, "");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ��ȡ��Ŀ��·��
	 * @return
	 */
	public static String getRootPath(){
		String rootPath ="";
		try{
			 File file = new File(CommonPageParser.class.getResource("/").getFile());
			 rootPath = file.getParentFile().getParentFile().getParent()+"\\";
			 rootPath = java.net.URLDecoder.decode(rootPath,"utf-8");
			 System.out.println(rootPath);
			 return rootPath;
		}catch(Exception e){
			e.printStackTrace();
		}
		return rootPath;
	}
}
