package com.acecoder.test.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.acecoder.datastore.bean.User;
import com.acecoder.datastore.greendao.utils.DaoManager;
import com.acecoder.datastore.greendao.utils.DaoUtilsStore;
import com.acecoder.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GreenDaoTestActivity extends ComponentActivity {

    private static String TAG = GreenDaoTestActivity.class.getSimpleName();

    private Button mBtQuery;
    private Button mBtAdd;
    private Button mBtUpdate;
    private Button mBtDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao_test);
        initView();
        DaoManager.getInstance().init(getApplication());
        initUser();
    }

    public void initView() {
        mBtQuery = findViewById(R.id.query);
        mBtUpdate = findViewById(R.id.update);
        mBtAdd = findViewById(R.id.add);
        mBtDelete = findViewById(R.id.delete);

        initListener();
    }

    public void initListener() {
        mBtQuery.setOnClickListener((view) -> {
            queryAllUser();
        });

        mBtUpdate.setOnClickListener((view) -> {
            updateUser();
        });

        mBtAdd.setOnClickListener((view) -> {
            insertUser();
        });

        mBtDelete.setOnClickListener((view) -> {
            deleteUser();
        });
    }

    private void initUser() {
        DaoUtilsStore.getInstance().getUserDaoUtils().deleteAll();

        List<User> userList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((long) i);
            user.setUserId("10086-" + i);
            user.setUserName("测试用户" + random.nextInt());
//            user.setAge(18 + random.nextInt(10));
            userList.add(user);
        }

        boolean success = DaoUtilsStore.getInstance().getUserDaoUtils().insertMultiple(userList);
        Log.i(TAG, "initUser: " + success);
        if (success) {
            for (User user : userList) {
                Log.i(TAG, "Id: " + user.getId()
                        + "  UserId: " + user.getUserId()
                        + "  UserName: " + user.getUserName());
//                        + "  userAge: " + user.getAge());
            }
        }
    }

    private void queryAllUser() {
        List<User> userList = DaoUtilsStore.getInstance().getUserDaoUtils().queryAll();
        Log.i(TAG, "queryAllUser:");
        for (User user : userList) {
            Log.i(TAG, "Id: " + user.getId()
                    + "  UserId: " + user.getUserId()
                    + "  UserName: " + user.getUserName());
//                    + "  userAge: " + user.getAge());
        }
    }

    private void insertUser() {
        User user = new User();
        Random random = new Random();
        int id = random.nextInt(100);
        user.setId((long) id);
        user.setUserId("10086-" + id);
        user.setUserName("测试用户" + random.nextInt());
//        user.setAge(18 + random.nextInt(10));

        // 插入新用户
        boolean success = DaoUtilsStore.getInstance().getUserDaoUtils().insert(user);
        Log.i(TAG, "insertUser: " + success);
        if (success) {
            Log.i(TAG, "Id: " + user.getId()
                    + "  UserId: " + user.getUserId()
                    + "  UserName: " + user.getUserName());
//                    + "  userAge: " + user.getAge());
        }
    }

    private void updateUser() {
        List<User> userList = DaoUtilsStore.getInstance().getUserDaoUtils().queryAll();
        if (userList != null && userList.size() > 0) {
            User user = userList.get(userList.size() - 1);
            user.setUserName("修改用户" + new Random().nextInt());
            boolean success = DaoUtilsStore.getInstance().getUserDaoUtils().update(user);
            Log.i(TAG, "updateUser: " + success);
        }
    }

    private void deleteUser() {
        List<User> userList = DaoUtilsStore.getInstance().getUserDaoUtils().queryAll();
        if (userList != null && userList.size() > 0) {
            User user = userList.get(userList.size() - 1);
            boolean success = DaoUtilsStore.getInstance().getUserDaoUtils().delete(user);
            Log.i(TAG, "deleteUser: " + success);
        }
    }
}
