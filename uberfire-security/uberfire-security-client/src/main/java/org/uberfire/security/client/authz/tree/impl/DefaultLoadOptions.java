/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uberfire.security.client.authz.tree.impl;

import java.util.Collection;

import org.uberfire.security.client.authz.tree.LoadOptions;

public class DefaultLoadOptions implements LoadOptions {

    private String nodeNamePattern;
    private Collection<String> resourceIds;
    private int maxNodes;

    @Override
    public String getNodeNamePattern() {
        return nodeNamePattern;
    }

    public void setNodeNamePattern(String nodeNamePattern) {
        this.nodeNamePattern = nodeNamePattern;
    }

    public void setResourceIds(Collection<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setMaxNodes(int maxNodes) {
        this.maxNodes = maxNodes;
    }

    public Collection<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public int getMaxNodes() {
        return maxNodes;
    }
}
