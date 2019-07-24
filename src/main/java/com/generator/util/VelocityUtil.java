package com.generator.util;


import com.generator.Generator;
import com.generator.entity.ColumnEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import java.io.*;
import java.util.*;

/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
public class VelocityUtil {



    public static void apiBuild(String outPath, String module,String buildApi_vm)throws Exception{
        VelocityContext context1 = new VelocityContext();
        context1.put("module", module);
        String buildApi = outPath+module+"/"+module+"-api/build.gradle";
        generate(buildApi_vm, buildApi, context1);
    }

    public static void apiEntity(String outPath,String packageName,
                                 List<ColumnEntity> list,String entityName,
                                 String entity_vm)throws Exception{

        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("entity", "entity");
        context.put("columnEntityList", list);
        context.put("time",DateTimeUtil.getDate());
        File file=new File(outPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String entity =outPath+entityName+".java";
        generate(entity_vm, entity, context);
    }


    public static void serverVo(String outPath,String packageName,
                                 List<ColumnEntity> list,String entityName,
                                 String vo_vm)throws Exception{
        String name=entityName+"CreateReqVO";
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("loseEntityName", entityName.toLowerCase());
        context.put("entityNameVO", name);
        context.put("type","controller.vo" );
        context.put("time",DateTimeUtil.getDate());
        File file=new File(outPath+entityName.toLowerCase());
        if (!file.exists()){
            file.mkdirs();
        }
        String entity =outPath+entityName.toLowerCase()+"/"+name+".java";
        generate(vo_vm, entity, context);

        //respVO
        String entityName4=entityName+"UpdateReqVO";
        String entity4=outPath+entityName.toLowerCase()+"/"+entityName4+".java";
        context.put("entityNameVO",entityName4 );
        generate(vo_vm, entity4, context);


        //respVO
        String entityName2=entityName+"RespVO";
        String entity2=outPath+entityName.toLowerCase()+"/"+entityName2+".java";
        context.put("entityNameVO",entityName2 );
        generate(vo_vm, entity2, context);

        //reqListVO
        String entityName3=entityName+"ListReqVO";
        String entity3=outPath+entityName.toLowerCase()+"/"+entityName3+".java";
        context.put("entityNameVO",entityName3 );
        context.put("isListReq",true );
        generate(vo_vm, entity3, context);


    }

    public static void apiEntityDTO(String outPath, String packageName, String entityName, String entity_dto_vm)throws Exception{
        //reqDTO
        String name=entityName+"ReqDTO";
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("loseEntityName", entityName.toLowerCase());
        context.put("entityNameDTO", name);
        context.put("type","dto" );
        context.put("time",DateTimeUtil.getDate());
        File file=new File(outPath+entityName.toLowerCase());
        if (!file.exists()){
            file.mkdirs();
        }
        String entity =outPath+entityName.toLowerCase()+"/"+name+".java";
        generate(entity_dto_vm, entity, context);

        //respDTO
        String entityName2=entityName+"RespDTO";
        String entity2=outPath+entityName.toLowerCase()+"/"+entityName2+".java";
        context.put("entityNameDTO",entityName2 );
        generate(entity_dto_vm, entity2, context);

        //reqList
        String entityName3=entityName+"ListReqDTO";
        String entity3=outPath+entityName.toLowerCase()+"/"+entityName3+".java";
        context.put("entityNameDTO",entityName3 );
        context.put("isListReq",true );
        generate(entity_dto_vm, entity3, context);
    }


    public static void apiService(String outPath, String packageName, String entityName, String service_vm)throws Exception {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("time",DateTimeUtil.getDate());
        context.put("firstLowerEntityName", StringUtil.toLowerCaseFirstOne(entityName));
        String entity =outPath+"/"+entityName+"Service.java";
        File file=new File(outPath);
        if (!file.exists()){
            file.mkdirs();
        }
        generate(service_vm, entity, context);
    }


    public static void apiConstants(String outPath, String packageName, String entityName, String constants_vm)throws Exception {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("ctime",DateTimeUtil.getDate());
        String entity =outPath+entityName.toLowerCase()+"/"+entityName+"ErrorEnum.java";
        File file=new File(outPath+entityName.toLowerCase());
        if (!file.exists()){
            file.mkdirs();
        }
        generate(constants_vm, entity, context);
    }



    public static void serverMapper(String mapperPath, String database, String tableName,
                                    String packageName, String entityName, List<ColumnEntity> listColumnEntity,
                                    String mapper_vm)throws Exception {

        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("database", database);
        context.put("columnEntityList", listColumnEntity);
        context.put("tableName", tableName);
        context.put("firstLowerEntityName",StringUtil.toLowerCaseFirstOne(entityName));
        context.put("ctime",DateTimeUtil.getDate());
        String entity =mapperPath+"/"+entityName+"Mapper.java";
        File file=new File(mapperPath);
        if (!file.exists()){
            file.mkdirs();
        }
        generate(mapper_vm, entity, context);
    }

    public static void serverMapperProvider(String mapperPath, String database, String tableName,
                                            String packageName, String entityName, String mapper_vm,
                                            List<ColumnEntity> listColumnEntity)throws Exception {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("database", database);
        context.put("tableName", tableName);
        context.put("columnEntityList", listColumnEntity);
        context.put("firstLowerEntityName",StringUtil.toLowerCaseFirstOne(entityName));
        context.put("ctime",DateTimeUtil.getDate());
        String entity =mapperPath+"/"+entityName+"Provider.java";
        File file=new File(mapperPath);
        if (!file.exists()){
            file.mkdirs();
        }
        generate(mapper_vm, entity, context);
    }


    public static void serverServiceImpl(String serviceImplPath, String packageName,
                                         String entityName, String mapper_provider_vm,
                                         List<ColumnEntity> listColumnEntity)throws Exception {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("columnEntityList", listColumnEntity);
        context.put("firstLowerEntityName",StringUtil.toLowerCaseFirstOne(entityName));
        context.put("ctime",DateTimeUtil.getDate());
        String entity =serviceImplPath+"/"+entityName+"ServiceImpl.java";
        File file=new File(serviceImplPath);
        if (!file.exists()){
            file.mkdirs();
        }
        generate(mapper_provider_vm, entity, context);
    }


    public static void serverController(String controllerPath, String packageName,
                                         String entityName, String controller_vm,
                                         List<ColumnEntity> listColumnEntity)throws Exception {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("upperEntityName", entityName.toUpperCase());

        context.put("columnEntityList", listColumnEntity);
        context.put("firstLowerEntityName",StringUtil.toLowerCaseFirstOne(entityName));
        context.put("ctime",DateTimeUtil.getDate());
        String entity =controllerPath+"/"+entityName+"Controller.java";
        File file=new File(controllerPath);
        if (!file.exists()){
            file.mkdirs();
        }
        generate(controller_vm, entity, context);
    }



    public static void serverBuild(String outPath, String module,String buildServer_vm)throws Exception{
        VelocityContext context1 = new VelocityContext();
        context1.put("module", module);
        String path=outPath+module+"/"+module+"-server";
        File file=new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        String buildApi =path+ "/build.gradle";
        generate(buildServer_vm, buildApi, context1);
    }



    // 递归删除非空文件夹
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i]);
            }
        }
        dir.delete();
    }




    /**
     * 根据模板生成文件
     * @param inputVmFilePath 模板路径
     * @param outputFilePath 输出文件路径
     * @param context
     * @throws Exception
     */
    public static void generate(String inputVmFilePath, String outputFilePath, VelocityContext context) throws Exception {
        try {
            Properties properties = new Properties();
            properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, getPath(inputVmFilePath));
            Velocity.init(properties);
            String file1 = getFile(inputVmFilePath);
            Template template = Velocity.getTemplate(file1, "utf-8");
            File file = new File(outputFilePath);
            if(!file.isDirectory()) {
                if (!file.exists()){
                    //不存在就创建
                    file.delete();
                    file.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(file), "utf-8"));
                    template.merge(context, writer);
                    writer.close();
                }else{
                    //存在的话是否替换
                    if(Generator.isNewFile){
                        file.delete();
                        file.createNewFile();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(file), "utf-8"));
                        template.merge(context, writer);
                        writer.close();
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }


    /**
     * 根据文件绝对路径获取目录
     * @param filePath
     * @return
     */
    public static String getPath(String filePath) {
        String path = "";
        if (StringUtils.isNotBlank(filePath)) {
            path = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        return path;
    }

    /**
     * 根据文件绝对路径获取文件
     * @param filePath
     * @return
     */
    public static String getFile(String filePath) {
        String file = "";
        if (StringUtils.isNotBlank(filePath)) {
            file = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return file;
    }

}
