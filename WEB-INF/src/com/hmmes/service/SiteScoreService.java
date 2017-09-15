package com.hmmes.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.SiteScore;
import com.hmmes.bean.SiteScore.ScoreType;
import com.hmmes.mapper.SiteScoreMapper;

/**
 * 
 * <br>
 * <b>���ܣ�</b>SiteScoreService<br>
 * <b>���ߣ�</b>�����<br>
 * <b>���ڣ�</b> Dec 9, 2011 <br>
 * <b>��Ȩ���У�<b>��Ȩ����(C) 2011��WWW.VOWO.COM<br>
 */
@Service("siteScoreService")
public class SiteScoreService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteScoreService.class);
	

	

	@Autowired
    private SiteScoreMapper<T> mapper;

	public void saveScore(Integer siteId,Integer scoreType){
		SiteScore score = (SiteScore)getMapper().queryById(siteId);
		if(score == null){
			score = new SiteScore(siteId,0,0,0,0,0,0);
			getMapper().add((T)score);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			String value = ScoreType.get(scoreType).value;
			map.put("siteId", siteId);
			map.put("column", value);
			getMapper().saveScore(map);
		}
		
	}
		
	public SiteScoreMapper<T> getMapper() {
		return mapper;
	}

}
