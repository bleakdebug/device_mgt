package cn.mgt.device.dao;

import cn.mgt.device.bean.SuperAdminDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuperAdminDao {
    SuperAdminDO login(SuperAdminDO superAdminDO);

    SuperAdminDO getById(int id);
}

