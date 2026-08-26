# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-verbose

# Preserve line numbers for debugging stack traces
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Keep click listeners
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# For enumeration classes, see http://proguard.sourceforge.net/FAQ.html#optimizing
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Parcelable implementations. Instantiated by the system.
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }
-keepclassmembers class * {
    *** Companion;
}
