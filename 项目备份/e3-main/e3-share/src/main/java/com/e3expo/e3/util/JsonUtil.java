package  com.e3expo.e3.util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

/**
 * Created by Administrator on 2016/3/24.
 */
public class JsonUtil {
    private static ObjectMapper MAPPER;

    private static ObjectMapper multiMapper;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
       

        multiMapper = new ObjectMapper();
        multiMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        multiMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        multiMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        multiMapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
    }

    /**
     * java对象转换成json
     */
    public static <T> String toJson(T pojo) {
        String result;
        try {
            result = MAPPER.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json转换异常", e);
        }
        return result;
    }

    /**
     * json转换成Java对象
     */
    public static <T> T fromJson(String json, Class<T> valueType) {
        T t;
        try {
            t = MAPPER.readValue(json, valueType);
        } catch (IOException e) {
            throw new RuntimeException("json转换异常", e);
        }
        return t;
    }
    
    /**
     * json转换成Java对象
     */
    public static <T> List<T>  fromJsonList(String json, Class<T> valueType) {
//    	JavaType javaType = getCollectionType(ArrayList.class, valueType); 
//    	ArrayType arrayType = MAPPER.getTypeFactory().constructArrayType(valueType);
    	JavaType javaType = MAPPER.getTypeFactory().constructParametrizedType(ArrayList.class, String.class, valueType);
//        T t;
        List<T> list ;
        try {
        	list = MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException("json转换异常", e);
        }
        return list;
    }
    
    public static <T> T mapToObject(Map map, Class<T> valueType) {
        T t;
        try {
            String result = MAPPER.writeValueAsString(map);
            t = MAPPER.readValue(result, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json转换异常",e);
        } catch (IOException e) {
            throw new RuntimeException("json转换异常",e);
        }

        return t;
    }

    public static <T> T multiValueMapToObject(MultiValueMap valueMap, Class<T> valueType) {
        T t;
        try {
            String result = multiMapper.writeValueAsString(valueMap);
            t = multiMapper.readValue(result, valueType);
        } catch (Exception e) {
            throw new RuntimeException("json转换异常");
        }

        return t;
    }

    public static Map objectToMap(Object object) {
        Map map;
        try {
            String result = MAPPER.writeValueAsString(object);
            map = MAPPER.readValue(result, Map.class);
        } catch (JsonProcessingException e) {
        	e.printStackTrace();
            throw new RuntimeException("json转换异常");
        } catch (IOException e) {
            throw new RuntimeException("json转换异常");
        }

        return map;
    }
}
