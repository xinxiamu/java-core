package com.mu.javacv.ffmpeg.img;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 即时从视频流截取一帧图片
 */
public class JavacvScreenshotTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        FFmpegFrameGrabber fg = new FFmpegFrameGrabber("rtsp://192.168.0.114/live/mainstream");
//        FFmpegFrameGrabber fg = new FFmpegFrameGrabber("C:\\Users\\HG11\\Videos\\bb.mp4");
//        FFmpegFrameGrabber fg = FFmpegFrameGrabber.createDefault("E:\\bb.mp4");
        FFmpegLogCallback.set();
        fg.start();

        // 获取五张图
        for (int i = 0; i < 5; i++) {
            Frame f = fg.grab();
            if (f.image != null) {
                BufferedImage bi = (new Java2DFrameConverter()).getBufferedImage(f);
                String imgFileName = String.format("E:\\a-%s.png", i);
                File file = new File(imgFileName);
                ImageIO.write(bi, "png", file);
            }
//            Thread.sleep(1000/视频帧率);
            Thread.sleep(1000 / 30);
        }

        //一张图片
        /*Frame f = fg.grab(); //抓取最新视频帧或者音频帧
        if (f.image != null) {
            BufferedImage bi = (new Java2DFrameConverter()).getBufferedImage(f);
            File file = new File("E:\\aa.png");
            ImageIO.write(bi, "png", file);
        }*/

    }

    @Test
    public void test2() throws IOException {
        File file = new File("E:\\bb.png");
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("rtsp://192.168.0.114/live/mainstream");
//        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("E:\\bb.mp4");
        FFmpegLogCallback.set();
        grabber.start();

        int lengthFrame = grabber.getLengthInFrames();
        int i = 0;
        Frame frame = grabber.grabImage();
        while (i < lengthFrame) {
            frame = grabber.grabImage();
            if (i > 1000 && frame.data != null) {
                break;
            }
            i++;
        }
        BufferedImage bufferedImage = toImg(frame);
        ImageIO.write(bufferedImage, "png", file);

        grabber.flush();
        grabber.close();
    }

    public static BufferedImage toImg(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(frame);
        return bi;
    }
}
