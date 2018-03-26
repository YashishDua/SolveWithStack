package com.example.lenovo.notify2;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements /*com.activeandroid.app.Application ,*/ SensorEventListener , GoogleApiClient.ConnectionCallbacks , GoogleApiClient.OnConnectionFailedListener {
    ArrayList<NotificationContent> notificationdata;
    GridView mNotificationListView;
    RecyclerView recyclerView;
    Notification_Listview_Adapter adapter;
    Notification_RCV_Adapter RCVadapter;

    EditText notification_message;
    EditText notification_location;
    EditText notification_latlong;
    EditText notification_alarm_inital_time , notificatioon_alarm_date ;
    EditText notification_SAVE_location_name , notification_SAVE_location_latlong;
    Button  getlatlong , notification_SAVE_location_button;
    Double lat , lon ;
    Button intialtime , date ;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    AlarmManager am;

    private long UPDATE_INTERVAL = 5 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    int mYear,mMonth,mDay,mHour,mMinute;

    View dialogView , SAVEdialogView;
    int IDUpdate ;

    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    Calendar calendar;

    DatePickerDialog dpd;
    TimePickerDialog tpd;
    ArrayList<LocationContent> locationContents;
    private SensorManager mSensorManager;
    private Sensor mSensor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.i("----",String.valueOf(mSensor)+"");
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);


        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

        locationContents = new ArrayList<>();



        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SAVEdialogView = View.inflate(MainActivity.this,R.layout.dialog_save_location,null);
                AlertDialog.Builder db = new AlertDialog.Builder(MainActivity.this);

                if(SAVEdialogView.getParent()!=null)
                    ((ViewGroup)dialogView.getParent()).removeView(dialogView);


                db.setView(SAVEdialogView);
                db.setTitle("Save a Location");

                notification_SAVE_location_name = (EditText)SAVEdialogView.findViewById(R.id.SAVE_edittext_notification_location_name);
                notification_SAVE_location_latlong = (EditText)SAVEdialogView.findViewById(R.id.SAVE_edittext_notification_lat_long_value);
                notification_SAVE_location_button = (Button)SAVEdialogView.findViewById(R.id.SAVE_button_get_current_location);

                notification_SAVE_location_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notification_SAVE_location_latlong.setText(String.valueOf(lat)+","+String.valueOf(lon));
                    }
                });

                db.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        locationContents.add(new LocationContent(String.valueOf(notification_SAVE_location_name.getText())
                                ,String.valueOf( notification_SAVE_location_latlong.getText())));
                        /*LocationContent content = new LocationContent(String.valueOf(notification_SAVE_location_name.getText())
                                ,String.valueOf( notification_SAVE_location_latlong.getText())));
                        content.sa*/
                        dialogInterface.dismiss();

                    }
                });
                db.create().show();


            }

        });


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks( this)
                .addOnConnectionFailedListener( this).build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        notificatioon_alarm_date.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);

        tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        notification_alarm_inital_time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);




        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();




        registerReceiver(broadcastReceiver, new IntentFilter("LocalNotify"));


        notificationdata = new ArrayList<>();

        FloatingActionButton fab_multiple = (FloatingActionButton) findViewById(R.id.fab1);
        fab_multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Clearing the fab from behind
                fab.startAnimation(rotate_backward);
                fab1.startAnimation(fab_close);
                fab2.startAnimation(fab_close);
                fab1.setClickable(false);
                fab2.setClickable(false);
                isFabOpen = false;
            //-------------------------------------------//

                dialogView = View.inflate(MainActivity.this,R.layout.dialog_add_notification, null);


                notification_message = (EditText)dialogView.findViewById(R.id.edittext_notification_message);
                notification_location = (EditText)dialogView.findViewById(R.id.edittext_notification_location_name);
                notification_latlong = (EditText)dialogView.findViewById(R.id.edittext_notification_lat_long_value);
                notification_alarm_inital_time = (EditText)dialogView.findViewById(R.id.edittext_notification_intital_time_range);
                notificatioon_alarm_date = (EditText)dialogView.findViewById(R.id.edittext_notification_date);
                intialtime = (Button) dialogView.findViewById(R.id.button_notification_intial_time_range);
                date = (Button) dialogView.findViewById(R.id.button_notification_date);


                AlertDialog.Builder db = new AlertDialog.Builder(MainActivity.this);
                if(dialogView.getParent()!=null)
                    ((ViewGroup)dialogView.getParent()).removeView(dialogView);


                    db.setView(dialogView);

                    db.setTitle("Add Notify");

                getlatlong = (Button)dialogView.findViewById(R.id.button_get_current_location);
                getlatlong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        notification_latlong.setText(String.valueOf(lat)+","+String.valueOf(lon));

                    }
                });

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dpd.show();

                    }
                });
                intialtime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tpd.show();
                    }
                });

                db.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String message = String.valueOf(notification_message.getText());
                        String location = String.valueOf(notification_location.getText());
                        String mInitialTime = String.valueOf(notification_alarm_inital_time.getText());
                        String date = String.valueOf(notificatioon_alarm_date.getText());

                        if(message==null && location==null && mInitialTime==null && date==null){
                            Toast.makeText(MainActivity.this , "Complete all detials" , Toast.LENGTH_SHORT).show();
                        }else {
                            String Initial[] = mInitialTime.split(":");
                            String dates[] = date.split("-");

                            NotificationContent c = new NotificationContent(message, location, String.valueOf(lat), String.valueOf(lon), mInitialTime, date, IDUpdate, 0);
                            notificationdata.add(c);
                            Log.i("----","Size before notifyng " + notificationdata.size());

                            RCVadapter.notifyItemInserted(notificationdata.indexOf(c));
                            Log.i("----","Size after notifyng " + notificationdata.size()+" data at 0"+notificationdata.get(0).Alarm_Date);

                            calendar.set(Integer.valueOf(dates[2]),Integer.valueOf(dates[1])-1,Integer.valueOf(dates[0]),Integer.valueOf(Initial[0]),Integer.valueOf(Initial[1]));


                            Intent intent = new Intent("SetAlarm");
                            intent.putExtra("ID",IDUpdate);
                            PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this , 0 ,intent,0);



                            IDUpdate++ ;
                            am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 60000 ,5000, pi);
                            Log.i("----","AlarmSetted!"+calendar.getTime());

                            dialog.dismiss();

                        }
                    }

                });
                db.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                db.create().show();
                }


        });


      //  mNotificationListView = (GridView) findViewById(R.id.listview_Notifications);

        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        RCVadapter = new Notification_RCV_Adapter(this,notificationdata);
        GridLayoutManager gm = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gm);
        recyclerView.setAdapter(RCVadapter);

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP, 0) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int  i = viewHolder.getAdapterPosition();
                int j = target.getAdapterPosition();
                NotificationContent ic1 = notificationdata.get(i);
                NotificationContent ic2 = notificationdata.get(j);
                NotificationContent temp ;
                temp = ic1;
                ic1 = ic2 ;
                ic2 = temp ;
                notificationdata.set(i,ic2);
                notificationdata.set(j,ic1);
                RCVadapter.notifyItemMoved(i,j);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);




        /*adapter = new Notification_Listview_Adapter(this , notificationdata);
        mNotificationListView.setAdapter(adapter);
*/
    }

   BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("----","LocalBroadcastRecevied");
            int id = intent.getIntExtra("ID",-1);
            setAlarm(id);
        }
    };



    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;


        }
    }


    protected void onStart() {
        super.onStart();
        // Connect the client.
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        // Disconnecting the client invalidates it.
        //  LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, );

        // only stop if it's connected, otherwise we crash
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(MainActivity.this);

        super.onDestroy();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Get last known recent location.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        // Note that this can be NULL if last location isn't already known.
        if (mCurrentLocation != null) {
            // Print current location if not null
            Log.d("DEBUG", "current location: " + mCurrentLocation.toString());
            // LatLng latLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        }
        // Begin polling for new location updates.
        startLocationUpdates();

    }

    @Override
    public void onConnectionSuspended(int i) {

        if (i == CAUSE_SERVICE_DISCONNECTED) {
            Toast.makeText(this, "Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
        } else if (i == CAUSE_NETWORK_LOST) {
            Toast.makeText(this, "Network lost. Please re-connect.", Toast.LENGTH_SHORT).show();
        }
    }

    // Trigger new location updates at interval
    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        getlocation(location.getLatitude() , location.getLongitude() );
                    }
                });
    }
    public void getlocation(Double lat , Double lon){
        Log.i("----","getLocation");

        this.lat = lat;
        this.lon = lon;


    }
    public void setAlarm(int DeleteId){

       // Checking Alarm Location





       for(NotificationContent n : notificationdata){
           Log.i("----","entered loop");
           Log.i("----",n.getNotification_Latitude()+"");
           Double la = Double.valueOf(n.getNotification_Latitude());
           Double lo = Double.valueOf(n.getNotification_Longitude());

           Double latitude = new BigDecimal(la)
                   .setScale(3, BigDecimal.ROUND_HALF_UP)
                   .doubleValue();
           Double longitude = new BigDecimal(lo)
                   .setScale(3, BigDecimal.ROUND_HALF_UP)
                   .doubleValue();
           Double UPDATEDlatitude = new BigDecimal(lat)
                   .setScale(3, BigDecimal.ROUND_HALF_UP)
                   .doubleValue();
           Double UPDATEDlongitude = new BigDecimal(lon)
                   .setScale(3, BigDecimal.ROUND_HALF_UP)
                   .doubleValue();
           Log.i("----",String.valueOf(latitude)+String.valueOf(longitude)+String.valueOf(UPDATEDlatitude)+"");


           if(latitude.compareTo(UPDATEDlatitude)==0 && longitude.compareTo(UPDATEDlongitude)==0 ){
               Log.i("----","location matched");
              // Toast.makeText(this,"YOU REACHED",Toast.LENGTH_LONG).show();

               if(Constants.alarm_count!=0){
                   return;
               }else {

                   Intent intent = new Intent("SetAlarm");
                   PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this , DeleteId ,intent,0 );

                   am.cancel(pi);
                   Log.i("----","Alarm Canceled for Id"+DeleteId);


                   Intent i = new Intent(this, AlaramOpen.class);
                   i.putExtra("MESSAGE",n.Notifiaction_Name);
                   startActivity(i);
                   Constants.alarm_count++;
               }
           }

       }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    int count = 0;

    public void changeColor(){
        Log.i("----","Changing Color");
        Log.i("----",String.valueOf(notificationdata.size()) + notificationdata.isEmpty()+"");


        for(NotificationContent c : notificationdata){
            if(count%2==0){
                c.setItem_color_ID(Constants.ColorID1);
            }else {
                c.setItem_color_ID(Constants.ColorID2);

            }
            count++;
        }
        RCVadapter.notifyDataSetChanged();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*Log.i("----","x "+String.valueOf(sensorEvent.values[0])+"");

        Log.i("----","y "+String.valueOf(sensorEvent.values[1])+"");
        Log.i("----","z "+String.valueOf(sensorEvent.values[2])+"");
        */if(sensorEvent.values[2]>15.00 && sensorEvent.values[0]>0){
            changeColor();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
