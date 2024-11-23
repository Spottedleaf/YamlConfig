package ca.spottedleaf.yamlconfig.adapter.collection;

import ca.spottedleaf.yamlconfig.adapter.TypeAdapter;
import ca.spottedleaf.yamlconfig.adapter.TypeAdapterRegistry;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CollectionTypeAdapter extends TypeAdapter<Collection<Object>, List<Object>> {

    public static final CollectionTypeAdapter INSTANCE = new CollectionTypeAdapter();

    @Override
    public Collection<Object> deserialize(final TypeAdapterRegistry registry, final Object input, final Type type) {
        if (!(type instanceof ParameterizedType parameterizedType)) {
            throw new IllegalArgumentException("Collection field must specify generic type");
        }
        final Type elementType = parameterizedType.getActualTypeArguments()[0];
        if (input instanceof Collection<?> collection) {
            final List<Object> ret = new ArrayList<>(collection.size());

            for (final Object v : collection) {
                ret.add(registry.deserialize(v, elementType));
            }

            return ret;
        }
        throw new IllegalArgumentException("Not a collection type: " + input.getClass());
    }

    @Override
    public List<Object> serialize(final TypeAdapterRegistry registry, final Collection<Object> value, final Type type) {
        final List<Object> ret = new ArrayList<>(value.size());

        final Type elementType = type instanceof ParameterizedType parameterizedType ? parameterizedType.getActualTypeArguments()[0] : null;

        for (final Object v : value) {
            ret.add(registry.serialize(v, elementType == null ? v.getClass() : elementType));
        }

        return ret;
    }
}
