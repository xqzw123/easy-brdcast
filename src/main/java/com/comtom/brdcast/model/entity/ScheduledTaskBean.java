package com.comtom.brdcast.model.entity;

//@Data
//@TableName(value = "scheduled_task", resultMap = "userMap")
//@ApiModel(value = "xx表", description = "xx表")
public class ScheduledTaskBean {
    /**
     * 任务key值 唯一
     */
    private String taskKey;
    /**
     * 任务描述
     */
    private String taskDesc;
    /**
     * 任务表达式
     */
    private String taskCron;

    /**
     * 程序初始化是否启动 1 是 0 否
     */
    private Integer initStartFlag;

    /**
     * 当前是否已启动
     */
    private boolean startFlag;

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public Integer getInitStartFlag() {
        return initStartFlag;
    }

    public void setInitStartFlag(Integer initStartFlag) {
        this.initStartFlag = initStartFlag;
    }

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }
}
