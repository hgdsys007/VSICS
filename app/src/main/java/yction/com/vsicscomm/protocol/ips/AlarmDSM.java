package yction.com.vsicscomm.protocol.ips;

import yction.com.vsicscomm.protocol.ByteBufferUnsigned;
import yction.com.vsicscomm.protocol.p808.Protocol;

import java.util.Date;

/**
 * 驾驶员状态监测系统报警信息
 */
public class AlarmDSM implements ReportExtra {
    public short Id = 0x65;

    public long 报警ID;
    public byte 标志状态;
    public byte 报警事件类型;
    public byte 报警级别;
    public byte 疲劳程度;
    //-- 保留4
    public byte 车速;
    public int 高程;
    public long 纬度;
    public long 经度;
    public Date 日期时间 = new Date();
    public int 车辆状态;
    public AlarmTag 报警标识号 = new AlarmTag();


    @Override
    public short getId() {
        return Id;
    }

    @Override
    public byte[] getBytes() {
        ByteBufferUnsigned bb = new ByteBufferUnsigned(47);
        bb.putUnsignedInt(报警ID);
        bb.raw().put(标志状态);
        bb.raw().put(报警事件类型);
        bb.raw().put(报警级别);
        bb.raw().put(疲劳程度);
        bb.raw().put((byte) 0x00);
        bb.raw().put((byte) 0x00);
        bb.raw().put((byte) 0x00);
        bb.raw().put((byte) 0x00);
        bb.raw().put(车速);
        bb.putUnsignedShort(高程);
        bb.putUnsignedInt(纬度);
        bb.putUnsignedInt(经度);
        bb.raw().put(Protocol.date2Bcd(日期时间));
        bb.putUnsignedShort(车辆状态);
        bb.raw().put(报警标识号.toBytes());
        return bb.raw().array();
    }
}
