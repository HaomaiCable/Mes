package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.GsdeBean;

import com.hmmes.mapper.GsdeMapper;

@Service("gsdeService")
public class GsdeService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(GsdeService.class);

	
	@Autowired
    private GsdeMapper<T> mapper;


	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}


	/**
	 * ��ѯID������
	 * @return
	 */
	public List<T> queryListById(Integer id){
		return mapper.queryListById(id);
	}

	/**
	 * ����DekSbmc��������豸���ƣ�,gxxh,gxgg,gxdy,xglb
	 * @return
	 */
	public T queryListByDeksbmcEtc(String deksbmc,String gxxh,String gxgg,String gxdy,String gxlb){
		return mapper.queryListByDeksbmcEtc(deksbmc,gxxh,gxgg,gxdy,gxlb);
	}

	
	public GsdeMapper<T> getMapper() {
		return mapper;
	}

}
