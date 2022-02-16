package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Controller
public class tcontroller implements controller {

    private static final String COORDINATE_SEP = ":";
    private static final String COMMA = ",";

    private final service service;

    @Autowired
    public tcontroller(service service) {
        this.service = service;
    }

    /**
     * Parse user argument and post a tweet by calling service class
     *
     * @param args Text, lat, lon
     * @return post a tweet
     * @throws IllegalArgumentException fi the args are invalid
     */
    @Override
    public Tweet postTweet(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "USAGE: TwitterCLIApp post \"text\" \"latitude:longitude\"");
        }

        float lon;
        float lat;
        String text = args[1];
        String coordinate = args[2];
        String[] coordinates = coordinate.split(COORDINATE_SEP);

        if (coordinates.length != 2 || text.isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid format\nUSAGE: TwitterCLIApp post \"text\" \"latitude:longitude\"");
        }

        try {
            lon = Float.parseFloat(coordinates[0]);
            lat = Float.parseFloat(coordinates[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid location format", e);
        }

        Tweet postTweet = TweetUtil.createTweet(text, lon, lat);
        return service.postTweet(postTweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        String[] fields = null;
        if (args.length < 2) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"id\" \"options..\"");
        }
        String id = args[1];

        if (args.length > 2) {
            String field = args[2];
            fields = field.split(COMMA);
        }

        return service.showTweet(id, fields);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp delete \"id\" \"options..\"");
        }
        String id = args[1];
        String[] ids = id.split(COMMA);
        return service.deleteTweets(ids);
    }

}