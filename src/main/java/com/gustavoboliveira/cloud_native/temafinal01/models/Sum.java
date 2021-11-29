package com.gustavoboliveira.cloud_native.temafinal01.models;

import com.gustavoboliveira.cloud_native.temafinal01.interfaces.Operation;

public class Sum implements Operation {

    @Override
    public double execute(double x, double y) {
        return x+y;
    }
}
