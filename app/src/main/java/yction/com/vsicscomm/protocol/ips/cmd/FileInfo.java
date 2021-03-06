package yction.com.vsicscomm.protocol.ips.cmd;

import yction.com.vsicscomm.protocol.ByteBufferUnsigned;
import yction.com.vsicscomm.protocol.ips.AttachmentType;
import yction.com.vsicscomm.protocol.p808.CmdReq;
import yction.com.vsicscomm.protocol.p808.MID;
import yction.com.vsicscomm.protocol.p808.Msg;
import yction.com.vsicscomm.utils.Utils;

/**
 * 4.6.3 文件信息上传
 * 消息ID：0x1211。
 * 报文类型：信令数据报文
 */
public class FileInfo extends CmdReq {
    // 文件名称
    public String fileName;
    // 文件类型
    public AttachmentType at;
    // 文件大小
    public long fileSize;

    public String filePath;
    public int fileId;

    public FileInfo() {
        super(MID.C_AlarmFileInfo);
    }


    @Override
    protected byte[] toBytes() {
        byte[] fnbs = Utils.getBytes(fileName);
        ByteBufferUnsigned bb = new ByteBufferUnsigned(6 + fnbs.length);
        bb.raw().put((byte) fnbs.length);
        bb.raw().put(fnbs);
        bb.raw().put(at.state);
        bb.putUnsignedInt(fileSize);
        return bb.raw().array();
    }
}
