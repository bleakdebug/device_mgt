package cn.mgt.device.service.impl;

import cn.mgt.device.bean.CheckDO;
import cn.mgt.device.bean.CheckQuryDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.dao.CheckDao;
import cn.mgt.device.enums.StatusEnum;
import cn.mgt.device.service.CheckService;
import cn.mgt.device.service.AssRecordService;
import cn.mgt.device.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckDao checkDao;  //注入Dao
    
    @Autowired
    private cn.mgt.device.dao.AssRecordDao assRecordDao;
    
    @Autowired
    private cn.mgt.device.dao.DeviceDao deviceDao;
    
    @Autowired
    private AssRecordService assRecordService;


   // @Override
    public List queryCheck(CheckQuryDO checkQuryDO, Integer adminId, boolean isSuperAdmin) throws Exception{
        // 如果不是超级管理员，需要通过device_id过滤
        if (!isSuperAdmin && adminId != null) {
            // 获取该管理员管理的所有设备ID
            cn.mgt.device.bean.DeviceDO queryDevice = new cn.mgt.device.bean.DeviceDO();
            queryDevice.setAdminId(adminId);
            List<cn.mgt.device.bean.DeviceDO> devices = deviceDao.queryDevice(queryDevice);
            if (devices == null || devices.isEmpty()) {
                return new java.util.ArrayList();
            }
            // 获取这些设备对应的所有part_id（通过组装线监控表）
            java.util.Set<String> partIds = new java.util.HashSet<>();
            for (cn.mgt.device.bean.DeviceDO device : devices) {
                cn.mgt.device.bean.AssRecordDO queryRecord = new cn.mgt.device.bean.AssRecordDO();
                queryRecord.setDeviceId(device.getId());
                List<cn.mgt.device.bean.AssRecordDO> records = assRecordDao.queryAssRecord(queryRecord);
                if (records != null) {
                    for (cn.mgt.device.bean.AssRecordDO record : records) {
                        if (record.getPartId() != null) {
                            partIds.add(record.getPartId());
                        }
                    }
                }
            }
            // 如果指定了partId，检查是否在允许的列表中
            if (checkQuryDO.getPartId() != null) {
                if (!partIds.contains(checkQuryDO.getPartId())) {
                    return new java.util.ArrayList();
                }
                // partId在允许列表中，继续查询
                return checkDao.queryCheck(checkQuryDO, null, false, null);
            } else {
                // 如果没有指定partId，需要添加partIds过滤条件
                if (partIds.isEmpty()) {
                    return new java.util.ArrayList();
                }
                return checkDao.queryCheck(checkQuryDO, null, false, new java.util.ArrayList<>(partIds));
            }
        }
        return checkDao.queryCheck(checkQuryDO, null, isSuperAdmin, null);
    }

    //@Override
    public ResultDO addCheck(CheckDO checkDO, Integer adminId, boolean isSuperAdmin) throws Exception {
        int count = checkDao.addCheck(checkDO);
        return CommonUtil.sResult(count > 0,count > 0 ? "添加成功" : "添加失败");
    }

    //@Override
    public ResultDO updateCheck(CheckDO checkDO, Integer adminId, boolean isSuperAdmin) throws Exception {
        // 检查权限：如果不是超级管理员，需要验证partId对应的组装记录是否属于该管理员管理的设备
        if (!isSuperAdmin && adminId != null) {
            // 先获取原有记录
            CheckDO existingCheck = checkDao.getCheckById(checkDO.getId());
            if (existingCheck == null) {
                return CommonUtil.sResult(false, "记录不存在");
            }
            // 如果修改了partId，需要验证新的partId是否属于该管理员
            String partIdToCheck = checkDO.getPartId() != null ? checkDO.getPartId() : existingCheck.getPartId();
            cn.mgt.device.bean.AssRecordDO queryRecord = new cn.mgt.device.bean.AssRecordDO();
            queryRecord.setPartId(partIdToCheck);
            List records = assRecordService.queryAssRecord(queryRecord, adminId, false);
            if (records == null || records.isEmpty()) {
                return CommonUtil.sResult(false, "该部件编号不属于您管理的设备");
            }
        }
        int count = checkDao.updateCheck(checkDO);
        return CommonUtil.sResult(count > 0, count > 0 ? "修改成功" : "修改失败");
    }

    //@Override
    public ResultDO deleteCheck(int id, Integer adminId, boolean isSuperAdmin) throws Exception {
        // 检查权限：如果不是超级管理员，需要验证partId对应的组装记录是否属于该管理员管理的设备
        if (!isSuperAdmin && adminId != null) {
            CheckDO check = checkDao.getCheckById(id);
            if (check == null) {
                return CommonUtil.sResult(false, "记录不存在");
            }
            // 检查该partId是否属于该管理员管理的设备
            cn.mgt.device.bean.AssRecordDO queryRecord = new cn.mgt.device.bean.AssRecordDO();
            queryRecord.setPartId(check.getPartId());
            List records = assRecordService.queryAssRecord(queryRecord, adminId, false);
            if (records == null || records.isEmpty()) {
                return CommonUtil.sResult(false, "无权删除此记录");
            }
        }
        int count = checkDao.deleteCheck(id);
        return CommonUtil.sResult(count > 0, count > 0 ? "删除成功" : "删除失败");
    }

}

