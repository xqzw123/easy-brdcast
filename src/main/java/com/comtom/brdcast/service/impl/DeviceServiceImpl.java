package com.comtom.brdcast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.comtom.brdcast.common.api.ApiPageResponse;
import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.common.natives.BrdNativeUtil;
import com.comtom.brdcast.common.natives.entity.NativeDevInitReq;
import com.comtom.brdcast.common.natives.entity.NativeResp;
import com.comtom.brdcast.dao.DeviceDao;
import com.comtom.brdcast.dao.DeviceTagDao;
import com.comtom.brdcast.dao.TagDao;
import com.comtom.brdcast.model.entity.DeviceEntity;
import com.comtom.brdcast.model.entity.DeviceTagEntity;
import com.comtom.brdcast.model.param.device.DeviceSaveReq;
import com.comtom.brdcast.model.param.device.DeviceSearchReq;
import com.comtom.brdcast.service.DeviceService;
import com.comtom.brdcast.service.DeviceTagService;
import com.sun.deploy.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.net.util.IPAddressUtil;

import java.util.*;

/**
 * 用户表业务层具体实现类
 *
 * @author zengwei
 * @date 2020-08-31 14:09:53
 */
@Service
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class DeviceServiceImpl extends ServiceImpl<DeviceDao,DeviceEntity> implements DeviceService {

	private static int  NAME_SUFFIX = 01;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private TagDao tagDao;

	@Autowired
	private DeviceTagDao deviceTagDao;

	@Autowired
	private DeviceTagService deviceTagService;


	/**
	 * 保存
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult save(DeviceSaveReq deviceSaveReq) throws Exception {

		List<String> sequences = deviceSaveReq.getSequences();
		String name = deviceSaveReq.getName();
		int volume = deviceSaveReq.getVolume();
		List<String> tags = deviceSaveReq.getTags();

		Map<String,List<NativeDevInitReq>> rmap = new HashMap<>();
		List<NativeDevInitReq> remoteList = new ArrayList<>();
		List<DeviceEntity> saveLists = new ArrayList<>();
		List<DeviceTagEntity> saveDTLists = new ArrayList<>();
		for(String sequence:sequences){
			if(IPAddressUtil.isIPv4LiteralAddress(sequence)){
				DeviceEntity entity = new DeviceEntity();
				NativeDevInitReq nativeReq = new NativeDevInitReq();

				// 下发数据
				nativeReq.setDevId(sequence);
				nativeReq.setVol(volume);
				//入库数据
				entity.setSequence(sequence);
				entity.setVolume(volume);
				for(String tagId: tags){
					DeviceTagEntity dtEntity = new DeviceTagEntity();
					dtEntity.setDeviceId(sequence);
					dtEntity.setTagId(tagId);
					saveDTLists.add(dtEntity);
				}
				entity.setName(name+String.format("%02d", ++NAME_SUFFIX));
				entity.setTags(StringUtils.join(tags,","));
				entity.setTagNames(deviceSaveReq.getTagNames());
				remoteList.add(nativeReq);
			}
		}
		rmap.put("DeviceList",remoteList);
		// 调用c++
		NativeResp nativeResp = BrdNativeUtil.sdkInit(rmap);
		if(nativeResp.getRet()==0){
			// 保存设备基础信息
			saveBatch(saveLists);
			// 删除已存在的设备标签关联信息
			QueryWrapper<DeviceTagEntity> delWrapper = new QueryWrapper<>();
			delWrapper.lambda()
					.in(DeviceTagEntity::getDeviceId,sequences);
			deviceTagDao.delete(delWrapper);
			// 保存新的标签关系
			deviceTagService.saveBatch(saveDTLists);
		}else{
			return ApiResult.failure(nativeResp.getRemark());
		}
		return ApiResult.success();
	}


	/**
	 * 查询详情(单条)
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult info(DeviceSearchReq deviceSearchReq) throws Exception {
		// UserEntity userDB = userDao.selectById(userInfoParam.getId());
		return ApiResult.success();
	}

	/**
	 * 查询列表
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiPageResponse list(DeviceSearchReq deviceSearchReq) throws Exception {
		// todo 直接插 数据通过回调更新

		LambdaQueryWrapper<DeviceEntity> userWrapper = new LambdaQueryWrapper<>();
		userWrapper.like(StrUtil.isNotBlank(deviceSearchReq.getObscureSearch()), DeviceEntity::getSequence,
				deviceSearchReq.getObscureSearch());
		userWrapper.like(StrUtil.isNotBlank(deviceSearchReq.getObscureSearch()), DeviceEntity::getIp,
				deviceSearchReq.getObscureSearch());
		userWrapper.like(StrUtil.isNotBlank(deviceSearchReq.getObscureSearch()), DeviceEntity::getName,
				deviceSearchReq.getObscureSearch());
		if(deviceSearchReq.getTags().size()>0){
			List<String> sequences = deviceTagDao.getIdsByTags(deviceSearchReq.getTags());
			userWrapper.in(DeviceEntity::getSequence,sequences);
		}
		IPage<DeviceEntity> page = new Page<>(deviceSearchReq.getCurrentPage(),deviceSearchReq.getPageSize());
		userWrapper.orderBy(true, Objects.isNull(deviceSearchReq.getAscFlag()) ? false : deviceSearchReq.getAscFlag(),
				DeviceEntity::getCreateTime);
		page = deviceDao.selectPage(page, userWrapper);
 		return ApiPageResponse.ok(page);
	}

	/**
	 * 更新(单条)
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult update(DeviceEntity deviceEntity) throws Exception {

		Map<String,List<NativeDevInitReq>> req = new HashMap<>();
		NativeDevInitReq nativeDevInitReq = new NativeDevInitReq();
		List<NativeDevInitReq> nlist = new ArrayList<>();
		nativeDevInitReq.setVol(deviceEntity.getVolume());
		nativeDevInitReq.setDevId(deviceEntity.getSequence());
		nlist.add(nativeDevInitReq);
		req.put("DeviceList",nlist);
		final NativeResp nativeResp = BrdNativeUtil.setDevConfig(req);
		if(nativeResp.getRet()==0){
			// 更新基本信息
			deviceDao.updateById(deviceEntity);
			// 删除标签关联信息
			QueryWrapper<DeviceTagEntity> delWrapper = new QueryWrapper<>();
			delWrapper.lambda()
					.eq(DeviceTagEntity::getDeviceId,deviceEntity.getSequence());
			deviceTagDao.delete(delWrapper);
			// 重新添加标签
			List<DeviceTagEntity> saveTGEntitys = new ArrayList<>();
			for(String tagId:deviceEntity.getTags().split(",")){
				DeviceTagEntity tgEntity = new DeviceTagEntity();
				tgEntity.setTagId(tagId);
				tgEntity.setDeviceId(deviceEntity.getSequence());
				saveTGEntitys.add(tgEntity);
			}
			deviceTagService.saveBatch(saveTGEntitys);
		}else{
			return ApiResult.failure(nativeResp.getRemark());
		}
		return ApiResult.success();
	}

	/**
	 * 删除(单条)
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult delete(String id) throws Exception {
		final NativeResp nativeResp = BrdNativeUtil.delDevices(Arrays.asList(id));
		if(nativeResp.getRet()==0) {
			deviceDao.deleteById(id);
			// 异步删除标签 todo
			delTagAndRel(id);
		}

		return ApiResult.success();
	}
	// 下面的删除标签操作需要异步化
	private void delTagAndRel(String id){
		DeviceEntity deviceEntity = deviceDao.selectById(id);

		List<String> tags = Arrays.asList(deviceEntity.getTags().split(","));
		for(String tagId: tags){
			int count = deviceTagService
					.lambdaQuery()
					.eq(DeviceTagEntity::getTagId,tagId)
					.count();
			if(count == 0){
				// 说明没有关联的标签了，则删除标签信息
				tagDao.deleteById(tagId);
			}
		}
		// 删除关联关系
		QueryWrapper<DeviceTagEntity> delWrapper = new QueryWrapper<>();
		delWrapper.eq("device_id",id);
		deviceTagDao.delete(delWrapper);
	}

	@Override
	public ApiResult deleteBatch(List<String> ids) throws Exception {
		final NativeResp nativeResp = BrdNativeUtil.delDevices(ids);
		if(nativeResp.getRet()==0){
			deviceDao.deleteBatchIds(ids);
			// 异步循环删除关联关系 todo
			for(String id:ids){
				delTagAndRel(id);
			}
		}else{
			return ApiResult.failure(nativeResp.getRemark());
		}
		return ApiResult.success();
	}

	public static void main(String[] args) {
		int as = 01;
		List<String> tst = new ArrayList<>();
		tst.add("123");
		tst.add("122");
		tst.add("124");
		System.out.println(StringUtils.join(tst,","));
		System.out.println(String.format("%02d", as+1));
	}

}
