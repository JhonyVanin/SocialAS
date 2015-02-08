package helper;

public class Config {

    private static Config config;
    private String userId;
    private String userPassword;
    private String userPasswordMD5;

    private Config() {

    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPasswordMD5() {
        return userPasswordMD5;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        this.userPasswordMD5 = MD5(userPassword);
    }


    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception ex) {
        }
        return null;
    }
}
