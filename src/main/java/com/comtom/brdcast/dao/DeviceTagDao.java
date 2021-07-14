package com.comtom.brdcast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.comtom.brdcast.model.entity.DeviceTagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户表
 * 
 * @author zengwei
 * @date 2020-08-31 14:09:53
 */
@Mapper
@Component
public interface DeviceTagDao extends BaseMapper<DeviceTagEntity>{
	List<String> getIdsByTags(List<String> tags);
}
