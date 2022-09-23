package com.mu.javacv.ffmpeg.record;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

import java.io.File;
import java.io.IOException;

/**
 * 直播流保存本地线程
 **/
public class RecordVideoThread implements Runnable {

//    private String stream_url = "rtmp://192.168.0.106/live/123";// 流地址 例如：rtmp://58.200.131.2:1935/livetv/hunantv 湖南卫视
    private String stream_url = "rtsp://192.168.0.114/live/mainstream";//
    private Long times_sec = 0L;// 停止录制时长 0为不限制时长
    private String out_file_path;//输出路径
    private String file_format = "mp4";//录制的文件格式
    private boolean is_audio = false;//是否录制声音

    @Override
    public void run() {
        // 获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(stream_url);
        FFmpegFrameRecorder recorder = null;
        try {
            FFmpegLogCallback.set();
            grabber.start();
            Frame frame = grabber.grabFrame();
            if (frame != null) {
                //保存到本地的文件
                File outFile = new File(out_file_path);
                // 如果文件不存在或者不是一个文件 则根据文件的路径创建一个文件
                if (out_file_path.isEmpty() || !outFile.exists() || outFile.isFile()) {
                    outFile.createNewFile();
                } else {
                    System.out.println("输出文件无法创建");
                    return;
                }
                // 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
                recorder = new FFmpegFrameRecorder(out_file_path, frame.imageWidth, frame.imageHeight, is_audio ? 1 : 0);
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);//直播流格式
                recorder.setFormat(file_format);//录制的视频格式
                recorder.setFrameRate(25);//帧数
                recorder.start();//开始录制
                // 计算结束时间
                long endTime = System.currentTimeMillis() + times_sec * 1000;
                // 如果没有到录制结束时间并且获取到了下一帧则继续录制
                while ((System.currentTimeMillis() < endTime) && (frame != null)) {
                    recorder.record(frame);//录制
                    frame = grabber.grabFrame();//获取下一帧
                }
                recorder.record(frame);
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //停止录制
            if (null != grabber) {
                try {
                    grabber.stop();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
            if (recorder != null) {
                try {
                    recorder.stop();
                } catch (FrameRecorder.Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("录制完成，录制时长：" + times_sec + "秒(0为没有限制录制时长)");
        }
    }

    public static void main(String[] args) {
        RecordVideoThread recordVideoThread = new RecordVideoThread();
        recordVideoThread.out_file_path = "D://1.mp4";
        recordVideoThread.times_sec = 10L;// 最好设置结束时长 如直接停止程序会造成输出文件的损坏无法正常播放
        recordVideoThread.is_audio = false;
        new Thread(recordVideoThread).start();
    }
}

