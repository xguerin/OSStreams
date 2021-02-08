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

/*
 * Implementation of the SPL::RedisConnection class.
 */

#include <SPL/Runtime/Common/RuntimeDebug.h>
#include <SPL/Runtime/Operator/State/Adapters/RedisAdapter/RedisConnection.h>
#include <SPL/Runtime/Operator/State/Adapters/RedisAdapter/RedisServer.h>
#include <SPL/Runtime/Operator/State/DataStoreException.h>
#include <assert.h>

using namespace std;
using namespace SPL;

RedisConnection::RedisConnection(RedisServer* server)
  : server_(server)
  , redisc_(NULL)
{
    assert(server != NULL);

    try {
        redisc_ = server_->getConnection();
        assert(redisc_ != NULL);
    } catch (DataStoreException const& e) {
        THROW_NESTED(DataStore, "Cannot create connection", e);
    }
}

RedisConnection::~RedisConnection()
{
    if (redisc_ != NULL) {
        try {
            server_->releaseConnection(redisc_);
        } catch (DataStoreException const& e) {
            APPTRC(L_DEBUG,
                   "Fail to destruct Redis connection: received exception: " << e.getExplanation(),
                   SPL_CKPT);
        }
    }
}
