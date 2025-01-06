package com.example.myapplication;

import org.json.JSONObject;

import java.util.List;


//REQUEST MODEL
public class OpenAIRequest {
    private String model; // The model to be used (e.g., "gpt-4")
    private List<Message> messages; // The conversation history
    private int max_tokens; // Maximum number of tokens in the response
    private double temperature; // Controls randomness (0.0 = deterministic, 1.0 = more random)

    // Constructor
    public OpenAIRequest(String model, List<Message> messages, int max_tokens, double temperature) {
        this.model = model;
        this.messages = messages;
        this.max_tokens = max_tokens;
        this.temperature = temperature;
    }

    // Inner class for messages
    public static class Message {
        private String role; // "system", "user", or "assistant"
        private String content; // The message content

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        // Getters and setters (optional)
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    // Getters and setters for OpenAIRequest (optional)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getMaxTokens() {
        return max_tokens;
    }

    public void setMaxTokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


}
