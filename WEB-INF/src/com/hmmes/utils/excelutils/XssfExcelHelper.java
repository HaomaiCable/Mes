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
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
//import com.hmmes.utils.DateUtil;
import com.hmmes.utils.excelutils.ReflectUtil;
import com.hmmes.utils.excelutils.StringUtil;
 
/**
 * ����POIʵ�ֵ�Excel������
 * 
 * @author liujiduo
 * 
 */
public class XssfExcelHelper extends ExcelHelper {
 
    private static XssfExcelHelper instance = null; // ��������
 
    private File file; // �����ļ�
 
    /**
     * ˽�л����췽��
     * 
     * @param file
     *            �ļ�����
     */
    private XssfExcelHelper(File file) {
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
    public static XssfExcelHelper getInstance(File file) {
        if (instance == null) {
            // ����������Ϊnullʱ����ͬ�������
            synchronized (XssfExcelHelper.class) {
                // �ٴ��жϵ��������Ƿ�Ϊnull����ֹ���̷߳���ʱ������ɶ���
                if (instance == null) {
                    instance = new XssfExcelHelper(file);
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
    public static XssfExcelHelper getInstance(String filePath) {
        return getInstance(new File(filePath));
    }
 
    @Override
    public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames,
            int sheetNo, boolean hasTitle) throws Exception {
        List<T> dataModels = new ArrayList<T>();
        // ��ȡexcel������
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);
        int start = sheet.getFirstRowNum() + (hasTitle ? 1 : 0); // ����б�����ӵڶ��п�ʼ
        for (int i = start; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
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
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                String content = getCellContent(cell);
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
        XSSFWorkbook workbook = null;
        // ����ļ��Ƿ���ڣ�����������޸��ļ������򴴽��ļ�
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } else {
            workbook = new XSSFWorkbook();
        }
        // ���ݵ�ǰ����������������Ӧ��ŵĹ�����
		String sheetName = sdf.format(new Date());
        //String sheetName = DateUtil.getFomartDate(new Date(), "yyyyMMddHHmmssSS");
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFRow headRow = sheet.createRow(0);
        // ��ӱ�����
        for (int i = 0; i < titles.length; i++) {
            XSSFCell cell = headRow.createCell(i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(titles[i]);
            // ��������Ӵ�
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
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
            T target = dataModels.get(i);
            XSSFRow row = sheet.createRow(i + 1);
            // ���������б�
            for (int j = 0; j < fieldNames.length; j++) {
                // ͨ�������ȡ���Ե�ֵ��
                String fieldName = fieldNames[j];
                if (fieldName == null || UID.equals(fieldName)) {
                    continue; // ����serialVersionUID����
                }
                Object result = ReflectUtil.invokeGetter(target, fieldName);
                XSSFCell cell = row.createCell(j);
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
 
    @Override
    protected <T> Object parseValueWithType(String value, Class<?> type) {
        // ����Excel2007��numeric����ֻ����double�ͣ����Զ�������Ϊ���͵����ԣ�Ҫ��ǰ��numeric�ַ�������ת��
        if (Byte.TYPE == type || Short.TYPE == type || Short.TYPE == type
                || Long.TYPE == type) {
            value = String.valueOf((long) Double.parseDouble(value));
        }
        return super.parseValueWithType(value, type);
    }
 
    /**
     * ��ȡ��Ԫ�������
     * 
     * @param cell
     *            ��Ԫ��
     * @return ���ص�Ԫ������
     */
    private String getCellContent(XSSFCell cell) {
        StringBuffer buffer = new StringBuffer();
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC : // ����
                buffer.append(cell.getNumericCellValue());
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN : // ����
                buffer.append(cell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_FORMULA : // ��ʽ
                buffer.append(cell.getCellFormula());
                break;
            case XSSFCell.CELL_TYPE_STRING : // �ַ���
                buffer.append(cell.getStringCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK : // ��ֵ
            case XSSFCell.CELL_TYPE_ERROR : // ����
            default :
                break;
        }
        return buffer.toString();
    }
}
