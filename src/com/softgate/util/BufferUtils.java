package com.softgate.util;

import java.nio.ByteBuffer;

public class BufferUtils {
	
    public static void putMediumInt(ByteBuffer buffer, int val) {
        buffer.put((byte) (val >> 16));
        buffer.put((byte) (val >> 8));
        buffer.put((byte) val);
    }

}
