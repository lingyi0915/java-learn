package com.hjh.java.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author hjh
 * @date 2019/11/6
 *     <p>Channels 对应stream 抽象 Buffers 缓存 数据落地的地方 Selectors 选择器 选择Channels进行处理
 */
public class NIODemo {
  public static void main(String[] args) {
    String path = "D:\\workspace\\workspace for hjh\\java-learn\\src\\main\\data\\";
    String fileName = "nio-data.txt";
    RandomAccessFile aFile = null;
    try {
      aFile = new RandomAccessFile(path + fileName, "rw");
      FileChannel inChannel = aFile.getChannel();
      ByteBuffer buf = ByteBuffer.allocate(48);
      int bytesRead = inChannel.read(buf);
      while (bytesRead != -1) {
        System.out.println("Read " + bytesRead);
        buf.flip();
        while (buf.hasRemaining()) {
          System.out.print((char) buf.get());
        }
        buf.clear();
        bytesRead = inChannel.read(buf);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        aFile.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
