package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.JtjhBean;

import com.hmmes.mapper.JtjhMapper;

@Service("jtjhService")
public class JtjhService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(JtjhService.class);

	
	@Autowired
    private JtjhMapper<T> mapper;

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}

	public void deleteById(Integer id) throws Exception{
		super.delete(id);
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
	 * ��ѯddID������
	 * @return
	 */
	public List<T> queryListByDdId(Integer ddid){
		return mapper.queryListByDdId(ddid);
	}
	/**
	 * ���ݼƻ���Ų�ѯ
	 * @return
	 */
	public List<T> queryListByJhbh(String jhbh){
		return mapper.queryListByJhbh(jhbh);
	}	
	/**
	 * ����sbmcdek(������豸����)��ѯ
	 * @return
	 */
	public List<T> queryListBySbmcdek(String sbmcdek){
		return mapper.queryListByJhbh(sbmcdek);
	}
	/**
	 * ����sbmc(�´�ƻ����豸����)��ѯ
	 * @return
	 */
	public List<T> queryListBySbmc(String sbmc){
		return mapper.queryListByJhbh(sbmc);
	}		
	public T queryByJhbhAndRow(String jhbh,Integer row){
		return mapper.queryByJhbhAndRow(jhbh,row);
	}		

	public JtjhMapper<T> getMapper() {
		return mapper;
	}

}
