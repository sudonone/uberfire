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

package org.uberfire.ext.preferences.backend;

import org.uberfire.ext.preferences.shared.PropertyFormType;
import org.uberfire.ext.preferences.shared.annotations.Property;
import org.uberfire.ext.preferences.shared.annotations.WorkbenchPreference;
import org.uberfire.ext.preferences.shared.bean.BasePreference;

@WorkbenchPreference(identifier = "MyPreference",
        category = "MyCategory",
        bundleKey = "MyPreference.Label",
        iconCss = "fa-gear")
public class MyPreference implements BasePreference<MyPreference> {

    @Property(bundleKey = "MyPreference.Text")
    String text;

    @Property(formType = PropertyFormType.BOOLEAN, bundleKey = "MyPreference.SendReports")
    boolean sendReports;

    @Property(formType = PropertyFormType.COLOR, bundleKey = "MyPreference.BackgroundColor")
    String backgroundColor;

    @Property(formType = PropertyFormType.NATURAL_NUMBER, bundleKey = "MyPreference.Age")
    int age;

    @Property(formType = PropertyFormType.SECRET_TEXT, bundleKey = "MyPreference.Password")
    String password;

    @Property(bundleKey = "MyPreference.MyInnerPreference")
    MyInnerPreference myInnerPreference;

    @Property(shared = true, bundleKey = "MyPreference.MySharedPreference")
    MySharedPreference mySharedPreference;

    @Override
    public MyPreference defaultValue( final MyPreference defaultValue ) {
        defaultValue.text = "text";
        defaultValue.sendReports = true;
        defaultValue.backgroundColor = "ABCDEF";
        defaultValue.age = 27;
        defaultValue.password = "password";
        defaultValue.myInnerPreference.text = "text";
        defaultValue.mySharedPreference.text = "text";
        defaultValue.mySharedPreference.myInnerPreference2.text = "text";

        return defaultValue;
    }
}