package com.tutk.IOTC;

import androidx.annotation.Keep;

/**
 * @Author: wangyj
 * @CreateDate: 2021/9/8
 * @Description:
 */
@Keep
public class St_SInfo {
    public byte 	Mode;
    public byte		CorD;
    public byte[] 	UID=new byte[21];
    public byte[]	RemoteIP=new byte[17];//RemoteIP[17]
    //char reserve1[2];
    public int		RemotePort;
    public int		TX_count;
    public int		RX_count;
    //----------------------
    public int		VID;
    public int		PID;
    public int 		GID;
    public int		IOTCVersion;
    public byte		NatType;
    public byte		isSecure;
}
