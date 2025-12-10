package cn.mgt.device.service.impl;

import cn.mgt.device.bean.AssRecordDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.dao.AssRecordDao;
import cn.mgt.device.enums.PartTypeEnum;
import cn.mgt.device.enums.StatusEnum;
import cn.mgt.device.enums.WorkStaEnum;
import cn.mgt.device.service.AssRecordService;
import cn.mgt.device.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssRecordServiceImpl implements AssRecordService {

    @Autowired
    private AssRecordDao assRecordDao;  //注入Dao


    @Autowired
    private cn.mgt.device.dao.DeviceDao deviceDao;

    //@Override
    public List queryAssRecord(AssRecordDO assRecordDO, Integer adminId, boolean isSuperAdmin) throws Exception{
        // 如果不是超级管理员，需要通过device_id过滤，只查询该管理员管理的设备
        if (!isSuperAdmin && adminId != null) {
            // 获取该管理员管理的所有设备ID
            cn.mgt.device.bean.DeviceDO queryDevice = new cn.mgt.device.bean.DeviceDO();
            queryDevice.setAdminId(adminId);
            List<cn.mgt.device.bean.DeviceDO> devices = deviceDao.queryDevice(queryDevice);
            if (devices == null || devices.isEmpty()) {
                return new java.util.ArrayList();
            }
            
            // 提取设备ID列表
            java.util.List<Integer> deviceIds = new java.util.ArrayList<>();
            for (cn.mgt.device.bean.DeviceDO device : devices) {
                deviceIds.add(device.getId());
            }
            
            // 如果指定了deviceId，检查是否属于该管理员
            if (assRecordDO.getDeviceId() != null) {
                if (!deviceIds.contains(assRecordDO.getDeviceId())) {
                    return new java.util.ArrayList();
                }
                // deviceId属于该管理员，继续查询
                return assRecordDao.queryAssRecord(assRecordDO);
            } else {
                // 如果没有指定deviceId，需要限制只能查询该管理员的设备
                // 使用deviceIds列表进行IN查询
                return assRecordDao.queryAssRecordByDeviceIds(deviceIds, assRecordDO);
            }
        }
        // 超级管理员或未指定adminId，直接查询
        return assRecordDao.queryAssRecord(assRecordDO);
    }

    //@Override
    public ResultDO addAssRecord(AssRecordDO assRecordDO) throws Exception {
        //在XX/XX/XX XX:XX:XX检测到工作站1部件A编号为xxx的机械部件组装状正常
        String detail = "在" + assRecordDO.getAssemblyDateTime() + "检测到"
                + WorkStaEnum.of(assRecordDO.getWorkstationId()).getDictName()
                + PartTypeEnum.of(assRecordDO.getPartType()).getDictName()
                + "编号为" + assRecordDO.getPartId() + "的机械部件组装状态"
                + StatusEnum.of(assRecordDO.getAssemblyStatus()).getDictName();
        assRecordDO.setDetail(detail);
        int count = assRecordDao.addAssRecord(assRecordDO);
        return CommonUtil.sResult(count > 0,count > 0 ? "记录成功" : "记录失败");
    }

    //@Override
    public ResultDO deleteAssRecord(int id, Integer adminId, boolean isSuperAdmin) throws Exception{
        // 检查权限：如果不是超级管理员，需要验证记录关联的设备是否属于该管理员
        if (!isSuperAdmin && adminId != null) {
            AssRecordDO record = assRecordDao.getAssRecordById(id);
            if (record == null || record.getDeviceId() == null) {
                return CommonUtil.sResult(false, "记录不存在");
            }
            // 检查设备是否属于该管理员
            cn.mgt.device.bean.DeviceDO device = deviceDao.getDeviceById(record.getDeviceId());
            if (device == null || !adminId.equals(device.getAdminId())) {
                return CommonUtil.sResult(false, "无权删除此记录");
            }
        }
        //删除当前id在数据库中的记录
        int count = assRecordDao.deleteAssRecord(id);
        return CommonUtil.sResult(count > 0,count > 0 ? "删除成功" : "删除失败");
    }

    //@Override
    public ResultDO updateAssRecord(AssRecordDO assRecordDO, Integer adminId, boolean isSuperAdmin) throws Exception {
        // 检查权限：如果不是超级管理员，需要验证记录关联的设备是否属于该管理员
        if (!isSuperAdmin && adminId != null) {
            AssRecordDO existingRecord = assRecordDao.getAssRecordById(assRecordDO.getId());
            if (existingRecord == null || existingRecord.getDeviceId() == null) {
                return CommonUtil.sResult(false, "记录不存在");
            }
            // 检查设备是否属于该管理员
            cn.mgt.device.bean.DeviceDO device = deviceDao.getDeviceById(existingRecord.getDeviceId());
            if (device == null || !adminId.equals(device.getAdminId())) {
                return CommonUtil.sResult(false, "无权修改此记录");
            }
            // 如果修改了deviceId，需要验证新的deviceId是否属于该管理员
            if (assRecordDO.getDeviceId() != null && !assRecordDO.getDeviceId().equals(existingRecord.getDeviceId())) {
                cn.mgt.device.bean.DeviceDO newDevice = deviceDao.getDeviceById(assRecordDO.getDeviceId());
                if (newDevice == null || !adminId.equals(newDevice.getAdminId())) {
                    return CommonUtil.sResult(false, "无权将记录转移到此设备");
                }
            } else {
                // 保持原有的deviceId
                assRecordDO.setDeviceId(existingRecord.getDeviceId());
            }
        }
        // 更新detail信息
        String detail = "在" + assRecordDO.getAssemblyDateTime() + "检测到"
                + WorkStaEnum.of(assRecordDO.getWorkstationId()).getDictName()
                + PartTypeEnum.of(assRecordDO.getPartType()).getDictName()
                + "编号为" + assRecordDO.getPartId() + "的机械部件组装状态"
                + StatusEnum.of(assRecordDO.getAssemblyStatus()).getDictName();
        assRecordDO.setDetail(detail);
        int count = assRecordDao.updateAssRecord(assRecordDO);
        return CommonUtil.sResult(count > 0, count > 0 ? "修改成功" : "修改失败");
    }

}
