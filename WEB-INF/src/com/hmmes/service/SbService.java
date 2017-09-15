package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.SbBean;

import com.hmmes.mapper.SbMapper;

@Service("sbService")
public class SbService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SbService.class);

	
	@Autowired
    private SbMapper<T> mapper;




	/**
	 *��ѯȫ����ЧList
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

	/**
	 * ��ѯID������
	 * @return
	 */
	public List<T> queryListById(Integer id){
		return mapper.queryListById(id);
	}

	/**
	 * ����deksbmc(������豸����)
	 * @return
	 */
	public List<T> queryListByDeksbmc(String deksbmc){
		return mapper.queryListByDeksbmc(deksbmc);
	}
	/**
	 * ����sbmc(�豸����)
	 * @return
	 */
	public T queryBySbmc(String sbmc){
		return mapper.queryBySbmc(sbmc);
	}	
	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	
	public SbMapper<T> getMapper() {
		return mapper;
	}

}
