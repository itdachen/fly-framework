package com.github.itdachen.framework.sensitive.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import com.github.itdachen.framework.sensitive.annotation.CustomSensitive;
import com.github.itdachen.framework.sensitive.utils.MaskUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Description: 序列化
 * Created by 王大宸 on 2023-07-05 17:20
 * Created with IntelliJ IDEA.
 */
public class CustomSensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 前置不需要打码的长度
     */
    private int start;

    /**
     * 后置不需要打码的长度
     */
    private int end;

    /**
     * 用什么打码
     */
    private String symbol;

    public CustomSensitiveSerializer(int start, int end, String symbol) {
        this.start = start;
        this.end = end;
        this.symbol = symbol;
    }

    public CustomSensitiveSerializer() {
    }

    @Override
    public void serialize(final String value,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(MaskUtils.wordMask(value, this.start, this.end, this.symbol));
    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
                                              final BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                CustomSensitive customSensitive = beanProperty.getAnnotation(CustomSensitive.class);
                if (null == customSensitive) {
                    customSensitive = beanProperty.getContextAnnotation(CustomSensitive.class);
                }
                if (null != customSensitive) {
                    return new CustomSensitiveSerializer(
                            customSensitive.start(),
                            customSensitive.end(),
                            customSensitive.symbol()
                    );
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
