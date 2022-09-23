/*
 * Kubernetes
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v1.21.1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.dissi.kafkaworkshop.kubernetes.kafka.models;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.Objects;

/**
 * Indicates the resource for which given ACL rule applies.
 */
@ApiModel(description = "Indicates the resource for which given ACL rule applies.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-09-23T10:08:26.048Z[Etc/UTC]")
public class V1alpha1KafkaUserSpecAuthorizationResource {

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  /**
   * Describes the pattern used in the resource field. The supported types are &#x60;literal&#x60; and
   * &#x60;prefix&#x60;. With &#x60;literal&#x60; pattern type, the resource field will be used as a definition of a
   * full name. With &#x60;prefix&#x60; pattern type, the resource name will be used only as a prefix. Default value is
   * &#x60;literal&#x60;.
   */
  @JsonAdapter(PatternTypeEnum.Adapter.class)
  public enum PatternTypeEnum {
    LITERAL("literal"),

    PREFIX("prefix");

    private String value;

    PatternTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static PatternTypeEnum fromValue(String value) {
      for (PatternTypeEnum b : PatternTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<PatternTypeEnum> {

      @Override
      public void write(final JsonWriter jsonWriter, final PatternTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public PatternTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return PatternTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_PATTERN_TYPE = "patternType";
  @SerializedName(SERIALIZED_NAME_PATTERN_TYPE)
  private PatternTypeEnum patternType;

  /**
   * Resource type. The available resource types are &#x60;topic&#x60;, &#x60;group&#x60;, &#x60;cluster&#x60;, and
   * &#x60;transactionalId&#x60;.
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    TOPIC("topic"),

    GROUP("group"),

    CLUSTER("cluster"),

    TRANSACTIONALID("transactionalId");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TypeEnum> {

      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeEnum type;


  public V1alpha1KafkaUserSpecAuthorizationResource name(String name) {

    this.name = name;
    return this;
  }

  /**
   * Name of resource for which given ACL rule applies. Can be combined with &#x60;patternType&#x60; field to use prefix
   * pattern.
   *
   * @return name
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Name of resource for which given ACL rule applies. Can be combined with `patternType` field to use prefix pattern.")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public V1alpha1KafkaUserSpecAuthorizationResource patternType(PatternTypeEnum patternType) {

    this.patternType = patternType;
    return this;
  }

  /**
   * Describes the pattern used in the resource field. The supported types are &#x60;literal&#x60; and
   * &#x60;prefix&#x60;. With &#x60;literal&#x60; pattern type, the resource field will be used as a definition of a
   * full name. With &#x60;prefix&#x60; pattern type, the resource name will be used only as a prefix. Default value is
   * &#x60;literal&#x60;.
   *
   * @return patternType
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Describes the pattern used in the resource field. The supported types are `literal` and `prefix`. With `literal` pattern type, the resource field will be used as a definition of a full name. With `prefix` pattern type, the resource name will be used only as a prefix. Default value is `literal`.")

  public PatternTypeEnum getPatternType() {
    return patternType;
  }


  public void setPatternType(PatternTypeEnum patternType) {
    this.patternType = patternType;
  }


  public V1alpha1KafkaUserSpecAuthorizationResource type(TypeEnum type) {

    this.type = type;
    return this;
  }

  /**
   * Resource type. The available resource types are &#x60;topic&#x60;, &#x60;group&#x60;, &#x60;cluster&#x60;, and
   * &#x60;transactionalId&#x60;.
   *
   * @return type
   **/
  @ApiModelProperty(required = true, value = "Resource type. The available resource types are `topic`, `group`, `cluster`, and `transactionalId`.")

  public TypeEnum getType() {
    return type;
  }


  public void setType(TypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1KafkaUserSpecAuthorizationResource v1alpha1KafkaUserSpecAuthorizationResource = (V1alpha1KafkaUserSpecAuthorizationResource) o;
    return Objects.equals(this.name, v1alpha1KafkaUserSpecAuthorizationResource.name) &&
      Objects.equals(this.patternType, v1alpha1KafkaUserSpecAuthorizationResource.patternType) &&
      Objects.equals(this.type, v1alpha1KafkaUserSpecAuthorizationResource.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, patternType, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1KafkaUserSpecAuthorizationResource {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    patternType: ").append(toIndentedString(patternType)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

