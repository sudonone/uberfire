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

package org.uberfire.ext.wires.client.preferences.central.tree;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.uberfire.client.mvp.UberElement;
import org.uberfire.ext.preferences.shared.bean.PreferenceHierarchyElement;
import org.uberfire.ext.preferences.client.event.HierarchyItemSelectedEvent;
import org.uberfire.ext.wires.client.preferences.central.hierarchy.HierarchyItemView;
import org.uberfire.ext.wires.client.preferences.central.hierarchy.HierarchyLeafItemPresenter;

public class TreeHierarchyLeafItemPresenter implements HierarchyLeafItemPresenter {

    public interface View extends HierarchyItemView,
                                  UberElement<TreeHierarchyLeafItemPresenter> {

        void select();
    }

    private final View view;

    private final Event<HierarchyItemSelectedEvent> hierarchyItemSelectedEvent;

    private PreferenceHierarchyElement<?> hierarchyElement;

    @Inject
    public TreeHierarchyLeafItemPresenter( final View view,
                                           final Event<HierarchyItemSelectedEvent> hierarchyItemSelectedEvent ) {
        this.view = view;
        this.hierarchyItemSelectedEvent = hierarchyItemSelectedEvent;
    }

    @Override
    public <T> void init( final PreferenceHierarchyElement<T> preference ) {
        hierarchyElement = preference;
        view.init( this );
    }

    @Override
    public void fireSelect() {
        view.select();
    }

    public void select() {
        final HierarchyItemSelectedEvent event = new HierarchyItemSelectedEvent( hierarchyElement );
        hierarchyItemSelectedEvent.fire( event );
    }

    public void itemSelectedEvent( @Observes HierarchyItemSelectedEvent event ) {
        if ( !hierarchyElement.getId().equals( event.getItemId() ) ) {
            view.deselect();
        }
    }

    @Override
    public View getView() {
        return view;
    }

    public PreferenceHierarchyElement<?> getHierarchyElement() {
        return hierarchyElement;
    }
}