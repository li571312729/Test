package com.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * DH密钥交换算法及AES数据加密
 *
 * @author Administrator
 */
public class DHEncryUtil {

    // 大质数P的bit位数
    public static final Integer PBITLENGTH = 512;
    // 大质数P的bit位数生成元G的bit位数
    public static final Integer GBITLENGTH = 128;
    // P为一个大质数，G是一个和P相关的数，称为生成元，G可以是一个较小的数字
    public static BigInteger p = null;
    public static BigInteger g = null;

    /**
     * 客户端随机生成大质数P和另一个质数G并将之发送给服务端
     */
    public static void keyInit() {
        Random random = new Random();
        do {
            p = BigInteger.probablePrime(PBITLENGTH, random);
        } while (!p.isProbablePrime(10));     // 素数的可能大于1 - 1/(2^10)

        do {
            g = BigInteger.probablePrime(GBITLENGTH, random);
        } while (!p.isProbablePrime(10));     // 素数的可能大于1 - 1/(2^10)
    }

    /**
     * 随机生成一个1~P-2之间的整数
     */
    public static Integer getScretNumber() {
        Integer scretNumber = 0;
        if (p.compareTo(BigInteger.valueOf(100002)) > 0) {
            scretNumber = (int) (Math.random() * 100000 + 1);
        } else {
            scretNumber = (int) (Math.random() * (p.intValue() - 2) + 1);
        }
        return scretNumber;
    }

    /**
     * AES 加密
     *
     * @param content 需要加密的内容
     * @param aesKey  加密密钥
     * @return
     */
    public static byte[] aESEncrypt(String content, byte[] aesKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(aesKey));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(byteContent);
        return result; // 加密
    }


    /**
     * 解密
     *
     * @param content 待解密内容
     * @param aesKey  解密密钥
     * @return
     */
    public static byte[] aESDecrypt(byte[] content, byte[] aesKey) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(aesKey));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(content);
        return result; // 加密

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        // 初始化 P，G
        keyInit();
        System.out.println("P: " + p + "\nG: " + g);

        // 客户端生成随机数A
        Integer scretNumberA = getScretNumber();
        System.out.println("客户端生成随机数A: " + scretNumberA);

        // 服务端生成随机数B
        Integer scretNumberB = getScretNumber();
        System.out.println("服务端生成随机数B: " + scretNumberB);

        // 客户端计算可公开的数 将之发送给 服务端
        BigInteger modA = g.pow(scretNumberA).mod(p);
        System.out.println("客户端计算可公开的数: " + modA);

        // 服务端计算可公开的数 将之发送给 客户端
        BigInteger modB = g.pow(scretNumberB).mod(p);
        System.out.println("服务端计算可公开的数: " + modB);

        // 客户端计算共享密钥
        BigInteger shareSecretA = modB.pow(scretNumberA).mod(p);
        System.out.println("客户端计算共享密钥: " + shareSecretA);

        // 服务端计算共享密钥
        BigInteger shareSecretB = modA.pow(scretNumberB).mod(p);
        System.out.println("服务端计算共享密钥: " + shareSecretB);

        // 判断双方共享密钥是否相等
        System.out.println(shareSecretA.compareTo(shareSecretB));

        // 将共享密钥经过md5编码后生成AES所需 128位密钥
        byte[] secretBytes = MessageDigest.getInstance("md5").digest(shareSecretA.toString().getBytes("UTF8"));
        // 使用AES对称加密算法加密后的byte数据
        byte[] bytes = aESEncrypt("hello 中国！", secretBytes);

        // 使用AES对称加密算法解密
        byte[] bytes1 = aESDecrypt(bytes, secretBytes);
        System.out.println("AES解密后数据:" + new String(bytes1));

        // P长度
        System.out.println(p.toString(2));
        // g长度
        System.out.println(g.toString(2));
    }
}
