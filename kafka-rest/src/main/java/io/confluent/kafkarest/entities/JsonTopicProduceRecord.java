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

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTopicProduceRecord extends JsonProduceRecord
    implements TopicProduceRecord<Object, Object> {

  // When producing to a topic, a partition may be explicitly requested.
  @Min(0)
  protected Integer partition;

  @JsonCreator
  public JsonTopicProduceRecord(
      @JsonProperty("key") Object key,
      @JsonProperty("value") Object value,
      @JsonProperty("partition") Integer partition
  ) {
    super(key, value);
    this.partition = partition;
  }

  @JsonCreator
  public JsonTopicProduceRecord(
      @JsonProperty("headers") Map<String, String> headers,
      @JsonProperty("key") Object key,
      @JsonProperty("value") Object value,
      @JsonProperty("partition") Integer partition
  ) {
    super(headers, key, value);
    this.partition = partition;
  }

  public JsonTopicProduceRecord(Object value, Integer partition) {
    this(null, value, partition);
  }

  public JsonTopicProduceRecord(Object value, Map<String, String> headers, Integer partition) {
    this(headers, value, null, partition);
  }

  @Override
  public Integer getPartition() {
    return partition;
  }

  @Override
  public Integer partition() {
    return partition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    JsonTopicProduceRecord that = (JsonTopicProduceRecord) o;

    return !(partition != null ? !partition.equals(that.partition) : that.partition != null);

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (partition != null ? partition.hashCode() : 0);
    return result;
  }
}
