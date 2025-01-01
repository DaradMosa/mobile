package com.example.myapplication;

import java.util.List;
import java.util.Map;


//RESPONSE MODEL
public class OpenAIResponse {
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public static class Choice {
        private Map<String, Object> message;

        public Map<String, Object> getMessage() {
            return message;
        }
    }
}
