package com.generator.util;
import com.generator.entity.ColumnEntity;
import com.generator.entity.JDBCEntity;
import com.generator.entity.MybatisGeneratorEntity;
import com.generator.entity.VmFileEntity;
import com.generator.sql.QueryUtil;
import java.util.List;
import java.util.Map;


/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
public class MybatisGeneratorUtil {



    public static void generator(MybatisGeneratorEntity mybatisGeneratorEntity, JDBCEntity jdbcEntity ) throws Exception{

        VmFileEntity vmFileEntity = VmPathUtil.getVm(mybatisGeneratorEntity);

        //TODO 获取所有的当前数据库下的所有的table
        List<Map<String, Object>> tablesList = QueryUtil.
                getTables(jdbcEntity, mybatisGeneratorEntity.getDatabase(),
                        mybatisGeneratorEntity.getTablePrefix());

        //TODO 生成api模块下的entity
        for (Map<String,Object> map:tablesList){

            List<ColumnEntity> listColumnEntity = QueryUtil.getColumns(jdbcEntity,
                    mybatisGeneratorEntity.getDatabase(),
                    map.get("table_name").toString());


            VelocityUtil.apiEntity(mybatisGeneratorEntity.getApiEntityPath(),
                    mybatisGeneratorEntity.getPackageName(),listColumnEntity,map.get("entity_name").toString(),
                    vmFileEntity.getEntity_vm());
            //TODO DTO下的实体类
            VelocityUtil.apiEntityDTO(mybatisGeneratorEntity.getApiEntityDTOPath(),
                    mybatisGeneratorEntity.getPackageName(),map.get("entity_name").toString(),
                    vmFileEntity.getEntity_dto_vm());

            VelocityUtil.apiConstants(mybatisGeneratorEntity.getApiConstants(),
                    mybatisGeneratorEntity.getPackageName(),map.get("entity_name").toString(),
                    vmFileEntity.getConstants_vm());

            //TODO service
            VelocityUtil.apiService(mybatisGeneratorEntity.getApiServicePath(),
                    mybatisGeneratorEntity.getPackageName(),map.get("entity_name").toString(),
                    vmFileEntity.getService_vm());

            //TODO mapper
            VelocityUtil.serverMapper(mybatisGeneratorEntity.getMapperPath(),
                    mybatisGeneratorEntity.getDatabase(),map.get("table_name").toString(),
                    mybatisGeneratorEntity.getPackageName(),map.get("entity_name").toString(),listColumnEntity,
                    vmFileEntity.getMapper_vm());

            //TODO provider
            VelocityUtil.serverMapperProvider(mybatisGeneratorEntity.getMapperProviderPath(),
                    mybatisGeneratorEntity.getDatabase(),map.get("table_name").toString(),
                    mybatisGeneratorEntity.getPackageName(),map.get("entity_name").toString(),
                    vmFileEntity.getMapper_provider_vm(),listColumnEntity);
            //TODO serviceImpl
            VelocityUtil.serverServiceImpl(mybatisGeneratorEntity.getServiceImplPath(),
                    mybatisGeneratorEntity.getPackageName(),map.get("entity_name").toString(),
                    vmFileEntity.getServiceImpl_vm(),listColumnEntity);

            //TODO controller
            VelocityUtil.serverController(mybatisGeneratorEntity.getControllerPath(),
                    mybatisGeneratorEntity.getPackageName(),map.get("entity_name").toString(),
                    vmFileEntity.getController_vm(),listColumnEntity);

            VelocityUtil.serverVo(mybatisGeneratorEntity.getVoPath(),
                    mybatisGeneratorEntity.getPackageName(),listColumnEntity,map.get("entity_name").toString(),
                    vmFileEntity.getVo_vm());

        }



        if (mybatisGeneratorEntity.isBuildGradle()) {
            //TODO 生成server模块下的build.gradle
            VelocityUtil.serverBuild(mybatisGeneratorEntity.getOutPath(), mybatisGeneratorEntity.getModule(),
                    vmFileEntity.getBuildServer_vm());
        }

        //TODO 生成api模块下的build.gradle
        if (mybatisGeneratorEntity.isBuildGradle()) {
            VelocityUtil.apiBuild(mybatisGeneratorEntity.getOutPath(), mybatisGeneratorEntity.getModule(),
                    vmFileEntity.getBuildApi_vm());
        }



    }




}
