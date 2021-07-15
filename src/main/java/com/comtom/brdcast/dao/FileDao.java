package com.comtom.brdcast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.comtom.brdcast.model.entity.FileEntity;
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
public interface FileDao extends BaseMapper<FileEntity> {
	
}
