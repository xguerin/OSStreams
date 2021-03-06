/*
 * Copyright 2021 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ibm.streams.controller.crds.jobs;

import io.fabric8.kubernetes.client.CustomResource;

public class Job extends CustomResource {

  private JobSpec spec;

  @Override
  public String toString() {
    return "{ apiVersion='"
        + getApiVersion()
        + '\''
        + ", metadata="
        + getMetadata()
        + ", spec="
        + spec
        + '}';
  }

  public JobSpec getSpec() {
    return spec;
  }

  public void setSpec(JobSpec spec) {
    this.spec = spec;
  }
}
