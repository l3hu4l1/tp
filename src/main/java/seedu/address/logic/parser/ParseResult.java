package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

/**
 * Represents the result of parsing a string into an object of type T.
 * It contains the parsed value and an optional string for warnings.
 *
 * @param <T> the type of the parsed value.
 */
public class ParseResult<T> {
    private final T value;
    private final Optional<String> warning;

    /**
     * Constructs a ParseResult with the given value and an optional warning.
     *
     * @param value   the parsed value of type T.
     * @param warning an optional string containing any warnings related to the parsing process.
     */
    public ParseResult(T value, Optional<String> warning) {
        requireNonNull(value);
        requireNonNull(warning);

        this.value = value;
        this.warning = warning;
    }

    /**
     * Returns the parsed value of type T.
     *
     * @return the parsed value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns an optional string containing any warnings related to the parsing process.
     *
     * @return an optional warning string.
     */
    public Optional<String> getWarning() {
        return warning;
    }

    @Override
    public String toString() {
        return "ParseResult[value=" + value + ", warning=" + warning + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParseResult<?> other)) {
            return false;
        }
        return value.equals(other.value) && warning.equals(other.warning);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(value, warning);
    }
}
