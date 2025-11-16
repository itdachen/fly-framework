package com.github.itdachen.framework.sensitive.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.github.itdachen.framework.sensitive.ISensitiveHandler;
import com.github.itdachen.framework.sensitive.annotation.Sensitive;

import java.io.IOException;
import java.util.Objects;

/**
 * Description: 脱敏序列化
 * Created by 剑鸣秋朔 on 2023-07-05 16:31
 * Created with IntelliJ IDEA.
 */
public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private ISensitiveHandler sensitiveHandler;

    public SensitiveJsonSerializer(ISensitiveHandler sensitiveHandler) {
        this.sensitiveHandler = sensitiveHandler;
    }

    public SensitiveJsonSerializer() {
    }

    @Override
    public void serialize(String value,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(sensitiveHandler.serialize(value));
    }


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer = null;
        if (null == beanProperty) jsonSerializer = serializerProvider.findNullValueSerializer(beanProperty);

        if (!Objects.equals(beanProperty.getType().getRawClass(), String.class))
            jsonSerializer = serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);

        if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
            jsonSerializer = setDesensitization(jsonSerializer, beanProperty);
        }
        return jsonSerializer;
    }

    /***
     * 设置脱敏
     *
     * @author 剑鸣秋朔
     * @date 2023/7/4 16:49
     * @param jsonSerializer jsonSerializer
     * @param beanProperty beanProperty
     * @return com.fasterxml.jackson.databind.JsonSerializer<?>
     */
    private JsonSerializer<?> setDesensitization(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) {
        Sensitive sensitive = beanProperty.getAnnotation(Sensitive.class);
        if (sensitive == null) sensitive = beanProperty.getContextAnnotation(Sensitive.class);
        if (sensitive != null) {
            //设置脱敏实例
            try {
                jsonSerializer = new SensitiveJsonSerializer(sensitive.value().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonSerializer;
    }
}
