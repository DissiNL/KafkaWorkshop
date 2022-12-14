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
 * Secret from which the password should be read.
 */
@ApiModel(description = "Secret from which the password should be read.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-09-23T10:08:26.048Z[Etc/UTC]")
public class V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom {

  public static final String SERIALIZED_NAME_SECRET_KEY_REF = "secretKeyRef";
  @SerializedName(SERIALIZED_NAME_SECRET_KEY_REF)
  private V1alpha1KafkaUserSpecAuthenticationPasswordValueFromSecretKeyRef secretKeyRef;


  public V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom secretKeyRef(
    V1alpha1KafkaUserSpecAuthenticationPasswordValueFromSecretKeyRef secretKeyRef) {

    this.secretKeyRef = secretKeyRef;
    return this;
  }

  /**
   * Get secretKeyRef
   *
   * @return secretKeyRef
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1alpha1KafkaUserSpecAuthenticationPasswordValueFromSecretKeyRef getSecretKeyRef() {
    return secretKeyRef;
  }


  public void setSecretKeyRef(V1alpha1KafkaUserSpecAuthenticationPasswordValueFromSecretKeyRef secretKeyRef) {
    this.secretKeyRef = secretKeyRef;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom v1alpha1KafkaUserSpecAuthenticationPasswordValueFrom = (V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom) o;
    return Objects.equals(this.secretKeyRef, v1alpha1KafkaUserSpecAuthenticationPasswordValueFrom.secretKeyRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(secretKeyRef);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1KafkaUserSpecAuthenticationPasswordValueFrom {\n");
    sb.append("    secretKeyRef: ").append(toIndentedString(secretKeyRef)).append("\n");
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

