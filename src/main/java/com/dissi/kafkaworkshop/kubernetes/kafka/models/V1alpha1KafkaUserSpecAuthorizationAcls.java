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
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.Objects;

/**
 * V1alpha1KafkaUserSpecAuthorizationAcls
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-09-23T10:08:26.048Z[Etc/UTC]")
public class V1alpha1KafkaUserSpecAuthorizationAcls {

  public static final String SERIALIZED_NAME_HOST = "host";
  @SerializedName(SERIALIZED_NAME_HOST)
  private String host;

  /**
   * Operation which will be allowed or denied. Supported operations are: Read, Write, Create, Delete, Alter, Describe,
   * ClusterAction, AlterConfigs, DescribeConfigs, IdempotentWrite and All.
   */
  @JsonAdapter(OperationEnum.Adapter.class)
  public enum OperationEnum {
    READ("Read"),

    WRITE("Write"),

    CREATE("Create"),

    DELETE("Delete"),

    ALTER("Alter"),

    DESCRIBE("Describe"),

    CLUSTERACTION("ClusterAction"),

    ALTERCONFIGS("AlterConfigs"),

    DESCRIBECONFIGS("DescribeConfigs"),

    IDEMPOTENTWRITE("IdempotentWrite"),

    ALL("All");

    private String value;

    OperationEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static OperationEnum fromValue(String value) {
      for (OperationEnum b : OperationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<OperationEnum> {

      @Override
      public void write(final JsonWriter jsonWriter, final OperationEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OperationEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return OperationEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_OPERATION = "operation";
  @SerializedName(SERIALIZED_NAME_OPERATION)
  private OperationEnum operation;

  public static final String SERIALIZED_NAME_RESOURCE = "resource";
  @SerializedName(SERIALIZED_NAME_RESOURCE)
  private V1alpha1KafkaUserSpecAuthorizationResource resource;

  /**
   * The type of the rule. Currently the only supported type is &#x60;allow&#x60;. ACL rules with type &#x60;allow&#x60;
   * are used to allow user to execute the specified operations. Default value is &#x60;allow&#x60;.
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    ALLOW("allow"),

    DENY("deny");

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


  public V1alpha1KafkaUserSpecAuthorizationAcls host(String host) {

    this.host = host;
    return this;
  }

  /**
   * The host from which the action described in the ACL rule is allowed or denied.
   *
   * @return host
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The host from which the action described in the ACL rule is allowed or denied.")

  public String getHost() {
    return host;
  }


  public void setHost(String host) {
    this.host = host;
  }


  public V1alpha1KafkaUserSpecAuthorizationAcls operation(OperationEnum operation) {

    this.operation = operation;
    return this;
  }

  /**
   * Operation which will be allowed or denied. Supported operations are: Read, Write, Create, Delete, Alter, Describe,
   * ClusterAction, AlterConfigs, DescribeConfigs, IdempotentWrite and All.
   *
   * @return operation
   **/
  @ApiModelProperty(required = true, value = "Operation which will be allowed or denied. Supported operations are: Read, Write, Create, Delete, Alter, Describe, ClusterAction, AlterConfigs, DescribeConfigs, IdempotentWrite and All.")

  public OperationEnum getOperation() {
    return operation;
  }


  public void setOperation(OperationEnum operation) {
    this.operation = operation;
  }


  public V1alpha1KafkaUserSpecAuthorizationAcls resource(V1alpha1KafkaUserSpecAuthorizationResource resource) {

    this.resource = resource;
    return this;
  }

  /**
   * Get resource
   *
   * @return resource
   **/
  @ApiModelProperty(required = true, value = "")

  public V1alpha1KafkaUserSpecAuthorizationResource getResource() {
    return resource;
  }


  public void setResource(V1alpha1KafkaUserSpecAuthorizationResource resource) {
    this.resource = resource;
  }


  public V1alpha1KafkaUserSpecAuthorizationAcls type(TypeEnum type) {

    this.type = type;
    return this;
  }

  /**
   * The type of the rule. Currently the only supported type is &#x60;allow&#x60;. ACL rules with type &#x60;allow&#x60;
   * are used to allow user to execute the specified operations. Default value is &#x60;allow&#x60;.
   *
   * @return type
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The type of the rule. Currently the only supported type is `allow`. ACL rules with type `allow` are used to allow user to execute the specified operations. Default value is `allow`.")

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
    V1alpha1KafkaUserSpecAuthorizationAcls v1alpha1KafkaUserSpecAuthorizationAcls = (V1alpha1KafkaUserSpecAuthorizationAcls) o;
    return Objects.equals(this.host, v1alpha1KafkaUserSpecAuthorizationAcls.host) &&
      Objects.equals(this.operation, v1alpha1KafkaUserSpecAuthorizationAcls.operation) &&
      Objects.equals(this.resource, v1alpha1KafkaUserSpecAuthorizationAcls.resource) &&
      Objects.equals(this.type, v1alpha1KafkaUserSpecAuthorizationAcls.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, operation, resource, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1KafkaUserSpecAuthorizationAcls {\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
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
