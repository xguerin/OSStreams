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

#include <cstdio>
#include <iostream>

#include <SPL/Core/MakeToolkit.h>
#include <SPL/Runtime/Utility/Visibility.h>
#include <UTILS/DistilleryApplication.h>
#include <dst-config.h>

using namespace std;
using namespace SPL;
using namespace Distillery;

PUSH_DLL_PUBLIC
MAIN_APP(MakeToolkit)
POP_DLL_PUBLIC
