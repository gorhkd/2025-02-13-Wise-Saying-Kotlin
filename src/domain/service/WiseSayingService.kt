package domain.service

import domain.entity.WiseSaying
import domain.exception.WiseSayingErrorCode
import domain.exception.WiseSayingException
import domain.repository.WiseSayingRepository

class WiseSayingService {

    private val wiseSayingRepository = WiseSayingRepository()

    fun save(newContent: String, newAuthor: String): WiseSaying {
        val wiseSayingList: List<WiseSaying> = wiseSayingRepository.findAllList()
        val num = wiseSayingList.maxOfOrNull { it.number }?.plus(1)?: 1
        val wiseSaying = WiseSaying(num, newContent, newAuthor)
        wiseSayingRepository.save(wiseSaying)
        return wiseSaying
    }

    fun delete(num: Int) {
        val wiseSaying = wiseSayingRepository.findByNumber(num)
        wiseSayingRepository.remove(wiseSaying)
    }

    fun modify(wiseSaying: WiseSaying, content: String, author: String) {
        val updateWiseSaying = copy(wiseSaying, content, author)

        var wiseSayings = wiseSayingRepository.findAllList().toMutableList()

        val index = wiseSayings.indexOfFirst { it.number == updateWiseSaying.number }
        if(index != -1) {
            wiseSayingRepository.replace(index, updateWiseSaying)
        }
    }

    fun getAll(): List<WiseSaying> {
        return wiseSayingRepository.findAllList()
    }

    fun getWiseSaying(num: Int): WiseSaying {
        return wiseSayingRepository.findByNumber(num)
    }

    fun copy(wiseSaying: WiseSaying, content: String, author: String): WiseSaying {
        return wiseSaying.copy(content = content, author = author)
    }

    // 예외처리 메서드
    fun readNonBlankInput(prompt: String): String {
        print("${prompt}")
        return readlnOrNull()?.takeIf { it.isNotBlank() }
            ?: throw WiseSayingException(WiseSayingErrorCode.INVALID_INPUT)
    }

    fun readOnlyInt(prompt: String): Int {
        print(prompt)
        return readlnOrNull()?.toIntOrNull()
            ?: throw WiseSayingException(WiseSayingErrorCode.NOT_FOUND_WISE_SAYING)
    }
}