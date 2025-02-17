package domain.exception

class WiseSayingException (val errorCode: WiseSayingErrorCode) : RuntimeException(errorCode.message){
    fun getStatus(): Int = errorCode.status
    fun getCode(): String = errorCode.code
}