package codexp.br.senai.sp.quick_mentoring_mobile.config;

import java.io.IOException;

import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.rest.RestInterface;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    // atributo
    private final Retrofit retrofit;

    // Nossa configuração será feita no construtor
    public RetrofitConfig() {

        // Precisamos construir um objeto do tipo retrofit
        this.retrofit = new Retrofit.Builder()
                // definimos a url base da nossa aplicação
                .baseUrl(AppUtils.API_BASE_URL)
                // precisamos transformar a nossa resposta que vem em JSON para String
                .addConverterFactory(GsonConverterFactory.create())
                // precisamos de fato criá-lo
                .build();

    }

    public RetrofitConfig(final String token) {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder b = chain.request().newBuilder();
                b.addHeader("Accept", "application/json");
                b.addHeader("Authorization", token);
                return chain.proceed(b.build());
            }
        }).build();

        // Precisamos construir um objeto do tipo retrofit
        this.retrofit = new Retrofit.Builder()
                // definimos a url base da nossa aplicação
                .baseUrl(AppUtils.API_BASE_URL)
                // caso seja necessário colocar um interceptor
                .client(okHttpClient)
                // precisamos transformar a nossa resposta que vem em JSON para String
                .addConverterFactory(GsonConverterFactory.create())
                // precisamos de fato criá-lo
                .build();

    }

    public RestInterface getRestInterface() {
        return this.retrofit.create(RestInterface.class);
    }

}
