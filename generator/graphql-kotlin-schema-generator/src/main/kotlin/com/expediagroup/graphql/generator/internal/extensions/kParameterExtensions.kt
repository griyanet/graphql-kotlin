/*
 * Copyright 2019 Expedia, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.expediagroup.graphql.generator.internal.extensions

import com.expediagroup.graphql.generator.exceptions.CouldNotGetNameOfKParameterException
import com.expediagroup.graphql.generator.execution.GraphQLContext
import graphql.schema.DataFetchingEnvironment
import kotlin.reflect.KParameter
import kotlin.reflect.full.isSubclassOf

internal fun KParameter.isInterface() = this.type.getKClass().isInterface()

internal fun KParameter.isList() = this.type.getKClass().isSubclassOf(List::class)

internal fun KParameter.isListType() = this.isList() || this.type.getJavaClass().isArray

internal fun KParameter.isGraphQLContext() = this.type.getKClass().isSubclassOf(GraphQLContext::class)

internal fun KParameter.isDataFetchingEnvironment() = this.type.classifier == DataFetchingEnvironment::class

@Throws(CouldNotGetNameOfKParameterException::class)
internal fun KParameter.getName(): String =
    this.getGraphQLName() ?: this.name ?: throw CouldNotGetNameOfKParameterException(this)
