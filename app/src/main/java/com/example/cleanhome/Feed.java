package com.example.cleanhome;

public class Feed {

    String feedback;

    String date;

    String feedbackGiver;

    String feedbackReceiver;



    public Feed(String feedback, String date, String feedbackGiver, String feedbackReceiver) {

        this.feedback = feedback;

        this.date = date;

        this.feedbackGiver = feedbackGiver;

        this.feedbackReceiver = feedbackReceiver;

    }



    public String getFeedback() {

        return feedback;
    }

    public void setFeedback(String feedback) {

        this.feedback = feedback;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getFeedbackGiver() {

        return feedbackGiver;
    }

    public void setFeedbackGiver(String feedbackGiver) {

        this.feedbackGiver = feedbackGiver;
    }

    public String getFeedbackReceiver() {

        return feedbackReceiver;
    }

    public void setFeedbackReceiver(String feedbackReceiver) {

        this.feedbackReceiver = feedbackReceiver;
    }


}
