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

package org.uberfire.ext.wires.client.preferences.central.actions;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Dependent
@Templated
public class PreferencesCentralActionsView implements IsElement,
                                                      PreferencesCentralActionsScreen.View {

    private PreferencesCentralActionsScreen presenter;

    @Inject
    @DataField( "preference-actions-save" )
    Button saveButton;

    @Inject
    @DataField( "preference-actions-cancel" )
    Button cancelButton;

    @Override
    public void init( final PreferencesCentralActionsScreen presenter ) {
        this.presenter = presenter;
    }

    @EventHandler("preference-actions-save")
    public void save( ClickEvent event ) {
        presenter.fireSaveEvent();
    }

    @EventHandler("preference-actions-cancel")
    public void undo( ClickEvent event ) {
        presenter.fireCancelEvent();
    }
}
