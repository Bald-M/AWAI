package com.example.awai
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.awai.PromptActivity


class SplashScreen : AppCompatActivity() {

    private val DELAY_TIME: Long = 4000

    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation
    private lateinit var imageView: ImageView
    private lateinit var appName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        imageView = findViewById(R.id.imageView2)
        appName = findViewById(R.id.app_name)

        appName.animation = topAnim
        imageView.animation = bottomAnim


        imageView.postDelayed({
            val intent = Intent(this, PromptActivity::class.java)
            intent.putExtra("navigateToPrompt", true)
            startActivity(intent)
            finish()
        }, DELAY_TIME)
    }
}