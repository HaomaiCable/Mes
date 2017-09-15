package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.TbxxBean;
import com.hmmes.model.TbxxModel;
import com.hmmes.mapper.TbxxMapper;

@Service("tbxxService")
public class TbxxService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(TbxxService.class);

	
	@Autowired
    private TbxxMapper<T> mapper;

	public void addBean(TbxxBean tbxx) throws Exception {
		super.add((T) tbxx);
		//saveTbxxs(tbxx.getId(),tbxx.getJhbh(),tbxx.getTbxxmxs());
	}

	public void updateBean(TbxxBean tbxx) throws Exception {
		super.update((T)tbxx);
		//saveTbxxs(tbxx.getId(),tbxx.getJhbh(),tbxx.getTbxxmxs());
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
	public List<T> queryListById(Integer tbxxid){
		return mapper.queryListById(tbxxid);
	}
	/**
	 * ��ѯID���������۶���
	 * @return
	 */
	public List<T> queryListByGgbh(String ggbh){
		return mapper.queryListByGgbh(ggbh);
	}	

	public List<T> queryListByGgbhAndGysId(String ggbh,Integer gysId){
        TbxxModel model = new TbxxModel();
		model.setGgbh(ggbh);
		model.setGysId(gysId);
		return mapper.queryListByGgbhAndGysId(model);
	}	
		

	public void deleteBean(Object[] ids) throws Exception {

		super.delete(ids);
		//ɾ��������ϵ
		//for(Object id : ids){
		//	tbxxsService.deleteByBtid((Integer)id);
		//}
	}
	
	
	public TbxxMapper<T> getMapper() {
		return mapper;
	}

}
