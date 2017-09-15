package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface GsdeMapper<T> extends BaseMapper<T> {
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> queryByAll();

	/**	
	 * ��ѯID���е�
	 * @return
	 */
	public List<T> queryListById(Integer id);

    /**
	 * ����DekSbmc��������豸���ƣ�,gxxh,gxgg,gxdy,xglb
	 * @return
	 */
	T queryListByDeksbmcEtc(String deksbmc,String gxxh,String gxgg,String gxdy,String gxlb);

}
