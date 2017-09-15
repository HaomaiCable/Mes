package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.SiteType;
import com.hmmes.exception.ServiceException;
import com.hmmes.mapper.SiteTypeMapper;
import com.hmmes.model.SiteTypeModel;

/**
 * 
 * <br>
 * <b>���ܣ�</b>SiteTypeService<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 9, 2011 <br>
 * <b>��Ȩ���У�<b>��Ȩ����(C) 2011��WWW.VOWO.COM<br>
 */
@Service("siteTypeService")
public class SiteTypeService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteTypeService.class);
	
	@Autowired
    private SiteTypeMapper<T> mapper;

	/**
	 * ���վ������
	 */
	public void add(T t) throws Exception{
		validation(t,0);
		getMapper().add(t);
	}
	
	
	/**
	 * �޸�վ������
	 */
	public void update(T t) throws Exception{
		validation(t,1);
		getMapper().update(t);
	}
	
	public void delete(Object[] ids) throws Exception{
		if(ids == null || ids.length < 1){
			return;
		}
		for(Object id : ids){
			getMapper().deleteSiteRel((Integer)id);
		}
		super.delete(ids);
	}
	
	
	public List<T> queryByAll() throws Exception{
		return getMapper().queryByAll();
	}
	
	
	public List<T> queryBySiteId(Integer siteId) throws Exception{
		return getMapper().queryBySiteId(siteId);
	}
	
	
	/**
	 * ��֤�Ƿ��ظ�
	 * @param t
	 * @param num
	 * @throws Exception
	 */
	private void validation(T t,int num) throws Exception{
		SiteType bean = (SiteType)t;
		//��֤name�Ƿ��ظ�
		SiteTypeModel model = new SiteTypeModel();
		model.setName(bean.getName());
		int count = getMapper().queryByCount(model);
		if(count > num ){
			 throw new ServiceException("name is can't be duplicate");
		}
		//��֤code�Ƿ��ظ�
		model.setName(null);
		model.setCode(bean.getCode());
		count = getMapper().queryByCount(model);
		if(count > num){
			 throw new ServiceException("code is can't be duplicate");
		}
	}
	
	public SiteTypeMapper<T> getMapper() {
		return mapper;
	}

}
