package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.XsddBean;

import com.hmmes.mapper.XsddMapper;

@Service("xsddService")
public class XsddService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(XsddService.class);

	
	@Autowired
    private XsddMapper<T> mapper;

	public void addBean(XsddBean xsdd) throws Exception {
		super.add((T) xsdd);
		//saveXsdds(xsdd.getId(),xsdd.getJhbh(),xsdd.getXsddmxs());
	}

	public void updateBean(XsddBean xsdd) throws Exception {
		super.update((T)xsdd);
		//saveXsdds(xsdd.getId(),xsdd.getJhbh(),xsdd.getXsddmxs());
	}

	/**
	 * ��ѯ�������۶�����ͷ
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * ��ѯID���������۶�����ͷ
	 * @return
	 */
	public List<T> queryListById(Integer xsddid){
		return mapper.queryListById(xsddid);
	}
	/**
	 * ��ѯID���������۶���
	 * @return
	 */
	public List<T> queryListByJhbh(String jhbh){
		return mapper.queryListByJhbh(jhbh);
	}	
	

	public void deleteBean(Object[] ids) throws Exception {

		super.delete(ids);
		//ɾ��������ϵ
		//for(Object id : ids){
		//	xsddsService.deleteByBtid((Integer)id);
		//}
	}
	
	
	public XsddMapper<T> getMapper() {
		return mapper;
	}

}
