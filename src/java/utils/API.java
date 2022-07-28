/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;
import java.util.logging.Level;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.json.JSONArray;

/**
 *
 * @author ptuan
 */
public class API {

    public static int putApi(String type, String id, String name, Vector<String> keys, Vector<String> values, String token) {
        int n = 0;
        try {
            URI dummyUri = new URI("https://gitlab.com/api/v4/" + type + "/" + id + "/" + name);
            HttpClient client = HttpClientBuilder.create().build();
            RequestBuilder build = RequestBuilder.put().setUri(dummyUri);

            //set keys and values for uri
            for (int i = 0; i < keys.size(); i++) {
                build.addParameter(keys.get(i), values.get(i));
            }

            HttpUriRequest httpUriRequest = build.build();
            httpUriRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            try {
                HttpResponse respon = client.execute(httpUriRequest);
                n = respon.getStatusLine().getStatusCode();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static int postApi(String type, String id, String name, Vector<String> keys, Vector<String> values, String token) {
        int n = 0;
        try {
            URI dummyUri = new URI("https://gitlab.com/api/v4/" + type + "/" + id + "/" + name);
            HttpClient client = HttpClientBuilder.create().build();
            RequestBuilder build = RequestBuilder.post().setUri(dummyUri);

            //set keys and values for uri
            for (int i = 0; i < keys.size(); i++) {
                build.addParameter(keys.get(i), values.get(i));
            }

            HttpUriRequest httpUriRequest = build.build();
            httpUriRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            try {
                HttpResponse respon = client.execute(httpUriRequest);
                n = respon.getStatusLine().getStatusCode();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static JSONArray getJsonArray(String type, String id, String name, String token) {
        JSONArray json = new JSONArray();
        int count = 1;
        boolean check = true;
        try {
            while (check) {
                URI dummyUri = new URI("https://gitlab.com/api/v4/" + type + "/" + id + "/" + name);
                HttpClient client = HttpClientBuilder.create().build();
                HttpUriRequest httpUriRequest = RequestBuilder.get()
                        .setUri(dummyUri)
                        .addParameter("page", count + "")
                        .build();
                httpUriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                httpUriRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                try {
                    HttpResponse respon = client.execute(httpUriRequest);
                    if (respon.getStatusLine().getStatusCode() != 200) {
                        return null;
                    }

                    String source = IOUtils.toString(respon.getEntity().getContent(), "UTF-8");
                        if ("[]".equals(source)) {
                        check = false;
                    } else {
                        JSONArray json1 = new JSONArray(source);
                        for (int i = 0; i < json1.length(); i++) {
                            json.put(json1.get(i));
                        }
                        count++;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public static JSONArray getJsonArrayLinks(String type, String id, String name, String token, int iid, String links) {
        JSONArray json = new JSONArray();
        int count = 1;
        try {
            URI dummyUri = new URI("https://gitlab.com/api/v4/" + type + "/" + id + "/" + name + "/" + iid + "/" + links);
            HttpClient client = HttpClientBuilder.create().build();
            System.out.println(count);
            HttpUriRequest httpUriRequest = RequestBuilder.get()
                    .setUri(dummyUri)
                    .addParameter("page", count + "")
                    .build();
            httpUriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpUriRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

            try {
                HttpResponse respon = client.execute(httpUriRequest);
                if (respon.getStatusLine().getStatusCode() != 200) {
                    return null;
                }
                String source = IOUtils.toString(respon.getEntity().getContent(), "UTF-8");
                System.out.println(source);
                json = new JSONArray(source);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
