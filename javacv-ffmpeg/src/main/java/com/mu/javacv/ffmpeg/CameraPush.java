package com.mu.javacv.ffmpeg;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;

import static org.bytedeco.ffmpeg.global.avcodec.av_packet_unref;

/**
 * ffmpeg RTSP转RTMP推流
 */
public class CameraPush {

    protected FFmpegFrameGrabber grabber = null;// 解码器
    protected FFmpegFrameRecorder record = null;// 编码器
    int width;// 视频像素宽
    int height;// 视频像素高
    // 视频参数
    protected int audiocodecid;
    protected int codecid;
    protected double framerate;// 帧率
    protected int bitrate;// 比特率


    // 音频参数
    private int audioChannels;
    private int audioBitrate;
    private int sampleRate;

    /**
     * 选择视频源
     *
     * @throws Exception
     */
    public void from() throws Exception {
        // 采集/抓取器（自己的推流地址）
        String rtsp ="rtsp://admin:a123456.@127.0.0.1:554/openUrl";
        System.out.println(rtsp);
        grabber = new FFmpegFrameGrabber(rtsp);
        // tcp用于解决丢包问题
        grabber.setOption("rtsp_transport", "tcp");

        // 设置采集器构造超时时间
        grabber.setOption("stimeout", "2000000");
        grabber.start();// 开始之后ffmpeg会采集视频信息，之后就可以获取音视频信息
        width = grabber.getImageWidth();
        height = grabber.getImageHeight();
        // 若视频像素值为0，说明采集器构造超时，程序结束
        if (width == 0 && height == 0) {
            System.err.println("[ERROR]   拉流超时...");
        }
        // 视频参数
        audiocodecid = grabber.getAudioCodec();
        codecid = grabber.getVideoCodec();
        framerate = grabber.getVideoFrameRate();// 帧率
        bitrate = grabber.getVideoBitrate();// 比特率
        // 音频参数
        // 想要录制音频，这三个参数必须有：audioChannels > 0 && audioBitrate > 0 && sampleRate > 0
        audioChannels = grabber.getAudioChannels();
        audioBitrate = grabber.getAudioBitrate();
        if (audioBitrate < 1) {
            audioBitrate = 128 * 1000;// 默认音频比特率
        }
    }

    /**
     * 选择输出
     *
     * @throws Exception
     */
    public void to() throws Exception {
        // 录制/推流器(自己的推流地址)
        String rtmp ="rtmp://text.text.com/live/cameraText?txSecret=123123123&txTime=213sas123";
        //未避免占用过高带宽，设置视频参数
        record = new FFmpegFrameRecorder(rtmp, 1024, 768);
        record.setVideoOption("crf", "18");// 画面质量参数，0~51；18~28是一个合理范围
        record.setGopSize(2);
        record.setFrameRate(framerate);
        record.setVideoBitrate(bitrate);

        record.setAudioChannels(audioChannels);
        record.setAudioBitrate(audioBitrate);
        record.setSampleRate(sampleRate);
        AVFormatContext fc = null;
        // 封装格式flv
        record.setFormat("flv");
        record.setAudioCodecName("aac");
        record.setVideoCodec(codecid);
        fc = grabber.getFormatContext();
        record.start(fc);
    }


    /**
     * 转封装
     *
     * @author wuguodong
     * @throws org.bytedeco.javacv.FrameGrabber.Exception
     * @throws org.bytedeco.javacv.FrameRecorder.Exception
     * @throws InterruptedException
     */
    public void go() throws Exception {
        long err_index = 0;// 采集或推流导致的错误次数
        // 连续五次没有采集到帧则认为视频采集结束，程序错误次数超过5次即中断程序
        //将探测时留下的数据帧释放掉，以免因为dts，pts的问题对推流造成影响
        grabber.flush();
        for (int no_frame_index = 0; no_frame_index < 5 || err_index < 5;) {
            try {
                // 获取没有解码的音视频帧
                AVPacket pkt = grabber.grabPacket();
                if (pkt == null || pkt.size() <= 0 || pkt.data() == null) {
                    // 空包记录次数跳过
                    no_frame_index++;
                    err_index++;
                    continue;
                }
                // 不需要编码直接把音视频帧推出去
                err_index += (record.recordPacket(pkt) ? 0 : 1);
                av_packet_unref(pkt);
            } catch (Exception e) {
                // 销毁构造器
                grabber.close();
                record.close();
                break;
            }
        }
        // 程序正常结束销毁构造器
        grabber.close();
        record.close();
    }


    /**
     * 销毁构造器
     * @throws Exception
     */
    public void stop() throws Exception {
        grabber.close();
        record.close();
    }

    public static void main(String[] args) throws Exception {
        CameraPush cameraPush = new CameraPush();
        cameraPush.from();
        cameraPush.to();
        cameraPush.go();
        Thread.sleep(5000);
        cameraPush.stop();
    }
}
