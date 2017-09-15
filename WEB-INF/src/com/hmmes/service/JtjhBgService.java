package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.JtjhBgBean;
import com.hmmes.mapper.JtjhBgMapper;

@Service("jtjhBgService")
public class JtjhBgService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(JtjhBgService.class);

	@Autowired
    private JtjhBgMapper<T> mapper;
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * ��ѯID��
	 * @return
	 */
	public List<T> queryListById(Integer jhid){
		return mapper.queryListById(jhid);
	}
	/**
	 * ����bh��
	 * @return
	 */
	public List<T> queryListByBh(String bh){
		return mapper.queryListByBh(bh);
	}	
	
	
	public JtjhBgMapper<T> getMapper() {
		return mapper;
	}

}
