package com.hmmes.utils.excelutils;
 
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
 
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
 
//import com.hmmes.utils.DateUtil;
import com.hmmes.utils.excelutils.ReflectUtil;
import com.hmmes.utils.excelutils.StringUtil;
 
/**
 * ����JXLʵ�ֵ�Excel������
 * 
 * @author liujiduo
 * 
 */
public class JxlExcelHelper extends ExcelHelper {
 
    private static JxlExcelHelper instance = null; // ��������
 
    private File file; // �����ļ�
 
    /**
     * ˽�л����췽��
     * 
     * @param file
     *            �ļ�����
     */
    private JxlExcelHelper(File file) {
        super();
        this.file = file;
    }
 
    public File getFile() {
        return file;
    }
 
    public void setFile(File file) {
        this.file = file;
    }
 
    /**
     * ��ȡ�������󲢽��г�ʼ��
     * 
     * @param file
     *            �ļ�����
     * @return ���س�ʼ����ĵ�������
     */
    public static JxlExcelHelper getInstance(File file) {
        if (instance == null) {
            // ����������Ϊnullʱ����ͬ�������
            synchronized (JxlExcelHelper.class) {
                // �ٴ��жϵ��������Ƿ�Ϊnull����ֹ���̷߳���ʱ������ɶ���
                if (instance == null) {
                    instance = new JxlExcelHelper(file);
                }
            }
        } else {
            // ����������ļ�����ͬ���������ļ�����
            if (!file.equals(instance.getFile())) {
                instance.setFile(file);
            }
        }
        return instance;
    }
 
    /**
     * ��ȡ�������󲢽��г�ʼ��
     * 
     * @param filePath
     *            �ļ�·��
     * @return ���س�ʼ����ĵ�������
     */
    public static JxlExcelHelper getInstance(String filePath) {
        return getInstance(new File(filePath));
    }
 
    @Override
    public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames,
            int sheetNo, boolean hasTitle) throws Exception {
        List<T> dataModels = new ArrayList<T>();
        // ��ȡexcel������
        Workbook workbook = Workbook.getWorkbook(file);
        try {
            Sheet sheet = workbook.getSheet(sheetNo);
            int start = hasTitle ? 1 : 0; // ����б�����ӵڶ��п�ʼ
            for (int i = start; i < sheet.getRows(); i++) {
                // ����ʵ����ͨ���������setter����
                T target = clazz.newInstance();
                for (int j = 0; j < fieldNames.length; j++) {
                    String fieldName = fieldNames[j];
                    if (fieldName == null || UID.equals(fieldName)) {
                        continue; // ����serialVersionUID����
                    }
                    // ��ȡexcel��Ԫ�������
                    Cell cell = sheet.getCell(j, i);
                    if (cell == null) {
                        continue;
                    }
                    String content = cell.getContents();
                    // �����������������������ת�������ڶ���
                    if (isDateType(clazz, fieldName)) {
                        // �����������������������ת�������ڶ���
		                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
   					     content=content.replace("/","-");
						 Date ddate=null;
				         try {
		                     ddate = sdf.parse(content);
                         } catch (ParseException e) {
                              //e.printStackTrace();
                         }

                         ReflectUtil.invokeSetter(target, fieldName,
                                new java.sql.Date(ddate.getTime()));
                    } else {
                        Field field = clazz.getDeclaredField(fieldName);
                        ReflectUtil.invokeSetter(target, fieldName,
                                parseValueWithType(content, field.getType()));
                    }
                }
                dataModels.add(target);
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return dataModels;
    }
 
    @Override
    public <T> void writeExcel(Class<T> clazz, List<T> dataModels,
            String[] fieldNames, String[] titles) throws Exception {
        WritableWorkbook workbook = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS"); 
        try {
            // ����ļ��Ƿ���ڣ�����������޸��ļ������򴴽��ļ�
            if (file.exists()) {
                Workbook book = Workbook.getWorkbook(file);
                workbook = Workbook.createWorkbook(file, book);
            } else {
                workbook = Workbook.createWorkbook(file);
            }
            // ���ݵ�ǰ����������������Ӧ��ŵĹ�����
            int sheetNo = workbook.getNumberOfSheets() + 1;
            String sheetName = sdf.format(new Date());
            WritableSheet sheet = workbook.createSheet(sheetName, sheetNo);
            // ��ӱ�����
            for (int i = 0; i < titles.length; i++) {
                // ��������Ӵ�
                WritableFont font = new WritableFont(WritableFont.ARIAL, 10,
                        WritableFont.BOLD);
                WritableCellFormat format = new WritableCellFormat(font);
                // �����Զ�����
                format.setWrap(true);
                Label label = new Label(i, 0, titles[i], format);
                sheet.addCell(label);
                // ���õ�Ԫ����
                sheet.setColumnView(i, titles[i].length() + 10);
            }
            // ��ӱ������
            for (int i = 0; i < dataModels.size(); i++) {
                // ���������б�
                for (int j = 0; j < fieldNames.length; j++) {
                    T target = dataModels.get(i);
                    // ͨ�������ȡ���Ե�ֵ��
                    String fieldName = fieldNames[j];
                    if (fieldName == null || UID.equals(fieldName)) {
                        continue; // ����serialVersionUID����
                    }
                    Object result = ReflectUtil.invokeGetter(target, fieldName);
                    Label label = new Label(j, i + 1,
                            StringUtil.toString(result));
                    // �����������������и�ʽ������
//System.out.println("����ɽfieldName=��"+fieldName);
//System.out.println("����ɽisDateType=��"+isDateType(clazz, fieldName));
                   // Boolean isdate=isDateType(clazz, fieldName);
                    if (isDateType(clazz, fieldName)) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd
                        label.setString(sdf.format((Date) result));
                    }
                    sheet.addCell(label);
                }
            }
        } finally {
            if (workbook != null) {
                workbook.write();
                workbook.close();
            }
        }
    }
 
}
