# Logback Appender for Google Chat(Google Hangouts)
[![Release](https://jitpack.io/v/pemassi/logback-google-chat-appender.svg)](https://jitpack.io/#pemassi/logback-google-chat-appender)
![Gradle CI](https://github.com/pemassi/logback-google-chat-appender/actions/workflows/gradle-ci.yml/badge.svg)

This project is logback appender for Google Chat(Google Hangouts).

## Setup
Add the JitPack repository in your build.gradle (top level module):
```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

And add next dependencies in the build.gradle of the module:
```gradle
dependencies {
    implementation 'com.github.pemassi:logback-google-chat-appender:[VERSION TAG]' 
    
    //Example 
    implementation 'com.github.pemassi:logback-google-chat-appender:1.0.0'
}
```

## How to use?

```xml
<appender name="google" class="io.pemassi.logback.GoogleChatAppender">
    <!-- Put your web hook URI here -->
    <!-- You need to replace `&` letter to `&amp;` for `webhookUri` because `&` is reserved letter in XML. -->
    <webhookUri>YOUR WEB HOOK URI</webhookUri>
    
    <!-- Formatting -->
    <layout class="ch.qos.logback.classic.PatternLayout">
        <pattern>%-4relative [%thread] %-5level %class - %msg%n</pattern>
    </layout>
</appender>
```

>You need to replace `&` letter to `&amp;` for `webhookUri` because `&` is reserved letter in XML.
