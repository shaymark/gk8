package com.markoapps.testserviceproject.utils



import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class EncryptionUtil {

    fun decryptECB256(secretKeySpec: String, encrypted: String): String? {
        try {
            val skeySpec = SecretKeySpec(secretKeySpec.toByteArray(), "AES")
            val cipher: Cipher = Cipher.getInstance("AES/ECB/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec)
            val original: ByteArray = cipher.doFinal(hexStringToByteArray(encrypted))
            return String(original)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    /* s must be an even-length string. */
    private fun hexStringToByteArray(s: String): ByteArray? {
        val len = s.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                    + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }
}