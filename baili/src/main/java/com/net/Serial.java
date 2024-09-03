package com.net;

import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;
import org.msgpack.template.Templates;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * java序列化和字节序列化对比
 * @author lxq
 * @date 2021年05月25日 15:57
 */
public class Serial {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.buildAge(1).buildSex(1).buildUserName("hello");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
        objectOutputStream.close();

        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        System.out.println("jdk serializable length is : " + bytes.length);

        System.out.println("byte array serializable length is :" + user.codeC(ByteBuffer.allocate(1024)).length);

        MessagePack messagePack = new MessagePack();
        byte[] write = messagePack.write(user);
        System.out.println("MessagePack byte serializable length is :" + write.length);

        User read = messagePack.read(write, User.class);

        List<String> list = new ArrayList<>(10);
        list.add("1");
        list.add("2");
        list.add("3");
        byte[] write1 = messagePack.write(list);
        List<String> read1 = messagePack.read(write1, Templates.tList(Templates.TString));

    }

}

@Message
class User implements Serializable {

    private static final long  serialVersionUID = 1L;

    private String name;

    private Integer age;

    private Integer sex;

    public User buildUserName(String userName){
        this.name = userName;
        return this;
    }

    public User buildAge(Integer age){
        this.age = age;
        return this;
    }

    public User buildSex(Integer sex){
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public byte[] codeC(ByteBuffer byteBuffer){
        byte[] value = this.toString().getBytes();
        byteBuffer.putInt(value.length);
        byteBuffer.put(value);
        byteBuffer.flip();
        byte[] result = new byte[byteBuffer.remaining()];
        byteBuffer.get(result);
        return result;
    }
}

