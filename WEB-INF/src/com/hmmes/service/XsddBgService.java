package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.XsddBgBean;
import com.hmmes.mapper.XsddBgMapper;

@Service("xsddBgService")
public class XsddBgService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(XsddBgService.class);

	@Autowired
    private XsddBgMapper<T> mapper;
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * ��ѯ���۶���ID��
	 * @return
	 */
	public List<T> queryListById(Integer ddid){
		return mapper.queryListById(ddid);
	}
	/**
	 * ����bh��
	 * @return
	 */
	public List<T> queryListByBh(String bh){
		return mapper.queryListByBh(bh);
	}	
	
	
	public XsddBgMapper<T> getMapper() {
		return mapper;
	}

}
