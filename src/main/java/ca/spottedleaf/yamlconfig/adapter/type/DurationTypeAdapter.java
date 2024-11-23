package ca.spottedleaf.yamlconfig.adapter.type;

import ca.spottedleaf.yamlconfig.adapter.TypeAdapter;
import ca.spottedleaf.yamlconfig.adapter.TypeAdapterRegistry;
import ca.spottedleaf.yamlconfig.type.Duration;
import java.lang.reflect.Type;

public final class DurationTypeAdapter extends TypeAdapter<Duration, String> {

    public static final DurationTypeAdapter INSTANCE = new DurationTypeAdapter();

    @Override
    public Duration deserialize(final TypeAdapterRegistry registry, final Object input, final Type type) {
        if (!(input instanceof String string)) {
            throw new IllegalArgumentException("Not a string: " + input.getClass());
        }
        return Duration.parse(string);
    }

    @Override
    public String serialize(final TypeAdapterRegistry registry, final Duration value, final Type type) {
        return value.toString();
    }
}
