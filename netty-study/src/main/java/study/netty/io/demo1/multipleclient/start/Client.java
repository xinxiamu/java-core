/**
 *
 */
package study.netty.io.demo1.multipleclient.start;


import study.netty.io.demo1.multipleclient.config.InitConfig;
import study.netty.io.demo1.multipleclient.netty.ClientConnect;
import study.netty.io.demo1.multipleclient.netty.LinkListener;

/**
 * @Description:
 * @version: v1.0.0
 * @author: wbl
 * @date: 2019年9月23日 上午10:10:37
 */
public class Client {

	  public static void main(String[] args) {
		    System.out.println("aaaaabbbbbccdcccccc");
	        for (int i = 0; i < InitConfig.MAX_CLIENT; i++) {
	        	try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	InitConfig.thread_num ++;
	            new Thread(new ClientConnect()).start();
	        }

	        new Thread(new LinkListener()).start();

	    }
}
