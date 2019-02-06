/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License; you may not use this file
 * except in compliance with the License.  You may obtain a copy of the License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.kafkarest.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonProduceRecord extends ProduceRecordBase<Object, Object> {
  
  private Map<String, String> headers;

  @JsonCreator
  public JsonProduceRecord(
      @JsonProperty("key") Object key,
      @JsonProperty("value") Object value
  ) {
    super(key, value);
  }

  @JsonCreator
  public JsonProduceRecord(
      @JsonProperty("headers") Map<String, String> headers,
      @JsonProperty("key") Object key,
      @JsonProperty("value") Object value
  ) {
    super(key, value);
    this.headers = headers;
  }

  public JsonProduceRecord(Object value) {
    this(null, value);
  }

  public JsonProduceRecord(Object value, Map<String, String> headers) {
    this(headers, null, value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JsonProduceRecord that = (JsonProduceRecord) o;

    return key != null
           ? key.equals(that.key)
           : that.key == null && !(value != null ? !value.equals(that.value) : that.value != null) 
           && that.headers == null && !(headers != null ? !headers.equals(that.headers)
               : that.headers != null);

  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0) + headers.hashCode();
    return result;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

}
