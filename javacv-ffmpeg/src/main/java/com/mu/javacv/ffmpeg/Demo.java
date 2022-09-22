package com.mu.javacv.ffmpeg;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_RGBA;

public class Demo {

    public static void main(String[] args) throws IOException {
//        FFmpegFrameGrabber fg = new FFmpegFrameGrabber("rtmp://192.168.0.106/live/pigy");
//        FFmpegFrameGrabber fg = new FFmpegFrameGrabber("C:\\Users\\HG11\\Videos\\bb.mp4");
        FFmpegFrameGrabber fg = FFmpegFrameGrabber.createDefault("E:\\bb.mp4");
        fg.setPixelFormat(AV_PIX_FMT_RGBA);
        fg.start();

        /*while(true) {
            Frame f = fg.grab();
            if(f.image!=null) {
                label.setIcon(new ImageIcon((new Java2DFrameConverter()).getBufferedImage(f)));
            }
//            Thread.sleep(1000/视频帧率);
            Thread.sleep(1000/30);
        }*/

        Frame f = fg.grab(); //抓取最新视频帧或者音频帧
        BufferedImage bi = (new Java2DFrameConverter()).getBufferedImage(f);
        File file = new File("E:\\pigy.png");
        ImageIO.write(bi, null, file);
        /*if (f.image != null) {

        }*/

    }
}
