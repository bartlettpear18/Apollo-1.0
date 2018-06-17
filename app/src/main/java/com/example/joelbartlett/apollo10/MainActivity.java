package com.example.joelbartlett.apollo10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.slamtec.slamware.AbstractSlamwarePlatform;
import com.slamtec.slamware.action.IMoveAction;
import com.slamtec.slamware.action.MoveDirection;
import com.slamtec.slamware.discovery.DeviceManager;
import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.InvalidArgumentException;
import com.slamtec.slamware.exceptions.OperationFailException;
import com.slamtec.slamware.exceptions.ParseInvalidException;
import com.slamtec.slamware.exceptions.RequestFailException;
import com.slamtec.slamware.exceptions.UnauthorizedRequestException;
import com.slamtec.slamware.exceptions.UnsupportedCommandException;
import com.slamtec.slamware.robot.MoveOption;
import com.slamtec.slamware.robot.Pose;

public class MainActivity extends AppCompatActivity {

    private String TAG = "debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AbstractSlamwarePlatform robotPlatform = DeviceManager.connect("192.168.11.1", 1445);
        try {
            Pose pose = robotPlatform.getPose();
            pose.getLocation();
            Log.d(TAG, "Current location: " + pose.getLocation().toString());
            Pose p2 = pose;
            p2.setX(pose.getX() + 100);
            robotPlatform.moveBy(MoveDirection.FORWARD);
            Log.d(TAG, "Moving forward for 1 second");
            wait(1000);
            robotPlatform.moveBy(MoveDirection.FORWARD).cancel();
            Log.d(TAG, "Canceled moveement");
//            robotPlatform.moveTo(p2.getLocation());

            Log.d(TAG, "Connection complete");
        } catch (RequestFailException e) {
            e.printStackTrace();
        } catch (ConnectionFailException e) {
            e.printStackTrace();
        } catch (ConnectionTimeOutException e) {
            e.printStackTrace();
        } catch (UnauthorizedRequestException e) {
            e.printStackTrace();
        } catch (UnsupportedCommandException e) {
            e.printStackTrace();
        } catch (ParseInvalidException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (OperationFailException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
