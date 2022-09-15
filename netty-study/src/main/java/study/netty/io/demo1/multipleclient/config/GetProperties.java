/**
 *
 */
package study.netty.io.demo1.multipleclient.config;

import java.io.IOException;
import java.util.Properties;


/**
 * @Description:
 * @version: v1.0.0
 * @author: wbl
 * @date: 2019年9月16日 下午6:22:11
 */
public class GetProperties {

	private static Properties properties = null;
    // 初始化
    static {
        properties = new Properties();
        try {
            properties.load(GetProperties.class.getClassLoader().getResourceAsStream("config/commonConfig.properties"));
        }catch(IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    // 获取值
    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        String value = GetProperties.getValue("author.name");
        System.out.print("username="+value);
    }



}
