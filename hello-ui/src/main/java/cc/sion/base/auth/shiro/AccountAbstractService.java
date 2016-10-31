package cc.sion.base.auth.shiro;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public abstract class AccountAbstractService implements IAccountService {

    /**
     * 判断是否超级管理员.
     */
    protected boolean isSupervisor(Long id) {
        return id == 1;
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    protected String getCurrentUserName() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.loginName;
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    protected void entryptPassword(SysUser user) {
        byte[] salt = generateSalt(SALT_SIZE);
        user.setSalt(Hex.encodeHexString(salt));


        byte[] hashPassword = generateHashPassword(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Hex.encodeHexString(hashPassword));
    }

    private static byte[] generateSalt(int len){
        byte[] bytes = new byte[len];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }

    private static byte[] generateHashPassword(byte[] pwd,byte[] salt,int count){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (salt != null) {
            digest.update(salt);
        }
        byte[] result = digest.digest(pwd);
        for (int i = 1; i < count; i++) {
            digest.reset();
            result = digest.digest(result);
        }
        return result;
    }

}
