package com.example.yasamkocum

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

import org.commonmark.parser.Parser
import org.commonmark.renderer.text.TextContentRenderer
import org.commonmark.node.Node
import org.commonmark.renderer.html.HtmlRenderer


class MainActivity : AppCompatActivity() {
    private lateinit var sleepEditText: EditText
    private lateinit var dietEditText: EditText
    private lateinit var exerciseEditText: EditText
    private lateinit var goalsRadioGroup: RadioGroup
    private lateinit var generatePlanButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set up window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        sleepEditText      = findViewById(R.id.sleepEditText)
        dietEditText       = findViewById(R.id.dietEditText)
        exerciseEditText   = findViewById(R.id.exerciseEditText)
        goalsRadioGroup    = findViewById(R.id.goalsRadioGroup)
        generatePlanButton = findViewById(R.id.generatePlanButton)
        resultTextView     = findViewById(R.id.resultTextView)
        scrollView         = findViewById(R.id.scrollView)

        // Set up button click listener
        generatePlanButton.setOnClickListener {
            generateHealthPlan()
        }
    }

    private fun generateHealthPlan() {
        // Validate input fields
        val sleep = sleepEditText.text.toString().trim()
        val diet = dietEditText.text.toString().trim()
        val exercise = exerciseEditText.text.toString().trim()

        if (sleep.isEmpty() || diet.isEmpty() || exercise.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            return
        }

        // Determine selected goal
        val goal = when (goalsRadioGroup.checkedRadioButtonId) {
            R.id.weightLossRadio -> "kilo vermek"
            R.id.muscleGainRadio -> "kas kazanmak"
            R.id.fitnessRadio -> "genel fitness"
            else -> "genel sağlık"
        }

        val prompt = getString(R.string.prompt_str, sleep, diet, exercise, goal)
        // val prompt = """
        //     Bilimsel kaynaklara ve sağlıklı yaşam kurallarına göre bir sağlık planı oluştur:
        //
        //     Mevcut Durum:
        //     - Uyku Düzeni: $sleep
        //     - Beslenme Alışkanlıkları: $diet
        //     - Spor Aktiviteleri: $exercise
        //
        //     Hedef: $goal
        //
        //     Detaylı bir günlük plan ve tavsiyeler ver. Plan bilimsel verilere dayalı olmalı ve
        //     kullanıcının mevcut durumunu ve hedefini dikkate almalı.
        // """.trimIndent()

        // Call Gemini API
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = getString(R.string.gemini_api_key)
        )

        // Use lifecycleScope for coroutine management
        lifecycleScope.launch {
            try {
                resultTextView.text = "Plan hazırlanıyor..."
                val response = generativeModel.generateContent(prompt)

                val markdownContent = response.text.toString()
                val html = markdownToPlainText(markdownContent)
                resultTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(html)
                }
                // Gerekli bağımlılıkları ekledikten sonra



                // Scroll to bottom of results
                scrollView.post {
                    scrollView.fullScroll(View.FOCUS_DOWN)
                }
            } catch (e: Exception) {
                resultTextView.text = "Hata oluştu: ${e.localizedMessage}"
            }
        }
    }

    fun markdownToPlainText(markdown: String): String {
        val parser = Parser.builder().build()
        val node: Node = parser.parse(markdown)
        val renderer = HtmlRenderer.builder().build()
        return renderer.render(node)
    }

    fun htmlToPlainText(html: String): String {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
    }


}

// Api key: AIzaSyDQUv_4QvshXZCWyl0VZQ_h6_og0NCI-HI