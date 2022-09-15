/**
 *
 */
package study.netty.io.demo1.multipleclient.netty;

import study.netty.io.demo1.multipleclient.config.InitConfig;

/**
 * @Description:
 * @version: v1.0.0
 * @author: wbl
 * @date: 2019年9月23日 下午2:38:41
 */
public class LinkListener implements Runnable{

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(10000);
				System.out.println("0~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Local_listener_main:发起连接线程数："+ InitConfig.thread_num);
				System.out.println("Local_listener_connect:发起连接客户端个数："+ClientConnect.localnum);
				System.out.println("Local_listener_client_num:收到确认客户端个数："+InitConfig.client_num);//ClientConnect
				System.out.println("Local_listener_client_num:活跃客户端个数："+InitConfig.client_active);//ClientConnect
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~1");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
