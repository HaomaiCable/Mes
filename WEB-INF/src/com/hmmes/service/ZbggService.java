package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.ZbggBean;

import com.hmmes.mapper.ZbggMapper;

@Service("zbggService")
public class ZbggService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ZbggService.class);

	
	@Autowired
    private ZbggMapper<T> mapper;

	public void addBean(ZbggBean zbgg) throws Exception {
		super.add((T) zbgg);
		//saveZbggs(zbgg.getId(),zbgg.getJhbh(),zbgg.getZbggmxs());
	}

	public void updateBean(ZbggBean zbgg) throws Exception {
		super.update((T)zbgg);
		//saveZbggs(zbgg.getId(),zbgg.getJhbh(),zbgg.getZbggmxs());
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
	public List<T> queryListById(Integer zbggid){
		return mapper.queryListById(zbggid);
	}
	/**
	 * ��ѯID���������۶���
	 * @return
	 */
	public List<T> queryListByGgbh(String ggbh){
		return mapper.queryListByGgbh(ggbh);
	}	
	

	public void deleteBean(Object[] ids) throws Exception {

		super.delete(ids);
		//ɾ��������ϵ
		//for(Object id : ids){
		//	zbggsService.deleteByBtid((Integer)id);
		//}
	}
	
	
	public ZbggMapper<T> getMapper() {
		return mapper;
	}

}
