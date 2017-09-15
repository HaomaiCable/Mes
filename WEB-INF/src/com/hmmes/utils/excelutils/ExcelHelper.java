package com.hmmes.utils.excelutils;
 
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
//import java.sql.Date;
/**
 * Excel���߳�����
 * 
 * @author liujiduo
 * 
 * @param <T>
 *            ������������
 */
public abstract class ExcelHelper {
    /**
     * �������л��汾������
     */
    public static final String UID = "serialVersionUID";
 
    /**
     * ��ָ��excel�ļ��е�����ת���������б�
     * 
     * @param clazz
     *            ��������
     * @param sheetNo
     *            ��������
     * @param hasTitle
     *            �Ƿ���б���
     * @return ����ת����������б�
     * @throws Exception
     */
    public <T> List<T> readExcel(Class<T> clazz, int sheetNo, boolean hasTitle)
            throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            fieldNames[i] = fieldName;
        }
        return readExcel(clazz, fieldNames, sheetNo, hasTitle);
    }
 
    /**
     * ��ָ��excel�ļ��е�����ת���������б�
     * 
     * @param clazz
     *            ��������
     * @param fieldNames
     *            �����б�
     * @param hasTitle
     *            �Ƿ���б���
     * @return ����ת����������б�
     * @throws Exception
     */
    public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames,
            boolean hasTitle) throws Exception {
        return readExcel(clazz, fieldNames, 0, hasTitle);
    }
 
    /**
     * ���󷽷�����ָ��excel�ļ��е�����ת���������б�������ʵ��
     * 
     * @param clazz
     *            ��������
     * @param fieldNames
     *            �����б�
     * @param sheetNo
     *            ��������
     * @param hasTitle
     *            �Ƿ���б���
     * @return ����ת����������б�
     * @throws Exception
     */
    public abstract <T> List<T> readExcel(Class<T> clazz, String[] fieldNames,
            int sheetNo, boolean hasTitle) throws Exception;
 
    /**
     * д�����ݵ�ָ��excel�ļ���
     * 
     * @param clazz
     *            ��������
     * @param dataModels
     *            �����б�
     * @throws Exception
     */
    public <T> void writeExcel(Class<T> clazz, List<T> dataModels)
            throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            fieldNames[i] = fieldName;
        }
        writeExcel(clazz, dataModels, fieldNames, fieldNames);
    }
 
    /**
     * д�����ݵ�ָ��excel�ļ���
     * 
     * @param clazz
     *            ��������
     * @param dataModels
     *            �����б�
     * @param fieldNames
     *            �����б�
     * @throws Exception
     */
    public <T> void writeExcel(Class<T> clazz, List<T> dataModels,
            String[] fieldNames) throws Exception {
        writeExcel(clazz, dataModels, fieldNames, fieldNames);
    }
 
    /**
     * ���󷽷���д�����ݵ�ָ��excel�ļ��У�������ʵ��
     * 
     * @param clazz
     *            ��������
     * @param dataModels
     *            �����б�
     * @param fieldNames
     *            �����б�
     * @param titles
     *            �����б�
     * @throws Exception
     */
    public abstract <T> void writeExcel(Class<T> clazz, List<T> dataModels,
            String[] fieldNames, String[] titles) throws Exception;
 
    /**
     * �ж������Ƿ�Ϊ��������
     * 
     * @param clazz
     *            ��������
     * @param fieldName
     *            ������
     * @return ���Ϊ�������ͷ���true�����򷵻�false
     */
    protected <T> boolean isDateType(Class<T> clazz, String fieldName) {
        boolean flag = false;
//		System.out.println("����ɽfieldName=��"+fieldName);
        try {
            Field field = clazz.getDeclaredField(fieldName);
            Object typeObj = field.getType().newInstance();
//System.out.println("����ɽfield=��"+field.getName()+",type="+field.getType().getName());
			if (typeObj instanceof Date)
			{
				flag=true;
			}
			else if (typeObj instanceof java.sql.Date)
			{
				flag=true;
			}
			else if (typeObj instanceof java.sql.Timestamp)
			{
				flag=true;
			}
			else 
			{
				flag=false;
			}
            //flag = typeObj instanceof Date;
        } catch (Exception e) {
            // ���쳣�̵�ֱ�ӷ���false
		//System.out.println("����ɽException e=��"+e);
        }
        return flag;
    }
 
    /**
     * �������ͽ�ָ������ת���ɶ�Ӧ������
     * 
     * @param value
     *            ָ������
     * @param type
     *            ָ������
     * @return ��������ת����Ķ���
     */
    protected <T> Object parseValueWithType(String value, Class<?> type) {
        Object result = null;
        try { // �������Ե����ͽ�����ת���ɶ�Ӧ������
            if (Boolean.TYPE == type) {
                result = Boolean.parseBoolean(value);
            } else if (Byte.TYPE == type) {
                result = Byte.parseByte(value);
            } else if (Short.TYPE == type) {
                result = Short.parseShort(value);
            } else if (Integer.TYPE == type) {
                result = Integer.parseInt(value);
            } else if (Long.TYPE == type) {
                result = Long.parseLong(value);
            } else if (Float.TYPE == type) {
                result = Float.parseFloat(value);
            } else if (Double.TYPE == type) {
                result = Double.parseDouble(value);
            } else {
                result = (Object) value;
            }
        } catch (Exception e) {
            // ���쳣�̵�ֱ�ӷ���null
        }
        return result;
    }
 
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1000, "Jones", 40, "Manager", 2975));
        employees.add(new Employee(1001, "Blake", 40, "Manager", 2850));
        employees.add(new Employee(1002, "Clark", 40, "Manager", 2450));
        employees.add(new Employee(1003, "Scott", 30, "Analyst", 3000));
        employees.add(new Employee(1004, "King", 50, "President", 5000));
        String[] titles = new String[]{"����", "����", "����", "ְ��", "н�ʣ���Ԫ��", "��ְʱ��"};
        String[] fieldNames = new String[]{"id", "name", "age", "job",
                "salery", "addtime"};
        try {
            File file1 = new File("E:\\JXL2003.xls");
            ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
            eh1.writeExcel(Employee.class, employees);
            eh1.writeExcel(Employee.class, employees, fieldNames, titles);
            List<Employee> list1 = eh1.readExcel(Employee.class, fieldNames,
                    true);
            System.out.println("-----------------JXL2003.xls-----------------");
            for (Employee user : list1) {
                System.out.println(user);
            }
            File file2 = new File("E:\\POI2003.xls");
            ExcelHelper eh2 = HssfExcelHelper.getInstance(file2);
            eh2.writeExcel(Employee.class, employees);
            eh2.writeExcel(Employee.class, employees, fieldNames, titles);
            List<Employee> list2 = eh2.readExcel(Employee.class, fieldNames,
                    true);
            System.out.println("-----------------POI2003.xls-----------------");
            for (Employee user : list2) {
                System.out.println(user);
            }
            File file3 = new File("E:\\POI2007.xlsx");
            ExcelHelper eh3 = XssfExcelHelper.getInstance(file3);
            eh3.writeExcel(Employee.class, employees);
            eh3.writeExcel(Employee.class, employees, fieldNames, titles);
            List<Employee> list3 = eh3.readExcel(Employee.class, fieldNames,
                    true);
            System.out.println("-----------------POI2007.xls-----------------");
            for (Employee user : list3) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}
