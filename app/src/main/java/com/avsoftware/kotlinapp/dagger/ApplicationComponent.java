package com.avsoftware.kotlinapp.dagger;

import com.avsoftware.data.dagger.DataComponent;
import com.avsoftware.kotlinapp.dagger.modules.UIModule;

import dagger.Component;

@Component(dependencies = DataComponent.class, modules = {UIModule.class})

public interface ApplicationComponent {
}
