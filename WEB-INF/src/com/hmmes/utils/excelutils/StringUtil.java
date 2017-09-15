package com.hmmes.utils.excelutils;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class StringUtil {
 
    // Delim style
    public static final String DELIM_DEFAULT = ".";
 
    private StringUtil() {
        // Cannot be instantiated
    }
 
    /**
     * ��ָ������ת�����ַ���
     * 
     * @param obj
     *            ָ������
     * @return ת������ַ���
     */
    public static String toString(Object obj) {
        StringBuffer buffer = new StringBuffer();
        if (obj != null) {
            buffer.append(obj);
        }
        return buffer.toString();
    }
 
    /**
     * �ж�ָ���ַ����Ƿ����null����ַ���
     * 
     * @param str
     *            ָ���ַ���
     * @return �������null����ַ����򷵻�true�����򷵻�false
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }
 
    /**
     * �ж�ָ���ַ����Ƿ񲻵���null�Ϳ��ַ���
     * 
     * @param str
     *            ָ���ַ���
     * @return ���������null�Ϳ��ַ����򷵻�true�����򷵻�false
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
 
    /**
     * ����Ĭ�Ϸָ�����ȡ�ַ���ǰ׺
     * 
     * @param str
     *            ָ���ַ���
     * @return ����ǰ׺�ַ���
     */
    public static String getPrefix(String str) {
        return getPrefix(str, DELIM_DEFAULT);
    }
 
    /**
     * ����ָ���ָ�����ȡ�ַ���ǰ׺
     * 
     * @param str
     *            ָ���ַ���
     * @param delim
     *            ָ���ָ���
     * @return �����ַ���ǰ׺
     */
    public static String getPrefix(String str, String delim) {
        String prefix = "";
        if (isNotBlank(str) && isNotBlank(delim)) {
            int pos = str.indexOf(delim);
            if (pos > 0) {
                prefix = str.substring(0, pos);
            }
        }
        return prefix;
    }
 
    /**
     * ����Ĭ�Ϸָ�����ȡ�ַ�����׺
     * 
     * @param str
     *            ָ���ַ���
     * @return �����ַ�����׺
     */
    public static String getSuffix(String str) {
        return getSuffix(str, DELIM_DEFAULT);
    }
 
    /**
     * ����ָ���ָ�����ȡ�ַ�����׺
     * 
     * @param str
     *            ָ���ַ���
     * @param delim
     *            ָ���ָ���
     * @return �����ַ�����׺
     */
    public static String getSuffix(String str, String delim) {
        String suffix = "";
        if (isNotBlank(str) && isNotBlank(delim)) {
            int pos = str.lastIndexOf(delim);
            if (pos > 0) {
                suffix = str.substring(pos + 1);
            }
        }
        return suffix;
    }
 
    /**
     * ����ָ���ַ������ظ������������ַ���
     * 
     * @param str
     *            ָ���ַ���
     * @param repeatCount
     *            �ظ�����
     * @return �������ɵ����ַ���
     */
    public static String newString(String str, int repeatCount) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < repeatCount; i++) {
            buf.append(str);
        }
        return buf.toString();
    }
 
    /**
     * �����ַ���ָ��λ�õ��ַ�
     * 
     * @param str
     *            ָ���ַ���
     * @param index
     *            ��ʼλ��
     * @param length
     *            �ַ�����
     * @return ���������ַ�����ַ���
     */
    public static String hideChars(String str, int index, int length) {
        return hideChars(str, index, length, true);
    }
 
    /**
     * �����ַ���ָ��λ�õ��ַ�
     * 
     * @param str
     *            ָ���ַ���
     * @param start
     *            ��ʼλ��
     * @param end
     *            ����λ��
     * @param confusion
     *            �Ƿ�������ص��ַ�����
     * @return ���������ַ�����ַ���
     */
    public static String hideChars(String str, int start, int end,
            boolean confusion) {
        StringBuffer buf = new StringBuffer();
        if (isNotBlank(str)) {
            int startIndex = Math.min(start, end);
            int endIndex = Math.max(start, end);
            // �����ʼλ�ó���������Χ��Ĭ����Ϊ0
            if (startIndex < 0 || startIndex > str.length()) {
                startIndex = 0;
            }
            // �������λ�ó���������Χ��Ĭ����Ϊ�ַ�������
            if (endIndex < 0 || endIndex > str.length()) {
                endIndex = str.length();
            }
            String temp = newString("*", confusion ? 4 : endIndex - startIndex);
            buf.append(str).replace(startIndex, endIndex, temp);
 
        }
        return buf.toString();
    }
 
    /**
     * ��ָ���ַ���ת���ɴ�д
     * 
     * @param str
     *            ָ���ַ���
     * @return ����ת����Ĵ�д�ַ���
     */
    public static String toLowerCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        for (int i = 0; i < buffer.length(); i++) {
            char c = buffer.charAt(i);
            buffer.setCharAt(i, Character.toLowerCase(c));
        }
        return buffer.toString();
    }
 
    /**
     * ��ָ���ַ���ת���ɴ�д
     * 
     * @param str
     *            ָ���ַ���
     * @return ����ת����Ĵ�д�ַ���
     */
    public static String toUpperCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        for (int i = 0; i < buffer.length(); i++) {
            char c = buffer.charAt(i);
            buffer.setCharAt(i, Character.toUpperCase(c));
        }
        return buffer.toString();
    }
 
    /**
     * ��ָ���ַ���ת�����շ�������ʽ
     * 
     * @param str
     *            ָ���ַ���
     * @return �����շ�������ʽ
     */
    public static String toCalmelCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            // ������ĸת����Сд
            char c = buffer.charAt(0);
            buffer.setCharAt(0, Character.toLowerCase(c));
            Pattern p = Pattern.compile("_\\w");
            Matcher m = p.matcher(buffer.toString());
            while (m.find()) {
                String temp = m.group(); // ƥ����ַ���
                int index = buffer.indexOf(temp); // ƥ���λ��
                // ȥ��ƥ���ַ����е��»��ߣ�����ʣ���ַ�ת���ɴ�д
                buffer.replace(index, index + temp.length(),
                        temp.replace("_", "").toUpperCase());
            }
        }
        return buffer.toString();
    }
 
    /**
     * ��ָ���ַ���ת����������������ʽ
     * 
     * @param str
     *            ָ���ַ���
     * @return ת�����������������ʽ
     */
    public static String toHungarianCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            Pattern p = Pattern.compile("[A-Z]");
            Matcher m = p.matcher(buffer.toString());
            while (m.find()) {
                String temp = m.group(); // ƥ����ַ���
                int index = buffer.indexOf(temp); // ƥ���λ��
                // ��ƥ����ַ���ǰ����»��ߣ����������ַ�ת���ɴ�д
                buffer.replace(index, index + temp.length(), (index > 0
                        ? "_"
                        : "") + temp.toLowerCase());
            }
        }
        return buffer.toString();
    }
 
    /**
     * ��ָ���ַ�������ĸת���ɴ�д��ĸ
     * 
     * @param str
     *            ָ���ַ���
     * @return ��������ĸ��д���ַ���
     */
    public static String firstCharUpperCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            char c = buffer.charAt(0);
            buffer.setCharAt(0, Character.toUpperCase(c));
        }
        return buffer.toString();
    }
 
    /**
     * ��ָ������ת�����ַ���
     * 
     * @param objs
     *            ָ������
     * @return ����ת������ַ���
     */
    public static String array2String(Object[] objs) {
        StringBuffer buffer = new StringBuffer();
        if (objs != null) {
            for (int i = 0; i < objs.length; i++) {
                buffer.append(objs[i]).append(",");
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }
 
    public static void main(String[] args) {
        String str = "log.text.txt";
        System.out.println(getPrefix(str));
        System.out.println(getSuffix(str));
        System.out.println(hideChars(str, 2, str.length() - 1));
        System.out.println(toString(null));
        System.out.println(toCalmelCase("rate_limit_exceeded"));
        System.out.println(toHungarianCase("rateLimitExceeded"));
        System.out.println(firstCharUpperCase(str));
        System.out.println(new StringBuffer().append(""));
        System.out.println(array2String(new String[]{"a", "b", "c"}));
    }
 
}
