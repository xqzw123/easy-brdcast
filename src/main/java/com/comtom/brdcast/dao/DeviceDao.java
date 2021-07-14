package com.comtom.brdcast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.comtom.brdcast.model.entity.DeviceEntity;
import com.comtom.brdcast.model.param.device.DeviceSearchReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 用户表
 * 
 * @author zengwei
 * @date 2020-08-31 14:09:53
 */
@Mapper
@Component
public interface DeviceDao extends BaseMapper<DeviceEntity> {
	Page<DeviceEntity> pageQuery(Page<DeviceEntity> page, DeviceSearchReq searchReq);
}
