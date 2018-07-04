package der.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by dev2 on 2018/5/15.
 */
public class FileChannelRead {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("test.txt");

        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer);
        byte[] bytes = new byte[byteBuffer.position()];
        byteBuffer.flip();
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        fileChannel.close();
        fileInputStream.close();
    }
}
