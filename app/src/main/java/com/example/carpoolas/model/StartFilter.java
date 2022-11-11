package com.example.carpoolas.model;

import java.util.Iterator;

public class StartFilter implements IFilter{
    String dStart;

    @Override
    public PageOfListings filterListings(PageOfListings lst) {
        PageOfListings newPage = new PageOfListings();

        Iterator<Listing> listingsIterator = lst.listings.iterator();
        while (listingsIterator.hasNext()) {
            Listing listing = listingsIterator.next();
            if (this.dStart.equals(listing.startLocation)) {
                newPage.listings.add(listing);
            }
        }

        return newPage;
    }
}
