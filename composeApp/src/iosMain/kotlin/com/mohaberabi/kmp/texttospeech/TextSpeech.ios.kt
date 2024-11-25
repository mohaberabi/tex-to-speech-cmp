package com.mohaberabi.kmp.texttospeech

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance

actual class TextSpeech {
    private val synthesizer = AVSpeechSynthesizer()
    private var currentLanguage = TtsLanguage.English.label
    private var currentVoice: AVSpeechSynthesisVoice? = null
    actual fun speech(text: String) {
        val utterance = AVSpeechUtterance(string = text).apply {
            voice = currentVoice ?: AVSpeechSynthesisVoice.voiceWithLanguage(currentLanguage)
            rate = 0.5f
        }
        synthesizer.speakUtterance(utterance)
    }

    actual fun setLanguage(lang: TtsLanguage) {
        currentLanguage = lang.label
        currentVoice = AVSpeechSynthesisVoice.voiceWithLanguage(currentLanguage)
    }

    actual fun setVoice(voice: TtsVoice) {
        val voices = AVSpeechSynthesisVoice.speechVoices() as? List<AVSpeechSynthesisVoice>
        currentVoice = voices?.find { it.identifier == voice.name }

    }


}

@Composable
actual fun rememberTts(): TextSpeech {
    return remember { TextSpeech() }
}