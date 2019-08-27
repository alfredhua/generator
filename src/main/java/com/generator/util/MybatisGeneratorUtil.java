package com.generator.util;
import com.generator.entity.ColumnEntity;
import com.generator.entity.JDBCEntity;
import com.generator.entity.VmFileEntity;
import com.generator.sql.QueryUtil;
import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.*;

/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
public class MybatisGeneratorUtil {



    public static void generator( JDBCEntity jdbcEntity ) throws Exception{

        VmFileEntity vmFileEntity = VmPathUtil.getVm();

        //TODO 获取所有的当前数据库下的所有的table
        List<Map<String, Object>> tablesList = QueryUtil.getTables(jdbcEntity, DATABASE, TABLE_PREFIX);

        //TODO 生成api模块下的entity
        for (Map<String,Object> map:tablesList){

            List<ColumnEntity> listColumnEntity = QueryUtil.getColumns(jdbcEntity,DATABASE,map.get("table_name").toString());

            VelocityUtil.apiEntity(listColumnEntity,map.get("entity_name").toString(),vmFileEntity.getEntity_vm());
            //TODO DTO下的实体类
            VelocityUtil.apiEntityDTO(map.get("entity_name").toString(),vmFileEntity.getEntity_dto_vm());

            VelocityUtil.apiConstants(map.get("entity_name").toString(),vmFileEntity.getConstants_vm());

            //TODO service
            VelocityUtil.apiService(map.get("entity_name").toString(), vmFileEntity.getService_vm());

            //TODO mapper
            VelocityUtil.serverMapper(map.get("table_name").toString(),map.get("entity_name").toString(),listColumnEntity,vmFileEntity.getMapper_vm());

            //TODO provider
            VelocityUtil.serverMapperProvider(map.get("table_name").toString(),map.get("entity_name").toString(),vmFileEntity.getMapper_provider_vm(),listColumnEntity);
            //TODO serviceImpl
            VelocityUtil.serverServiceImpl(map.get("entity_name").toString(),vmFileEntity.getServiceImpl_vm(),listColumnEntity);

            //TODO controller
            VelocityUtil.serverController(map.get("entity_name").toString(),vmFileEntity.getController_vm(),listColumnEntity);

            VelocityUtil.serverVo(map.get("entity_name").toString(),vmFileEntity.getVo_vm());

        }


        if (isBuildGradle) {
            //TODO 生成server模块下的build.gradle
            VelocityUtil.serverBuild(outPath, MODULE,vmFileEntity.getBuildServer_vm());
        }

        //TODO 生成api模块下的build.gradle
        if (isBuildGradle) {
            VelocityUtil.apiBuild(outPath, MODULE,vmFileEntity.getBuildApi_vm());
        }



    }




}
