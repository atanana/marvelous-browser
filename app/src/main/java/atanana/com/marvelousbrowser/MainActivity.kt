package atanana.com.marvelousbrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import atanana.com.marvelousbrowser.screens.characters.CharactersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CharactersFragment.newInstance())
            .commit()
    }
}
