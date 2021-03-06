package me.ghui.fruit.converter.retrofit;


import me.ghui.fruit.Fruit;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by ghui on 11/04/2017.
 */

public class FruitResponseBodyConverter<T> implements Converter<ResponseBody, T> {
  private Fruit mPicker;
  private Type mType;

  FruitResponseBodyConverter(Fruit fruit, Type type) {
    mPicker = fruit;
    mType = type;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    try {
      String response = value.string();
      T data = mPicker.fromHtml(response, mType);
      if(data != null && data instanceof IBaseWrapper){
          ((IBaseWrapper) data).setResponse(response);
      }
      return data;
    } finally {
      value.close();
    }
  }
}
