package hr.algebra.lmandic.procvat

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.lmandic.procvat.framework.applyAnimation

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startAnimations()
        //redirect()
    }

    private fun startAnimations() {
        val ivSplash = findViewById<ImageView>(R.id.ivSplash)
        ivSplash.applyAnimation(R.anim.scale)
        ivSplash.applyAnimation(R.anim.blink)


    }

    private fun redirect() {
        TODO("Not yet implemented")
    }
}