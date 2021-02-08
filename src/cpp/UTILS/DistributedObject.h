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

#ifndef _DISTRIBUTEDOBJECT_H_
#define _DISTRIBUTEDOBJECT_H_

#include <UTILS/UTILSTypes.h>
#include <string>

UTILS_NAMESPACE_BEGIN

/**
   \internal

   This class simplifies access to the CORBA Name Service
*/
class DistributedObject
{

  protected:
    std::string _name;

    DistributedObject(void);
    DistributedObject(const DistributedObject& doo);
    DistributedObject(const std::string& name);

  public:
    virtual const std::string& getName(void) const;

    virtual ~DistributedObject();
};

UTILS_NAMESPACE_END

#endif

// Local Variables: ***
// mode:c++ ***
// End: ***
