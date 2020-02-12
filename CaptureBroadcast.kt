package com.example.echo.utils

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.example.echo.R
import com.example.echo.activities.MainActivity
import com.example.echo.fragments.SongsPlayingFragment

class CaptureBroadcast :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_NEW_OUTGOING_CALL){
            try {
                MainActivity.Statified.notificationManager?.cancel(2019)
            }catch (e:Exception){
                e.printStackTrace()
            }
            try {
                    if (SongsPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                        SongsPlayingFragment.Statified.mediaplayer?.pause()
                        SongsPlayingFragment.Statified.playpauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                    }
            }catch (e:Exception){
                    e.printStackTrace()
                }
        }else{
            val tm:TelephonyManager=context?.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
            when(tm?.callState){
                TelephonyManager.CALL_STATE_RINGING->{
                    try {
                        MainActivity.Statified.notificationManager?.cancel(2019)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                    try{
                        if (SongsPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                            SongsPlayingFragment.Statified.mediaplayer?.pause()
                            SongsPlayingFragment.Statified.playpauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
                else->{

                }
            }
        }
    }
}