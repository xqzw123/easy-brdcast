package com.comtom.brdcast.common.natives;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.comtom.brdcast.common.natives.entity.*;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * JNA框架DLL动态库读取调用示例类
 * @ClassName: DllCall
 * @Description: 读取调用DLL动态库文件中的方法

 */
@Slf4j
public class BrdNativeUtil {

    private static void logRes(String method,String req, String res){
        log.debug("native method: {},req: {},res: {}", method,res);
    }

    /**
     * 初始化设备列表
     * @param
     * @return
     */
    public static NativeResp sdkInit(Map<String,List<NativeDevInitReq>> reqs){
        String res = BrdNative.INSTANCE.BC_SDKInit(JSONObject.toJSONString(reqs));
        logRes("BC_SDKInit",JSONObject.toJSONString(reqs),res);
        return JSONObject.parseObject(res,NativeResp.class);
    }

    /**
     * 释放资源
     */
    public static void sdkFIni(){
        BrdNative.INSTANCE.BC_SDKFini();
    }


    public static NativeResp setProdutKey(String date){
        String res = BrdNative.INSTANCE.BC_SetProductKey(date);
        logRes("BC_SetProductKey",date,res);
        return JSONObject.parseObject(res,NativeResp.class);
    }
    public static NativeResp setDevConfig(Map<String,List<NativeDevInitReq>> reqs){
        String res = BrdNative.INSTANCE.BC_SetDevConfig(JSONObject.toJSONString(reqs));
        logRes("BC_SetDevConfig",JSONObject.toJSONString(reqs),res);
        return JSONObject.parseObject(res,NativeResp.class);
    }

    public static List<NativeDevInfo> getDevices(List<String> reqs){
        String res = BrdNative.INSTANCE.BC_GetDevice(JSONObject.toJSONString(reqs));
        logRes("BC_GetDevice",JSONObject.toJSONString(reqs),res);
        return JSONArray.parseArray(res,NativeDevInfo.class);
    }

    public static NativeResp delDevices(List<String> reqs){
        String res = BrdNative.INSTANCE.BC_DelDevice(JSONObject.toJSONString(reqs));
        logRes("BC_DelDevice",JSONObject.toJSONString(reqs),res);
        return JSONObject.parseObject(res,NativeResp.class);
    }

    public static NativeResp startFilePlay(NativeBrdReq reqs){
        String res = BrdNative.INSTANCE.BC_StartFilePlay(JSONObject.toJSONString(reqs));
        logRes("BC_StartFilePlay",JSONObject.toJSONString(reqs),res);
        return JSONObject.parseObject(res,NativeResp.class);
    }

    public static NativeResp stopFilePlay(String ebmId){
        String res = BrdNative.INSTANCE.BC_StopFilePlay(ebmId);
        logRes("BC_StopFilePlay",ebmId,res);
        return JSONObject.parseObject(res,NativeResp.class);
    }

    public static List<NativeBrdResp> getBCStatus(List<String> ebmIds){
        String res = BrdNative.INSTANCE.BC_GetBCStatus(JSONObject.toJSONString(ebmIds));
        logRes("BC_GetBCStatus",JSONObject.toJSONString(ebmIds),res);
        return JSONArray.parseArray(res,NativeBrdResp.class);
    }

    public interface BrdNative extends Library {
        // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
        BrdNative INSTANCE = (BrdNative) Native.loadLibrary((Platform.isWindows() ? "tts" : "c"),
                BrdNative.class);

        // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)

        /**
         * 初始化设备列表
         * @param jsonStr
         * @return
         */
        String BC_SDKInit(String jsonStr);

        /**
         * 释放资源
         * @param
         * @return
         */
        void BC_SDKFini();

        /**
         * 设置终端批次
         * @param jsonStr
         * @return
         */
        String BC_SetProductKey(String jsonStr);

        /**
         * 新增或修改设备配置
         * @param jsonStr
         * @return
         */
        String BC_SetDevConfig(String jsonStr);

        /**
         *查询设备数据
         * @param jsonStr
         * @return
         */
        String BC_GetDevice(String jsonStr);

        /**
         * 删除设备
         * @param jsonStr
         * @return
         */
        String BC_DelDevice(String jsonStr);

        /**
         * 开启文件广播
         * @param jsonStr
         * @return
         */
        String BC_StartFilePlay(String jsonStr);

        /**
         * 停止广播
         * @param jsonStr
         * @return
         */
        String BC_StopFilePlay(String jsonStr);

        /**
         * 查询文件播发状态
         * @param jsonStr
         * @return
         */
        String BC_GetBCStatus(String jsonStr);
    }

    public static void main(String[] args) {
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("rdn", 0);
//        params.put("volume", 100);
//        params.put("pitch", 50);
//        params.put("speed", 50);
//        params.put("voice_name", "xiaoyan");
//        params.put("sample_rate", 16000);
//        params.put("text_encoding", "UTF8");
//        params.put("text", "你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好");
//        params.put("filename", "G:/test.wav");
//        int text_to_speech = BrdNative.INSTANCE.text_to_speech(JSON.toJSONString(params));
//        System.out.println(text_to_speech);
    }
}