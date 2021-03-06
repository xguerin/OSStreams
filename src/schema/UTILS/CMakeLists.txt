#
# Copyright 2021 IBM Corporation
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

set(CMAKE_POSITION_INDEPENDENT_CODE 1)
include_directories(${XercesC_INCLUDE_DIR})

set(XSD_FLAGS cxx-tree
  --proprietary-license
  --generate-serialization
  --generate-inline
  --generate-default-ctor
  --generate-ostream
  --generate-doxygen
  --hxx-suffix .h
  --cxx-suffix .cpp)

file(GLOB MODELS RELATIVE ${CMAKE_CURRENT_SOURCE_DIR} *.xsd)
list(REMOVE_ITEM MODELS Exception.xsd)

foreach(MODEL ${MODELS})
  get_filename_component(BASENAME ${MODEL} NAME_WE)
  XSD(${BASENAME} SOURCES XSD_FLAGS)
endforeach()

add_custom_target(schema_utils_xsd DEPENDS ${SOURCES})
add_library(schema_utils OBJECT ${SOURCES})
add_dependencies(schema_utils schema_sam_xsd schema_spl_core_xsd)

XSD(Exception APPUTILS_SOURCES XSD_FLAGS)
add_custom_target(schema_apputils_xsd DEPENDS ${APPUTILS_SOURCES})
add_library(schema_apputils OBJECT ${APPUTILS_SOURCES})
