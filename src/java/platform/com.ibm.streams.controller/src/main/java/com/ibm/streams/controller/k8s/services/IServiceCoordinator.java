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

package com.ibm.streams.controller.k8s.services;

import com.ibm.streams.controller.crds.jobs.Job;
import com.ibm.streams.controller.events.IEventConsumerDelegate;
import com.ibm.streams.controller.instance.sam.InputPortLabel;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServicePort;
import java.util.List;

public interface IServiceCoordinator extends IEventConsumerDelegate<Service> {

  void addUserServicePort(Service svc, ServicePort port);

  void deleteUserServicePort(Service svc, String epName);

  void updateService(Job job, Service svc, List<InputPortLabel> ips);
}
