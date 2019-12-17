package base.support;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class PasswordEncoderUtils {

    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static String passwordEncode(String input) {
        return passwordEncoder.encode(input);
    }

    public static boolean matches(String rawPassword, String encodePassword) {
        return passwordEncoder.matches(rawPassword, encodePassword);
    }
}
