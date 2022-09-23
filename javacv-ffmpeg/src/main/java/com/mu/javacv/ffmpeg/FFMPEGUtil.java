package com.mu.javacv.ffmpeg;

import org.bytedeco.ffmpeg.avcodec.*;
import org.bytedeco.ffmpeg.avformat.*;
import org.bytedeco.ffmpeg.avutil.*;
import org.bytedeco.ffmpeg.swscale.*;
import org.bytedeco.javacpp.BytePointer;

import java.io.File;
import java.io.IOException;

import static org.bytedeco.ffmpeg.global.avcodec.*;
import static org.bytedeco.ffmpeg.global.avformat.*;
import static org.bytedeco.ffmpeg.global.avutil.*;
import static org.bytedeco.ffmpeg.global.swscale.*;


public class FFMPEGUtil {

    /**
     * 获取播放时间
     * @param videoDuration
     * @return
     */
    static String getPlayTime(long videoDuration){
        long duration = videoDuration + 5000;
        long hours, mins, secs, us;
        secs = duration / AV_TIME_BASE;
        us = duration % AV_TIME_BASE;
        mins = secs / 60;
        secs %= 60;
        hours = mins/ 60;
        mins %= 60;
        return hours+":"+mins+":"+secs+":"+ (100 * us) / AV_TIME_BASE;
    }


    /**
     * 获取视频信息
     * @param videoStream
     */
    static void getVideoInfo(AVStream videoStream){
        //宽度
        int width=videoStream.codecpar().width();
        //高度
        int height=videoStream.codecpar().height();
        //帧率
        double fps=av_q2d(videoStream.avg_frame_rate());
        System.out.println("width="+width+","+"height="+height+",fps="+fps);
    }


    public static void main(String[] args)throws Exception {

        //图片输出宽度
        int outWidth=1024;
        //图片输出高度
        int outHeight=768;
        //截取起始时间，单位秒
        int minute=30;
        int cutStartTime=minute*60;

        //解视频封装
        AVFormatContext avFormatContext=avformat_alloc_context();
//        String url="鹿鼎记2：神龙教BD国粤双语中字.mp4";
        String url="rtsp://192.168.0.114/live/mainstream";
        avformat_open_input(avFormatContext,url,null,null);
        avformat_find_stream_info(avFormatContext,(AVDictionary)null);


        //获取视频索引
        int videoStreamIndex=av_find_best_stream(avFormatContext,AVMEDIA_TYPE_VIDEO,-1,-1,(AVCodec)null,0);

        //获取视频流
        AVStream videoAvStream=avFormatContext.streams(videoStreamIndex);
        getVideoInfo(videoAvStream);
        System.out.println("total time:"+getPlayTime(avFormatContext.duration()));
        //查找对应视频解码器
        AVCodec videoCodec=avcodec_find_decoder(avFormatContext.streams(videoStreamIndex).codecpar().codec_id());
        AVCodecContext videoCodecCtx=avcodec_alloc_context3(videoCodec);
        avcodec_parameters_to_context(videoCodecCtx, avFormatContext.streams(videoStreamIndex).codecpar());
        avcodec_open2(videoCodecCtx,videoCodec,(AVDictionary)null);




        //初始化解码后视频桢对象
        AVFrame frame = av_frame_alloc();
        //初始化压缩包对象
        AVPacket pkt = av_packet_alloc();

        //初始化转换桢，此对象用于原始码率转换，并且转换每一帧的图片大小
        AVFrame pFrameRGB = av_frame_alloc();

        //初始化像素格式转换的上下文
        SwsContext videoSwsCtx = null;

        //---初始化像素转换的存储对象
        int numBytes = av_image_get_buffer_size(AV_PIX_FMT_YUVJ420P, outWidth,
                outHeight, 1);
        BytePointer buffer = new BytePointer(av_malloc(numBytes));
        av_image_fill_arrays(pFrameRGB.data(), pFrameRGB.linesize(),
                buffer, AV_PIX_FMT_YUVJ420P, outWidth,outHeight, 1);

        boolean flag=true;
        int index=0;

        //设置截取视频其实位置
        Long  pos= new Double(cutStartTime/av_q2d(videoAvStream.time_base())).longValue();
        av_seek_frame(avFormatContext,videoStreamIndex,pos,AVSEEK_FLAG_BACKWARD );


        while(flag){

            //截取10张图片就退出
            if(index==10){
                break;
            }

            //开始获取视频数据,将解复用的数据存储到AVPacket对象
            int re = av_read_frame(avFormatContext,pkt);

            //读取到最后就退出
            if(re != 0)
            {
                flag=false;
                break;
            }



            //视频流，使用视频解码器
            AVCodecContext temp = null;
            if(pkt.stream_index()==videoStreamIndex){
                temp=videoCodecCtx;

            }else { //音频流跳过
                av_packet_unref(pkt);
                continue;
            }

            //显示时间戳
            long pts = pkt.pts();
            //解码时间戳
            long dts = pkt.dts();
            //标志，其中最低为1表示该数据是一个关键帧
            int flags = pkt.flags();


            //数据的时长，以所属媒体流的时间基准为单位
            long duration = pkt.duration();
            //data的大小
            int size = pkt.size();

            //计算出这一桢在视频中的位置 播放时刻值：timestamp(单位秒)
            double now_time=(pts*av_q2d(videoAvStream.time_base()));
            //播放时长值：duration(单位秒)
            double playDuration=duration*av_q2d(videoAvStream.time_base());

            System.out.println("pts="+pts+",dts="+dts+",flags="+flags+",duration="+duration+",size="+size+",now_time="+now_time+",playDuration="+playDuration);

            //发送到线程中解码
            re = avcodec_send_packet(temp,pkt);
            //清理
            av_packet_unref(pkt);
            if(re != 0)
            {
                System.out.println("avcodec_send_packet failed!");
                continue;
            }

            while (true){

                //接收到解码一帧数据
                re = avcodec_receive_frame(temp,frame);
                if(re !=0)  break;
                //如果是视频帧
                if(temp == videoCodecCtx)
                {
                    double second=frame.pts()*av_q2d(videoAvStream.time_base());
                    if(second<=cutStartTime){
                        break;
                    }
                    System.out.println(second);
                    videoSwsCtx = sws_getCachedContext(videoSwsCtx,
                            frame.width(),
                            frame.height(),
                            frame.format(),
                            outWidth,
                            outHeight,
                            AV_PIX_FMT_YUVJ420P,
                            SWS_FAST_BILINEAR,
                            null,null, (double[]) null
                    );

                    sws_scale(videoSwsCtx,frame.data(),frame.linesize(),0,frame.height(),pFrameRGB.data(),pFrameRGB.linesize());
                    pFrameRGB.width(outWidth);
                    pFrameRGB.height(outHeight);
                    pFrameRGB.format(AV_PIX_FMT_YUVJ420P);
                    save_frame(pFrameRGB,index);
                    index++;
                    cutStartTime=cutStartTime+50;
                    pos= new Double(cutStartTime/av_q2d(videoAvStream.time_base())).longValue();
                    av_seek_frame(avFormatContext,videoStreamIndex,pos,AVSEEK_FLAG_BACKWARD );
                    break;
                }


            }



        }

        av_free(pFrameRGB);
        av_free(frame);
        sws_freeContext(videoSwsCtx);
        avcodec_free_context(videoCodecCtx);
        avformat_close_input(avFormatContext);
    }


