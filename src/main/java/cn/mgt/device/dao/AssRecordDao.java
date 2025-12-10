package cn.mgt.device.dao;


import cn.mgt.device.bean.AssRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssRecordDao {
    List queryAssRecord(AssRecordDO assRecordDO);
    
    List queryAssRecordByDeviceIds(@org.apache.ibatis.annotations.Param("deviceIds") List<Integer> deviceIds, @org.apache.ibatis.annotations.Param("query") AssRecordDO query);

    int deleteAssRecord(int id);

    int addAssRecord(AssRecordDO assRecordDO);
    
    int updateAssRecord(AssRecordDO assRecordDO);
    
    AssRecordDO getAssRecordById(int id);
}
