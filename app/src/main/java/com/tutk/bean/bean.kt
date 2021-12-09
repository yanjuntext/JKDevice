package com.tutk.bean

import com.tutk.IOTC.AVIOCTRLDEFs
import com.tutk.IOTC.status.*


/**
 * @Author: wangyj
 * @CreateDate: 2021/12/3
 * @Description: 设备端返回的数据
 */

open class TBean(val cmd: Int, val result: Int)

/**
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GETSUPPORTSTREAM_RESP]
 */
data class TSupportStream(val index: Int, val channel: Int)

/**
 * 设置移动侦测
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_SETMOTIONDETECT_RESP]
 * @param result 设置结果  0: success; otherwise: failed.
 */
data class TSetMotionDetect(val result: Boolean)

/**
 *设备信息
 * [com.tutk.IOTC.AVIOCTRLDEFs.IOTYPE_USER_IPCAM_DEVINFO_RESP]
 * @param model 型号
 * @param vender 厂商
 * @param version 版本号
 * @param sdcardState TF卡状态
 * @param total TF卡总容量 MB
 * @param free TF卡空闲容量 MB
 */
data class TDeviceInfo(
    val model: String?, val vender: String?, val version: String?,
    val sdcardState: SDCardStatus, val total: Int, val free: Int
)

/**
 * 扫描WIFI
 * @param total 总条数
 * @param list wifi信息
 */
data class TScanWifi(val total: Int, val list: MutableList<TWifiInfo>)

/**
 *WIFI信息
 * @param ssid wifi名称
 * @param mode
 * @param enctype 加密类型
 * @param signal 信号轻度 （如果值大于0 ，则是实际的信号强度+110）
 * @param status 连接状态
 */
data class TWifiInfo(
    val ssid: String?,
    val mode: Int,
    val enctype: Int,
    val signal: Int,
    val status: WifiStatus
)

/**
 * 录像模式
 * [com.tutk.IOTC.AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GETRECORD_RESP]
 * @param result
 * @param mode 录像模式
 */
data class TRecordMode(val result: Int, val mode: RecordMode?)

/**
 * 录像模式 支持定时录像
 * [com.tutk.IOTC.AVIOCTRLDEFs.IOTYPE_USER_IPCAM_SET_SCHEDULE_REOCRD_SEC_REQ]
 * @param type 方式 0是获取  1是设置
 * @param mode 录像方式
 * @param limitTime 录像文件时长
 * @param scheduleIndex
 * @param startTime 开始时间
 * @param endTime 结束时间
 */
data class TRecordModeWithTime(
    val type: Int, val mode: RecordMode, val limitTime: Int,
    val scheduleIndex: Int, val startTime: Int, val endTime: Int
)

/**
 * 录像质量
 * [com.tutk.IOTC.AVIOCTRLDEFs.IOTYPE_RECORD_SETTING_REQ]
 * @param type
 * @param cmd
 * @param quality 录像质量
 * @param cycle 循环录像
 */
data class TRecordQuality(
    val type: Int,
    val cmd: Int,
    var quality: RecordQuality,
    var cycle: Boolean
)

/**
 * 录像事件
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_LISTEVENT_REQ]
 * @param channel
 * @param total 录像总个数
 * @param index 第几包 package index, 0,1,2... because avSendIOCtrl() send package up to 1024 bytes one time, you may want split search results to serveral package to send.
 * @param end 是否结束
 * @param count 当前包的视频个数
 */
data class TRecordVideoInfo(
    val channel: Int,
    val total: Int,
    val index: Int,
    val end: Boolean,
    val count: Int,
    val video: MutableList<TEvent>
)

/**
 * 事件
 * @param time 事件时间
 * @param event 事件类型
 * @param status // 0x00: Recording file exists, Event unreaded
 *              // 0x01: Recording file exists, Event readed
 *             // 0x02: No Recording file in the event
 */
data class TEvent(val buf: ByteArray, val time: Long, val event: Int, val status: Int)

/**
 * 设备时区
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GET_TIMEZONE_RESP]
 * @param size the following package size in bytes, should be sizeof(SMsgAVIoctrlTimeZone)
 * @param supportTimeZone 是否支持  device is support TimeZone or not, 1: Supported, 0: Unsupported.
 * @param gmtDiff 当前时区与零时区偏移事件 the difference between GMT in hours
 */
data class TTimeZone(var size: Int, var supportTimeZone: Boolean, var gmtDiff: Int)

/**
 * 设备推送地址
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_PUSHSERVER_ADDR_SETTING_REQ]
 * @example 120.24.215.192:8088/OuCamRaise
 * @param type 类型 0-GET, 1-SET
 * @param result 结果 0-ok, orthers-failed
 * @param ip   120.24.215.192
 * @param port  8088
 * @param path  OuCamRaise
 */
data class TDevicePushServiceUrl(
    val type: Int,
    val result: Int,
    val ip: String,
    val port: Int,
    val path: String
)

/**
 * 指示灯状态
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_THIRDPART_SETTING_RESP]
 * @param type 1-Get 0-Set
 * @param result 0-Success other-Fail
 * @param status
 */
data class TLedStatus(val type: Int, val result: Int, val status: Boolean)

/**
 * 设备视频状态
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GET_CAMERA_RESP]
 * @param mode
 * @param value
 */
data class TCameraStatus(val mode: CameraVideoMode, val value: Int)

/**
 * 夜视灯状态
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_SET_IRLED_RESP]
 * @param type 0-Get 1-Set
 * @param status
 */
data class TIRLedStatus(val type: Int, val status: IrLedStatus)

/**
 * 获取视频镜像
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GET_VIDEOMODE_RESP]
 */
data class TGetVideoMirror(val channel: Int, var mode: VideoMirrorMode)

/**
 * 设置视频镜像
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_SET_VIDEOMODE_RESP]
 * @param channel
 * @param success
 */
data class TSetVideoMirror(val channel: Int, val success: Boolean)

/**
 * 格式化SDCard
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_FORMATEXTSTORAGE_RESP]
 * @param storage //Storage index (ex. sdcard slot = 0, internal flash = 1, ...)
 * @param result  // 0: success // -1: format command is not supported. // otherwise: failed.
 */
data class TFormatSdCard(val storage: Int, val result: Boolean)

/**
 * TFCard 录像回放
 * [AVIOCTRLDEFs.IOTYPE_USER_IPCAM_RECORD_PLAYCONTROL_RESP]
 * @param type 回放状态
 * @param channel 回放结果  [type]==[PlaybackStatus.START] channel是通道，取值范围[0,31]  [channel]>=0   real channel no used by device for playback
 * @param time 时间  //if [type]==[PlaybackStatus.START] time is recordVideo total time (ms)
 * if [type]==[PlaybackStatus.SEEKTIME] time is Percent 50%=50
 * @param iType //if [type] is NULL
 */
data class TPlayback(val type: PlaybackStatus?, val channel: Int, val time: Int,val iType:Int)

