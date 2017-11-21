package com.jzheadley.swifey.util

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

/**
 * Utility class for ResponseEntity creation.
 */
object ResponseUtil {

    /**
     * Wrap the optional into a [ResponseEntity] with an [HttpStatus.OK] status, or if it's empty,
     * it returns a [ResponseEntity] with [HttpStatus.NOT_FOUND].
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @return response containing `maybeResponse` if present or [HttpStatus.NOT_FOUND]
    </X> */
    fun <X> wrapOrNotFound(maybeResponse: Optional<X>): ResponseEntity<X> = wrapOrNotFound(maybeResponse, null)

    /**
     * Wrap the optional into a [ResponseEntity] with an [HttpStatus.OK] status with the headers,
     * or if it's empty, it returns a [ResponseEntity] with [HttpStatus.NOT_FOUND].
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @param header        headers to be added to the response
     * @return response containing `maybeResponse` if present or [HttpStatus.NOT_FOUND]
    </X> */
    fun <X> wrapOrNotFound(maybeResponse: Optional<X>, header: HttpHeaders?): ResponseEntity<X> {
        return maybeResponse
                .map { response -> ResponseEntity.ok().headers(header).body(response) }
                .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }

}
