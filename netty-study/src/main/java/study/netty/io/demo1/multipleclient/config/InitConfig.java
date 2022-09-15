/**
 *
 */
package study.netty.io.demo1.multipleclient.config;


public class InitConfig {

	public static  String HEART_INFO = GetProperties.getValue("client.heart");

	public static int MAX_CLIENT = Integer.parseInt(GetProperties.getValue("client.maxnum"));

	public static int client_num = 0;

	public static int client_active = 0;
	public static int thread_num = 0;

	public String getHEART_INFO() {
		return HEART_INFO;
	}
	public void setHEART_INFO(String hEART_INFO) {
		HEART_INFO = hEART_INFO;
	}
	public int getMAX_CLIENT() {
		return MAX_CLIENT;
	}
	public void setMAX_CLIENT(int mAX_CLIENT) {
		MAX_CLIENT = mAX_CLIENT;
	}




}
