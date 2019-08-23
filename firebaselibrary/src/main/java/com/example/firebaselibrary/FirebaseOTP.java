package com.example.firebaselibrary;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class FirebaseOTP {

    private static FirebaseAuth mAuth;
    private static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    static boolean status;
    static String message, code;


    public static String callBacks(String phoneNumber, Context context) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                (Activity) context,
                mCallbacks);


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                message = "Verification Completed";

                code = credential.getSmsCode();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                message = "Verification Failed";
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                message = "Code Sent";


            }
        };


        return code;

    }


}
