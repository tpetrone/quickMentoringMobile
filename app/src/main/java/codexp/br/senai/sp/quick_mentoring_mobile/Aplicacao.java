package codexp.br.senai.sp.quick_mentoring_mobile;

import android.app.Application;
import android.content.Context;

/**
 * Created by tpetrone on 26/03/18.
 */

public class Aplicacao extends Application {
    private static Aplicacao app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Context getContext() {
        return app.getBaseContext();
    }
}
