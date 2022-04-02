package com.example.anagram

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName ("Posts")
class Post : ParseObject(){
    // Every post object has three attributes - Description, Image, User (who made it)
    // Create getter and setter for these
    fun getDescription() : String? {
        return getString(KEY_DESCRIPTION)
    }
    fun setDescription(description : String){
        put(KEY_DESCRIPTION, description)
    }
    fun getImage() : ParseFile?{
        return getParseFile(KEY_IMAGE)
    }
    fun setImage(parseFile: ParseFile){
        put(KEY_IMAGE, parseFile)
    }
    fun getUsername() : ParseUser?{
        return getParseUser(KEY_USERNAME)
    }
    fun setUsername(user: ParseUser){
        put(KEY_USERNAME, user)
    }
    companion object{
        const val KEY_DESCRIPTION = "Description"
        const val KEY_IMAGE = "Image"
        const val KEY_USERNAME = "Username"


    }
}