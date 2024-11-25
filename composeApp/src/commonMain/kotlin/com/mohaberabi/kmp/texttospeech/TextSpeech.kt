package com.mohaberabi.kmp.texttospeech

import androidx.compose.runtime.Composable

expect class TextSpeech {


    fun speech(text: String)


    fun setLanguage(lang: TtsLanguage)

    fun setVoice(voice: TtsVoice)
}


@Composable
expect fun rememberTts(): TextSpeech