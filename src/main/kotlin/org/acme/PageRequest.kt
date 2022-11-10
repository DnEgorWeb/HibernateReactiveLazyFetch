package org.acme

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

import javax.validation.constraints.NotEmpty

@JsonRootName("page")
@RegisterForReflection
internal data class PageRequest(
    @JsonProperty("book_id")
    @NotEmpty(message = "book_id is required")
    val bookId: Long?,
) {
    fun toEntity() = Page()
}