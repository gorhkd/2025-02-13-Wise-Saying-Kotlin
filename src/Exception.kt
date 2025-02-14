object MyHttpStatus {
    const val OK = 200
    const val NOT_FOUND = 404
    const val BAD_REQUEST = 400
}

enum class WiseSayingErrorCode(val status: Int, val code: String, val message: String) {
    NOT_FOUND_WISE_SAYING(MyHttpStatus.NOT_FOUND, "404", "존재하지 않는 명언입니다."),
    INVALID_INPUT(MyHttpStatus.BAD_REQUEST, "400", "유효하지 않은 입력입니다.")
}

class WiseSayingException(val errorCode: WiseSayingErrorCode) : RuntimeException(errorCode.message) {
    fun getStatus(): Int = errorCode.status
    fun getCode(): String = errorCode.code
}