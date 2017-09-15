package com.hmmes.mapper;

import java.util.List;
import com.hmmes.model.TbxxModel;

/**
 *  Mapper
 * @author Administrator
 *
 */
public interface TbxxMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ�������۶����б�
	 * @return
	 */
	public List<T> queryByAll();	



	/**
	 * ��ѯID���������۶�����ͷ
	 * @return
	 */

	public List<T> queryListById(Integer zbggid);
		
	/**
	 * @param jhbh 
	 * @return
	 */
	public List<T> queryListByGgbh(String ggbh);

	public List<T> queryListByGgbhAndGysId(TbxxModel model);	
	
	
}
