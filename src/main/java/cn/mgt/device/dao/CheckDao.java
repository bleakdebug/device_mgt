package cn.mgt.device.dao;


import cn.mgt.device.bean.CheckDO;
import cn.mgt.device.bean.CheckQuryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CheckDao {
    List queryCheck(@Param("query") CheckQuryDO checkQuryDO, @Param("adminId") Integer adminId, @Param("isSuperAdmin") boolean isSuperAdmin, @Param("partIds") List<String> partIds);

    int addCheck(CheckDO checkDO);
    
    int updateCheck(CheckDO checkDO);
    
    int deleteCheck(int id);
    
    CheckDO getCheckById(int id);

}
