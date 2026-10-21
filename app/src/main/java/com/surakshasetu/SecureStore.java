package com.surakshasetu;

import android.content.Context;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/** Small encrypted local store for evidence text. Nothing is uploaded. */
public final class SecureStore {
    private static final String ALIAS="surakshasetu_evidence_key"; private static final String PREF="secure_evidence"; private SecureStore(){}
    private static SecretKey key() throws Exception { KeyStore ks=KeyStore.getInstance("AndroidKeyStore");ks.load(null);if(!ks.containsAlias(ALIAS)){KeyGenerator g=KeyGenerator.getInstance("AES","AndroidKeyStore");g.init(256);g.generateKey();}return ((KeyStore.SecretKeyEntry)ks.getEntry(ALIAS,null)).getSecretKey(); }
    public static void put(Context c,String value){try{Cipher x=Cipher.getInstance("AES/GCM/NoPadding");x.init(Cipher.ENCRYPT_MODE,key());String v=Base64.encodeToString(x.getIV(),Base64.NO_WRAP)+":"+Base64.encodeToString(x.doFinal(value.getBytes(StandardCharsets.UTF_8)),Base64.NO_WRAP);c.getSharedPreferences(PREF,0).edit().putString("capsules",v).apply();}catch(Exception ignored){}}
    public static String get(Context c){try{String v=c.getSharedPreferences(PREF,0).getString("capsules","");if(v.isEmpty())return "";String[] p=v.split(":",2);Cipher x=Cipher.getInstance("AES/GCM/NoPadding");x.init(Cipher.DECRYPT_MODE,key(),new GCMParameterSpec(128,Base64.decode(p[0],Base64.NO_WRAP)));return new String(x.doFinal(Base64.decode(p[1],Base64.NO_WRAP)),StandardCharsets.UTF_8);}catch(Exception e){return "";}}
}
