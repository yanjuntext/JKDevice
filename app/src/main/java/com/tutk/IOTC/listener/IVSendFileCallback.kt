package com.tutk.IOTC.listener

import com.tutk.IOTC.camera.SendFileStatus

/**
 * @Author: wangyj
 * @CreateDate: 2021/11/20
 * @Description: ๆไปถๅ้
 */
fun interface IVSendFileCallback {
    fun onAvChannelSendFileStatus(status:SendFileStatus,total:Int,sendTotal:Int,progress:Int)
}