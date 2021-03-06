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

package org.uberfire.ext.widgets.common.client.breadcrumbs.widget;

import com.google.inject.Inject;
import org.jboss.errai.common.client.dom.Anchor;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.mvp.UberElement;
import org.uberfire.mvp.Command;

import javax.enterprise.context.Dependent;

@Templated
public class BreadcrumbsView implements UberElement<BreadcrumbsPresenter>,
        BreadcrumbsPresenter.View, IsElement {


    private BreadcrumbsPresenter presenter;

    @Inject
    @DataField
    Anchor breadcrumb;

    @Override
    public void init( BreadcrumbsPresenter presenter ) {
        this.presenter = presenter;
    }

    @Override
    public void setup( String label, Command clickCommand ) {
        breadcrumb.setTextContent( label );
        breadcrumb.setOnclick( ( e ) -> clickCommand.execute() );
    }

    @Override
    public void activate() {
        breadcrumb.setClassName( "active" );
    }

    @Override
    public void deactivate() {
        breadcrumb.setClassName( "" );
    }
}