package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.JtBean;
import com.hmmes.mapper.JtMapper;


@Service("JtService")
public class JtService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(JtService.class);
	
	
	/**
	 *��ѯȫ����Ч�Ļ�̨
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

	/**
	 * ����jtmc(��̨����)
	 * @return
	 */
	public T queryByJtmc(String jtmc){
		return mapper.queryByJtmc(jtmc);
	}			
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}

	@Autowired
    private JtMapper<T> mapper;

		
	public JtMapper<T> getMapper() {
		return mapper;
	}

}
