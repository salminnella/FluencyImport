package com.example.fluency.driveimport.async;

import org.json.JSONObject;

/**
 * Created by anthony on 10/24/16.
 */

public interface AsyncResult {
    void onResult(JSONObject object);
}
