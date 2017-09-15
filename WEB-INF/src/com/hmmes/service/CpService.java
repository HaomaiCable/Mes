package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CpBean;
import com.hmmes.bean.CpgxmxBean;

import com.hmmes.mapper.CpMapper;

@Service("cpService")
public class CpService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CpService.class);

	
	@Autowired
    private CpMapper<T> mapper;
	@Autowired
	private CpgxmxService<CpgxmxBean> cpgxmxService;



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
	public List<T> queryListById(Integer id){
		return mapper.queryListById(id);
	}

	

	public void deleteBean(Object[] ids) throws Exception {

		super.delete(ids);
		//ɾ��������ϵ
		for(Object cpid : ids){
			cpgxmxService.deleteByCpId((Integer)cpid);
		}
	}

	
	/**
	 *����xh��gg��ѯ��Ʒ
	 * @return
	 */
	public T queryByXhAndGg(String xh, String gg){
		return getMapper().queryByXhAndGg(xh,gg);
	}	


	public CpMapper<T> getMapper() {
		return mapper;
	}

}
