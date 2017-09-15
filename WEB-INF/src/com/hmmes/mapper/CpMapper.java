package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface CpMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * 查询ID的
	 * @return
	 */
	public List<T> queryListById(Integer id);

	/**
	 *根据xh，gg查询产品
	 * @return
	 */
	public T queryByXhAndGg(String xh, String gg);
	
	
}
