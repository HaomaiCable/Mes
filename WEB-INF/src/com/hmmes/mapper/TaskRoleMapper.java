package com.hmmes.mapper;
import java.util.List;
import java.util.Map;
import com.hmmes.model.TaskRoleModel;

/**
 * TaskRole Mapper
 * @author Administrator
 *
 */
public interface TaskRoleMapper<T> extends BaseMapper<T> {

		/**
	 * ������
	 * @return
	 */
	public List<T> queryByAll();
    public List<T> queryByGlzId(Integer roleId);
    public T queryByUserId(Integer userId);

    /**
	 * ��������鿴����
	 * @param taskroleId
	 * @param gysId
	 */	
	public void addTaskRoleRel(Map<String,Object> map);


	public void deleteTaskRoleRel(Integer roleId);


}
