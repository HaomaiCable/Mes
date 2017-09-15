package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.ClcpBean;
import com.hmmes.bean.ClcpCljzBean;

import com.hmmes.mapper.ClcpMapper;

@Service("clcpService")
public class ClcpService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ClcpService.class);

	
	@Autowired
    private ClcpMapper<T> mapper;
	@Autowired
	private ClcpCljzService<ClcpCljzBean> clcpCljzService;



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
			clcpCljzService.deleteByCpId((Integer)cpid);
		}
	}
	
	/**
	 *����xh��gg��ѯ��Ʒ
	 * @return
	 */
	public T queryByXhAndGgAndDy(String xh, String gg, String dy){
		return getMapper().queryByXhAndGgAndDy(xh,gg,dy);
	}	


	public ClcpMapper<T> getMapper() {
		return mapper;
	}

}
