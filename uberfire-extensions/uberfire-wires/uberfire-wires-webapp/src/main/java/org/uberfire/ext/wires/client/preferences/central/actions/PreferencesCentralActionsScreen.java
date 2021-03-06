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

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.uberfire.client.annotations.DefaultPosition;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.UberElement;
import org.uberfire.ext.preferences.client.event.PreferencesCentralPreSaveEvent;
import org.uberfire.ext.preferences.client.event.PreferencesCentralSaveEvent;
import org.uberfire.ext.preferences.client.event.PreferencesCentralUndoChangesEvent;
import org.uberfire.ext.wires.client.preferences.central.hierarchy.HierarchyStructureView;
import org.uberfire.ext.wires.client.preferences.settings.SettingsPerspective;
import org.uberfire.workbench.events.NotificationEvent;
import org.uberfire.workbench.model.CompassPosition;

@WorkbenchScreen( identifier = PreferencesCentralActionsScreen.IDENTIFIER )
public class PreferencesCentralActionsScreen {

    public static final String IDENTIFIER = "PreferencesCentralActionsScreen";

    public interface View extends HierarchyStructureView,
                                  UberElement<PreferencesCentralActionsScreen> {

    }

    private final View view;

    private final PlaceManager placeManager;

    private final Event<PreferencesCentralPreSaveEvent> preSaveEvent;

    private final Event<PreferencesCentralSaveEvent> saveEvent;

    private final Event<PreferencesCentralUndoChangesEvent> undoChangesEvent;

    private final Event<NotificationEvent> notification;

    @Inject
    public PreferencesCentralActionsScreen( final View view,
                                            final PlaceManager placeManager,
                                            final Event<PreferencesCentralPreSaveEvent> preSaveEvent,
                                            final Event<PreferencesCentralSaveEvent> saveEvent,
                                            final Event<PreferencesCentralUndoChangesEvent> undoChangesEvent,
                                            final Event<NotificationEvent> notification ) {
        this.view = view;
        this.placeManager = placeManager;
        this.preSaveEvent = preSaveEvent;
        this.saveEvent = saveEvent;
        this.undoChangesEvent = undoChangesEvent;
        this.notification = notification;
    }

    @PostConstruct
    public void init() {
        view.init( this );
    }

    public void fireSaveEvent() {
        preSaveEvent.fire( new PreferencesCentralPreSaveEvent() );
        saveEvent.fire( new PreferencesCentralSaveEvent() );
        goBackToHome();
    }

    public void fireCancelEvent() {
        undoChangesEvent.fire( new PreferencesCentralUndoChangesEvent() );
        notification.fire( new NotificationEvent( "Changes undone successfully!", NotificationEvent.NotificationType.SUCCESS ) );
        goBackToHome();
    }

    private void goBackToHome() {
        placeManager.goTo( SettingsPerspective.IDENTIFIER );
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "";
    }

    @WorkbenchPartView
    public HierarchyStructureView getView() {
        return view;
    }

    @DefaultPosition
    public CompassPosition getDefaultPosition() {
        return CompassPosition.SOUTH;
    }
}