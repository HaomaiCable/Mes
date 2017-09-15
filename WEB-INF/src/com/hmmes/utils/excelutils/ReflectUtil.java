package com.hmmes.utils.excelutils;
 
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 
import com.hmmes.utils.excelutils.Employee;
import com.hmmes.utils.excelutils.StringUtil;
 
/**
 * ���乤����
 * 
 * @author liujiduo
 * 
 */
public class ReflectUtil {
 
    /**
     * �������ָ�����췽����������
     * 
     * @param clazz
     *            ��������
     * @param argTypes
     *            ��������
     * @param args
     *            �������
     * @return ���ع����Ķ���
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * 
     */
    public static <T> T invokeConstructor(Class<T> clazz, Class<?>[] argTypes,
            Object[] args) throws NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Constructor<T> constructor = clazz.getConstructor(argTypes);
        return constructor.newInstance(args);
    }
 
    /**
     * �������ָ���������Ե�getter����
     * 
     * @param <T>
     *            ����
     * @param target
     *            ָ������
     * @param fieldName
     *            ������
     * @return ���ص��ú��ֵ
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * 
     */
    public static <T> Object invokeGetter(T target, String fieldName)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // ���������Ϊxxx���򷽷���ΪgetXxx
        String methodName = "get" + StringUtil.firstCharUpperCase(fieldName);
        Method method = target.getClass().getMethod(methodName);
        return method.invoke(target);
    }
 
    /**
     * �������ָ���������Ե�setter����
     * 
     * @param <T>
     *            ����
     * @param target
     *            ָ������
     * @param fieldName
     *            ������
     * @param argTypes
     *            ��������
     * @param args
     *            �����б�
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * 
     */
    public static <T> void invokeSetter(T target, String fieldName, Object args)
            throws NoSuchFieldException, SecurityException,
            NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        // ���������Ϊxxx���򷽷���ΪsetXxx
        String methodName = "set" + StringUtil.firstCharUpperCase(fieldName);
        Class<?> clazz = target.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        Method method = clazz.getMethod(methodName, field.getType());
        method.invoke(target, args);
    }
 
    public static void main(String[] args) {
        try {
            Class<Employee> clazz = Employee.class;
            Employee user = ReflectUtil.invokeConstructor(clazz,
                    new Class<?>[]{long.class, String.class, int.class,
                            String.class, double.class}, new Object[]{1001,
                            "Linux", 30, "123", 20.55});
            System.out.println(user);
            ReflectUtil.invokeSetter(user, "salery", 2055);
            System.out.println(user);
            Object ret = ReflectUtil.invokeGetter(user, "salery");
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}
