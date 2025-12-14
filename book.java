package com.example.bookapp.models; 
public class book { 
private String title; 
private String authors; 
private String thumbnail; 
private String description; 
public book(String title, String authors, String thumbnail, String 
description) { 
this.title = title; 
this.authors = authors; 
this.thumbnail = thumbnail; 
this.description = description; 
} 
public String getTitle() { return title; } 
public String getAuthors() { return authors; } 
public String getThumbnail() { return thumbnail; } 
public String getDescription() { return description; } 
} 
