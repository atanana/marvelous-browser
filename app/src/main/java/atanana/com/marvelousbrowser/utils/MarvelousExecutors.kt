package atanana.com.marvelousbrowser.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object MarvelousExecutors {
    private val mainHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    val ui = Executor {
        mainHandler.post(it)
    }

    val io = Executors.newFixedThreadPool(4)!!
}