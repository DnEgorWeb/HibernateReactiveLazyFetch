package org.acme

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.Max
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

@JsonRootName("book")
@RegisterForReflection
internal data class BookRequest(
    @JsonProperty("name")
    @NotEmpty(message = "Name is required")
    val name: String?,
) {
    fun toEntity() = Book()
}