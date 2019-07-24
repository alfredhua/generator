package com.generator;



import com.generator.entity.JDBCEntity;
import com.generator.entity.MybatisGeneratorEntity;
import com.generator.util.MybatisGeneratorUtil;
import com.generator.util.PropertiesFileUtil;

/**
 * 代码生成类
 */
public class Generator {

	// 根据命名规范，只修改此常量值即可
    private static String outPath= PropertiesFileUtil.getInstance("generator").get("outPath");   //输出路径
	private static String MODULE = PropertiesFileUtil.getInstance("generator").get("module");						   //模块名称
	private static String DATABASE =  PropertiesFileUtil.getInstance("generator").get("databse");				       //数据库名称
	private static String TABLE_PREFIX = PropertiesFileUtil.getInstance("generator").get("table_prefix");				   //表的前缀
	private static String PACKAGE_NAME = "com."+MODULE;		       //包
	private static boolean isBuildGradle=new Boolean(PropertiesFileUtil.getInstance("generator").get("isBuildGradle"));					//是否生成build.gradle文件
    public  static  boolean isNewFile=new Boolean(PropertiesFileUtil.getInstance("generator").get("isNewFile"));          //存在文件时是否替换：true：替换，false:不替换

	//api下的entity目录
	private static String API_ENTITY=outPath+MODULE+"/"
			+MODULE+"-api/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/entity/";

	//api下的dto目录
	private static String API_ENTITY_DTO=outPath+MODULE+"/"
			+MODULE+"-api/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/dto/";

	//api下的service
	private static String SERVICE=outPath+MODULE+"/"
			+MODULE+"-api/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/api/";
	//api下的constants
	private static String CONSTANTS=outPath+MODULE+"/"
			+MODULE+"-api/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/constants/";


	//server下的mapper
	private static String MAPPER=outPath+MODULE+"/"
			+MODULE+"-server/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/dao/mapper";

	//server下的mapper_provider
	private static String MAPPER_PROVIDER=outPath+MODULE+"/"
			+MODULE+"-server/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/dao/mapper";

	//server下的impl
	private static String SERVICE_IMPL=outPath+MODULE+"/"
			+MODULE+"-server/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/impl";


	//server下的impl
	private static String CONTROLLER=outPath+MODULE+"/"
			+MODULE+"-server/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/controller";


	//server下的vo
	private static String VO=outPath+MODULE+"/"
			+MODULE+"-server/src/main/java/"+PACKAGE_NAME.replace(".", "/")+"/controller/vo/";



	/**
	 * 数据库连接
	 */
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
	private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");


	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

        MybatisGeneratorEntity mybatisGeneratorEntity = MybatisGeneratorEntity.getInstance().
                setDatabase(DATABASE).setOutPath(outPath).setModule(MODULE).setTablePrefix(TABLE_PREFIX).
                setPackageName(PACKAGE_NAME).setIsBuildGradle(isBuildGradle).setApiEntityPath(API_ENTITY).
				setApiEntityDTOPath(API_ENTITY_DTO).setApiServicePath(SERVICE).setApiConstants(CONSTANTS).
				setMapperPath(MAPPER).setMapperProviderPath(MAPPER_PROVIDER).setServiceImplPath(SERVICE_IMPL).
				setControllerPath(CONTROLLER).setVoPath(VO);

		JDBCEntity jdbcEntity= JDBCEntity.getInstance().setJdbcDriver(JDBC_DRIVER).
				setJdbcPassword(JDBC_PASSWORD). setJdbcUseName(JDBC_USERNAME).
				setJdbcUrl(JDBC_URL);
        //生成项目结构
      MybatisGeneratorUtil.generator(mybatisGeneratorEntity,jdbcEntity);
	}



}
