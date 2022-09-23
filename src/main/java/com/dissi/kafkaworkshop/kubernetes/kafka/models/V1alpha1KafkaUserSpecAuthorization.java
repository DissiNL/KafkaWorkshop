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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Authorization rules for this Kafka user.
 */
@ApiModel(description = "Authorization rules for this Kafka user.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-09-23T10:08:26.048Z[Etc/UTC]")
public class V1alpha1KafkaUserSpecAuthorization {

  public static final String SERIALIZED_NAME_ACLS = "acls";
  @SerializedName(SERIALIZED_NAME_ACLS)
  private List<V1alpha1KafkaUserSpecAuthorizationAcls> acls = new ArrayList<>();

  /**
   * Authorization type. Currently the only supported type is &#x60;simple&#x60;. &#x60;simple&#x60; authorization type
   * uses Kafka&#39;s &#x60;kafka.security.authorizer.AclAuthorizer&#x60; class for authorization.
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    SIMPLE("simple");

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


  public V1alpha1KafkaUserSpecAuthorization acls(List<V1alpha1KafkaUserSpecAuthorizationAcls> acls) {

    this.acls = acls;
    return this;
  }

  public V1alpha1KafkaUserSpecAuthorization addAclsItem(V1alpha1KafkaUserSpecAuthorizationAcls aclsItem) {
    this.acls.add(aclsItem);
    return this;
  }

  /**
   * List of ACL rules which should be applied to this user.
   *
   * @return acls
   **/
  @ApiModelProperty(required = true, value = "List of ACL rules which should be applied to this user.")

  public List<V1alpha1KafkaUserSpecAuthorizationAcls> getAcls() {
    return acls;
  }


  public void setAcls(List<V1alpha1KafkaUserSpecAuthorizationAcls> acls) {
    this.acls = acls;
  }


  public V1alpha1KafkaUserSpecAuthorization type(TypeEnum type) {

    this.type = type;
    return this;
  }

  /**
   * Authorization type. Currently the only supported type is &#x60;simple&#x60;. &#x60;simple&#x60; authorization type
   * uses Kafka&#39;s &#x60;kafka.security.authorizer.AclAuthorizer&#x60; class for authorization.
   *
   * @return type
   **/
  @ApiModelProperty(required = true, value = "Authorization type. Currently the only supported type is `simple`. `simple` authorization type uses Kafka's `kafka.security.authorizer.AclAuthorizer` class for authorization.")

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
    V1alpha1KafkaUserSpecAuthorization v1alpha1KafkaUserSpecAuthorization = (V1alpha1KafkaUserSpecAuthorization) o;
    return Objects.equals(this.acls, v1alpha1KafkaUserSpecAuthorization.acls) &&
      Objects.equals(this.type, v1alpha1KafkaUserSpecAuthorization.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acls, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1KafkaUserSpecAuthorization {\n");
    sb.append("    acls: ").append(toIndentedString(acls)).append("\n");
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
