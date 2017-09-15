package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.mapper.SiteColumnMapper;

/**
 * 
 * <br>
 * <b>���ܣ�</b>SiteColumnService<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 9, 2011 <br>
 * <b>��Ȩ���У�<b>��Ȩ����(C) 2011��WWW.VOWO.COM<br>
 */
@Service("siteColumnService")
public class SiteColumnService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);
	
	/**
	 * ����վ��id��ѯ��Ŀ
	 * @param siteId
	 */
	public List<T> queryBySiteId(Integer siteId){
		return getMapper().queryBySiteId(siteId);
	}
	

	@Autowired
    private SiteColumnMapper<T> mapper;

		
	public SiteColumnMapper<T> getMapper() {
		return mapper;
	}

}
