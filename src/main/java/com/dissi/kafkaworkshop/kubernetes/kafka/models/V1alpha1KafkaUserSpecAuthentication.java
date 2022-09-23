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
 * Authentication mechanism enabled for this Kafka user. The supported authentication mechanisms are
 * &#x60;scram-sha-512&#x60;, &#x60;tls&#x60;, and &#x60;tls-external&#x60;.   * &#x60;scram-sha-512&#x60; generates a
 * secret with SASL SCRAM-SHA-512 credentials. * &#x60;tls&#x60; generates a secret with user certificate for mutual TLS
 * authentication. * &#x60;tls-external&#x60; does not generate a user certificate.   But prepares the user for using
 * mutual TLS authentication using a user certificate generated outside the User Operator.   ACLs and quotas set for
 * this user are configured in the &#x60;CN&#x3D;&lt;username&gt;&#x60; format.  Authentication is optional. If
 * authentication is not configured, no credentials are generated. ACLs and quotas set for the user are configured in
 * the &#x60;&lt;username&gt;&#x60; format suitable for SASL authentication.
 */
@ApiModel(description = "Authentication mechanism enabled for this Kafka user. The supported authentication mechanisms are `scram-sha-512`, `tls`, and `tls-external`.   * `scram-sha-512` generates a secret with SASL SCRAM-SHA-512 credentials. * `tls` generates a secret with user certificate for mutual TLS authentication. * `tls-external` does not generate a user certificate.   But prepares the user for using mutual TLS authentication using a user certificate generated outside the User Operator.   ACLs and quotas set for this user are configured in the `CN=<username>` format.  Authentication is optional. If authentication is not configured, no credentials are generated. ACLs and quotas set for the user are configured in the `<username>` format suitable for SASL authentication.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-09-23T10:08:26.048Z[Etc/UTC]")
public class V1alpha1KafkaUserSpecAuthentication {

  public static final String SERIALIZED_NAME_PASSWORD = "password";
  @SerializedName(SERIALIZED_NAME_PASSWORD)
  private V1alpha1KafkaUserSpecAuthenticationPassword password;

  /**
   * Authentication type.
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    TLS("tls"),

    TLS_EXTERNAL("tls-external"),

    SCRAM_SHA_512("scram-sha-512");

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


  public V1alpha1KafkaUserSpecAuthentication password(V1alpha1KafkaUserSpecAuthenticationPassword password) {

    this.password = password;
    return this;
  }

  /**
   * Get password
   *
   * @return password
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1alpha1KafkaUserSpecAuthenticationPassword getPassword() {
    return password;
  }


  public void setPassword(V1alpha1KafkaUserSpecAuthenticationPassword password) {
    this.password = password;
  }


  public V1alpha1KafkaUserSpecAuthentication type(TypeEnum type) {

    this.type = type;
    return this;
  }

  /**
   * Authentication type.
   *
   * @return type
   **/
  @ApiModelProperty(required = true, value = "Authentication type.")

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
    V1alpha1KafkaUserSpecAuthentication v1alpha1KafkaUserSpecAuthentication = (V1alpha1KafkaUserSpecAuthentication) o;
    return Objects.equals(this.password, v1alpha1KafkaUserSpecAuthentication.password) &&
      Objects.equals(this.type, v1alpha1KafkaUserSpecAuthentication.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1KafkaUserSpecAuthentication {\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

