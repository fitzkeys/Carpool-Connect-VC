package com.example.carpoolas.view;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.carpoolas.model.Account;

public interface ICreateAccountView {

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        void onCreatePassengerListing(@NonNull int date, int time, String start, String end, int listingId, int seats, @NonNull ICreatePassengerLView view);

        /**
         * called when an account is created
         */
        void onCreateAccount(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view);
    }

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    View getRootView();

    /**
     * tells view to update display and show account created
     * @param acc
     */
    void updateAccountDisplay(Account acc);
}