package smartadapter.internal.exception;

/*
 * Created by Manne Öhlund on 2019-07-17.
 * Copyright (c) All rights reserved.
 */

import android.view.View;
import android.view.ViewGroup;

/**
 * Exception indicates that the constructor of a target class with specified params was not found.
 */
public final class ConstructorNotFoundException extends RuntimeException {

    public ConstructorNotFoundException(Class<?> viewHolderClass) {
        super(String.format("Constructor for '%s' with only one valid parameter '%s' or '%s' not found", viewHolderClass, View.class.getName(), ViewGroup.class.getName()));
    }
}
