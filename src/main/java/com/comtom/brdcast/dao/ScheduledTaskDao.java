package com.comtom.brdcast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.comtom.brdcast.model.entity.ScheduledTaskBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ScheduledTaskDao extends BaseMapper<ScheduledTaskBean> {
    /**
     * 根据key 获取 任务信息
     */
    @Select("select task_key as taskKey,task_desc as taskDesc,task_cron as taskCron,init_start_flag as initStartFlag  from scheduled_task where task_key = '${taskKey}' ")
    ScheduledTaskBean getByKey(@Param("taskKey") String taskKey);

    /**
     * 获取程序初始化需要自启的任务信息
     */
    @Select("select task_key as taskKey,task_desc as taskDesc,task_cron as taskCron,init_start_flag as initStartFlag from scheduled_task where init_start_flag=1 ")
    List<ScheduledTaskBean> getAllNeedStartTask();

    /**
     * 获取所有任务
     */
    @Select("select task_key as taskKey,task_desc as taskDesc,task_cron as taskCron,init_start_flag as initStartFlag  from scheduled_task ")
    List<ScheduledTaskBean> getAllTask();
}
