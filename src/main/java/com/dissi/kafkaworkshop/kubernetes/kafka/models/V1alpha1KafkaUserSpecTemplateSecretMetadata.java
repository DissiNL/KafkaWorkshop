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
 * Metadata applied to the resource.
 */
@ApiModel(description = "Metadata applied to the resource.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-09-23T10:08:26.048Z[Etc/UTC]")
public class V1alpha1KafkaUserSpecTemplateSecretMetadata {

  public static final String SERIALIZED_NAME_ANNOTATIONS = "annotations";
  @SerializedName(SERIALIZED_NAME_ANNOTATIONS)
  private Object annotations;

  public static final String SERIALIZED_NAME_LABELS = "labels";
  @SerializedName(SERIALIZED_NAME_LABELS)
  private Object labels;


  public V1alpha1KafkaUserSpecTemplateSecretMetadata annotations(Object annotations) {

    this.annotations = annotations;
    return this;
  }

  /**
   * Annotations added to the resource template. Can be applied to different resources such as &#x60;StatefulSets&#x60;,
   * &#x60;Deployments&#x60;, &#x60;Pods&#x60;, and &#x60;Services&#x60;.
   *
   * @return annotations
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Annotations added to the resource template. Can be applied to different resources such as `StatefulSets`, `Deployments`, `Pods`, and `Services`.")

  public Object getAnnotations() {
    return annotations;
  }


  public void setAnnotations(Object annotations) {
    this.annotations = annotations;
  }


  public V1alpha1KafkaUserSpecTemplateSecretMetadata labels(Object labels) {

    this.labels = labels;
    return this;
  }

  /**
   * Labels added to the resource template. Can be applied to different resources such as &#x60;StatefulSets&#x60;,
   * &#x60;Deployments&#x60;, &#x60;Pods&#x60;, and &#x60;Services&#x60;.
   *
   * @return labels
   **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Labels added to the resource template. Can be applied to different resources such as `StatefulSets`, `Deployments`, `Pods`, and `Services`.")

  public Object getLabels() {
    return labels;
  }


  public void setLabels(Object labels) {
    this.labels = labels;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1KafkaUserSpecTemplateSecretMetadata v1alpha1KafkaUserSpecTemplateSecretMetadata = (V1alpha1KafkaUserSpecTemplateSecretMetadata) o;
    return Objects.equals(this.annotations, v1alpha1KafkaUserSpecTemplateSecretMetadata.annotations) &&
      Objects.equals(this.labels, v1alpha1KafkaUserSpecTemplateSecretMetadata.labels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotations, labels);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1KafkaUserSpecTemplateSecretMetadata {\n");
    sb.append("    annotations: ").append(toIndentedString(annotations)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
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

