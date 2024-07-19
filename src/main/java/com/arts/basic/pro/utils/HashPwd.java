package com.arts.basic.pro.utils;

public class HashPwd {

	public static final int HASH_INTERATIONS = 1024;

	private static final int SALT_SIZE = 8;
	/**
	 * 密码加密
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String encryptPassword(String password, String salt) {
		byte[] hashPassword = EncryptUtils.sha1(password.getBytes(), Encodes.decodeHex(salt), HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
	}
	
	public static String getEntryptPassword(String pwdKey, String pwd) {
		byte[] salt = Encodes.decodeHex(pwdKey);
		byte[] hashPassword = Digests.sha1(pwd.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}
}
