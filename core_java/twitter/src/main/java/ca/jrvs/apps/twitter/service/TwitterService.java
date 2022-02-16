package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class TwitterService implements service {
    private final CrdDao<Tweet, String> dao;
    private static final int maxLength = 140;

    @Autowired
    public TwitterService(CrdDao<Tweet, String> dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        validatePostTweet(tweet);
        return dao.create(tweet);
    }
    private void validatePostTweet(Tweet tweet) {
        int lengthTweet = tweet.getText().length();
        Double lon = tweet.getCoordinates().getCoordinates().get(0);
        Double lat = tweet.getCoordinates().getCoordinates().get(1);

        // check if the tweet text exceeds 140 characters
        if (lengthTweet > maxLength) {
            throw new IllegalArgumentException("Tweet characters exceeds 140 characters!");
        }

        // check if lon/lat is out of range
        if (lon <= -180 || lon >= 80) {
            throw new IllegalArgumentException("Longitude-out of range");
        }

        if (lat <= -90 || lat >= 90) {
            throw new IllegalArgumentException("Latitude-out of range");
        }

    }
    @Override
    public Tweet showTweet(String id, String[] fields) {
        if (id == null) {
            throw new NullPointerException("No Id Provided!");
        }

        if (!id.matches("[0-9]+")) {
            throw new IllegalArgumentException("Incorrect ID format: " + id);
        }

        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect ID format : " + id);
        }

        return dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> deletedTweet = new ArrayList<>();
        for (String id : ids) {
            if (!id.matches("[0-9]+")) {
                throw new IllegalArgumentException("ID is not in correct format");
            } else {
                deletedTweet.add(dao.deleteById(id));
            }
        }
        return deletedTweet;
    }
}