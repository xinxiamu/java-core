package com.mu.javacv.ffmpeg.record;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

public class FrameRecord {

    public static void main(String[] args) throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception {
        /*new Runnable() {
            @Override
            public void run() {
                String inputFile = "rtsp://192.168.0.103/live/mainstream";
                String outputFile = "E:\\rr.mp4";
                try {
                    frameRecord(inputFile, outputFile, 1);
                } catch (FrameGrabber.Exception e) {
                    throw new RuntimeException(e);
                } catch (FFmpegFrameRecorder.Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }.run();*/

        String a = "202201/220101083045/1#220101/P02.jpg";
        System.out.println();
        int i = Integer.parseInt("P42".substring(1));
        System.out.println(String.format("%02d", i + 1));
    }

    public static void frameRecord(String inputFile, String outputFile, int audioChannel) throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception {
        boolean isStart = true; //控制录制结束，最好作为全局变量
        //获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        //流媒体输出地址，分辨率（长、高），是否录制（0-不录制；1-录制）
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, 1280, 720, audioChannel);
        //开始录制视频
        recordByFrame(grabber, recorder, isStart);
    }

    public static void recordByFrame(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder, Boolean status) throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception {
        try {
            grabber.start();
            recorder.start();
            Frame frame = null;
            int i = 0;
            while (status && (frame = grabber.grabFrame()) != null) {
                if (i > 100) { //录制一百帧就跳出
                    break;
                }
                recorder.record(frame);
                i++;
            }
            recorder.stop();
            grabber.stop();
        } finally {
            if (grabber != null) {
                grabber.stop();
            }
        }
    }
}
