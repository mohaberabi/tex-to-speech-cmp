package com.mohaberabi.kmp.texttospeech

enum class TtsVoice() {

    Default,
    Male,
    Female,
}

enum class TtsLanguage(
    val label: String,
) {
    English("en-US"),
    Arabic("ar-SA")
}
