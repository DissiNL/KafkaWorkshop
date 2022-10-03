{{/*
Expand the name of the chart.
*/}}
{{- define "kafka-workshop.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "kafka-workshop.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Custom environment variables
*/}}
{{- define "kafka-workshop.env-vars" -}}
{{- $envVars := .Values.env -}}
{{- include "kafka-workshop.create-env-section" (dict "Values" .Values "Template" .Template "Env" $envVars) -}}
{{- end -}}

{{/*
  Create an `env` key for a container or an ‘init’ container.
*/}}
{{- define "kafka-workshop.create-env-section" -}}
  {{- if gt (len .Env) 0 }}
# Generic environment variables
  {{- end -}}
  {{- range $envVarName, $envVarValue := .Env -}}
    {{- if typeIs "string" $envVarValue }}
- name: {{ $envVarName | quote }}
  value: {{ tpl $envVarValue $ | quote }}
    {{- else if typeIs "map[string]interface {}" $envVarValue }}
- name: {{ $envVarName | quote }}
{{- tpl ( toYaml $envVarValue ) $ | nindent 2 }}
    {{- else }}
# Type of environment variable ‘{{ $envVarName }}’ is neither string nor map of strings; nothing added
    {{- end -}}
  {{- end -}}
{{- end -}}


{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "kafka-workshop.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "kafka-workshop.labels" -}}
helm.sh/chart: {{ include "kafka-workshop.chart" . }}
{{ include "kafka-workshop.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "kafka-workshop.selectorLabels" -}}
app.kubernetes.io/name: {{ include "kafka-workshop.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "kafka-workshop.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "kafka-workshop.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}
