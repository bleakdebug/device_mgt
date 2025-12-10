# Device Management System

##  项目简介

Device Management System（设备管理系统）是一个基于 Spring Boot 2.1.5 开发的工业设备管理平台。系统提供了完整的设备信息管理、组装线监控、质量检测等功能，支持设备管理员和超级管理员两种角色，实现了权限控制和数据隔离。

##  主要功能

### 1. 设备管理 (Device Management)
-  设备信息的增删改查
-  设备状态管理（正常/异常）
-  设备购置时间和作废时间管理
-  设备与管理员关联管理

### 2. 组装线监控 (Assembly Line Monitoring)
-  组装记录的增删改查
-  工作站、部件类型、组装状态管理

### 3. 质量检测 (Quality Inspection)
-  质量检测记录的增删改查
-  检测项目管理和结果统计
-  检测记录与部件关联

### 4. 用户权限管理
-  设备管理员：只能管理分配给自己的设备及相关数据
-  超级管理员：拥有所有权限，可以管理所有设备和用户

## 技术栈

- **后端框架**: Spring Boot 2.1.5
- **持久层框架**: MyBatis
- **数据库**: MySQL 8.0+
- **前端技术**: HTML5, CSS3, JavaScript
- **模板引擎**: Thymeleaf
- **构建工具**: Maven

## 项目结构

```
DeviceMgt/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── cn/czy/device/
│   │   │       ├── ApiApplication.java          # 主启动类
│   │   │       ├── bean/                        # 实体类
│   │   │       │   ├── DeviceDO.java
│   │   │       │   ├── AssRecordDO.java
│   │   │       │   ├── CheckDO.java
│   │   │       │   └── ...
│   │   │       ├── controller/                  # 控制器层
│   │   │       │   ├── DeviceController.java
│   │   │       │   ├── AssRecordController.java
│   │   │       │   ├── CheckController.java
│   │   │       │   └── ...
│   │   │       ├── service/                     # 服务层
│   │   │       │   ├── DeviceService.java
│   │   │       │   ├── AssRecordService.java
│   │   │       │   ├── CheckService.java
│   │   │       │   └── impl/                    # 服务实现类
│   │   │       ├── dao/                         # 数据访问层
│   │   │       │   ├── DeviceDao.java
│   │   │       │   ├── AssRecordDao.java
│   │   │       │   └── ...
│   │   │       ├── config/                      # 配置类
│   │   │       ├── util/                        # 工具类
│   │   │       └── enums/                       # 枚举类
│   │   └── resources/
│   │       ├── mapper/                         # MyBatis映射文件
│   │       ├── templates/                      # 前端页面模板
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   ├── device.html
│   │       │   ├── assRecord.html
│   │       │   └── check.html
│   │       └── application.properties          # 配置文件
├── device.sql                                  # 数据库初始化脚本
└── pom.xml                                     # Maven配置文件
```

##数据库设计

### 数据表说明

#### 1. super_admin (超级管理员表)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int(6) | 主键，自增 |
| name | varchar(50) | 管理员姓名 |
| password | varchar(255) | 登录密码 |

#### 2. device_admin (设备管理员表)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int(6) | 主键，自增 |
| name | varchar(50) | 管理员姓名 |
| password | varchar(255) | 登录密码 |

#### 3. device (设备表)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int(6) | 主键，自增 |
| no | int(12) | 设备编号 |
| type | varchar(12) | 设备类型 |
| model | varchar(6) | 设备型号 |
| status | varchar(6) | 设备状态（1:正常, 2:异常） |
| purchase_time | date | 购置时间 |
| dumpe_time | date | 作废时间 |
| admin_id | int(6) | 设备管理员ID（外键） |

