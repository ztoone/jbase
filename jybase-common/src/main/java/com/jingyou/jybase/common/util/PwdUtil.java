package com.jingyou.jybase.common.util;

import com.jingyou.jybase.common.util.security.Digests;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public class PwdUtil {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static String encryptPassword(String password) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        String saltKey="";
        if(org.apache.commons.lang3.StringUtils.isNotBlank(password)){
            saltKey = password.substring(0,16);
        }
        byte[] salt = Encodes.decodeHex(saltKey);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("zhongjingyang@jingyougroup.com"));
        String p = "7f86375b2dd218b139061dd3ae98eca3fee3448ac4470225bc25f73e";
        System.out.println(validatePassword("123456",p));
    }
}
