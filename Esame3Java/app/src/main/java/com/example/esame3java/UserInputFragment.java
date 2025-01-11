package com.example.esame3java;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class UserInputFragment extends Fragment {

    public interface OnMessageSendListener {
        void onMessageSend(String message);
    }
    private OnMessageSendListener messageSendListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Ensure the Activity implements the interface
        if (context instanceof OnMessageSendListener) {
            messageSendListener = (OnMessageSendListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnMessageSendListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_user_input, container, false);

        // Initialize UI components
        EditText editTextName = view.findViewById(R.id.editTextName);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);

        // Set button click listener
        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            if (!name.isEmpty()) {
                messageSendListener.onMessageSend(name);
            } else {
                Toast.makeText(getContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}