#### 4. assembly_records (组装线监控表)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int(6) | 主键，自增 |
| workstation_id | varchar(12) | 工作站编号 |
| part_id | varchar(12) | 部件编号 |
| part_type | varchar(6) | 部件类型（1:类型A, 2:类型B, 3:类型C） |
| assembly_date_time | datetime | 组装日期和时间 |
| assembly_status | varchar(6) | 组装状态（1:正常, 2:异常） |
| detail | varchar(255) | 异常详情 |
| device_id | int(6) | 设备ID（外键） |

#### 5. quality_check (质量检测表)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | int(6) | 主键，自增 |
| part_id | varchar(12) | 部件编号 |
| project_name | varchar(255) | 检测项目名称 |
| result | varchar(255) | 检测结果（0:不合格, 1:合格） |
| time | date | 检测时间 |
| remark | varchar(255) | 备注 |

##  快速开始

### 环境要求

- JDK 8 或以上版本
- Maven 3.6.0 或以上版本
- MySQL 8.0 或以上版本

### 安装步骤

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd DeviceMgt
   ```

2. **创建数据库**
   ```sql
   CREATE DATABASE device CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
   ```

3. **导入数据库**
   ```bash
   mysql -u root -p device < device.sql
   ```

4. **配置数据库连接**
   
   编辑 `src/main/resources/application.properties`：
   ```properties
   jdbc.url=jdbc:mysql://localhost:3306/device?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
   jdbc.username=root
   jdbc.password=your_password
   ```

5. **编译项目**
   ```bash
   mvn clean install
   ```

6. **运行项目**
   ```bash
   mvn spring-boot:run
   ```
   
   或者在 IDE 中直接运行 `ApiApplication.java`

7. **访问系统**
   - 首页: http://localhost:8090/
   - 登录页: http://localhost:8090/login

### 默认账号

**超级管理员**
- ID: 1
- 密码: super123

**设备管理员**
- ID: 1, 密码: admin123
- ID: 2, 密码: admin123

##  API 文档

### 主要接口

#### 设备管理
- `POST /api/device/query` - 查询设备
- `POST /api/device/addDevice` - 添加设备
- `POST /api/device/updateDevice` - 更新设备
- `POST /api/device/deleteDevice` - 删除设备

#### 组装线监控
- `POST /api/assRecord/query` - 查询组装记录
- `POST /api/assRecord/addAssRecord` - 添加组装记录
- `POST /api/assRecord/updateAssRecord` - 更新组装记录
- `POST /api/assRecord/deleteAssRecord` - 删除组装记录

#### 质量检测
- `POST /api/check/query` - 查询检测记录
- `POST /api/check/addCheck` - 添加检测记录
- `POST /api/check/updateCheck` - 更新检测记录
- `POST /api/check/deleteCheck` - 删除检测记录

#### 用户登录
- `POST /api/login/deviceAdmin` - 设备管理员登录
- `POST /api/login/superAdmin` - 超级管理员登录
- `GET /api/login/info` - 获取当前登录用户信息
- `POST /api/logout` - 退出登录

##  权限说明

### 设备管理员 (Device Admin)
-  只能查看和管理分配给自己的设备
-  只能查看和管理自己设备相关的组装记录
-  只能查看和管理自己设备相关部件的质量检测记录
-  不能管理其他管理员的设备
-  不能管理设备管理员账户

### 超级管理员 (Super Admin)
-  可以管理所有设备
-  可以查看和管理所有组装记录
-  可以查看和管理所有质量检测记录
-  可以管理设备管理员账户

##  前端页面

系统提供了完整的前端界面，包括：

- **首页** (`/`): 系统功能概览
- **登录页** (`/login`): 设备管理员登录
- **超级管理员登录** (`/superLogin`): 超级管理员登录
- **主页** (`/home`): 登录后的主页面
- **设备管理** (`/device`): 设备信息管理页面
- **组装线监控** (`/assRecord`): 组装记录管理页面
- **质量检测** (`/check`): 质量检测管理页面
- **设备管理员管理** (`/deviceAdmin`): 仅超级管理员可见

