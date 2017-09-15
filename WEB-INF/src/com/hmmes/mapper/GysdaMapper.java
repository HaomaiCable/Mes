package com.hmmes.mapper;

import java.util.List;
import java.util.Map;

/**
 * Mapper
 * @author Administrator
 *
 */
public interface GysdaMapper<T> extends BaseMapper<T> {
	
	/**
	 * ������
	 * @return
	 */
	public List<T> queryByAll();

    /**
	 * ����ɹ����ʷ���
	 * @param cgflId
	 * @param gysId
	 */	
	public void addCgflRel(Map<String,Object> map);

    /**
	 * �����б�����
	 * @param zbwzId
	 * @param gysId
	 */	
	public void addZbwzRel(Map<String,Object> map);	
	
	/**
	 * ɾ������������
	 * @param cgflId
	 * @param gysId
	 */	
	public void deleteCgflRel(Integer gysId);
	public void deleteZbwzRel(Integer gysId);
	
}
