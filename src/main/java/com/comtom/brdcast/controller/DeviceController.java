package com.comtom.brdcast.controller;

import com.comtom.brdcast.common.api.ApiPageResponse;
import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.model.entity.DeviceEntity;
import com.comtom.brdcast.model.param.device.DeviceSaveReq;
import com.comtom.brdcast.model.param.device.DeviceSearchReq;
import com.comtom.brdcast.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表
 * 
 * @author zengwei
 * @date 2020-08-31 16:39:56
 */
@Slf4j
@RestController
@RequestMapping(value = "/safeRest/device")
@Api(value = "设备管理", tags = "设备管理")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

    /**
     * @param
     * @return
     */
    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "设备新增",  notes = "设备新增")
    public ResponseEntity<?> save(@Validated @RequestBody DeviceSaveReq deviceSaveReq) throws Exception{
        ApiResult apiResult = deviceService.save(deviceSaveReq);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询详情
     *
     * @param
     * @return
     */
    @GetMapping(value = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "设备分页查询",  notes = "设备分页查询")
    public ResponseEntity<?> page(@Validated DeviceSearchReq deviceSearchReq) throws Exception {
        ApiPageResponse<DeviceEntity> apiResult = deviceService.list(deviceSearchReq);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<ApiPageResponse>(apiResult, headers, HttpStatus.OK);
    }


    /**
     * 修改(单条)
     *
     * @param
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "设备修改",  notes = "设备修改")
    public ResponseEntity<?> update(@Validated @RequestBody DeviceEntity deviceEntity) throws Exception {
        ApiResult apiResult = deviceService.update(deviceEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 删除
     *
     * @param
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "设备删除",  notes = "设备删除")
    public ResponseEntity<?> delete(@Validated @RequestBody List<String> ids) throws Exception {
        ApiResult apiResult = deviceService.deleteBatch(ids);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }





}
