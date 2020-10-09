package smartrecycleradapter.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class AssetsUtils {
    companion object {
        @Throws(IOException::class)
        inline fun <reified T : Any> loadStyleFromAssets(context: Context, fileName: String): T {
            return loadStyle(context.assets.open(fileName))
        }

        @Throws(IOException::class)
        inline fun <reified T : Any> loadStyleFromFile(file: File): T {
            return loadStyle(FileInputStream(file))
        }

        @Throws(IOException::class)
        inline fun <reified T : Any> loadStyle(inputStream: InputStream): T {
            return Gson().fromJson(InputStreamReader(inputStream), object : TypeToken<T>() {}.type)
        }
    }
}