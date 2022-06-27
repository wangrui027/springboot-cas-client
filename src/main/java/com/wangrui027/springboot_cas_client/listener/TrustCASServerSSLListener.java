package com.wangrui027.springboot_cas_client.listener;

import javax.net.ssl.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * 信任所有ssl
 */
public class TrustCASServerSSLListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TrustManager[] trustAllCerts = new TrustManager[]{new TrustAllManager()};
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier hv = (urlHostName, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    static class TrustAllManager implements TrustManager, X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) {
            return;
        }
    }


}
