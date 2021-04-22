package com.markoapps.testserviceproject

import com.markoapps.testserviceproject.utils.Constans
import com.markoapps.testserviceproject.utils.EncryptionUtil
import org.junit.Test

class EncyptionUtilTest {

    @Test
    fun isEncryped(){

        val encryped = "FAE8A9CBAECFBD34AE6CF9DB33A1C9EF573FA96A4E489E076E76AC6A564172AC5C0F4CC57B1A5FCA839F7B048A12A8C00C9A834D4EBBF516DB01DCB2EFD1100C58B9BE662E1069A3A20BA78FCAFF31B8"

        val encryptionUtil = EncryptionUtil()

        val result = encryptionUtil.decryptECB256(Constans.secretKeySpec, encryped)

        assert(result == "It’s only after we’ve lost everything that we’re free to do anything.\u0005\u0005\u0005\u0005\u0005")
    }
}