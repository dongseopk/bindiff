/*
 * Decompiled with CFR 0_115.
 */
package com.google.common.hash;

import com.google.common.hash.Hashing$1;
import com.google.common.hash.Hashing$ChecksumType;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

final class Hashing$ChecksumType$1
extends Hashing$ChecksumType {
    Hashing$ChecksumType$1(String string2, int n3, int n4) {
        super(string, n2, (int)string2, null);
    }

    @Override
    public Checksum get() {
        return new CRC32();
    }
}

