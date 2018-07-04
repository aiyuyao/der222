package der.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by dev2 on 2018/5/15.
 */
public class FileChannelWrite {
    public static void main(String[] args) throws Exception {

//        FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt",true);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        byte[] array = {'d','e','r','2','2','1','\n'};
        byteBuffer.put(array);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            fileChannel.write(byteBuffer);
        }
        fileChannel.close();
        fileOutputStream.close();
    }
}
