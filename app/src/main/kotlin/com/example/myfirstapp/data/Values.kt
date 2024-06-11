package com.example.myfirstapp.data

import com.example.myfirstapp.extensions.canExecuteCommand
import com.example.myfirstapp.extensions.file
import com.example.myfirstapp.extensions.hardwareContains
import com.example.myfirstapp.extensions.isStartOfBrand
import com.example.myfirstapp.extensions.isStartOfDevice
import com.example.myfirstapp.extensions.isStartOfFingerprint
import com.example.myfirstapp.extensions.manufacturerContains
import com.example.myfirstapp.extensions.modelContains
import com.example.myfirstapp.extensions.productContains
import com.example.myfirstapp.extensions.regexFromString
import com.google.gson.Gson
import java.time.LocalDate
import java.time.LocalDateTime

val gson: Gson = Gson()

val today2: LocalDate = LocalDate.now()

val runTime: Runtime = Runtime.getRuntime()

val today1: LocalDateTime = LocalDateTime.now()

val pwdRegex: Regex =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$".regexFromString()

val mailRegex: Regex =
    "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,253}[a-zA-Z0-9])?(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,253}[a-zA-Z0-9])?)*$".regexFromString()

val isDeviceRooted: Boolean =
    "/system/app/Superuser.apk".file.exists() && ("/system/xbin/which su".canExecuteCommand
            || "/system/bin/which su".canExecuteCommand
            || "which su".canExecuteCommand)

/**
 * In Development - an idea of ours was to check the if selinux is enforcing - this could be disabled for some rooting apps
 * Checking for selinux mode
 *
 * @return true if selinux enabled
 */

val isSelinuxFlagInEnabled: Boolean
    get() {
        return try {
            val c = Class.forName("android.os.SystemProperties")
            val get = c.getMethod("get", String::class.java)
            val selinux = (get.invoke(c, "ro.build.selinux") as String).toIntOrNull() ?: 0
            selinux > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

val isEmulator: Boolean =
    ("generic".isStartOfBrand() && "generic".isStartOfDevice())
            || "sdk".productContains()
            || "Emulator".modelContains()
            || "sdk_x86".productContains()
            || "vbox86p".productContains()
            || "ranchu".hardwareContains()
            || "google_sdk".modelContains()
            || "emulator".productContains()
            || "simulator".productContains()
            || "goldfish".hardwareContains()
            || "sdk_google".productContains()
            || "google_sdk".productContains()
            || "generic".isStartOfFingerprint()
            || "unknown".isStartOfFingerprint()
            || "Genymotion".manufacturerContains()
            || "sdk_gphone64_arm64".productContains()
            || "Android SDK built for x86".modelContains()