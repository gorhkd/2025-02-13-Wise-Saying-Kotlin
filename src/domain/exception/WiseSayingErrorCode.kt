package domain.exception

enum class WiseSayingErrorCode(val status: Int, val code: String, val message: String) {
    NOT_FOUND_WISE_SAYING(MyHttpStatus.NOT_FOUND, "404", "존재하지 않는 명령어입니다."),
    INVALID_INPUT(MyHttpStatus.BAD_REQUEST, "400", "유효하지 않은 입력입니다.")
}