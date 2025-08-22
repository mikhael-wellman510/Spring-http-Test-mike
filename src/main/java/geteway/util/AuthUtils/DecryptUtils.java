package geteway.util.AuthUtils;

import lombok.experimental.UtilityClass;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@UtilityClass
public class DecryptUtils {
	private static final String SECRET_KEY = "SuperSecretKey12";
	// Todo -> ini untuk menerjemahkan enkripsi data nya
	public static String decrypt(String base64Cipher) {
		try {
			// decode Base64 dari FE
			byte[] cipherData = Base64.getDecoder().decode(base64Cipher);

			// ambil IV (12 byte pertama)
			byte[] iv = new byte[12];
			System.arraycopy(cipherData, 0, iv, 0, iv.length);

			// ambil ciphertext (sisanya)
			byte[] encryptedBytes = new byte[cipherData.length - iv.length];
			System.arraycopy(cipherData, iv.length, encryptedBytes, 0, encryptedBytes.length);

			// buat SecretKey
			SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

			// AES/GCM/NoPadding
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
			GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv); // tag length 128 bit
			cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

			byte[] decrypted = cipher.doFinal(encryptedBytes);

			return new String(decrypted, StandardCharsets.UTF_8);

		} catch (Exception e) {
			throw new RuntimeException("Decrypt error", e);
		}
	}
}
