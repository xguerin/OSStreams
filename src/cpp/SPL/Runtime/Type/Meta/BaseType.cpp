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

#include <SPL/Runtime/Type/Meta/BaseType.h>

#include <SPL/Runtime/Type/Meta/BaseTypeImpl.h>
#include <SPL/Runtime/Type/ValueHandle.h>

namespace SPL {
namespace Meta {

const BaseType& BaseType::makeType(const ConstValueHandle& vh)
{
    return BaseTypeFactory::makeType(vh);
}

std::ostream& operator<<(std::ostream& ostr, BaseType const& type)
{
    return (ostr << type.getName());
}

bool operator==(BaseType const& lhs, BaseType const& rhs)
{
    return &lhs == &rhs;
}

bool operator!=(BaseType const& lhs, BaseType const& rhs)
{
    return &lhs != &rhs;
}

};
};
