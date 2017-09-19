package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface GzhsJtkqMapper<T> extends BaseMapper<T> {
	
	/**
	 *��ѯȫ����
	 * @return
	 */
	public List<T> queryAllList();

    /**
	 * ��ѯsbmc,kqrq,bc
	 * @return
	 */
    public T queryBySbmcAndKqrqAndBc(String sbmc,java.sql.Date kqrq,Integer bc);

}
