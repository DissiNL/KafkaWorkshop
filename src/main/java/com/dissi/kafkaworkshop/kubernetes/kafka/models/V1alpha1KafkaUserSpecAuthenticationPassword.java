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

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * Specify the password for the user. If not set, a new password is generated by the User Operator.
 */
@ApiModel(description = "Specify the password for the user. If not set, a new password is generated by the User Operator.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-09-23T10:08:26.048Z[Etc/UTC]")
public class V1alpha1KafkaUserSpecAuthenticationPassword {

  public static final String SERIALIZED_NAME_VALUE_FROM = "valueFrom";
  @SerializedName(SERIALIZED_NAME_VALUE_FROM)
  private V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom valueFrom;


  public V1alpha1KafkaUserSpecAuthenticationPassword valueFrom(
    V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom valueFrom) {

    this.valueFrom = valueFrom;
    return this;
  }

  /**
   * Get valueFrom
   *
   * @return valueFrom
   **/
  @ApiModelProperty(required = true, value = "")

  public V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom getValueFrom() {
    return valueFrom;
  }


  public void setValueFrom(V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom valueFrom) {
    this.valueFrom = valueFrom;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1KafkaUserSpecAuthenticationPassword v1alpha1KafkaUserSpecAuthenticationPassword = (V1alpha1KafkaUserSpecAuthenticationPassword) o;
    return Objects.equals(this.valueFrom, v1alpha1KafkaUserSpecAuthenticationPassword.valueFrom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valueFrom);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1KafkaUserSpecAuthenticationPassword {\n");
    sb.append("    valueFrom: ").append(toIndentedString(valueFrom)).append("\n");
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

