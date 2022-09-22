package com.mu.javacv.ffmpeg.streaming_chunker;

import java.io.File;

import com.mu.javacv.ffmpeg.streaming_chunker.simplified_grabber.SimplifiedGrabberWrap;
import org.bytedeco.javacv.FFmpegFrameGrabber;

/**
 *
 * @author Dmitriy Gerashenko <d.a.gerashenko@gmail.com>
 */
public class StreamingChunkerDemo {

    public static void main(String[] args) throws Exception {
//        String source = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
        String source = "rtmp://192.168.0.106/live/pigy";
        File outDir = new File("chunks");
        StreamingChunker fFmpegChunker = new StreamingChunker(() -> {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(source);
            grabber.setOption("stimeout", "10000000");
            //FFmpegFrameGrabber grabber1 = new FFmpegFrameGrabber(source);
            //grabber.setOption("stimeout", "10000000");
            return new SimplifiedGrabberWrap(grabber);
            //return new FFmpegVideoWrap(grabber);
            //return new FFmpegAudioWrap(grabber);
            //return new FFmpegMixerWrap(grabber, grabber1);
        }, outDir);

        fFmpegChunker.setChunkHandler((chunkFile) -> {
            System.out.println(chunkFile);
        });
        fFmpegChunker.start();
        while (fFmpegChunker.next()) {
        }
        fFmpegChunker.stop();
    }
}
