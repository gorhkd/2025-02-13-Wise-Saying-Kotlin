package domain.repository

import domain.entity.WiseSaying
import domain.entity.quotes
import domain.exception.WiseSayingErrorCode
import domain.exception.WiseSayingException

class WiseSayingRepository {

    fun findAllList(): List<WiseSaying> {
        return quotes
    }

    fun findByNumber(num: Int): WiseSaying {
        return quotes.firstOrNull() { it.number == num }
            ?: throw WiseSayingException(WiseSayingErrorCode.NOT_FOUND_WISE_SAYING)
    }

    fun save(wiseSaying: WiseSaying) {
        quotes.add(wiseSaying)
    }

    fun remove(wiseSaying: WiseSaying) {
        quotes.remove(wiseSaying)
    }

    fun replace(index: Int, updateWiseSaying: WiseSaying) {
        quotes[index] = updateWiseSaying
    }

}