package com.syz.cloud.simpleconssumermovie.utils;

import java.io.*;
/**
 *之后我们需要定义JavaBean对象怎么序列化和反序列化，我们使用ObjectOutputStream和ObjectInputStream实现。（大家也可以考虑更高效的序列化方法）
 */
public class BeanUtils {
    private BeanUtils() {}
    /**
     * 对象序列化为byte数组
     *
     * @param obj
     * @return
     */
    public static byte[] bean2Byte(Object obj) {
        byte[] bb = null;
        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArray)){
            outputStream.writeObject(obj);
            outputStream.flush();
            bb = byteArray.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bb;
    }
    /**
     * 字节数组转为Object对象
     *
     * @param bytes
     * @return
     */
    public static Object byte2Obj(byte[] bytes) {
        Object readObject = null;
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes);
             ObjectInputStream inputStream = new ObjectInputStream(in)){
            readObject = inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readObject;
    }
}
