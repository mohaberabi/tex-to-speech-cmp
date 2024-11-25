package com.mohaberabi.kmp.texttospeech

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

actual class TextSpeech(
    private val context: Context
) {

    private val tts by lazy {
        TextToSpeech(context) {}
    }

    init {
        tts.language = Locale.getDefault()
    }

    actual fun speech(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    actual fun setLanguage(lang: TtsLanguage) {
        tts.setLanguage(Locale.forLanguageTag(lang.label))
    }

    actual fun setVoice(voice: TtsVoice) {
        val availableVoices = tts.voices
        val selectedVoice = availableVoices.find { it.name == voice.name }
        if (selectedVoice != null) {
            tts.voice = selectedVoice
        }
    }


}

@Composable
actual fun rememberTts(): TextSpeech {
    val context = LocalContext.current

    return remember {

        TextSpeech(context)
    }
}