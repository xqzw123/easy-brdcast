package com.comtom.brdcast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.comtom.brdcast.dao.DeviceTagDao;
import com.comtom.brdcast.model.entity.DeviceTagEntity;
import com.comtom.brdcast.service.DeviceTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表业务层具体实现类
 *
 * @author zengwei
 * @date 2020-08-31 14:09:53
 */
@Service
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class DeviceTagServiceImpl extends ServiceImpl<DeviceTagDao,DeviceTagEntity> implements DeviceTagService {

}
