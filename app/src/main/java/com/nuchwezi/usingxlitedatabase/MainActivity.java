package com.nuchwezi.usingxlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.nuchwezi.xlitedatabase.DBAdapter;

public class MainActivity extends AppCompatActivity {

    // we shall use the XLiteDatabase layer for managing an in-app data cache
    DBAdapter dbAdapter;

    // Have some keys that will be used to store or read stuff from the XLDB dictionary-like interface
    public class CACHE_KEYS {
        public static final String VARIABLE_A = "A";
        public static final String VARIABLE_B = "B";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the XLDAL
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        // EXAMPLE 1: Store a strings into the XLDB
        String someValue = "TESTING";
        dbAdapter.cacheSET(CACHE_KEYS.VARIABLE_A, "testing"); // store literal string
        dbAdapter.cacheSET(CACHE_KEYS.VARIABLE_B, someValue); // store referenced string


        // EXAMPLE 2: Fetch a string from the XLDB
        assert ("testing" == dbAdapter.cacheGET(CACHE_KEYS.VARIABLE_A));
        assert (someValue == dbAdapter.cacheGET(CACHE_KEYS.VARIABLE_B));

        // EXAMPLE 3: Delete string from XLDB
        if(dbAdapter.cacheDELETE(CACHE_KEYS.VARIABLE_B)){
            assert (null == dbAdapter.cacheGET(CACHE_KEYS.VARIABLE_B));
        }
        // if tests passed (no errors), xlitedatabase is working fine.

        dbAdapter.cacheSET(CACHE_KEYS.VARIABLE_A, "XLite Database Works!");
        ((TextView)findViewById(R.id.txtTestStatus)).setText(dbAdapter.cacheGET(CACHE_KEYS.VARIABLE_A));
    }
}
