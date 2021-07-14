package com.comtom.brdcast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.model.entity.DeviceEntity;
import com.comtom.brdcast.model.param.device.DeviceSaveReq;
import com.comtom.brdcast.model.param.device.DeviceSearchReq;

import java.util.List;

/**
 * 用户表业务层接口
 * 
 * @author zengwei
 * @date 2020-08-31 14:09:53
 */
public interface DeviceService extends IService<DeviceEntity> {

	/**
     * 保存
     *
     * @param deviceSaveReq
     * @return
     * @throws Exception
     */
	ApiResult save(DeviceSaveReq deviceSaveReq) throws Exception;

	/**
     * 查询详情(单条)
     *
     * @param deviceSearchReq
     * @return
     * @throws Exception
     */
	ApiResult info(DeviceSearchReq deviceSearchReq) throws Exception;

	/**
     * 查询列表
     *
     * @param deviceSearchReq
     * @return
     * @throws Exception
     */
	ApiResult list(DeviceSearchReq deviceSearchReq) throws Exception;

	/**
     * 更新(单条)
     *
     * @param deviceEntity
     * @return
     * @throws Exception
     */
	ApiResult update(DeviceEntity deviceEntity) throws Exception;

	/**
     * 删除(单条)
     *
     * @param id
     * @return
     * @throws Exception
     */
	ApiResult delete(String id) throws Exception;

	/**
     * 批量删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
	ApiResult deleteBatch(List<String> ids) throws Exception;


}