    /**
     * 存储图片,音视频封装操作
     * @param pFrame
     * @param count
     * @throws IOException
     */
    static void save_frame(AVFrame pFrame,int count) throws IOException {

        int width=pFrame.width();
        int height=pFrame.height();

        AVFormatContext pFormatCtx = avformat_alloc_context();
        pFormatCtx.oformat(av_guess_format("mjpeg", null, null)) ;
        AVIOContext pb = new AVIOContext();
//        String saveUrl=System.getProperty("user.dir")+File.separator+count+".jpeg";
        String saveUrl="E:\\" +count+".jpeg";
        avio_open(pb, saveUrl, AVIO_FLAG_READ_WRITE);
        pFormatCtx.pb(pb);
        AVStream pAVStream = avformat_new_stream(pFormatCtx, null);

        AVCodec pCodec = avcodec_find_encoder(pFormatCtx.oformat().video_codec());


        AVCodecContext pCodeCtx  = avcodec_alloc_context3(pCodec);
        pCodeCtx.time_base().num(1);
        pCodeCtx.time_base().den(25);
        pCodeCtx.pix_fmt(AV_PIX_FMT_YUVJ420P);
        pCodeCtx.codec_type(AVMEDIA_TYPE_VIDEO);
        pCodeCtx.width(width);
        pCodeCtx.height(height);
        pCodeCtx.codec_id(pFormatCtx.oformat().video_codec());
        avcodec_open2(pCodeCtx, pCodec, (AVDictionary)null);

        avcodec_parameters_from_context(pAVStream.codecpar(), pCodeCtx);

        avformat_write_header(pFormatCtx,(AVDictionary)null);

        int y_size = width * height;
        AVPacket pkt=new AVPacket();
        av_new_packet(pkt, y_size);
        int ret=avcodec_send_frame(pCodeCtx, pFrame);
        if(ret<0){
            System.out.println("ret=="+ret);
        }
        avcodec_receive_packet(pCodeCtx, pkt);
        av_write_frame(pFormatCtx, pkt);
        av_packet_unref(pkt);
        av_write_trailer(pFormatCtx);

        avcodec_close(pCodeCtx);
        avio_close(pFormatCtx.pb());
        avformat_free_context(pFormatCtx);
    }

}

