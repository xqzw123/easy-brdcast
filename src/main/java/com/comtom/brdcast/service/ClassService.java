package com.comtom.brdcast.service;

import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.model.param.clazz.*;

import java.sql.SQLException;

/**
 * 班级业务层接口
 * 
 * @author zengwei
 * @date 2021/07/09
 */
public interface ClassService {

	/**
     * 保存(单条)
     *
     * @param classSaveParam
     * @return
     */
	ApiResult save(ClassSaveParam classSaveParam);

	/**
	 * 班级教师批量保存
	 *
	 * @param classTeacherSaveBatchParam
	 * @return
	 */
	ApiResult saveClassTeacherBatch(ClassTeacherSaveBatchParam classTeacherSaveBatchParam);

	/**
     * 查询详情(单条)
     *
     * @param classInfoParam
     * @return
     */
	ApiResult info(ClassInfoParam classInfoParam);

	/**
	 * 查询班级集合
	 *
	 * @param collectionParam
	 * @return
	 */
	ApiResult collection(ClassCollectionParam collectionParam);

	/**
     * 查询列表
     *
     * @param classListParam
     * @return
     */
	ApiResult list(ClassListParam classListParam) throws SQLException;

	/**
     * 更新(单条)
     *
     * @param classUpdateParam
     * @return
     */
	ApiResult update(ClassUpdateParam classUpdateParam);

	/**
     * 删除(单条)
     *
     * @param classDeleteParam
     * @return
     */
	ApiResult delete(ClassDeleteParam classDeleteParam);

	/**
	 * 批量删除
	 *
	 * @param deleteBatchParam
	 * @return
	 */
	ApiResult deleteBatch(ClassDeleteBatchParam deleteBatchParam);


}
