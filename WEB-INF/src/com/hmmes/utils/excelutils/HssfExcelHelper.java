package com.hmmes.utils.excelutils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
 
//import com.hmmes.utils.DateUtil;
import com.hmmes.utils.excelutils.ReflectUtil;
import com.hmmes.utils.excelutils.StringUtil;
 
/**
 * ����POIʵ�ֵ�Excel������
 * 
 * @author liujiduo
 * 
 */
public class HssfExcelHelper extends ExcelHelper {
 
    private static HssfExcelHelper instance = null; // ��������
 
    private File file; // �����ļ�
 
    /**
     * ˽�л����췽��
     * 
     * @param file
     *            �ļ�����
     */
    private HssfExcelHelper(File file) {
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
    public static HssfExcelHelper getInstance(File file) {
        if (instance == null) {
            // ����������Ϊnullʱ����ͬ�������
            synchronized (HssfExcelHelper.class) {
                // �ٴ��жϵ��������Ƿ�Ϊnull����ֹ���̷߳���ʱ������ɶ���
                if (instance == null) {
                    instance = new HssfExcelHelper(file);
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
    public static HssfExcelHelper getInstance(String filePath) {
        return getInstance(new File(filePath));
    }
 
    @Override
    public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames,
            int sheetNo, boolean hasTitle) throws Exception {
        List<T> dataModels = new ArrayList<T>();
        // ��ȡexcel������
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = workbook.getSheetAt(sheetNo);
        int start = sheet.getFirstRowNum() + (hasTitle ? 1 : 0); // ����б�����ӵڶ��п�ʼ
        for (int i = start; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            // ����ʵ����ͨ���������setter����
            T target = clazz.newInstance();
            for (int j = 0; j < fieldNames.length; j++) {
                String fieldName = fieldNames[j];
                if (fieldName == null || UID.equals(fieldName)) {
                    continue; // ����serialVersionUID����
                }
                // ��ȡexcel��Ԫ�������
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                String content = cell.getStringCellValue();
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
                         //ReflectUtil.invokeSetter(target, fieldName,
                         //   DateUtil.parse(content));
                } else {
                    Field field = clazz.getDeclaredField(fieldName);
                    ReflectUtil.invokeSetter(target, fieldName,
                            parseValueWithType(content, field.getType()));
                }
            }
            dataModels.add(target);
        }
        return dataModels;
    }
 
    @Override
    public <T> void writeExcel(Class<T> clazz, List<T> dataModels,
            String[] fieldNames, String[] titles) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS"); 
        HSSFWorkbook workbook = null;
        // ����ļ��Ƿ���ڣ�����������޸��ļ������򴴽��ļ�
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new HSSFWorkbook(fis);
        } else {
            workbook = new HSSFWorkbook();
        }
        // ���ݵ�ǰ����������������Ӧ��ŵĹ�����
        //String sheetName = DateUtil.getFomartDate(new Date(), "yyyyMMddHHmmssSS");
		String sheetName = sdf.format(new Date());
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow headRow = sheet.createRow(0);
        // ��ӱ�����
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = headRow.createCell(i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(titles[i]);
            // ��������Ӵ�
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            cellStyle.setFont(font);
            // �����Զ�����
            cellStyle.setWrapText(true);
            cell.setCellStyle(cellStyle);
            // ���õ�Ԫ����
            sheet.setColumnWidth(i, titles[i].length() * 1000);
        }
        // ��ӱ������
        for (int i = 0; i < dataModels.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            // ���������б�
            for (int j = 0; j < fieldNames.length; j++) {
                // ͨ�������ȡ���Ե�ֵ��
                String fieldName = fieldNames[j];
                if (fieldName == null || UID.equals(fieldName)) {
                    continue; // ����serialVersionUID����
                }
                Object result = ReflectUtil.invokeGetter(dataModels.get(i),
                        fieldName);
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(StringUtil.toString(result));
                // �����������������и�ʽ������
                if (isDateType(clazz, fieldName)) {
                     sdf = new SimpleDateFormat("yyyy-MM-dd");
                     //label.setString(sdf.format((Date) result));
					 cell.setCellValue(sdf.format((Date) result));
                    //cell.setCellValue(DateUtil.format((Date) result));
                }
            }
        }
        // ������д��������
        FileOutputStream fos = new FileOutputStream(file);
        try {
            workbook.write(new FileOutputStream(file));
        } finally {
            if (fos != null) {
                fos.close(); // �����Ƿ����쳣�������ر��ļ������
            }
        }
    }
}
