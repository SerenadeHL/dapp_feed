extern "C" {
JNIEXPORT jstring
JNICALL Java_com_test_native_Main_getString(JNIEnv * env, jobject obj, jint index) {
  return env->NewStringUTF(sprintf("from native index: %d", index));
}
}