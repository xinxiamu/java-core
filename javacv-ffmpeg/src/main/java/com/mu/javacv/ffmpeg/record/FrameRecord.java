package com.mu.javacv.ffmpeg.record;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

public class FrameRecord {

    public static void main(String[] args) throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception {
        String inputFile = "rtsp://192.168.0.114/live/mainstream";
        String outputFile = "E:\\rr.mp4";
        frameRecord(inputFile, outputFile, 1);
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
