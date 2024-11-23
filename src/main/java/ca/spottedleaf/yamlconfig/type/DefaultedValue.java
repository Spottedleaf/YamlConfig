package ca.spottedleaf.yamlconfig.type;

public final class DefaultedValue<T> {

    private final T value;

    public DefaultedValue() {
        this(null);
    }

    public DefaultedValue(final T value) {
        this.value = value;
    }

    public T getValueRaw() {
        return value;
    }

    public T getOrDefault(final T dfl) {
        return this.value != null ? this.value : dfl;
    }
}
