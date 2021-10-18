package fpt.edu.sharedpreferencesdemo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataLocalManager {

    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static final String PREF_NAME_USER = "PREF_NAME_USER";
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static final String PREF_LIST_USER = "PREF_LIST_USER";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFirstInstalled(boolean isFirst){
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PREF_FIRST_INSTALL, isFirst);
    }

    public static boolean getFirstInstalled(){
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(PREF_FIRST_INSTALL);
    }

    public static void setNameUser(Set<String> nameUsers){
        DataLocalManager.getInstance().mySharedPreferences.putStringSetValue(PREF_NAME_USER, nameUsers);
    }

    public static Set<String> getNameUser(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringSetValue(PREF_NAME_USER);
    }

    public static void setUser(User user){
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_OBJECT_USER, strJsonUser);
    }

    public static User getUser(){
        String strJsonUser = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strJsonUser, User.class);
        return user;
    }

    public static void setListUsers(List<User> listUsers){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(listUsers).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_LIST_USER, strJsonArray);
    }

    public static List<User> getListUsers(){
        String strJsonArray = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_LIST_USER);
        List<User> listUsers = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            User user;
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                user = gson.fromJson(jsonObject.toString(), User.class);
                listUsers.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listUsers;
    }
}
