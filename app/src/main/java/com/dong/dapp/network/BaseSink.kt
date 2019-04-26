package com.dong.dapp.network

import okio.*
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-26 13:51:51
 */
abstract class BaseSink :BufferedSink{
    override fun writeShort(s: Int): BufferedSink {
        return this
    }

    override fun writeString(string: String, charset: Charset): BufferedSink {
        return this
    }

    override fun writeString(string: String, beginIndex: Int, endIndex: Int, charset: Charset): BufferedSink {
        return this
    }

    override fun timeout(): Timeout? {
        return null
    }

    override fun write(byteString: ByteString): BufferedSink {
        return this
    }

    override fun write(source: ByteArray): BufferedSink {
        return this
    }

    override fun write(source: ByteArray, offset: Int, byteCount: Int): BufferedSink {
        return this
    }

    override fun write(source: Source, byteCount: Long): BufferedSink {
        return this
    }

    override fun write(source: Buffer, byteCount: Long) {
    }

    override fun write(src: ByteBuffer?): Int {
        return 0
    }

    override fun writeLongLe(v: Long): BufferedSink {
        return this
    }

    override fun flush() {
    }

    override fun writeShortLe(s: Int): BufferedSink {
        return this
    }

    override fun emit(): BufferedSink {
        return this
    }

    override fun isOpen(): Boolean {
        return false
    }

    override fun buffer(): Buffer? {
        return null
    }

    override fun writeUtf8(string: String): BufferedSink {
        return this
    }

    override fun writeUtf8(string: String, beginIndex: Int, endIndex: Int): BufferedSink {
        return this
    }

    override fun writeInt(i: Int): BufferedSink {
        return this
    }

    override fun writeAll(source: Source): Long {
        return 0
    }

    override fun close() {
    }

    override fun emitCompleteSegments(): BufferedSink {
        return this
    }

    override fun writeUtf8CodePoint(codePoint: Int): BufferedSink {
        return this
    }

    override fun outputStream(): OutputStream? {
        return null
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun writeLong(v: Long): BufferedSink {
        return this
    }

    override fun writeByte(b: Int): BufferedSink {
        return this
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun writeHexadecimalUnsignedLong(v: Long): BufferedSink {
        return this
    }

    override fun writeDecimalLong(v: Long): BufferedSink {
        return this
    }

    override fun writeIntLe(i: Int): BufferedSink {
        return this
    }
}