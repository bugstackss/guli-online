package com.macro.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse.PlayInfo;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @auther macro
 * @description 测试获取视频播放地址和凭证
 * @date 2023/12/22 14:17
 */
public class TestVod {

    public static void main(final String[] args) throws Exception {

        /* 请填写您的AccessKeyId */
        final String accessKeyId = "LTAI5tNdeDGoFfuMFLjjA53T";
        final String accessKeySecret = "BhMNXVnC3aybRDHOnLolzc3SzSMaWc"; // 请填写您的AccessKeySecret
        final String title = "6 - What If I Want to Move Faster - upload by sdk"; // 上传之后文件名称
        final String fileName = "C:\\Users\\macro\\Desktop\\6 - What If I Want to Move Faster.mp4"; // 本地文件路径和名称

        // 上传视频的方法
        final UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        //request.setEnableCheckpoint(false);
        /* OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位: 毫秒，默认为300000毫秒*/
        //request.setSlowRequestsThreshold(300000L);
        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
        //request.setSlowRequestsThreshold(300000L);
        /* 是否显示水印(可选)，指定模板组ID时，根据模板组配置确定是否显示水印*/
        //request.setIsShowWaterMark(true);
        /* 自定义消息回调设置(可选)，参数说明参考文档 https://help.aliyun.com/document_detail/86952.html#UserData */
        // request.setUserData("{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 工作流ID(可选) */
        //request.setWorkflowId("d4430d07361f0*be1339577859b0177b");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        //request.setPrintProgress(false);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        //request.setProgressListener(new PutObjectProgressListener());
        /* 设置您实现的生成STS信息的接口实现类*/
        // request.setVoDRefreshSTSTokenListener(new RefreshSTSTokenImpl());
        /* 设置应用ID*/
        //request.setAppId("app-1000000");
        /* 点播服务接入点 */
        //request.setApiRegionId("cn-shanghai");
        /* ECS部署区域*/
        // request.setEcsRegionId("cn-shanghai");
        final UploadVideoImpl uploader = new UploadVideoImpl();
        final UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 根据视频id获取视频播放凭证
     *
     * @throws ClientException ClientException异常
     */
    public static void getPlayAuth() throws ClientException {
        // 初始化对象
        final DefaultAcsClient client =
                InitObject.initVodClient("LTAI5tNdeDGoFfuMFLjjA53T", "BhMNXVnC3aybRDHOnLolzc3SzSMaWc");
        // 创建获取凭证的request和response
        final GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        // 设置视频id
        request.setVideoId("706ad74ba3b971ee80456732b78e0102");
        // 调用初始化对象里面的方法传递request,获取数据
        response = client.getAcsResponse(request);
        System.out.println("PlayAuth: " + response.getPlayAuth());
    }

    /**
     * 根据视频id获取视频播放地址
     *
     * @throws ClientException ClientException异常
     */
    public static void getPlayUrl() throws ClientException {
        // 1.根据视频id获取视频播放地址
        // 创建初始化对象
        final DefaultAcsClient client =
                InitObject.initVodClient("LTAI5tNdeDGoFfuMFLjjA53T", "BhMNXVnC3aybRDHOnLolzc3SzSMaWc");

        // 创建获取视频地址request和response
        final GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        // 向request对象里面设置视频id
        request.setVideoId("706ad74ba3b971ee80456732b78e0102"); // 视频id

        // 调用初始化对象里面的方法传递request,获取数据
        response = client.getAcsResponse(request);

        final List<PlayInfo> playInfoList = response.getPlayInfoList();
        // 播放地址
        for (final PlayInfo playInfo : playInfoList) {
            System.out.println("PlayInfo.PlayURL：" + playInfo.getPlayURL());
        }
        // BASE信息
        System.out.println("PlayInfo.BaseInfo.Title：" + response.getVideoBase().getTitle());
    }

}
