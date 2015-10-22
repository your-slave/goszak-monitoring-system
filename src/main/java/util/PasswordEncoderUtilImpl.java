package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Bonaparte on 25.05.2015.
 */
public class PasswordEncoderUtilImpl
{
    public  static String Encode(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
