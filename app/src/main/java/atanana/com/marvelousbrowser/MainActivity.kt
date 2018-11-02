package atanana.com.marvelousbrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import atanana.com.marvelousbrowser.data.Character
import atanana.com.marvelousbrowser.utils.parseResult
import atanana.com.marvelousbrowser.web.MarvelService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val marvelService: MarvelService by inject()
    private val moshi: Moshi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val response = marvelService.characters().await()
            val characters = moshi.parseResult<Character>(response.data.resultsString)
            val names = characters.map { it.name }
        }
    }
}
