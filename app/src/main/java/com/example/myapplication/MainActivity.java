package com.example.myapplication;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;



// ... other imports and class definition ...

public class MainActivity extends AppCompatActivity {

    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new MyRecognitionListener());

        // Start listening for speech
        startListening();
    }

    private void startListening() {
        // Create the intent and set the language model to free form
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // Start listening
        speechRecognizer.startListening(intent);
    }

    // RecognitionListener to handle speech recognition events
    private class MyRecognitionListener implements RecognitionListener {
        @Override
        public void onReadyForSpeech(Bundle params) {
            // Called when the recognizer is ready to receive speech input.
        }

        @Override
        public void onBeginningOfSpeech() {
            // Called when the user has started to speak.
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            // Called when the RMS (root mean square) value of the audio input has changed.
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            // Called when partial recognition results are available.
        }

        @Override
        public void onEndOfSpeech() {
            // Called when the user has finished speaking.
            // You can start listening again if needed.
            startListening();
        }

        @Override
        public void onError(int error) {
            // Called when an error occurs.
            // Handle errors as needed.
            startListening(); // Restart listening after an error
        }

        @Override
        public void onResults(Bundle results) {
            // Called when recognition results are ready.
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (matches != null && !matches.isEmpty()) {
                // Check if the recognized speech contains the trigger phrase
                for (String result : matches) {
                    if (result.toLowerCase().contains("turn on the light")) {
                        // Perform an action when the trigger phrase is detected
                        System.out.println("Turning on...............");
                    } else {
                        System.out.println("Useless............... " + result);
                    }

                }
            }

            // Start listening again
            startListening();
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            // Called when partial recognition results are available.
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            // Called when an event related to the recognition is generated.
        }
    }

}
